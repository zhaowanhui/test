package com.zwh.entity.po;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

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
public class CaseOfCourt implements Serializable {

    private Long id;

    private String caseName;

    private String caseNumber;

    private String caseNumberWord;

    private String caseNumberUsed;

    private String caseNumberRelated;

    private int caseYear;

    private Long caseSubNumber;

    private Integer caseCauseCode;

    private String caseCauseName;

    private String caseCause;

    private String caseTypeId;

    private String caseTypeName;

    private String caseSubTypeId;

    private String caseSubTypeName;

    private Boolean isPretrialCase;

    private Boolean isStringCase;

    private Long courtDepartmentId;

    private String courtDepartmentName;

    private Integer courtCaseGroupId;

    private String courtCaseGroupName;

    private Long judgeId;

    private String judgeName;

    private String plaintiff;

    private String defendant;

    private Long receivingDepartmentId;

    private String receivingDepartmentName;

    private Long receivingJudgeId;

    private String receivingJudgeName;

    private String receivingSource;

    private Integer caseSource;

    private String closingmethod;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date receivingTime;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date filingTime;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date closingTime;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date openingTime;

    private Long openingAddressId;

    private Long caseAmount;

    private Integer caseLevel;

    private Integer maximumTrialDays;

    private Integer caseStatus;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date startJudgeTime;

    private Integer courtId;

    private String caseTypeWord;

    private Integer caseTypeCode;

    private String courtName;

    private Integer status;

    private Integer category;

    private String remark;

    private Long createdId;

    private String createdName;

    private Long updatedId;

    private String updatedName;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date created;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updated;

    private Boolean isDeleted;

    private Integer syncFlag;

    private Long rowVer;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date allotTime;

    private String platformCode;

    private String processVo;

    private String processVoList;

    private String receiveTimeSum;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCaseName() {
        return caseName;
    }

    public void setCaseName(String caseName) {
        this.caseName = caseName;
    }

    public String getCaseNumber() {
        return caseNumber;
    }

    public void setCaseNumber(String caseNumber) {
        this.caseNumber = caseNumber;
    }

    public String getCaseNumberWord() {
        return caseNumberWord;
    }

    public void setCaseNumberWord(String caseNumberWord) {
        this.caseNumberWord = caseNumberWord;
    }

    public String getCaseNumberUsed() {
        return caseNumberUsed;
    }

    public void setCaseNumberUsed(String caseNumberUsed) {
        this.caseNumberUsed = caseNumberUsed;
    }

    public String getCaseNumberRelated() {
        return caseNumberRelated;
    }

    public void setCaseNumberRelated(String caseNumberRelated) {
        this.caseNumberRelated = caseNumberRelated;
    }

    public Integer getCaseYear() {
        return caseYear;
    }

    public void setCaseYear(Integer caseYear) {
        this.caseYear = caseYear;
    }

    public Long getCaseSubNumber() {
        return caseSubNumber;
    }

    public void setCaseSubNumber(Long caseSubNumber) {
        this.caseSubNumber = caseSubNumber;
    }

    public Integer getCaseCauseCode() {
        return caseCauseCode;
    }

    public void setCaseCauseCode(Integer caseCauseCode) {
        this.caseCauseCode = caseCauseCode;
    }

    public String getCaseCauseName() {
        return caseCauseName;
    }

    public void setCaseCauseName(String caseCauseName) {
        this.caseCauseName = caseCauseName;
    }

    public String getCaseCause() {
        return caseCause;
    }

    public void setCaseCause(String caseCause) {
        this.caseCause = caseCause;
    }

    public String getCaseTypeId() {
        return caseTypeId;
    }

    public void setCaseTypeId(String caseTypeId) {
        this.caseTypeId = caseTypeId;
    }

    public String getCaseTypeName() {
        return caseTypeName;
    }

    public void setCaseTypeName(String caseTypeName) {
        this.caseTypeName = caseTypeName;
    }

    public String getCaseSubTypeId() {
        return caseSubTypeId;
    }

