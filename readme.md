# EuPathDB Plugin
Adds the ability to submit searches to all of the EuPathDB pathogen databases from within Geneious.

## Authors
* Biomatters Ltd
* Original plugins by Charles Ma - <charles_ma@email.com>

## More Information
* [EuPathDB Website](http://eupathdb.org)

## Requirements
Java Development Kit 1.6+

## Installation
From the root folder run the following command:

    gradlew createPlugin

This will create the plugin under build/distributions

## Development
### Branches
The development of this project follows the [Gitflow branching strategy](https://www.atlassian.com/git/tutorials/comparing-workflows/gitflow-workflow).  All development is done in the develop branch and merged to master when complete.  Thus master only contains released code.

When switching branches it is always a good idea to run a build with

    gradlew build

This will ensure any dependency changes for the new branch are applied and everything compiles.

## Releases
Latest official release can be found at http://www.geneious.com/plugins/eupathdb

See also https://github.com/Biomatters/geneious-eupathdb/releases


## Contributing
Report bugs to support@geneious.com or log it via [GitHub issues](https://github.com/Biomatters/geneious-eupathdb/issues)
