import java.sql.*; 
import java.io.File;
import java.io.IOException;
import java.io.FileWriter;
import java.io.FileReader;
import java.util.Scanner;

public class Reader{ 


	public boolean createFile() {

		boolean ok = false;
		try {
			File file = new File("ThermoResults.txt");
			if(file.createNewFile())
			{
				ok=true;
				System.out.println("File created : "+ file.getName());
			}
			else 
			{
				System.out.println("File already exists!!");
				if(file.delete())
					System.out.println("File now is deleted...");

			}
		}
		catch(IOException e) {
			System.out.println("An error occured!!");
			System.out.println(e.getStackTrace());
		}

		return ok;
	}

	public void writeData(String value) {
		try {
			FileWriter data = new FileWriter("ThermoResults.txt",true); // To append a file set the append flag to true
			data.write(value);											// This is next to the filename initiating the object (end of line 38)
			System.out.println("Data written into the file.");
			data.close();
		}

		catch(Exception e) {
			System.out.println("Error occured!!");
			System.out.println(e.getMessage());
		} 

	}
	
	public void readData() {
		try {
			FileReader data = new FileReader("ThermoResults.txt");	// create our reader object
			int i;
			while((i=data.read())!=-1) {							//.read returns ASCII values of charecters, so we can convert to char,\
				System.out.print((char)i);							// will return -1 at the end of the file	
			}
			data.close();
			System.out.println("\nHere is the inputed value ");
		}
		catch(IOException e) {
			e.printStackTrace();
		}
	}

	public void findMatchingChar(char x, int position) {

		try {
			FileReader fi = new FileReader("ThermoResults.txt");
			Scanner scan = new Scanner(fi);
			char i = x;
			while(scan.hasNextLine()) {
				String line = scan.nextLine();
				if(line.charAt(position)==i)
					System.out.println(line);
			}
			fi.close();
			scan.close();
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	public void findMatchingString(String x, int startIndex , int endIndex) {
		try {
			File fi = new File("ThermoResults.txt");
			Scanner scan = new Scanner(fi);
			while(scan.hasNextLine()) {
				String line = scan.nextLine();
				if(line.substring(startIndex, endIndex+1).equals(x))
					System.out.println(line);
			}
			scan.close();
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	

	public static void main(String[] args)
	{
		
		//DatabaseReader machine = new DatabaseReader();
		//machine.createFile();
		try
		{
			// create our mysql database connection
			//String myDriver = "org.gjt.mm.mysql.Driver";
			String myUrl = "jdbc:mysql://127.0.0.1:3306/?user=root/ThermodynamicsTables";
			//Class.forName(myDriver);
			Connection conn = DriverManager.getConnection(myUrl, "root", "Matta/9991");

			// our SQL SELECT query. 
			// if you only need a few columns, specify them by name instead of using "*"
			String query = "SELECT * FROM ThermodynamicsTables.C1";

			// create the java statement
			Statement st = conn.createStatement();

			// execute the query, and get a java resultset
			ResultSet rs = st.executeQuery(query);

			// iterate through the java resultset
			while (rs.next())
			{
				float Temp = rs.getFloat(1);
				float press = rs.getFloat(2);
				float VolumeF = rs.getFloat(3);
				float VolumeG = rs.getFloat(4);
				float EnergyF = rs.getFloat(5);
				float EnergyG = rs.getFloat(6);
				float EnthalpyF = rs.getFloat(7);
				float EnthalpyFG = rs.getFloat(8);
				float EnthalpyG = rs.getFloat(9);
				float EntropyF = rs.getFloat(10);
				float EntropyFG = rs.getFloat(11);
				float EntropyG = rs.getFloat(12);
				
//				 String value = String.valueOf(Temp)+","+String.valueOf(press)+","+String.valueOf(VolumeF)+","+String.valueOf(VolumeG)+","+
//				 String.valueOf(EnergyF)+","+String.valueOf(EnergyG)+","+String.valueOf(EnthalpyF)+","+String.valueOf(EnthalpyFG)+","+
//						 String.valueOf(EnthalpyG)+","+String.valueOf(EntropyF)+","+String.valueOf(EntropyFG)+","+String.valueOf(EntropyG);
				// print the results
				System.out.format("%-5s, %-6s, %-6s, %-6s, %-6s, %-6s ,%-6s ,%-6s ,%-6s ,%-6s ,%-6s ,%-6s \n",
						Temp, press, VolumeF, VolumeG, EnergyF, EnergyG, EnthalpyF,
						EnthalpyFG, EnthalpyG, EntropyF, EntropyFG, EntropyG);
			
					//machine.writeData(value+"\n");
						}
			st.close();
		}
		catch (Exception e)
		{
			System.err.println("Got an exception! ");
			System.err.println(e.getMessage());
		}
	}
}