package za.ac.nwu.api.model;

import java.time.Instant;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Type;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Entity
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Table(name = "cm_student_enrollment")
@Data
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)

public class NWUStudentEnrollment {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private Long id;

	@EqualsAndHashCode.Include
	@Column(name = "course_id", nullable = false)
	private Long courseId;

	@EqualsAndHashCode.Include
	@Column(name = "nwu_number", nullable = false)
	private Integer nwuNumber;

	@Column(name = "student_status", length = 45, nullable = false)
	private String studentStatus;

	@Column(name = "faculty", length = 45, nullable = false)
	private String faculty;

    @Type(type = "org.hibernate.type.InstantType")
	@Column(name = "audit_date_time", nullable = true)
	private Instant auditDateTime;

    @ManyToOne
    @JoinColumn(name = "id", updatable = false, insertable = false)
    private NWUCourse course;

	public NWUStudentEnrollment() {
	}

	public NWUStudentEnrollment(Long courseId, Integer nwuNumber, String studentStatus, String faculty,
			Instant auditDateTime) {
		this.courseId = courseId;
		this.nwuNumber = nwuNumber;
		this.studentStatus = studentStatus;
		this.faculty = faculty;
		this.auditDateTime = auditDateTime;
	}
}
