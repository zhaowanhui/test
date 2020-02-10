package com.zwh.entity.po;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 案件
 * </p>
 *
 * @author zwh
 * @since 2019-08-03
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="Records对象", description="案件")
public class CaseOfCourt implements Serializable {

    @ApiModelProperty(value = "案件流水号，年YY+6位法院ID+8位流水号")
    private Long id;

    @ApiModelProperty(value = "案件名称")
    private String caseName;

    @ApiModelProperty(value = "代码规约化的案号，CaseNumber= CaseYear+CourtId+CaseType+CaseSubNumber")
    private String caseNumber;

    @ApiModelProperty(value = "法院提供的带中文字的案号")
    private String caseNumberWord;

    @ApiModelProperty(value = "曾用的案号，预字案号放在这个字段，多个用逗号分隔")
    private String caseNumberUsed;

    @ApiModelProperty(value = "关联案号，多个用逗号分隔")
    private String caseNumberRelated;

    @ApiModelProperty(value = "案件年份，取自案号的编年")
    private Integer caseYear;

    @ApiModelProperty(value = "案件序号，取自案号的顺序号")
    private Long caseSubNumber;

    @ApiModelProperty(value = "案由编码")
    private Integer caseCauseCode;

    @ApiModelProperty(value = "案由编码名称")
    private String caseCauseName;

    @ApiModelProperty(value = "案由内容(接口获取的id如无对应编码，则直接填写中文案由)")
    private String caseCause;

    @ApiModelProperty(value = "案件类型")
    private String caseTypeId;

    @ApiModelProperty(value = "案件类型名称")
    private String caseTypeName;

    @ApiModelProperty(value = "案件子类型")
    private String caseSubTypeId;

    @ApiModelProperty(value = "案件子类型名称")
    private String caseSubTypeName;

    @ApiModelProperty(value = "是否预审案件。预案的法院代字，用CourtPreWord字段表达")
    private Boolean isPretrialCase;

    @ApiModelProperty(value = "是否串案。串案的对应号码请查询CaseString表")
    private Boolean isStringCase;

    @ApiModelProperty(value = "承办部门/承办庭室")
    private Long courtDepartmentId;

    @ApiModelProperty(value = "承办部门/承办庭室名称")
    private String courtDepartmentName;

    @ApiModelProperty(value = "承办组或审判团队")
    private Integer courtCaseGroupId;

    @ApiModelProperty(value = "承办组或审判团队名称")
    private String courtCaseGroupName;

    @ApiModelProperty(value = "承办法官/承办人")
    private Long judgeId;

    @ApiModelProperty(value = "承办法官/承办人名称")
    private String judgeName;

    @ApiModelProperty(value = "原告人名称，多个用‘,’隔开")
    private String plaintiff;

    @ApiModelProperty(value = "被告人名称，多个用‘,’隔开")
    private String defendant;

    @ApiModelProperty(value = "收案部门/收案庭室")
    private Long receivingDepartmentId;

    @ApiModelProperty(value = "收案部门/收案庭室名称")
    private String receivingDepartmentName;

    @ApiModelProperty(value = "收案登记人")
    private Long receivingJudgeId;

    @ApiModelProperty(value = "收案登记人名称")
    private String receivingJudgeName;

    @ApiModelProperty(value = "收案来源/收案途径，如窗口立案。按照最高法院标准2015附件3收立案信息登记表, 从法院案件信息中获取的数据。")
    private String receivingSource;

    @ApiModelProperty(value = "案件信息来源: 1-抓取,2-接口推送, 3-手动输入，4-导入,255-其他")
    private Integer caseSource;

    @ApiModelProperty(value = "结案方式，直接输入中文字符串")
    private String closingmethod;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "收案时间")
    private Date receivingTime;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "立案时间")
    private Date filingTime;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "结案时间")
    private Date closingTime;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "开庭时间")
    private Date openingTime;

    @ApiModelProperty(value = "开庭地址")
    private Long openingAddressId;

    @ApiModelProperty(value = "标的数额，金额用分表示")
    private Long caseAmount;

    @ApiModelProperty(value = "案件等级（1.重大敏感案件,2.串案,3.小额诉讼, 4.要素案件,5请示案件）")
    private Integer caseLevel;

    @ApiModelProperty(value = "法定审限(天)，详见最高法院标准附件三：立案信息登记表")
    private Integer maximumTrialDays;

    @ApiModelProperty(value = "案件状态：0-已收案, 1-已立案，2-审理中，6-结案")
    private Integer caseStatus;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "案件开始审理时间")
    private Date startJudgeTime;

    @ApiModelProperty(value = "所属法院")
    private Integer courtId;

    @ApiModelProperty(value = "案件类型代字中文名")
    private String caseTypeWord;

    @ApiModelProperty(value = "案件类型代字编码TypeCode")
    private Integer caseTypeCode;

    @ApiModelProperty(value = "法院名称")
    private String courtName;

    @ApiModelProperty(value = "状态定义：-2：待删除，-1：无效，0：有效/正常，1：默认/未开通；2：停止， ")
    private Integer status;

    @ApiModelProperty(value = "分类")
    private Integer category;

    @ApiModelProperty(value = "备注")
    private String remark;

    @ApiModelProperty(value = "创建者id")
    private Long createdId;

    @ApiModelProperty(value = "创建者名字")
    private String createdName;

    @ApiModelProperty(value = "更新者id")
    private Long updatedId;

    @ApiModelProperty(value = "更新者名字")
    private String updatedName;

    @ApiModelProperty(value = "创建时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date created;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "更新时间")
    private Date updated;

    @ApiModelProperty(value = "删除标识 0-正常1-删除")
    private Boolean isDeleted;

    @ApiModelProperty(value = "同步标识 0-正常 1-待同步 2-同步中 4-同步异常 8-被同步")
    private Integer syncFlag;

    @ApiModelProperty(value = "记录版本")
    private Long rowVer;

    @ApiModelProperty(value = "分配时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date allotTime;

    @ApiModelProperty(value = "平台号代码")
    private String platformCode;

    private String processVo;

    private String processVoList;

    private String receiveTimeSum;


}
