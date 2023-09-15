package edu.nwu.sakaistudentlink.services;

import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import jakarta.xml.ws.BindingProvider;
import za.ac.nwu.wsdl.courseoffering.AcademicPeriodInfo;
import za.ac.nwu.wsdl.courseoffering.ContextInfo;
import za.ac.nwu.wsdl.courseoffering.CourseOfferingService;
import za.ac.nwu.wsdl.courseoffering.CourseOfferingService_Service;
import za.ac.nwu.wsdl.courseoffering.DoesNotExistException_Exception;
import za.ac.nwu.wsdl.courseoffering.InvalidParameterException_Exception;
import za.ac.nwu.wsdl.courseoffering.MissingParameterException_Exception;
import za.ac.nwu.wsdl.courseoffering.ModuleOfferingInfo;
import za.ac.nwu.wsdl.courseoffering.ModuleOfferingSearchCriteriaInfo;
import za.ac.nwu.wsdl.courseoffering.OperationFailedException_Exception;
import za.ac.nwu.wsdl.courseoffering.PermissionDeniedException_Exception;

/**
 * author: Jaco Gillman
 */
public class ModuleSearch {

	private static final Logger log = LoggerFactory.getLogger(ModuleSearch.class);

	private SettingsProperties settingsProperties;

	private ConnectionManager connectionManager;

	private ModuleSearch() {
	}

