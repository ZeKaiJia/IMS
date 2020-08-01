package cn.kevin.ims.entity;

import lombok.*;

import java.io.Serializable;

/**
 * The type Abstract entity.
 * AbstractEntity
 * @Author: Kevin
 * @Date: 2020 /6/2 3:42 下午
 */
@Data
public abstract class AbstractEntity implements Serializable {
    /**
     * 序列化参数
     */
    private static final long serialVersionUID = 1L;
    /**
     * 创建时间
     */
    private long utcCreate;
    /**
     * 更新时间
     */
    private long utcModify;
    /**
     * 是否有效
     */
    private Boolean isReal;
}
