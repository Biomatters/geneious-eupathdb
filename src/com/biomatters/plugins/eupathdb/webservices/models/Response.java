package com.biomatters.plugins.eupathdb.webservices.models;

import com.google.gson.Gson;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * The class <code>Response</code> is a POJO to map response from webservice response.
 *
 * @author cybage
 * @version $Revision: 1.0 $
 */
@XmlRootElement
public class Response {

    /**
     * Recordset
     */
    @XmlElement
    private Recordset recordset;

    /**
     * Error
     */
    @XmlElement
    private Error error;

    /**
     * Constructor
     *
     * @param recordset the recordset
     */
    public Response(Recordset recordset) {
        this.recordset = recordset;
    }

    /**
     * Constructor
     *
     * @param error the error
     */
    public Response(Error error) {
        this.error = error;
    }

    /**
     * Empty constructor needed for deserialization by JAXB
     */
    public Response() {
    }


    /**
     * Get Recordset
     *
     * @return Recordset
     */
    public Recordset getRecordset() {
        return recordset;
    }

    /**
     * Get Error
     *
     * @return Error
     */
    public Error getError() {
        return error;
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
