package com.biomatters.plugins.eupathdb.webservices.models.wadl;

import com.google.gson.Gson;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

/**
 * The class <code>Resource</code> is a POJO to map resource
 *
 * @author sidney
 */
@XmlRootElement
public class Resource {

    /**
     * path
     */
    @XmlAttribute
    private String path;

    /**
     * Resource
     */
    @XmlElement
    private List<Resource> resource;

    /**
     * Method
     */
    @XmlElement
    private List<Method> method;

    /**
     * Constructor
     *
     * @param path
     * @param resource
     * @param method
     */
    public Resource(String path, List<Resource> resource, List<Method> method) {
        this.path = path;
        this.resource = resource;
        this.method = method;
    }

    /**
     * Empty constructor needed for deserialization by JAXB
     */
    public Resource() {
    }

    /**
     * Get path
     *
     * @return String
     */
    public String getPath() {
        return path;
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
     * Get method
     *
     * @return method
     */
    public List<Method> getMethod() {
        return method;
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
