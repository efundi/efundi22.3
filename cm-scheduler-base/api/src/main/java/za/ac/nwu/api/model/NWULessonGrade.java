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
import org.hibernate.annotations.NamedQueries;
import org.hibernate.annotations.NamedQuery;
import org.hibernate.annotations.Type;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Entity
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Table(name = "gb_lesson_grades")
@Data
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)

@NamedQueries({
	@NamedQuery(name = "FindAllGradesByLessonId", query = "SELECT c FROM NWULessonGrade c WHERE c.lesson_id = :lesson_id"),
	@NamedQuery(name = "FindAllGradesbyNwuNumber", query = "SELECT c FROM NWULessonGrade c WHERE c.nwu_number = :nwu_number")})

public class NWULessonGrade {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private Long id;

	@EqualsAndHashCode.Include
	@Column(name = "lesson_id", length = 11, nullable = false)
	private Integer lesson_id;

	@EqualsAndHashCode.Include
	@Column(name = "nwu_number", length = 20, nullable = false)
	private Integer nwu_number;

	@EqualsAndHashCode.Include
	@Column(name = "grade", length = 3, nullable = false)
	private Double grade;	


    @Type(type = "org.hibernate.type.InstantType")
	@Column(name = "audit_date_time", nullable = false)
	private Instant auditDateTime;    
   
    @OneToOne
    @JoinColumn(name="lesson_id", updatable = false, insertable = false)
    @ToString.Exclude private NWUGBLesson nwuGbLesson;

	public NWULessonGrade(Integer lesson_id, Integer nwu_number, Double grade, Instant auditDateTime) {
		this.lesson_id = lesson_id;
		this.nwu_number = nwu_number;
		this.grade = grade;
		this.auditDateTime = auditDateTime;
	}
	
	public 
}
