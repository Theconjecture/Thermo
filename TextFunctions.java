import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class TextFunctions {
	
	Scanner scan = new Scanner(System.in);
	
	public String getFileName() {
		System.out.print("Enter the file name: ");
		return scan.next();
	}
		
	public boolean createFile() {

		boolean ok = false;
		
		try {
			File file = new File(getFileName()+".txt");
			if(file.createNewFile())
			{
				ok=true;
				System.out.println("File created : "+ file.getName());
			}
			else 
			{
				System.out.println("File already exists!!");
			}
		}
		
		catch(IOException e) {
			System.out.println("An error occured!!");
			System.out.println(e.getStackTrace());
		}

		return ok;
	}

	public void writeData(String record) {
		try {
			FileWriter data = new FileWriter(getFileName()+".txt",true); // To append a file set the append flag to true
			data.write(record+"\n");										   // This is next to the filename initiating the object 
			data.close();
		}

		catch(Exception e) {
			System.out.println("Error occured!!");
			System.out.println(e.getMessage());
		} 

	}

	public void readData() {
		
		try {
			FileReader data = new FileReader(getFileName()+".txt");	// create our reader object
			
			while(data.read()!=-1) {							//.read returns ASCII values of charecters, so we can convert to char,\
				System.out.print((char)data.read());							// will return -1 at the end of the file	
			}
			data.close();
		}
		
		catch(IOException e) {
			e.printStackTrace();
		}
	}

	public void findMatchingChar(char x, int position) {

		try {
			FileReader fi = new FileReader(getFileName()+".txt");
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

}
