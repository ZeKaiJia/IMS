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
    /**
     * The constant serialVersionUID.
     */
    private static final long serialVersionUID = 1L;
    /**
     * The Per id.
     */
    private String perId;
    /**
     * The Per name.
     */
    private String perName;
}
