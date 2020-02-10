package com.zwh.entity;

import java.util.Date;
import com.baomidou.mybatisplus.activerecord.Model;
import java.io.Serializable;

/**
 * <p>
 * 案件
 * </p>
 *
 * @author zwh
 * @since 2019-08-04
 */
public class Case_of_court extends Model<Case_of_court> {

    private static final long serialVersionUID = 1L;

    /**
     * 案件流水号，年YY+6位法院ID+8位流水号
     */
    private Long id;

    /**
     * 案件名称
     */
    private String case_name;

    /**
     * 代码规约化的案号，CaseNumber= CaseYear+CourtId+CaseType+CaseSubNumber
     */
    private String case_number;

    /**
     * 法院提供的带中文字的案号
     */
    private String case_number_word;

    /**
     * 曾用的案号，预字案号放在这个字段，多个用逗号分隔
     */
    private String case_number_used;

    /**
     * 关联案号，多个用逗号分隔
     */
    private String case_number_related;

    /**
     * 案件年份，取自案号的编年
     */
    private Integer case_year;

    /**
     * 案件序号，取自案号的顺序号
     */
    private Long case_sub_number;

    /**
     * 案由编码
     */
    private Integer case_cause_code;

    /**
     * 案由编码名称
     */
    private String case_cause_name;

    /**
     * 案由内容(接口获取的id如无对应编码，则直接填写中文案由)
     */
    private String case_cause;

    /**
     * 案件类型
     */
    private String case_type_id;

    /**
     * 案件类型名称
     */
    private String case_type_name;

    /**
     * 案件子类型
     */
    private String case_sub_type_id;

    /**
     * 案件子类型名称
     */
    private String case_sub_type_name;

    /**
     * 是否预审案件。预案的法院代字，用CourtPreWord字段表达
     */
    private Integer is_pretrial_case;

    /**
     * 是否串案。串案的对应号码请查询CaseString表
     */
    private Integer is_string_case;

    /**
     * 承办部门/承办庭室
     */
    private Long court_department_id;

    /**
     * 承办部门/承办庭室名称
     */
    private String court_department_name;

    /**
     * 承办组或审判团队
     */
    private Integer court_case_group_id;

    /**
     * 承办组或审判团队名称
     */
    private String court_case_group_name;

    /**
     * 承办法官/承办人
     */
    private Long judge_id;

    /**
     * 承办法官/承办人名称
     */
    private String judge_name;

    /**
     * 原告人名称，多个用‘,’隔开
     */
    private String plaintiff;

    /**
     * 被告人名称，多个用‘,’隔开
     */
    private String defendant;

    /**
     * 收案部门/收案庭室
     */
    private Long receiving_department_id;

    /**
     * 收案部门/收案庭室名称
     */
    private String receiving_department_name;

    /**
     * 收案登记人
     */
    private Long receiving_judge_id;

    /**
     * 收案登记人名称
     */
    private String receiving_judge_name;

    /**
     * 收案来源/收案途径，如窗口立案。按照最高法院标准2015附件3收立案信息登记表, 从法院案件信息中获取的数据。
     */
    private String receiving_source;

    /**
     * 案件信息来源: 1-抓取,2-接口推送, 3-手动输入，4-导入,255-其他
     */
    private Integer case_source;

    /**
     * 结案方式，直接输入中文字符串
     */
    private String closingmethod;

    /**
     * 收案时间
     */
    private Date receiving_time;

    /**
     * 立案时间
     */
    private Date filing_time;

    /**
     * 结案时间
     */
    private Date closing_time;

    /**
     * 开庭时间
     */
    private Date opening_time;

    /**
     * 开庭地址
     */
    private Long opening_address_id;

    /**
     * 标的数额，金额用分表示
     */
    private Long case_amount;

    /**
     * 案件等级（1.重大敏感案件,2.串案,3.小额诉讼, 4.要素案件,5请示案件）
     */
    private Integer case_level;

    /**
     * 法定审限(天)，详见最高法院标准附件三：立案信息登记表
     */
    private Integer maximum_trial_days;

    /**
     * 案件状态：0-已收案, 1-已立案，2-审理中，6-结案
     */
    private Integer case_status;

