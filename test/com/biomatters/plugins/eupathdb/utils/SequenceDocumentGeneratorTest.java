package com.biomatters.plugins.eupathdb.utils;

import com.biomatters.geneious.publicapi.documents.sequence.SequenceAnnotationQualifier;
import com.biomatters.geneious.publicapi.documents.sequence.SequenceDocument;
import com.biomatters.geneious.publicapi.implementations.sequence.DefaultAminoAcidSequence;
import com.biomatters.geneious.publicapi.implementations.sequence.DefaultNucleotideSequence;
import com.biomatters.geneious.publicapi.implementations.sequence.DefaultSequenceDocument;
import com.biomatters.geneious.publicapi.plugin.DocumentImportException;
import com.biomatters.plugins.eupathdb.webservices.models.Field;
import com.biomatters.plugins.eupathdb.webservices.models.Record;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.ArrayList;
import java.util.List;

/**
 * The class <code>SequenceDocumentGeneratorTest</code> contains tests for the class
 * <code>{@link SequenceDocumentGenerator}</code>.
 *
 * @author cybage
 */
public class SequenceDocumentGeneratorTest {

    private static final String NUCLEOTIDE_SEQUENCE = "ATGAAAATCGAAT";
    private static final String PROTEIN_SEQUENCE = "MKIESKEIQNSS";

    /**
     * Run the DefaultSequenceDocument getDefaultSequenceDocument(Map<String,
     * String>, String, DocType) method test for DefaultNucleotideSequence
     * document and their annotation qualifier.
     *
     * @throws Exception
     */
    @Test
    public void testGetDefaultSequenceDocumentNucleotide() throws Exception {
        SequenceDocument.Alphabet alphabet = SequenceDocument.Alphabet.NUCLEOTIDE;
        String url = "http://amoebadb.org/amoeba/showRecord.do?name=GeneRecordClasses.GeneRecordClass&source_id=";
        String expectedUrl = url + getRecord().getId() + "&project_id=AmoebaDB";

        DefaultSequenceDocument document = SequenceDocumentGenerator.getDefaultSequenceDocument(getRecord(), url, alphabet,"AmoebaDB", null);
        Assert.assertNotNull("getDefaultSequenceDocument method returned null. Expected is an instance of DefaultNucleotideSequence", document);
        Assert.assertTrue("An instance of DefaultNucleotideSequence should have been generated. Generated is an instance of " + document.getClass() + ".", document instanceof DefaultNucleotideSequence);
        Assert.assertEquals(NUCLEOTIDE_SEQUENCE, document.getSequenceString());

        SequenceAnnotationQualifier sequenceAnnotationQualifier = document.getSequenceAnnotations().get(0).getQualifiers().get(0);
        Assert.assertEquals("URL", sequenceAnnotationQualifier.getName());
        Assert.assertEquals(expectedUrl, sequenceAnnotationQualifier.getValue());
    }

    /**
     * Run the DefaultSequenceDocument getDefaultSequenceDocument(Map<String,
     * String>, String, DocType) method test for DefaultAminoAcidSequence
     * document.
     *
     * @throws Exception
     */
    @Test
    public void testGetDefaultSequenceDocumentAminoAcid() throws Exception {
        SequenceDocument.Alphabet alphabet = SequenceDocument.Alphabet.PROTEIN;

        DefaultSequenceDocument document = SequenceDocumentGenerator.getDefaultSequenceDocument(getRecord(), "", alphabet,"", null);
        Assert.assertNotNull("getDefaultSequenceDocument method returned null. Expected is an instance of DefaultAminoAcidSequence", document);
        Assert.assertTrue("An instance of DefaultAminoAcidSequence should have been generated. Generated is an instance of " + document.getClass() + ".", document instanceof DefaultAminoAcidSequence);
        Assert.assertEquals(PROTEIN_SEQUENCE, document.getSequenceString());
    }

    /**
     * Returns Record containing fields required to generate DefaultSequenceDocument.
     * @return record The Record
     */
    private Record getRecord() {
        List<Field> fields = new ArrayList<Field>(5);
        fields.add(new Field("primary_key", "PF3D7_1133400"));
        fields.add(new Field("organism", "P. falciparum 3D7"));
        fields.add(new Field("product", "apical membrane antigen 1 AMA1"));
        fields.add(new Field("cds", NUCLEOTIDE_SEQUENCE));
        fields.add(new Field("sequence", PROTEIN_SEQUENCE));
        return new Record("PF3D7_1133400",fields);
    }
}