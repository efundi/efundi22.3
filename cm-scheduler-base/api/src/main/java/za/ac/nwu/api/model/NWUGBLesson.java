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
@Table(name = "gb_lesson_plan")
@Data
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)

public class NWUGBLesson {	

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private Long id;

	@EqualsAndHashCode.Include
	@Column(name = "course_id", nullable = false)
	private Long courseId;

	@EqualsAndHashCode.Include
	@Column(name = "class_test_number", nullable = false)
	private Integer classTestNumber;

	@EqualsAndHashCode.Include
	@Column(name = "class_test_code", length = 8, nullable = false)
	private String classTestCode;	

	@Column(name = "class_test_name", length = 40, nullable = true)
	private String classTestName;

	@Column(name = "class_test_max_score", nullable = false)
	private Double classTestMaxScore;
	
	@Column(name = "efundi_gradebook_id", nullable = true)
	private Long efundiGradebookId;

    @Type(type = "org.hibernate.type.InstantType")
	@Column(name = "audit_date_time", nullable = false)
	private Instant auditDateTime;
    
    @OneToOne
    @JoinColumn(name="course_id", updatable = false, insertable = false)
    @ToString.Exclude private NWUCourse course;
    
	public NWUGBLesson() {
	}

	public NWUGBLesson(Long courseId, Integer classTestNumber, String classTestCode, String classTestName,
			Double classTestMaxScore, Instant auditDateTime) {
		this.courseId = courseId;
		this.classTestNumber = classTestNumber;
		this.classTestCode = classTestCode;
		this.classTestName = classTestName;
		this.classTestMaxScore = classTestMaxScore;
		this.auditDateTime = auditDateTime;
	}

	public Object getEfundiGradebookId() {
		return this.efundiGradebookId;
	}

	public Long getId() {
		return id;
	}

	public Long getCourseSiteId() {
		return courseId;
		
	}
}
