package cn.kevin.ims.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

/**
 * 权限POJO类
 * Permission
 * @Author: Kevin
 * @Date: 2020 /8/1 9:11 下午
 */
@Getter
@Setter
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(description = "权限对象")
public class Permission extends BaseEntity {
    /**
     * 序列化
     * The constant serialVersionUID.
     */
    private static final long serialVersionUID = 8482551052982019660L;
    /**
     * 权限Id
     * The Id.
     */
    private Integer id;
    /**
     * 权限名
     * The Name.
     */
    private String name;
    /**
     * 权限代码
     * The Permission.
     */
    private String permission;
    /**
     * 权限Url
     * The Url.
     */
    private String url;
}
