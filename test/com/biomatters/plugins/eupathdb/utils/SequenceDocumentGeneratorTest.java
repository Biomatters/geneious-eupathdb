package com.biomatters.plugins.eupathdb.utils;

import com.biomatters.geneious.publicapi.documents.sequence.SequenceDocument;
import com.biomatters.geneious.publicapi.implementations.sequence.DefaultAminoAcidSequence;
import com.biomatters.geneious.publicapi.implementations.sequence.DefaultNucleotideSequence;
import com.biomatters.geneious.publicapi.implementations.sequence.DefaultSequenceDocument;
import com.biomatters.plugins.eupathdb.EuPathDBGenes;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * The class <code>SequenceDocumentGeneratorTest</code> contains tests for the class
 * <code>{@link SequenceDocumentGenerator}</code>.
 *
 * @author cybage
 * @version $Revision: 1.0 $
 */
public class SequenceDocumentGeneratorTest {

    /**
     * Map of parameters required to generate DefaultSequenceDocument.
     */
    private Map<String, String> parameters;

    /**
     * Perform pre-test initialization.
     *
     * @throws Exception if the initialization fails for some reason
     */
    @Before
    public void setUp() throws Exception {
        parameters = new HashMap<String, String>();
        parameters.put("primary_key", "PF3D7_1133400");
        parameters.put("organism", "P. falciparum 3D7");
        parameters.put("product", "apical membrane antigen 1 AMA1");
        parameters.put("cds", "ATGAAAATCGAATCTAAGGAAATACAGAACTCATCAAAACTTCCAAACATAATTATAACTGGGACGCCAGGAGTTGGTAAAAGCACCTTATGTGAAGAATTAGTTGAAATCATAAATAAAGATTTTGAAGAAAAGTTTAGAATAGATGGAAAAGAACAACTAAAAATGATACATTTAAATTTATCAAATATTATAAAAAATGAAAGATTATATGAAGAATATGATGACGAATTAGATGCAAGTATATTTAGTGAAGAACTAGTAAATCAAAAATTAAAAAAATTAAATTTACAAAATGGTGGTTATATAATAGATTTTCATGATGTTAATTTTTTATACGAAAATAAATATATCGATAAAATTTTTTTATTAACAGCATCAACAAATGTTTTATACGAACGTTTAGAAAAAAGAAATTACACGAAAGATAAAATTAAAAATAATATTGAATGTGAAATATTTCAGGTTATAAAAGAAGACATATTAGAGAATTATGACGATGAAAATATTTTTGTAGAGTTACAAAATAATAATTTAGAAGATCATGACAAAAACATTTCTTTTATTCAAAAATGGATTCATTCTTATATGACTCAAGGGTTCTAA");
        parameters.put("protein_sequence", "MKIESKEIQNSSKLPNIIITGTPGVGKSTLCEELVEIINKDFEEKFRIDGKEQLKMIHLNLSNIIKNERLYEEYDDELDASIFSEELVNQKLKKLNLQNGGYIIDFHDVNFLYENKYIDKIFLLTASTNVLYERLEKRNYTKDKIKNNIECEIFQVIKEDILENYDDENIFVELQNNNLEDHDKNISFIQKWIHSYMTQGF");
    }

    /**
     * Run the DefaultSequenceDocument getDefaultSequenceDocument(Map<String,
     * String>, String, DocType) method test for DefaultNucleotideSequence
     * document.
     *
     * @throws Exception
     */
    @Test
    public void testGetDefaultSequenceDocumentNucleotide() throws Exception {
        String dbUrl = EuPathDBUtilities.getValue(EuPathDBGenes.EuPathDatabase.PLASMODB + EuPathDBConstants.DBURL);
        SequenceDocument.Alphabet alphabet = SequenceDocument.Alphabet.NUCLEOTIDE;

        DefaultSequenceDocument document = SequenceDocumentGenerator.getDefaultSequenceDocument(parameters, dbUrl, alphabet);
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
        String dbUrl = EuPathDBUtilities.getValue(EuPathDBGenes.EuPathDatabase.PIROPLASMADB + EuPathDBConstants.DBURL);
        SequenceDocument.Alphabet alphabet = SequenceDocument.Alphabet.PROTEIN;

        DefaultSequenceDocument document = SequenceDocumentGenerator.getDefaultSequenceDocument(parameters, dbUrl, alphabet);
        Assert.assertNotNull("getDefaultSequenceDocument method returned null. Expected is an instance of DefaultAminoAcidSequence", document);
        Assert.assertTrue("An instance of DefaultAminoAcidSequence should have been generated. Generated is an instance of " + document.getClass() + ".", document instanceof DefaultAminoAcidSequence);
    }
}