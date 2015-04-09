package com.biomatters.plugins.eupathdb.webservices.models;

import com.google.gson.Gson;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlValue;

/**
 * The class <code>Field</code> is a POJO to map fields from webservice response.
 *
 * @author cybage
 * @version $Revision: 1.0 $
 */
@XmlRootElement
public class Field {

    /**
     * Field name
     */
    @XmlAttribute
    private String name;

    /**
     * Field value
     */
    @XmlValue
    private String value;

    /**
     * Field title
     */
    @XmlAttribute
    private String title;

    /**
     * Constructor
     *
     * @param name  the field name
     * @param title the filed title
     * @param value the filed value
     */
    public Field(String name, String title, String value) {
        this.name = name;
        this.title = title;
        this.value = value;
    }

    /**
     * Constructor
     *
     * @param name  the field name
     * @param value the field value
     */
    public Field(String name, String value) {
        this.name = name;
        this.value = value;
    }

    /**
     * Empty constructor needed for deserialization by JAXB
     */
    public Field() {
    }

    /**
     * Get field name
     *
     * @return String
     */
    public String getName() {
        return name;
    }

    /**
     * Get field value
     *
     * @return String
     */
    public String getValue() {
        return value;
    }

    /**
     * Get field title
     *
     * @return String
     */
    public String getTitle() {
        return title;
    }

    /**
     * JSON representation of the class <code>Field</code>.
     *
     * @return String
     */
    @Override
    public String toString() {
        return new Gson().toJson(this);
    }
}
