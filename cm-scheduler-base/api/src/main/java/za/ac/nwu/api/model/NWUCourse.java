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
		@NamedQuery(name = "FindCoursesByAcadYear", query = "SELECT c FROM NWUCourse c WHERE c.year = :year") })
//@NamedQueries({
//	@NamedQuery(name = "FindCoursesByAcadYear", query = "SELECT c FROM NWUCourse c WHERE c.year = :year AND c.sakaiSiteId is null") })

public class NWUCourse {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private Long id;

	@EqualsAndHashCode.Include
	@Column(name = "campus", length = 2, nullable = false)
	private String campus;

	@EqualsAndHashCode.Include
	@Column(name = "year", nullable = false)
	private Integer year;

	@EqualsAndHashCode.Include
	@Column(name = "term", length = 45, nullable = false)
	private String term;

	@EqualsAndHashCode.Include
	@Column(name = "course_code", length = 45, nullable = false)
	private String courseCode;

	@Column(name = "course_descr", length = 99, nullable = false)
	private String courseDescr;

	@EqualsAndHashCode.Include
	@Column(name = "section_code", length = 8, nullable = false)
	private String sectionCode;

	@Column(name = "section_descr", length = 45, nullable = false)
	private String sectionDescr;

	@EqualsAndHashCode.Include
	@Column(name = "instructor_number", nullable = false)
	private Integer instructorNumber;

	@Column(name = "instructor_name", length = 45, nullable = false)
	private String instructorName;

    @Type(type = "org.hibernate.type.InstantType")
	@Column(name = "audit_date_time", nullable = true)
	private Instant auditDateTime;

	// @OneToOne
	// @JoinColumn(foreignKey = @ForeignKey(name = "fk_course_site"))
	// private SakaiSite site;

	public NWUCourse() {
	}

	public NWUCourse(String campus, Integer year, String term, String courseCode, String courseDescr, String sectionCode, String sectionDescr, Integer instructorNumber,
			String instructorName, Instant auditDateTime) {
		this.campus = campus;
		this.year = year;
		this.term = term;
		this.courseCode = courseCode;
		this.courseDescr = courseDescr;
		this.sectionCode = sectionCode;
		this.sectionDescr = sectionDescr;
		this.instructorNumber = instructorNumber;
//		this.sakaiSiteId = sakaiSiteId;
		this.instructorName = instructorName;
		this.auditDateTime = auditDateTime;
	}
}
