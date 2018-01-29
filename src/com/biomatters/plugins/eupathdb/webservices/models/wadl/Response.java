package com.biomatters.plugins.eupathdb.webservices.models.wadl;

import com.google.gson.Gson;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

/**
 * The class <code>Response</code> is a POJO to map response from webservice wadl.
 *
 * @author sidney
 */
@XmlRootElement
public class Response {
    /**
     * Representation elements
     */
    @XmlElement
    private List<Representation> representation;

    /**
     * Constructor
     *
     * @param representation the representation
     */
    public Response(List<Representation> representation) {
        this.representation = representation;
    }

    /**
     * Empty constructor needed for deserialization by JAXB
     */
    public Response() {
    }

    /**
     * Get representation
     *
     * @return representation
     */
    public List<Representation> getRepresentation() {
        return representation;
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
