
package za.ac.nwu.wsdl.studentacademicregistration;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Java class for getActiveStudentAcademicRegistrationByModuleOffering complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="getActiveStudentAcademicRegistrationByModuleOffering"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="moduleOfferingSearchCriteriaInfo" type="{http://nwu.ac.za/wsdl/StudentAcademicRegistration}ModuleOfferingSearchCriteriaInfo"/&gt;
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
@XmlType(name = "getActiveStudentAcademicRegistrationByModuleOffering", propOrder = {
    "moduleOfferingSearchCriteriaInfo",
    "contextInfo"
})
public class GetActiveStudentAcademicRegistrationByModuleOffering {

    @XmlElement(required = true)
    protected ModuleOfferingSearchCriteriaInfo moduleOfferingSearchCriteriaInfo;
    protected ContextInfo contextInfo;

    /**
     * Gets the value of the moduleOfferingSearchCriteriaInfo property.
     * 
     * @return
     *     possible object is
     *     {@link ModuleOfferingSearchCriteriaInfo }
     *     
     */
    public ModuleOfferingSearchCriteriaInfo getModuleOfferingSearchCriteriaInfo() {
        return moduleOfferingSearchCriteriaInfo;
    }

    /**
     * Sets the value of the moduleOfferingSearchCriteriaInfo property.
     * 
     * @param value
     *     allowed object is
     *     {@link ModuleOfferingSearchCriteriaInfo }
     *     
     */
    public void setModuleOfferingSearchCriteriaInfo(ModuleOfferingSearchCriteriaInfo value) {
        this.moduleOfferingSearchCriteriaInfo = value;
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
