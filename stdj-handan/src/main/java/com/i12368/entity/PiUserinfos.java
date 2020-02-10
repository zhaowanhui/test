package com.i12368.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>
 * 当事人数据信息
 * </p>
 *
 * @author zhaowanhui
 * @since 2019-8-22
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class PiUserinfos implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "PUID", type = IdType.AUTO)
    private Integer puid;

    private Integer ajid;

    @TableField("UserType")
    private Integer UserType;

    @TableField("UserName")
    private String UserName;

    @TableField("CardType")
    private Integer CardType;

    @TableField("CardNum")
    private String CardNum;

    @TableField("Address")
    private String Address;

    @TableField("Email")
    private String Email;

    @TableField("PostCode")
    private String PostCode;

    @TableField("TelPhone")
    private String TelPhone;

    @TableField("ZzJgdm")
    private String ZzJgdm;

    @TableField("Dwmc")
    private String Dwmc;

    @TableField("AgentName")
    private String AgentName;

    @TableField("AgentCardType")
    private String AgentCardType;

    @TableField("AgentCardNum")
    private String AgentCardNum;

    @TableField("AgentTelPhone")
    private String AgentTelPhone;

    @TableField("AgentEmail")
    private String AgentEmail;

    @TableField("AgentAddress")
    private String AgentAddress;

    @TableField("InfosSource")
    private Integer InfosSource;

    @TableField("Flag")
    private Integer Flag;

    @TableField("Remark")
    private String Remark;

    @TableField("AgentRelation")
    private String AgentRelation;


}
