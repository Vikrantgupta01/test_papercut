1) List of changes to run the application.
    a) create a folder for test input files and copy all files from $ProjectPath/doc/testdata
    b) update "filepath" property in fileinformation.properties file according to your folder location.
    c) update "log4j.appender.logfile.File" in log4j.properties file according to desired log file location in  your system



2) How to run programme.
   - Run the main programme of Client class. which reads the file from defined location and process it.
     OR
   - Use mvn test command to run multiple scenario (defined by several input files)



3) How its scalable to support different page size.
  - Taking an example if in future need to add support for A3 paper size.
  - Assumption ( paper type will be 4th input of job)
   a)Need to make changes in JobReaderImpl to set paper type in job information by using enums class PaperType (considering setting default value A4 ).
   b)Need to add following properties in "printing_cost.properties" file with respective values.
     A3_SINGLE_BW
     A3_SINGLE_CLR
     A3_DOUBLE_BW
     A3_DOUBLE_CLR




