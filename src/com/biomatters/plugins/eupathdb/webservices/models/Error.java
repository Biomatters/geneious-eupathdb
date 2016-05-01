package com.biomatters.plugins.eupathdb.webservices.models;

import com.google.gson.Gson;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * The class <code>Error</code> is a POJO to map error string from webservice response.
 *
 * @author cybage
 */
@XmlRootElement
public class Error {

    /**
     * Error type
     */
    @XmlAttribute
    private String type;

    /**
     * Error code
     */
    @XmlAttribute
    private String code;

    /**
     * Error message
     */
    @XmlElement
    private String msg;

    /**
     * Empty constructor needed for deserialization by JAXB
     */
    public Error() {
    }

    /**
     * Constructor
     *
     * @param type the error type
     * @param code the error code
     * @param msg  the error message
     */
    public Error(String type, String code, String msg) {
        this.type = type;
        this.code = code;
        this.msg = msg;
    }

    /**
     * Get error type
     *
     * @return String
     */
    public String getType() {
        return type;
    }

    /**
     * Get error code
     *
     * @return String
     */
    public String getCode() {
        return code;
    }

    /**
     * Get error message
     *
     * @return String
     */
    public String getMsg() {
        return msg;
    }

    /**
     * JSON representation of the class <code>Error</code>.
     *
     * @return String
     */
    @Override
    public String toString() {
        return new Gson().toJson(this);
    }

}
