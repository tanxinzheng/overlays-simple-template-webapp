package com.xmomen.module.system.entity;

import com.xmomen.framework.mybatis.model.BaseMybatisExample;
import java.util.ArrayList;
import java.util.List;

public class DictionaryExample extends BaseMybatisExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public DictionaryExample() {
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

        public Criteria andShowValueIsNull() {
            addCriterion("SHOW_VALUE is null");
            return (Criteria) this;
        }

        public Criteria andShowValueIsNotNull() {
            addCriterion("SHOW_VALUE is not null");
            return (Criteria) this;
        }

        public Criteria andShowValueEqualTo(String value) {
            addCriterion("SHOW_VALUE =", value, "showValue");
            return (Criteria) this;
        }

        public Criteria andShowValueNotEqualTo(String value) {
            addCriterion("SHOW_VALUE <>", value, "showValue");
            return (Criteria) this;
        }

        public Criteria andShowValueGreaterThan(String value) {
            addCriterion("SHOW_VALUE >", value, "showValue");
            return (Criteria) this;
        }

        public Criteria andShowValueGreaterThanOrEqualTo(String value) {
            addCriterion("SHOW_VALUE >=", value, "showValue");
            return (Criteria) this;
        }

        public Criteria andShowValueLessThan(String value) {
            addCriterion("SHOW_VALUE <", value, "showValue");
            return (Criteria) this;
        }

        public Criteria andShowValueLessThanOrEqualTo(String value) {
            addCriterion("SHOW_VALUE <=", value, "showValue");
            return (Criteria) this;
        }

        public Criteria andShowValueLike(String value) {
            addCriterion("SHOW_VALUE like", value, "showValue");
            return (Criteria) this;
        }

        public Criteria andShowValueNotLike(String value) {
            addCriterion("SHOW_VALUE not like", value, "showValue");
            return (Criteria) this;
        }

        public Criteria andShowValueIn(List<String> values) {
            addCriterion("SHOW_VALUE in", values, "showValue");
            return (Criteria) this;
        }

        public Criteria andShowValueNotIn(List<String> values) {
            addCriterion("SHOW_VALUE not in", values, "showValue");
            return (Criteria) this;
        }

        public Criteria andShowValueBetween(String value1, String value2) {
            addCriterion("SHOW_VALUE between", value1, value2, "showValue");
            return (Criteria) this;
        }

        public Criteria andShowValueNotBetween(String value1, String value2) {
            addCriterion("SHOW_VALUE not between", value1, value2, "showValue");
            return (Criteria) this;
        }

        public Criteria andCodeValueIsNull() {
            addCriterion("CODE_VALUE is null");
            return (Criteria) this;
        }

        public Criteria andCodeValueIsNotNull() {
            addCriterion("CODE_VALUE is not null");
            return (Criteria) this;
        }

        public Criteria andCodeValueEqualTo(String value) {
            addCriterion("CODE_VALUE =", value, "codeValue");
            return (Criteria) this;
        }

        public Criteria andCodeValueNotEqualTo(String value) {
            addCriterion("CODE_VALUE <>", value, "codeValue");
            return (Criteria) this;
        }

        public Criteria andCodeValueGreaterThan(String value) {
            addCriterion("CODE_VALUE >", value, "codeValue");
            return (Criteria) this;
        }

        public Criteria andCodeValueGreaterThanOrEqualTo(String value) {
            addCriterion("CODE_VALUE >=", value, "codeValue");
            return (Criteria) this;
        }

        public Criteria andCodeValueLessThan(String value) {
            addCriterion("CODE_VALUE <", value, "codeValue");
            return (Criteria) this;
        }

        public Criteria andCodeValueLessThanOrEqualTo(String value) {
            addCriterion("CODE_VALUE <=", value, "codeValue");
            return (Criteria) this;
        }

        public Criteria andCodeValueLike(String value) {
            addCriterion("CODE_VALUE like", value, "codeValue");
            return (Criteria) this;
        }

        public Criteria andCodeValueNotLike(String value) {
            addCriterion("CODE_VALUE not like", value, "codeValue");
            return (Criteria) this;
        }

        public Criteria andCodeValueIn(List<String> values) {
            addCriterion("CODE_VALUE in", values, "codeValue");
            return (Criteria) this;
        }

        public Criteria andCodeValueNotIn(List<String> values) {
            addCriterion("CODE_VALUE not in", values, "codeValue");
            return (Criteria) this;
        }

        public Criteria andCodeValueBetween(String value1, String value2) {
            addCriterion("CODE_VALUE between", value1, value2, "codeValue");
            return (Criteria) this;
        }

        public Criteria andCodeValueNotBetween(String value1, String value2) {
            addCriterion("CODE_VALUE not between", value1, value2, "codeValue");
            return (Criteria) this;
        }

        public Criteria andSortValueIsNull() {
            addCriterion("SORT_VALUE is null");
            return (Criteria) this;
        }

        public Criteria andSortValueIsNotNull() {
            addCriterion("SORT_VALUE is not null");
            return (Criteria) this;
        }

        public Criteria andSortValueEqualTo(Integer value) {
            addCriterion("SORT_VALUE =", value, "sortValue");
            return (Criteria) this;
        }

        public Criteria andSortValueNotEqualTo(Integer value) {
            addCriterion("SORT_VALUE <>", value, "sortValue");
            return (Criteria) this;
        }

