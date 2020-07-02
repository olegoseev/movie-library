package com.oseevol.repository.specs;

public interface SearchSpecification {
	void addCriteria(SearchCriteria criteria);
	boolean hasCriteria();
}
