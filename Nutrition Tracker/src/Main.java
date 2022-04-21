//JavaFX stuff
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.File;
import java.io.PrintWriter;

public class Main extends Application{
	private String[][] userInfo = new String[25][6];
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
			size = countRow;
			
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
	private int searchUser(String userName, String[][] Info, int size)
	{
		for(int i = 0;i < size;i++) {
			if(Info[i][0].equals(userName)) {
				return i;
			}
		}
		return -1;
	}

	public static void main(String args[]) {
		launch(args); //JavaFX
	}

	VBox loginLayout;

	//Main JavaFX code
	@Override
	public void start(Stage primaryStage) throws Exception {
		//General information
		Main test = new Main();
		Methods methods = new Methods();
		test.readFile("NutritionTrackerAccounts.txt");
		BorderPane mainLayout = new BorderPane();
		Scene mainScene = new Scene(mainLayout,960,540);

		//Sets up return to login button
		Button returnButton = new Button("Return To Login");
		returnButton.setStyle("-fx-text-fill: #FFFFFFFF;-fx-background-color: #3e3e3e");
		returnButton.setOnAction(e -> {
			mainLayout.setCenter(loginLayout);
			mainLayout.setTop(null);
		});
		HBox returnLayout = new HBox();
		returnLayout.setPadding(new Insets(3,10,10,10));
		returnLayout.setStyle("-fx-background-color : #3e3e3e");
		returnLayout.getChildren().addAll(returnButton);

		//Adds yellow bar across bottom
		HBox yellowBottom = new HBox();
		yellowBottom.setPadding(new Insets(10,0,0,0));
		yellowBottom.setStyle("-fx-background-color:linear-gradient(to top, #ffd818, #3e3e3e)");
		yellowBottom.getChildren().addAll();
		mainLayout.setBottom(yellowBottom);

		//Sets up results page
		//Title
		Label resultsHeader = new Label("Results");
		resultsHeader.setFont(new Font("Arial",30));
		resultsHeader.setStyle("-fx-text-fill: #ffffff");
		//Calories results
		Label resultsCalories = new Label();
		resultsCalories.setStyle("-fx-text-fill: #ffffff");
		resultsCalories.setFont(new Font("Arial",16));
		//Sodium results
		Label resultsSodium = new Label();
		resultsSodium.setStyle("-fx-text-fill: #ffffff");
		resultsSodium.setFont(new Font("Arial",16));
		//Carbohydrates results
		Label resultsCarbohydrates = new Label();
		resultsCarbohydrates.setStyle("-fx-text-fill: #ffffff");
		resultsCarbohydrates.setFont(new Font("Arial",16));
		//Protein results
		Label resultsProtein = new Label();
		resultsProtein.setStyle("-fx-text-fill: #ffffff");
		resultsProtein.setFont(new Font("Arial",16));
		//Sugar results
		Label resultsSugar = new Label();
		resultsSugar.setStyle("-fx-text-fill: #ffffff");
		resultsSugar.setFont(new Font("Arial",16));
		VBox resultsLayout = new VBox(20);
		resultsLayout.setAlignment(Pos.CENTER);
		resultsLayout.setStyle("-fx-background-color : #3e3e3e");
		resultsLayout.getChildren().addAll(resultsHeader,resultsCalories,resultsSodium,resultsCarbohydrates,resultsProtein,resultsSugar);

		//Sets up information fill page
		//Title
		Label formHeader = new Label("Enter Nutrition Information");
		formHeader.setFont(new Font("Arial",30));
		formHeader.setStyle("-fx-text-fill: #ffffff");
		//warning label
		Label formWarningLabel = new Label();
		formWarningLabel.setStyle("-fx-text-fill: #ffd818");
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
		formEnter.setStyle("-fx-text-fill: #FFFFFFFF;-fx-background-color: #3e3e3e;-fx-border-color: #FFFFFFFF;-fx-border-width: 2");
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
				int cals = methods.calcCalories(Integer.parseInt(test.userInfo[test.user][3]),test.userInfo[test.user][4],Integer.parseInt(calories.getText()));
				if(cals > 0) {
					resultsCalories.setText("You should eat " + cals + " more calories");
				}else if (cals < 0) {
					resultsCalories.setText("You should eat " + Math.abs(cals) + " less calories");
				}else{
					resultsCalories.setText("You ate the right amount of calories");
				}
				int pro = methods.calcProtein(Integer.parseInt(test.userInfo[test.user][3]), Integer.parseInt(protein.getText()));
				if(pro > 0){
					resultsProtein.setText("You should eat " + pro + " more grams of protein");
				}else if (pro < 0){
					resultsProtein.setText("You should eat " + Math.abs(pro) + " less grams of protein");
				}else{
					resultsProtein.setText("You ate the right amount of protein");
				}
				resultsSodium.setText(methods.getSodium(Integer.parseInt(sodium.getText())));
				int sug = methods.calcSugar(test.userInfo[test.user][4],Integer.parseInt(protein.getText()));
				if(sug > 0) {
					resultsSugar.setText("You should eat " + sug + " less grams of sugar");
				}else{
					resultsSugar.setText("You ate the right amount of sugar");
				}
				int carb = methods.calcCarbohydrates(methods.calcCalories(Integer.parseInt(test.userInfo[test.user][3]),test.userInfo[test.user][4],cals),Integer.parseInt(carbohydrates.getText()));
				if(carb > 0){
					resultsCarbohydrates.setText("You should eat " + carb + " more grams of carbohydrates");
				}else if (carb < 0){
					resultsCarbohydrates.setText("You should eat " + Math.abs(carb) + " less grams of carbohydrates");
				}else{
					resultsCarbohydrates.setText("You ate the right amount of carbohydrates");
				}
			}else{
				resultsCalories.setText(methods.getCalories(Integer.parseInt(calories.getText())));
				resultsCarbohydrates.setText(methods.getCarbohydrates(Integer.parseInt(carbohydrates.getText())));
				resultsSodium.setText(methods.getSodium(Integer.parseInt(sodium.getText())));
				resultsProtein.setText(methods.getProtein(Integer.parseInt(protein.getText())));
				resultsSugar.setText(methods.getSugar(Integer.parseInt(sugar.getText())));
			}
			formWarningLabel.setText(""); //Clears no generic warning from previous uses
			mainLayout.setCenter(resultsLayout);
		});
		VBox formLayout = new VBox(20);
		formLayout.setAlignment(Pos.CENTER);
		formLayout.setStyle("-fx-background-color : #3e3e3e");
		formLayout.getChildren().addAll(formHeader,formWarningLabel,calories,sodium,carbohydrates,protein,sugar,formEnter);

		//Sets up account creation page
		//Title
		Label createHeader = new Label("Account Creation");
		createHeader.setFont(new Font("Arial",30));
		createHeader.setStyle("-fx-text-fill: #ffffff");
		//warning label
		Label creationWarningLabel = new Label();
		creationWarningLabel.setStyle("-fx-text-fill: #ffd818");
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
		createAccount.setStyle("-fx-text-fill: #FFFFFFFF;-fx-background-color: #3e3e3e;-fx-border-color: #FFFFFFFF;-fx-border-width: 2");
		createAccount.setOnAction(e -> {
			if(newUsername.getText().equals("")){
				creationWarningLabel.setText("Username is empty");
				return;
			}else if(searchUser(newUsername.getText(), test.userInfo, test.size)!=-1){
				test.user = searchUser(newUsername.getText(), test.userInfo, test.size);
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
			test.userInfo[test.size][0] = newUsername.getText();
			test.userInfo[test.size][1] = newPassword.getText();
			test.userInfo[test.size][2] = newHeight.getText();
			test.userInfo[test.size][3] = newWeight.getText();
			test.userInfo[test.size][4] = Sex.getValue()+"";
			test.userInfo[test.size][5] = newAge.getText();
			test.size++;
			test.writeFile("NutritionTrackerAccounts.txt");
			mainLayout.setCenter(loginLayout);
			mainLayout.setTop(null);
		});
		VBox accountCreationLayout = new VBox(20);
		accountCreationLayout.setAlignment(Pos.CENTER);
		accountCreationLayout.setStyle("-fx-background-color : #3e3e3e");
		accountCreationLayout.getChildren().addAll(createHeader,creationWarningLabel,newUsername,newPassword,newHeight,newWeight,Sex,newAge,createAccount);

		//Sets up account update page
		//Title
		Label updateHeader = new Label("Edit information you wish to change");
		updateHeader.setFont(new Font("Arial",18));
		updateHeader.setStyle("-fx-text-fill: #ffffff");
		//warning label
		Label updateWarningLabel = new Label();
		updateWarningLabel.setStyle("-fx-text-fill: #ffd818");
		//Username field
		TextField updateUsername = new TextField();
		updateUsername.setMaxWidth(300);
		//Password field
		TextField updatePassword = new TextField();
		updatePassword.setMaxWidth(300);
		//Height field
		TextField updateHeight = new TextField();
		updateHeight.setMaxWidth(300);
		//Weight field
		TextField updateWeight = new TextField();
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
		updateAccount.setStyle("-fx-text-fill: #FFFFFFFF;-fx-background-color: #3e3e3e;-fx-border-color: #FFFFFFFF;-fx-border-width: 2");
		updateAccount.setOnAction(e -> {
			userInfo = test.userInfo;
			size = test.size;
			if(searchUser(updateUsername.getText(), test.userInfo, test.size)!=-1){
				test.user = searchUser(updateUsername.getText(), test.userInfo, test.size);
				updateWarningLabel.setText("Username already exists");
				return;
			}else if(!updateHeight.getText().equals("") && isInt(updateHeight.getText())==false){
				updateWarningLabel.setText("Height is not a valid number");
				return;
			}else if(!updateWeight.getText().equals("") && isInt(updateWeight.getText())==false){
				updateWarningLabel.setText("Weight is not a valid number");
				return;
			}else if(!updateAge.getText().equals("") && isInt(updateAge.getText())==false){
				updateWarningLabel.setText("Age is not a valid number");
				return;
			}
			if(!(updateUsername.getText().equals(""))){
				test.userInfo[test.user][0] = updateUsername.getText();	
			}
			if(!(updatePassword.getText().equals(""))){
				test.userInfo[test.user][1] = updatePassword.getText();
			}
			if(!(updateHeight.getText().equals(""))){
				test.userInfo[test.user][2] = updateHeight.getText();
			}
			if(!(updateWeight.getText().equals(""))){
				test.userInfo[test.user][3] = updateWeight.getText();
			}
			if(!(updateSex.getSelectionModel().isEmpty())){
				test.userInfo[test.user][4] = updateSex.getValue()+"";
			}
			if(!(updateAge.getText().equals(""))){
				test.userInfo[test.user][5] = updateAge.getText();
			}
			test.writeFile("NutritionTrackerAccounts.txt");
			mainLayout.setCenter(loginLayout);
		});
		VBox accountEditLayout = new VBox(20);
		accountEditLayout.setAlignment(Pos.CENTER);
		accountEditLayout.setStyle("-fx-background-color : #3e3e3e");
		accountEditLayout.getChildren().addAll(updateHeader,updateWarningLabel,updateUsername,updatePassword,updateHeight,updateWeight,updateSex,updateAge,updateAccount);

		//Sets up login page
		//Title
		Label loginHeader = new Label("Login");
		loginHeader.setFont(new Font("Arial",30));
		loginHeader.setStyle("-fx-text-fill: #ffffff");
		loginHeader.setUnderline(false);
		//warning label
		Label loginWarningLabel = new Label();
		loginWarningLabel.setStyle("-fx-text-fill: #ffd818");
		//Username field
		TextField loginUsername = new TextField();
		loginUsername.setPromptText("Enter Username:");
		loginUsername.setMaxWidth(300);
		//Password field
		PasswordField loginPassword = new PasswordField();
		loginPassword.setPromptText("Enter Password:");
		loginPassword.setMaxWidth(300);
		//Enter button
		Button loginButton = new Button("Enter");
		loginButton.setStyle("-fx-text-fill: #FFFFFFFF;-fx-background-color: #3e3e3e;-fx-border-color: #FFFFFFFF;-fx-border-width: 2");
		loginButton.setFont(new Font("Arial",13));
		//Enter button functionality (checking username and password
		loginButton.setOnAction(e -> {
			if(loginUsername.getText().equals("")){
				loginWarningLabel.setText("No username entered");
				return;
			}else if(loginPassword.getText().equals("")){
				loginWarningLabel.setText("No password entered");
				return;
			}else{
				int account = -1;
				account = test.searchUser(loginUsername.getText(), test.userInfo, test.size);
				test.user = account;
				if(account == -1){
					loginWarningLabel.setText("No matching username found");
					return;
				}
				if(loginPassword.getText().equals(test.userInfo[account][1])){
					//Password matches
					loginWarningLabel.setText("");
					loginPassword.setText("");
					updateWarningLabel.setText("");
					updateUsername.setText("");
					updatePassword.setText("");
					updateHeight.setText("");
					updateWeight.setText("");
					updateAge.setText("");
					creationWarningLabel.setText("");
					newUsername.setText("");
					newPassword.setText("");
					newHeight.setText("");
					newWeight.setText("");
					Sex.getSelectionModel().select(null);
					newAge.setText("");
					formWarningLabel.setText("");
					calories.setText("");
					sodium.setText("");
					carbohydrates.setText("");
					protein.setText("");
					sugar.setText("");
					//Popup
					BorderPane popupPane = new BorderPane(); //Border pane
					HBox update = new HBox(20); //HBox for center
					Button yes = new Button("Yes");
					Button no = new Button("No");
					update.setAlignment(Pos.CENTER);
					update.getChildren().addAll(yes,no);
					HBox header = new HBox(20); //VBox for header
					Label popupHeader = new Label("Do you want to update your information?");
					popupHeader.setStyle("-fx-text-fill: #ffffff");
					header.getChildren().add(popupHeader);
					header.setAlignment(Pos.CENTER);
					popupPane.setTop(header);
					popupPane.setCenter(update); //Combining HBox and border pane
					popupPane.setStyle("-fx-background-color : #3e3e3e");
					Scene popupCenter = new Scene(popupPane,300,100); //Creates scene
					Stage popupBorder = new Stage(); //Creates stage
					popupBorder.setScene(popupCenter); //Applies scene to stage
					yes.setStyle("-fx-text-fill: #FFFFFFFF;-fx-background-color: #3e3e3e;-fx-border-color: #FFFFFFFF;-fx-border-width: 1");
					yes.setOnAction(f -> {
						updateUsername.setPromptText("Update username: "+test.userInfo[test.user][0]);
						updatePassword.setPromptText("Update password: "+test.userInfo[test.user][1]);
						updateHeight.setPromptText("Update height (inches): "+test.userInfo[test.user][2]);
						updateWeight.setPromptText("Update weight (pounds): "+test.userInfo[test.user][3]);
						updateAge.setPromptText("Update age (years): "+test.userInfo[test.user][5]);
						mainLayout.setCenter(accountEditLayout);
						popupBorder.close();
					});
					popupBorder.setTitle("Edit");
					no.setStyle("-fx-text-fill: #FFFFFFFF;-fx-background-color: #3e3e3e;-fx-border-color: #FFFFFFFF;-fx-border-width: 1");
					no.setOnAction(f -> {
						mainLayout.setCenter(formLayout);
						mainLayout.setTop(returnLayout);
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
		Button createNewAccount = new Button("Create an account");
		createNewAccount.setStyle("-fx-text-fill: #FFFFFFFF;-fx-background-color: #3e3e3e");
		createNewAccount.setOnAction(e -> {
			mainLayout.setCenter(accountCreationLayout);
			mainLayout.setTop(returnLayout);
		});
		//Create no account button functionality
		Button noAccount = new Button("Continue without an account");
		noAccount.setStyle("-fx-text-fill: #FFFFFFFF;-fx-background-color: #3e3e3e");
		noAccount.setOnAction(e -> {
			test.user=-1;
			formWarningLabel.setText("No account created. Results may be generic");
			calories.setText("");
			sodium.setText("");
			carbohydrates.setText("");
			protein.setText("");
			sugar.setText("");
			mainLayout.setCenter(formLayout);
			mainLayout.setTop(returnLayout);
		});
		loginLayout = new VBox(20);
		loginLayout.setAlignment(Pos.CENTER);
		loginLayout.setStyle("-fx-background-color : #3e3e3e");
		loginLayout.getChildren().addAll(loginHeader,loginWarningLabel,loginUsername,loginPassword,loginButton,createNewAccount,noAccount);
		
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
			Integer.parseInt(message);
			return true;
		}catch (NumberFormatException e){
			//Catches non integers
			return false;
		}
	}
}
