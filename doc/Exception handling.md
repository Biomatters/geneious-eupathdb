# Dropbox Exception Handling

This document outlines the strategy for handling various exception types within the Geneious EuPathDB plugin.

## Unchecked Exceptions

In general, unchecked exception should never be caught by any of the plugin code. Core Geneious will handle any such
exceptions (eg. NullPointerException)

## Checked Exceptions

### DatabaseServiceException

This is the primary exception type that can be thrown from DatabaseService methods.  These are handled by
Geneious core and should almost always be propagated rather than being handled internal to the plugin.

Checked exceptions of other types should be converted into this type and thrown.  See below.
```
try {
...
} catch(IOException e) {
    throw new DatabaseServiceException(e, "Search failed: " + e.getMessage(), false);
}
```
Always pass the original exception into the constructor of a new DatabaseServiceException so that no information about
the cause of the problem is lost. A DatabaseServiceException should also indicate to Geneious core whether or not it
was caused by a network problem by passing in a appropriate value for the last parameter of the constructor.

**Note**: In general, plugin code should never catch a `DatabaseServiceException`. Instead it should be propagated up
to the core of Geneious to be handled.