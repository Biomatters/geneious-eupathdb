package com.biomatters.plugins.eupathdb.webservices.models;

import com.google.gson.Gson;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

/**
 * The class <code>Record</code> is a POJO to map records from webservice response.
 *
 * @author cybage
 * @version $Revision: 1.0 $
 */
@XmlRootElement
public class Record {

    /**
     * Record id
     */
    @XmlAttribute
    private String id;

    /**
     * Fields of the each record
     */
    @XmlElement
    private List<Field> field;

    /**
     * Constructor
     *
     * @param id    the records id
     * @param field the fields
     */
    public Record(String id, List<Field> field) {
        this.id = id;
        this.field = field;
    }

    /**
     * Empty constructor needed for deserialization by JAXB
     */
    public Record() {
    }

    /**
     * Get record ID
     *
     * @return String
     */
    public String getId() {
        return id;
    }

    /**
     * Get fields
     *
     * @return field
     */
    public List<Field> getField() {
        return field;
    }

    /**
     * JSON representation of the class <code>Record</code>.
     *
     * @return String
     */
    @Override
    public String toString() {
        return new Gson().toJson(this);
    }
}
