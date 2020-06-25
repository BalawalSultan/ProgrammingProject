# Programming Project report

### Application Architecture
The Application of our Project has been structured by having one Java file that is running the Program (App.java) and executes the phases for the entire fetching and analysis of data, previously created using other classes. 
The Activity class is the class which uses various getters/ setters for the Activities and is the basic building block for the program. The JsonSchemaValidator class focuses on making sure the Json File has been properly declared and formatted, and includes only two methods.
The Analysis class is the class responsible for the computation of the data from the Activity json files.

### Application of different techniques in class
The project uses as techniques taught in class the following:

- Java Collections
- Data extraction 
- reading/ writing to files
- JSON Schema Validation
- Logging
- Unit Testing using JUnit

We used the Java Collections API in the Analysis class that uses HashMaps, and it is used to process the information present in each Activity object that is passed to the performAnalysis() method of the Analysis class such as keeping track of how many types of activities there are, how many activities there are per region and getting the most and least number of Activities in a region.
The methods for reference are activityTypesAnalysis(), regionAnalysis(), getMostActivities() and getLeastActivities().

For the API  we used the Open Data Hub, and to get data we used the MyRequest class that fetches the data using an HTTP GET request and returns the fetched data as a string.

To generate the output JSON files we created the FileManager class that has a generateJsonFile() method which uses 
a Gson object to parse the passed object into JSON, and uses a BufferedWriter to create the output file and write JSON in it. Specifically, we pass the objects in the ArrayList of Activities containing the information from the API to the generateJsonFile() method to write it in JSON format. 

For the JSON Schema Validation, we programmed a custom class to verify that the JSON files that our application produces respect the JSON Schema we created, ensuring a level of trust and quality in the output files.
This is done through the JsonSchemaValidator class, that reads the two JSON schemas present in the resource folder
and the rest of the verification is done as shown in slides regarding JSON schema.

For the Logging part, we used the Log4j library to log what our class is doing in any given time, ensuring that in case of an exception the exception gets logged and we can see what went wrong. We wanted the error messages to be 
as precise as possible, and to do that we made our class for logging, MyLogger, which contains an object of the Logger class found in the Log4j library and an object of the Gson class found in Gson, we used the toJson() method of the gson object to parse the objects passed in the methods of MyLogger and logged using the Logger object. 

For the Unit Testing of the Program, we used JUnit and tested the  ActivityParser, Activity, Analysis, FileManager and JsonSchemaValidator classes. The classes have been tested for the various parts to check whether the methods return the expected output for the program to run properly.

### Challenges Throughout the Project

**Alberto**

From my point of view, the main challenge was to condense all knowledge that we gathered along the course into straight coding, I had to experiment and try many things for the first time, since I wasn't able to be present to all labs. I found out some big mistakes of mine, by just trying and with the help of my teammates I was able to figure out what I needed to do and how. 
Some mistakes were, for example, using the "wrong" IDE (I switched from Eclipse to Intellij ) or the fact that I had never installed maven on the pc, some pretty basic stuff but that I had never figured out. Sometimes I had to go through the slides of the lecture or the recordings of the lecture just to find out what I did wrong or to copy a strategy or a piece of code I wanted to use. All in all, it has been a challenging project, but it has also been a lot of fun, and I am proud of code we produced altogether.

**Balawal**

For me, the biggest challenge I faced was to write classes that are easy to UnitTest and easy to understand. At the start of this project, I started writing classes that were able to accomplish their goals, but these classes had a common problem, most of the methods in these classes were over 100 lines of code, and not clear in what they were doing.

At that point in time, I had completely forgotten that I would have to provide UnitTesting for these classes.
And after a while came the time to start UnitTesting these classes, luckily for me, my teammates made me see
my classes for the abomination that they were. And so my teammates and I refactored a sizable portion of our code, after refactoring the code we were finally able to write tests for our classes without going doing mental gymnastics.

What I learned from this is that writing test cases for a class without implementing it isn't such a bad idea after all.