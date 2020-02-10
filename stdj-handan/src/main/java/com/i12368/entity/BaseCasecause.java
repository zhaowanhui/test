package com.i12368.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 基础数据 - 案由管理
 * </p>
 *
 * @author zhaowanhui
 * @since 2019-8-22
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class BaseCasecause implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "CCID", type = IdType.AUTO)
    private Integer ccid;

    @TableField("CCName")
    private String CCName;

    @TableField("ParentCCID")
    private Integer ParentCCID;

    @TableField("CTCode")
    private Integer CTCode;

    @TableField("CCType")
    private String CCType;

    @TableField("CCCode")
    private Integer CCCode;

    @TableField("OrderNum")
    private String OrderNum;

    @TableField("Status")
    private String Status;

    @TableField("AddTime")
    private LocalDateTime AddTime;


}
