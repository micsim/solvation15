package de.gwvprojekt.algorithm;

public class StateImpl extends DataImpl implements State{
	@Override
	public State move(int direction) throws CloneNotSupportedException {
		int[] tile_pos = getPos(16);
		
		switch(direction){
		case 1:
			tile_pos[0]--;
			break;
		case 2:
			tile_pos[1]--;
			break;
		case 3:
			tile_pos[1]++;
			break;
		case 4:
			tile_pos[0]++;
			break;
		}
		
		State state = (State) this.clone();
		state.move(tile_pos[0], tile_pos[1]);
		
		return state;
	}

	@Override
	public int[] getPossibleDirections() {
		int[] pos = getPos(16);

		if(pos[0] > 0 && pos[0] < 3 && pos[1] > 0 && pos[1] < 3){
			int[] arr = {1,2,3,4}; // All directions.
			return arr;
		}else if(pos[0] == 0){
			// In the first row.
			if(pos[1] > 0 && pos[1] < 3){
				int[] arr = {2, 3, 4}; // Not up.
				return arr;
			}else if(pos[1] == 0){
				// Upper left corner.
				int[] arr = {3, 4}; // Right or down.
				return arr;
			}else{
				// Upper right corner.
				int[] arr = {2, 3}; // Left or down.
				return arr;
			}
		}else if(pos[0] == 3){
			// In the last row.
			if(pos[1] > 0 && pos[1] < 3){
				int[] arr = {1, 2, 3}; // Not down.
				return arr;
			}else if(pos[1] == 0){
				// Lower left corner.
				int[] arr = {1, 3}; // Right or up.
				return arr;
			}else{
				// Lower right corner.
				int[] arr = {2, 3}; // Left or up.
				return arr;
			}
		}else if(pos[1] == 0){
			// In the first column. (Not at the edges.)
			int[] arr = {1, 3, 4}; // Up, down or right.
			return arr;
		}else{
			// In the last column. (Not at the edges.)
			int[] arr = {1, 2, 4}; // Up, down or left.
			return arr;
		}
	}
}
