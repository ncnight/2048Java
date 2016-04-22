
public class board {
	
	
	public int[][] board;
	
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
	 * 
	 * Empty form 
	 * 
2048: Enter a direction!
---------------------------------
|   0   |   0   |   0   |   0   |
---------------------------------
|   0   |   0   |   0   |   0   |
---------------------------------
|   0   |   0   |   0   |   0   |
---------------------------------
|   0   |   0   |   0   |   0   |
---------------------------------
	 */
	public void print(){
		System.out.println("2048: Enter a direction!");
		for (int x = 0; x < this.board.length; x++) {
			System.out.println("---------------------------------");
			for (int y = 0; y < this.board[0].length; y++) {
				System.out.print("|   "+this.board[x][y] + "   "); //TODO: fix spacing for multiple digits
			}
			System.out.println("|");
		}
		System.out.println("---------------------------------");
	}
	
	/**
	 * Shifts the board in the given direction 
	 * 
	 * @param char d - directions w,a,s,d
	 */
	public void changeBoard(char d){
		switch(d) {
		
		case 'w':
			this.shiftUp();
			break;
		case 'a':
			this.shiftLeft();
			break;
		case 's':
			this.shiftDown();
			break;
		case 'd':
			this.shiftRight();
			break;
		}
			
//				for (int x = 0; x < this.board.length; x++) {
//					for (int y = 0; y < this.board[0].length; y++) {
//						
//					}
//				}
	}
	/**
	 * Randomly generates a 2 or 4 on the gameboard
	 */
	public void generateNum() {
		this.board[1][2] = 2;
		this.board[1][0] = 2;
		this.board[1][1] = 2;
		this.board [1][3] = 2;
		this.board[2][0] = 8;
		this.board[0][0] = 16;
	}

	/*
	 * PRIVATE HELPER METHODS
	 */
	private void shiftUp() {
		//shift up
		int tmp; //for traversing 
		for (int x = this.board.length-1; x > -1; x--) {
			innercolumn: //naming inner for loop for continue on Array out of bounds catch
			for (int y = this.board[0].length-1; y > -1; y--) {
				tmp = 1;
				if (this.board[x][y] == 0) {
					continue innercolumn;
				}
				try { 
					while(this.board[x-tmp][y] == 0) {
						this.board[x-tmp][y] = this.board[x][y];
						this.board[x][y] = 0;
						x++;
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
				}		
			}
		}
		//shift up
		for (int x = this.board.length-1; x > -1; x--) {
			innercolumn: //naming inner for loop for continue on Array out of bounds catch
			for (int y = this.board[0].length-1; y > -1; y--) {
				tmp = 1;
				if (this.board[x][y] == 0) {
					continue innercolumn;
				}
				try { 
					while(this.board[x-tmp][y] == 0) {
						this.board[x-tmp][y] = this.board[x][y];
						this.board[x][y] = 0;
						x++;
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


	}
	private void shiftLeft() {
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
	}
	private void shiftDown() {
		int tmp; //for traversing 
		for (int x = this.board.length-1; x > -1; x--) {
			innercolumn: //naming inner for loop for continue on Array out of bounds catch
			for (int y = this.board[0].length-1; y > -1; y--) {
				tmp = 1;
				if (this.board[x][y] == 0) {
					continue innercolumn;
				}
				try { 
					while(this.board[x+tmp][y] == 0) {
						this.board[x+tmp][y] = this.board[x][y];
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
		//combine
		for (int x = this.board.length-2; x > -1; x--) {
			for (int y = this.board[0].length-1; y > -1; y--) {
				if (this.board[x][y] == this.board[x+1][y]){
					this.board[x][y] = 0;
					this.board[x+1][y] *= 2;
				}		
			}
		}
		//shift down
		for (int x = this.board.length-1; x > -1; x--) {
			innercolumn: //naming inner for loop for continue on Array out of bounds catch
			for (int y = this.board[0].length-1; y > -1; y--) {
				tmp = 1;
				if (this.board[x][y] == 0) {
					continue innercolumn;
				}
				try { 
					while(this.board[x+tmp][y] == 0) {
						this.board[x+tmp][y] = this.board[x][y];
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
		
	}
	
	private void shiftRight(){
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
	}
}
