import java.util.Random;


public class board {
	//COLORS
	public static final String ANSI_RESET = "\u001B[0m";
	public static final String ANSI_BLACK = "\u001B[30m";
	public static final String ANSI_RED = "\u001B[31m";
	public static final String ANSI_GREEN = "\u001B[32m";
	public static final String ANSI_YELLOW = "\u001B[33m";
	public static final String ANSI_BLUE = "\u001B[34m";
	public static final String ANSI_PURPLE = "\u001B[35m";
	public static final String ANSI_CYAN = "\u001B[36m";
	public static final String ANSI_WHITE = "\u001B[37m";

	//Member variables
	private int[][] board;
	
	/**
	 * custom constructor
	 * 
	 * @param n - num of rows and columns
	 */
	public board(int n) {
		board = new int[n][n];
	}
	
	/**
	 * Default constructor
	 * 
	 */
	public board() {
		board = new int[4][4];
	}

	/**
	 * Prints the board 
	 * The code is a bit messy 
	 * TODO: Clean up
	 * Empty output
	 * 
	 * 2048: Enter a direction!
	 * --------------------------------------------
	 * |    0     |    0     |    0     |    0    |
 	 * --------------------------------------------
 	 * |    0     |    0     |    0     |    0    |
 	 * --------------------------------------------
	 * |    0     |    0     |    0     |    0    |
	 * --------------------------------------------
	 * |    0     |    0     |    0     |    0    |
	 * --------------------------------------------
	 */
	public void print(){
		
		System.out.print("\n\n"); //spacing after last board
		int len = this.board.length; //How far the horizontal borders should go
		String s = ""; //Printed for each square
		System.out.println(ANSI_YELLOW + "2048: Enter a direction!" + ANSI_RESET);
		for (int x = 0; x < this.board.length; x++) { //for each horizontal row
			System.out.print(" "); 
			for (int i = 0; i < len; i++) { //prints a border one top of the current row
				System.out.print(ANSI_BLUE+"-----------"+ANSI_RESET);
			}
			System.out.println(); //next line
			for (int y = 0; y < this.board[0].length; y++) { //For each column in the current row
				s = Integer.toString(this.board[x][y]); 
				for (int i = 8; i > s.length(); i-- ) { //add spacing to the right side - accounts for different sized digits
					s+= " ";
				}
				s = ANSI_RED + s + ANSI_RESET;
				System.out.print(ANSI_BLUE + " |    "+ s);
			}
			System.out.println(ANSI_BLUE + "|" + ANSI_RESET); //the last one vertical border of the current row
		}
		System.out.print(" "); //end space 
		for (int i = 0; i < len; i++) { //last border on the bottom
			System.out.print(ANSI_BLUE + "-----------" + ANSI_RESET);
		}
		System.out.println(); //ready for input
	}
	
	public int getTotal() {
		int total = 0;
		for (int x = 0; x < this.board.length; x++) {
			for (int y = 0; y < this.board[x].length; y++) {
				total+= this.board[x][y];
			}
		}
		return total;
	}

	/**
	 * Shifts the board in the given direction 
	 * 
	 * @param char d - directions w,a,s,d
	 */
	public boolean changeBoard(char d) throws IllegalArgumentException{
		boolean shifted = false;
		switch(d) {
		
		case 'w':
			shifted = this.shiftUp();
			break;
		case 'a':
			shifted = this.shiftLeft();
			break;
		case 's':
			shifted = this.shiftDown();
			break;
		case 'd':
			shifted = this.shiftRight();
			break;
		default:
			throw new IllegalArgumentException();
		}
		return shifted;
	}
	
	/**
	 * Randomly generates a 2 or 4 on the gameboard
	 */
	public void generateNum() {
		Random rd = new Random();
		//Two random ints
		int x = rd.nextInt(this.board.length);
		int y = rd.nextInt(this.board[0].length);
		
		while(this.board[x][y] != 0) {
			x = rd.nextInt(this.board.length);
			y = rd.nextInt(this.board[0].length);
		}
		
		int prob = rd.nextInt(5);
		if (prob == 3) { //no reason for 3, just to make a 1/5 probability assuming Rand is uniformally distributed
			this.board[x][y] = 4;
		}
		else {
			this.board[x][y] = 2;
		}
		
	}

