
package za.ac.nwu.wsdl.studentacademicregistration;

import java.util.List;
import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.jws.WebResult;
import jakarta.jws.WebService;
import jakarta.xml.bind.annotation.XmlSeeAlso;
import jakarta.xml.ws.RequestWrapper;
import jakarta.xml.ws.ResponseWrapper;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 3.0.2
 * Generated source version: 3.0
 * 
 */
@WebService(name = "StudentAcademicRegistrationService", targetNamespace = "http://nwu.ac.za/wsdl/StudentAcademicRegistration")
@XmlSeeAlso({
    ObjectFactory.class
})
public interface StudentAcademicRegistrationService {


    /**
     * 
     * @param contextInfo
     * @param moduleOfferingSearchCriteriaInfo
     * @return
     *     returns java.util.List<java.lang.String>
     * @throws InvalidParameterException_Exception
     * @throws OperationFailedException_Exception
     * @throws DoesNotExistException_Exception
     * @throws MissingParameterException_Exception
     * @throws PermissionDeniedException_Exception
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "getActiveStudentAcademicRegistrationByModuleOffering", targetNamespace = "http://nwu.ac.za/wsdl/StudentAcademicRegistration", className = "za.ac.nwu.wsdl.studentacademicregistration.GetActiveStudentAcademicRegistrationByModuleOffering")
    @ResponseWrapper(localName = "getActiveStudentAcademicRegistrationByModuleOfferingResponse", targetNamespace = "http://nwu.ac.za/wsdl/StudentAcademicRegistration", className = "za.ac.nwu.wsdl.studentacademicregistration.GetActiveStudentAcademicRegistrationByModuleOfferingResponse")
    public List<String> getActiveStudentAcademicRegistrationByModuleOffering(
        @WebParam(name = "moduleOfferingSearchCriteriaInfo", targetNamespace = "")
        ModuleOfferingSearchCriteriaInfo moduleOfferingSearchCriteriaInfo,
        @WebParam(name = "contextInfo", targetNamespace = "")
        ContextInfo contextInfo)
        throws DoesNotExistException_Exception, InvalidParameterException_Exception, MissingParameterException_Exception, OperationFailedException_Exception, PermissionDeniedException_Exception
    ;

    /**
     * 
     * @param contextInfo
     * @param academicPeriodInfo
     * @param systemLanguageTypeKey
     * @param univNumber
     * @return
     *     returns java.util.List<za.ac.nwu.wsdl.studentacademicregistration.StudentAcademicRegistrationInfo>
     * @throws OperationFailedException_Exception
     * @throws InvalidParameterException_Exception
     * @throws DoesNotExistException_Exception
     * @throws MissingParameterException_Exception
     * @throws PermissionDeniedException_Exception
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "getStudentAcademicRegistrationByLang", targetNamespace = "http://nwu.ac.za/wsdl/StudentAcademicRegistration", className = "za.ac.nwu.wsdl.studentacademicregistration.GetStudentAcademicRegistrationByLang")
    @ResponseWrapper(localName = "getStudentAcademicRegistrationByLangResponse", targetNamespace = "http://nwu.ac.za/wsdl/StudentAcademicRegistration", className = "za.ac.nwu.wsdl.studentacademicregistration.GetStudentAcademicRegistrationByLangResponse")
    public List<StudentAcademicRegistrationInfo> getStudentAcademicRegistrationByLang(
        @WebParam(name = "univNumber", targetNamespace = "")
        String univNumber,
        @WebParam(name = "academicPeriodInfo", targetNamespace = "")
        AcademicPeriodInfo academicPeriodInfo,
        @WebParam(name = "systemLanguageTypeKey", targetNamespace = "")
        String systemLanguageTypeKey,
        @WebParam(name = "contextInfo", targetNamespace = "")
        ContextInfo contextInfo)
        throws DoesNotExistException_Exception, InvalidParameterException_Exception, MissingParameterException_Exception, OperationFailedException_Exception, PermissionDeniedException_Exception
    ;

    /**
     * 
     * @param contextInfo
     * @param systemLanguageTypeKey
     * @param studentModuleAcadInfos
     * @return
     *     returns java.util.List<za.ac.nwu.wsdl.studentacademicregistration.StudentModulePrerequisteResultInfo>
     * @throws InvalidParameterException_Exception
     * @throws OperationFailedException_Exception
     * @throws DoesNotExistException_Exception
     * @throws MissingParameterException_Exception
     * @throws PermissionDeniedException_Exception
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "validateStudentModulesPrerequisite", targetNamespace = "http://nwu.ac.za/wsdl/StudentAcademicRegistration", className = "za.ac.nwu.wsdl.studentacademicregistration.ValidateStudentModulesPrerequisite")
    @ResponseWrapper(localName = "validateStudentModulesPrerequisiteResponse", targetNamespace = "http://nwu.ac.za/wsdl/StudentAcademicRegistration", className = "za.ac.nwu.wsdl.studentacademicregistration.ValidateStudentModulesPrerequisiteResponse")
    public List<StudentModulePrerequisteResultInfo> validateStudentModulesPrerequisite(
        @WebParam(name = "studentModuleAcadInfos", targetNamespace = "")
        List<StudentModuleAcademicInfo> studentModuleAcadInfos,
        @WebParam(name = "systemLanguageTypeKey", targetNamespace = "")
        String systemLanguageTypeKey,
        @WebParam(name = "contextInfo", targetNamespace = "")
        ContextInfo contextInfo)
        throws DoesNotExistException_Exception, InvalidParameterException_Exception, MissingParameterException_Exception, OperationFailedException_Exception, PermissionDeniedException_Exception
    ;

    /**
     * 
     * @param contextInfo
     * @return
     *     returns java.lang.String
     * @throws OperationFailedException_Exception
     * @throws InvalidParameterException_Exception
     * @throws DoesNotExistException_Exception
     * @throws MissingParameterException_Exception
     * @throws PermissionDeniedException_Exception
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "resetService", targetNamespace = "http://nwu.ac.za/wsdl/StudentAcademicRegistration", className = "za.ac.nwu.wsdl.studentacademicregistration.ResetService")
    @ResponseWrapper(localName = "resetServiceResponse", targetNamespace = "http://nwu.ac.za/wsdl/StudentAcademicRegistration", className = "za.ac.nwu.wsdl.studentacademicregistration.ResetServiceResponse")
    public String resetService(
        @WebParam(name = "contextInfo", targetNamespace = "")
        ContextInfo contextInfo)
        throws DoesNotExistException_Exception, InvalidParameterException_Exception, MissingParameterException_Exception, OperationFailedException_Exception, PermissionDeniedException_Exception
    ;

    /**
     * 
     * @param contextInfo
     * @param moduleOfferingSearchCriteriaInfo
     * @return
     *     returns java.util.List<java.lang.String>
     * @throws InvalidParameterException_Exception
     * @throws OperationFailedException_Exception
     * @throws DoesNotExistException_Exception
     * @throws MissingParameterException_Exception
     * @throws PermissionDeniedException_Exception
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "getStudentAcademicRegistrationByModuleOffering", targetNamespace = "http://nwu.ac.za/wsdl/StudentAcademicRegistration", className = "za.ac.nwu.wsdl.studentacademicregistration.GetStudentAcademicRegistrationByModuleOffering")
    @ResponseWrapper(localName = "getStudentAcademicRegistrationByModuleOfferingResponse", targetNamespace = "http://nwu.ac.za/wsdl/StudentAcademicRegistration", className = "za.ac.nwu.wsdl.studentacademicregistration.GetStudentAcademicRegistrationByModuleOfferingResponse")
    public List<String> getStudentAcademicRegistrationByModuleOffering(
        @WebParam(name = "moduleOfferingSearchCriteriaInfo", targetNamespace = "")
        ModuleOfferingSearchCriteriaInfo moduleOfferingSearchCriteriaInfo,
        @WebParam(name = "contextInfo", targetNamespace = "")
        ContextInfo contextInfo)
        throws DoesNotExistException_Exception, InvalidParameterException_Exception, MissingParameterException_Exception, OperationFailedException_Exception, PermissionDeniedException_Exception
    ;

    /**
     * 
     * @param contextInfo
     * @param studentModuleAcadInfo
     * @param systemLanguageTypeKey
     * @return
     *     returns za.ac.nwu.wsdl.studentacademicregistration.StudentModulePrerequisteResultInfo
     * @throws OperationFailedException_Exception
     * @throws InvalidParameterException_Exception
     * @throws DoesNotExistException_Exception
     * @throws MissingParameterException_Exception
     * @throws PermissionDeniedException_Exception
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "validateStudentModulePrerequisite", targetNamespace = "http://nwu.ac.za/wsdl/StudentAcademicRegistration", className = "za.ac.nwu.wsdl.studentacademicregistration.ValidateStudentModulePrerequisite")
    @ResponseWrapper(localName = "validateStudentModulePrerequisiteResponse", targetNamespace = "http://nwu.ac.za/wsdl/StudentAcademicRegistration", className = "za.ac.nwu.wsdl.studentacademicregistration.ValidateStudentModulePrerequisiteResponse")
    public StudentModulePrerequisteResultInfo validateStudentModulePrerequisite(
        @WebParam(name = "studentModuleAcadInfo", targetNamespace = "")
        StudentModuleAcademicInfo studentModuleAcadInfo,
        @WebParam(name = "systemLanguageTypeKey", targetNamespace = "")
        String systemLanguageTypeKey,
        @WebParam(name = "contextInfo", targetNamespace = "")
        ContextInfo contextInfo)
        throws DoesNotExistException_Exception, InvalidParameterException_Exception, MissingParameterException_Exception, OperationFailedException_Exception, PermissionDeniedException_Exception
    ;

    /**
     * 
     * @param contextInfo
     * @param academicPeriodInfo
     * @param univNumber
     * @return
     *     returns java.util.List<za.ac.nwu.wsdl.studentacademicregistration.StudentAcademicRegistrationInfo>
     * @throws OperationFailedException_Exception
     * @throws InvalidParameterException_Exception
     * @throws DoesNotExistException_Exception
     * @throws MissingParameterException_Exception
     * @throws PermissionDeniedException_Exception
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "getStudentAcademicRegistration", targetNamespace = "http://nwu.ac.za/wsdl/StudentAcademicRegistration", className = "za.ac.nwu.wsdl.studentacademicregistration.GetStudentAcademicRegistration")
    @ResponseWrapper(localName = "getStudentAcademicRegistrationResponse", targetNamespace = "http://nwu.ac.za/wsdl/StudentAcademicRegistration", className = "za.ac.nwu.wsdl.studentacademicregistration.GetStudentAcademicRegistrationResponse")
    public List<StudentAcademicRegistrationInfo> getStudentAcademicRegistration(
        @WebParam(name = "univNumber", targetNamespace = "")
        String univNumber,
        @WebParam(name = "academicPeriodInfo", targetNamespace = "")
        AcademicPeriodInfo academicPeriodInfo,
        @WebParam(name = "contextInfo", targetNamespace = "")
        ContextInfo contextInfo)
        throws DoesNotExistException_Exception, InvalidParameterException_Exception, MissingParameterException_Exception, OperationFailedException_Exception, PermissionDeniedException_Exception
    ;

}