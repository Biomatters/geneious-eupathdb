package com.biomatters.plugins.eupathdb.webservices.models;

import com.google.gson.Gson;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

/**
 * The class <code>Recordset</code> is a POJO to map recordset from webservice response.
 *
 * @author cybage
 */
@XmlRootElement
public class Recordset {


    /**
     * Records
     */
    @XmlElement
    private List<Record> record;
    /**
     * Recordset ID
     */
    @XmlAttribute
    private String id;
    /**
     * Recordset count
     */
    @XmlAttribute
    private String count;
    /**
     * Recordset type
     */
    @XmlAttribute
    private String type;

    /**
     * Constructor
     *
     * @param id      the recordset ID
     * @param count   the recordset count
     * @param type    the recordset type
     * @param records the records
     */
    public Recordset(String id, String count, String type, List<Record> records) {
        this.id = id;
        this.count = count;
        this.type = type;
        this.record = records;
    }

    /**
     * Empty constructor needed for deserialization by JAXB
     */
    public Recordset() {
    }

    /**
     * Get Recordset ID
     *
     * @return String
     */
    public String getId() {
        return id;
    }

    /**
     * Get Recordset count
     *
     * @return String
     */
    public String getCount() {
        return count;
    }

    /**
     * Get Recordset type
     *
     * @return String
     */
    public String getType() {
        return type;
    }

    /**
     * Get records
     *
     * @return records
     */
    public List<Record> getRecord() {
        return record;
    }

    /**
     * JSON representation of the class <code>Field</code>.
     *
     * @return String
     */
    @Override
    public String toString() {
        return new Gson().toJson(this);
    }
}