	/*
	 * PRIVATE HELPER METHODS
	 */
	private boolean shiftUp() {
		boolean shifted = false;
		//shift up
		int tmp; //for traversing 
		for (int x = this.board.length-1; x > -1; x--) {
			innercolumn: //naming inner for loop for continue on Array out of bounds catch
			for (int y = this.board[0].length-1; y > -1; y--) {
				tmp = 1;
				if (x <= -1 || this.board[x][y] == 0) {
					continue innercolumn;
				}
				try { 
					while(this.board[x-tmp][y] == 0) {
						this.board[x-tmp][y] = this.board[x][y];
						this.board[x][y] = 0;
						x++;
						shifted = true;
					}
				}
				catch(ArrayIndexOutOfBoundsException e) { //purposefully tripped 
					continue innercolumn;
				}
				catch(Exception e) { 
					e.printStackTrace();
				}
			}
		}
		
		//combine
		for (int x = this.board.length-2; x > -1; x--) {
			for (int y = this.board[0].length-1; y > -1; y--) {
				if (this.board[x][y] == this.board[x+1][y]){
					this.board[x][y] = 0;
					this.board[x+1][y] *= 2;
					shifted = true;
				}		
			}
		}
		//shift up
		for (int x = this.board.length-1; x > -1; x--) {
			innercolumn: //naming inner for loop for continue on Array out of bounds catch
			for (int y = this.board[0].length-1; y > -1; y--) {
				tmp = 1;
				if (x <= -1 || this.board[x][y] == 0) {
					continue innercolumn;
				}
				try { 
					while(this.board[x-tmp][y] == 0) {
						this.board[x-tmp][y] = this.board[x][y];
						this.board[x][y] = 0;
						x++;
						shifted = true;
					}
				}
				catch(ArrayIndexOutOfBoundsException e) { //purposefully tripped 
					continue innercolumn;
				}
				catch(Exception e) { 
					e.printStackTrace();
				}
			}
		}
		return shifted;
	}
	private boolean shiftLeft() {
		boolean shifted = false;
		int tmp; //for traversing 
		for (int x = this.board.length-1; x > -1; x--) {
			innercolumn: //naming inner for loop for continue on Array out of bounds catch
			for (int y = 0; y < this.board[0].length; y++) {
				tmp = 1;
				if (this.board[x][y] == 0) {
					continue innercolumn;
				}
				try { 
					while(this.board[x][y-tmp] == 0) {
						this.board[x][y-tmp] = this.board[x][y];
						this.board[x][y] = 0;
						y--;
						shifted = true;
					}
				}
				catch(ArrayIndexOutOfBoundsException e) { //purposefully tripped 
					continue innercolumn;
				}
				catch(Exception e) { 
					e.printStackTrace();
				}
			}
		}
		//combine
		for (int x = this.board.length-1; x > -1; x--) {
			for (int y = this.board[0].length-2; y > -1; y--) {
				if (this.board[x][y] == this.board[x][y+1]){
					this.board[x][y] = 0;
					this.board[x][y+1] *= 2;
					shifted = true;
				}		
			}
		}
		//shift left
		for (int x = this.board.length-1; x > -1; x--) {
			innercolumn: //naming inner for loop for continue on Array out of bounds catch
			for (int y = 0; y < this.board[0].length; y++) {
				tmp = 1;
				if (this.board[x][y] == 0) {
					continue innercolumn;
				}
				try { 
					while(this.board[x][y-tmp] == 0) {
						this.board[x][y-tmp] = this.board[x][y];
						this.board[x][y] = 0;
						y--;
						shifted = true;
					}
				}
				catch(ArrayIndexOutOfBoundsException e) { //purposefully tripped 
					continue innercolumn;
				}
				catch(Exception e) { 
					e.printStackTrace();
				}
			}
		}
		return shifted;
	}
	private boolean shiftDown() {
		boolean shifted = false;
		int tmp; //for traversing 
		for (int x = this.board.length-1; x > -1; x--) {
			innercolumn: //naming inner for loop for continue on Array out of bounds catch
			for (int y = this.board[0].length-1; y > -1; y--) {
				tmp = 1;
				if ( x <= -1 || this.board[x][y] == 0) {
					continue innercolumn;
				}
				try { 
					while(this.board[x+tmp][y] == 0) {
						this.board[x+tmp][y] = this.board[x][y];
						this.board[x][y] = 0;
						x--;
						shifted = true;
					}
				}
				catch(ArrayIndexOutOfBoundsException e) { //purposefully tripped 
					continue innercolumn;
				}
				catch(Exception e) { 
					e.printStackTrace();
				}
			}
		}		
		//combine
		for (int x = this.board.length-2; x > -1; x--) {
			for (int y = this.board[0].length-1; y > -1; y--) {
				if (this.board[x][y] == this.board[x+1][y]){
					this.board[x][y] = 0;
					this.board[x+1][y] *= 2;
					shifted = true;
				}		
			}
		}
		//shift down
		for (int x = this.board.length-1; x > -1; x--) {
			innercolumn: //naming inner for loop for continue on Array out of bounds catch
			for (int y = this.board[0].length-1; y > -1; y--) {
				tmp = 1;
				if (x <= -1 || this.board[x][y] == 0) {
					continue innercolumn;
				}
				try { 
					while(this.board[x+tmp][y] == 0) {
						this.board[x+tmp][y] = this.board[x][y];
						shifted = true;
						this.board[x][y] = 0;
						x--;
					}
				}
				catch(ArrayIndexOutOfBoundsException e) { //purposefully tripped 
					continue innercolumn;
				}
				catch(Exception e) { 
					e.printStackTrace();
				}
			}
		}
		return shifted;
		
	}
	private boolean shiftRight(){
		boolean shifted = false;
		int tmp; //for traversing 
		for (int x = this.board.length-1; x > -1; x--) {
			innercolumn: //naming inner for loop for continue on Array out of bounds catch
			for (int y = this.board[0].length-1; y > -1; y--) {
				tmp = 1;
				if (this.board[x][y] == 0) {
					continue innercolumn;
				}
				try { 
					while(this.board[x][y+tmp] == 0) {
						this.board[x][y+tmp] = this.board[x][y];
						shifted = true;
						this.board[x][y] = 0;
						y++;
					}
				}
				catch(ArrayIndexOutOfBoundsException e) { //purposefully tripped 
					continue innercolumn;
				}
				catch(Exception e) { 
					e.printStackTrace();
				}
			}
		}

		//combine
		for (int x = this.board.length-1; x > -1; x--) {
			for (int y = this.board[0].length-2; y > -1; y--) {
				if (this.board[x][y] == this.board[x][y+1]){
					this.board[x][y] = 0;
					this.board[x][y+1] *= 2;
					shifted = true;
				}		
			}
		}
		//shift right
		for (int x = this.board.length-1; x > -1; x--) {
			innercolumn: //naming inner for loop for continue on Array out of bounds catch
			for (int y = this.board[0].length-1; y > -1; y--) {
				tmp = 1;
				if (this.board[x][y] == 0) {
					continue innercolumn;
				}
				try { 
					while(this.board[x][y+tmp] == 0) {
						this.board[x][y+tmp] = this.board[x][y];
						this.board[x][y] = 0;
						y++;
						shifted = true;
					}
				}
				catch(ArrayIndexOutOfBoundsException e) { //purposefully tripped 
					continue innercolumn;
				}
				catch(Exception e) { 
					e.printStackTrace();
				}
			}
		}
		return shifted;
	}

}
