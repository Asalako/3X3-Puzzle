import java.util.Scanner;
import java.io.IOException;
import java.util.HashSet;

public class Grid {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub		
		Scanner scanner = new Scanner(System.in);
		System.out.print("Enter the first State: ");
		State state1 = new State(scanner.next());
		state1.combinations(state1);
		
		System.out.print(
				"Would you like write all possible states to a file? Enter y/Y for yes any other character for no. ");
		
		if (scanner.next().toLowerCase().equals("y")) {
			state1.outputTree(10, "state1.txt");
		}
	
		
		System.out.print("Enter the second State: ");
		State state2 = new State(scanner.next());
		state2.combinations(state2);
	
		System.out.print(
				"Would you like write all possible states to a file? Enter y/Y for yes any other character for no. ");

		if (scanner.next().toLowerCase().equals("y")) {
			state2.outputTree(10, "state2.txt");
		}
		
		HashSet<String> diffStates = State.compare(state1, state2);
				
		for (String state: diffStates) {
			new State(state).outputGrid();
		}
		
		System.out.println("\nNumber of states in State 1: " + state1.getTraversedStates().size());
		System.out.println("Number of states in State 2: " + state2.getTraversedStates().size());
		System.out.println("Number of states in State 1 but not in State 2 is: " + diffStates.size() );

	}

}
