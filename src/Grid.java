
public class Grid {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		State state1 = new State("abcdefgh_");
		State state2 = new State("abcdefg_h");
		//System.out.println(state1.getStateGrid()[0]);
//		ArrayList<State> states = state1.nextState();
//		for (State state : states) {
//			state.outputGrid();
//			System.out.println("");
//		}
		state1.combinations(state1, 10);
		state2.combinations(state2, 10);
		State.compare(state1, state2);
	}

}
