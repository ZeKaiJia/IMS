package cn.kevin.ims.entity;

import lombok.*;

import java.util.Set;

/**
 * 角色
 * The type Role.
 * Role
 * @Author: Kevin
 * @Date: 2020 /8/1 3:07 下午
 */
@Getter
@Setter
@ToString
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class Role extends AbstractEntity {
    /**
     * The constant serialVersionUID.
     */
    private static final long serialVersionUID = -1644537057173967996L;
    /**
     * 序号
     */
    private Integer id;
    /**
     * 描述
     */
    private String description;
    /**
     * 角色
     */
    private String role;
}
