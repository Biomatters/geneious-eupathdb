package com.biomatters.plugins.eupathdb.webservices.models.wadl;

import com.google.gson.Gson;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

/**
 * The class <code>Param</code> is a POJO to map param
 *
 * @author sidney
 */
@XmlRootElement
public class Param {
    /**
     * name
     */
    @XmlAttribute
    private String name;

    /**
     * type
     */
    @XmlAttribute
    private String type;

    /**
     * required
     */
    @XmlAttribute
    private String required;

    /**
     * default
     */
    @XmlAttribute(name="default")
    private String defaultValue;

    /**
     * repeating
     */
    @XmlAttribute
    private String repeating;

    /**
     * Doc
     */
    @XmlElement
    private List<Doc> doc;

    /**
     * Option
     */
    @XmlElement
    private List<Option> option;

    /**
     * Constructor
     *
     * @param name the name
     * @param type the type
     * @param required the required
     * @param defaultValue the default
     * @param repeating the repeating
     * @param doc the doc
     * @param option the option
     */
    public Param(String name, String type, String required, String defaultValue, String repeating, List<Doc> doc, List<Option> option) {
        this.name = name;
        this.type = type;
        this.required = required;
        this.defaultValue = defaultValue;
        this.repeating = repeating;
        this.doc = doc;
        this.option = option;
    }

    /**
     * Empty constructor needed for deserialization by JAXB
     */
    public Param() {
    }

    /**
     * Get name
     *
     * @return String
     */
    public String getName() {
        return name;
    }

    /**
     * Get type
     *
     * @return String
     */
    public String getType() {
        return type;
    }

    /**
     * Get required
     *
     * @return String
     */
    public String getRequired() {
        return required;
    }

    /**
     * Get default
     *
     * @return String
     */
    public String getDefault() {
        return defaultValue;
    }

    /**
     * Get repeating
     *
     * @return String
     */
    public String getRepeating() {
        return repeating;
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
     * Get option
     *
     * @return option
     */
    public List<Option> getOption() {
        return option;
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
