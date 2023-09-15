
package za.ac.nwu.wsdl.studentacademicregistration;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Java class for AcademicPeriodInfo complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="AcademicPeriodInfo"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="acadPeriodtTypeKey" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="acadPeriodValue" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "AcademicPeriodInfo", propOrder = {
    "acadPeriodtTypeKey",
    "acadPeriodValue"
})
public class AcademicPeriodInfo {

    @XmlElement(required = true)
    protected String acadPeriodtTypeKey;
    @XmlElement(required = true)
    protected String acadPeriodValue;

    /**
     * Gets the value of the acadPeriodtTypeKey property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAcadPeriodtTypeKey() {
        return acadPeriodtTypeKey;
    }

    /**
     * Sets the value of the acadPeriodtTypeKey property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAcadPeriodtTypeKey(String value) {
        this.acadPeriodtTypeKey = value;
    }

    /**
     * Gets the value of the acadPeriodValue property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAcadPeriodValue() {
        return acadPeriodValue;
    }

    /**
     * Sets the value of the acadPeriodValue property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAcadPeriodValue(String value) {
        this.acadPeriodValue = value;
    }

}
