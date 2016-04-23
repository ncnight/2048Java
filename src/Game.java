import java.io.IOException;
import java.util.Scanner;


public class Game {

	public static void main(String[] args) throws IOException {
		board game = new board(); // default to 4
		Scanner sc = new Scanner(System.in);
		System.out.println("2048 java implementation in the console");
		game.print();
		sc.nextLine();
		sc.close();
		
	}

}
