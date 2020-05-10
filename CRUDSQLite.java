import java.sql.*;
import java.util.*;

class CRUDSQLite implements iCRUD 
{
	Connection connection;
	String tableName = "PassenegerDetails";
	String [] columnNames = {"PNR Number", "Name", "Mobile Number"};
	int countOfColumns = columnNames.length;
	public CRUDSQLite()
	{
		try
		{
			Class.forName("org.sqlite.JDBC");
			connection = DriverManager.getConnection("jdbc:sqlite:test.db");
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
			int columnCounter = 0;
			while(resultSet.next())
			{
				for( ; columnCounter < countOfColumns; columnCounter++)
				{
					System.out.format("%-15s%s%s",columnNames[columnCounter], ": ", resultSet.getString(columnCounter + 1));
					System.out.println();
				}
			}
			if(columnCounter == 0)
			{
				System.out.println("No records found in the given table.");
			}

		}
		catch(Exception e)
		{
			System.out.println(e);
		}
	}
}






