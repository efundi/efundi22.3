import java.net.MalformedURLException;
import java.net.URL;
import java.util.Calendar;
import java.util.List;
import java.util.TreeSet;

import edu.nwu.sakaistudentlink.services.ModuleOffering;
import jakarta.xml.ws.BindingProvider;
import za.ac.nwu.wsdl.courseoffering.CourseOfferingService;
import za.ac.nwu.wsdl.courseoffering.CourseOfferingService_Service;
import za.ac.nwu.wsdl.courseoffering.DoesNotExistException_Exception;
import za.ac.nwu.wsdl.courseoffering.InvalidParameterException_Exception;
import za.ac.nwu.wsdl.courseoffering.MissingParameterException_Exception;
import za.ac.nwu.wsdl.courseoffering.ModuleOfferingInfo;
import za.ac.nwu.wsdl.courseoffering.OperationFailedException_Exception;
import za.ac.nwu.wsdl.courseoffering.PermissionDeniedException_Exception;
import za.ac.nwu.wsdl.studentacademicregistration.AcademicPeriodInfo;
import za.ac.nwu.wsdl.studentacademicregistration.ContextInfo;
import za.ac.nwu.wsdl.studentacademicregistration.ModuleOfferingSearchCriteriaInfo;
import za.ac.nwu.wsdl.studentacademicregistration.StudentAcademicRegistrationService;
import za.ac.nwu.wsdl.studentacademicregistration.StudentAcademicRegistrationService_Service;

public class WebserviceTest {

	public static void main(String[] args) {
		
//		testStudentAcademicRegistrationService();
		testCourseOfferingService();
	
	}

	private static void testStudentAcademicRegistrationService() {
		
		StringBuilder students = new StringBuilder();
		Calendar calendar = Calendar.getInstance();

		ModuleOfferingSearchCriteriaInfo searchCriteria = new ModuleOfferingSearchCriteriaInfo();

		AcademicPeriodInfo academicPeriodInfo = new AcademicPeriodInfo();
		academicPeriodInfo.setAcadPeriodtTypeKey("vss.code.AcademicPeriod.Year");
		academicPeriodInfo.setAcadPeriodValue(Integer.toString(calendar.get(Calendar.YEAR)));

		searchCriteria.setAcademicPeriod(academicPeriodInfo);
		searchCriteria.setModuleSubjectCode("ALDA");
		// searchCriteria.setModuleNumber(moduleDetail.getModuleNumber());
		searchCriteria.setModuleSite("1");
		// searchCriteria.setMethodOfDeliveryTypeKey(moduleDetail.getMethodOfDeliveryCodeParam());
		// searchCriteria.setModeOfDeliveryTypeKey(moduleDetail.getModeOfDeliveryCodeParam());

		try {                      
			URL wsdlURL = new URL("http://workflow7prd.nwu.ac.za:80/sapi-vss-v8/StudentAcademicRegistrationService/StudentAcademicRegistrationService?wsdl");
	        StudentAcademicRegistrationService_Service service = new StudentAcademicRegistrationService_Service(wsdlURL);
	        StudentAcademicRegistrationService port = service.getStudentAcademicRegistrationServicePort();      
	        
	        ContextInfo contextInfo = new ContextInfo();
	        contextInfo.setSubscriberClientName("SOAPUI");

			((BindingProvider) port).getRequestContext().put( BindingProvider.USERNAME_PROPERTY, "sapiappreadprod");
			((BindingProvider) port).getRequestContext().put( BindingProvider.PASSWORD_PROPERTY, "5p@ssw0rd4pr0dr");

			List<String> studentUserNames = port.getStudentAcademicRegistrationByModuleOffering(searchCriteria, contextInfo);
	        
	        for (int j = 0; j < studentUserNames.size(); j++) {
	        	String studentUserName = studentUserNames.get(j);
	        	students.append(studentUserName);
	            if (j != studentUserNames.size() - 1) {
	                students.append(",");
	            }
			} 

			System.out.println(students);

		} catch (Exception e) {
			e.printStackTrace();
		} 
	}

	private static void testCourseOfferingService() {

//        List<ModuleOffering> modules = new ArrayList<ModuleOffering>();
        TreeSet<ModuleOffering> moduleSet = new TreeSet<ModuleOffering>();
        Calendar calendar = Calendar.getInstance();
        
        za.ac.nwu.wsdl.courseoffering.ModuleOfferingSearchCriteriaInfo searchCriteria = new za.ac.nwu.wsdl.courseoffering.ModuleOfferingSearchCriteriaInfo();
        za.ac.nwu.wsdl.courseoffering.AcademicPeriodInfo academicPeriodInfo = new za.ac.nwu.wsdl.courseoffering.AcademicPeriodInfo();
        academicPeriodInfo.setAcadPeriodtTypeKey("vss.code.AcademicPeriod.Year");
        academicPeriodInfo.setAcadPeriodValue(Integer.toString(calendar.get(Calendar.YEAR)));

		searchCriteria.setAcademicPeriod(academicPeriodInfo);
		
		searchCriteria.setModuleSubjectCode("ALDA");
		searchCriteria.setModuleSite("1");
		
//        String webserviceUrl = settingsProperties
//                .getProperty("ws.module.url", "http://workflowprd.nwu.ac.za/student-tracs-v3/CourseOfferingTracsService/OfferingTracsService?wsdl");
//        String contextInfoUsername = settingsProperties.getProperty("nwu.context.info.username", "sapiappreadprod");
//        String contextInfoPassword = settingsProperties.getProperty("nwu.context.info.password", "5p@ssw0rd4pr0dr");
        		
		try {

			URL wsdlURL = new URL("http://workflow7prd.nwu.ac.za:80/curriculum-delivery-v3/CourseOfferingService?wsdl");
			
			CourseOfferingService_Service service = new CourseOfferingService_Service(wsdlURL);
	        CourseOfferingService port = service.getCourseOfferingServicePort();      
	        
	        za.ac.nwu.wsdl.courseoffering.ContextInfo contextInfo = new za.ac.nwu.wsdl.courseoffering.ContextInfo();
	        contextInfo.setSubscriberClientName("SOAPUI");

			((BindingProvider) port).getRequestContext().put( BindingProvider.USERNAME_PROPERTY, "sapiappreadprod");
			((BindingProvider) port).getRequestContext().put( BindingProvider.PASSWORD_PROPERTY, "5p@ssw0rd4pr0dr");

			List<ModuleOfferingInfo> modules = port.getModuleOfferingBySearchCriteria(searchCriteria, "vss.code.LANGUAGE.2", contextInfo);
			
			if (modules != null) {
				System.out.println(modules);
			}
			
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (DoesNotExistException_Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidParameterException_Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MissingParameterException_Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (OperationFailedException_Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (PermissionDeniedException_Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
