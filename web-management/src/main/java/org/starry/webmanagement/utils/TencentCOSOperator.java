package org.starry.webmanagement.utils;

import java.io.ByteArrayInputStream;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

import com.qcloud.cos.COSClient;
import com.qcloud.cos.ClientConfig;
import com.qcloud.cos.auth.BasicCOSCredentials;
import com.qcloud.cos.auth.COSCredentials;
import com.qcloud.cos.exception.CosClientException;
import com.qcloud.cos.exception.CosServiceException;
import com.qcloud.cos.model.ObjectMetadata;
import com.qcloud.cos.model.PutObjectRequest;
import com.qcloud.cos.model.PutObjectResult;
import com.qcloud.cos.region.Region;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class TencentCOSOperator {
    private static String secretId = System.getenv("SECRETID");
    private static String secretKey = System.getenv("SECRETKEY");
    //    private static String bucketName = System.getenv("BUCKET_NAME");
    //    private static String region = System.getenv("REGION");

    @Value("${tencent.cos.endpoint}")
    private static String endpoint;
    @Value("${tencent.cos.bucketName}")
    private static String bucketName;
    @Value("${tencent.cos.region}")
    private static String region;



    private static COSClient createCli() {
     // 初始化用户身份信息(secretId, secretKey)
        COSCredentials cred = new BasicCOSCredentials(secretId,secretKey);
        // 设置 bucket 的区域, COS 地域的简称请参照 https://cloud.tencent.com/document/product/436/6224
        ClientConfig clientConfig = new ClientConfig(new Region(region));
        // 生成 cos 客户端
        return new COSClient(cred, clientConfig);
    }

    public String putLocalFile(byte[] content, String originalFilename) {


        //获取当前系统日期的字符串,格式为 yyyy/MM
        String dir = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy/MM"));
        //生成一个新的不重复的文件名
        String suffix = "";
        if (originalFilename != null && originalFilename.contains(".")) {
            suffix = originalFilename.substring(originalFilename.lastIndexOf("."));
        }
        String key = dir + "/" + UUID.randomUUID() + suffix;

        COSClient cosClient = createCli();
        ObjectMetadata metadata = new ObjectMetadata();
        metadata.setContentLength(content.length);

        PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName, key, new ByteArrayInputStream(content),metadata);
        try {
            PutObjectResult putObjectResult = cosClient.putObject(putObjectRequest);
            return "https://" + bucketName + ".cos." + region + endpoint + key;
            //System.out.println(putObjectResult.getRequestId());
        } catch (CosServiceException cse) {
            cse.printStackTrace();
        } catch (CosClientException cce) {
            cce.printStackTrace();
        } finally {
            cosClient.shutdown();
        }
        return null;
    }
}