	public List<ModuleOffering> findSearchModule(Map<SearchCriteria, String> criteria)
			throws IntegrationException {

        List<ModuleOffering> modules = new ArrayList<ModuleOffering>();
		TreeSet<ModuleOffering> moduleSet = new TreeSet<ModuleOffering>();
		Calendar calendar = Calendar.getInstance();
		
		AcademicPeriodInfo academicPeriodInfo = new AcademicPeriodInfo();
		academicPeriodInfo.setAcadPeriodtTypeKey("vss.code.AcademicPeriod.YEAR");
		academicPeriodInfo.setAcadPeriodValue(Integer.toString(calendar.get(Calendar.YEAR)));
		
		ContextInfo contextInfo = new ContextInfo();
		contextInfo.setSubscriberClientName("SOAPUI");		

		ModuleOfferingSearchCriteriaInfo searchCriteria = new ModuleOfferingSearchCriteriaInfo();
		searchCriteria.setAcademicPeriod(academicPeriodInfo);

		String courseCode = criteria.get(SearchCriteria.MODULE_SUBJECT_CODE);
		if (courseCode != null) {
			searchCriteria.setModuleSubjectCode(criteria.get(SearchCriteria.MODULE_SUBJECT_CODE));
		}
		String moduleNumber = criteria.get(SearchCriteria.MODULE_NUMBER);
		if (moduleNumber != null) {
			searchCriteria.setModuleNumber(moduleNumber);
		}
		searchCriteria.setModuleSite(criteria.get(SearchCriteria.CAMPUS));
		String methodOfDel = criteria.get(SearchCriteria.METHOD_OF_DEL) == null ? null
				: criteria.get(SearchCriteria.METHOD_OF_DEL);
		if (methodOfDel != null) {
			searchCriteria.setMethodOfDeliveryTypeKey("vss.code.ENROLCAT." + methodOfDel);
		}
		String modeOfDel = criteria.get(SearchCriteria.PRESENT_CAT) == null ? null : criteria.get(SearchCriteria.PRESENT_CAT);
		if (modeOfDel != null) {
			searchCriteria.setModeOfDeliveryTypeKey("vss.code.PRESENTCAT." + modeOfDel);
		}                

		try {
			URL wsdlURL = new URL(settingsProperties
	                .getProperty("ws.module.url", "http://workflow7prd.nwu.ac.za:80/curriculum-delivery-v3/CourseOfferingService?wsdl"));
			
			CourseOfferingService_Service service = new CourseOfferingService_Service(wsdlURL);
	        CourseOfferingService port = service.getCourseOfferingServicePort();

			((BindingProvider) port).getRequestContext().put( BindingProvider.USERNAME_PROPERTY, settingsProperties.getProperty("nwu.context.info.username", "sapiappreadprod"));
			((BindingProvider) port).getRequestContext().put( BindingProvider.PASSWORD_PROPERTY, settingsProperties.getProperty("nwu.context.info.password", "5p@ssw0rd4pr0dr"));

			List<ModuleOfferingInfo> moduleOfferingList = port.getModuleOfferingBySearchCriteria(searchCriteria, "vss.code.LANGUAGE.2", contextInfo);

			int moduleIdCnt = 1;
			for (ModuleOfferingInfo moduleOfferingInfo : moduleOfferingList) {

				ModuleOffering moduleOffering = new ModuleOffering();
				moduleOffering.setKsapimotracsid(Integer.toString(moduleIdCnt)); // This has a null value returned, must have a
																				 // value
				moduleOffering.setModuleSubjectCode(moduleOfferingInfo.getModuleSubjectCode());
				moduleOffering.setModuleNumber(moduleOfferingInfo.getModuleNumber());
				moduleOffering.setModuleSite("" + Math.abs(Integer.parseInt(moduleOfferingInfo.getModuleSite())));
				moduleOffering.setMethodOfDeliveryTypeKey(moduleOfferingInfo.getMethodOfDeliveryTypeKey());
				moduleOffering.setModeOfDeliveryTypeKey(moduleOfferingInfo.getModeOfDeliveryTypeKey());
				moduleOffering.setLanguageTypeKey(moduleOfferingInfo.getLanguageTypeKey());
				moduleOffering.setTermTypeKey(moduleOfferingInfo.getTermTypeKey());
				moduleOffering.setStartDate(moduleOfferingInfo.getModuleSubjectCode());
				moduleOffering.setEndDate(moduleOfferingInfo.getModuleSubjectCode());

				String methodOfDelResult = moduleOfferingInfo.getMethodOfDeliveryTypeKey();
				if (methodOfDelResult != null && methodOfDelResult.length() != 0) {
					methodOfDelResult = methodOfDelResult.replace("vss.code.ENROLCAT.", "");
					methodOfDelResult = "vss.code.ENROLCAT." + methodOfDelResult.substring(0, methodOfDelResult.indexOf("."));
				}
				String modeOfDelResult = moduleOfferingInfo.getModeOfDeliveryTypeKey();
				if (modeOfDelResult != null && modeOfDelResult.length() != 0) {
					modeOfDelResult = modeOfDelResult.replace("vss.code.PRESENTCAT.", "");
					modeOfDelResult = "vss.code.PRESENTCAT." + modeOfDelResult.substring(0, modeOfDelResult.indexOf("."));
				}

				moduleOffering.setLinkedByLecturer(getLinkedByLecturer(calendar.get(Calendar.YEAR),
						moduleOfferingInfo.getModuleSubjectCode(), moduleOfferingInfo.getModuleNumber().substring(0, 1),
						moduleOfferingInfo.getModuleNumber().substring(1, 3),
						Campus.getCampus(moduleOfferingInfo.getModuleSite()).getNumber(), methodOfDelResult,
						modeOfDelResult));
				moduleSet.add(moduleOffering);
				moduleIdCnt += 1;
			}

		}  catch (MalformedURLException e) {
			log.error("CourseOfferingService - MalformedURLException: ", e);

			IntegrationException ie = new IntegrationException("Could not retrieve the modules ", e);
			IntegrationError error = new IntegrationError();
			error.setErrorMessage(e.getMessage());
			ie.addError(error);
			throw ie;
		} catch (DoesNotExistException_Exception e) {
			log.error("CourseOfferingService - DoesNotExistException_Exception: ", e);

			IntegrationException ie = new IntegrationException("Could not retrieve the modules ", e);
			IntegrationError error = new IntegrationError();
			error.setErrorMessage(e.getMessage());
			ie.addError(error);
			throw ie;
		} catch (InvalidParameterException_Exception e) {
			log.error("CourseOfferingService - InvalidParameterException_Exception: ", e);

			IntegrationException ie = new IntegrationException("Could not retrieve the modules ", e);
			IntegrationError error = new IntegrationError();
			error.setErrorMessage(e.getMessage());
			ie.addError(error);
			throw ie;
		} catch (MissingParameterException_Exception e) {
			log.error("CourseOfferingService - MissingParameterException_Exception: ", e);

			IntegrationException ie = new IntegrationException("Could not retrieve the modules ", e);
			IntegrationError error = new IntegrationError();
			error.setErrorMessage(e.getMessage());
			ie.addError(error);
			throw ie;
		} catch (OperationFailedException_Exception e) {
			log.error("CourseOfferingService - OperationFailedException_Exception: ", e);

			IntegrationException ie = new IntegrationException("Could not retrieve the modules ", e);
			IntegrationError error = new IntegrationError();
			error.setErrorMessage(e.getMessage());
			ie.addError(error);
			throw ie;
		} catch (PermissionDeniedException_Exception e) {
			log.error("CourseOfferingService - PermissionDeniedException_Exception: ", e);

			IntegrationException ie = new IntegrationException("Could not retrieve the modules ", e);
			IntegrationError error = new IntegrationError();
			error.setErrorMessage(e.getMessage());
			ie.addError(error);
			throw ie;
		} catch (ConnectionNotEstablishedException e) {
			log.error("CourseOfferingService - ConnectionNotEstablishedException: ", e);

			IntegrationException ie = new IntegrationException("Could not retrieve the linked lecturers  ", e);
			IntegrationError error = new IntegrationError();
			error.setErrorMessage(e.getMessage());
			ie.addError(error);
			throw ie;
		}
       
        modules.addAll(moduleSet);
        return modules;
	}

