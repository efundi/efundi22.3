
package za.ac.nwu.wsdl.courseoffering;

import java.util.ArrayList;
import java.util.List;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlSeeAlso;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ModulePresentationLocationInfo complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ModulePresentationLocationInfo"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="academicPeriod" type="{http://nwu.ac.za/wsdl/CourseOffering}AcademicPeriodInfo" minOccurs="0"/&gt;
 *         &lt;element name="moduleSubjectCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="moduleNumber" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="startDate" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="endDate" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="metaInfo" type="{http://nwu.ac.za/wsdl/CourseOffering}MetaInfo" minOccurs="0"/&gt;
 *         &lt;element name="presentationLocationUnivNumber" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="description" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="presentationLocationTypeKey" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
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
 *                             &lt;element name="value" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
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
@XmlType(name = "ModulePresentationLocationInfo", propOrder = {
    "academicPeriod",
    "moduleSubjectCode",
    "moduleNumber",
    "startDate",
    "endDate",
    "metaInfo",
    "presentationLocationUnivNumber",
    "description",
    "presentationLocationTypeKey",
    "legacyKeys"
})
@XmlSeeAlso({
    ModuleOfferingPresentationLocationInfo.class
})
public class ModulePresentationLocationInfo {

    protected AcademicPeriodInfo academicPeriod;
    protected String moduleSubjectCode;
    protected String moduleNumber;
    protected String startDate;
    protected String endDate;
    protected MetaInfo metaInfo;
    protected String presentationLocationUnivNumber;
    protected String description;
    protected String presentationLocationTypeKey;
    @XmlElement(required = true)
    protected ModulePresentationLocationInfo.LegacyKeys legacyKeys;

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
     * Gets the value of the presentationLocationUnivNumber property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPresentationLocationUnivNumber() {
        return presentationLocationUnivNumber;
    }

    /**
     * Sets the value of the presentationLocationUnivNumber property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPresentationLocationUnivNumber(String value) {
        this.presentationLocationUnivNumber = value;
    }

    /**
     * Gets the value of the description property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the value of the description property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDescription(String value) {
        this.description = value;
    }

    /**
     * Gets the value of the presentationLocationTypeKey property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPresentationLocationTypeKey() {
        return presentationLocationTypeKey;
    }

    /**
     * Sets the value of the presentationLocationTypeKey property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPresentationLocationTypeKey(String value) {
        this.presentationLocationTypeKey = value;
    }

    /**
     * Gets the value of the legacyKeys property.
     * 
     * @return
     *     possible object is
     *     {@link ModulePresentationLocationInfo.LegacyKeys }
     *     
     */
    public ModulePresentationLocationInfo.LegacyKeys getLegacyKeys() {
        return legacyKeys;
    }

    /**
     * Sets the value of the legacyKeys property.
     * 
     * @param value
     *     allowed object is
     *     {@link ModulePresentationLocationInfo.LegacyKeys }
     *     
     */
    public void setLegacyKeys(ModulePresentationLocationInfo.LegacyKeys value) {
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
     *                   &lt;element name="value" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
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

        protected List<ModulePresentationLocationInfo.LegacyKeys.Entry> entry;

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
         * {@link ModulePresentationLocationInfo.LegacyKeys.Entry }
         * 
         * 
         */
        public List<ModulePresentationLocationInfo.LegacyKeys.Entry> getEntry() {
            if (entry == null) {
                entry = new ArrayList<ModulePresentationLocationInfo.LegacyKeys.Entry>();
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
         *         &lt;element name="value" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
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
            protected String value;

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
             *     {@link String }
             *     
             */
            public String getValue() {
                return value;
            }

            /**
             * Sets the value of the value property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setValue(String value) {
                this.value = value;
            }

        }

    }

}
