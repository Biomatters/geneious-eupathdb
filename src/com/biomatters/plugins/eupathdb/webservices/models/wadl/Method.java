package com.biomatters.plugins.eupathdb.webservices.models.wadl;

import com.google.gson.Gson;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

/**
 * The class <code>Method</code> is a POJO to map method
 *
 * @author sidney
 */
@XmlRootElement
public class Method {
    /**
     * href
     */
    @XmlAttribute
    private String href;

    /**
     * name
     */
    @XmlAttribute
    private String name;

    /**
     * id
     */
    @XmlAttribute
    private String id;

    /**
     * Doc
     */
    @XmlElement
    private List<Doc> doc;

    /**
     * Request
     */
    @XmlElement
    private Request request;

    /**
     * Response
     */
    @XmlElement
    private Response response;

    /**
     * Constructor
     *
     * @param id the id
     * @param doc
     * @param request
     * @param response
     */
    public Method(String id, List<Doc> doc, Request request, Response response) {
        this.id = id;
        this.doc = doc;
        this.request = request;
        this.response = response;
    }

    /**
     * Empty constructor needed for deserialization by JAXB
     */
    public Method() {
    }

    /**
     * Get path
     *
     * @return String
     */
    public String getId() {
        return id;
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
     * Get request
     *
     * @return request
     */
    public Request getRequest() {
        return request;
    }

    /**
     * Get response
     *
     * @return response
     */
    public Response getResponse() {
        return response;
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
