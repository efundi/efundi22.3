package za.ac.nwu.api.model;

import java.time.Instant;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Type;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Entity
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Table(name = "gb_exam_lesson")
@Data
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)

public class NWUGBExamLesson {	

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private Long id;
	
	@EqualsAndHashCode.Include
	@Column(name = "campus", length = 8, nullable = false)
	private String campus;

	@EqualsAndHashCode.Include
	@Column(name = "term", length = 16, nullable = false)
	private String term;
	
	@EqualsAndHashCode.Include
	@Column(name = "course_code", length = 12, nullable = false)
	private String courseCode;
	
	@EqualsAndHashCode.Include
	@Column(name = "section_code", length = 8, nullable = false)
	private String sectionCode;

	@EqualsAndHashCode.Include
	@Column(name = "exam_lesson_code", length = 8, nullable = false)
	private String examLessonCode;	

	@EqualsAndHashCode.Include
	@Column(name = "exam_lesson_number", nullable = false)
	private Integer examLessonNumber;

	@Column(name = "exam_lesson_name", length = 40, nullable = false)
	private String examLessonName;	
	
	@Column(name = "percentage", nullable = false)
	private Double percentage;

	@EqualsAndHashCode.Include
	@Column(name = "exam_lesson_max_score", nullable = false)
	private Integer maxScore;

	@Column(name = "action", length = 6, nullable = false)
	private String action;
	
	@Column(name = "efundi_gradebook_id", nullable = true)
	private Long efundiGradebookId;

    @Type(type = "org.hibernate.type.InstantType")
	@Column(name = "audit_date_time", nullable = false)
	private Instant auditDateTime;
    
	public NWUGBExamLesson() {
	}

	public NWUGBExamLesson(String campus, String term, String courseCode, String sectionCode, String examLessonCode,
			Integer examLessonNumber, String examLessonName, Double percentage, Integer maxScore, String action) {
		this.campus = campus;
		this.term = term;
		this.courseCode = courseCode;
		this.sectionCode = sectionCode;
		this.examLessonCode = examLessonCode;
		this.examLessonNumber = examLessonNumber;
		this.examLessonName = examLessonName;
		this.percentage = percentage;
		this.maxScore = maxScore;
		this.action = action;
	}	
}
