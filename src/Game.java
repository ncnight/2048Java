import java.io.IOException;
import java.util.Scanner;


public class Game {

	public static void main(String[] args) throws IOException {
		board game = new board(); // default to 4
		Scanner sc = new Scanner(System.in);
		System.out.println("2048 java implementation in the console. Enter a direction using w,a,s,d");
		char direction = 'f';
		String input = "";
		boolean keepPlaying = true;
		while(keepPlaying) {
			game.generateNum();
			game.print();
			input = sc.nextLine();
			if (input.equals("exit")) {
				sc.close();
				return;
			}
			else if (input.length() < 1) {
				System.out.println("please enter a valid direction (w,a,s,d)");
				continue;
			}
			direction = input.trim().toCharArray()[0];
			try {
				keepPlaying = game.changeBoard(direction);
			}
			catch(IllegalArgumentException e) {
				System.out.print("You didnt put a WASD character! Only put a wasd char then click enter!");
				continue;
			}
			catch(Exception e) {
				e.printStackTrace();
			}
		}
		System.out.println(board.ANSI_PURPLE + "Your score was" + game.getTotal() + "THANKS FOR PLAYING " + board.ANSI_RED + "<3" + board.ANSI_RESET);
		sc.close();
		
	}

}
