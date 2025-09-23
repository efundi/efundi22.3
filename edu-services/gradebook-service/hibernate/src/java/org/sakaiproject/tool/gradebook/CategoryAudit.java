package org.sakaiproject.tool.gradebook;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

public class CategoryAudit implements Serializable {

	private static final long serialVersionUID = -1860687940278849915L;
	private Long id;
	private Category parentCategory;
	private String name;
	private Double weight;
	private Integer dropLowest;
	private Integer dropHighest;
	private Integer keepHighest;
	private boolean removed;
	private Boolean extraCredit = false;
	private Boolean unweighted;
	private Boolean equalWeightAssignments = false;
	private Boolean enforcePointWeighting;
	private Date auditDatetime;
	private String auditAction;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Category getParentCategory() {
		return parentCategory;
	}

	public void setParentCategory(Category parentCategory) {
		this.parentCategory = parentCategory;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Double getWeight() {
		return weight;
	}

	public void setWeight(Double weight) {
		this.weight = weight;
	}

	public Integer getDropLowest() {
		return dropLowest;
	}

	public void setDropLowest(Integer dropLowest) {
		this.dropLowest = dropLowest;
	}

	public Integer getDropHighest() {
		return dropHighest;
	}

	public void setDropHighest(Integer dropHighest) {
		this.dropHighest = dropHighest;
	}

	public Integer getKeepHighest() {
		return keepHighest;
	}

	public void setKeepHighest(Integer keepHighest) {
		this.keepHighest = keepHighest;
	}

	public boolean isRemoved() {
		return removed;
	}

	public void setRemoved(boolean removed) {
		this.removed = removed;
	}

	public Boolean isExtraCredit() {
		return extraCredit;
	}

	public void setExtraCredit(Boolean extraCredit) {
		this.extraCredit = extraCredit;
	}

	public Boolean isUnweighted() {
		return unweighted;
	}

	public void setUnweighted(Boolean unweighted) {
		this.unweighted = unweighted;
	}

	public Boolean isEqualWeightAssignments() {
		return equalWeightAssignments;
	}

	public void setEqualWeightAssignments(Boolean equalWeightAssignments) {
		this.equalWeightAssignments = equalWeightAssignments;
	}

	public Boolean isEnforcePointWeighting() {
		return enforcePointWeighting;
	}

	public void setEnforcePointWeighting(Boolean enforcePointWeighting) {
		this.enforcePointWeighting = enforcePointWeighting;
	}

	public Date getAuditDatetime() {
		return auditDatetime;
	}

	public void setAuditDatetime(Date auditDatetime) {
		this.auditDatetime = auditDatetime;
	}

	public String getAuditAction() {
		return auditAction;
	}

	public void setAuditAction(String auditAction) {
		this.auditAction = auditAction;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (!(o instanceof Category))
			return false;

		Category category = (Category) o;

		return removed == category.isRemoved() && Objects.equals(name, category.getName())
				&& Objects.equals(weight, category.getWeight()) && Objects.equals(dropLowest, category.getDropLowest())
				&& Objects.equals(dropHighest, category.getDropHighest())
				&& Objects.equals(keepHighest, category.getKeepHighest())
				&& Objects.equals(extraCredit, category.isExtraCredit())
				&& Objects.equals(unweighted, category.isUnweighted())
				&& Objects.equals(equalWeightAssignments, category.isEqualWeightAssignments())
				&& Objects.equals(enforcePointWeighting, category.isEnforcePointWeighting());
	}

	@Override
	public int hashCode() {
		return Objects.hash(name, weight, dropLowest, dropHighest, keepHighest, removed, extraCredit, unweighted,
				equalWeightAssignments, enforcePointWeighting);
	}
}
