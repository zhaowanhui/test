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
 * 案件信息数据
 * </p>
 *
 * @author zhaowanhui
 * @since 2019-8-22
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class PiCaseUserRel implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "ID", type = IdType.AUTO)
    private Integer id;

    private Integer ajid;

    private Integer puid;

    private Integer apuid;

    @TableField("Position")
    private Integer Position;

    @TableField("AddTime")
    private LocalDateTime AddTime;

    @TableField("Remark")
    private String Remark;


}
