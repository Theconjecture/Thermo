import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.Scanner;

public class DBFunctions {

	Scanner scan = new Scanner(System.in);

	public String getUserName() {
		System.out.print("Enter Username:");
		return scan.next();
	}

	public String getPassword() {
		System.out.print("Enter Password:");
		return scan.next();
	}
	
	public String createRecord(ResultSet rs) {
		
		String record = "";
		
		try {
			ResultSetMetaData md = rs.getMetaData(); 
			int colCount = md.getColumnCount(); 
			for(int i=2; i<=colCount; i++)							//There is a primary auto increment key in the
				record = record + "," + rs.getString(i);			//database so we will skip the first column
		}
		
		catch (Exception e)
		{
			System.err.println("Got an exception! ");
			System.err.println(e.getMessage());
		}
		return record;
	}

	public void printAll(ResultSet rs) {

		try {
			ResultSetMetaData md = rs.getMetaData(); 
			int colCount = md.getColumnCount(); 
			
			while (rs.next())
			{
				for(int i = 2; i<=colCount; i++) {
					System.out.print(rs.getString(i)+",");
					if (i==colCount)
						System.out.print(rs.getString(i));
				}
				System.out.println();
			}
		}
		
		catch (Exception e)
		{
			System.err.println("Got an exception! ");
			e.printStackTrace();
		}
	}
	
	public void read() {

		try
		{
			// create our mysql database connection
			
			Connection conn = DriverManager.getConnection("Path", 
					getUserName(), getPassword());

			// our SQL SELECT query. 

			String query = "SELECT * FROM Thermo_Tables.T1";

			// create the java statement
			
			Statement st = conn.createStatement();

			// execute the query, and get a java result set
			
			ResultSet rs = st.executeQuery(query);

			// iterate through the java result set
			printAll(rs);
			
			
			st.close();
		}
		catch (Exception e)
		{
			System.err.println("Got an exception! ");
			System.err.println(e.getMessage());
		}
	}

}
