package cn.kevin.ims.entity;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * @Author: Kevin
 * @Date: 2020/6/2 3:42 下午
 */
@Getter
@Setter
public abstract class AbstractEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    /** 创建时间 */
    private long utcCreate;
    /** 更新时间 */
    private long utcModify;
    /** 是否有效 */
    private Boolean isReal;

    public AbstractEntity() {
        isReal = true;
    }

    public AbstractEntity(long utcCreate, long utcModify) {
        this.utcCreate = utcCreate;
        this.utcModify = utcModify;
        this.isReal = true;
    }
}
