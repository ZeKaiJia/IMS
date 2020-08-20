package cn.kevin.ims.entity;

import lombok.*;

/**
 * 角色POJO类
 * Role
 * @Author: Kevin
 * @Date: 2020 /8/1 3:07 下午
 */
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
    private Integer id;
    /**
     * 角色描述
     * The Description.
     */
    private String description;
    /**
     * 角色名
     * The Role.
     */
    private String role;
}
