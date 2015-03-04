package com.biomatters.plugins.eupathdb.utils;

import java.util.Arrays;
import java.util.Date;
import java.util.Map;

import com.biomatters.geneious.publicapi.documents.DocumentField;
import com.biomatters.geneious.publicapi.documents.sequence.SequenceAnnotation;
import com.biomatters.geneious.publicapi.documents.sequence.SequenceAnnotationInterval;
import com.biomatters.geneious.publicapi.documents.sequence.SequenceAnnotationQualifier;
import com.biomatters.geneious.publicapi.implementations.sequence.DefaultAminoAcidSequence;
import com.biomatters.geneious.publicapi.implementations.sequence.DefaultNucleotideSequence;
import com.biomatters.geneious.publicapi.implementations.sequence.DefaultSequenceDocument;

/**
 * The Class <code>SequenceDocumentGenerator</code> generates Nucleotide
 * Sequence document and Amino Acid Sequence document from search results
 * obtained from EuPathDB family of databases.
 * 
 * @author cybage
 * @version $Revision: 1.0 $
 */
public class SequenceDocumentGenerator {

	private static final String PROTEIN = "Protein";
	private static final String DNA = "DNA";
	private static final String URL = "URL";
	private static final String PROTEIN_SEQUENCE = "protein_sequence";
	private static final String ORGANISM = "organism";
	private static final String CDS = "cds";
	private static final String PRODUCT = "product";
	private static final String PRIMARY_KEY = "primary_key";

	/**
	 * The Enum DocType.
	 * 
	 * @author cybage
	 * @version $Revision: 1.0 $
	 */
	public enum DocType {

		NUCLEOTIDE("Nucleotides"), AMINOACID("Amino acids");

		/** The value. */
		private String value;

		/**
		 * Instantiates a new doc type.
		 * 
		 * @param value
		 *            the value
		 */
		private DocType(String value) {
			this.value = value;
		}

		/**
		 * Gets the value.
		 * 
		 * @return the value
		 */
		public String getValue() {
			return value;
		}
	}

	/**
	 * Gets the default sequence document.
	 * 
	 * @param parameters
	 *            the parameters
	 * @param dbUrl
	 *            the db url
	 * @param type
	 *            the type
	 * @return the default sequence document
	 */
	public static DefaultSequenceDocument getDefaultSequenceDocument(
			Map<String, String> parameters, String dbUrl, DocType type) {

		DefaultSequenceDocument doc;

		String geneId = parameters.get(PRIMARY_KEY);
		String product = parameters.get(PRODUCT);
		String cds = parameters.get(CDS);
		String speciesId = parameters.get(ORGANISM);
		String proSeq = parameters.get(PROTEIN_SEQUENCE);

		doc = type.equals(DocType.NUCLEOTIDE) ? new DefaultNucleotideSequence(
				geneId, product, cds, new Date())
				: new DefaultAminoAcidSequence(geneId, product, proSeq,
						new Date());

		SequenceAnnotationInterval interval = new SequenceAnnotationInterval(1,
				doc.getSequenceLength());
		SequenceAnnotation annotation = new SequenceAnnotation(geneId,
				type.getValue(), interval);
		annotation.addQualifier(new SequenceAnnotationQualifier(URL, dbUrl
				+ geneId));
		doc.setAnnotations(Arrays.asList(annotation));
		doc.addDisplayableField(DocumentField.ORGANISM_FIELD);
		doc.setFieldValue(DocumentField.ORGANISM_FIELD.getCode(), speciesId);
		doc.addDisplayableField(DocumentField.MOLECULE_TYPE_FIELD);
		doc.setFieldValue(DocumentField.MOLECULE_TYPE_FIELD.getCode(),
				type.equals(DocType.NUCLEOTIDE) ? DNA : PROTEIN);

		return doc;
	}
}
