import java.util.Scanner;

public class Main {
	static Scanner scan = new Scanner(System.in);
	public static void main(String[] args) {
	getCalories();
	getSodium();
	getCarbohydrates();
	getProtein();
	scan.close();
	}
	public static void getCalories() {
		final int cal = 2500; 
		int dif = 0;
		System.out.println("How many calories did you eat today?");
		while (!scan.hasNextInt()) scan.next();
		int ical = scan.nextInt(); 
		if (ical > cal) {
			dif = ical - cal;
			System.out.printf("You need to eat %d less calories.\n", dif);
		}
		else if (ical < cal) {
			dif = cal - ical;
			System.out.printf("You need to eat %d more calories.\n", dif);
		}
		else {
			System.out.println("You are eating the correct amount of calories.");
		}
	}
	public static void getSodium() {
		final int sod = 2000; 
		int dif = 0;
		System.out.println("How much sodium did you eat today? (mg)");
		while (!scan.hasNextInt()) scan.next();
		int isod = scan.nextInt();  
		if (isod > sod) {
			dif = isod - sod;
			System.out.printf("You need to eat %d less mg of sodium.\n", dif);
		}
		else if (isod < sod) {
			dif = sod - isod;
			System.out.printf("You need to eat %d more mg of sodium.\n", dif);
		}
		else {
			System.out.println("You are eating the correct amount of sodium.");
		}
	}
	public static void getCarbohydrates() {
		final int car = 250; 
		int dif = 0;
		System.out.println("How much carbohydrates did you eat today? (g)");
		while (!scan.hasNextInt()) scan.next();
		int icar = scan.nextInt();  
		if (icar > car) {
			dif = icar - car;
			System.out.printf("You need to eat %d less g of carbohydrates.\n", dif);
		}
		else if (icar < car) {
			dif = car - icar;
			System.out.printf("You need to eat %d more g of carbohydrates.\n", dif);
		}
		else {
			System.out.println("You are eating the correct amount of carbohydrates.");
		}
	}
	public static void getProtein() {
		final int pro = 50; 
		int dif = 0;
		System.out.println("How much protein did you eat today? (g)");
		while (!scan.hasNextInt()) scan.next();
		int ipro = scan.nextInt();  
		if (ipro > pro) {
			dif = ipro - pro;
			System.out.printf("You need to eat %d less g of protein.\n", dif);
		}
		else if (ipro < pro) {
			dif = pro - ipro;
			System.out.printf("You need to eat %d more g of protein.\n", dif);
		}
		else {
			System.out.println("You are eating the correct amount of protein.");
		}
	}
	public static void getSugar() {
		final int sug = 36; 
		int dif = 0;
		System.out.println("How much sugar did you eat today? (g)");
		while (!scan.hasNextInt()) scan.next();
		int isug = scan.nextInt();  
		if (isug > sug) {
			dif = isug - sug;
			System.out.printf("You need to eat %d less g of sugar.\n", dif);
		}
		else if (isug < sug) {
			dif = sug - isug;
			System.out.printf("You need to eat %d more g of sugar.\n", dif);
		}
		else {
			System.out.println("You are eating the correct amount of sugar.");
		}
	}
}
