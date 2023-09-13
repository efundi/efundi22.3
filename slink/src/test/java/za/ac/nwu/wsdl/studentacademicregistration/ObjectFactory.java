
package za.ac.nwu.wsdl.studentacademicregistration;

import javax.xml.namespace.QName;
import jakarta.xml.bind.JAXBElement;
import jakarta.xml.bind.annotation.XmlElementDecl;
import jakarta.xml.bind.annotation.XmlRegistry;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the za.ac.nwu.wsdl.studentacademicregistration package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _GetActiveStudentAcademicRegistrationByModuleOffering_QNAME = new QName("http://nwu.ac.za/wsdl/StudentAcademicRegistration", "getActiveStudentAcademicRegistrationByModuleOffering");
    private final static QName _GetActiveStudentAcademicRegistrationByModuleOfferingResponse_QNAME = new QName("http://nwu.ac.za/wsdl/StudentAcademicRegistration", "getActiveStudentAcademicRegistrationByModuleOfferingResponse");
    private final static QName _GetStudentAcademicRegistration_QNAME = new QName("http://nwu.ac.za/wsdl/StudentAcademicRegistration", "getStudentAcademicRegistration");
    private final static QName _GetStudentAcademicRegistrationByLang_QNAME = new QName("http://nwu.ac.za/wsdl/StudentAcademicRegistration", "getStudentAcademicRegistrationByLang");
    private final static QName _GetStudentAcademicRegistrationByLangResponse_QNAME = new QName("http://nwu.ac.za/wsdl/StudentAcademicRegistration", "getStudentAcademicRegistrationByLangResponse");
    private final static QName _GetStudentAcademicRegistrationByModuleOffering_QNAME = new QName("http://nwu.ac.za/wsdl/StudentAcademicRegistration", "getStudentAcademicRegistrationByModuleOffering");
    private final static QName _GetStudentAcademicRegistrationByModuleOfferingResponse_QNAME = new QName("http://nwu.ac.za/wsdl/StudentAcademicRegistration", "getStudentAcademicRegistrationByModuleOfferingResponse");
    private final static QName _GetStudentAcademicRegistrationResponse_QNAME = new QName("http://nwu.ac.za/wsdl/StudentAcademicRegistration", "getStudentAcademicRegistrationResponse");
    private final static QName _ResetService_QNAME = new QName("http://nwu.ac.za/wsdl/StudentAcademicRegistration", "resetService");
    private final static QName _ResetServiceResponse_QNAME = new QName("http://nwu.ac.za/wsdl/StudentAcademicRegistration", "resetServiceResponse");
    private final static QName _ValidateStudentModulePrerequisite_QNAME = new QName("http://nwu.ac.za/wsdl/StudentAcademicRegistration", "validateStudentModulePrerequisite");
    private final static QName _ValidateStudentModulePrerequisiteResponse_QNAME = new QName("http://nwu.ac.za/wsdl/StudentAcademicRegistration", "validateStudentModulePrerequisiteResponse");
    private final static QName _ValidateStudentModulesPrerequisite_QNAME = new QName("http://nwu.ac.za/wsdl/StudentAcademicRegistration", "validateStudentModulesPrerequisite");
    private final static QName _ValidateStudentModulesPrerequisiteResponse_QNAME = new QName("http://nwu.ac.za/wsdl/StudentAcademicRegistration", "validateStudentModulesPrerequisiteResponse");
    private final static QName _DoesNotExistException_QNAME = new QName("http://nwu.ac.za/wsdl/StudentAcademicRegistration", "DoesNotExistException");
    private final static QName _PermissionDeniedException_QNAME = new QName("http://nwu.ac.za/wsdl/StudentAcademicRegistration", "PermissionDeniedException");
    private final static QName _OperationFailedException_QNAME = new QName("http://nwu.ac.za/wsdl/StudentAcademicRegistration", "OperationFailedException");
    private final static QName _InvalidParameterException_QNAME = new QName("http://nwu.ac.za/wsdl/StudentAcademicRegistration", "InvalidParameterException");
    private final static QName _MissingParameterException_QNAME = new QName("http://nwu.ac.za/wsdl/StudentAcademicRegistration", "MissingParameterException");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: za.ac.nwu.wsdl.studentacademicregistration
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link StudentModuleAcademicInfo }
     * 
     */
    public StudentModuleAcademicInfo createStudentModuleAcademicInfo() {
        return new StudentModuleAcademicInfo();
    }

    /**
     * Create an instance of {@link StudentModuleAcademicInfo.LegacyKeys }
     * 
     */
    public StudentModuleAcademicInfo.LegacyKeys createStudentModuleAcademicInfoLegacyKeys() {
        return new StudentModuleAcademicInfo.LegacyKeys();
    }

    /**
     * Create an instance of {@link StudentAcademicQualificationInfo }
     * 
     */
    public StudentAcademicQualificationInfo createStudentAcademicQualificationInfo() {
        return new StudentAcademicQualificationInfo();
    }

    /**
     * Create an instance of {@link StudentAcademicQualificationInfo.LegacyKeys }
     * 
     */
    public StudentAcademicQualificationInfo.LegacyKeys createStudentAcademicQualificationInfoLegacyKeys() {
        return new StudentAcademicQualificationInfo.LegacyKeys();
    }

    /**
     * Create an instance of {@link GetActiveStudentAcademicRegistrationByModuleOffering }
     * 
     */
    public GetActiveStudentAcademicRegistrationByModuleOffering createGetActiveStudentAcademicRegistrationByModuleOffering() {
        return new GetActiveStudentAcademicRegistrationByModuleOffering();
    }

    /**
     * Create an instance of {@link GetActiveStudentAcademicRegistrationByModuleOfferingResponse }
     * 
     */
    public GetActiveStudentAcademicRegistrationByModuleOfferingResponse createGetActiveStudentAcademicRegistrationByModuleOfferingResponse() {
        return new GetActiveStudentAcademicRegistrationByModuleOfferingResponse();
    }

    /**
     * Create an instance of {@link GetStudentAcademicRegistration }
     * 
     */
    public GetStudentAcademicRegistration createGetStudentAcademicRegistration() {
        return new GetStudentAcademicRegistration();
    }

    /**
     * Create an instance of {@link GetStudentAcademicRegistrationByLang }
     * 
     */
    public GetStudentAcademicRegistrationByLang createGetStudentAcademicRegistrationByLang() {
        return new GetStudentAcademicRegistrationByLang();
    }

    /**
     * Create an instance of {@link GetStudentAcademicRegistrationByLangResponse }
     * 
     */
    public GetStudentAcademicRegistrationByLangResponse createGetStudentAcademicRegistrationByLangResponse() {
        return new GetStudentAcademicRegistrationByLangResponse();
    }

    /**
     * Create an instance of {@link GetStudentAcademicRegistrationByModuleOffering }
     * 
     */
    public GetStudentAcademicRegistrationByModuleOffering createGetStudentAcademicRegistrationByModuleOffering() {
        return new GetStudentAcademicRegistrationByModuleOffering();
    }

    /**
     * Create an instance of {@link GetStudentAcademicRegistrationByModuleOfferingResponse }
     * 
     */
    public GetStudentAcademicRegistrationByModuleOfferingResponse createGetStudentAcademicRegistrationByModuleOfferingResponse() {
        return new GetStudentAcademicRegistrationByModuleOfferingResponse();
    }

    /**
     * Create an instance of {@link GetStudentAcademicRegistrationResponse }
     * 
     */
    public GetStudentAcademicRegistrationResponse createGetStudentAcademicRegistrationResponse() {
        return new GetStudentAcademicRegistrationResponse();
    }

    /**
     * Create an instance of {@link ResetService }
     * 
     */
    public ResetService createResetService() {
        return new ResetService();
    }

    /**
     * Create an instance of {@link ResetServiceResponse }
     * 
     */
    public ResetServiceResponse createResetServiceResponse() {
        return new ResetServiceResponse();
    }

    /**
     * Create an instance of {@link ValidateStudentModulePrerequisite }
     * 
     */
    public ValidateStudentModulePrerequisite createValidateStudentModulePrerequisite() {
        return new ValidateStudentModulePrerequisite();
    }

    /**
     * Create an instance of {@link ValidateStudentModulePrerequisiteResponse }
     * 
     */
    public ValidateStudentModulePrerequisiteResponse createValidateStudentModulePrerequisiteResponse() {
        return new ValidateStudentModulePrerequisiteResponse();
    }

    /**
     * Create an instance of {@link ValidateStudentModulesPrerequisite }
     * 
     */
    public ValidateStudentModulesPrerequisite createValidateStudentModulesPrerequisite() {
        return new ValidateStudentModulesPrerequisite();
    }

    /**
     * Create an instance of {@link ValidateStudentModulesPrerequisiteResponse }
     * 
     */
    public ValidateStudentModulesPrerequisiteResponse createValidateStudentModulesPrerequisiteResponse() {
        return new ValidateStudentModulesPrerequisiteResponse();
    }

    /**
     * Create an instance of {@link DoesNotExistException }
     * 
     */
    public DoesNotExistException createDoesNotExistException() {
        return new DoesNotExistException();
    }

    /**
     * Create an instance of {@link PermissionDeniedException }
     * 
     */
    public PermissionDeniedException createPermissionDeniedException() {
        return new PermissionDeniedException();
    }

    /**
     * Create an instance of {@link OperationFailedException }
     * 
     */
    public OperationFailedException createOperationFailedException() {
        return new OperationFailedException();
    }

    /**
     * Create an instance of {@link InvalidParameterException }
     * 
     */
    public InvalidParameterException createInvalidParameterException() {
        return new InvalidParameterException();
    }

    /**
     * Create an instance of {@link MissingParameterException }
     * 
     */
    public MissingParameterException createMissingParameterException() {
        return new MissingParameterException();
    }

    /**
     * Create an instance of {@link ModuleOfferingSearchCriteriaInfo }
     * 
     */
    public ModuleOfferingSearchCriteriaInfo createModuleOfferingSearchCriteriaInfo() {
        return new ModuleOfferingSearchCriteriaInfo();
    }

    /**
     * Create an instance of {@link AcademicPeriodInfo }
     * 
     */
    public AcademicPeriodInfo createAcademicPeriodInfo() {
        return new AcademicPeriodInfo();
    }

    /**
     * Create an instance of {@link ContextInfo }
     * 
     */
    public ContextInfo createContextInfo() {
        return new ContextInfo();
    }

    /**
     * Create an instance of {@link StudentAcademicRegistrationInfo }
     * 
     */
    public StudentAcademicRegistrationInfo createStudentAcademicRegistrationInfo() {
        return new StudentAcademicRegistrationInfo();
    }

    /**
     * Create an instance of {@link StudentModulePrerequisteResultInfo }
     * 
     */
    public StudentModulePrerequisteResultInfo createStudentModulePrerequisteResultInfo() {
        return new StudentModulePrerequisteResultInfo();
    }

    /**
     * Create an instance of {@link StudentModuleAcademicInfo.LegacyKeys.Entry }
     * 
     */
    public StudentModuleAcademicInfo.LegacyKeys.Entry createStudentModuleAcademicInfoLegacyKeysEntry() {
        return new StudentModuleAcademicInfo.LegacyKeys.Entry();
    }

    /**
     * Create an instance of {@link StudentAcademicQualificationInfo.LegacyKeys.Entry }
     * 
     */
    public StudentAcademicQualificationInfo.LegacyKeys.Entry createStudentAcademicQualificationInfoLegacyKeysEntry() {
        return new StudentAcademicQualificationInfo.LegacyKeys.Entry();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetActiveStudentAcademicRegistrationByModuleOffering }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link GetActiveStudentAcademicRegistrationByModuleOffering }{@code >}
     */
    @XmlElementDecl(namespace = "http://nwu.ac.za/wsdl/StudentAcademicRegistration", name = "getActiveStudentAcademicRegistrationByModuleOffering")
    public JAXBElement<GetActiveStudentAcademicRegistrationByModuleOffering> createGetActiveStudentAcademicRegistrationByModuleOffering(GetActiveStudentAcademicRegistrationByModuleOffering value) {
        return new JAXBElement<GetActiveStudentAcademicRegistrationByModuleOffering>(_GetActiveStudentAcademicRegistrationByModuleOffering_QNAME, GetActiveStudentAcademicRegistrationByModuleOffering.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetActiveStudentAcademicRegistrationByModuleOfferingResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link GetActiveStudentAcademicRegistrationByModuleOfferingResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://nwu.ac.za/wsdl/StudentAcademicRegistration", name = "getActiveStudentAcademicRegistrationByModuleOfferingResponse")
    public JAXBElement<GetActiveStudentAcademicRegistrationByModuleOfferingResponse> createGetActiveStudentAcademicRegistrationByModuleOfferingResponse(GetActiveStudentAcademicRegistrationByModuleOfferingResponse value) {
        return new JAXBElement<GetActiveStudentAcademicRegistrationByModuleOfferingResponse>(_GetActiveStudentAcademicRegistrationByModuleOfferingResponse_QNAME, GetActiveStudentAcademicRegistrationByModuleOfferingResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetStudentAcademicRegistration }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link GetStudentAcademicRegistration }{@code >}
     */
    @XmlElementDecl(namespace = "http://nwu.ac.za/wsdl/StudentAcademicRegistration", name = "getStudentAcademicRegistration")
    public JAXBElement<GetStudentAcademicRegistration> createGetStudentAcademicRegistration(GetStudentAcademicRegistration value) {
        return new JAXBElement<GetStudentAcademicRegistration>(_GetStudentAcademicRegistration_QNAME, GetStudentAcademicRegistration.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetStudentAcademicRegistrationByLang }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link GetStudentAcademicRegistrationByLang }{@code >}
     */
    @XmlElementDecl(namespace = "http://nwu.ac.za/wsdl/StudentAcademicRegistration", name = "getStudentAcademicRegistrationByLang")
    public JAXBElement<GetStudentAcademicRegistrationByLang> createGetStudentAcademicRegistrationByLang(GetStudentAcademicRegistrationByLang value) {
        return new JAXBElement<GetStudentAcademicRegistrationByLang>(_GetStudentAcademicRegistrationByLang_QNAME, GetStudentAcademicRegistrationByLang.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetStudentAcademicRegistrationByLangResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link GetStudentAcademicRegistrationByLangResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://nwu.ac.za/wsdl/StudentAcademicRegistration", name = "getStudentAcademicRegistrationByLangResponse")
    public JAXBElement<GetStudentAcademicRegistrationByLangResponse> createGetStudentAcademicRegistrationByLangResponse(GetStudentAcademicRegistrationByLangResponse value) {
        return new JAXBElement<GetStudentAcademicRegistrationByLangResponse>(_GetStudentAcademicRegistrationByLangResponse_QNAME, GetStudentAcademicRegistrationByLangResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetStudentAcademicRegistrationByModuleOffering }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link GetStudentAcademicRegistrationByModuleOffering }{@code >}
     */
    @XmlElementDecl(namespace = "http://nwu.ac.za/wsdl/StudentAcademicRegistration", name = "getStudentAcademicRegistrationByModuleOffering")
    public JAXBElement<GetStudentAcademicRegistrationByModuleOffering> createGetStudentAcademicRegistrationByModuleOffering(GetStudentAcademicRegistrationByModuleOffering value) {
        return new JAXBElement<GetStudentAcademicRegistrationByModuleOffering>(_GetStudentAcademicRegistrationByModuleOffering_QNAME, GetStudentAcademicRegistrationByModuleOffering.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetStudentAcademicRegistrationByModuleOfferingResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link GetStudentAcademicRegistrationByModuleOfferingResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://nwu.ac.za/wsdl/StudentAcademicRegistration", name = "getStudentAcademicRegistrationByModuleOfferingResponse")
    public JAXBElement<GetStudentAcademicRegistrationByModuleOfferingResponse> createGetStudentAcademicRegistrationByModuleOfferingResponse(GetStudentAcademicRegistrationByModuleOfferingResponse value) {
        return new JAXBElement<GetStudentAcademicRegistrationByModuleOfferingResponse>(_GetStudentAcademicRegistrationByModuleOfferingResponse_QNAME, GetStudentAcademicRegistrationByModuleOfferingResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetStudentAcademicRegistrationResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link GetStudentAcademicRegistrationResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://nwu.ac.za/wsdl/StudentAcademicRegistration", name = "getStudentAcademicRegistrationResponse")
    public JAXBElement<GetStudentAcademicRegistrationResponse> createGetStudentAcademicRegistrationResponse(GetStudentAcademicRegistrationResponse value) {
        return new JAXBElement<GetStudentAcademicRegistrationResponse>(_GetStudentAcademicRegistrationResponse_QNAME, GetStudentAcademicRegistrationResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ResetService }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link ResetService }{@code >}
     */
    @XmlElementDecl(namespace = "http://nwu.ac.za/wsdl/StudentAcademicRegistration", name = "resetService")
    public JAXBElement<ResetService> createResetService(ResetService value) {
        return new JAXBElement<ResetService>(_ResetService_QNAME, ResetService.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ResetServiceResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link ResetServiceResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://nwu.ac.za/wsdl/StudentAcademicRegistration", name = "resetServiceResponse")
    public JAXBElement<ResetServiceResponse> createResetServiceResponse(ResetServiceResponse value) {
        return new JAXBElement<ResetServiceResponse>(_ResetServiceResponse_QNAME, ResetServiceResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ValidateStudentModulePrerequisite }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link ValidateStudentModulePrerequisite }{@code >}
     */
    @XmlElementDecl(namespace = "http://nwu.ac.za/wsdl/StudentAcademicRegistration", name = "validateStudentModulePrerequisite")
    public JAXBElement<ValidateStudentModulePrerequisite> createValidateStudentModulePrerequisite(ValidateStudentModulePrerequisite value) {
        return new JAXBElement<ValidateStudentModulePrerequisite>(_ValidateStudentModulePrerequisite_QNAME, ValidateStudentModulePrerequisite.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ValidateStudentModulePrerequisiteResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link ValidateStudentModulePrerequisiteResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://nwu.ac.za/wsdl/StudentAcademicRegistration", name = "validateStudentModulePrerequisiteResponse")
    public JAXBElement<ValidateStudentModulePrerequisiteResponse> createValidateStudentModulePrerequisiteResponse(ValidateStudentModulePrerequisiteResponse value) {
        return new JAXBElement<ValidateStudentModulePrerequisiteResponse>(_ValidateStudentModulePrerequisiteResponse_QNAME, ValidateStudentModulePrerequisiteResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ValidateStudentModulesPrerequisite }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link ValidateStudentModulesPrerequisite }{@code >}
     */
    @XmlElementDecl(namespace = "http://nwu.ac.za/wsdl/StudentAcademicRegistration", name = "validateStudentModulesPrerequisite")
    public JAXBElement<ValidateStudentModulesPrerequisite> createValidateStudentModulesPrerequisite(ValidateStudentModulesPrerequisite value) {
        return new JAXBElement<ValidateStudentModulesPrerequisite>(_ValidateStudentModulesPrerequisite_QNAME, ValidateStudentModulesPrerequisite.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ValidateStudentModulesPrerequisiteResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link ValidateStudentModulesPrerequisiteResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://nwu.ac.za/wsdl/StudentAcademicRegistration", name = "validateStudentModulesPrerequisiteResponse")
    public JAXBElement<ValidateStudentModulesPrerequisiteResponse> createValidateStudentModulesPrerequisiteResponse(ValidateStudentModulesPrerequisiteResponse value) {
        return new JAXBElement<ValidateStudentModulesPrerequisiteResponse>(_ValidateStudentModulesPrerequisiteResponse_QNAME, ValidateStudentModulesPrerequisiteResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DoesNotExistException }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link DoesNotExistException }{@code >}
     */
    @XmlElementDecl(namespace = "http://nwu.ac.za/wsdl/StudentAcademicRegistration", name = "DoesNotExistException")
    public JAXBElement<DoesNotExistException> createDoesNotExistException(DoesNotExistException value) {
        return new JAXBElement<DoesNotExistException>(_DoesNotExistException_QNAME, DoesNotExistException.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link PermissionDeniedException }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link PermissionDeniedException }{@code >}
     */
    @XmlElementDecl(namespace = "http://nwu.ac.za/wsdl/StudentAcademicRegistration", name = "PermissionDeniedException")
    public JAXBElement<PermissionDeniedException> createPermissionDeniedException(PermissionDeniedException value) {
        return new JAXBElement<PermissionDeniedException>(_PermissionDeniedException_QNAME, PermissionDeniedException.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link OperationFailedException }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link OperationFailedException }{@code >}
     */
    @XmlElementDecl(namespace = "http://nwu.ac.za/wsdl/StudentAcademicRegistration", name = "OperationFailedException")
    public JAXBElement<OperationFailedException> createOperationFailedException(OperationFailedException value) {
        return new JAXBElement<OperationFailedException>(_OperationFailedException_QNAME, OperationFailedException.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link InvalidParameterException }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link InvalidParameterException }{@code >}
     */
    @XmlElementDecl(namespace = "http://nwu.ac.za/wsdl/StudentAcademicRegistration", name = "InvalidParameterException")
    public JAXBElement<InvalidParameterException> createInvalidParameterException(InvalidParameterException value) {
        return new JAXBElement<InvalidParameterException>(_InvalidParameterException_QNAME, InvalidParameterException.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link MissingParameterException }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link MissingParameterException }{@code >}
     */
    @XmlElementDecl(namespace = "http://nwu.ac.za/wsdl/StudentAcademicRegistration", name = "MissingParameterException")
    public JAXBElement<MissingParameterException> createMissingParameterException(MissingParameterException value) {
        return new JAXBElement<MissingParameterException>(_MissingParameterException_QNAME, MissingParameterException.class, null, value);
    }

}
