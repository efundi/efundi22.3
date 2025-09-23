package org.sakaiproject.tool.gradebook;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

public class GradableObjectAudit implements Serializable {

	private static final long serialVersionUID = 3500841148859190706L;
	private Long id;
	private GradebookAssignment parentGradableObject;
	private String name;
	private boolean removed;
	private Double pointsPossible;
	private Date dueDate;
	private Boolean notCounted;
	private Boolean released;
	private Long categoryId;
	private Boolean ungraded;
	private Boolean extraCredit = Boolean.FALSE;
	private Boolean countNullsAsZeros;
	private Boolean hideInAllGradesTable = Boolean.FALSE;
	private Date auditDatetime;
	private String auditAction;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public GradebookAssignment getParentGradableObject() {
		return parentGradableObject;
	}

	public void setParentGradableObject(GradebookAssignment parentGradableObject) {
		this.parentGradableObject = parentGradableObject;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isRemoved() {
		return removed;
	}

	public void setRemoved(boolean removed) {
		this.removed = removed;
	}

	public Double getPointsPossible() {
		return pointsPossible;
	}

	public void setPointsPossible(Double pointsPossible) {
		this.pointsPossible = pointsPossible;
	}

	public Date getDueDate() {
		return dueDate;
	}

	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}

	public Boolean isNotCounted() {
		return notCounted;
	}

	public void setNotCounted(Boolean notCounted) {
		this.notCounted = notCounted;
	}

	public Boolean isReleased() {
		return released;
	}

	public void setReleased(Boolean released) {
		this.released = released;
	}

	public Long getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}

	public Boolean isUngraded() {
		return ungraded;
	}

	public void setUngraded(Boolean ungraded) {
		this.ungraded = ungraded;
	}

	public Boolean isExtraCredit() {
		return extraCredit;
	}

	public void setExtraCredit(Boolean extraCredit) {
		this.extraCredit = extraCredit;
	}

	public Boolean isCountNullsAsZeros() {
		return countNullsAsZeros;
	}

	public void setCountNullsAsZeros(Boolean countNullsAsZeros) {
		this.countNullsAsZeros = countNullsAsZeros;
	}

	public Boolean isHideInAllGradesTable() {
		return hideInAllGradesTable;
	}

	public void setHideInAllGradesTable(Boolean hideInAllGradesTable) {
		this.hideInAllGradesTable = hideInAllGradesTable;
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
		if (!(o instanceof GradebookAssignment))
			return false;

		GradebookAssignment parent = (GradebookAssignment) o;
		return removed == parent.isRemoved() && Objects.equals(name, parent.getName())
				&& Objects.equals(pointsPossible, parent.getPointsPossible())
				&& Objects.equals(dueDate, parent.getDueDate()) && Objects.equals(notCounted, parent.isNotCounted())
				&& Objects.equals(released, parent.isReleased())
				&& Objects.equals(categoryId, parent.getCategory() != null ? parent.getCategory().getId() : null)
				&& Objects.equals(ungraded, parent.getUngraded()) && Objects.equals(extraCredit, parent.isExtraCredit())
				&& Objects.equals(countNullsAsZeros, parent.getCountNullsAsZeros())
				&& Objects.equals(hideInAllGradesTable, parent.isHideInAllGradesTable());
	}

	@Override
	public int hashCode() {
		return Objects.hash(removed, name, pointsPossible, dueDate, notCounted, released, categoryId, ungraded,
				extraCredit, countNullsAsZeros, hideInAllGradesTable);
	}
}
