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
@ApiModel(description = "权限实体", value = "权限实体类")
@Getter
@Setter
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
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
    @ApiModelProperty(value = "权限号", required = true)
    private Integer id;
    /**
     * 权限名
     * The Name.
     */
    @ApiModelProperty(value = "权限名", required = true)
    private String name;
    /**
     * 权限代码
     * The Permission.
     */
    @ApiModelProperty(value = "权限代码", required = true)
    private String permission;
    /**
     * 权限Url
     * The Url.
     */
    @ApiModelProperty(value = "权限Url", required = true)
    private String url;
}
