package cn.kevin.ims.entity;

import lombok.*;

import java.io.Serializable;

/**
 * 抽象POJO类
 * The type Abstract entity.
 * BaseEntity
 * @Author: Kevin
 * @Date: 2020 /6/2 3:42 下午
 */
@Data
public abstract class BaseEntity implements Serializable {
    /**
     * 序列化
     * The constant serialVersionUID.
     */
    protected static final long serialVersionUID = 4846381295033205916L;
    /**
     * UTC创建时间
     * The Utc create.
     */
    protected long utcCreate;
     /**
      * UTC修改时间
     * The Utc modify.
     */
    protected long utcModify;
    /**
     * 修改人
     * The Modify by.
     */
    protected String modifyBy;
    /**
     * 是否有效
     * The Valid.
     */
    protected Boolean valid;
    /**
     * 备注
     * The Remark.
     */
    protected String remark;
}
