package com.oseevol.repository.specs;

// https://attacomsian.com/blog/spring-data-jpa-specifications#
// https://leaks.wanari.com/2018/01/23/awesome-spring-specification

public final class SearchCriteria {
	private String key;
	private Object value;
	private SearchOperation operation;
	
	@SuppressWarnings("unused")
	private SearchCriteria() {
		
	};
	
	public SearchCriteria(String key, Object value, SearchOperation operation) {
		super();
		this.key = key;
		this.value = value;
		this.operation = operation;
	}
	
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public Object getValue() {
		return value;
	}
	public void setValue(Object value) {
		this.value = value;
	}
	public SearchOperation getOperation() {
		return operation;
	}
	public void setOperation(SearchOperation operation) {
		this.operation = operation;
	}
	
}
