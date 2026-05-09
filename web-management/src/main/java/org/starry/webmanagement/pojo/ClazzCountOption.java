package org.starry.webmanagement.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClazzCountOption {
    private List<Object> clazzList;
    private List<Object> dataList;
}
