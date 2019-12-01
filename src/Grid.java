
public class Grid {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		State state1 = new State("abcdefgh_");
		//System.out.println(state1.getStateGrid()[0]);
//		ArrayList<State> states = state1.nextState();
//		for (State state : states) {
//			state.outputGrid();
//			System.out.println("");
//		}
		state1.outputGrid();
		state1.combinations(state1);
		state1.sizeOfTraversedStates();

	}

}