    public void setCaseSubTypeId(String caseSubTypeId) {
        this.caseSubTypeId = caseSubTypeId;
    }

    public String getCaseSubTypeName() {
        return caseSubTypeName;
    }

    public void setCaseSubTypeName(String caseSubTypeName) {
        this.caseSubTypeName = caseSubTypeName;
    }

    public Boolean getPretrialCase() {
        return isPretrialCase;
    }

    public void setPretrialCase(Boolean pretrialCase) {
        isPretrialCase = pretrialCase;
    }

    public Boolean getStringCase() {
        return isStringCase;
    }

    public void setStringCase(Boolean stringCase) {
        isStringCase = stringCase;
    }

    public Long getCourtDepartmentId() {
        return courtDepartmentId;
    }

    public void setCourtDepartmentId(Long courtDepartmentId) {
        this.courtDepartmentId = courtDepartmentId;
    }

    public String getCourtDepartmentName() {
        return courtDepartmentName;
    }

    public void setCourtDepartmentName(String courtDepartmentName) {
        this.courtDepartmentName = courtDepartmentName;
    }

    public Integer getCourtCaseGroupId() {
        return courtCaseGroupId;
    }

    public void setCourtCaseGroupId(Integer courtCaseGroupId) {
        this.courtCaseGroupId = courtCaseGroupId;
    }

    public String getCourtCaseGroupName() {
        return courtCaseGroupName;
    }

    public void setCourtCaseGroupName(String courtCaseGroupName) {
        this.courtCaseGroupName = courtCaseGroupName;
    }

    public Long getJudgeId() {
        return judgeId;
    }

    public void setJudgeId(Long judgeId) {
        this.judgeId = judgeId;
    }

    public String getJudgeName() {
        return judgeName;
    }

    public void setJudgeName(String judgeName) {
        this.judgeName = judgeName;
    }

    public String getPlaintiff() {
        return plaintiff;
    }

    public void setPlaintiff(String plaintiff) {
        this.plaintiff = plaintiff;
    }

    public String getDefendant() {
        return defendant;
    }

    public void setDefendant(String defendant) {
        this.defendant = defendant;
    }

    public Long getReceivingDepartmentId() {
        return receivingDepartmentId;
    }

    public void setReceivingDepartmentId(Long receivingDepartmentId) {
        this.receivingDepartmentId = receivingDepartmentId;
    }

    public String getReceivingDepartmentName() {
        return receivingDepartmentName;
    }

    public void setReceivingDepartmentName(String receivingDepartmentName) {
        this.receivingDepartmentName = receivingDepartmentName;
    }

    public Long getReceivingJudgeId() {
        return receivingJudgeId;
    }

    public void setReceivingJudgeId(Long receivingJudgeId) {
        this.receivingJudgeId = receivingJudgeId;
    }

    public String getReceivingJudgeName() {
        return receivingJudgeName;
    }

    public void setReceivingJudgeName(String receivingJudgeName) {
        this.receivingJudgeName = receivingJudgeName;
    }

    public String getReceivingSource() {
        return receivingSource;
    }

    public void setReceivingSource(String receivingSource) {
        this.receivingSource = receivingSource;
    }

    public Integer getCaseSource() {
        return caseSource;
    }

    public void setCaseSource(Integer caseSource) {
        this.caseSource = caseSource;
    }

    public String getClosingmethod() {
        return closingmethod;
    }

    public void setClosingmethod(String closingmethod) {
        this.closingmethod = closingmethod;
    }

    public Date getReceivingTime() {
        return receivingTime;
    }

    public void setReceivingTime(Date receivingTime) {
        this.receivingTime = receivingTime;
    }

    public Date getFilingTime() {
        return filingTime;
    }

    public void setFilingTime(Date filingTime) {
        this.filingTime = filingTime;
    }

    public Date getClosingTime() {
        return closingTime;
    }

    public void setClosingTime(Date closingTime) {
        this.closingTime = closingTime;
    }

