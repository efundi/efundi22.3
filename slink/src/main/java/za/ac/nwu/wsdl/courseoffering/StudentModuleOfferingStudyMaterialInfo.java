
package za.ac.nwu.wsdl.courseoffering;

import java.util.ArrayList;
import java.util.List;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Java class for StudentModuleOfferingStudyMaterialInfo complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="StudentModuleOfferingStudyMaterialInfo"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="academicPeriod" type="{http://nwu.ac.za/wsdl/CourseOffering}AcademicPeriodInfo" minOccurs="0"/&gt;
 *         &lt;element name="moduleSubjectCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="moduleNumber" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="moduleSite" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="moduleOrgEnt" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="methodOfDeliveryTypeKey" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="modeOfDeliveryTypeKey" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="startDate" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="endDate" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="metaInfo" type="{http://nwu.ac.za/wsdl/CourseOffering}MetaInfo" minOccurs="0"/&gt;
 *         &lt;element name="itemCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="studyMaterialLanguageTypeKey" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="studyMaterialDescription" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="studyMaterialIsOptional" type="{http://www.w3.org/2001/XMLSchema}boolean"/&gt;
 *         &lt;element name="studyMaterialEnabled" type="{http://www.w3.org/2001/XMLSchema}boolean"/&gt;
 *         &lt;element name="legacyKeys"&gt;
 *           &lt;complexType&gt;
 *             &lt;complexContent&gt;
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                 &lt;sequence&gt;
 *                   &lt;element name="entry" maxOccurs="unbounded" minOccurs="0"&gt;
 *                     &lt;complexType&gt;
 *                       &lt;complexContent&gt;
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                           &lt;sequence&gt;
 *                             &lt;element name="key" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *                             &lt;element name="value" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/&gt;
 *                           &lt;/sequence&gt;
 *                         &lt;/restriction&gt;
 *                       &lt;/complexContent&gt;
 *                     &lt;/complexType&gt;
 *                   &lt;/element&gt;
 *                 &lt;/sequence&gt;
 *               &lt;/restriction&gt;
 *             &lt;/complexContent&gt;
 *           &lt;/complexType&gt;
 *         &lt;/element&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "StudentModuleOfferingStudyMaterialInfo", propOrder = {
    "academicPeriod",
    "moduleSubjectCode",
    "moduleNumber",
    "moduleSite",
    "moduleOrgEnt",
    "methodOfDeliveryTypeKey",
    "modeOfDeliveryTypeKey",
    "startDate",
    "endDate",
    "metaInfo",
    "itemCode",
    "studyMaterialLanguageTypeKey",
    "studyMaterialDescription",
    "studyMaterialIsOptional",
    "studyMaterialEnabled",
    "legacyKeys"
})
public class StudentModuleOfferingStudyMaterialInfo {

    protected AcademicPeriodInfo academicPeriod;
    protected String moduleSubjectCode;
    protected String moduleNumber;
    protected String moduleSite;
    protected String moduleOrgEnt;
    protected String methodOfDeliveryTypeKey;
    protected String modeOfDeliveryTypeKey;
    protected String startDate;
    protected String endDate;
    protected MetaInfo metaInfo;
    protected String itemCode;
    protected String studyMaterialLanguageTypeKey;
    protected String studyMaterialDescription;
    protected boolean studyMaterialIsOptional;
    protected boolean studyMaterialEnabled;
    @XmlElement(required = true)
    protected StudentModuleOfferingStudyMaterialInfo.LegacyKeys legacyKeys;

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
     *     {@link String }
     *     
     */
    public String getStartDate() {
        return startDate;
    }

    /**
     * Sets the value of the startDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStartDate(String value) {
        this.startDate = value;
    }

    /**
     * Gets the value of the endDate property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEndDate() {
        return endDate;
    }

    /**
     * Sets the value of the endDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEndDate(String value) {
        this.endDate = value;
    }

    /**
     * Gets the value of the metaInfo property.
     * 
     * @return
     *     possible object is
     *     {@link MetaInfo }
     *     
     */
    public MetaInfo getMetaInfo() {
        return metaInfo;
    }

    /**
     * Sets the value of the metaInfo property.
     * 
     * @param value
     *     allowed object is
     *     {@link MetaInfo }
     *     
     */
    public void setMetaInfo(MetaInfo value) {
        this.metaInfo = value;
    }

    /**
     * Gets the value of the itemCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getItemCode() {
        return itemCode;
    }

    /**
     * Sets the value of the itemCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setItemCode(String value) {
        this.itemCode = value;
    }

    /**
     * Gets the value of the studyMaterialLanguageTypeKey property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStudyMaterialLanguageTypeKey() {
        return studyMaterialLanguageTypeKey;
    }

    /**
     * Sets the value of the studyMaterialLanguageTypeKey property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStudyMaterialLanguageTypeKey(String value) {
        this.studyMaterialLanguageTypeKey = value;
    }

    /**
     * Gets the value of the studyMaterialDescription property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStudyMaterialDescription() {
        return studyMaterialDescription;
    }

    /**
     * Sets the value of the studyMaterialDescription property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStudyMaterialDescription(String value) {
        this.studyMaterialDescription = value;
    }

    /**
     * Gets the value of the studyMaterialIsOptional property.
     * 
     */
    public boolean isStudyMaterialIsOptional() {
        return studyMaterialIsOptional;
    }

    /**
     * Sets the value of the studyMaterialIsOptional property.
     * 
     */
    public void setStudyMaterialIsOptional(boolean value) {
        this.studyMaterialIsOptional = value;
    }

    /**
     * Gets the value of the studyMaterialEnabled property.
     * 
     */
    public boolean isStudyMaterialEnabled() {
        return studyMaterialEnabled;
    }

    /**
     * Sets the value of the studyMaterialEnabled property.
     * 
     */
    public void setStudyMaterialEnabled(boolean value) {
        this.studyMaterialEnabled = value;
    }

    /**
     * Gets the value of the legacyKeys property.
     * 
     * @return
     *     possible object is
     *     {@link StudentModuleOfferingStudyMaterialInfo.LegacyKeys }
     *     
     */
    public StudentModuleOfferingStudyMaterialInfo.LegacyKeys getLegacyKeys() {
        return legacyKeys;
    }

    /**
     * Sets the value of the legacyKeys property.
     * 
     * @param value
     *     allowed object is
     *     {@link StudentModuleOfferingStudyMaterialInfo.LegacyKeys }
     *     
     */
    public void setLegacyKeys(StudentModuleOfferingStudyMaterialInfo.LegacyKeys value) {
        this.legacyKeys = value;
    }


    /**
     * <p>Java class for anonymous complex type.
     * 
     * <p>The following schema fragment specifies the expected content contained within this class.
     * 
     * <pre>
     * &lt;complexType&gt;
     *   &lt;complexContent&gt;
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
     *       &lt;sequence&gt;
     *         &lt;element name="entry" maxOccurs="unbounded" minOccurs="0"&gt;
     *           &lt;complexType&gt;
     *             &lt;complexContent&gt;
     *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
     *                 &lt;sequence&gt;
     *                   &lt;element name="key" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
     *                   &lt;element name="value" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/&gt;
     *                 &lt;/sequence&gt;
     *               &lt;/restriction&gt;
     *             &lt;/complexContent&gt;
     *           &lt;/complexType&gt;
     *         &lt;/element&gt;
     *       &lt;/sequence&gt;
     *     &lt;/restriction&gt;
     *   &lt;/complexContent&gt;
     * &lt;/complexType&gt;
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "entry"
    })
    public static class LegacyKeys {

        protected List<StudentModuleOfferingStudyMaterialInfo.LegacyKeys.Entry> entry;

        /**
         * Gets the value of the entry property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the Jakarta XML Binding object.
         * This is why there is not a <CODE>set</CODE> method for the entry property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getEntry().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link StudentModuleOfferingStudyMaterialInfo.LegacyKeys.Entry }
         * 
         * 
         */
        public List<StudentModuleOfferingStudyMaterialInfo.LegacyKeys.Entry> getEntry() {
            if (entry == null) {
                entry = new ArrayList<StudentModuleOfferingStudyMaterialInfo.LegacyKeys.Entry>();
            }
            return this.entry;
        }


        /**
         * <p>Java class for anonymous complex type.
         * 
         * <p>The following schema fragment specifies the expected content contained within this class.
         * 
         * <pre>
         * &lt;complexType&gt;
         *   &lt;complexContent&gt;
         *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
         *       &lt;sequence&gt;
         *         &lt;element name="key" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
         *         &lt;element name="value" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/&gt;
         *       &lt;/sequence&gt;
         *     &lt;/restriction&gt;
         *   &lt;/complexContent&gt;
         * &lt;/complexType&gt;
         * </pre>
         * 
         * 
         */
        @XmlAccessorType(XmlAccessType.FIELD)
        @XmlType(name = "", propOrder = {
            "key",
            "value"
        })
        public static class Entry {

            protected String key;
            protected Integer value;

            /**
             * Gets the value of the key property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getKey() {
                return key;
            }

            /**
             * Sets the value of the key property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setKey(String value) {
                this.key = value;
            }

            /**
             * Gets the value of the value property.
             * 
             * @return
             *     possible object is
             *     {@link Integer }
             *     
             */
            public Integer getValue() {
                return value;
            }

            /**
             * Sets the value of the value property.
             * 
             * @param value
             *     allowed object is
             *     {@link Integer }
             *     
             */
            public void setValue(Integer value) {
                this.value = value;
            }

        }

    }

}
