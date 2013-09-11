package com.biomatters.PlasmoDB;

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
 * Copyright (C) 2012, Charles Ma <charles_ma@email.com>
 *
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Library General Public
 * License as published by the Free Software Foundation; either
 * version 2 of the License, or (at your option) any later version.
 * 
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Library General Public License for more details.
 * 
 * You should have received a copy of the GNU Library General Public
 * License along with this library; if not, write to the
 * Free Software Foundation, Inc., 51 Franklin St, Fifth Floor,
 * Boston, MA  02110-1301, USA.
 */
public class PlasmoDB extends DatabaseService {

    public String getUniqueID() {
        return "PlasmoDBPlugin";
    }

    public String getName() {
        return "PlasmoDB";
    }

    public String getDescription() {
        return "Plugin for access to PlasmoDB";
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
                new QueryField(new DocumentField("P. falciparum","Plasmodium falciparum","Pf",String.class,false,false),new Condition[]{Condition.CONTAINS}),
                new QueryField(new DocumentField("P. vivax","Plasmodium vivax","Pv",String.class,false,false),new Condition[]{Condition.CONTAINS}),
                new QueryField(new DocumentField("P. yoelii","Plasmodium yoelii","Py",String.class,false,false),new Condition[]{Condition.CONTAINS}),
                new QueryField(new DocumentField("P. berghei","Plasmodium berghei","Pb",String.class,false,false),new Condition[]{Condition.CONTAINS}),
                new QueryField(new DocumentField("P. chabaudi","Plasmodium chabaudi","Pc",String.class,false,false),new Condition[]{Condition.CONTAINS}),
                new QueryField(new DocumentField("P. knowlesi","Plasmodium knowlesi","Pk",String.class,false,false),new Condition[]{Condition.CONTAINS})
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
            if (myString.contains("PF") || myString.contains("MAL") || myString.contains("PV") || myString.contains("PY") || myString.contains("PB") || myString.contains("PC") || myString.contains("PK")){
            	beginPos = myString.indexOf("'");
                text = myString.substring(beginPos+1, myString.lastIndexOf("'"));
            	System.out.print(text);
            	yahoo = new URL("http://PlasmoDB.org/webservices/GeneQuestions/GeneByLocusTag.xml?ds_gene_ids="+text+"&o-fields=primary_key,organism,product,cds,protein_sequence");

            }
            else{
				if (myString.contains("P. falciparum")) {
					species = "Plasmodium%20falciparum";
				} else if (myString.contains("P. vivax")) {
					species = "Plasmodium%20vivax";
				} else if (myString.contains("P. yoelii")) {
					species = "Plasmodium%20yoelii";
				} else if (myString.contains("P. berghei")) {
					species = "Plasmodium%20berghei";
				} else if (myString.contains("P. chabaudi")) {
					species = "Plasmodium%20chabaudi";
				} else if (myString.contains("P. knowlesi")) {
					species = "Plasmodium%20knowlesi";
				} else {
					species = "Plasmodium%20falciparum,Plasmodium%20vivax,Plasmodium%20yoelii,Plasmodium%20berghei,Plasmodium%20chabaudi,Plasmodium%20knowlesi";
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
                if ((text.charAt(0) != '"')&&(text.charAt(text.length()-1) != '"')){
                	text = text + "*";
                };
                		
				System.out.print(searchText);
                yahoo = new URL("http://PlasmoDB.org/webservices/GeneQuestions/GenesByTextSearch.xml?text_search_organism="+species+"&text_expression="+text+"&text_fields=Gene%20product&max_pvalue=-30&o-fields=primary_key,organism,product,cds,protein_sequence");
            };
            
            URLConnection yc = yahoo.openConnection();
            BufferedReader in = new BufferedReader(
            	new InputStreamReader(
                   			yc.getInputStream()));
                    
            while ((inputLine = in.readLine()) != null){
            	System.out.println(inputLine);
            	allLines= allLines + inputLine;
            };
            in.close();
          
            int total=0;
            String token;
            String geneId = "";
            speciesId ="";
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
            			if (sc.findInLine("Gene ID")!=null){
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
            				System.out.println("Organism" + speciesId);
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
            		annotation.addQualifier(new SequenceAnnotationQualifier("URL", "http://plasmodb.org/gene/"+geneId));
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
            		annotation.addQualifier(new SequenceAnnotationQualifier("URL", "http://plasmodb.org/gene/"+geneId));
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
