## Generate Car Number Plates 
1. Generate random number plates into xml files for each of the 5 stations every 5 mins
2. Extract data from the generated xml files and save it into a database

## How to set up
Set up the database. SQL dump is on the repo. 
Edit `SaveToDatabas.java` around line 15 `conn = DriverManager.getConnection("jdbc:mysql://localhost/tms_viewer?" + "user=root&password=");` to connect  to your database and execute!                  