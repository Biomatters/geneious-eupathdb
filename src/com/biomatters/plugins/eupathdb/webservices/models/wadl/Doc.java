package com.biomatters.plugins.eupathdb.webservices.models.wadl;

import com.google.gson.Gson;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlValue;

/**
 * The class <code>Doc</code> is a POJO to map doc
 *
 * @author sidney
 */
@XmlRootElement
public class Doc {
    /**
     * title
     */
    @XmlAttribute
    private String title;

    /**
     * value
     */
    @XmlValue
    private String value;

    /**
     * Constructor
     *
     * @param title the filed title
     * @param value the filed value
     */
    public Doc(String title, String value) {
        this.title = title;
        this.value = value;
    }

    /**
     * Empty constructor needed for deserialization by JAXB
     */
    public Doc() {
    }

    /**
     * Get title
     *
     * @return String
     */
    public String getTitle() {
        return title;
    }

    /**
     * Get value
     *
     * @return String
     */
    public String getValue() {
        return value;
    }

    /**
     * JSON representation of the class <code>Doc</code>.
     *
     * @return String
     */
    @Override
    public String toString() {
        return new Gson().toJson(this);
    }
}
