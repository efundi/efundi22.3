
package za.ac.nwu.wsdl.studentacademicregistration;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Java class for getStudentAcademicRegistrationByLang complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="getStudentAcademicRegistrationByLang"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="univNumber" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="academicPeriodInfo" type="{http://nwu.ac.za/wsdl/StudentAcademicRegistration}AcademicPeriodInfo" minOccurs="0"/&gt;
 *         &lt;element name="systemLanguageTypeKey" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="contextInfo" type="{http://nwu.ac.za/wsdl/StudentAcademicRegistration}ContextInfo" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "getStudentAcademicRegistrationByLang", propOrder = {
    "univNumber",
    "academicPeriodInfo",
    "systemLanguageTypeKey",
    "contextInfo"
})
public class GetStudentAcademicRegistrationByLang {

    @XmlElement(required = true)
    protected String univNumber;
    protected AcademicPeriodInfo academicPeriodInfo;
    @XmlElement(required = true)
    protected String systemLanguageTypeKey;
    protected ContextInfo contextInfo;

    /**
     * Gets the value of the univNumber property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUnivNumber() {
        return univNumber;
    }

    /**
     * Sets the value of the univNumber property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUnivNumber(String value) {
        this.univNumber = value;
    }

    /**
     * Gets the value of the academicPeriodInfo property.
     * 
     * @return
     *     possible object is
     *     {@link AcademicPeriodInfo }
     *     
     */
    public AcademicPeriodInfo getAcademicPeriodInfo() {
        return academicPeriodInfo;
    }

    /**
     * Sets the value of the academicPeriodInfo property.
     * 
     * @param value
     *     allowed object is
     *     {@link AcademicPeriodInfo }
     *     
     */
    public void setAcademicPeriodInfo(AcademicPeriodInfo value) {
        this.academicPeriodInfo = value;
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
