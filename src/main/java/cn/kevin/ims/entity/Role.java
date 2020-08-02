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
    private static final long serialVersionUID = 1L;
    /**
     * The Role id.
     */
    private String roleId;
    /**
     * The Role name.
     */
    private String roleName;
    /**
     * The Permissions.
     */
    private Set<Permission> permissions;
}
