package com.biomatters.plugins.eupathdb.webservices.models.wadl;

import com.google.gson.Gson;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

/**
 * The class <code>Resources</code> is a POJO to map resources from webservice wadl.
 *
 * @author sidney
 */
@XmlRootElement
public class Resources {

    /**
     * base
     */
    @XmlAttribute
    private String base;

    /**
     * Resource of each resources
     */
    @XmlElement
    private List<Resource> resource;

    /**
     * Constructor
     *
     * @param base
     * @param resource
     */
    public Resources(String base, List<Resource> resource) {
        this.base = base;
        this.resource = resource;
    }

    /**
     * Empty constructor needed for deserialization by JAXB
     */
    public Resources() {
    }

    /**
     * Get base
     *
     * @return String
     */
    public String getBase() {
        return base;
    }

    /**
     * Get resource
     *
     * @return resource
     */
    public List<Resource> getResource() {
        return resource;
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
