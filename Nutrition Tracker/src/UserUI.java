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

import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.File;
import java.io.PrintWriter;

public class UserUI extends Application{
	private String[][] userInfo = new String[10][6];
	private int size;
	private int user;
	
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
	}

	//Main JavaFX code
	@Override
	public void start(Stage primaryStage) throws Exception {
		//General information
		UserUI test = new UserUI();
		Main main = new Main();
		test.readFile("NutritionTrackerAccounts.txt");
		BorderPane mainLayout = new BorderPane();
		Scene mainScene = new Scene(mainLayout,960,540);

		//Declare login at beginning so it can be called from any page
		VBox loginLayout = new VBox(20);

		//TODO exit confirmation
		//TODO continue without account
		//TODO return to login page
		//TODO check for invalid characters
		//TODO clear old messages

		//Sets up results page
		//Title
		Label resultsHeader = new Label("Results");
		resultsHeader.setFont(new Font("Arial",30));
		//warning results
		Label resultsWarningLabel = new Label();
		resultsWarningLabel.setStyle("-fx-text-fill: #d91212");
		//Calories results
		Label resultsCalories = new Label();
		//Sodium results
		Label resultsSodium = new Label();
		//Carbohydrates results
		Label resultsCarbohydrates = new Label();
		//Protein results
		Label resultsProtein = new Label();
		//Sugar results
		Label resultsSugar = new Label();
		VBox resultsLayout = new VBox(20);
		resultsLayout.setAlignment(Pos.CENTER);
		resultsLayout.getChildren().addAll(resultsHeader,resultsWarningLabel,resultsCalories,resultsSodium,resultsCarbohydrates,resultsProtein,resultsSugar);

		//Sets up information fill page
		//Title
		Label formHeader = new Label("Enter Nutrition information");
		formHeader.setFont(new Font("Arial",30));
		//warning label
		Label formWarningLabel = new Label();
		formWarningLabel.setStyle("-fx-text-fill: #d91212");
		//Calories field
		TextField calories = new TextField();
		calories.setPromptText("Enter calories:");
		calories.setMaxWidth(300);
		//Sodium field
		TextField sodium = new TextField();
		sodium.setPromptText("Enter sodium:");
		sodium.setMaxWidth(300);
		//Carbohydrates field
		TextField carbohydrates = new TextField();
		carbohydrates.setPromptText("Enter carbohydrates:");
		carbohydrates.setMaxWidth(300);
		//Protein field
		TextField protein = new TextField();
		protein.setPromptText("Enter protein:");
		protein.setMaxWidth(300);
		//Sugar field
		TextField sugar = new TextField();
		sugar.setPromptText("Enter sugar:");
		sugar.setMaxWidth(300);
		//Create account button
		Button formEnter = new Button("Enter information");
		formEnter.setStyle("-fx-background-color: rgba(0,0,0,0)");
		formEnter.setStyle("-fx-text-fill: #2929f3");
		formEnter.setOnAction(e -> {
			if(calories.getText().equals("")){
				formWarningLabel.setText("Calories is empty");
				return;
			}else if(sodium.getText().equals("")){
				formWarningLabel.setText("Sodium already exists");
				return;
			}else if(carbohydrates.getText().equals("")){
				formWarningLabel.setText("Carbohydrates is empty");
				return;
			}else if(protein.getText().equals("")){
				formWarningLabel.setText("Protein is empty");
				return;
			}else if(sugar.getText().equals("")){
				formWarningLabel.setText("Sugar is empty");
				return;
			}else if(!isInt(calories.getText())){
				formWarningLabel.setText("Calories are not a valid number");
				return;
			}else if(!isInt(sodium.getText())){
				formWarningLabel.setText("Sodium is not a valid number");
				return;
			}else if(!isInt(carbohydrates.getText())){
				formWarningLabel.setText("Carbohydrates are not a valid number");
				return;
			}else if(!isInt(protein.getText())){
				formWarningLabel.setText("Protein is not a valid number");
				return;
			}else if(!isInt(sugar.getText())){
				formWarningLabel.setText("Sugar is not a valid number");
				return;
			}
			if(test.user != -1){
				//Calculates calories based on information about the user
				int cals = main.calcCalories(Integer.parseInt(test.userInfo[test.user][3]),test.userInfo[test.user][4],Integer.parseInt(calories.getText()));
				if(cals > 0) {
					resultsCalories.setText("You should eat " + cals + " more calories");
				}else if (cals < 0) {
					resultsCalories.setText("You should eat " + Math.abs(cals) + " less calories");
				}else{
					resultsCalories.setText("You ate the right amount of calories");
				}
				int pro = main.calcProtein(Integer.parseInt(test.userInfo[test.user][3]), Integer.parseInt(protein.getText()));
				if(pro > 0){
					resultsProtein.setText("You should eat " + pro + " more grams of protein");
				}else if (pro < 0){
					resultsProtein.setText("You should eat " + Math.abs(pro) + " less grams of protein");
				}else{
					resultsProtein.setText("You ate the right amount of protein");
				}
				resultsSodium.setText(main.getSodium(Integer.parseInt(sodium.getText())));
				int sug = main.calcSugar(test.userInfo[test.user][4],Integer.parseInt(protein.getText()));
				if(sug > 0) {
					resultsSugar.setText("You should eat " + sug + " less grams of sugar");
				}else{
					resultsSugar.setText("You ate the right amount of sugar");
				}
				int carb = main.calcCarbohydrates(main.calcCalories(Integer.parseInt(test.userInfo[test.user][3]),test.userInfo[test.user][4],cals),Integer.parseInt(carbohydrates.getText()));
				if(carb > 0){
					resultsCarbohydrates.setText("You should eat " + carb + " more grams of carbohydrates");
				}else if (carb < 0){
					resultsCarbohydrates.setText("You should eat " + Math.abs(carb) + " less grams of carbohydrates");
				}else{
					resultsCarbohydrates.setText("You ate the right amount of carbohydrates");
				}
			}else{
				resultsCalories.setText(main.getCalories(Integer.parseInt(calories.getText())));
				resultsCarbohydrates.setText(main.getCarbohydrates(Integer.parseInt(carbohydrates.getText())));
				resultsSodium.setText(main.getSodium(Integer.parseInt(sodium.getText())));
				resultsProtein.setText(main.getProtein(Integer.parseInt(protein.getText())));
				resultsSugar.setText(main.getSugar(Integer.parseInt(sugar.getText())));
			}
			formWarningLabel.setText(""); //Clears no generic warning from previous uses
			mainLayout.setCenter(resultsLayout);
		});
		VBox formLayout = new VBox(20);
		formLayout.setAlignment(Pos.CENTER);
		formLayout.getChildren().addAll(formHeader,formWarningLabel,calories,sodium,carbohydrates,protein,sugar,formEnter);



		//Sets up account creation page
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
		ComboBox<String> Sex = new ComboBox<String>();
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
			//TODO check number of users is < 10 before adding a new one
			test.userInfo[test.user][0] = newUsername.getText();
			test.userInfo[test.user][1] = newPassword.getText();
			test.userInfo[test.user][2] = newHeight.getText();
			test.userInfo[test.user][3] = newWeight.getText();
			test.userInfo[test.user][4] = Sex.getValue()+"";
			test.userInfo[test.user][5] = newAge.getText();
			test.writeFile("NutritionTrackerAccounts.txt");
			mainLayout.setCenter(loginLayout);
		});
		VBox accountCreationLayout = new VBox(20);
		accountCreationLayout.setAlignment(Pos.CENTER);
		accountCreationLayout.getChildren().addAll(createHeader,creationWarningLabel,newUsername,newPassword,newHeight,newWeight,Sex,newAge,createAccount);

		//Sets up account update page
		//TODO unames can't be set until user is set
		//Title
		Label updateHeader = new Label("Edit information you wish to change");
		updateHeader.setFont(new Font("Arial",18));
		//warning label
		Label updateWarningLabel = new Label();
		updateWarningLabel.setStyle("-fx-text-fill: #d91212");
		//Username field
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
		ComboBox<String> updateSex = new ComboBox<>();
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
			if(!(newUsername.getText().equals(""))){
				test.userInfo[this.user][0] = newUsername.getText();
			}else if(!(newPassword.getText().equals(""))){
				test.userInfo[this.user][1] = newPassword.getText();
			}else if(!(newHeight.getText().equals(""))){
				test.userInfo[this.user][2] = newHeight.getText();
			}else if(!(newWeight.getText().equals(""))){
				test.userInfo[this.user][3] = newWeight.getText();
			}else if(!(Sex.getSelectionModel().isEmpty())){
				test.userInfo[this.user][4] = Sex.getValue()+"";
			}else if(!(newAge.getText().equals(""))){
				test.userInfo[this.user][5] = newAge.getText();
			}
			test.writeFile("NutritionTrackerAccounts.txt");
			mainLayout.setCenter(loginLayout);
		});
		VBox accountEditLayout = new VBox(20);
		accountEditLayout.setAlignment(Pos.CENTER);
		accountEditLayout.getChildren().addAll(updateHeader,updateWarningLabel,updateUsername,updatePassword,updateHeight,updateWeight,updateSex,updateAge,updateAccount);


		//Sets up login page
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
					no.setOnAction(f -> {
						mainLayout.setCenter(formLayout);
						formWarningLabel.setText("No account created. Results may be generic");
						popupBorder.close();
					});
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

		loginLayout.setAlignment(Pos.CENTER);
		loginLayout.getChildren().addAll(loginHeader,loginWarningLabel,loginUsername,loginPassword,loginButton,createNewAccount);


		
		//Combines pieces and shows window allowing the program to launch
		primaryStage.setTitle("Nutrition Tracker");
		primaryStage.setScene(mainScene);
		mainLayout.setCenter(loginLayout);
		primaryStage.show();
	}

	//Method used for checking integers provided by text fields in GUI
	private boolean isInt(String message){
		try{
			//Attempts to write strings to an int
			int integer = Integer.parseInt(message);
			return true;
		}catch (NumberFormatException e){
			//Catches non integers
			return false;
		}
	}
}
