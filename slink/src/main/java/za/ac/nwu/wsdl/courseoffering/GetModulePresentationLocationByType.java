
package za.ac.nwu.wsdl.courseoffering;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Java class for getModulePresentationLocationByType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="getModulePresentationLocationByType"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="academicPeriod" type="{http://nwu.ac.za/wsdl/CourseOffering}AcademicPeriodInfo" minOccurs="0"/&gt;
 *         &lt;element name="moduleSubjectCode" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="moduleNumber" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="presentationLocationTypeKey" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="systemLanguageTypeKey" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="contextInfo" type="{http://nwu.ac.za/wsdl/CourseOffering}ContextInfo"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "getModulePresentationLocationByType", propOrder = {
    "academicPeriod",
    "moduleSubjectCode",
    "moduleNumber",
    "presentationLocationTypeKey",
    "systemLanguageTypeKey",
    "contextInfo"
})
public class GetModulePresentationLocationByType {

    protected AcademicPeriodInfo academicPeriod;
    @XmlElement(required = true)
    protected String moduleSubjectCode;
    @XmlElement(required = true)
    protected String moduleNumber;
    @XmlElement(required = true)
    protected String presentationLocationTypeKey;
    @XmlElement(required = true)
    protected String systemLanguageTypeKey;
    @XmlElement(required = true)
    protected ContextInfo contextInfo;

    /**
     * Gets the value of the academicPeriod property.
     * 
     * @return
     *     possible object is
     *     {@link AcademicPeriodInfo }
     *     
     */
    public AcademicPeriodInfo getAcademicPeriod() {
        return academicPeriod;
    }

    /**
     * Sets the value of the academicPeriod property.
     * 
     * @param value
     *     allowed object is
     *     {@link AcademicPeriodInfo }
     *     
     */
    public void setAcademicPeriod(AcademicPeriodInfo value) {
        this.academicPeriod = value;
    }

    /**
     * Gets the value of the moduleSubjectCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getModuleSubjectCode() {
        return moduleSubjectCode;
    }

    /**
     * Sets the value of the moduleSubjectCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setModuleSubjectCode(String value) {
        this.moduleSubjectCode = value;
    }

    /**
     * Gets the value of the moduleNumber property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getModuleNumber() {
        return moduleNumber;
    }

    /**
     * Sets the value of the moduleNumber property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setModuleNumber(String value) {
        this.moduleNumber = value;
    }

    /**
     * Gets the value of the presentationLocationTypeKey property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPresentationLocationTypeKey() {
        return presentationLocationTypeKey;
    }

    /**
     * Sets the value of the presentationLocationTypeKey property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPresentationLocationTypeKey(String value) {
        this.presentationLocationTypeKey = value;
    }

    /**
     * Gets the value of the systemLanguageTypeKey property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSystemLanguageTypeKey() {
        return systemLanguageTypeKey;
    }

    /**
     * Sets the value of the systemLanguageTypeKey property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSystemLanguageTypeKey(String value) {
        this.systemLanguageTypeKey = value;
    }

    /**
     * Gets the value of the contextInfo property.
     * 
     * @return
     *     possible object is
     *     {@link ContextInfo }
     *     
     */
    public ContextInfo getContextInfo() {
        return contextInfo;
    }

    /**
     * Sets the value of the contextInfo property.
     * 
     * @param value
     *     allowed object is
     *     {@link ContextInfo }
     *     
     */
    public void setContextInfo(ContextInfo value) {
        this.contextInfo = value;
    }

}
