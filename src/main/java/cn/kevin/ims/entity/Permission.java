package cn.kevin.ims.entity;

import lombok.*;

/**
 * 权限
 * Permission
 * @Author: Kevin
 * @Date: 2020 /8/1 9:11 下午
 */
@Getter
@Setter
@ToString
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class Permission extends AbstractEntity {
    private static final long serialVersionUID = 8482551052982019660L;
    private Integer id;
    private String name;
    private String permission;
    private String url;
}
