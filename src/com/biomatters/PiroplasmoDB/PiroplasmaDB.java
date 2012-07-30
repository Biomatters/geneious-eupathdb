package com.biomatters.PiroplasmaDB;

import com.biomatters.geneious.publicapi.databaseservice.*;
import com.biomatters.geneious.publicapi.documents.Condition;
import com.biomatters.geneious.publicapi.documents.DocumentField;
import com.biomatters.geneious.publicapi.documents.URN;
import com.biomatters.geneious.publicapi.documents.sequence.SequenceAnnotation;
import com.biomatters.geneious.publicapi.documents.sequence.SequenceAnnotationInterval;
import com.biomatters.geneious.publicapi.documents.sequence.SequenceAnnotationQualifier;
import com.biomatters.geneious.publicapi.implementations.sequence.DefaultAminoAcidSequence;
import com.biomatters.geneious.publicapi.implementations.sequence.DefaultNucleotideSequence;
import com.biomatters.geneious.publicapi.implementations.sequence.DefaultSequenceDocument;
import com.biomatters.geneious.publicapi.plugin.Icons;
import com.biomatters.geneious.publicapi.utilities.IconUtilities;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.Scanner;
import java.net.*;

/**
 * This class is an example of a DatabaseService which provides access to the contents of a fasta file.
 *
 * @version $Id$
 */

public class PiroplasmaDB extends DatabaseService {

    public PiroplasmaDB(File fastaFile) {
    }

    public String getUniqueID() {
        return "PiroplasmaDBPlugin";
    }

    public String getName() {
        return "PiroplasmaDB";
    }

    public String getDescription() {
        return "Plugin for access to PiroplasmaDB";
    }

    public String getHelp() {
        return "Help with the service!";
    }

    public Icons getIcons() {
    	Icons icon = IconUtilities.getIcons("database32.png");
        return icon;
    }

