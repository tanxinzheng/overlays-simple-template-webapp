package com.xmomen.module.system.entity;

import com.xmomen.framework.mybatis.model.BaseMybatisExample;
import java.util.ArrayList;
import java.util.List;

public class DictionaryGroupExample extends BaseMybatisExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public DictionaryGroupExample() {
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

        public Criteria andDictionaryTypeIsNull() {
            addCriterion("DICTIONARY_TYPE is null");
            return (Criteria) this;
        }

        public Criteria andDictionaryTypeIsNotNull() {
            addCriterion("DICTIONARY_TYPE is not null");
            return (Criteria) this;
        }

        public Criteria andDictionaryTypeEqualTo(String value) {
            addCriterion("DICTIONARY_TYPE =", value, "dictionaryType");
            return (Criteria) this;
        }

        public Criteria andDictionaryTypeNotEqualTo(String value) {
            addCriterion("DICTIONARY_TYPE <>", value, "dictionaryType");
            return (Criteria) this;
        }

        public Criteria andDictionaryTypeGreaterThan(String value) {
            addCriterion("DICTIONARY_TYPE >", value, "dictionaryType");
            return (Criteria) this;
        }

        public Criteria andDictionaryTypeGreaterThanOrEqualTo(String value) {
            addCriterion("DICTIONARY_TYPE >=", value, "dictionaryType");
            return (Criteria) this;
        }

        public Criteria andDictionaryTypeLessThan(String value) {
            addCriterion("DICTIONARY_TYPE <", value, "dictionaryType");
            return (Criteria) this;
        }

        public Criteria andDictionaryTypeLessThanOrEqualTo(String value) {
            addCriterion("DICTIONARY_TYPE <=", value, "dictionaryType");
            return (Criteria) this;
        }

        public Criteria andDictionaryTypeLike(String value) {
            addCriterion("DICTIONARY_TYPE like", value, "dictionaryType");
            return (Criteria) this;
        }

        public Criteria andDictionaryTypeNotLike(String value) {
            addCriterion("DICTIONARY_TYPE not like", value, "dictionaryType");
            return (Criteria) this;
        }

        public Criteria andDictionaryTypeIn(List<String> values) {
            addCriterion("DICTIONARY_TYPE in", values, "dictionaryType");
            return (Criteria) this;
        }

        public Criteria andDictionaryTypeNotIn(List<String> values) {
            addCriterion("DICTIONARY_TYPE not in", values, "dictionaryType");
            return (Criteria) this;
        }

        public Criteria andDictionaryTypeBetween(String value1, String value2) {
            addCriterion("DICTIONARY_TYPE between", value1, value2, "dictionaryType");
            return (Criteria) this;
        }

        public Criteria andDictionaryTypeNotBetween(String value1, String value2) {
            addCriterion("DICTIONARY_TYPE not between", value1, value2, "dictionaryType");
            return (Criteria) this;
        }

        public Criteria andDictionaryDescIsNull() {
            addCriterion("DICTIONARY_DESC is null");
            return (Criteria) this;
        }

        public Criteria andDictionaryDescIsNotNull() {
            addCriterion("DICTIONARY_DESC is not null");
            return (Criteria) this;
        }

        public Criteria andDictionaryDescEqualTo(String value) {
            addCriterion("DICTIONARY_DESC =", value, "dictionaryDesc");
            return (Criteria) this;
        }

        public Criteria andDictionaryDescNotEqualTo(String value) {
            addCriterion("DICTIONARY_DESC <>", value, "dictionaryDesc");
            return (Criteria) this;
        }

        public Criteria andDictionaryDescGreaterThan(String value) {
            addCriterion("DICTIONARY_DESC >", value, "dictionaryDesc");
            return (Criteria) this;
        }

        public Criteria andDictionaryDescGreaterThanOrEqualTo(String value) {
            addCriterion("DICTIONARY_DESC >=", value, "dictionaryDesc");
            return (Criteria) this;
        }

        public Criteria andDictionaryDescLessThan(String value) {
            addCriterion("DICTIONARY_DESC <", value, "dictionaryDesc");
            return (Criteria) this;
        }

        public Criteria andDictionaryDescLessThanOrEqualTo(String value) {
            addCriterion("DICTIONARY_DESC <=", value, "dictionaryDesc");
            return (Criteria) this;
        }

        public Criteria andDictionaryDescLike(String value) {
            addCriterion("DICTIONARY_DESC like", value, "dictionaryDesc");
            return (Criteria) this;
        }

        public Criteria andDictionaryDescNotLike(String value) {
            addCriterion("DICTIONARY_DESC not like", value, "dictionaryDesc");
            return (Criteria) this;
        }

        public Criteria andDictionaryDescIn(List<String> values) {
            addCriterion("DICTIONARY_DESC in", values, "dictionaryDesc");
            return (Criteria) this;
        }

        public Criteria andDictionaryDescNotIn(List<String> values) {
            addCriterion("DICTIONARY_DESC not in", values, "dictionaryDesc");
            return (Criteria) this;
        }

        public Criteria andDictionaryDescBetween(String value1, String value2) {
            addCriterion("DICTIONARY_DESC between", value1, value2, "dictionaryDesc");
            return (Criteria) this;
        }

        public Criteria andDictionaryDescNotBetween(String value1, String value2) {
            addCriterion("DICTIONARY_DESC not between", value1, value2, "dictionaryDesc");
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