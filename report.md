# Programming Project report

### Application Architecture
The Application of our Project has been structured by having one Java file that is running the Program (App.java) and executes the phases for the entire fetching and analysis of data, previously created using other classes. 
The Activity class is the class which uses various getters/ setters for the Activities and is the basic building block for the program. It contains also a boolean method to verify whether an Activity is the same as another.
The JsonSchemaValidator class focuses on making sure the Json File has been properly declared and formatted, and includes only two methods.
The Analysis class is the class responsible for the computation of the data from the Activity json files.

### Application of different techniques in class
The project uses as techniques taught in class the following:

- Generics/ collection
- API/ URL extraction 
- reading/ writing to files
- JSON Schema Validation
-  Logging
- Unit Testing using JUnit

For the Generics/ collection part of the program we used it in the Analysis class, and it is used to process the information for the various Json files properties and attributes, fetching the information such as getting most Activities and getting the least Activities number ( methods for reference getMostActivities() and getLeastActivities()).

For the API  we used the Open Data Hub to get and extract the information for the Activities and their respective data attributes. 

We implemented a technique to read and write data to files from the Open Data Hub content to their respective JSON Files. Specifically, we got the information from the API and written it in json format on a file created by the java methods. 

For the JSON Schema Validation, we programmed a custom class to verify the schema of the Json file and its nodes making sure it has been formatted using the right Json standards. This has been done by checking that the File created has the Strings correctly formatted to output the file as a JSON Schema.

For the Logging part we used the Log4j library to make sure the contents for the Json files have been properly read and written successfully via the creation of a custom file based from the API data. Examples for logging are included in the MyLogger java class and it various types of messages for the various logging outputs depending on the seriousness of the message log (info, warn, error, etc... ).

For the Unit Testing of the Program we used JUnit and tested the  ActivityParser, Activity, Analysis, FileManager and JsonSchemaValidator classes. The classes have been tested for the various parts to check whether the methods return the expected output for the program to run properly.

### Challenges Throughout the Project

**Alberto**

From my point of view, the main challenge was to condense all knowledge that we gathered along the course into straight coding, I had to experiment and try many things for the first time, since I wasn't able to be present to all labs. I found out some big mistakes of mine, by just trying and with the help of my teammates I was able to figure out what I needed to do and how. 
Some mistakes were, for example, using the "wrong" IDE (I switched from Eclipse to Intellij ) or the fact that I had never installed maven on the pc, some pretty basic stuff but that I had never figured out. Sometimes I had to go through the slides of the lecture or the recordings of the lecture just to find out what I did wrong or to copy a strategy or a piece of code I wanted to use. All in all, it has been a challenging project, but it has also been a lot of fun, and I am proud of code we produced altogether.
