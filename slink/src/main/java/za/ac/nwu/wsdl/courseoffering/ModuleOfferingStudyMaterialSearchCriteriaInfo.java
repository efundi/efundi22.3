
package za.ac.nwu.wsdl.courseoffering;

import javax.xml.datatype.XMLGregorianCalendar;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlSchemaType;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ModuleOfferingStudyMaterialSearchCriteriaInfo complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ModuleOfferingStudyMaterialSearchCriteriaInfo"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="academicPeriod" type="{http://nwu.ac.za/wsdl/CourseOffering}AcademicPeriodInfo"/&gt;
 *         &lt;element name="moduleSubjectCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="moduleNumber" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="moduleSite" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="moduleOrgEnt" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="methodOfDeliveryTypeKey" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="modeOfDeliveryTypeKey" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="startDate" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/&gt;
 *         &lt;element name="endDate" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ModuleOfferingStudyMaterialSearchCriteriaInfo", propOrder = {
    "academicPeriod",
    "moduleSubjectCode",
    "moduleNumber",
    "moduleSite",
    "moduleOrgEnt",
    "methodOfDeliveryTypeKey",
    "modeOfDeliveryTypeKey",
    "startDate",
    "endDate"
})
public class ModuleOfferingStudyMaterialSearchCriteriaInfo {

    @XmlElement(required = true)
    protected AcademicPeriodInfo academicPeriod;
    protected String moduleSubjectCode;
    protected String moduleNumber;
    protected String moduleSite;
    protected String moduleOrgEnt;
    protected String methodOfDeliveryTypeKey;
    protected String modeOfDeliveryTypeKey;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar startDate;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar endDate;

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
     * Gets the value of the moduleSite property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getModuleSite() {
        return moduleSite;
    }

    /**
     * Sets the value of the moduleSite property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setModuleSite(String value) {
        this.moduleSite = value;
    }

    /**
     * Gets the value of the moduleOrgEnt property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getModuleOrgEnt() {
        return moduleOrgEnt;
    }

    /**
     * Sets the value of the moduleOrgEnt property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setModuleOrgEnt(String value) {
        this.moduleOrgEnt = value;
    }

    /**
     * Gets the value of the methodOfDeliveryTypeKey property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMethodOfDeliveryTypeKey() {
        return methodOfDeliveryTypeKey;
    }

    /**
     * Sets the value of the methodOfDeliveryTypeKey property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMethodOfDeliveryTypeKey(String value) {
        this.methodOfDeliveryTypeKey = value;
    }

    /**
     * Gets the value of the modeOfDeliveryTypeKey property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getModeOfDeliveryTypeKey() {
        return modeOfDeliveryTypeKey;
    }

    /**
     * Sets the value of the modeOfDeliveryTypeKey property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setModeOfDeliveryTypeKey(String value) {
        this.modeOfDeliveryTypeKey = value;
    }

    /**
     * Gets the value of the startDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getStartDate() {
        return startDate;
    }

    /**
     * Sets the value of the startDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setStartDate(XMLGregorianCalendar value) {
        this.startDate = value;
    }

    /**
     * Gets the value of the endDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getEndDate() {
        return endDate;
    }

    /**
     * Sets the value of the endDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setEndDate(XMLGregorianCalendar value) {
        this.endDate = value;
    }

}
