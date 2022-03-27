import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.File;
import java.io.PrintWriter;

public class UserUI {
	private String[][] userInfo = new String[10][6];
	private int size = 0;
	
	//method for reading in the info from the text file
	private void readFile(String file)
	{	
		int countRow = 0;
		try {
			Scanner sc2 = new Scanner(new File(file));
			while (sc2.hasNextLine()) {
				int countColumn = 0;
		        Scanner s2 = new Scanner(sc2.nextLine());
		        while (s2.hasNext()) {
		            userInfo [countRow][countColumn] = s2.next();
		            countColumn++;
		        }
		        countRow++;
		        s2.close();
		    }
			sc2.close();
		}
		catch(FileNotFoundException e)
		{
			e.printStackTrace();
			System.out.println("incorrect file name");
		}
		size = countRow;
	}
	//method for writing out to the file
	private void writeFile(String file)
	{
		try {
			PrintWriter printWriter = new PrintWriter (new File(file));
			for(int i = 0;i < size;i++)
			{
				for(int j = 0;j < 6;j++)
				{
					printWriter.print(userInfo[i][j] + " ");
				}
				printWriter.println("");
			}
			printWriter.close();
		}
		catch(FileNotFoundException e)
		{
			e.printStackTrace();
			System.out.println("incorrect file name");
		}
	}
	//method for finding the user
	private int searchUser(String userName)
	{
		for(int i = 0;i < userInfo.length;i++)
		{
			if(userInfo[i][0].equals(userName))
			{
				return i;
			}
		}
		return -1;
	}
	
