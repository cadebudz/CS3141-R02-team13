//JavaFX stuff
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.File;
import java.io.PrintWriter;

public class UserUI extends Application{
	private String[][] userInfo = new String[10][6];
	private int size = 0;
	private int user = 0;
	
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
			//No file exists so one is created
			try{
				PrintWriter printWriter = new PrintWriter (new File(file));
			}catch (FileNotFoundException f){

			}

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
		for(int i = 0;i < size;i++)
		{
			if(userInfo[i][0].equals(userName))
			{
				user = i;
				return i;
			}
		}
		user = -1;
		return -1;
	}


	public static void main(String args[]) {
		launch(args); //JavaFX
		//Prevents old code from running because operations need to be performed by JavaFX
		System.exit(69);
		//create instances of the classes
		Main main = new Main();
		UserUI test = new UserUI();
		//create local variables
		boolean isUpdated = false;
		int account = -1;  //
		test.readFile("NutritionTrackerAccounts.txt"); //
		Scanner scan = new Scanner(System.in);
		//log in if they have an account
		System.out.println("Do you have an account already? ('Yes' or 'No')");
		if(scan.next().equalsIgnoreCase("yes"))

		{
			//Finished moving
			/*
			boolean search = true;
			while (search == true)
			{
				System.out.println("What is your UserName?");
				//account = test.searchUser(scan.next());
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
			}*/
			//Finished moving
			//if they have an account, ask if they want to update it
			/*if(account != -1)
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
			}*/
			//TODO return specific nutrition info if user has account, otherwise use the general methods
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
				//main.getSodium();
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
			/*else
			{

				main.getCalories();
				main.getCarbohydrates();
				main.getSodium();
				main.getProtein();
				main.getSugar();
			}*/
		}
		//Finished moving
		//if they do not have an account, ask if they would like to make one
		/*else
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
		}*/
		//Finished moving
		//close scanner and update text file if needed
		/*scan.close();
		if(isUpdated == true)
		{
			test.writeFile("NutritionTrackerAccounts.txt");
		}*/
	}

	//Main JavaFX code
	@Override
	public void start(Stage primaryStage) throws Exception {
		//General information
		UserUI test = new UserUI();
		test.readFile("NutritionTrackerAccounts.txt");
		BorderPane mainLayout = new BorderPane();
		Scene mainScene = new Scene(mainLayout,960,540);

		//Scenes
		/*
		Scene fillableScene; //Page where nutrition information can be entered
		//Scene updateAccountScene; //Page where personal information can be updated (username, password)
		Scene nutritionResultsScene; //Page where nutrition results are displayed*/

		//Sets up information fill page
		VBox fillableLayout = new VBox(20);
		fillableLayout.setAlignment(Pos.CENTER);
		fillableLayout.getChildren().addAll();



		//Sets up account creation page
		primaryStage.setTitle("Nutrition Tracker - Account Creation");
		//Title
		Label createHeader = new Label("Account Creation");
		createHeader.setFont(new Font("Arial",30));
		//warning label
		Label creationWarningLabel = new Label();
		creationWarningLabel.setStyle("-fx-text-fill: #d91212");
		//Username field
		TextField newUsername = new TextField();
		newUsername.setPromptText("Enter Username:");
		newUsername.setMaxWidth(300);
		//Password field
		TextField newPassword = new TextField();
		newPassword.setPromptText("Enter Password:");
		newPassword.setMaxWidth(300);
		//Height field
		TextField newHeight = new TextField();
		newHeight.setPromptText("Enter Height (inches):");
		newHeight.setMaxWidth(300);
		//Weight field
		TextField newWeight = new TextField();
		newWeight.setPromptText("Enter weight (pounds):");
		newWeight.setMaxWidth(300);
		//Sex field
		ComboBox Sex = new ComboBox();
		Sex.getItems().add("Male");
		Sex.getItems().add("Female");
		//Age field
		TextField newAge = new TextField();
		newAge.setPromptText("Enter Age:");
		newAge.setMaxWidth(300);
		//Create account button
		Button createAccount = new Button("Create an Account");
		createAccount.setStyle("-fx-background-color: rgba(0,0,0,0)");
		createAccount.setStyle("-fx-text-fill: #2929f3");
		createAccount.setOnAction(e -> {
			if(newUsername.getText().equals("")){
				creationWarningLabel.setText("Username is empty");
				return;
			}else if(searchUser(newUsername.getText())!=-1){
				creationWarningLabel.setText("Username already exists");
				return;
			}else if(newPassword.getText().equals("")){
				creationWarningLabel.setText("Password is empty");
				return;
			}else if(newHeight.getText().equals("")){
				creationWarningLabel.setText("Height is empty");
				return;
			}else if(isInt(newHeight.getText())==false){
				creationWarningLabel.setText("Height is not a valid number");
				return;
			}else if(newWeight.getText().equals("")){
				creationWarningLabel.setText("Weight is empty");
				return;
			}else if(isInt(newWeight.getText())==false){
				creationWarningLabel.setText("Weight is not a valid number");
				return;
			}else if(Sex.getSelectionModel().isEmpty()){
				creationWarningLabel.setText("Sex is empty");
				return;
			}else if(newAge.getText().equals("")){
				creationWarningLabel.setText("Age is empty");
				return;
			}else if(isInt(newAge.getText())==false){
				creationWarningLabel.setText("Age is not a valid number");
				return;
			}
			//TODO fix rewriting first line
			test.userInfo[test.user][0] = newUsername.getText();
			test.userInfo[test.user][1] = newPassword.getText();
			test.userInfo[test.user][2] = newHeight.getText();
			test.userInfo[test.user][3] = newWeight.getText();
			test.userInfo[test.user][4] = Sex.getValue()+"";
			test.userInfo[test.user][5] = newAge.getText();
			test.writeFile("NutritionTrackerAccounts.txt");
		});
		VBox accountCreationLayout = new VBox(20);
		accountCreationLayout.setAlignment(Pos.CENTER);
		accountCreationLayout.getChildren().addAll(createHeader,creationWarningLabel,newUsername,newPassword,newHeight,newWeight,Sex,newAge,createAccount);

		//Sets up account creation page
		primaryStage.setTitle("Nutrition Tracker - Account Update");
		//TODO unames can't be set until user is set
		//Title
		Label updateHeader = new Label("Edit information you wish to change");
		updateHeader.setFont(new Font("Arial",18));
		//warning label
		Label updateWarningLabel = new Label();
		updateWarningLabel.setStyle("-fx-text-fill: #d91212");
		//Username field
		System.out.println(user);
		TextField updateUsername = new TextField();
		updateUsername.setPromptText("Update username: "+test.userInfo[test.user][0]);
		updateUsername.setMaxWidth(300);
		//Password field
		TextField updatePassword = new TextField();
		updatePassword.setPromptText("Update password: "+test.userInfo[test.user][1]);
		updatePassword.setMaxWidth(300);
		//Height field
		TextField updateHeight = new TextField();
		updateHeight.setPromptText("Update height (inches): "+test.userInfo[test.user][2]);
		updateHeight.setMaxWidth(300);
		//Weight field
		TextField updateWeight = new TextField();
		updateWeight.setPromptText("Update weight (pounds): "+test.userInfo[test.user][3]);
		updateWeight.setMaxWidth(300);
		//Sex field
		ComboBox updateSex = new ComboBox();
		updateSex.getItems().add("Male");
		updateSex.getItems().add("Female");
		if(test.userInfo[test.user][4].equals("Male")){
			updateSex.getSelectionModel().select(0);
		}else{
			updateSex.getSelectionModel().select(1);
		}
		//Age field
		TextField updateAge = new TextField();
		updateAge.setPromptText("Update age: "+test.userInfo[test.user][5]);
		updateAge.setMaxWidth(300);
		//Create account button
		Button updateAccount = new Button("Update Account");
		updateAccount.setStyle("-fx-background-color: rgba(0,0,0,0)");
		updateAccount.setStyle("-fx-text-fill: #2929f3");
		updateAccount.setOnAction(e -> {
			//TODO use correct line
			if(searchUser(newUsername.getText())!=-1){
				creationWarningLabel.setText("Username already exists");
				return;
			}else if(isInt(newHeight.getText())==false){
				creationWarningLabel.setText("Height is not a valid number");
				return;
			}else if(isInt(newWeight.getText())==false){
				creationWarningLabel.setText("Weight is not a valid number");
				return;
			}else if(isInt(newAge.getText())==false){
				creationWarningLabel.setText("Age is not a valid number");
				return;
			}
			//TODO add information
			if(!(newUsername.getText().equals(""))){
				test.userInfo[0][0] = newUsername.getText();
			}else if(!(newPassword.getText().equals(""))){
				test.userInfo[0][1] = newPassword.getText();
			}else if(!(newHeight.getText().equals(""))){
				test.userInfo[0][2] = newHeight.getText();
			}else if(!(newWeight.getText().equals(""))){
				test.userInfo[0][3] = newWeight.getText();
			}else if(!(Sex.getSelectionModel().isEmpty())){
				test.userInfo[0][4] = Sex.getValue()+"";
			}else if(!(newAge.getText().equals(""))){
				test.userInfo[0][5] = newAge.getText();
			}
			test.writeFile("NutritionTrackerAccounts.txt");
		});
		VBox accountEditLayout = new VBox(20);
		accountEditLayout.setAlignment(Pos.CENTER);
		accountEditLayout.getChildren().addAll(updateHeader,updateWarningLabel,updateUsername,updatePassword,updateHeight,updateWeight,updateSex,updateAge,updateAccount);


		//Sets up login page
		primaryStage.setTitle("Nutrition Tracker - Login");
		//Title
		Label loginHeader = new Label("Login");
		loginHeader.setFont(new Font("Arial",30));
		//warning label
		Label loginWarningLabel = new Label();
		loginWarningLabel.setStyle("-fx-text-fill: #d91212");
		//Username field
		TextField loginUsername = new TextField();
		loginUsername.setPromptText("Enter Username:");
		loginUsername.setMaxWidth(300);
		//Password field
		TextField loginPassword = new TextField();
		loginPassword.setPromptText("Enter Password:");
		loginPassword.setMaxWidth(300);
		//Enter button
		Button loginButton = new Button("Enter");
		//Enter button functionality (checking username and password)
		loginButton.setOnAction(e -> {
			if(loginUsername.getText().equals("")){
				loginWarningLabel.setText("No username entered");
				return;
			}else if(loginPassword.getText().equals("")){
				loginWarningLabel.setText("No password entered");
				return;
			}else{
				int account = -1;
				account = test.searchUser(loginUsername.getText());
				if(account == -1){
					loginWarningLabel.setText("No matching username found");
					return;
				}
				if(loginPassword.getText().equals(test.userInfo[account][1])){
					//Password matches
					//Popup
					BorderPane popupPane = new BorderPane(); //Border pane
					HBox update = new HBox(20); //HBox for center
					Button yes = new Button("Yes");

					Button no = new Button("No");

					update.setAlignment(Pos.CENTER);
					update.getChildren().addAll(yes,no);
					HBox header = new HBox(20); //VBox for header
					Label popupHeader = new Label("Do you want to update your information?");
					header.getChildren().add(popupHeader);
					header.setAlignment(Pos.CENTER);
					popupPane.setTop(header);
					popupPane.setCenter(update); //Combining HBox and border pane
					Scene popupCenter = new Scene(popupPane,300,100); //Creates scene
					Stage popupBorder = new Stage(); //Creates stage
					popupBorder.setScene(popupCenter); //Applies scene to stage
					yes.setOnAction(f -> {
						mainLayout.setCenter(accountEditLayout);
						popupBorder.close();
					});
					popupBorder.setTitle("Edit");
					//TODO update when you have account information
					//no.setOnAction(f -> mainLayout.setCenter(accountCreationLayout));
					popupBorder.show(); //Shows stage
				}else{
					loginWarningLabel.setText("Incorrect password");
					return;
				}
			}
		});
		//Create account button functionality
		Button createNewAccount = new Button("Create an Account");
		createNewAccount.setStyle("-fx-background-color: rgba(0,0,0,0)");
		createNewAccount.setStyle("-fx-text-fill: #2929f3");
		createNewAccount.setOnAction(e -> mainLayout.setCenter(accountCreationLayout));
		VBox loginLayout = new VBox(20);
		loginLayout.setAlignment(Pos.CENTER);
		loginLayout.getChildren().addAll(loginHeader,loginWarningLabel,loginUsername,loginPassword,loginButton,createNewAccount);


		
		//Combines pieces and shows window
		primaryStage.setScene(mainScene);
		mainLayout.setCenter(loginLayout);

		primaryStage.show();
	}

	//Methods for checking integers
	private boolean isInt(String message){
		try{
			int integer = Integer.parseInt(message);
			return true;
		}catch (NumberFormatException e){
			return false;
		}
	}
}
