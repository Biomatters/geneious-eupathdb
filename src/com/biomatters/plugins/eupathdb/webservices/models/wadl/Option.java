package com.biomatters.plugins.eupathdb.webservices.models.wadl;

import com.google.gson.Gson;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

/**
 * The class <code>Option</code> is a POJO to map option
 *
 * @author sidney
 */
@XmlRootElement
public class Option {
    /**
     * value
     */
    @XmlAttribute
    private String value;

    /**
     * Doc
     */
    @XmlElement
    private List<Doc> doc;

    /**
     * Constructor
     *
     * @param value the value
     * @param doc the doc
     */
    public Option(String value, List<Doc> doc) {
        this.value = value;
        this.doc = doc;
    }

    /**
     * Empty constructor needed for deserialization by JAXB
     */
    public Option() {
    }

    /**
     * Get name
     *
     * @return String
     */
    public String getValue() {
        return value;
    }

    /**
     * Get doc
     *
     * @return doc
     */
    public List<Doc> getDoc() {
        return doc;
    }

    /**
     * JSON representation of the class <code>Resources</code>.
     *
     * @return String
     */
    @Override
    public String toString() {
        return new Gson().toJson(this);
    }

}
