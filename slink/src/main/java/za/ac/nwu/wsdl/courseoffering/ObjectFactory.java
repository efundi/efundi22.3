
package za.ac.nwu.wsdl.courseoffering;

import javax.xml.namespace.QName;
import jakarta.xml.bind.JAXBElement;
import jakarta.xml.bind.annotation.XmlElementDecl;
import jakarta.xml.bind.annotation.XmlRegistry;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the za.ac.nwu.wsdl.courseoffering package. 
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

    private final static QName _GetModuleOfferingBySearchCriteria_QNAME = new QName("http://nwu.ac.za/wsdl/CourseOffering", "getModuleOfferingBySearchCriteria");
    private final static QName _GetModuleOfferingBySearchCriteriaResponse_QNAME = new QName("http://nwu.ac.za/wsdl/CourseOffering", "getModuleOfferingBySearchCriteriaResponse");
    private final static QName _GetModuleOfferingFee_QNAME = new QName("http://nwu.ac.za/wsdl/CourseOffering", "getModuleOfferingFee");
    private final static QName _GetModuleOfferingFeeResponse_QNAME = new QName("http://nwu.ac.za/wsdl/CourseOffering", "getModuleOfferingFeeResponse");
    private final static QName _GetModuleOfferingPresentationLocation_QNAME = new QName("http://nwu.ac.za/wsdl/CourseOffering", "getModuleOfferingPresentationLocation");
    private final static QName _GetModuleOfferingPresentationLocationResponse_QNAME = new QName("http://nwu.ac.za/wsdl/CourseOffering", "getModuleOfferingPresentationLocationResponse");
    private final static QName _GetModuleOfferingStudyMaterialBySearchCriteria_QNAME = new QName("http://nwu.ac.za/wsdl/CourseOffering", "getModuleOfferingStudyMaterialBySearchCriteria");
    private final static QName _GetModuleOfferingStudyMaterialBySearchCriteriaResponse_QNAME = new QName("http://nwu.ac.za/wsdl/CourseOffering", "getModuleOfferingStudyMaterialBySearchCriteriaResponse");
    private final static QName _GetModulePresentationLocationByType_QNAME = new QName("http://nwu.ac.za/wsdl/CourseOffering", "getModulePresentationLocationByType");
    private final static QName _GetModulePresentationLocationByTypeResponse_QNAME = new QName("http://nwu.ac.za/wsdl/CourseOffering", "getModulePresentationLocationByTypeResponse");
    private final static QName _ResetService_QNAME = new QName("http://nwu.ac.za/wsdl/CourseOffering", "resetService");
    private final static QName _ResetServiceResponse_QNAME = new QName("http://nwu.ac.za/wsdl/CourseOffering", "resetServiceResponse");
    private final static QName _OperationFailedException_QNAME = new QName("http://nwu.ac.za/wsdl/CourseOffering", "OperationFailedException");
    private final static QName _InvalidParameterException_QNAME = new QName("http://nwu.ac.za/wsdl/CourseOffering", "InvalidParameterException");
    private final static QName _MissingParameterException_QNAME = new QName("http://nwu.ac.za/wsdl/CourseOffering", "MissingParameterException");
    private final static QName _DoesNotExistException_QNAME = new QName("http://nwu.ac.za/wsdl/CourseOffering", "DoesNotExistException");
    private final static QName _PermissionDeniedException_QNAME = new QName("http://nwu.ac.za/wsdl/CourseOffering", "PermissionDeniedException");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: za.ac.nwu.wsdl.courseoffering
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link StudentModuleOfferingStudyMaterialInfo }
     * 
     */
    public StudentModuleOfferingStudyMaterialInfo createStudentModuleOfferingStudyMaterialInfo() {
        return new StudentModuleOfferingStudyMaterialInfo();
    }

    /**
     * Create an instance of {@link StudentModuleOfferingStudyMaterialInfo.LegacyKeys }
     * 
     */
    public StudentModuleOfferingStudyMaterialInfo.LegacyKeys createStudentModuleOfferingStudyMaterialInfoLegacyKeys() {
        return new StudentModuleOfferingStudyMaterialInfo.LegacyKeys();
    }

    /**
     * Create an instance of {@link ModuleOfferingFeeInfo }
     * 
     */
    public ModuleOfferingFeeInfo createModuleOfferingFeeInfo() {
        return new ModuleOfferingFeeInfo();
    }

    /**
     * Create an instance of {@link ModuleOfferingFeeInfo.LegacyKeys }
     * 
     */
    public ModuleOfferingFeeInfo.LegacyKeys createModuleOfferingFeeInfoLegacyKeys() {
        return new ModuleOfferingFeeInfo.LegacyKeys();
    }

    /**
     * Create an instance of {@link ModulePresentationLocationInfo }
     * 
     */
    public ModulePresentationLocationInfo createModulePresentationLocationInfo() {
        return new ModulePresentationLocationInfo();
    }

    /**
     * Create an instance of {@link ModulePresentationLocationInfo.LegacyKeys }
     * 
     */
    public ModulePresentationLocationInfo.LegacyKeys createModulePresentationLocationInfoLegacyKeys() {
        return new ModulePresentationLocationInfo.LegacyKeys();
    }

    /**
     * Create an instance of {@link ModuleOfferingInfo }
     * 
     */
    public ModuleOfferingInfo createModuleOfferingInfo() {
        return new ModuleOfferingInfo();
    }

    /**
     * Create an instance of {@link ModuleOfferingInfo.LegacyKeys }
     * 
     */
    public ModuleOfferingInfo.LegacyKeys createModuleOfferingInfoLegacyKeys() {
        return new ModuleOfferingInfo.LegacyKeys();
    }

    /**
     * Create an instance of {@link GetModuleOfferingBySearchCriteria }
     * 
     */
    public GetModuleOfferingBySearchCriteria createGetModuleOfferingBySearchCriteria() {
        return new GetModuleOfferingBySearchCriteria();
    }

    /**
     * Create an instance of {@link GetModuleOfferingBySearchCriteriaResponse }
     * 
     */
    public GetModuleOfferingBySearchCriteriaResponse createGetModuleOfferingBySearchCriteriaResponse() {
        return new GetModuleOfferingBySearchCriteriaResponse();
    }

    /**
     * Create an instance of {@link GetModuleOfferingFee }
     * 
     */
    public GetModuleOfferingFee createGetModuleOfferingFee() {
        return new GetModuleOfferingFee();
    }

    /**
     * Create an instance of {@link GetModuleOfferingFeeResponse }
     * 
     */
    public GetModuleOfferingFeeResponse createGetModuleOfferingFeeResponse() {
        return new GetModuleOfferingFeeResponse();
    }

    /**
     * Create an instance of {@link GetModuleOfferingPresentationLocation }
     * 
     */
    public GetModuleOfferingPresentationLocation createGetModuleOfferingPresentationLocation() {
        return new GetModuleOfferingPresentationLocation();
    }

    /**
     * Create an instance of {@link GetModuleOfferingPresentationLocationResponse }
     * 
     */
    public GetModuleOfferingPresentationLocationResponse createGetModuleOfferingPresentationLocationResponse() {
        return new GetModuleOfferingPresentationLocationResponse();
    }

    /**
     * Create an instance of {@link GetModuleOfferingStudyMaterialBySearchCriteria }
     * 
     */
    public GetModuleOfferingStudyMaterialBySearchCriteria createGetModuleOfferingStudyMaterialBySearchCriteria() {
        return new GetModuleOfferingStudyMaterialBySearchCriteria();
    }

    /**
     * Create an instance of {@link GetModuleOfferingStudyMaterialBySearchCriteriaResponse }
     * 
     */
    public GetModuleOfferingStudyMaterialBySearchCriteriaResponse createGetModuleOfferingStudyMaterialBySearchCriteriaResponse() {
        return new GetModuleOfferingStudyMaterialBySearchCriteriaResponse();
    }

    /**
     * Create an instance of {@link GetModulePresentationLocationByType }
     * 
     */
    public GetModulePresentationLocationByType createGetModulePresentationLocationByType() {
        return new GetModulePresentationLocationByType();
    }

    /**
     * Create an instance of {@link GetModulePresentationLocationByTypeResponse }
     * 
     */
    public GetModulePresentationLocationByTypeResponse createGetModulePresentationLocationByTypeResponse() {
        return new GetModulePresentationLocationByTypeResponse();
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
     * Create an instance of {@link ContextInfo }
     * 
     */
    public ContextInfo createContextInfo() {
        return new ContextInfo();
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
     * Create an instance of {@link MetaInfo }
     * 
     */
    public MetaInfo createMetaInfo() {
        return new MetaInfo();
    }

    /**
     * Create an instance of {@link ModuleOfferingPresentationLocationInfo }
     * 
     */
    public ModuleOfferingPresentationLocationInfo createModuleOfferingPresentationLocationInfo() {
        return new ModuleOfferingPresentationLocationInfo();
    }

    /**
     * Create an instance of {@link ModuleOfferingStudyMaterialSearchCriteriaInfo }
     * 
     */
    public ModuleOfferingStudyMaterialSearchCriteriaInfo createModuleOfferingStudyMaterialSearchCriteriaInfo() {
        return new ModuleOfferingStudyMaterialSearchCriteriaInfo();
    }

    /**
     * Create an instance of {@link StudentModuleOfferingStudyMaterialInfo.LegacyKeys.Entry }
     * 
     */
    public StudentModuleOfferingStudyMaterialInfo.LegacyKeys.Entry createStudentModuleOfferingStudyMaterialInfoLegacyKeysEntry() {
        return new StudentModuleOfferingStudyMaterialInfo.LegacyKeys.Entry();
    }

    /**
     * Create an instance of {@link ModuleOfferingFeeInfo.LegacyKeys.Entry }
     * 
     */
    public ModuleOfferingFeeInfo.LegacyKeys.Entry createModuleOfferingFeeInfoLegacyKeysEntry() {
        return new ModuleOfferingFeeInfo.LegacyKeys.Entry();
    }

    /**
     * Create an instance of {@link ModulePresentationLocationInfo.LegacyKeys.Entry }
     * 
     */
    public ModulePresentationLocationInfo.LegacyKeys.Entry createModulePresentationLocationInfoLegacyKeysEntry() {
        return new ModulePresentationLocationInfo.LegacyKeys.Entry();
    }

    /**
     * Create an instance of {@link ModuleOfferingInfo.LegacyKeys.Entry }
     * 
     */
    public ModuleOfferingInfo.LegacyKeys.Entry createModuleOfferingInfoLegacyKeysEntry() {
        return new ModuleOfferingInfo.LegacyKeys.Entry();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetModuleOfferingBySearchCriteria }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link GetModuleOfferingBySearchCriteria }{@code >}
     */
    @XmlElementDecl(namespace = "http://nwu.ac.za/wsdl/CourseOffering", name = "getModuleOfferingBySearchCriteria")
    public JAXBElement<GetModuleOfferingBySearchCriteria> createGetModuleOfferingBySearchCriteria(GetModuleOfferingBySearchCriteria value) {
        return new JAXBElement<GetModuleOfferingBySearchCriteria>(_GetModuleOfferingBySearchCriteria_QNAME, GetModuleOfferingBySearchCriteria.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetModuleOfferingBySearchCriteriaResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link GetModuleOfferingBySearchCriteriaResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://nwu.ac.za/wsdl/CourseOffering", name = "getModuleOfferingBySearchCriteriaResponse")
    public JAXBElement<GetModuleOfferingBySearchCriteriaResponse> createGetModuleOfferingBySearchCriteriaResponse(GetModuleOfferingBySearchCriteriaResponse value) {
        return new JAXBElement<GetModuleOfferingBySearchCriteriaResponse>(_GetModuleOfferingBySearchCriteriaResponse_QNAME, GetModuleOfferingBySearchCriteriaResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetModuleOfferingFee }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link GetModuleOfferingFee }{@code >}
     */
    @XmlElementDecl(namespace = "http://nwu.ac.za/wsdl/CourseOffering", name = "getModuleOfferingFee")
    public JAXBElement<GetModuleOfferingFee> createGetModuleOfferingFee(GetModuleOfferingFee value) {
        return new JAXBElement<GetModuleOfferingFee>(_GetModuleOfferingFee_QNAME, GetModuleOfferingFee.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetModuleOfferingFeeResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link GetModuleOfferingFeeResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://nwu.ac.za/wsdl/CourseOffering", name = "getModuleOfferingFeeResponse")
    public JAXBElement<GetModuleOfferingFeeResponse> createGetModuleOfferingFeeResponse(GetModuleOfferingFeeResponse value) {
        return new JAXBElement<GetModuleOfferingFeeResponse>(_GetModuleOfferingFeeResponse_QNAME, GetModuleOfferingFeeResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetModuleOfferingPresentationLocation }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link GetModuleOfferingPresentationLocation }{@code >}
     */
    @XmlElementDecl(namespace = "http://nwu.ac.za/wsdl/CourseOffering", name = "getModuleOfferingPresentationLocation")
    public JAXBElement<GetModuleOfferingPresentationLocation> createGetModuleOfferingPresentationLocation(GetModuleOfferingPresentationLocation value) {
        return new JAXBElement<GetModuleOfferingPresentationLocation>(_GetModuleOfferingPresentationLocation_QNAME, GetModuleOfferingPresentationLocation.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetModuleOfferingPresentationLocationResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link GetModuleOfferingPresentationLocationResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://nwu.ac.za/wsdl/CourseOffering", name = "getModuleOfferingPresentationLocationResponse")
    public JAXBElement<GetModuleOfferingPresentationLocationResponse> createGetModuleOfferingPresentationLocationResponse(GetModuleOfferingPresentationLocationResponse value) {
        return new JAXBElement<GetModuleOfferingPresentationLocationResponse>(_GetModuleOfferingPresentationLocationResponse_QNAME, GetModuleOfferingPresentationLocationResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetModuleOfferingStudyMaterialBySearchCriteria }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link GetModuleOfferingStudyMaterialBySearchCriteria }{@code >}
     */
    @XmlElementDecl(namespace = "http://nwu.ac.za/wsdl/CourseOffering", name = "getModuleOfferingStudyMaterialBySearchCriteria")
    public JAXBElement<GetModuleOfferingStudyMaterialBySearchCriteria> createGetModuleOfferingStudyMaterialBySearchCriteria(GetModuleOfferingStudyMaterialBySearchCriteria value) {
        return new JAXBElement<GetModuleOfferingStudyMaterialBySearchCriteria>(_GetModuleOfferingStudyMaterialBySearchCriteria_QNAME, GetModuleOfferingStudyMaterialBySearchCriteria.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetModuleOfferingStudyMaterialBySearchCriteriaResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link GetModuleOfferingStudyMaterialBySearchCriteriaResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://nwu.ac.za/wsdl/CourseOffering", name = "getModuleOfferingStudyMaterialBySearchCriteriaResponse")
    public JAXBElement<GetModuleOfferingStudyMaterialBySearchCriteriaResponse> createGetModuleOfferingStudyMaterialBySearchCriteriaResponse(GetModuleOfferingStudyMaterialBySearchCriteriaResponse value) {
        return new JAXBElement<GetModuleOfferingStudyMaterialBySearchCriteriaResponse>(_GetModuleOfferingStudyMaterialBySearchCriteriaResponse_QNAME, GetModuleOfferingStudyMaterialBySearchCriteriaResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetModulePresentationLocationByType }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link GetModulePresentationLocationByType }{@code >}
     */
    @XmlElementDecl(namespace = "http://nwu.ac.za/wsdl/CourseOffering", name = "getModulePresentationLocationByType")
    public JAXBElement<GetModulePresentationLocationByType> createGetModulePresentationLocationByType(GetModulePresentationLocationByType value) {
        return new JAXBElement<GetModulePresentationLocationByType>(_GetModulePresentationLocationByType_QNAME, GetModulePresentationLocationByType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetModulePresentationLocationByTypeResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link GetModulePresentationLocationByTypeResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://nwu.ac.za/wsdl/CourseOffering", name = "getModulePresentationLocationByTypeResponse")
    public JAXBElement<GetModulePresentationLocationByTypeResponse> createGetModulePresentationLocationByTypeResponse(GetModulePresentationLocationByTypeResponse value) {
        return new JAXBElement<GetModulePresentationLocationByTypeResponse>(_GetModulePresentationLocationByTypeResponse_QNAME, GetModulePresentationLocationByTypeResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ResetService }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link ResetService }{@code >}
     */
    @XmlElementDecl(namespace = "http://nwu.ac.za/wsdl/CourseOffering", name = "resetService")
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
    @XmlElementDecl(namespace = "http://nwu.ac.za/wsdl/CourseOffering", name = "resetServiceResponse")
    public JAXBElement<ResetServiceResponse> createResetServiceResponse(ResetServiceResponse value) {
        return new JAXBElement<ResetServiceResponse>(_ResetServiceResponse_QNAME, ResetServiceResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link OperationFailedException }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link OperationFailedException }{@code >}
     */
    @XmlElementDecl(namespace = "http://nwu.ac.za/wsdl/CourseOffering", name = "OperationFailedException")
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
    @XmlElementDecl(namespace = "http://nwu.ac.za/wsdl/CourseOffering", name = "InvalidParameterException")
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
    @XmlElementDecl(namespace = "http://nwu.ac.za/wsdl/CourseOffering", name = "MissingParameterException")
    public JAXBElement<MissingParameterException> createMissingParameterException(MissingParameterException value) {
        return new JAXBElement<MissingParameterException>(_MissingParameterException_QNAME, MissingParameterException.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DoesNotExistException }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link DoesNotExistException }{@code >}
     */
    @XmlElementDecl(namespace = "http://nwu.ac.za/wsdl/CourseOffering", name = "DoesNotExistException")
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
    @XmlElementDecl(namespace = "http://nwu.ac.za/wsdl/CourseOffering", name = "PermissionDeniedException")
    public JAXBElement<PermissionDeniedException> createPermissionDeniedException(PermissionDeniedException value) {
        return new JAXBElement<PermissionDeniedException>(_PermissionDeniedException_QNAME, PermissionDeniedException.class, null, value);
    }

}
