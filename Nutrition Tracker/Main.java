import java.util.Scanner;

public class Main {
	final int[][] wcal = {
			{ 110, 115, 120, 125, 130, 135, 140, 145, 150, 155, 160, 165, 170, 175, 180, 185, 190, 195, 200, 205, 210,
					215, 220 },
			{ 1265, 1304, 1353, 1402, 1451, 1500, 1550, 1599, 1648, 1697, 1746, 1795, 1844, 1893, 1942, 1991, 2040,
					2090, 2139, 2188, 2237, 2286, 2335 } };
	final int[][] mcal = {
			{ 140, 145, 150, 155, 160, 165, 170, 175, 180, 185, 190, 195, 200, 205, 210, 215, 220, 225, 230, 235, 240,
					245, 250 },
			{ 1727, 1782, 1836, 1891, 1945, 2000, 2055, 2109, 2164, 2218, 2273, 2327, 2382, 2436, 2491, 2545, 2600,
					2655, 2709, 2764, 2818, 2873, 2927 } };
	final int[][] pro = {{110,121,132,143,154,165,176,187,198,209,220,231,243},
			             {100,110,120,130,140,150,160,170,180,190,200,210,220}};
	static Scanner scan = new Scanner(System.in);

	public static void main(String[] args) {
		getCalories();
		getSodium();
		getCarbohydrates();
		getProtein();
		getSugar();
		scan.close();
	}

	public static void getCalories() {
		final int cal = 2500;
		int dif = 0;
		System.out.println("How many calories did you eat today?");
		while (!scan.hasNextInt())
			scan.next();
		int ical = scan.nextInt();
		if (ical > cal) {
			dif = ical - cal;
			System.out.printf("You need to eat %d less calories.\n", dif);
		} else if (ical < cal) {
			dif = cal - ical;
			System.out.printf("You need to eat %d more calories.\n", dif);
		} else {
			System.out.println("You are eating the correct amount of calories.");
		}
	}

	public static void getSodium() {
		final int sod = 2000;
		int dif = 0;
		System.out.println("How much sodium did you eat today? (mg)");
		while (!scan.hasNextInt())
			scan.next();
		int isod = scan.nextInt();
		if (isod > sod) {
			dif = isod - sod;
			System.out.printf("You need to eat %d less mg of sodium.\n", dif);
		} else if (isod < sod) {
			dif = sod - isod;
			System.out.printf("You need to eat %d more mg of sodium.\n", dif);
		} else {
			System.out.println("You are eating the correct amount of sodium.");
		}
	}

	public static void getCarbohydrates() {
		final int car = 250;
		int dif = 0;
		System.out.println("How much carbohydrates did you eat today? (g)");
		while (!scan.hasNextInt())
			scan.next();
		int icar = scan.nextInt();
		if (icar > car) {
			dif = icar - car;
			System.out.printf("You need to eat %d less g of carbohydrates.\n", dif);
		} else if (icar < car) {
			dif = car - icar;
			System.out.printf("You need to eat %d more g of carbohydrates.\n", dif);
		} else {
			System.out.println("You are eating the correct amount of carbohydrates.");
		}
	}

	public static void getProtein() {
		final int pro = 50;
		int dif = 0;
		System.out.println("How much protein did you eat today? (g)");
		while (!scan.hasNextInt())
			scan.next();
		int ipro = scan.nextInt();
		if (ipro > pro) {
			dif = ipro - pro;
			System.out.printf("You need to eat %d less g of protein.\n", dif);
		} else if (ipro < pro) {
			dif = pro - ipro;
			System.out.printf("You need to eat %d more g of protein.\n", dif);
		} else {
			System.out.println("You are eating the correct amount of protein.");
		}
	}

	public static void getSugar() {
		final int sug = 36;
		int dif = 0;
		System.out.println("How much sugar did you eat today? (g)");
		while (!scan.hasNextInt())
			scan.next();
		int isug = scan.nextInt();
		if (isug > sug) {
			dif = isug - sug;
			System.out.printf("You need to eat %d less g of sugar.\n", dif);
		} else if (isug < sug) {
			dif = sug - isug;
			System.out.printf("You need to eat %d more g of sugar.\n", dif);
		} else {
			System.out.println("You are eating the correct amount of sugar.");
		}
	}
	// returns negative if less calories are needed, positive if more calories are needed
	public int calcCalories(int w, String g, int c) {
		int cal = 0;
		if (g.equalsIgnoreCase("Male")) {
			for (int i = 0; i < 23; i++) {
				if (w < mcal[0][i]) {
					cal = mcal[1][i];
					break;
				}
			}
			if (cal == 0) {
				cal = 2927;
			}
		} else {
			for (int i = 0; i < 23; i++) {
				if (w < wcal[0][i]) {
					cal = mcal[1][i];
					break;
				}
			}
			if (cal == 0) {
				cal = 2335;
			}
		}
		return cal - c;
	}
	// returns 0 if carbs are within range, positive if more carbs are needed, negative if less carbs are needed
	public int calcCarbohydrates(int cal, int carb) {
		if (carb >= .45 * cal && carb <= .65 * cal) {
			return 0;
		} else if (carb < .45 * cal) {
			return (int) (cal * .45) - carb;
		} else {
			return (int) (cal * .65) - carb;
		}
	}
	// returns negative if less protein is needed, positive if more protein is needed
	public int calcProtein(int w, int p) {
		int prot = 0;
		for (int i = 0; i < 13; i++) {
			if (w < pro[0][i]) {
				prot = pro[1][i];
				break;
			}
		}
		if (prot == 0) {
			prot = 220;
		}
		return prot - p;
	}
	// return 0 if not eating too much, positive for too much
	public int calcSugar(String g, int s) {
		int sug = 0;
		if (g.equalsIgnoreCase("Male")) {
			sug = 36;
		}
		else {
			sug = 24;
		}
		if (s <= sug) {
			return 0;
		}
		else {
			return s - sug;
		}
	}
}