    /**
     * 案件开始审理时间
     */
    private Date start_judge_time;

    /**
     * 所属法院
     */
    private Integer court_id;

    /**
     * 案件类型代字中文名
     */
    private String case_type_word;

    /**
     * 案件类型代字编码TypeCode
     */
    private Integer case_type_code;

    /**
     * 法院名称
     */
    private String court_name;

    /**
     * 状态定义：-2：待删除，-1：无效，0：有效/正常，1：默认/未开通；2：停止， 
     */
    private Integer status;

    /**
     * 分类
     */
    private Integer category;

    /**
     * 备注
     */
    private String remark;

    /**
     * 创建者id
     */
    private Long created_id;

    /**
     * 创建者名字
     */
    private String created_name;

    /**
     * 更新者id
     */
    private Long updated_id;

    /**
     * 更新者名字
     */
    private String updated_name;

    /**
     * 创建时间
     */
    private Date created;

    /**
     * 更新时间
     */
    private Date updated;

    /**
     * 删除标识 0-正常1-删除
     */
    private Integer is_deleted;

    /**
     * 同步标识 0-正常 1-待同步 2-同步中 4-同步异常 8-被同步
     */
    private Integer sync_flag;

    /**
     * 记录版本
     */
    private Long row_ver;

    /**
     * 分配时间
     */
    private Date allot_time;

    /**
     * 平台号代码
     */
    private String platform_code;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public String getCase_name() {
        return case_name;
    }

    public void setCase_name(String case_name) {
        this.case_name = case_name;
    }
    public String getCase_number() {
        return case_number;
    }

    public void setCase_number(String case_number) {
        this.case_number = case_number;
    }
    public String getCase_number_word() {
        return case_number_word;
    }

    public void setCase_number_word(String case_number_word) {
        this.case_number_word = case_number_word;
    }
    public String getCase_number_used() {
        return case_number_used;
    }

    public void setCase_number_used(String case_number_used) {
        this.case_number_used = case_number_used;
    }
    public String getCase_number_related() {
        return case_number_related;
    }

    public void setCase_number_related(String case_number_related) {
        this.case_number_related = case_number_related;
    }
    public Integer getCase_year() {
        return case_year;
    }

    public void setCase_year(Integer case_year) {
        this.case_year = case_year;
    }
    public Long getCase_sub_number() {
        return case_sub_number;
    }

    public void setCase_sub_number(Long case_sub_number) {
        this.case_sub_number = case_sub_number;
    }
    public Integer getCase_cause_code() {
        return case_cause_code;
    }

    public void setCase_cause_code(Integer case_cause_code) {
        this.case_cause_code = case_cause_code;
    }
    public String getCase_cause_name() {
        return case_cause_name;
    }

    public void setCase_cause_name(String case_cause_name) {
        this.case_cause_name = case_cause_name;
    }
    public String getCase_cause() {
        return case_cause;
    }

    public void setCase_cause(String case_cause) {
        this.case_cause = case_cause;
    }
    public String getCase_type_id() {
        return case_type_id;
    }

    public void setCase_type_id(String case_type_id) {
        this.case_type_id = case_type_id;
    }
    public String getCase_type_name() {
        return case_type_name;
    }

    public void setCase_type_name(String case_type_name) {
        this.case_type_name = case_type_name;
    }
    public String getCase_sub_type_id() {
        return case_sub_type_id;
    }

    public void setCase_sub_type_id(String case_sub_type_id) {
        this.case_sub_type_id = case_sub_type_id;
    }
    public String getCase_sub_type_name() {
        return case_sub_type_name;
    }

    public void setCase_sub_type_name(String case_sub_type_name) {
        this.case_sub_type_name = case_sub_type_name;
    }
    public Integer getIs_pretrial_case() {
        return is_pretrial_case;
    }

    public void setIs_pretrial_case(Integer is_pretrial_case) {
        this.is_pretrial_case = is_pretrial_case;
    }
    public Integer getIs_string_case() {
        return is_string_case;
    }

    public void setIs_string_case(Integer is_string_case) {
        this.is_string_case = is_string_case;
    }
    public Long getCourt_department_id() {
        return court_department_id;
    }

