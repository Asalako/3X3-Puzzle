import java.util.*;

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
	 * @returns	
	 * 
	 */
	public boolean combinations(State currentState) {
		//The current state is checked against every state that's been visited, returns if it already exists
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
		
		//Next possible states are retrieved, then checked if it exists recursively
		ArrayList<State> states = nextState(currentState);
		
		for (State nextState : states) {
			if (!traversedStates.contains(nextState.getStateString()) && backTrack == true) {
				System.out.println("back to");
				currentState.outputGrid();
			}
			backTrack = combinations(nextState);
		}
		
		return true;
	}
	
	public ArrayList<State> nextState(State currentState) {
		int[] position = currentState.getPosition();
		ArrayList<State> states = new ArrayList<State>();
		String[] stateCopy = new String[3];
		char[][] stateCharArray;
		char charswap;
		
		//Checks if an element is above the current position, to then make a swap. A new state is created and stored
		if (position[0] > 0 && position[0] <=2) {
			stateCopy = copy( currentState.getState() );
			stateCharArray = toListOfChars(stateCopy);
			charswap = stateCharArray[position[0] - 1][position[1]];
			stateCharArray[position[0] - 1][position[1]] = '_';
			stateCharArray[position[0]][position[1]] = charswap;
			stateCopy = charListToString(stateCharArray);
			states.add(new State( toStateString(stateCopy) ));
		}
		
		//Checks if an element is below...
		if (position[0] >= 0 && position[0] <2) {
			stateCopy = copy( currentState.getState() );
			stateCharArray = toListOfChars(stateCopy);
			charswap = stateCharArray[position[0] + 1][position[1]];
			stateCharArray[position[0] + 1][position[1]] = '_';
			stateCharArray[position[0]][position[1]] = charswap;
			stateCopy = charListToString(stateCharArray);
			states.add(new State( toStateString(stateCopy) ));
		}
		
		//Checks if an element is to the left
		if (position[1] > 0 && position[1] <=2) {
			stateCopy = copy( currentState.getState() );
			stateCharArray = toListOfChars(stateCopy);
			charswap = stateCharArray[position[0]][position[1] - 1];
			stateCharArray[position[0]][position[1] - 1] = '_';
			stateCharArray[position[0]][position[1]] = charswap;
			stateCopy = charListToString(stateCharArray);
			states.add(new State( toStateString(stateCopy) ));
		}
		
		//Checks if an element is to the right
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
	
	public int[] getPosition() {
		int[] position = new int[2];
		int index;
		for (int i = 0; i < 3; i++) {
			index = this.getState()[i].indexOf("_");
			if (index > -1 ) {
				position[0] = i;
				position[1] = index;
			}
		}
		
		return position;
	}
	
	static public String[] copy(String[] state) {
		String[] stateCopy = { state[0], state[1], state[2] };
		return stateCopy;
	}
	
	static public char[][] toListOfChars(String[] state) {
		char[][] charArray = new char[3][3];
		for (int i = 0; i < 3; i++) {
			charArray[i] = state[i].toCharArray();
		}
		return charArray;

	}
	
	static public String[] charListToString(char[][] charArray) {
		String[] state = { new String(charArray[0]), new String(charArray[1]), new String(charArray[2]) };
		return state;
	}
	
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
		System.out.println("| " + charArray[2][0] + " |" + "| " + charArray[2][1] + " |" + "| " + charArray[2][2] + " |");

	}
	
	static public String[] stringToState(String str) {
		String[] state = new String[3];
		state[0] = str.substring(0, 3);
		state[1] = str.substring(3, 6);
		state[2] = str.substring(6, 9);
		return state;
	}
	
	public void outputTree(int statesPerLine) {
		ArrayList<State> copyOfOutputTree = new ArrayList<State>(outputTree);
		ArrayList<State> copyOfStates = new ArrayList<State>();
		
		int noOfLines = Math.floorDiv(traversedStates.size(), statesPerLine);
		System.out.println(noOfLines);
		int i = 0;
		int j = 1;
		
		while (i < noOfLines) {
			for (int k = 0; k < statesPerLine; k++) {
				State state =  copyOfOutputTree.get(k);
				char[][] charArray = toListOfChars(state.getState());
				copyOfStates.add(state);
				
				if (j < statesPerLine - 1) {
					System.out.print("| " + charArray[0][0] + " |" + "| " + charArray[0][1] + " |" + "| " + charArray[0][2] + " |      ");
					j++;
				} 
				else {
					System.out.print("| " + charArray[0][0] + " |" + "| " + charArray[0][1] + " |" + "| " + charArray[0][2] + " |      ");
					j = 0;
				}
			}
			System.out.println();

			for (State stateCopy: copyOfStates) {
				char[][] grid = toListOfChars(stateCopy.getState());

				if (j < statesPerLine - 1) {
					System.out.print("| " + grid[1][0] + " |" + "| " + grid[1][1] + " |" + "| " + grid[1][2] + " |      ");
					j++;
				} 
				else {
					System.out.print("| " + grid[1][0] + " |" + "| " + grid[1][1] + " |" + "| " + grid[1][2] + " |      ");
					j = 0;
				}
			}
			System.out.println();

			for (State stateCopy: copyOfStates) {
				char[][] grid = toListOfChars(stateCopy.getState());
				if (j < statesPerLine - 1) {
					System.out.print("| " + grid[2][0] + " |" + "| " + grid[2][1] + " |" + "| " + grid[2][2] + " |      ");
					j++;
				} 
				else {
					System.out.print("| " + grid[2][0] + " |" + "| " + grid[2][1] + " |" + "| " + grid[2][2] + " |      ");
					j = 0;
				}
			}
			
			System.out.println();
			System.out.println();
				
			for (State stateCopy: copyOfStates) {
				copyOfOutputTree.remove(stateCopy);
			}
			copyOfStates.clear();
			i++;
		}
		
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
		System.out.println(distinctStates.size());
		return distinctStates;
	}
	
}
