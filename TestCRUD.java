import java.util.Scanner;

class TestCRUD
{
	public static void main(String [] args)
	{
		Scanner scanner = new Scanner(System.in);
		String className = null;
		if(args.length == 1)
		{
			className = args[0];
		}
		else
		{
			System.out.print("Enter class name: ");
			className = scanner.next();
		}
		try
		{
			iCRUD crud = (iCRUD)Class.forName(className).newInstance();
			System.out.println("Welcome\n1. create.\n2. read.");
			System.out.print("Enter your choice: ");
			int userChoice = scanner.nextInt();
			if(userChoice == 1)
			{
				crud.create();
			}
			else if(userChoice == 2)
			{
				crud.read();
			}
			else
			{
				printInvalidChoiceMessage();
			}
			
		}
		catch(Exception e)
		{
			scanner.next();
			printInvalidChoiceMessage();
		}
	}
	public static void printInvalidChoiceMessage()
	{
		System.out.println("Invalid choice.");
	}
}