    public Date getOpeningTime() {
        return openingTime;
    }

    public void setOpeningTime(Date openingTime) {
        this.openingTime = openingTime;
    }

    public Long getOpeningAddressId() {
        return openingAddressId;
    }

    public void setOpeningAddressId(Long openingAddressId) {
        this.openingAddressId = openingAddressId;
    }

    public Long getCaseAmount() {
        return caseAmount;
    }

    public void setCaseAmount(Long caseAmount) {
        this.caseAmount = caseAmount;
    }

    public Integer getCaseLevel() {
        return caseLevel;
    }

    public void setCaseLevel(Integer caseLevel) {
        this.caseLevel = caseLevel;
    }

    public Integer getMaximumTrialDays() {
        return maximumTrialDays;
    }

    public void setMaximumTrialDays(Integer maximumTrialDays) {
        this.maximumTrialDays = maximumTrialDays;
    }

    public Integer getCaseStatus() {
        return caseStatus;
    }

    public void setCaseStatus(Integer caseStatus) {
        this.caseStatus = caseStatus;
    }

    public Date getStartJudgeTime() {
        return startJudgeTime;
    }

    public void setStartJudgeTime(Date startJudgeTime) {
        this.startJudgeTime = startJudgeTime;
    }

    public Integer getCourtId() {
        return courtId;
    }

    public void setCourtId(Integer courtId) {
        this.courtId = courtId;
    }

    public String getCaseTypeWord() {
        return caseTypeWord;
    }

    public void setCaseTypeWord(String caseTypeWord) {
        this.caseTypeWord = caseTypeWord;
    }

    public Integer getCaseTypeCode() {
        return caseTypeCode;
    }

    public void setCaseTypeCode(Integer caseTypeCode) {
        this.caseTypeCode = caseTypeCode;
    }

    public String getCourtName() {
        return courtName;
    }

    public void setCourtName(String courtName) {
        this.courtName = courtName;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getCategory() {
        return category;
    }

    public void setCategory(Integer category) {
        this.category = category;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Long getCreatedId() {
        return createdId;
    }

    public void setCreatedId(Long createdId) {
        this.createdId = createdId;
    }

    public String getCreatedName() {
        return createdName;
    }

    public void setCreatedName(String createdName) {
        this.createdName = createdName;
    }

    public Long getUpdatedId() {
        return updatedId;
    }

    public void setUpdatedId(Long updatedId) {
        this.updatedId = updatedId;
    }

    public String getUpdatedName() {
        return updatedName;
    }

    public void setUpdatedName(String updatedName) {
        this.updatedName = updatedName;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Date getUpdated() {
        return updated;
    }

    public void setUpdated(Date updated) {
        this.updated = updated;
    }

    public Boolean getDeleted() {
        return isDeleted;
    }

    public void setDeleted(Boolean deleted) {
        isDeleted = deleted;
    }

    public Integer getSyncFlag() {
        return syncFlag;
    }

    public void setSyncFlag(Integer syncFlag) {
        this.syncFlag = syncFlag;
    }

    public Long getRowVer() {
        return rowVer;
    }

    public void setRowVer(Long rowVer) {
        this.rowVer = rowVer;
    }

    public Date getAllotTime() {
        return allotTime;
    }

    public void setAllotTime(Date allotTime) {
        this.allotTime = allotTime;
    }

    public String getPlatformCode() {
        return platformCode;
    }

    public void setPlatformCode(String platformCode) {
        this.platformCode = platformCode;
    }

    public String getProcessVo() {
        return processVo;
    }

    public void setProcessVo(String processVo) {
        this.processVo = processVo;
    }

    public String getProcessVoList() {
        return processVoList;
    }

    public void setProcessVoList(String processVoList) {
        this.processVoList = processVoList;
    }

    public String getReceiveTimeSum() {
        return receiveTimeSum;
    }

    public void setReceiveTimeSum(String receiveTimeSum) {
        this.receiveTimeSum = receiveTimeSum;
    }
}
