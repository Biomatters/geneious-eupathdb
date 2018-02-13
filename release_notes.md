# v1.0 (7th October 2015)
First release of new EuPathDB plugin based on original PiroplasmoDB and PiroplasmaDB plugins by Charles Ma.

Adds to [Geneious](http://geneious.com) the ability to submit searches to all of the EuPathDB pathogen databases.

# v1.0.1 (21st February 2017)
- GEN-28102 Fixed search failures by updating to be compatible with EuPathDB server changes

# v1.0.2 (23rd Februray 2017)
- GEN-28419 Restored plugin's R9 compatibility

# v1.0.3 (** TO BE RELEASED **)
- GEN-30327 Update to be compatible with changes in format of EuPathDB server response
- GEN-30525 Lists of organisms for text searches were incomplete. Now download them dynamically from server's wadl
            The search by locus tag (gene ID) was implemented incorrectly. It should only be used when the search
              string is a list of gene IDs separated by commas, semicolons, or whitespace, with no wild cards
              Added a checkbox for user to indicate that the query string represents a list of gene IDs
            Refactored the service classes to remove code duplication.
            Rewrote the tests to do actual online searches. The tests no longer depend on PowerMock.
            Changed Gradle dependency to specify Geneious 9.1.8 since the plugin requires Geneous R9 public API
            Changed Java version source and target from Java 6 to Java 8 which is standard for Geneious as of R9
 
