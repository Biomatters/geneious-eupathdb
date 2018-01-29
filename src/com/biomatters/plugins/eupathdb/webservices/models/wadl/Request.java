package com.biomatters.plugins.eupathdb.webservices.models.wadl;

import com.google.gson.Gson;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

/**
 * The class <code>Request</code> is a POJO to map records from webservice request.
 *
 * @author sidney
 */
@XmlRootElement
public class Request {
    /**
     * Param elements
     */
    @XmlElement
    private List<Param> param;

    /**
     * Constructor
     *
     * @param param the param
     */
    public Request(List<Param> param) {
        this.param = param;
    }

    /**
     * Empty constructor needed for deserialization by JAXB
     */
    public Request() {
    }

    /**
     * Get param
     *
     * @return param
     */
    public List<Param> getParam() {
        return param;
    }

    /**
     * JSON representation of the class <code>PrimaryKey</code>.
     *
     * @return String
     */
    @Override
    public String toString() {
        return new Gson().toJson(this);
    }

}