	public static void main(String args[]) {
		//create instances of the classes
		Main main = new Main();
		UserUI test = new UserUI();
		//create local variables
		boolean isUpdated = false;
		int account = -1;
		test.readFile("NutritionTrackerAccounts.txt");
		Scanner scan = new Scanner(System.in);
		//log in if they have an account
		System.out.println("Do you have an account already? ('Yes' or 'No')");
		if(scan.next().equalsIgnoreCase("yes"))
		{
			boolean search = true;
			while (search == true)
			{
				System.out.println("What is your UserName?");
				account = test.searchUser(scan.next());
				if(account == -1)
				{
					System.out.println("UserName entered is incorrect, would you like to try again?('Yes' or 'No')");
					if(!scan.next().equalsIgnoreCase("yes"))
					{
						search = false;
					}
				}
				else {
					System.out.println("What is your password?");
					if(scan.next().equals(test.userInfo[account][1]))
					{
						search = false;
					}
					else
					{
						account = -1;
						System.out.println("UserName or password are incorrect, please try again");
					}
				}
			}
			//if they have an account, ask if they want to update it
			if(account != -1)
			{
				boolean update = true;
				while(update == true)
				{
					System.out.println("Do you want to update any information on your account?('height' or 'weight' or 'sex' or 'age' or 'no')");
					String updateInfo = scan.next();
					switch(updateInfo)
					{
					case "height":System.out.println("What is your new height?(inches)");
						test.userInfo [account][2] = scan.next();
						isUpdated = true;
						break;
					case "weight":System.out.println("What is your new weight?(pounds)");
						test.userInfo [account][3] = scan.next();
						isUpdated = true;
						break;
					case "sex":System.out.println("What is your new sex?(Male or Female)");
						test.userInfo [account][4] = scan.next();
						isUpdated = true;
						break;
					case "age":System.out.println("What is your new age?(years)");
						test.userInfo [account][5] = scan.next();
						isUpdated = true;
						break;
					case"no":update = false;
					}
				}
			}
			//return specific nutrition info if user has account, otherwise use the general methods
			if(account != -1)
			{
				System.out.println("How many calories have you eaten today?");
				int cals = main.calcCalories(Integer.parseInt(test.userInfo[account][3]),test.userInfo[account][4],scan.nextInt());
				if(cals > 0)
				{
					System.out.println("You should eat " + cals + " more calories");
				}
				else if (cals < 0)
				{
					System.out.println("You should eat " + Math.abs(cals) + " less calories");
				}
				else
				{
					System.out.println("You ate the right amount of calories");
				}
				System.out.println("How much protein have you eaten today?(g)");
				int pro = main.calcProtein(Integer.parseInt(test.userInfo[account][3]), scan.nextInt());
				if(pro > 0)
				{
					System.out.println("You should eat " + pro + " more grams of protein");
				}
				else if (pro < 0)
				{
					System.out.println("You should eat " + Math.abs(pro) + " less grams of protein");
				}
				else
				{
					System.out.println("You ate the right amount of protein");
				}
				main.getSodium();
				System.out.println("How much sugar have you eaten today?(g)");
				int sug = main.calcSugar(test.userInfo[account][4],scan.nextInt());
				if(sug > 0)
				{
					System.out.println("You should eat " + sug + " less grams of sugar");
				}
				else
				{
					System.out.println("You ate the right amount of sugar");
				}
				System.out.println("How much carbohydrates have you eaten today?(g)");
				int carb = main.calcCarbohydrates(main.calcCalories(Integer.parseInt(test.userInfo[account][3]),test.userInfo[account][4],cals),scan.nextInt());
				if(carb > 0)
				{
					System.out.println("You should eat " + carb + " more grams of carbohydrates");
				}
				else if (carb < 0)
				{
					System.out.println("You should eat " + Math.abs(carb) + " less grams of carbohydrates");
				}
				else
				{
					System.out.println("You ate the right amount of carbohydrates");
				}
			}
			else
			{
				main.getCalories();
				main.getCarbohydrates();
				main.getSodium();
				main.getProtein();
				main.getSugar();
			}
		}
		//if they do not have an account, ask if they would like to make one
		else 
		{
			System.out.println("Would you want to make an account?('Yes' or 'No')");
			if(scan.next().equalsIgnoreCase("yes"))
			{
				while(account == -1)
				{
					if(test.size >= 10)
					{
						System.out.println("Account Storage is full, what is the UserName of the file you want to replace?");
						account = test.searchUser(scan.next());
					}
					else
					{
						account = test.size;
						test.size++;
					}
				}
				System.out.println("What is your username?");
				test.userInfo[account][0] = scan.next();
				System.out.println("What is your password?");
				test.userInfo[account][1] = scan.next();
				System.out.println("What is your height?(inches)");
				test.userInfo[account][2] = scan.next();
				System.out.println("What is your weight?(pounds)");
				test.userInfo[account][3] = scan.next();
				System.out.println("What is your sex?(Male or Female");
				test.userInfo[account][4] = scan.next();
				System.out.println("What is your age?(years)");
				test.userInfo[account][5] = scan.next();
				isUpdated = true;
			}
		}
		//if they created an account, return specific information, if not then return general info
		if(account != -1)
		{
			System.out.println("How many calories have you eaten today?");
			int cals = main.calcCalories(Integer.parseInt(test.userInfo[account][3]),test.userInfo[account][4],scan.nextInt());
			if(cals > 0)
			{
				System.out.println("You should eat " + cals + " more calories");
			}
			else if (cals < 0)
			{
				System.out.println("You should eat " + Math.abs(cals) + " less calories");
			}
			else
			{
				System.out.println("You ate the right amount of calories");
			}
			System.out.println("How much protein have you eaten today?(g)");
			int pro = main.calcProtein(Integer.parseInt(test.userInfo[account][3]), scan.nextInt());
			if(pro > 0)
			{
				System.out.println("You should eat " + pro + " more grams of protein");
			}
			else if (pro < 0)
			{
				System.out.println("You should eat " + Math.abs(pro) + " less grams of protein");
			}
			else
			{
				System.out.println("You ate the right amount of protein");
			}
			main.getSodium();
			System.out.println("How much sugar have you eaten today?(g)");
			int sug = main.calcSugar(test.userInfo[account][4],scan.nextInt());
			if(sug > 0)
			{
				System.out.println("You should eat " + sug + " less grams of sugar");
			}
			else
			{
				System.out.println("You ate the right amount of sugar");
			}
			System.out.println("How much carbohydrates have you eaten today?(g)");
			int carb = main.calcCarbohydrates(main.calcCalories(Integer.parseInt(test.userInfo[account][3]),test.userInfo[account][4],cals),scan.nextInt());
			if(carb > 0)
			{
				System.out.println("You should eat " + carb + " more grams of carbohydrates");
			}
			else if (carb < 0)
			{
				System.out.println("You should eat " + Math.abs(carb) + " less grams of carbohydrates");
			}
			else
			{
				System.out.println("You ate the right amount of carbohydrates");
			}
		}
		else
		{
			main.getCalories();
			main.getCarbohydrates();
			main.getSodium();
			main.getProtein();
			main.getSugar();
		}
		//close scanner and update text file if needed
		scan.close();
		if(isUpdated == true)
		{
			test.writeFile("NutritionTrackerAccounts.txt");
		}
	}
}
