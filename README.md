
# intercom-assignment

## How to setup :

    mvn clean install
    java -jar target/vjsai-intercom-assignmnet-1.0-SNAPSHOT-jar-with-dependencies.jar
    

    Change app.properties for changing path of input and output file

Problem Statement :
We have some customer records in a text file (customers.txt) -- one customer per line, JSON lines formatted. We want to invite any customer within 100km of our Dublin office for some food and drinks on us. Write a program that will read the full list of customers and output the names and user ids of matching customers (within 100km), sorted by User ID (ascending).

Office Center : 53.339428, -6.257664

### Implementation Phase :

#### Input :
* Reading the file in the form of stream rather than loading all lines in the memory.

#### Processing :
* Created a Util to compute the Spherical distance as per the wiki article.
* For demonstration used cache to store the computed distances
* Filtered the records basing upon the distance
 
#### Output :
* Write the filtered lines to file.

#### Design Patterns:
* Created a interface to DataStore
* Designed a DataStore Adapter to abstract away the reading and writing part.
* The idea here of using Adapter pattern is to be able to switch with ease in case you need to read from a diffrent DataStore.
* Created a caching layer to cache the computed distance of the user Id from office center.
* Created a filter which would filter basing upon the distance configured and returns the sorted list of filtered users within the given distance. 

TODO :
* Need to implement a TTl Cache like https://github.com/vjsai/mini-kafka/blob/master/mini-kafka-core/mini-kafka-core/src/main/java/com/vjsai/mini/kafka/cache/DataCache.java

### Assumptions:
* Like specified each line will have json record.
* Location of the user doesnt change frequently.(Like address of home)

### Other Scenarios thought of :


## Scenario 1 :

If the size of the input file is pretty huge and the filtered users are of low compared to all users.And filtered users are written to a single file.

## Approach :

* We can use a single thread to read lines from file
* These lines can be pushed to Queue (BlockingLinkedQueue).
* A ThreadPool to filter out basing upon the distance from queue.
* The filtered records are pushed to writer thread which only hanldes writing to a file.

## Scenario 2:
If the input is from different files and output should be written in different files  with filtered entries.

## Approach :

* Would use LMAX Disruptor to read multiple files and write multiple files.

  Links : https://www.baeldung.com/lmax-disruptor-concurrency
