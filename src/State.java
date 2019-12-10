import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Scanner;

public class State {
	
	private String stateString;
	private String[] state = new String[3];
	private HashSet<String> traversedStates = new HashSet<String>();
	private ArrayList<State> outputTree = new ArrayList<State>();

	public State(String input) {
		// TODO Auto-generated constructor stub
		this.stateString = input;
		
		//Separates the string into 3 rows
		this.state = stringToState(input);
		
	}
	
	/*
	 * Finds all possibles distinct states that can be achieved through x moves recursively
	 * 
	 * @param	currentState - takes in a state to be computed.
	 * @returns	returns true to know if last node of the tree has been reach 
	 * 
	 */
	public boolean combinations(State currentState) {
		//The current state is checked against every state that's been visited, returns true if it already exists
		boolean backTrack = false;
		
		if (traversedStates.contains(currentState.getStateString())) {
			return true;
		}
		
		traversedStates.add(currentState.getStateString());
		outputTree.add(currentState);

		
		System.out.print("Number Of States:");
		System.out.println(traversedStates.size());
		currentState.outputGrid();
		System.out.println();
		
		//Next possible states are retrieved based on the current state
		ArrayList<State> states = nextState(currentState);
		
		//Next possible states are checked to see if it exists recursively
		for (State nextState : states) {
			//If last node of the tree has been reach. It will backtrack to find the next working tree and output
			//That node, to know where it went back to
			if (!traversedStates.contains(nextState.getStateString()) && backTrack == true) {
				System.out.println("End of tree. backtracking to");
				currentState.outputGrid();
			}
			backTrack = combinations(nextState);
		}
		
		return true;
	}
	
	/*
	 * Gets the valid moves that can be made from the given state
	 * 
	 * @param	currentState - takes in a state to find the next possible moves
	 * @returns array of states
	 */
	public ArrayList<State> nextState(State currentState) {
		int[] position = currentState.getPosition();
		ArrayList<State> states = new ArrayList<State>();
		String[] stateCopy = new String[3];
		char[][] stateCharArray;
		char charswap;
		
		//Based on the x,y position of the empty space, checks if it can swap with an element above
		if (position[0] > 0 && position[0] <=2) {
			stateCopy = copy( currentState.getState() );
			stateCharArray = toListOfChars(stateCopy);
			charswap = stateCharArray[position[0] - 1][position[1]];
			stateCharArray[position[0] - 1][position[1]] = '_';
			stateCharArray[position[0]][position[1]] = charswap;
			stateCopy = charListToString(stateCharArray);
			states.add(new State( toStateString(stateCopy) ));
		}
		
		//Checks if a swap can be made below
		if (position[0] >= 0 && position[0] <2) {
			stateCopy = copy( currentState.getState() );
			stateCharArray = toListOfChars(stateCopy);
			charswap = stateCharArray[position[0] + 1][position[1]];
			stateCharArray[position[0] + 1][position[1]] = '_';
			stateCharArray[position[0]][position[1]] = charswap;
			stateCopy = charListToString(stateCharArray);
			states.add(new State( toStateString(stateCopy) ));
		}
		
		//Checks if a swap can be made to the left
		if (position[1] > 0 && position[1] <=2) {
			stateCopy = copy( currentState.getState() );
			stateCharArray = toListOfChars(stateCopy);
			charswap = stateCharArray[position[0]][position[1] - 1];
			stateCharArray[position[0]][position[1] - 1] = '_';
			stateCharArray[position[0]][position[1]] = charswap;
			stateCopy = charListToString(stateCharArray);
			states.add(new State( toStateString(stateCopy) ));
		}
		
		//Checks if a swap can be made to the right
		if (position[1] >= 0 && position[1] <2) {
			stateCopy = copy( currentState.getState() );
			stateCharArray = toListOfChars(stateCopy);
			charswap = stateCharArray[position[0]][position[1] + 1];
			stateCharArray[position[0]][position[1] + 1] = '_';
			stateCharArray[position[0]][position[1]] = charswap;
			stateCopy = charListToString(stateCharArray);
			states.add(new State( toStateString(stateCopy) ));
		}
		
		return states;
		
	}
	
	/*
	 * Gets the index position of the _ (empty space in the state) in the 2d array
	 * 
	 * @returns array of ints
	 */
	public int[] getPosition() {
		int[] position = new int[2];
		int index;
		
		//For each row in the 2d array, checks if it contains the _
		for (int i = 0; i < 3; i++) {
			index = this.getState()[i].indexOf("_");
			if (index > -1 ) {
				position[0] = i;
				position[1] = index;
			}
		}
		
		return position;
	}
	
	/*
	 * Creates a copy of a 2d array
	 * 
	 * @param	state - the state to copy
	 * @returns	2d array
	 */
	static public String[] copy(String[] state) {
		String[] stateCopy = { state[0], state[1], state[2] };
		return stateCopy;
	}
	
	/*
	 * Converts each character in the string array into an array of a single character
	 * 
	 * @param	state - state to convert
	 * @returns	2d array of characters
	 */
	static public char[][] toListOfChars(String[] state) {
		char[][] charArray = new char[3][3];
		for (int i = 0; i < 3; i++) {
			charArray[i] = state[i].toCharArray();
		}
		return charArray;

	}
	
