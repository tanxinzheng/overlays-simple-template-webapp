package com.xmomen.module.authorization.entity;

import com.xmomen.framework.mybatis.model.BaseMybatisExample;
import java.util.ArrayList;
import java.util.List;

public class PermissionExample extends BaseMybatisExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public PermissionExample() {
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

        public Criteria andIdIsNull() {
            addCriterion("ID is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("ID is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(String value) {
            addCriterion("ID =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(String value) {
            addCriterion("ID <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(String value) {
            addCriterion("ID >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(String value) {
            addCriterion("ID >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(String value) {
            addCriterion("ID <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(String value) {
            addCriterion("ID <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLike(String value) {
            addCriterion("ID like", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotLike(String value) {
            addCriterion("ID not like", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<String> values) {
            addCriterion("ID in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<String> values) {
            addCriterion("ID not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(String value1, String value2) {
            addCriterion("ID between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(String value1, String value2) {
            addCriterion("ID not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andPermissionCodeIsNull() {
            addCriterion("PERMISSION_CODE is null");
            return (Criteria) this;
        }

        public Criteria andPermissionCodeIsNotNull() {
            addCriterion("PERMISSION_CODE is not null");
            return (Criteria) this;
        }

        public Criteria andPermissionCodeEqualTo(String value) {
            addCriterion("PERMISSION_CODE =", value, "permissionCode");
            return (Criteria) this;
        }

        public Criteria andPermissionCodeNotEqualTo(String value) {
            addCriterion("PERMISSION_CODE <>", value, "permissionCode");
            return (Criteria) this;
        }

        public Criteria andPermissionCodeGreaterThan(String value) {
            addCriterion("PERMISSION_CODE >", value, "permissionCode");
            return (Criteria) this;
        }

        public Criteria andPermissionCodeGreaterThanOrEqualTo(String value) {
            addCriterion("PERMISSION_CODE >=", value, "permissionCode");
            return (Criteria) this;
        }

        public Criteria andPermissionCodeLessThan(String value) {
            addCriterion("PERMISSION_CODE <", value, "permissionCode");
            return (Criteria) this;
        }

        public Criteria andPermissionCodeLessThanOrEqualTo(String value) {
            addCriterion("PERMISSION_CODE <=", value, "permissionCode");
            return (Criteria) this;
        }

        public Criteria andPermissionCodeLike(String value) {
            addCriterion("PERMISSION_CODE like", value, "permissionCode");
            return (Criteria) this;
        }

        public Criteria andPermissionCodeNotLike(String value) {
            addCriterion("PERMISSION_CODE not like", value, "permissionCode");
            return (Criteria) this;
        }

        public Criteria andPermissionCodeIn(List<String> values) {
            addCriterion("PERMISSION_CODE in", values, "permissionCode");
            return (Criteria) this;
        }

        public Criteria andPermissionCodeNotIn(List<String> values) {
            addCriterion("PERMISSION_CODE not in", values, "permissionCode");
            return (Criteria) this;
        }

        public Criteria andPermissionCodeBetween(String value1, String value2) {
            addCriterion("PERMISSION_CODE between", value1, value2, "permissionCode");
            return (Criteria) this;
        }

        public Criteria andPermissionCodeNotBetween(String value1, String value2) {
            addCriterion("PERMISSION_CODE not between", value1, value2, "permissionCode");
            return (Criteria) this;
        }

        public Criteria andPermissionNameIsNull() {
            addCriterion("PERMISSION_NAME is null");
            return (Criteria) this;
        }

        public Criteria andPermissionNameIsNotNull() {
            addCriterion("PERMISSION_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andPermissionNameEqualTo(String value) {
            addCriterion("PERMISSION_NAME =", value, "permissionName");
            return (Criteria) this;
        }

        public Criteria andPermissionNameNotEqualTo(String value) {
            addCriterion("PERMISSION_NAME <>", value, "permissionName");
            return (Criteria) this;
        }

        public Criteria andPermissionNameGreaterThan(String value) {
            addCriterion("PERMISSION_NAME >", value, "permissionName");
            return (Criteria) this;
        }

        public Criteria andPermissionNameGreaterThanOrEqualTo(String value) {
            addCriterion("PERMISSION_NAME >=", value, "permissionName");
            return (Criteria) this;
        }

        public Criteria andPermissionNameLessThan(String value) {
            addCriterion("PERMISSION_NAME <", value, "permissionName");
            return (Criteria) this;
        }

        public Criteria andPermissionNameLessThanOrEqualTo(String value) {
            addCriterion("PERMISSION_NAME <=", value, "permissionName");
            return (Criteria) this;
        }

        public Criteria andPermissionNameLike(String value) {
            addCriterion("PERMISSION_NAME like", value, "permissionName");
            return (Criteria) this;
        }

        public Criteria andPermissionNameNotLike(String value) {
            addCriterion("PERMISSION_NAME not like", value, "permissionName");
            return (Criteria) this;
        }

        public Criteria andPermissionNameIn(List<String> values) {
            addCriterion("PERMISSION_NAME in", values, "permissionName");
            return (Criteria) this;
        }

        public Criteria andPermissionNameNotIn(List<String> values) {
            addCriterion("PERMISSION_NAME not in", values, "permissionName");
            return (Criteria) this;
        }

        public Criteria andPermissionNameBetween(String value1, String value2) {
            addCriterion("PERMISSION_NAME between", value1, value2, "permissionName");
            return (Criteria) this;
        }

        public Criteria andPermissionNameNotBetween(String value1, String value2) {
            addCriterion("PERMISSION_NAME not between", value1, value2, "permissionName");
            return (Criteria) this;
        }

        public Criteria andDescriptionIsNull() {
            addCriterion("DESCRIPTION is null");
            return (Criteria) this;
        }

        public Criteria andDescriptionIsNotNull() {
            addCriterion("DESCRIPTION is not null");
            return (Criteria) this;
        }

        public Criteria andDescriptionEqualTo(String value) {
            addCriterion("DESCRIPTION =", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionNotEqualTo(String value) {
            addCriterion("DESCRIPTION <>", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionGreaterThan(String value) {
            addCriterion("DESCRIPTION >", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionGreaterThanOrEqualTo(String value) {
            addCriterion("DESCRIPTION >=", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionLessThan(String value) {
            addCriterion("DESCRIPTION <", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionLessThanOrEqualTo(String value) {
            addCriterion("DESCRIPTION <=", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionLike(String value) {
            addCriterion("DESCRIPTION like", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionNotLike(String value) {
            addCriterion("DESCRIPTION not like", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionIn(List<String> values) {
            addCriterion("DESCRIPTION in", values, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionNotIn(List<String> values) {
            addCriterion("DESCRIPTION not in", values, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionBetween(String value1, String value2) {
            addCriterion("DESCRIPTION between", value1, value2, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionNotBetween(String value1, String value2) {
            addCriterion("DESCRIPTION not between", value1, value2, "description");
            return (Criteria) this;
        }

        public Criteria andActiveIsNull() {
            addCriterion("ACTIVE is null");
            return (Criteria) this;
        }

        public Criteria andActiveIsNotNull() {
            addCriterion("ACTIVE is not null");
            return (Criteria) this;
        }

        public Criteria andActiveEqualTo(Boolean value) {
            addCriterion("ACTIVE =", value, "active");
            return (Criteria) this;
        }

        public Criteria andActiveNotEqualTo(Boolean value) {
            addCriterion("ACTIVE <>", value, "active");
            return (Criteria) this;
        }

        public Criteria andActiveGreaterThan(Boolean value) {
            addCriterion("ACTIVE >", value, "active");
            return (Criteria) this;
        }

        public Criteria andActiveGreaterThanOrEqualTo(Boolean value) {
            addCriterion("ACTIVE >=", value, "active");
            return (Criteria) this;
        }

        public Criteria andActiveLessThan(Boolean value) {
            addCriterion("ACTIVE <", value, "active");
            return (Criteria) this;
        }

        public Criteria andActiveLessThanOrEqualTo(Boolean value) {
            addCriterion("ACTIVE <=", value, "active");
            return (Criteria) this;
        }

        public Criteria andActiveLike(Boolean value) {
            addCriterion("ACTIVE like", value, "active");
            return (Criteria) this;
        }

        public Criteria andActiveNotLike(Boolean value) {
            addCriterion("ACTIVE not like", value, "active");
            return (Criteria) this;
        }

        public Criteria andActiveIn(List<Boolean> values) {
            addCriterion("ACTIVE in", values, "active");
            return (Criteria) this;
        }

        public Criteria andActiveNotIn(List<Boolean> values) {
            addCriterion("ACTIVE not in", values, "active");
            return (Criteria) this;
        }

        public Criteria andActiveBetween(Boolean value1, Boolean value2) {
            addCriterion("ACTIVE between", value1, value2, "active");
            return (Criteria) this;
        }

        public Criteria andActiveNotBetween(Boolean value1, Boolean value2) {
            addCriterion("ACTIVE not between", value1, value2, "active");
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