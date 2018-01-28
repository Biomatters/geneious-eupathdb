package com.biomatters.plugins.eupathdb.webservices.models;

import com.google.gson.Gson;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Collections;
import java.util.List;

/**
 * The class <code>Record</code> is a POJO to map records from webservice response.
 *
 * @author cybage
 */
@XmlRootElement
public class Record {

    /**
     * The name of the Field that is used as the record id
     */
    private static final String PRIMARY_ID_KEY = "primary_key";

    /**
     * Record primary key element
     */
    @XmlElement
    private PrimaryKey primaryKey;

    /**
     * Record actual primary key as derived from primary_key filed or the primary_key element
     */
    private String id;

    /**
     * Fields of the each record
     */
    @XmlElement
    private List<Field> field;

    /**
     * Constructor used in tests
     *
     * @param id    the records id
     * @param field the fields
     */
    public Record(String id, List<Field> field) {
        this.primaryKey = null;
        if (field == null) {
            this.field = Collections.singletonList(new Field(PRIMARY_ID_KEY, id));
        } else {
            this.field = field;
        }
        this.id = id;
    }

    /**
     * Empty constructor needed for deserialization by JAXB
     */
    public Record() {
    }

    /**
     * Get record primary key
     *
     * @return PrimaryKey
     */
    public PrimaryKey getPrimaryKey() {
        return primaryKey;
    }

    /**
     * Get record id from primary key
     * Caches the result which is found in the field PRIMARY_ID_KEY if it exists,
     * If there is no such field use the first column of the primary_key element
     * This can return null if the record instance is completely empty, but that should never happen in real use
     *
     * @return id
     */
    public String getId() {
        // This was not synchronized because all threads in a race condition would get the same result anyway
        if (id != null)
            return id;
        if (field != null) {
            for (Field f:field) {
                if (f.getName().equals(PRIMARY_ID_KEY)) {
                    id = f.getValue();
                    return id;
                }
            }
        }
        if (primaryKey != null) {
            id = primaryKey.getColumn().get(0).getValue();
        }
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Record record = (Record) o;

        return getId().equals(record.getId());

    }

    @Override
    public int hashCode() {
        return getId().hashCode();
    }
}