	/*
	 * Converts 2d array of characters into array of strings
	 * 
	 * @param	charArray - 2d array of characters to convert
	 * @returns	array of strings
	 */
	static public String[] charListToString(char[][] charArray) {
		String[] state = { new String(charArray[0]), new String(charArray[1]), new String(charArray[2]) };
		return state;
	}
	
	/*
	 * Converts 
	 */
	static public String toStateString(String[] state) {
		return state[0] + state[1] + state[2];
	}

	public String getStateString() {
		return stateString;
	}
	
	public String[] getState() {
		return state;
	}
	
	public HashSet<String> getTraversedStates() {
		return traversedStates;
	}
	
	public void sizeOfTraversedStates() {
		System.out.println(traversedStates.size());
	}

	public void outputState() {
		System.out.println(this.state[0].toString());
		System.out.println(this.state[1].toString());
		System.out.println(this.state[2].toString());
	}
	
	public void outputGrid() {
		char[][] charArray = toListOfChars(state);
		System.out.println("| " + charArray[0][0] + " |" + "| " + charArray[0][1] + " |" + "| " + charArray[0][2] + " |");
		System.out.println("| " + charArray[1][0] + " |" + "| " + charArray[1][1] + " |" + "| " + charArray[1][2] + " |");
		System.out.println("| " + charArray[2][0] + " |" + "| " + charArray[2][1] + " |" + "| " + charArray[2][2] + " |\n");

	}
	
	static public String[] stringToState(String str) {
		String[] state = { str.substring(0, 3), str.substring(3, 6), str.substring(6, 9) };
		return state;
	}
	
	public void outputTree(int statesPerLine, String fileName) throws IOException {
		ArrayList<State> copyOfOutputTree = new ArrayList<State>(outputTree);
		ArrayList<State> copyOfStates = new ArrayList<State>();
		
		int noOfLines = Math.floorDiv(traversedStates.size(), statesPerLine);
		FileWriter file = new FileWriter(fileName);
		int i = 0;
		int j = 1;
		String str;
		
		while (i < noOfLines) {
			str = "";
			for (int k = 0; k < statesPerLine; k++) {
				State state =  copyOfOutputTree.get(k);
				char[][] charArray = toListOfChars(state.getState());
				copyOfStates.add(state);
				
				if (j < statesPerLine - 1) {
					str += "| " + charArray[0][0] + " |" + "| " + charArray[0][1] + " |" + "| " + charArray[0][2] + " |      ";
					j++;
				} 
				else {
					str +="| " + charArray[0][0] + " |" + "| " + charArray[0][1] + " |" + "| " + charArray[0][2] + " |      ";
					j = 0;
				}
			}
			str += "\n";
			file.write(str);
			str = "";
			
			for (State stateCopy: copyOfStates) {
				char[][] grid = toListOfChars(stateCopy.getState());

				if (j < statesPerLine - 1) {
					str += "| " + grid[1][0] + " |" + "| " + grid[1][1] + " |" + "| " + grid[1][2] + " |      ";
					j++;
				} 
				else {
					str += "| " + grid[1][0] + " |" + "| " + grid[1][1] + " |" + "| " + grid[1][2] + " |      ";
					j = 0;
				}
			}
			str += "\n";
			file.write(str);
			str = "";

			for (State stateCopy: copyOfStates) {
				char[][] grid = toListOfChars(stateCopy.getState());
				if (j < statesPerLine - 1) {
					str += "| " + grid[2][0] + " |" + "| " + grid[2][1] + " |" + "| " + grid[2][2] + " |      ";
					j++;
				} 
				else {
					str += "| " + grid[2][0] + " |" + "| " + grid[2][1] + " |" + "| " + grid[2][2] + " |      ";
					j = 0;
				}
			}
			
			str += "\n\n";
			file.write(str);
			str = "";
				
			for (State stateCopy: copyOfStates) {
				copyOfOutputTree.remove(stateCopy);
			}
			copyOfStates.clear();
			i++;
		}
		file.close();
		
	}
	
	static public String stateValidation(String input) {
		Scanner scanner = new Scanner(System.in);
		
		while ( input.length() != 9 || !input.contains("_") ) {
			if (input.length() != 9) {
				System.out.println("The state must have 9 characters.");
				System.out.print("Enter a valid State: ");
				input = scanner.next();
			}
			else if ( !input.contains("_") ) {
				System.out.println("The state must contain an underscore. _");
				System.out.print("Enter a valid State: ");
				input = scanner.next();
			}
			
		}
		
		return input;
	}
	
	static public HashSet<String> compare(State state1, State state2) {
		HashSet<String> distinctStates = new HashSet<String>();
		
		Iterator<String> set = state1.getTraversedStates().iterator();
		while (set.hasNext()) {
			String currentState = set.next();
			if (!state2.getTraversedStates().contains(currentState)) {
				distinctStates.add(currentState);
			}
		}
		
		return distinctStates;
	}
	
}
