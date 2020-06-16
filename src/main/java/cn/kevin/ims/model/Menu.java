package cn.kevin.ims.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * @Author: Kevin
 * @Date: 2020/6/16 4:09 下午
 */
@Data
@AllArgsConstructor
public class Menu {
    private Integer id;
    private String authName;
    private String path;
    private Menu children;
}
