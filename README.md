# Cultural Leaf Service

## Requirements

JDK 11+ or OpenJDK 11+<br/>
GraphDB 10+<br/>


## Setup

1. Download and install JDK 11 or OpenJDK 11 (or newer versions)
2. Download and install [GraphDB](https://graphdb.ontotext.com/)
3. Open the instance of GraphDB
4. Download prepared RDF datasets:
   1. [Cultural Heritage](https://zenodo.org/records/8024461)
   2. [Museums](https://zenodo.org/records/13908580)
5. [Create a GraphDB repository and load the downloaded datasets](https://graphdb.ontotext.com/documentation/10.0/quick-start-guide.html#create-a-repository)
   * when loading datasets you must fill the following value to the **Named graph** option "http://opendata.cs.pub.ro/resource/"
     <img width="1050" alt="Screenshot 2024-10-09 at 16 08 23" src="https://github.com/user-attachments/assets/48bfb617-3cb9-4f37-968a-1c346bbd0a1b">

6. Clone the repository:
```bash
git clone https://github.com/iliedorobat/cultural-leaf-service.git
```
7. Run **Server.java**
