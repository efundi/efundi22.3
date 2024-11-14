package za.ac.nwu.api.model;

import java.time.Instant;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Type;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Entity
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Table(name = "cm_course_section_instructor")
@Data
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)

public class NWULecturer {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private Long id;

	@EqualsAndHashCode.Include
	@Column(name = "course_id", nullable = false)
	private Long courseId;

	@EqualsAndHashCode.Include
	@Column(name = "instructor_number", nullable = false)
	private Integer instructorNumber;

	@Column(name = "instructor_name", length = 160, nullable = true)
	private String instructorName;

    @Type(type = "org.hibernate.type.InstantType")
	@Column(name = "audit_date_time", nullable = false)
	private Instant auditDateTime;
    
    @OneToOne
    @JoinColumn(name="course_id", updatable = false, insertable = false)
    @ToString.Exclude private NWUCourse course;
    
	public NWULecturer() {
	}

	public NWULecturer(Long courseId, Integer instructorNumber, String instructorName, Instant auditDateTime) {
		this.courseId = courseId;
		this.instructorNumber = instructorNumber;
		this.instructorName = instructorName;
		this.auditDateTime = auditDateTime;
	}
}
