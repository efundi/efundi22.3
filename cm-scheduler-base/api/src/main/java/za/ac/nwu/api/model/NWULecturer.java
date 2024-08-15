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
@Table(name = "cm_course_section_instructor")
@Data
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)

//@NamedQueries({
//		@NamedQuery(name = "FindLecturersByYear", query = "SELECT l FROM NWULecturer l WHERE l.year = :year"),
//		@NamedQuery(name = "FindLecturersByYearOrderBySakaiSiteId", query = "SELECT l FROM NWULecturer l WHERE l.year = :year ORDER BY sakaiSiteId") })
public class NWULecturer {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private Long id;

	@EqualsAndHashCode.Include
	@Column(name = "course_code", length = 45, nullable = false)
	private String courseCode;

	@Column(name = "description", length = 45, nullable = false)
	private String description;

	@EqualsAndHashCode.Include
	@Column(name = "year", nullable = false)
	private Integer year;

	@Column(name = "status", length = 45, nullable = false)
	private String status;

	@Column(name = "published_code", length = 45, nullable = false)
	private String publishedCode;

	@EqualsAndHashCode.Include
	@Column(name = "term", length = 45, nullable = false)
	private String term;

    @Type(type = "org.hibernate.type.InstantType")
	@Column(name = "start_date", nullable = false)
	private Instant startDate;

    @Type(type = "org.hibernate.type.InstantType")
	@Column(name = "end_date", nullable = false)
	private Instant endDate;

	@EqualsAndHashCode.Include
	@Column(name = "campus", length = 45, nullable = false)
	private String campus;

	@EqualsAndHashCode.Include
	@Column(name = "section_code", length = 8, nullable = false)
	private String sectionCode;

	@Column(name = "section_descr", length = 45, nullable = false)
	private String sectionDescr;

    @Type(type = "org.hibernate.type.InstantType")
	@Column(name = "section_start_date", nullable = false)
	private Instant sectionStartDate;

    @Type(type = "org.hibernate.type.InstantType")
	@Column(name = "section_end_date", nullable = false)
	private Instant sectionEndDate;

	@EqualsAndHashCode.Include
	@Column(name = "nwu_number", nullable = false)
	private Integer nwuNumber;

	@Column(name = "instructor_name", length = 45, nullable = false)
	private String instructorName;

	@Column(name = "max_students", nullable = false)
	private Integer maxStudents;

    @Type(type = "org.hibernate.type.InstantType")
	@Column(name = "audit_date_time", nullable = true)
	private Instant auditDateTime;

//	@Column(name = "SAKAI_SITE_ID", length = 30, nullable = true)
//	private String sakaiSiteId;

	public NWULecturer() {
	}

	public NWULecturer(String courseCode, String description, Integer year, String status, String publishedCode,
			String term, Instant startDate, Instant endDate, String campus, String sectionCode, String sectionDescr,
			Instant sectionStartDate, Instant sectionEndDate, Integer nwuNumber, String instructorName,
			Integer maxStudents) {
		this.courseCode = courseCode;
		this.description = description;
		this.year = year;
		this.status = status;
		this.publishedCode = publishedCode;
		this.term = term;
		this.startDate = startDate;
		this.endDate = endDate;
		this.campus = campus;
		this.sectionCode = sectionCode;
		this.sectionDescr = sectionDescr;
		this.sectionStartDate = sectionStartDate;
		this.sectionEndDate = sectionEndDate;
		this.nwuNumber = nwuNumber;
		this.instructorName = instructorName;
		this.maxStudents = maxStudents;
	}

}
