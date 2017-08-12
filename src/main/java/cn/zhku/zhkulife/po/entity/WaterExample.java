package cn.zhku.zhkulife.po.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class WaterExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public WaterExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andWaterIdIsNull() {
            addCriterion("water_id is null");
            return (Criteria) this;
        }

        public Criteria andWaterIdIsNotNull() {
            addCriterion("water_id is not null");
            return (Criteria) this;
        }

        public Criteria andWaterIdEqualTo(String value) {
            addCriterion("water_id =", value, "waterId");
            return (Criteria) this;
        }

        public Criteria andWaterIdNotEqualTo(String value) {
            addCriterion("water_id <>", value, "waterId");
            return (Criteria) this;
        }

        public Criteria andWaterIdGreaterThan(String value) {
            addCriterion("water_id >", value, "waterId");
            return (Criteria) this;
        }

        public Criteria andWaterIdGreaterThanOrEqualTo(String value) {
            addCriterion("water_id >=", value, "waterId");
            return (Criteria) this;
        }

        public Criteria andWaterIdLessThan(String value) {
            addCriterion("water_id <", value, "waterId");
            return (Criteria) this;
        }

        public Criteria andWaterIdLessThanOrEqualTo(String value) {
            addCriterion("water_id <=", value, "waterId");
            return (Criteria) this;
        }

        public Criteria andWaterIdLike(String value) {
            addCriterion("water_id like", value, "waterId");
            return (Criteria) this;
        }

        public Criteria andWaterIdNotLike(String value) {
            addCriterion("water_id not like", value, "waterId");
            return (Criteria) this;
        }

        public Criteria andWaterIdIn(List<String> values) {
            addCriterion("water_id in", values, "waterId");
            return (Criteria) this;
        }

        public Criteria andWaterIdNotIn(List<String> values) {
            addCriterion("water_id not in", values, "waterId");
            return (Criteria) this;
        }

        public Criteria andWaterIdBetween(String value1, String value2) {
            addCriterion("water_id between", value1, value2, "waterId");
            return (Criteria) this;
        }

        public Criteria andWaterIdNotBetween(String value1, String value2) {
            addCriterion("water_id not between", value1, value2, "waterId");
            return (Criteria) this;
        }

        public Criteria andWaterNumIsNull() {
            addCriterion("water_num is null");
            return (Criteria) this;
        }

        public Criteria andWaterNumIsNotNull() {
            addCriterion("water_num is not null");
            return (Criteria) this;
        }

        public Criteria andWaterNumEqualTo(Integer value) {
            addCriterion("water_num =", value, "waterNum");
            return (Criteria) this;
        }

        public Criteria andWaterNumNotEqualTo(Integer value) {
            addCriterion("water_num <>", value, "waterNum");
            return (Criteria) this;
        }

        public Criteria andWaterNumGreaterThan(Integer value) {
            addCriterion("water_num >", value, "waterNum");
            return (Criteria) this;
        }

        public Criteria andWaterNumGreaterThanOrEqualTo(Integer value) {
            addCriterion("water_num >=", value, "waterNum");
            return (Criteria) this;
        }

        public Criteria andWaterNumLessThan(Integer value) {
            addCriterion("water_num <", value, "waterNum");
            return (Criteria) this;
        }

        public Criteria andWaterNumLessThanOrEqualTo(Integer value) {
            addCriterion("water_num <=", value, "waterNum");
            return (Criteria) this;
        }

        public Criteria andWaterNumIn(List<Integer> values) {
            addCriterion("water_num in", values, "waterNum");
            return (Criteria) this;
        }

        public Criteria andWaterNumNotIn(List<Integer> values) {
            addCriterion("water_num not in", values, "waterNum");
            return (Criteria) this;
        }

        public Criteria andWaterNumBetween(Integer value1, Integer value2) {
            addCriterion("water_num between", value1, value2, "waterNum");
            return (Criteria) this;
        }

        public Criteria andWaterNumNotBetween(Integer value1, Integer value2) {
            addCriterion("water_num not between", value1, value2, "waterNum");
            return (Criteria) this;
        }

        public Criteria andOperateTimeIsNull() {
            addCriterion("operate_time is null");
            return (Criteria) this;
        }

        public Criteria andOperateTimeIsNotNull() {
            addCriterion("operate_time is not null");
            return (Criteria) this;
        }

        public Criteria andOperateTimeEqualTo(Date value) {
            addCriterion("operate_time =", value, "operateTime");
            return (Criteria) this;
        }

        public Criteria andOperateTimeNotEqualTo(Date value) {
            addCriterion("operate_time <>", value, "operateTime");
            return (Criteria) this;
        }

        public Criteria andOperateTimeGreaterThan(Date value) {
            addCriterion("operate_time >", value, "operateTime");
            return (Criteria) this;
        }

        public Criteria andOperateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("operate_time >=", value, "operateTime");
            return (Criteria) this;
        }

        public Criteria andOperateTimeLessThan(Date value) {
            addCriterion("operate_time <", value, "operateTime");
            return (Criteria) this;
        }

        public Criteria andOperateTimeLessThanOrEqualTo(Date value) {
            addCriterion("operate_time <=", value, "operateTime");
            return (Criteria) this;
        }

        public Criteria andOperateTimeIn(List<Date> values) {
            addCriterion("operate_time in", values, "operateTime");
            return (Criteria) this;
        }

        public Criteria andOperateTimeNotIn(List<Date> values) {
            addCriterion("operate_time not in", values, "operateTime");
            return (Criteria) this;
        }

        public Criteria andOperateTimeBetween(Date value1, Date value2) {
            addCriterion("operate_time between", value1, value2, "operateTime");
            return (Criteria) this;
        }

        public Criteria andOperateTimeNotBetween(Date value1, Date value2) {
            addCriterion("operate_time not between", value1, value2, "operateTime");
            return (Criteria) this;
        }

        public Criteria andWaterTimeIsNull() {
            addCriterion("water_time is null");
            return (Criteria) this;
        }

        public Criteria andWaterTimeIsNotNull() {
            addCriterion("water_time is not null");
            return (Criteria) this;
        }

        public Criteria andWaterTimeEqualTo(Date value) {
            addCriterion("water_time =", value, "waterTime");
            return (Criteria) this;
        }

        public Criteria andWaterTimeNotEqualTo(Date value) {
            addCriterion("water_time <>", value, "waterTime");
            return (Criteria) this;
        }

        public Criteria andWaterTimeGreaterThan(Date value) {
            addCriterion("water_time >", value, "waterTime");
            return (Criteria) this;
        }

        public Criteria andWaterTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("water_time >=", value, "waterTime");
            return (Criteria) this;
        }

        public Criteria andWaterTimeLessThan(Date value) {
            addCriterion("water_time <", value, "waterTime");
            return (Criteria) this;
        }

        public Criteria andWaterTimeLessThanOrEqualTo(Date value) {
            addCriterion("water_time <=", value, "waterTime");
            return (Criteria) this;
        }

        public Criteria andWaterTimeIn(List<Date> values) {
            addCriterion("water_time in", values, "waterTime");
            return (Criteria) this;
        }

        public Criteria andWaterTimeNotIn(List<Date> values) {
            addCriterion("water_time not in", values, "waterTime");
            return (Criteria) this;
        }

        public Criteria andWaterTimeBetween(Date value1, Date value2) {
            addCriterion("water_time between", value1, value2, "waterTime");
            return (Criteria) this;
        }

        public Criteria andWaterTimeNotBetween(Date value1, Date value2) {
            addCriterion("water_time not between", value1, value2, "waterTime");
            return (Criteria) this;
        }

        public Criteria andWaterStateIsNull() {
            addCriterion("water_state is null");
            return (Criteria) this;
        }

        public Criteria andWaterStateIsNotNull() {
            addCriterion("water_state is not null");
            return (Criteria) this;
        }

        public Criteria andWaterStateEqualTo(Integer value) {
            addCriterion("water_state =", value, "waterState");
            return (Criteria) this;
        }

        public Criteria andWaterStateNotEqualTo(Integer value) {
            addCriterion("water_state <>", value, "waterState");
            return (Criteria) this;
        }

        public Criteria andWaterStateGreaterThan(Integer value) {
            addCriterion("water_state >", value, "waterState");
            return (Criteria) this;
        }

        public Criteria andWaterStateGreaterThanOrEqualTo(Integer value) {
            addCriterion("water_state >=", value, "waterState");
            return (Criteria) this;
        }

        public Criteria andWaterStateLessThan(Integer value) {
            addCriterion("water_state <", value, "waterState");
            return (Criteria) this;
        }

        public Criteria andWaterStateLessThanOrEqualTo(Integer value) {
            addCriterion("water_state <=", value, "waterState");
            return (Criteria) this;
        }

        public Criteria andWaterStateIn(List<Integer> values) {
            addCriterion("water_state in", values, "waterState");
            return (Criteria) this;
        }

        public Criteria andWaterStateNotIn(List<Integer> values) {
            addCriterion("water_state not in", values, "waterState");
            return (Criteria) this;
        }

        public Criteria andWaterStateBetween(Integer value1, Integer value2) {
            addCriterion("water_state between", value1, value2, "waterState");
            return (Criteria) this;
        }

        public Criteria andWaterStateNotBetween(Integer value1, Integer value2) {
            addCriterion("water_state not between", value1, value2, "waterState");
            return (Criteria) this;
        }

        public Criteria andUserIdIsNull() {
            addCriterion("user_id is null");
            return (Criteria) this;
        }

        public Criteria andUserIdIsNotNull() {
            addCriterion("user_id is not null");
            return (Criteria) this;
        }

        public Criteria andUserIdEqualTo(String value) {
            addCriterion("user_id =", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotEqualTo(String value) {
            addCriterion("user_id <>", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdGreaterThan(String value) {
            addCriterion("user_id >", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdGreaterThanOrEqualTo(String value) {
            addCriterion("user_id >=", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdLessThan(String value) {
            addCriterion("user_id <", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdLessThanOrEqualTo(String value) {
            addCriterion("user_id <=", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdLike(String value) {
            addCriterion("user_id like", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotLike(String value) {
            addCriterion("user_id not like", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdIn(List<String> values) {
            addCriterion("user_id in", values, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotIn(List<String> values) {
            addCriterion("user_id not in", values, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdBetween(String value1, String value2) {
            addCriterion("user_id between", value1, value2, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotBetween(String value1, String value2) {
            addCriterion("user_id not between", value1, value2, "userId");
            return (Criteria) this;
        }

        public Criteria andAdminIdIsNull() {
            addCriterion("admin_id is null");
            return (Criteria) this;
        }

        public Criteria andAdminIdIsNotNull() {
            addCriterion("admin_id is not null");
            return (Criteria) this;
        }

        public Criteria andAdminIdEqualTo(String value) {
            addCriterion("admin_id =", value, "adminId");
            return (Criteria) this;
        }

        public Criteria andAdminIdNotEqualTo(String value) {
            addCriterion("admin_id <>", value, "adminId");
            return (Criteria) this;
        }

        public Criteria andAdminIdGreaterThan(String value) {
            addCriterion("admin_id >", value, "adminId");
            return (Criteria) this;
        }

        public Criteria andAdminIdGreaterThanOrEqualTo(String value) {
            addCriterion("admin_id >=", value, "adminId");
            return (Criteria) this;
        }

        public Criteria andAdminIdLessThan(String value) {
            addCriterion("admin_id <", value, "adminId");
            return (Criteria) this;
        }

        public Criteria andAdminIdLessThanOrEqualTo(String value) {
            addCriterion("admin_id <=", value, "adminId");
            return (Criteria) this;
        }

        public Criteria andAdminIdLike(String value) {
            addCriterion("admin_id like", value, "adminId");
            return (Criteria) this;
        }

        public Criteria andAdminIdNotLike(String value) {
            addCriterion("admin_id not like", value, "adminId");
            return (Criteria) this;
        }

        public Criteria andAdminIdIn(List<String> values) {
            addCriterion("admin_id in", values, "adminId");
            return (Criteria) this;
        }

        public Criteria andAdminIdNotIn(List<String> values) {
            addCriterion("admin_id not in", values, "adminId");
            return (Criteria) this;
        }

        public Criteria andAdminIdBetween(String value1, String value2) {
            addCriterion("admin_id between", value1, value2, "adminId");
            return (Criteria) this;
        }

        public Criteria andAdminIdNotBetween(String value1, String value2) {
            addCriterion("admin_id not between", value1, value2, "adminId");
            return (Criteria) this;
        }

        public Criteria andWaterFeedbackIsNull() {
            addCriterion("water_feedback is null");
            return (Criteria) this;
        }

        public Criteria andWaterFeedbackIsNotNull() {
            addCriterion("water_feedback is not null");
            return (Criteria) this;
        }

        public Criteria andWaterFeedbackEqualTo(Integer value) {
            addCriterion("water_feedback =", value, "waterFeedback");
            return (Criteria) this;
        }

        public Criteria andWaterFeedbackNotEqualTo(Integer value) {
            addCriterion("water_feedback <>", value, "waterFeedback");
            return (Criteria) this;
        }

        public Criteria andWaterFeedbackGreaterThan(Integer value) {
            addCriterion("water_feedback >", value, "waterFeedback");
            return (Criteria) this;
        }

        public Criteria andWaterFeedbackGreaterThanOrEqualTo(Integer value) {
            addCriterion("water_feedback >=", value, "waterFeedback");
            return (Criteria) this;
        }

        public Criteria andWaterFeedbackLessThan(Integer value) {
            addCriterion("water_feedback <", value, "waterFeedback");
            return (Criteria) this;
        }

        public Criteria andWaterFeedbackLessThanOrEqualTo(Integer value) {
            addCriterion("water_feedback <=", value, "waterFeedback");
            return (Criteria) this;
        }

        public Criteria andWaterFeedbackIn(List<Integer> values) {
            addCriterion("water_feedback in", values, "waterFeedback");
            return (Criteria) this;
        }

        public Criteria andWaterFeedbackNotIn(List<Integer> values) {
            addCriterion("water_feedback not in", values, "waterFeedback");
            return (Criteria) this;
        }

        public Criteria andWaterFeedbackBetween(Integer value1, Integer value2) {
            addCriterion("water_feedback between", value1, value2, "waterFeedback");
            return (Criteria) this;
        }

        public Criteria andWaterFeedbackNotBetween(Integer value1, Integer value2) {
            addCriterion("water_feedback not between", value1, value2, "waterFeedback");
            return (Criteria) this;
        }

        public Criteria andWaterCountIsNull() {
            addCriterion("water_count is null");
            return (Criteria) this;
        }

        public Criteria andWaterCountIsNotNull() {
            addCriterion("water_count is not null");
            return (Criteria) this;
        }

        public Criteria andWaterCountEqualTo(Integer value) {
            addCriterion("water_count =", value, "waterCount");
            return (Criteria) this;
        }

        public Criteria andWaterCountNotEqualTo(Integer value) {
            addCriterion("water_count <>", value, "waterCount");
            return (Criteria) this;
        }

        public Criteria andWaterCountGreaterThan(Integer value) {
            addCriterion("water_count >", value, "waterCount");
            return (Criteria) this;
        }

        public Criteria andWaterCountGreaterThanOrEqualTo(Integer value) {
            addCriterion("water_count >=", value, "waterCount");
            return (Criteria) this;
        }

        public Criteria andWaterCountLessThan(Integer value) {
            addCriterion("water_count <", value, "waterCount");
            return (Criteria) this;
        }

        public Criteria andWaterCountLessThanOrEqualTo(Integer value) {
            addCriterion("water_count <=", value, "waterCount");
            return (Criteria) this;
        }

        public Criteria andWaterCountIn(List<Integer> values) {
            addCriterion("water_count in", values, "waterCount");
            return (Criteria) this;
        }

        public Criteria andWaterCountNotIn(List<Integer> values) {
            addCriterion("water_count not in", values, "waterCount");
            return (Criteria) this;
        }

        public Criteria andWaterCountBetween(Integer value1, Integer value2) {
            addCriterion("water_count between", value1, value2, "waterCount");
            return (Criteria) this;
        }

        public Criteria andWaterCountNotBetween(Integer value1, Integer value2) {
            addCriterion("water_count not between", value1, value2, "waterCount");
            return (Criteria) this;
        }

        public Criteria andZoneIsNull() {
            addCriterion("zone is null");
            return (Criteria) this;
        }

        public Criteria andZoneIsNotNull() {
            addCriterion("zone is not null");
            return (Criteria) this;
        }

        public Criteria andZoneEqualTo(Integer value) {
            addCriterion("zone =", value, "zone");
            return (Criteria) this;
        }

        public Criteria andZoneNotEqualTo(Integer value) {
            addCriterion("zone <>", value, "zone");
            return (Criteria) this;
        }

        public Criteria andZoneGreaterThan(Integer value) {
            addCriterion("zone >", value, "zone");
            return (Criteria) this;
        }

        public Criteria andZoneGreaterThanOrEqualTo(Integer value) {
            addCriterion("zone >=", value, "zone");
            return (Criteria) this;
        }

        public Criteria andZoneLessThan(Integer value) {
            addCriterion("zone <", value, "zone");
            return (Criteria) this;
        }

        public Criteria andZoneLessThanOrEqualTo(Integer value) {
            addCriterion("zone <=", value, "zone");
            return (Criteria) this;
        }

        public Criteria andZoneIn(List<Integer> values) {
            addCriterion("zone in", values, "zone");
            return (Criteria) this;
        }

        public Criteria andZoneNotIn(List<Integer> values) {
            addCriterion("zone not in", values, "zone");
            return (Criteria) this;
        }

        public Criteria andZoneBetween(Integer value1, Integer value2) {
            addCriterion("zone between", value1, value2, "zone");
            return (Criteria) this;
        }

        public Criteria andZoneNotBetween(Integer value1, Integer value2) {
            addCriterion("zone not between", value1, value2, "zone");
            return (Criteria) this;
        }

        public Criteria andAdminPhoneIsNull() {
            addCriterion("admin_phone is null");
            return (Criteria) this;
        }

        public Criteria andAdminPhoneIsNotNull() {
            addCriterion("admin_phone is not null");
            return (Criteria) this;
        }

        public Criteria andAdminPhoneEqualTo(String value) {
            addCriterion("admin_phone =", value, "adminPhone");
            return (Criteria) this;
        }

        public Criteria andAdminPhoneNotEqualTo(String value) {
            addCriterion("admin_phone <>", value, "adminPhone");
            return (Criteria) this;
        }

        public Criteria andAdminPhoneGreaterThan(String value) {
            addCriterion("admin_phone >", value, "adminPhone");
            return (Criteria) this;
        }

        public Criteria andAdminPhoneGreaterThanOrEqualTo(String value) {
            addCriterion("admin_phone >=", value, "adminPhone");
            return (Criteria) this;
        }

        public Criteria andAdminPhoneLessThan(String value) {
            addCriterion("admin_phone <", value, "adminPhone");
            return (Criteria) this;
        }

        public Criteria andAdminPhoneLessThanOrEqualTo(String value) {
            addCriterion("admin_phone <=", value, "adminPhone");
            return (Criteria) this;
        }

        public Criteria andAdminPhoneLike(String value) {
            addCriterion("admin_phone like", value, "adminPhone");
            return (Criteria) this;
        }

        public Criteria andAdminPhoneNotLike(String value) {
            addCriterion("admin_phone not like", value, "adminPhone");
            return (Criteria) this;
        }

        public Criteria andAdminPhoneIn(List<String> values) {
            addCriterion("admin_phone in", values, "adminPhone");
            return (Criteria) this;
        }

        public Criteria andAdminPhoneNotIn(List<String> values) {
            addCriterion("admin_phone not in", values, "adminPhone");
            return (Criteria) this;
        }

        public Criteria andAdminPhoneBetween(String value1, String value2) {
            addCriterion("admin_phone between", value1, value2, "adminPhone");
            return (Criteria) this;
        }

        public Criteria andAdminPhoneNotBetween(String value1, String value2) {
            addCriterion("admin_phone not between", value1, value2, "adminPhone");
            return (Criteria) this;
        }

        public Criteria andUserPhoneIsNull() {
            addCriterion("user_phone is null");
            return (Criteria) this;
        }

        public Criteria andUserPhoneIsNotNull() {
            addCriterion("user_phone is not null");
            return (Criteria) this;
        }

        public Criteria andUserPhoneEqualTo(String value) {
            addCriterion("user_phone =", value, "userPhone");
            return (Criteria) this;
        }

        public Criteria andUserPhoneNotEqualTo(String value) {
            addCriterion("user_phone <>", value, "userPhone");
            return (Criteria) this;
        }

        public Criteria andUserPhoneGreaterThan(String value) {
            addCriterion("user_phone >", value, "userPhone");
            return (Criteria) this;
        }

        public Criteria andUserPhoneGreaterThanOrEqualTo(String value) {
            addCriterion("user_phone >=", value, "userPhone");
            return (Criteria) this;
        }

        public Criteria andUserPhoneLessThan(String value) {
            addCriterion("user_phone <", value, "userPhone");
            return (Criteria) this;
        }

        public Criteria andUserPhoneLessThanOrEqualTo(String value) {
            addCriterion("user_phone <=", value, "userPhone");
            return (Criteria) this;
        }

        public Criteria andUserPhoneLike(String value) {
            addCriterion("user_phone like", value, "userPhone");
            return (Criteria) this;
        }

        public Criteria andUserPhoneNotLike(String value) {
            addCriterion("user_phone not like", value, "userPhone");
            return (Criteria) this;
        }

        public Criteria andUserPhoneIn(List<String> values) {
            addCriterion("user_phone in", values, "userPhone");
            return (Criteria) this;
        }

        public Criteria andUserPhoneNotIn(List<String> values) {
            addCriterion("user_phone not in", values, "userPhone");
            return (Criteria) this;
        }

        public Criteria andUserPhoneBetween(String value1, String value2) {
            addCriterion("user_phone between", value1, value2, "userPhone");
            return (Criteria) this;
        }

        public Criteria andUserPhoneNotBetween(String value1, String value2) {
            addCriterion("user_phone not between", value1, value2, "userPhone");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}