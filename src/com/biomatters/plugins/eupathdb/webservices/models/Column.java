package com.biomatters.plugins.eupathdb.webservices.models;

import com.google.gson.Gson;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlValue;

/**
 * The class <code>Column</code> is a POJO to map fields from webservice response.
 *
 * @author sidney
 */
@XmlRootElement
public class Column {

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
     * Constructor
     *
     * @param name  the field name
     * @param value the field value
     */
    public Column(String name, String value) {
        this.name = name;
        this.value = value;
    }

    /**
     * Empty constructor needed for deserialization by JAXB
     */
    public Column() {
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
     * JSON representation of the class <code>Field</code>.
     *
     * @return String
     */
    @Override
    public String toString() {
        return new Gson().toJson(this);
    }
}
