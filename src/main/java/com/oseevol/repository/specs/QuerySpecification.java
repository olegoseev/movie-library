package com.oseevol.repository.specs;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

public class QuerySpecification <T> {

	private final List<SearchCriteria> criterias = new ArrayList<>();
	
	private static final String WILDCARD = "%";

	protected String containsLowerCase(String searchField) {
		return WILDCARD + searchField.toLowerCase() + WILDCARD;
	}

	protected Specification<T> attributeContains(String attribute, String value) {
		return (root, query, cb) -> {
			if (value == null) {
				return null;
			}
			return cb.like(cb.lower(root.get(attribute)), containsLowerCase(value));
		};
	}

	protected Predicate attributeContains(Root<T> root, CriteriaBuilder cb, String attribute, String value) {
		return cb.like(cb.lower(root.get(attribute)), containsLowerCase(value));
	}
	
	
	protected Predicate attributeHasWithNoCase(Root<T> root, CriteriaBuilder cb, String attribute, String value) {
		return cb.equal(cb.lower(root.get(attribute)), containsLowerCase(value));
	}
	
	protected Predicate attributeHas(Root<T> root, CriteriaBuilder cb, String attribute, Object value) {
		return cb.equal(root.get(attribute), value);
	}
	

	public void addCriteria(SearchCriteria criteria) {
		criterias.add(criteria);
	}
	
	public boolean hasCriteria() {
		return !criterias.isEmpty();
	}

	public Specification<T> getFilter(AssemblyOption option) {
		return (root, query, cb) -> {
			
			List<Predicate> predicates = new ArrayList<>();
			
			for(var criteria : criterias) {
				
				switch (criteria.getOperation()) {
				case EQUAL:
					predicates.add(attributeHasWithNoCase(root, cb, criteria.getKey(), (String)criteria.getValue()));
					break;
				case CONTAINS:
					predicates.add(attributeContains(root, cb, criteria.getKey(), (String)criteria.getValue()));
					break;
				default:
					break;
				}
			}
			if(option == AssemblyOption.AND) {
				return cb.and(predicates.toArray(new Predicate[0]));
			}
			return cb.or(predicates.toArray(new Predicate[0]));
		};
	}

}
