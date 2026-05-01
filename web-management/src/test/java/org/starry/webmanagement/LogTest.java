package org.starry.webmanagement;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;

import java.time.LocalDateTime;

public class LogTest {

    private static final Logger logger = org.slf4j.LoggerFactory.getLogger(LogTest.class);
    @Test
    public void testLog(){
//        System.out.println(LocalDateTime.now() + " : Start...");
        logger.debug("Start...");
        int sum = 0;
        int[] nums = {1, 5, 3, 2, 1, 4, 5, 4, 6, 7, 4, 34, 2, 23};
        for (int num : nums) {
            sum += num;
        }
        logger.info("Result: {}", sum);
        logger.debug("Finished");
        
//        System.out.println("Result: "+sum);
//        System.out.println(LocalDateTime.now() + "Finished...");
    }
}