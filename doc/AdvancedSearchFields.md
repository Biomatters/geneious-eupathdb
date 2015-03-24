#Advanced Search Fields
This plugin supports advanced searching with all the search methods listed under Text, IDs and Organism at http://eupathdb.org/eupathdb/

DocumentField/QueryFields should be created to represent each different search method or term.

Of these methods, there are multiple that can be filtered on organism.  This functionality will not be provided
separate to the standalone organism field.  These searches should use the default organism value unless the user is
performing a compound query.

For compound queries it would be advantageous for the implementation to be optimized by using the organism filter if
the operator of the query is AND and the user has specified an organism value to search by.

Below is a list of the different search terms and how they should be handled.

##GeneByTextSearch
Create a different String based DocumentField for each of the 12 different text terms. (eg) Alias, EC Descriptions, Gene ID etc.

##GeneByLocusTag
This search method can be ignored. The functionality is covered by the GeneByTextSearch search using the text term Gene ID.

##GenesByTaxon
DocumentField.createEnumeratedField() with allowable values being any of the available organisms at any level.
(eg) User can search for organism = Amoebozoa or organism = Acanthamoeba castellanii str. Neff

##GenesWithUserComments
DocumentField.createEnumeratedField() with Yes or No as the valid values.

##GenesWithUpdatedAnnotation
DocumentField.createEnumeratedField() with Yes or No as the valid values.

## GenesByMr4Reagents
DocumentField.createEnumeratedField() with any of the valid values specified by EuPathDB.

