package com.biomatters.plugins.eupathdb.utils;

import com.biomatters.geneious.publicapi.documents.DocumentField;
import com.biomatters.geneious.publicapi.documents.URN;
import com.biomatters.geneious.publicapi.documents.sequence.SequenceAnnotation;
import com.biomatters.geneious.publicapi.documents.sequence.SequenceAnnotationInterval;
import com.biomatters.geneious.publicapi.documents.sequence.SequenceAnnotationQualifier;
import com.biomatters.geneious.publicapi.documents.sequence.SequenceDocument;
import com.biomatters.geneious.publicapi.implementations.sequence.DefaultAminoAcidSequence;
import com.biomatters.geneious.publicapi.implementations.sequence.DefaultNucleotideSequence;
import com.biomatters.geneious.publicapi.implementations.sequence.DefaultSequenceDocument;
import com.biomatters.plugins.eupathdb.webservices.models.Field;
import com.biomatters.plugins.eupathdb.webservices.models.Record;

import java.util.Arrays;
import java.util.Date;

/**
 * The Class <code>SequenceDocumentGenerator</code> generates Nucleotide
 * Sequence document and Amino Acid Sequence document from search results
 * obtained from EuPathDB family of databases.
 *
 * @author cybage
 * @version $Revision: 1.0 $
 */
public class SequenceDocumentGenerator {

    private static final String URL = "URL";
    private static final String SEQUENCE = "sequence";
    private static final String ORGANISM = "organism";
    private static final String CDS = "cds";
    private static final String PRODUCT = "product";
    private static final String PRIMARY_KEY = "primary_key";
    private static final String URN_NAMESPACE = "sequence" ;
    private static final String URN_ASSIGNER = "eupathdb" ;
    private static final String ORTHOMCL_DATABASE = "OrthoMCL";
    private static final String EUPATH_DATABASE = "EuPathDB";
    private static final String PROJECT_ID_PARAMETER_URL = "&project_id=";

    /**
     * Utility class
     */
    private SequenceDocumentGenerator() {
    }

    /**
     * Gets the default sequence document.
     *
     * @param record   the Record
     * @param dbUrl    the db url
     * @param alphabet the type   @return the default sequence document
     */
    public static DefaultSequenceDocument getDefaultSequenceDocument(
            Record record, String dbUrl, SequenceDocument.Alphabet alphabet, String database) {

        String geneId = "";
        String product = "";
        String cds = "";
        String speciesId = "";
        String proSeq = "";

        for (Field field : record.getField()) {
            if (field.getName().equalsIgnoreCase(PRIMARY_KEY)) {
                geneId = field.getValue();
            } else if (field.getName().equalsIgnoreCase(PRODUCT)) {
                product = field.getValue();
            } else if (field.getName().equalsIgnoreCase(CDS)) {
                cds = field.getValue();
            } else if (field.getName().equalsIgnoreCase(ORGANISM)) {
                speciesId = field.getValue();
            } else if (field.getName().equalsIgnoreCase(SEQUENCE)) {
                proSeq = field.getValue();
            }
        }

        DefaultSequenceDocument doc;
        URN urn = new URN(URN_NAMESPACE, URN_ASSIGNER, record.getId());
        doc = alphabet.equals(SequenceDocument.Alphabet.NUCLEOTIDE) ? new DefaultNucleotideSequence(
                geneId, product, cds, new Date(), urn)
                : new DefaultAminoAcidSequence(geneId, product, proSeq,
                new Date(), urn);

        SequenceAnnotationInterval interval = new SequenceAnnotationInterval(1,
                doc.getSequenceLength());
        SequenceAnnotation annotation = new SequenceAnnotation(geneId,
                SequenceAnnotation.TYPE_GENE, interval);
        String url = dbUrl + record.getId();
        if (EUPATH_DATABASE.equals(database)) {
            String EupathDbUrl = "";
            if(!"".equals(speciesId)) {
                String projectId = getDatabaseNameByOrganism(speciesId);
                if(!"".equals(projectId)) {
                    EupathDbUrl = url + PROJECT_ID_PARAMETER_URL + projectId;
                }
            }
            url = EupathDbUrl;
        }else if(!ORTHOMCL_DATABASE.equals(database)) {
             url = url + PROJECT_ID_PARAMETER_URL + database;
        }
        if(!"".equals(url)) {
            annotation.addQualifier(new SequenceAnnotationQualifier(URL, url));
        }
        doc.setAnnotations(Arrays.asList(annotation));
        doc.addDisplayableField(DocumentField.ORGANISM_FIELD);
        doc.setFieldValue(DocumentField.ORGANISM_FIELD.getCode(), speciesId);

        return doc;
    }

    //Todo - get database name from Organism
    private static String getDatabaseNameByOrganism(String Organism) {
        String databaseName = "";

        return  databaseName;
    }
}
