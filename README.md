# Cultural Leaf Service

## Requirements
JDK 11+ or OpenJDK 11+<br/>
Maven 3.x


## Setup
1. Download and install [JDK 11](https://www.oracle.com/nl/java/technologies/javase/jdk11-archive-downloads.html) or [OpenJDK 11](https://openjdk.org/install/) (or newer versions)
2. Download and install [Maven 3.x](https://maven.apache.org/install.html)
3. Download and install [GraphDB](https://graphdb.ontotext.com/)
4. Open the instance of GraphDB
5. Download prepared RDF datasets:
   1. [Cultural Heritage](https://zenodo.org/records/8024461)
   2. [Museums](https://zenodo.org/records/13908580)
6. [Create a GraphDB repository and load the downloaded datasets](https://graphdb.ontotext.com/documentation/10.0/quick-start-guide.html#create-a-repository)
   * when loading datasets you must fill the following value to the **Named graph** option "http://opendata.cs.pub.ro/resource/"
     <img width="1050" alt="Screenshot 2024-10-09 at 16 08 23" src="https://github.com/user-attachments/assets/48bfb617-3cb9-4f37-968a-1c346bbd0a1b">

7. Clone the repository:
```bash
git clone https://github.com/iliedorobat/cultural-leaf-service.git
```
8. Generate the library<sup>***</sup>:
```bash
mvn validate && mvn clean package
```
9. Run the server<sup>***</sup>:
```bash
java -jar target/cultural-leaf-server-1.1-jar-with-dependencies.jar
```

<sup>***</sup>**If you choose this path the server will produce HTTP error 415. To run the server, you must run the `Server.java` class from your IDE.**
