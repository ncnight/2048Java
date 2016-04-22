
public class tester {

	
	public static void main(String[] args) {
		board b = new board();
		b.print();

		b.generateNum();
		b.print();
		b.changeBoard('d');
		b.print();
		b.changeBoard('a');
		b.print();
		b.changeBoard('s');
		b.print();
		b.changeBoard('w');
		b.print();
	}

}