	private String getLinkedByLecturer(int year, String courseCode, String courseLevel, String courseModule, Integer campus,
			String methodOfDeliveryCode, String presentationCategoryCode) throws ConnectionNotEstablishedException {
		log.info("Performing ModuleSearch.getLinkedByLecturer");
		PreparedStatement pstmt = null;
		Connection conn = null;
		ResultSet rset = null;
		String lecturerName = "";
		try {
			conn = connectionManager.getCourseManagementConnection();
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT l.username ");
			sql.append("FROM CM_MODULES m, ");
			sql.append("CM_LECTURER l, ");
			sql.append("CM_YEAR_CAMPUS c ");
			sql.append("WHERE  ");
			sql.append("m.lecturer_f_id = l.lecturer_id ");
			sql.append("AND l.year_campus_f_id = c.year_campus_id ");
			sql.append("AND c.year = ? ");
			sql.append("AND m.course_code = ? ");
			sql.append("AND m.course_level = ? ");
			sql.append("AND m.course_module = ? ");
			if (campus.intValue() != 0) {
				sql.append("AND c.campus_code = ? ");
			}
			sql.append("and m.method_of_del = ? ");
			sql.append("and m.present_cat = ?");
			pstmt = conn.prepareStatement(sql.toString());
			int cnt = 1;
			pstmt.setInt(cnt++, year);
			pstmt.setString(cnt++, courseCode);
			pstmt.setString(cnt++, courseLevel);
			pstmt.setString(cnt++, courseModule);
			if (campus.intValue() != 0) {
				pstmt.setInt(cnt++, campus.intValue());
			}
			pstmt.setString(cnt++, methodOfDeliveryCode);
			pstmt.setString(cnt++, presentationCategoryCode);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				lecturerName = rset.getString(1);
				break;
			}
		} catch (ConnectionNotEstablishedException e) {
			log.error("A SQL Connection could not be established while performing findSearchModuleJDBC", e);
			throw new ConnectionNotEstablishedException("A SQL Connection could not be established to the Sakai database.", e);
		} catch (SQLException e) {
			log.error("SQL error occured when performing ModuleSearch.getLinkedByLecturer", e);
			throw new ConnectionNotEstablishedException(
					"SQL error occured while retrieving Linked Lecturer data from the Sakai database.", e);
		} finally {
			try {
				ConnectionManager.close(rset, pstmt, conn);
			} catch (Exception e) {
				log.error("Error closing connection to the Sakai database", e);
				throw new ConnectionNotEstablishedException("Error closing connection to the Sakai database", e);
			}
		}
		return lecturerName;
	}

	@Autowired
	public void setSettingsProperties(SettingsProperties settingsProperties) {
		this.settingsProperties = settingsProperties;
	}

	@Autowired
	public void setConnectionManager(ConnectionManager connectionManager) {
		this.connectionManager = connectionManager;
	}
}