    public QueryField[] getSearchFields() {
        return new QueryField[]{
                new QueryField(new DocumentField("B. bovis","Babesia bovis","Bb",String.class,false,false),new Condition[]{Condition.CONTAINS}),
                new QueryField(new DocumentField("T. annulata","Theileria annulata","Ta",String.class,false,false),new Condition[]{Condition.CONTAINS}),
                new QueryField(new DocumentField("T. parva","Theileria parva","Tp",String.class,false,false),new Condition[]{Condition.CONTAINS}),
        };
    }

    
	public void retrieve(Query query, RetrieveCallback callback, URN[] urnsToNotRetrieve) throws DatabaseServiceException {
		try {
            int beginPos; 
            int endPos;
            String species="", speciesId=""; 
            String text="", searchText=""; 
           	String inputLine, allLines="";
           	URL yahoo;

			String myString = query.toString();
			if (myString.contains("BB") || myString.contains("TA")|| myString.contains("TP")) {
				beginPos = myString.indexOf("'");
				text = myString.substring(beginPos + 1,	myString.lastIndexOf("'"));
				System.out.print(text);
				yahoo = new URL("http://PiroplasmaDB.org/webservices/GeneQuestions/GeneByLocusTag.xml?ds_gene_ids=" + text + "&o-fields=primary_key,organism,product,cds,protein_sequence");
			}
			else {
				if (myString.contains("B. bovis")) {
					species = "Babesia%20bovis";
				} else if (myString.contains("T. annulata")) {
					species = "Theileria%20annulata";
				} else if (myString.contains("T. parva")) {
					species = "Theileria%20parva";
				} else {
					species = "Babesia%20bovis,Theileria%20annulata,Theileria%20parva";
				};

				beginPos = myString.indexOf("'");
				searchText = myString.substring(beginPos + 1,
						myString.lastIndexOf("'"));
				for (int i = 0; i < searchText.length(); i++) {
					if (searchText.charAt(i) != ' ') {
						text = text + searchText.charAt(i);
					} else {
						text = text + "%20";
					}
				};
				System.out.print(searchText);
				yahoo = new URL("http://PiroplasmaDB.org/webservices/GeneQuestions/GenesByTextSearch.xml?text_search_organism="+species+"&text_expression="+text+"*&text_fields=Gene%20product&max_pvalue=-30&o-fields=primary_key,organism,product,cds,protein_sequence");
			};
            
            URLConnection yc = yahoo.openConnection();
            BufferedReader in = new BufferedReader(new InputStreamReader(yc.getInputStream()));
            while ((inputLine = in.readLine()) != null){
            	System.out.println(inputLine);
            	allLines= allLines + inputLine;
            };
            in.close();
          
            int total=0;
            String token;
            String geneId = "";
            speciesId="";
            String product = "";
            String cds = "";
            String proSeq = "";
            Scanner sc = new Scanner(allLines);
            sc.useDelimiter("<");
            token = sc.next();
            System.out.println(token);
            if (sc.findInLine("recordset")!=null){
            	if (sc.findInLine("count")!=null){
            		token = sc.next();
            		System.out.println(token);
            		beginPos = token.indexOf("="); //index position of first
            		endPos = token.indexOf("type");
            		total = Integer.parseInt(token.substring(beginPos+2, endPos-2));
                }
            };
            for (int i=0; i<total; i++){
            	if (sc.findInLine("record")!=null){
            		if (sc.findInLine("field name")!=null){
            			if (sc.findInLine("Gene")!=null){
            				token = sc.next();
            				token = sc.next();
            				beginPos = token.indexOf("CDATA[");
            				endPos = token.indexOf("]");
            				geneId = token.substring(beginPos+6, endPos);
            				System.out.println("Gene ID: " + geneId);
            			};
            		};
            		if (sc.findInLine("field name")!=null){
            			if (sc.findInLine("Organism")!=null){
            				token = sc.next();
            				token = sc.next();
            				beginPos = token.indexOf("CDATA[");
            				endPos = token.indexOf("]");
            				speciesId = token.substring(beginPos+6, endPos);
            				System.out.println("Organism " + speciesId);
            			};
            		};
            		if (sc.findInLine("field name")!=null){
            			if (sc.findInLine("Product Description")!=null){
            				token = sc.next();
            				token = sc.next();
            				beginPos = token.indexOf("CDATA[");
            				endPos = token.indexOf("]");
            				product = token.substring(beginPos+6, endPos);
            				System.out.println("Product Description " + product);
            			};
            		};
            		if (sc.findInLine("field name")!=null){
            			if (sc.findInLine("Coding Sequence")!=null){
            				token = sc.next();
            				token = sc.next();
            				beginPos = token.indexOf("CDATA[");
            				endPos = token.indexOf("]");
            				cds = token.substring(beginPos+6, endPos);
            				System.out.println("Coding Sequence" + cds);
            			};
            		};
            		if (sc.findInLine("field name")!=null){
            			if (sc.findInLine("Protein Sequence")!=null){
            				token = sc.next();
            				token = sc.next();
            				beginPos = token.indexOf("CDATA[");
            				endPos = token.indexOf("]");
            				proSeq = token.substring(beginPos+6, endPos);
            				System.out.println("Protein Sequence" + proSeq);
            			};
            		};

                    DefaultSequenceDocument doc, adoc;
            		doc = new DefaultNucleotideSequence(geneId, product, cds, new Date());
            		SequenceAnnotationInterval interval = new SequenceAnnotationInterval(1, doc.getSequenceLength());
            		SequenceAnnotation annotation=new SequenceAnnotation(geneId,"Nucleotides",interval);
            		annotation.addQualifier(new SequenceAnnotationQualifier("URL", "http://beta.piroplasmadb.org/gene/"+geneId));
            		doc.setAnnotations(Arrays.asList(annotation));
                    doc.addDisplayableField(DocumentField.ORGANISM_FIELD);
                    doc.setFieldValue(DocumentField.ORGANISM_FIELD.getCode(), speciesId);
                    doc.addDisplayableField(DocumentField.MOLECULE_TYPE_FIELD);
                    doc.setFieldValue(DocumentField.MOLECULE_TYPE_FIELD.getCode(), "DNA");
                    if(doc != null){
            			callback.add(doc, Collections.<String,Object>emptyMap());
            		};   

            		adoc = new DefaultAminoAcidSequence(geneId, product, proSeq, new Date());
            		interval = new SequenceAnnotationInterval(1, adoc.getSequenceLength());
            		annotation=new SequenceAnnotation(geneId,"Amino acids",interval);
            		annotation.addQualifier(new SequenceAnnotationQualifier("URL", "http://beta.piroplasmadb.org/gene/"+geneId));
            		adoc.setAnnotations(Arrays.asList(annotation));            		
                    adoc.addDisplayableField(DocumentField.ORGANISM_FIELD);
                    adoc.setFieldValue(DocumentField.ORGANISM_FIELD.getCode(), speciesId);
                    adoc.addDisplayableField(DocumentField.MOLECULE_TYPE_FIELD);
                    adoc.setFieldValue(DocumentField.MOLECULE_TYPE_FIELD.getCode(), "Protein");
            		if(adoc != null){
            			callback.add(adoc, Collections.<String,Object>emptyMap());
            		};   
            	};
            };
		}
		catch (Exception e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
}
