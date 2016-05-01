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
import com.biomatters.geneious.publicapi.plugin.GeneiousService;
import com.biomatters.plugins.eupathdb.services.EuPathDatabaseService;
import com.biomatters.plugins.eupathdb.webservices.models.Field;
import com.biomatters.plugins.eupathdb.webservices.models.Record;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * The Class <code>SequenceDocumentGenerator</code> generates Nucleotide
 * Sequence document and Amino Acid Sequence document from search results
 * obtained from EuPathDB family of databases.
 *
 * @author cybage
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
     * @param database database-name
     * @param childServiceList - child services
     * @return Sequence Document
     */
    public static DefaultSequenceDocument getDefaultSequenceDocument(
            Record record, String dbUrl, SequenceDocument.Alphabet alphabet, String database, List<GeneiousService> childServiceList) {

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
        StringBuilder url = new StringBuilder();
        url.append(dbUrl).append(record.getId());
        if (EUPATH_DATABASE.equals(database)) {
            StringBuilder eupathDbUrl = new StringBuilder();
            if (!speciesId.isEmpty()) {
                String projectId = getDatabaseNameByOrganism(speciesId, childServiceList);
                if (!projectId.isEmpty()) {
                    eupathDbUrl.append(url).append(PROJECT_ID_PARAMETER_URL).append(projectId);
                }
            }
            url = eupathDbUrl;
        } else if (!ORTHOMCL_DATABASE.equals(database)) {
            url.append(PROJECT_ID_PARAMETER_URL).append(database);
        }
        if (!url.toString().isEmpty()) {
            annotation.addQualifier(new SequenceAnnotationQualifier(URL, url.toString()));
        }
        doc.setAnnotations(Arrays.asList(annotation));
        doc.addDisplayableField(DocumentField.ORGANISM_FIELD);
        doc.setFieldValue(DocumentField.ORGANISM_FIELD.getCode(), speciesId);

        return doc;
    }


    /**
     * Get database-name from the organism value.
     *
     * @param organism         - organism name
     * @param childServiceList - child -service
     * @return - database name
     */
    private static String getDatabaseNameByOrganism(String organism, List<GeneiousService> childServiceList) {
        String databaseName = "";
        for (GeneiousService geneiousService : childServiceList) {
            EuPathDatabaseService euPathDatabaseService = (EuPathDatabaseService) geneiousService;
            String allOrganism = euPathDatabaseService.getEukaryoticDatabase().getWebServiceTextSearchOrganismParamValue();
            if (matches(organism, allOrganism)) {
                databaseName = euPathDatabaseService.getName();
                break;
            }
        }
        return databaseName;
    }

    private static boolean matches(String organism, String allOrganism) {
        if(allOrganism == null) {
            return false;
        }
        for (String candidate : allOrganism.split(",")) {
            if(organism.equals(candidate) || organism.equals(abbreviateSpeciesName(candidate))) {
                return true;
            }
        }
        return false;
    }

    private static String abbreviateSpeciesName(String candidate) {
        int firstSpace = candidate.indexOf(" ");
        if(firstSpace > 0 && firstSpace < candidate.length()-1) {
            return candidate.substring(0,1) + ". " + candidate.substring(firstSpace+1);
        }
        return null;
    }
}
