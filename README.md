# South Tyrol activities analyzer

This Java application uses the [Open Data Hub](https://opendatahub.bz.it/) to retrieve information about various activities that can be performed in South Tyrol, and stores the data into compact JSON files.

## Application Architecture
To read the architecture open the [report.md](https://github.com/BalawalSultan/ProgrammingProject/blob/master/REPORT.md) file.

## Installation

Use [Maven](https://maven.apache.org/) to prepare the application.

```bash
mvn install compile package
```

## Usage

In order for the application to run an input file containing the number of activities to retrieve must be provided.
The input file must be called input.txt and must be placed in the resources folder.

The input.txt file must contain only the number of activities to retrieve here is an example.
```bash
100
```

### How to run the application

Once the input file is ready use the following command to run the application.

```bash
mvn clean package exec:exec
```

### How to test the application

To run the unit test on the application use the following command.

```bash
mvn test
```
