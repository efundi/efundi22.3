
package za.ac.nwu.wsdl.studentacademicregistration;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Java class for DoesNotExistException complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="DoesNotExistException"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="functionalMsgValue" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="techMsgValue" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="functionalMsgKey" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="techMsgKey" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="message" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DoesNotExistException", propOrder = {
    "functionalMsgValue",
    "techMsgValue",
    "functionalMsgKey",
    "techMsgKey",
    "message"
})
public class DoesNotExistException {

    protected String functionalMsgValue;
    protected String techMsgValue;
    protected String functionalMsgKey;
    protected String techMsgKey;
    protected String message;

    /**
     * Gets the value of the functionalMsgValue property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFunctionalMsgValue() {
        return functionalMsgValue;
    }

    /**
     * Sets the value of the functionalMsgValue property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFunctionalMsgValue(String value) {
        this.functionalMsgValue = value;
    }

    /**
     * Gets the value of the techMsgValue property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTechMsgValue() {
        return techMsgValue;
    }

    /**
     * Sets the value of the techMsgValue property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTechMsgValue(String value) {
        this.techMsgValue = value;
    }

    /**
     * Gets the value of the functionalMsgKey property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFunctionalMsgKey() {
        return functionalMsgKey;
    }

    /**
     * Sets the value of the functionalMsgKey property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFunctionalMsgKey(String value) {
        this.functionalMsgKey = value;
    }

    /**
     * Gets the value of the techMsgKey property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTechMsgKey() {
        return techMsgKey;
    }

    /**
     * Sets the value of the techMsgKey property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTechMsgKey(String value) {
        this.techMsgKey = value;
    }

    /**
     * Gets the value of the message property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMessage() {
        return message;
    }

    /**
     * Sets the value of the message property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMessage(String value) {
        this.message = value;
    }

}
