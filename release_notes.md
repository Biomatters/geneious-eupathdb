# v1.0 (7th October 2015)
First release of new EuPathDB plugin based on original PiroplasmoDB and PiroplasmaDB plugins by Charles Ma.

Adds to [Geneious](http://geneious.com) the ability to submit searches to all of the EuPathDB pathogen databases.

# v1.0.1 (21st February 2017)
- GEN-28102 Fixed search failures by updating to be compatible with EuPathDB server changes

# v1.0.2 (23rd Februray 2017)
- GEN-28419 Restored plugin's R9 compatibility

# v1.0.3 (27 February 2018)
- GEN-30327 Update to be compatible with changes in format of EuPathDB server response
- GEN-30525 Plugin now uses information from server to continue to work in the face of certain changes on the server
            Added a checkbox for user to indicate that the query string represents a list of gene IDs
            Rewrote the tests to do actual online searches so changes in hte server that cause problems will be detected
# v1.0.4 (***PUT DATE HERE ***)
- GEN-32939 Some EuPathDB servers now use https URLs and 301 redirect http queries to https
            Changed the URLS of those that have already changed
            Handle 301 redirect responses so the servers that are still http but will be changed continue to work
 
