package cn.zhku.zhkulife.po.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class UserExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public UserExample() {
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

        public Criteria andUserPasswordIsNull() {
            addCriterion("user_password is null");
            return (Criteria) this;
        }

        public Criteria andUserPasswordIsNotNull() {
            addCriterion("user_password is not null");
            return (Criteria) this;
        }

        public Criteria andUserPasswordEqualTo(String value) {
            addCriterion("user_password =", value, "userPassword");
            return (Criteria) this;
        }

        public Criteria andUserPasswordNotEqualTo(String value) {
            addCriterion("user_password <>", value, "userPassword");
            return (Criteria) this;
        }

        public Criteria andUserPasswordGreaterThan(String value) {
            addCriterion("user_password >", value, "userPassword");
            return (Criteria) this;
        }

        public Criteria andUserPasswordGreaterThanOrEqualTo(String value) {
            addCriterion("user_password >=", value, "userPassword");
            return (Criteria) this;
        }

        public Criteria andUserPasswordLessThan(String value) {
            addCriterion("user_password <", value, "userPassword");
            return (Criteria) this;
        }

        public Criteria andUserPasswordLessThanOrEqualTo(String value) {
            addCriterion("user_password <=", value, "userPassword");
            return (Criteria) this;
        }

        public Criteria andUserPasswordLike(String value) {
            addCriterion("user_password like", value, "userPassword");
            return (Criteria) this;
        }

        public Criteria andUserPasswordNotLike(String value) {
            addCriterion("user_password not like", value, "userPassword");
            return (Criteria) this;
        }

        public Criteria andUserPasswordIn(List<String> values) {
            addCriterion("user_password in", values, "userPassword");
            return (Criteria) this;
        }

        public Criteria andUserPasswordNotIn(List<String> values) {
            addCriterion("user_password not in", values, "userPassword");
            return (Criteria) this;
        }

        public Criteria andUserPasswordBetween(String value1, String value2) {
            addCriterion("user_password between", value1, value2, "userPassword");
            return (Criteria) this;
        }

        public Criteria andUserPasswordNotBetween(String value1, String value2) {
            addCriterion("user_password not between", value1, value2, "userPassword");
            return (Criteria) this;
        }

        public Criteria andUserRoomIsNull() {
            addCriterion("user_room is null");
            return (Criteria) this;
        }

        public Criteria andUserRoomIsNotNull() {
            addCriterion("user_room is not null");
            return (Criteria) this;
        }

        public Criteria andUserRoomEqualTo(String value) {
            addCriterion("user_room =", value, "userRoom");
            return (Criteria) this;
        }

        public Criteria andUserRoomNotEqualTo(String value) {
            addCriterion("user_room <>", value, "userRoom");
            return (Criteria) this;
        }

        public Criteria andUserRoomGreaterThan(String value) {
            addCriterion("user_room >", value, "userRoom");
            return (Criteria) this;
        }

        public Criteria andUserRoomGreaterThanOrEqualTo(String value) {
            addCriterion("user_room >=", value, "userRoom");
            return (Criteria) this;
        }

        public Criteria andUserRoomLessThan(String value) {
            addCriterion("user_room <", value, "userRoom");
            return (Criteria) this;
        }

        public Criteria andUserRoomLessThanOrEqualTo(String value) {
            addCriterion("user_room <=", value, "userRoom");
            return (Criteria) this;
        }

        public Criteria andUserRoomLike(String value) {
            addCriterion("user_room like", value, "userRoom");
            return (Criteria) this;
        }

        public Criteria andUserRoomNotLike(String value) {
            addCriterion("user_room not like", value, "userRoom");
            return (Criteria) this;
        }

        public Criteria andUserRoomIn(List<String> values) {
            addCriterion("user_room in", values, "userRoom");
            return (Criteria) this;
        }

        public Criteria andUserRoomNotIn(List<String> values) {
            addCriterion("user_room not in", values, "userRoom");
            return (Criteria) this;
        }

        public Criteria andUserRoomBetween(String value1, String value2) {
            addCriterion("user_room between", value1, value2, "userRoom");
            return (Criteria) this;
        }

        public Criteria andUserRoomNotBetween(String value1, String value2) {
            addCriterion("user_room not between", value1, value2, "userRoom");
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

        public Criteria andUserZoneIsNull() {
            addCriterion("user_zone is null");
            return (Criteria) this;
        }

        public Criteria andUserZoneIsNotNull() {
            addCriterion("user_zone is not null");
            return (Criteria) this;
        }

        public Criteria andUserZoneEqualTo(Integer value) {
            addCriterion("user_zone =", value, "userZone");
            return (Criteria) this;
        }

        public Criteria andUserZoneNotEqualTo(Integer value) {
            addCriterion("user_zone <>", value, "userZone");
            return (Criteria) this;
        }

        public Criteria andUserZoneGreaterThan(Integer value) {
            addCriterion("user_zone >", value, "userZone");
            return (Criteria) this;
        }

        public Criteria andUserZoneGreaterThanOrEqualTo(Integer value) {
            addCriterion("user_zone >=", value, "userZone");
            return (Criteria) this;
        }

        public Criteria andUserZoneLessThan(Integer value) {
            addCriterion("user_zone <", value, "userZone");
            return (Criteria) this;
        }

        public Criteria andUserZoneLessThanOrEqualTo(Integer value) {
            addCriterion("user_zone <=", value, "userZone");
            return (Criteria) this;
        }

        public Criteria andUserZoneIn(List<Integer> values) {
            addCriterion("user_zone in", values, "userZone");
            return (Criteria) this;
        }

        public Criteria andUserZoneNotIn(List<Integer> values) {
            addCriterion("user_zone not in", values, "userZone");
            return (Criteria) this;
        }

        public Criteria andUserZoneBetween(Integer value1, Integer value2) {
            addCriterion("user_zone between", value1, value2, "userZone");
            return (Criteria) this;
        }

        public Criteria andUserZoneNotBetween(Integer value1, Integer value2) {
            addCriterion("user_zone not between", value1, value2, "userZone");
            return (Criteria) this;
        }

        public Criteria andUserYibanidIsNull() {
            addCriterion("user_yibanid is null");
            return (Criteria) this;
        }

        public Criteria andUserYibanidIsNotNull() {
            addCriterion("user_yibanid is not null");
            return (Criteria) this;
        }

        public Criteria andUserYibanidEqualTo(String value) {
            addCriterion("user_yibanid =", value, "userYibanid");
            return (Criteria) this;
        }

        public Criteria andUserYibanidNotEqualTo(String value) {
            addCriterion("user_yibanid <>", value, "userYibanid");
            return (Criteria) this;
        }

        public Criteria andUserYibanidGreaterThan(String value) {
            addCriterion("user_yibanid >", value, "userYibanid");
            return (Criteria) this;
        }

        public Criteria andUserYibanidGreaterThanOrEqualTo(String value) {
            addCriterion("user_yibanid >=", value, "userYibanid");
            return (Criteria) this;
        }

        public Criteria andUserYibanidLessThan(String value) {
            addCriterion("user_yibanid <", value, "userYibanid");
            return (Criteria) this;
        }

        public Criteria andUserYibanidLessThanOrEqualTo(String value) {
            addCriterion("user_yibanid <=", value, "userYibanid");
            return (Criteria) this;
        }

        public Criteria andUserYibanidLike(String value) {
            addCriterion("user_yibanid like", value, "userYibanid");
            return (Criteria) this;
        }

        public Criteria andUserYibanidNotLike(String value) {
            addCriterion("user_yibanid not like", value, "userYibanid");
            return (Criteria) this;
        }

        public Criteria andUserYibanidIn(List<String> values) {
            addCriterion("user_yibanid in", values, "userYibanid");
            return (Criteria) this;
        }

        public Criteria andUserYibanidNotIn(List<String> values) {
            addCriterion("user_yibanid not in", values, "userYibanid");
            return (Criteria) this;
        }

        public Criteria andUserYibanidBetween(String value1, String value2) {
            addCriterion("user_yibanid between", value1, value2, "userYibanid");
            return (Criteria) this;
        }

        public Criteria andUserYibanidNotBetween(String value1, String value2) {
            addCriterion("user_yibanid not between", value1, value2, "userYibanid");
            return (Criteria) this;
        }

        public Criteria andUserFlagIsNull() {
            addCriterion("user_flag is null");
            return (Criteria) this;
        }

        public Criteria andUserFlagIsNotNull() {
            addCriterion("user_flag is not null");
            return (Criteria) this;
        }

        public Criteria andUserFlagEqualTo(Integer value) {
            addCriterion("user_flag =", value, "userFlag");
            return (Criteria) this;
        }

        public Criteria andUserFlagNotEqualTo(Integer value) {
            addCriterion("user_flag <>", value, "userFlag");
            return (Criteria) this;
        }

        public Criteria andUserFlagGreaterThan(Integer value) {
            addCriterion("user_flag >", value, "userFlag");
            return (Criteria) this;
        }

        public Criteria andUserFlagGreaterThanOrEqualTo(Integer value) {
            addCriterion("user_flag >=", value, "userFlag");
            return (Criteria) this;
        }

        public Criteria andUserFlagLessThan(Integer value) {
            addCriterion("user_flag <", value, "userFlag");
            return (Criteria) this;
        }

        public Criteria andUserFlagLessThanOrEqualTo(Integer value) {
            addCriterion("user_flag <=", value, "userFlag");
            return (Criteria) this;
        }

        public Criteria andUserFlagIn(List<Integer> values) {
            addCriterion("user_flag in", values, "userFlag");
            return (Criteria) this;
        }

        public Criteria andUserFlagNotIn(List<Integer> values) {
            addCriterion("user_flag not in", values, "userFlag");
            return (Criteria) this;
        }

        public Criteria andUserFlagBetween(Integer value1, Integer value2) {
            addCriterion("user_flag between", value1, value2, "userFlag");
            return (Criteria) this;
        }

        public Criteria andUserFlagNotBetween(Integer value1, Integer value2) {
            addCriterion("user_flag not between", value1, value2, "userFlag");
            return (Criteria) this;
        }

        public Criteria andLoginTimeIsNull() {
            addCriterion("login_time is null");
            return (Criteria) this;
        }

        public Criteria andLoginTimeIsNotNull() {
            addCriterion("login_time is not null");
            return (Criteria) this;
        }

        public Criteria andLoginTimeEqualTo(Date value) {
            addCriterion("login_time =", value, "loginTime");
            return (Criteria) this;
        }

        public Criteria andLoginTimeNotEqualTo(Date value) {
            addCriterion("login_time <>", value, "loginTime");
            return (Criteria) this;
        }

        public Criteria andLoginTimeGreaterThan(Date value) {
            addCriterion("login_time >", value, "loginTime");
            return (Criteria) this;
        }

        public Criteria andLoginTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("login_time >=", value, "loginTime");
            return (Criteria) this;
        }

        public Criteria andLoginTimeLessThan(Date value) {
            addCriterion("login_time <", value, "loginTime");
            return (Criteria) this;
        }

        public Criteria andLoginTimeLessThanOrEqualTo(Date value) {
            addCriterion("login_time <=", value, "loginTime");
            return (Criteria) this;
        }

        public Criteria andLoginTimeIn(List<Date> values) {
            addCriterion("login_time in", values, "loginTime");
            return (Criteria) this;
        }

        public Criteria andLoginTimeNotIn(List<Date> values) {
            addCriterion("login_time not in", values, "loginTime");
            return (Criteria) this;
        }

        public Criteria andLoginTimeBetween(Date value1, Date value2) {
            addCriterion("login_time between", value1, value2, "loginTime");
            return (Criteria) this;
        }

        public Criteria andLoginTimeNotBetween(Date value1, Date value2) {
            addCriterion("login_time not between", value1, value2, "loginTime");
            return (Criteria) this;
        }

        public Criteria andTotalWaterIsNull() {
            addCriterion("total_water is null");
            return (Criteria) this;
        }

        public Criteria andTotalWaterIsNotNull() {
            addCriterion("total_water is not null");
            return (Criteria) this;
        }

        public Criteria andTotalWaterEqualTo(Integer value) {
            addCriterion("total_water =", value, "totalWater");
            return (Criteria) this;
        }

        public Criteria andTotalWaterNotEqualTo(Integer value) {
            addCriterion("total_water <>", value, "totalWater");
            return (Criteria) this;
        }

        public Criteria andTotalWaterGreaterThan(Integer value) {
            addCriterion("total_water >", value, "totalWater");
            return (Criteria) this;
        }

        public Criteria andTotalWaterGreaterThanOrEqualTo(Integer value) {
            addCriterion("total_water >=", value, "totalWater");
            return (Criteria) this;
        }

        public Criteria andTotalWaterLessThan(Integer value) {
            addCriterion("total_water <", value, "totalWater");
            return (Criteria) this;
        }

        public Criteria andTotalWaterLessThanOrEqualTo(Integer value) {
            addCriterion("total_water <=", value, "totalWater");
            return (Criteria) this;
        }

        public Criteria andTotalWaterIn(List<Integer> values) {
            addCriterion("total_water in", values, "totalWater");
            return (Criteria) this;
        }

        public Criteria andTotalWaterNotIn(List<Integer> values) {
            addCriterion("total_water not in", values, "totalWater");
            return (Criteria) this;
        }

        public Criteria andTotalWaterBetween(Integer value1, Integer value2) {
            addCriterion("total_water between", value1, value2, "totalWater");
            return (Criteria) this;
        }

        public Criteria andTotalWaterNotBetween(Integer value1, Integer value2) {
            addCriterion("total_water not between", value1, value2, "totalWater");
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