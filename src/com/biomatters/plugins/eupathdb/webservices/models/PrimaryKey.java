package com.biomatters.plugins.eupathdb.webservices.models;

import com.google.gson.Gson;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

/**
 * The class <code>PrimaryKey</code> is a POJO to map records from webservice response.
 *
 * @author sidney
 */
@XmlRootElement
public class PrimaryKey {

    /**
     * Column of the each record
     */
    @XmlElement
    private List<Column> column;

    /**
     * Constructor
     *
     * @param column the column
     */
    public PrimaryKey(List<Column> column) {
        this.column = column;
    }

    /**
     * Empty constructor needed for deserialization by JAXB
     */
    public PrimaryKey() {
    }

    /**
     * Get column
     *
     * @return column
     */
    public List<Column> getColumn() {
        return column;
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
