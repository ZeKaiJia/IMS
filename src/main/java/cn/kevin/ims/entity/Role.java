package cn.kevin.ims.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

/**
 * 角色POJO类
 * Role
 * @Author: Kevin
 * @Date: 2020 /8/1 3:07 下午
 */
@ApiModel(description = "角色实体", value = "角色实体类")
@Getter
@Setter
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class Role extends BaseEntity {
    /**
     * 序列化
     * The constant serialVersionUID.
     */
    private static final long serialVersionUID = -1644537057173967996L;
    /**
     * 角色Id
     * The Id.
     */
    @ApiModelProperty(value = "角色号", required = true)
    private Integer id;
    /**
     * 角色描述
     * The Description.
     */
    @ApiModelProperty(value = "角色描述", required = true)
    private String description;
    /**
     * 角色名
     * The Role.
     */
    @ApiModelProperty(value = "角色名", required = true)
    private String role;
}
