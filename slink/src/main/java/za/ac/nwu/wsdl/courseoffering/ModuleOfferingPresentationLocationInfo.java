
package za.ac.nwu.wsdl.courseoffering;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ModuleOfferingPresentationLocationInfo complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ModuleOfferingPresentationLocationInfo"&gt;
 *   &lt;complexContent&gt;
 *     &lt;extension base="{http://nwu.ac.za/wsdl/CourseOffering}ModulePresentationLocationInfo"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="moduleSite" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="moduleOrgEnt" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="methodOfDeliveryTypeKey" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="modeOfDeliveryTypeKey" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/extension&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ModuleOfferingPresentationLocationInfo", propOrder = {
    "moduleSite",
    "moduleOrgEnt",
    "methodOfDeliveryTypeKey",
    "modeOfDeliveryTypeKey"
})
public class ModuleOfferingPresentationLocationInfo
    extends ModulePresentationLocationInfo
{

    protected String moduleSite;
    protected String moduleOrgEnt;
    protected String methodOfDeliveryTypeKey;
    protected String modeOfDeliveryTypeKey;

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

}
