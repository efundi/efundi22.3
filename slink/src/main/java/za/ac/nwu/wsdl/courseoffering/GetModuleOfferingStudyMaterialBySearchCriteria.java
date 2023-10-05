
package za.ac.nwu.wsdl.courseoffering;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Java class for getModuleOfferingStudyMaterialBySearchCriteria complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="getModuleOfferingStudyMaterialBySearchCriteria"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="moduleOfferingStudyMaterialSearchCriteriaInfo" type="{http://nwu.ac.za/wsdl/CourseOffering}ModuleOfferingStudyMaterialSearchCriteriaInfo"/&gt;
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
@XmlType(name = "getModuleOfferingStudyMaterialBySearchCriteria", propOrder = {
    "moduleOfferingStudyMaterialSearchCriteriaInfo",
    "systemLanguageTypeKey",
    "contextInfo"
})
public class GetModuleOfferingStudyMaterialBySearchCriteria {

    @XmlElement(required = true)
    protected ModuleOfferingStudyMaterialSearchCriteriaInfo moduleOfferingStudyMaterialSearchCriteriaInfo;
    @XmlElement(required = true)
    protected String systemLanguageTypeKey;
    @XmlElement(required = true)
    protected ContextInfo contextInfo;

    /**
     * Gets the value of the moduleOfferingStudyMaterialSearchCriteriaInfo property.
     * 
     * @return
     *     possible object is
     *     {@link ModuleOfferingStudyMaterialSearchCriteriaInfo }
     *     
     */
    public ModuleOfferingStudyMaterialSearchCriteriaInfo getModuleOfferingStudyMaterialSearchCriteriaInfo() {
        return moduleOfferingStudyMaterialSearchCriteriaInfo;
    }

    /**
     * Sets the value of the moduleOfferingStudyMaterialSearchCriteriaInfo property.
     * 
     * @param value
     *     allowed object is
     *     {@link ModuleOfferingStudyMaterialSearchCriteriaInfo }
     *     
     */
    public void setModuleOfferingStudyMaterialSearchCriteriaInfo(ModuleOfferingStudyMaterialSearchCriteriaInfo value) {
        this.moduleOfferingStudyMaterialSearchCriteriaInfo = value;
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
