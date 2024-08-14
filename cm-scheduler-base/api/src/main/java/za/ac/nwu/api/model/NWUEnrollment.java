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
@Table(name = "cm_student_enrollment")
@Data
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)

//@NamedQueries({
//		@NamedQuery(name = "FindEnrollmentsByAcadYear", query = "SELECT e FROM NWUEnrollment e WHERE e.acadYear = :acadYear"),
//		@NamedQuery(name = "FindEnrollmentsByAcadYearOrderBySakaiSiteId", query = "SELECT e FROM NWUEnrollment e WHERE e.acadYear = :acadYear ORDER BY sakaiSiteId"),
//		@NamedQuery(name = "FindEnrollmentsForCurrentAndNextAcadYear", query = "SELECT e FROM NWUEnrollment e WHERE (e.acadYear = :acadYear OR e.acadYear = :acadYearNext) ORDER BY sakaiSiteId") })
public class NWUEnrollment {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private Long id;

	@EqualsAndHashCode.Include
	@Column(name = "nwu_number", nullable = false)
	private Integer nwuNumber;

	@Column(name = "student_status", length = 45, nullable = false)
	private String studentStatus;

    @Type(type = "org.hibernate.type.InstantType")
	@Column(name = "enrol_end_date", nullable = true)
	private Instant enrolEndDate;

	@Column(name = "presentation_method", length = 45, nullable = false)
	private String presentationMethod;

	@Column(name = "mode_of_delivery", length = 45, nullable = false)
	private String modeOfDelivery;

	@Column(name = "faculty", length = 45, nullable = false)
	private String faculty;

	@EqualsAndHashCode.Include
	@Column(name = "campus_code", length = 45, nullable = false)
	private String campusCode;

	@EqualsAndHashCode.Include
	@Column(name = "program_version_code", length = 45, nullable = false)
	private String programVersionCode;

    @Type(type = "org.hibernate.type.InstantType")
	@Column(name = "registration_date", nullable = false)
	private Instant registrationDate;

	@EqualsAndHashCode.Include
	@Column(name = "enrollment_year", nullable = false)
	private Integer enrollmentYear;

	@Column(name = "year_level", length = 45, nullable = false)
	private String yearLevel;

	@EqualsAndHashCode.Include
	@Column(name = "course", length = 45, nullable = false)
	private String course;

	@Column(name = "practical_type", length = 45, nullable = true)
	private String practicalType;

	@Column(name = "learning_type", length = 45, nullable = true)
	private String learningType;

	@Column(name = "is_practical")
	private Boolean isPractical = Boolean.FALSE;

	@Column(name = "is_research")
	private Boolean isResearch = Boolean.FALSE;

	@EqualsAndHashCode.Include
	@Column(name = "term", length = 45, nullable = false)
	private String term;

    @Type(type = "org.hibernate.type.InstantType")
	@Column(name = "term_start_date", nullable = false)
	private Instant termStartDate;

    @Type(type = "org.hibernate.type.InstantType")
	@Column(name = "term_end_date", nullable = false)
	private Instant termEndDate;

	@EqualsAndHashCode.Include
	@Column(name = "section_code", length = 8, nullable = false)
	private String sectionCode;

	@Column(name = "section_descr", length = 45, nullable = false)
	private String sectionDescr;

    @Type(type = "org.hibernate.type.InstantType")
	@Column(name = "audit_date_time", nullable = true)
	private Instant auditDateTime;

//	@Column(name = "SAKAI_SITE_ID", length = 30, nullable = true)
//	private String sakaiSiteId;

	public NWUEnrollment() {
	}

	public NWUEnrollment(Integer nwuNumber, String studentStatus, Instant enrolEndDate, String presentationMethod,
			String modeOfDelivery, String faculty, String campusCode, String programVersionCode,
			Instant registrationDate, Integer enrollmentYear, String yearLevel, String course, String practicalType,
			String learningType, Boolean isPractical, Boolean isResearch, String term, Instant termStartDate,
			Instant termEndDate, String sectionCode, String sectionDescr) {
		this.nwuNumber = nwuNumber;
		this.studentStatus = studentStatus;
		this.enrolEndDate = enrolEndDate;
		this.presentationMethod = presentationMethod;
		this.modeOfDelivery = modeOfDelivery;
		this.faculty = faculty;
		this.campusCode = campusCode;
		this.programVersionCode = programVersionCode;
		this.registrationDate = registrationDate;
		this.enrollmentYear = enrollmentYear;
		this.yearLevel = yearLevel;
		this.course = course;
		this.practicalType = practicalType;
		this.learningType = learningType;
		this.isPractical = isPractical;
		this.isResearch = isResearch;
		this.term = term;
		this.termStartDate = termStartDate;
		this.termEndDate = termEndDate;
		this.sectionCode = sectionCode;
		this.sectionDescr = sectionDescr;
	}
}
