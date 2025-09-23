package org.sakaiproject.tool.gradebook;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

public class AssignmentGradeRecordAudit implements Serializable {

	private static final long serialVersionUID = -4888697853542822267L;

	private Long id;
	private AssignmentGradeRecord parentGradeRecord;
	private String graderId;
	private String studentId;
	private Date dateRecorded;
	private String pointsEarned;
	private Boolean excludedFromGrade;
	private Date auditDatetime;
	private String auditAction;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public AssignmentGradeRecord getParentGradeRecord() {
		return parentGradeRecord;
	}

	public void setParentGradeRecord(AssignmentGradeRecord parentGradeRecord) {
		this.parentGradeRecord = parentGradeRecord;
	}

	public String getGraderId() {
		return graderId;
	}

	public void setGraderId(String graderId) {
		this.graderId = graderId;
	}

	public String getStudentId() {
		return studentId;
	}

	public void setStudentId(String studentId) {
		this.studentId = studentId;
	}

	public Date getDateRecorded() {
		return dateRecorded;
	}

	public void setDateRecorded(Date dateRecorded) {
		this.dateRecorded = dateRecorded;
	}

	public String getPointsEarned() {
		return pointsEarned;
	}

	public void setPointsEarned(String pointsEarned) {
		this.pointsEarned = pointsEarned;
	}

	public Boolean getExcludedFromGrade() {
		return excludedFromGrade;
	}

	public void setExcludedFromGrade(Boolean excludedFromGrade) {
		this.excludedFromGrade = excludedFromGrade;
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
		if (!(o instanceof AssignmentGradeRecord))
			return false;

		AssignmentGradeRecord record = (AssignmentGradeRecord) o;

		return Objects.equals(graderId, record.getGraderId()) && Objects.equals(studentId, record.getStudentId())
				&& Objects.equals(dateRecorded, record.getDateRecorded())
				&& Objects.equals(pointsEarned, record.getPointsEarned())
				&& Objects.equals(excludedFromGrade, record.isExcludedFromGrade());
	}

	@Override
	public int hashCode() {
		// Compute hash based on fields compared in equals, excluding audit fields
		return Objects.hash(graderId, studentId, dateRecorded, pointsEarned, excludedFromGrade);
	}
}
