User Guide:

**WARNING**
SET UP ENVRIONMENT
1. ensure the residents list is in the proper format (nusnet, matric, name, gender)
2. ensure the cca list is in the proper format:
	Sheet 1: nusnet, matric, name, gender, semster, category, cca name, attendance, performance, outstanding
	Sheet 2: nusnet, matric, name, bonus points, description
3. ensure no passwords in the excel files. [will allow encrypted files in V2.0]

To run the programme:
Compile all the components together as a package or alternatively for now run ExcelReader.java in intellij
Are we making a jar executable file for this im too lazy
no

After gettting the programme to run
1. enter the resident list file name. E.g sheet/testresidents.xlsx
2. keep entering cca list file names. E.g sheet/testdata1.xlsx
3. after all the ccas have been processed, enter any of the commands supported to make it do work


Commands supported: 
print - prints out the student list into console
printtoexcel - prints out the students nusnet and matric and totalpoints to an excel file
-1 - terminates the programme
printtojson - prints out the students as a json object 



Dev Guide:
sorry we're not paid enough to do this. go figure out the code yourself