    public void setCourt_department_id(Long court_department_id) {
        this.court_department_id = court_department_id;
    }
    public String getCourt_department_name() {
        return court_department_name;
    }

    public void setCourt_department_name(String court_department_name) {
        this.court_department_name = court_department_name;
    }
    public Integer getCourt_case_group_id() {
        return court_case_group_id;
    }

    public void setCourt_case_group_id(Integer court_case_group_id) {
        this.court_case_group_id = court_case_group_id;
    }
    public String getCourt_case_group_name() {
        return court_case_group_name;
    }

    public void setCourt_case_group_name(String court_case_group_name) {
        this.court_case_group_name = court_case_group_name;
    }
    public Long getJudge_id() {
        return judge_id;
    }

    public void setJudge_id(Long judge_id) {
        this.judge_id = judge_id;
    }
    public String getJudge_name() {
        return judge_name;
    }

    public void setJudge_name(String judge_name) {
        this.judge_name = judge_name;
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
    public Long getReceiving_department_id() {
        return receiving_department_id;
    }

    public void setReceiving_department_id(Long receiving_department_id) {
        this.receiving_department_id = receiving_department_id;
    }
    public String getReceiving_department_name() {
        return receiving_department_name;
    }

    public void setReceiving_department_name(String receiving_department_name) {
        this.receiving_department_name = receiving_department_name;
    }
    public Long getReceiving_judge_id() {
        return receiving_judge_id;
    }

    public void setReceiving_judge_id(Long receiving_judge_id) {
        this.receiving_judge_id = receiving_judge_id;
    }
    public String getReceiving_judge_name() {
        return receiving_judge_name;
    }

    public void setReceiving_judge_name(String receiving_judge_name) {
        this.receiving_judge_name = receiving_judge_name;
    }
    public String getReceiving_source() {
        return receiving_source;
    }

    public void setReceiving_source(String receiving_source) {
        this.receiving_source = receiving_source;
    }
    public Integer getCase_source() {
        return case_source;
    }

    public void setCase_source(Integer case_source) {
        this.case_source = case_source;
    }
    public String getClosingmethod() {
        return closingmethod;
    }

    public void setClosingmethod(String closingmethod) {
        this.closingmethod = closingmethod;
    }
    public Date getReceiving_time() {
        return receiving_time;
    }

    public void setReceiving_time(Date receiving_time) {
        this.receiving_time = receiving_time;
    }
    public Date getFiling_time() {
        return filing_time;
    }

    public void setFiling_time(Date filing_time) {
        this.filing_time = filing_time;
    }
    public Date getClosing_time() {
        return closing_time;
    }

    public void setClosing_time(Date closing_time) {
        this.closing_time = closing_time;
    }
    public Date getOpening_time() {
        return opening_time;
    }

    public void setOpening_time(Date opening_time) {
        this.opening_time = opening_time;
    }
    public Long getOpening_address_id() {
        return opening_address_id;
    }

    public void setOpening_address_id(Long opening_address_id) {
        this.opening_address_id = opening_address_id;
    }
    public Long getCase_amount() {
        return case_amount;
    }

    public void setCase_amount(Long case_amount) {
        this.case_amount = case_amount;
    }
    public Integer getCase_level() {
        return case_level;
    }

    public void setCase_level(Integer case_level) {
        this.case_level = case_level;
    }
    public Integer getMaximum_trial_days() {
        return maximum_trial_days;
    }

    public void setMaximum_trial_days(Integer maximum_trial_days) {
        this.maximum_trial_days = maximum_trial_days;
    }
    public Integer getCase_status() {
        return case_status;
    }

    public void setCase_status(Integer case_status) {
        this.case_status = case_status;
    }
    public Date getStart_judge_time() {
        return start_judge_time;
    }

    public void setStart_judge_time(Date start_judge_time) {
        this.start_judge_time = start_judge_time;
    }
    public Integer getCourt_id() {
        return court_id;
    }

    public void setCourt_id(Integer court_id) {
        this.court_id = court_id;
    }
    public String getCase_type_word() {
        return case_type_word;
    }

    public void setCase_type_word(String case_type_word) {
        this.case_type_word = case_type_word;
    }
    public Integer getCase_type_code() {
        return case_type_code;
    }

    public void setCase_type_code(Integer case_type_code) {
        this.case_type_code = case_type_code;
    }
    public String getCourt_name() {
        return court_name;
    }

    public void setCourt_name(String court_name) {
        this.court_name = court_name;
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
    public Long getCreated_id() {
        return created_id;
    }

    public void setCreated_id(Long created_id) {
        this.created_id = created_id;
    }
    public String getCreated_name() {
        return created_name;
    }

    public void setCreated_name(String created_name) {
        this.created_name = created_name;
    }
    public Long getUpdated_id() {
        return updated_id;
    }

    public void setUpdated_id(Long updated_id) {
        this.updated_id = updated_id;
    }
    public String getUpdated_name() {
        return updated_name;
    }

    public void setUpdated_name(String updated_name) {
        this.updated_name = updated_name;
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
    public Integer getIs_deleted() {
        return is_deleted;
    }

    public void setIs_deleted(Integer is_deleted) {
        this.is_deleted = is_deleted;
    }
    public Integer getSync_flag() {
        return sync_flag;
    }

    public void setSync_flag(Integer sync_flag) {
        this.sync_flag = sync_flag;
    }
    public Long getRow_ver() {
        return row_ver;
    }

    public void setRow_ver(Long row_ver) {
        this.row_ver = row_ver;
    }
    public Date getAllot_time() {
        return allot_time;
    }

    public void setAllot_time(Date allot_time) {
        this.allot_time = allot_time;
    }
    public String getPlatform_code() {
        return platform_code;
    }

    public void setPlatform_code(String platform_code) {
        this.platform_code = platform_code;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "Case_of_court{" +
        "id=" + id +
        ", case_name=" + case_name +
        ", case_number=" + case_number +
        ", case_number_word=" + case_number_word +
        ", case_number_used=" + case_number_used +
        ", case_number_related=" + case_number_related +
        ", case_year=" + case_year +
        ", case_sub_number=" + case_sub_number +
        ", case_cause_code=" + case_cause_code +
        ", case_cause_name=" + case_cause_name +
        ", case_cause=" + case_cause +
        ", case_type_id=" + case_type_id +
        ", case_type_name=" + case_type_name +
        ", case_sub_type_id=" + case_sub_type_id +
        ", case_sub_type_name=" + case_sub_type_name +
        ", is_pretrial_case=" + is_pretrial_case +
        ", is_string_case=" + is_string_case +
        ", court_department_id=" + court_department_id +
        ", court_department_name=" + court_department_name +
        ", court_case_group_id=" + court_case_group_id +
        ", court_case_group_name=" + court_case_group_name +
        ", judge_id=" + judge_id +
        ", judge_name=" + judge_name +
        ", plaintiff=" + plaintiff +
        ", defendant=" + defendant +
        ", receiving_department_id=" + receiving_department_id +
        ", receiving_department_name=" + receiving_department_name +
        ", receiving_judge_id=" + receiving_judge_id +
        ", receiving_judge_name=" + receiving_judge_name +
        ", receiving_source=" + receiving_source +
        ", case_source=" + case_source +
        ", closingmethod=" + closingmethod +
        ", receiving_time=" + receiving_time +
        ", filing_time=" + filing_time +
        ", closing_time=" + closing_time +
        ", opening_time=" + opening_time +
        ", opening_address_id=" + opening_address_id +
        ", case_amount=" + case_amount +
        ", case_level=" + case_level +
        ", maximum_trial_days=" + maximum_trial_days +
        ", case_status=" + case_status +
        ", start_judge_time=" + start_judge_time +
        ", court_id=" + court_id +
        ", case_type_word=" + case_type_word +
        ", case_type_code=" + case_type_code +
        ", court_name=" + court_name +
        ", status=" + status +
        ", category=" + category +
        ", remark=" + remark +
        ", created_id=" + created_id +
        ", created_name=" + created_name +
        ", updated_id=" + updated_id +
        ", updated_name=" + updated_name +
        ", created=" + created +
        ", updated=" + updated +
        ", is_deleted=" + is_deleted +
        ", sync_flag=" + sync_flag +
        ", row_ver=" + row_ver +
        ", allot_time=" + allot_time +
        ", platform_code=" + platform_code +
        "}";
    }
}
