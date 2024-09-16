package za.ac.nwu.api.model;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
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
@ToString(exclude = "students")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)

@NamedQueries({
	@NamedQuery(name = "FindAllCoursesByYearAndSiteId", query = "SELECT c FROM NWUCourse c WHERE c.year = :year AND (c.efundiSiteId is null OR c.efundiSiteId = '')"),
	@NamedQuery(name = "FindAllCoursesByYear", query = "SELECT c FROM NWUCourse c WHERE c.year = :year")})

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
    
//    @OneToOne(fetch = FetchType.LAZY)
//    @JoinColumn(foreignKey = @ForeignKey(name = "fk_post"))
    
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id") 
    private NWULecturer lecturer;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy="course")  
    private List<NWUStudentEnrollment> students = new ArrayList<>();
        
	public NWUCourse() {
	}

	public NWUCourse(String campus, Integer year, String term, String courseCode, String courseDescr, String sectionCode, String sectionDescr, String efundiSiteId, Instant auditDateTime) {
		this.campus = campus;
		this.year = year;
		this.term = term;
		this.courseCode = courseCode;
		this.courseDescr = courseDescr;
		this.sectionCode = sectionCode;
		this.sectionDescr = sectionDescr;
		this.efundiSiteId = efundiSiteId;
		this.auditDateTime = auditDateTime;
	}
}
