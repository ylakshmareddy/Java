import java.util.*;
import java.sql.*;

class CRUDMySQL implements CRUD
{
	Connection connection;
	String tableName = "Bank";
	String[] columnNames = {"Account Number", "Name", "Mobile Number"};
	int countOfColumns = columnNames.length;

	public CRUDMySQL()
	{
		String url = "jdbc:mysql://165.22.14.77/dbLakshmaReddy?A" + "autoReconnect=true&useSSL=false";
		String userName = "LakshmaReddy";
		String password = "LakshmaReddy";
		try
		{  
			Class.forName("com.mysql.jdbc.Driver");  
			connection = DriverManager.getConnection(url, userName, password); 
		}
		catch(Exception e)
		{ 
			System.out.println(e);
		}
	}
	public void create()
	{
		Scanner scanner = new Scanner(System.in);
		ArrayList<String>columnValues = new ArrayList<String>();
		for(int columnCounter = 0; columnCounter < countOfColumns; columnCounter++)
		{
			System.out.print("Enter " + columnNames[columnCounter] + ": ");
			columnValues.add(scanner.next());
		}
		String sqlInsertQuery = "INSERT INTO " + tableName + " VALUES (?,?,?)";
		try
		{
			PreparedStatement preStmt = connection.prepareStatement(sqlInsertQuery);
			for(int columnCounter = 0; columnCounter < countOfColumns; columnCounter++)
			{
				preStmt.setString(columnCounter + 1, columnValues.get(columnCounter));
			}
			preStmt.executeUpdate();
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
	}
	public void read()
	{
		String sqlQuery = "SELECT * FROM " + tableName;
		try
		{
			Statement stmt = connection.createStatement();
			ResultSet resultSet = stmt.executeQuery(sqlQuery);
			if(!resultSet.next())
			{
				System.out.println("No item details found.");
			}
			else
			{
				for(int rowCounter = 1; resultSet.absolute(rowCounter); rowCounter++)
				{
					for(int columnCounter = 0; columnCounter < countOfColumns; columnCounter++)
					{
						System.out.format("%-15s%s%s",columnNames[columnCounter], ": ", resultSet.getString(columnCounter + 1));
						System.out.println();
					}
				}	
			}
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
	}
}