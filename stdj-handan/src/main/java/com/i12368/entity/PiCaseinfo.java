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
public class PiCaseinfo implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "AJID", type = IdType.AUTO)
    private Integer ajid;

    @TableField("Ah")
    private String Ah;

    @TableField("Ajlb")
    private String Ajlb;

    @TableField("Cbfyid")
    private String Cbfyid=""; //邯郸

    @TableField("Cbts")
    private String Cbts;

    @TableField("Cbr")
    private String Cbr;

    @TableField("Cbrmc")
    private String Cbrmc;

    @TableField("CCCode")
    private Integer CCCode;

    @TableField("FilingTime")
    private LocalDateTime FilingTime;

    @TableField("LimitTime")
    private LocalDateTime LimitTime;

    @TableField("Amount")
    private String Amount;

    @TableField("Remark")
    private String Remark;

    @TableField("Ajbh")
    private Integer Ajbh;

    public PiCaseinfo(String ah) {
        Ah = ah;
    }

    public PiCaseinfo(String ah, String cbfyid) {
        Ah = ah;
        Cbfyid = cbfyid;
    }

    public PiCaseinfo() {
    }
}
