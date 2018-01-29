package com.biomatters.plugins.eupathdb.webservices.models.wadl;

import com.google.gson.Gson;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * The class <code>Application</code> is a POJO to map application from webservice wadl.
 *
 * @author sidney
 */

@XmlRootElement(name = "application", namespace="http://wadl.dev.java.net/2009/02")
public class Application {

    /**
     * Resources
     */
    @XmlElement
    private Resources resources;

    /**
     * Get Resources
     *
     * @return Resources
     */
    public Resources getResources() {
        return resources;
    }

    /**
     * Constructor
     *
     * @param resources the resources
     */
    public Application(Resources resources) {
        this.resources = resources;
    }

    /**
     * Empty constructor needed for deserialization by JAXB
     */
    public Application() {
    }

    /**
     * JSON representation of the class <code>Response</code>.
     *
     * @return String
     */
    @Override
    public String toString() {
        return new Gson().toJson(this);
    }

}
