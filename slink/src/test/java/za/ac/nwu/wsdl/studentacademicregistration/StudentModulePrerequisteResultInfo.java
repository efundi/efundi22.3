
package za.ac.nwu.wsdl.studentacademicregistration;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Java class for studentModulePrerequisteResultInfo complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="studentModulePrerequisteResultInfo"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="prereqPassed" type="{http://www.w3.org/2001/XMLSchema}boolean"/&gt;
 *         &lt;element name="prereqResultMessage" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="studentModuleAcademic" type="{http://nwu.ac.za/wsdl/StudentAcademicRegistration}studentModuleAcademicInfo" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "studentModulePrerequisteResultInfo", propOrder = {
    "prereqPassed",
    "prereqResultMessage",
    "studentModuleAcademic"
})
public class StudentModulePrerequisteResultInfo {

    protected boolean prereqPassed;
    protected String prereqResultMessage;
    protected StudentModuleAcademicInfo studentModuleAcademic;

    /**
     * Gets the value of the prereqPassed property.
     * 
     */
    public boolean isPrereqPassed() {
        return prereqPassed;
    }

    /**
     * Sets the value of the prereqPassed property.
     * 
     */
    public void setPrereqPassed(boolean value) {
        this.prereqPassed = value;
    }

    /**
     * Gets the value of the prereqResultMessage property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPrereqResultMessage() {
        return prereqResultMessage;
    }

    /**
     * Sets the value of the prereqResultMessage property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPrereqResultMessage(String value) {
        this.prereqResultMessage = value;
    }

    /**
     * Gets the value of the studentModuleAcademic property.
     * 
     * @return
     *     possible object is
     *     {@link StudentModuleAcademicInfo }
     *     
     */
    public StudentModuleAcademicInfo getStudentModuleAcademic() {
        return studentModuleAcademic;
    }

    /**
     * Sets the value of the studentModuleAcademic property.
     * 
     * @param value
     *     allowed object is
     *     {@link StudentModuleAcademicInfo }
     *     
     */
    public void setStudentModuleAcademic(StudentModuleAcademicInfo value) {
        this.studentModuleAcademic = value;
    }

}
