package com.biomatters.plugins.eupathdb.webservices.models.wadl;

import com.google.gson.Gson;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * The class <code>Representation</code> is a POJO to map representation
 *
 * @author sidney
 */
@XmlRootElement
public class Representation {
    /**
     * mediaType
     */
    @XmlAttribute
    private String mediaType;

    /**
     * Constructor
     *
     * @param mediaType the filed mediaType
     */
    public Representation(String mediaType) {
        this.mediaType = mediaType;
    }

    /**
     * Empty constructor needed for deserialization by JAXB
     */
    public Representation() {
    }

    /**
     * Get title
     *
     * @return String
     */
    public String getMediaType() {
        return mediaType;
    }

    /**
     * JSON representation of the class <code>Doc</code>.
     *
     * @return String
     */
    @Override
    public String toString() {
        return new Gson().toJson(this);
    }

}
