package cn.kevin.ims.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
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
@ApiModel(description = "基本实体")
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
    @ApiModelProperty(value = "创建时间", required = true)
    protected long utcCreate;
     /**
      * UTC修改时间
     * The Utc modify.
     */
     @ApiModelProperty(value = "修改时间", required = true)
    protected long utcModify;
    /**
     * 修改人
     * The Modify by.
     */
    @ApiModelProperty(value = "修改人", required = true)
    protected String modifyBy;
    /**
     * 是否有效
     * The Valid.
     */
    @ApiModelProperty(value = "是否有效", required = true)
    protected Boolean valid;
    /**
     * 备注
     * The Remark.
     */
    @ApiModelProperty(value = "备注", required = true)
    protected String remark;
}
