package com.zwh.entity;

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
 * 账户管理 - 法院管理（对应组织管理）
 * </p>
 *
 * @author zzq
 * @since 2019-08-27
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class AcctCourt implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "CourtID", type = IdType.AUTO)
    private Integer CourtID;

    @TableField("CourtName")
    private String CourtName;

    @TableField("CourtCode")
    private String CourtCode;

    @TableField("CourtType")
    private String CourtType;

    @TableField("ParentCourtID")
    private Integer ParentCourtID;

    @TableField("ProvinceCode")
    private String ProvinceCode;

    @TableField("CityCode")
    private String CityCode;

    @TableField("Address")
    private String Address;

    @TableField("ShortName")
    private String ShortName;

    @TableField("LevelNum")
    private Integer LevelNum;

    @TableField("OrderNum")
    private Integer OrderNum;

    @TableField("PostCode")
    private String PostCode;

    @TableField("OfficePhone")
    private String OfficePhone;

    @TableField("Remark")
    private String Remark;

    @TableField("Status")
    private Integer Status;

    @TableField("AddTime")
    private LocalDateTime AddTime;


}
