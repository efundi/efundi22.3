
package za.ac.nwu.wsdl.studentacademicregistration;

import java.util.ArrayList;
import java.util.List;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Java class for validateStudentModulesPrerequisite complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="validateStudentModulesPrerequisite"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="studentModuleAcadInfos" type="{http://nwu.ac.za/wsdl/StudentAcademicRegistration}studentModuleAcademicInfo" maxOccurs="unbounded"/&gt;
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
@XmlType(name = "validateStudentModulesPrerequisite", propOrder = {
    "studentModuleAcadInfos",
    "systemLanguageTypeKey",
    "contextInfo"
})
public class ValidateStudentModulesPrerequisite {

    @XmlElement(required = true)
    protected List<StudentModuleAcademicInfo> studentModuleAcadInfos;
    @XmlElement(required = true)
    protected String systemLanguageTypeKey;
    protected ContextInfo contextInfo;

    /**
     * Gets the value of the studentModuleAcadInfos property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the Jakarta XML Binding object.
     * This is why there is not a <CODE>set</CODE> method for the studentModuleAcadInfos property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getStudentModuleAcadInfos().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link StudentModuleAcademicInfo }
     * 
     * 
     */
    public List<StudentModuleAcademicInfo> getStudentModuleAcadInfos() {
        if (studentModuleAcadInfos == null) {
            studentModuleAcadInfos = new ArrayList<StudentModuleAcademicInfo>();
        }
        return this.studentModuleAcadInfos;
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
