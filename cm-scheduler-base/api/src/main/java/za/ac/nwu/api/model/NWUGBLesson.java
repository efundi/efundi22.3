package za.ac.nwu.api.model;

import java.time.Instant;

import javax.persistence.Column;
import javax.persistence.Entity;
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
@Table(name = "gb_lesson_plan")
@Data
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)

public class NWUGBLesson {
	
	@Id
	@EqualsAndHashCode.Include
	@Column(name = "source_system_id", length = 100, nullable = false)
	private String sourceSystemId;
	
	@EqualsAndHashCode.Include
	@Column(name = "course_id", nullable = false)
	private Long courseId;

	@Column(name = "lesson_code", length = 15, nullable = false)
	private String lessonCode;
	
	@Column(name = "class_test_number", nullable = false)
	private Integer classTestNumber;

	@Column(name = "class_test_code", length = 8, nullable = false)
	private String classTestCode;

	@Column(name = "class_test_name", length = 40, nullable = true)
	private String classTestName;

	@Column(name = "class_test_max_score", nullable = false)
	private Double classTestMaxScore;
	
	@Column(name = "efundi_gradebook_id", nullable = true)
	private Long efundiGradebookId;
	
	@Column(name = "processed", nullable = false)
    private Byte processed;

	@Column(name = "controlNote", length = 20, nullable = false)
	private String controlNote;	

	@Column(name = "action", length = 20, nullable = false)
	private String action;
	  
    @Type(type = "org.hibernate.type.InstantType")
	@Column(name = "audit_date_time", nullable = false)
	private Instant auditDateTime;

    @ManyToOne
    @JoinColumn(name="course_id", updatable = false, insertable = false)
    @ToString.Exclude private NWUCourse course;
    
	public NWUGBLesson() {
	}

	public NWUGBLesson(String sourceSystemId, Long courseId, String lessonCode, Integer classTestNumber, String classTestCode, String classTestName,
			Double classTestMaxScore, Byte processed, String controlNote, String action, Instant auditDateTime) {
		this.sourceSystemId = sourceSystemId;
		this.courseId = courseId;
		this.lessonCode = lessonCode;
		this.classTestNumber = classTestNumber;
		this.classTestCode = classTestCode;
		this.classTestName = classTestName;
		this.classTestMaxScore = classTestMaxScore;
		this.processed = processed;
		this.controlNote = controlNote;
		this.action = action;
		this.auditDateTime = auditDateTime;
	}
}
