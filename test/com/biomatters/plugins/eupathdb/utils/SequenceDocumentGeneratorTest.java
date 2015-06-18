package com.biomatters.plugins.eupathdb.utils;

import com.biomatters.geneious.publicapi.documents.sequence.SequenceDocument;
import com.biomatters.geneious.publicapi.implementations.sequence.DefaultAminoAcidSequence;
import com.biomatters.geneious.publicapi.implementations.sequence.DefaultNucleotideSequence;
import com.biomatters.geneious.publicapi.implementations.sequence.DefaultSequenceDocument;
import com.biomatters.plugins.eupathdb.webservices.models.Field;
import com.biomatters.plugins.eupathdb.webservices.models.Record;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * The class <code>SequenceDocumentGeneratorTest</code> contains tests for the class
 * <code>{@link SequenceDocumentGenerator}</code>.
 *
 * @author cybage
 * @version $Revision: 1.0 $
 */
public class SequenceDocumentGeneratorTest {

    /**
     * Run the DefaultSequenceDocument getDefaultSequenceDocument(Map<String,
     * String>, String, DocType) method test for DefaultNucleotideSequence
     * document.
     *
     * @throws Exception
     */
    @Test
    public void testGetDefaultSequenceDocumentNucleotide() throws Exception {
        SequenceDocument.Alphabet alphabet = SequenceDocument.Alphabet.NUCLEOTIDE;

        DefaultSequenceDocument document = SequenceDocumentGenerator.getDefaultSequenceDocument(getRecord(), "", alphabet);
        Assert.assertNotNull("getDefaultSequenceDocument method returned null. Expected is an instance of DefaultNucleotideSequence", document);
        Assert.assertTrue("An instance of DefaultNucleotideSequence should have been generated. Generated is an instance of " + document.getClass() + ".", document instanceof DefaultNucleotideSequence);
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

        DefaultSequenceDocument document = SequenceDocumentGenerator.getDefaultSequenceDocument(getRecord(), "", alphabet);
        Assert.assertNotNull("getDefaultSequenceDocument method returned null. Expected is an instance of DefaultAminoAcidSequence", document);
        Assert.assertTrue("An instance of DefaultAminoAcidSequence should have been generated. Generated is an instance of " + document.getClass() + ".", document instanceof DefaultAminoAcidSequence);
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
        fields.add(new Field("cds", "ATGAAAATCGAATCTAAGGAAATACAGAACTCATCAAAACTTCCAAACATAATTATAACTGGGACGCCAGGAGTTGGTAAAAGCACCTTATGTGAAGAATTAGTTGAAATCATAAATAAAGATTTTGAAGAAAAGTTTAGAATAGATGGAAAAGAACAACTAAAAATGATACATTTAAATTTATCAAATATTATAAAAAATGAAAGATTATATGAAGAATATGATGACGAATTAGATGCAAGTATATTTAGTGAAGAACTAGTAAATCAAAAATTAAAAAAATTAAATTTACAAAATGGTGGTTATATAATAGATTTTCATGATGTTAATTTTTTATACGAAAATAAATATATCGATAAAATTTTTTTATTAACAGCATCAACAAATGTTTTATACGAACGTTTAGAAAAAAGAAATTACACGAAAGATAAAATTAAAAATAATATTGAATGTGAAATATTTCAGGTTATAAAAGAAGACATATTAGAGAATTATGACGATGAAAATATTTTTGTAGAGTTACAAAATAATAATTTAGAAGATCATGACAAAAACATTTCTTTTATTCAAAAATGGATTCATTCTTATATGACTCAAGGGTTCTAA"));
        fields.add(new Field("protein_sequence", "MKIESKEIQNSSKLPNIIITGTPGVGKSTLCEELVEIINKDFEEKFRIDGKEQLKMIHLNLSNIIKNERLYEEYDDELDASIFSEELVNQKLKKLNLQNGGYIIDFHDVNFLYENKYIDKIFLLTASTNVLYERLEKRNYTKDKIKNNIECEIFQVIKEDILENYDDENIFVELQNNNLEDHDKNISFIQKWIHSYMTQGF"));
        return new Record("",fields);
    }
}