package za.ac.nwu.api.model;

import java.time.Instant;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
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
@Table(name = "cm_curriculum_course")
@Data
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)

@NamedQueries({
	@NamedQuery(name = "FindAllCoursesByYearAndSiteId", query = "SELECT c FROM NWUCourse c WHERE c.year = :year AND (c.efundiSiteId is null OR c.efundiSiteId = '')"),
	@NamedQuery(name = "FindAllCoursesByYear", query = "SELECT c FROM NWUCourse c WHERE c.year = :year"),
	@NamedQuery(name = "FindAllCoursesWithNoSiteId", query = "SELECT c FROM NWUCourse c WHERE c.efundiSiteId is null OR c.efundiSiteId = '' "),
	@NamedQuery(name = "FindAllCoursesWithSiteId", query = "SELECT c FROM NWUCourse c WHERE c.efundiSiteId is not null AND c.efundiSiteId != '' ")})

public class NWUCourse {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private Long id;

	@EqualsAndHashCode.Include
	@Column(name = "campus", length = 8, nullable = false)
	private String campus;

	@EqualsAndHashCode.Include
	@Column(name = "enrolment_year", nullable = false)
	private Integer year;

	@EqualsAndHashCode.Include
	@Column(name = "term", length = 16, nullable = false)
	private String term;
	
	@Column(name = "term_start_date", nullable = false)
	private LocalDate termStartDate;
	
	@Column(name = "term_end_date", nullable = true)
	private LocalDate termEndDate;

	@EqualsAndHashCode.Include
	@Column(name = "course_code", length = 12, nullable = false)
	private String courseCode;

	@Column(name = "course_descr", length = 99, nullable = false)
	private String courseDescr;

	@EqualsAndHashCode.Include
	@Column(name = "section_code", length = 8, nullable = false)
	private String sectionCode;

	@Column(name = "section_descr", length = 99, nullable = true)
	private String sectionDescr;

	@Column(name = "efundi_site_id", length = 99, nullable = true)
	private String efundiSiteId;

    @Type(type = "org.hibernate.type.InstantType")
	@Column(name = "audit_date_time", nullable = false)
	private Instant auditDateTime;
    
    @OneToOne(mappedBy = "course")
    @ToString.Exclude private NWULecturer lecturer;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy="course")  
    @ToString.Exclude private List<NWUStudentEnrollment> students = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy="course")  
    @ToString.Exclude private List<NWUGBLesson> lessons = new ArrayList<>();
        
	public NWUCourse() {
	}

	public NWUCourse(String campus, Integer year, String term, LocalDate termStartDate, LocalDate termEndDate, String courseCode, String courseDescr, String sectionCode, String sectionDescr, String efundiSiteId, Instant auditDateTime) {
		this.campus = campus;
		this.year = year;
		this.term = term;
		this.termStartDate = termStartDate;
		this.termEndDate = termEndDate;
		this.courseCode = courseCode;
		this.courseDescr = courseDescr;
		this.sectionCode = sectionCode;
		this.sectionDescr = sectionDescr;
		this.efundiSiteId = efundiSiteId;
		this.auditDateTime = auditDateTime;
	}
}