        public Criteria andSortValueGreaterThan(Integer value) {
            addCriterion("SORT_VALUE >", value, "sortValue");
            return (Criteria) this;
        }

        public Criteria andSortValueGreaterThanOrEqualTo(Integer value) {
            addCriterion("SORT_VALUE >=", value, "sortValue");
            return (Criteria) this;
        }

        public Criteria andSortValueLessThan(Integer value) {
            addCriterion("SORT_VALUE <", value, "sortValue");
            return (Criteria) this;
        }

        public Criteria andSortValueLessThanOrEqualTo(Integer value) {
            addCriterion("SORT_VALUE <=", value, "sortValue");
            return (Criteria) this;
        }

        public Criteria andSortValueIn(List<Integer> values) {
            addCriterion("SORT_VALUE in", values, "sortValue");
            return (Criteria) this;
        }

        public Criteria andSortValueNotIn(List<Integer> values) {
            addCriterion("SORT_VALUE not in", values, "sortValue");
            return (Criteria) this;
        }

        public Criteria andSortValueBetween(Integer value1, Integer value2) {
            addCriterion("SORT_VALUE between", value1, value2, "sortValue");
            return (Criteria) this;
        }

        public Criteria andSortValueNotBetween(Integer value1, Integer value2) {
            addCriterion("SORT_VALUE not between", value1, value2, "sortValue");
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

        public Criteria andParentIdIsNull() {
            addCriterion("PARENT_ID is null");
            return (Criteria) this;
        }

        public Criteria andParentIdIsNotNull() {
            addCriterion("PARENT_ID is not null");
            return (Criteria) this;
        }

        public Criteria andParentIdEqualTo(String value) {
            addCriterion("PARENT_ID =", value, "parentId");
            return (Criteria) this;
        }

        public Criteria andParentIdNotEqualTo(String value) {
            addCriterion("PARENT_ID <>", value, "parentId");
            return (Criteria) this;
        }

        public Criteria andParentIdGreaterThan(String value) {
            addCriterion("PARENT_ID >", value, "parentId");
            return (Criteria) this;
        }

        public Criteria andParentIdGreaterThanOrEqualTo(String value) {
            addCriterion("PARENT_ID >=", value, "parentId");
            return (Criteria) this;
        }

        public Criteria andParentIdLessThan(String value) {
            addCriterion("PARENT_ID <", value, "parentId");
            return (Criteria) this;
        }

        public Criteria andParentIdLessThanOrEqualTo(String value) {
            addCriterion("PARENT_ID <=", value, "parentId");
            return (Criteria) this;
        }

        public Criteria andParentIdLike(String value) {
            addCriterion("PARENT_ID like", value, "parentId");
            return (Criteria) this;
        }

        public Criteria andParentIdNotLike(String value) {
            addCriterion("PARENT_ID not like", value, "parentId");
            return (Criteria) this;
        }

        public Criteria andParentIdIn(List<String> values) {
            addCriterion("PARENT_ID in", values, "parentId");
            return (Criteria) this;
        }

        public Criteria andParentIdNotIn(List<String> values) {
            addCriterion("PARENT_ID not in", values, "parentId");
            return (Criteria) this;
        }

        public Criteria andParentIdBetween(String value1, String value2) {
            addCriterion("PARENT_ID between", value1, value2, "parentId");
            return (Criteria) this;
        }

        public Criteria andParentIdNotBetween(String value1, String value2) {
            addCriterion("PARENT_ID not between", value1, value2, "parentId");
            return (Criteria) this;
        }

        public Criteria andShowIsNull() {
            addCriterion("SHOW is null");
            return (Criteria) this;
        }

        public Criteria andShowIsNotNull() {
            addCriterion("SHOW is not null");
            return (Criteria) this;
        }

        public Criteria andShowEqualTo(Boolean value) {
            addCriterion("SHOW =", value, "show");
            return (Criteria) this;
        }

        public Criteria andShowNotEqualTo(Boolean value) {
            addCriterion("SHOW <>", value, "show");
            return (Criteria) this;
        }

        public Criteria andShowGreaterThan(Boolean value) {
            addCriterion("SHOW >", value, "show");
            return (Criteria) this;
        }

        public Criteria andShowGreaterThanOrEqualTo(Boolean value) {
            addCriterion("SHOW >=", value, "show");
            return (Criteria) this;
        }

        public Criteria andShowLessThan(Boolean value) {
            addCriterion("SHOW <", value, "show");
            return (Criteria) this;
        }

        public Criteria andShowLessThanOrEqualTo(Boolean value) {
            addCriterion("SHOW <=", value, "show");
            return (Criteria) this;
        }

        public Criteria andShowIn(List<Boolean> values) {
            addCriterion("SHOW in", values, "show");
            return (Criteria) this;
        }

        public Criteria andShowNotIn(List<Boolean> values) {
            addCriterion("SHOW not in", values, "show");
            return (Criteria) this;
        }

        public Criteria andShowBetween(Boolean value1, Boolean value2) {
            addCriterion("SHOW between", value1, value2, "show");
            return (Criteria) this;
        }

        public Criteria andShowNotBetween(Boolean value1, Boolean value2) {
            addCriterion("SHOW not between", value1, value2, "show");
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