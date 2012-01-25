package de.gwvprojekt.algorithm;

import java.util.Random;

public class StateImpl implements State, Cloneable{
	// Felder
	private int[][] _matrix;
	private Random _random;
	
	/**
	 * Konstruktor der Klasse DataImpl, die das Interface Data implementiert
	 */
	public StateImpl()
	{
		_matrix = new int[4][4];
		_random = new Random();
		initializeData();
	}
	
	public StateImpl(int[][] matrix){
		_matrix = matrix;
		_random = new Random();
	}
	
	@Override
	public String getStringValue(int row, int column)
	{
		return ""+_matrix[row][column];
	}

	@Override
	public void move(int row, int column)
	{
		if(isMovable(row, column))
		{
			int[] pos16 = get16Position();
			swap(row, column, pos16[0], pos16[1]);
		}
	}

	@Override
	public boolean isMovable(int row, int column)
	{
		boolean result = false;
		int[] pos16 = get16Position();		
		if((pos16[0]-1 == row && pos16[1] == column)
				||(pos16[0]+1 == row && pos16[1] == column)
				||(pos16[0] == row && pos16[1]-1 == column)
				||(pos16[0] == row && pos16[1]+1 == column))
		{
			result = true;
		}
		return result;
	}

	@Override
	public void initializeData()
	{
		int a = 1;
		
		for (int i = 0; i < 4; i++)
		{
			for (int j = 0; j < 4; j++)
			{
				//System.out.println(a);
				_matrix[i][j] = a;
				a++;
			}
		}
	}
	
	public void randomizeData(){
		_random.setSeed(System.nanoTime());

		int swapnumber = 50; 
		for (int i = 0; i < swapnumber; i++)
		{
			int a1, a2, b1, b2;
			do{
				a1 = _random.nextInt(4);
				a2 = _random.nextInt(4);
				b1 = _random.nextInt(4);
				b2 = _random.nextInt(4);
			}while((a1==b1)&&(a2==b2));
			
			swap(a1,a2,b1,b2);
		}
	}
	
	public int getValue(int row, int column) {
		return _matrix[row][column];
	}

	public int[] getPos(int value)
	{
		int[] result = new int[2];
		
		for(int i = 0; i < 4; i++)
		{
			for (int j = 0; j < 4; j++)
			{
				if(_matrix[i][j] == value)
				{
					result[0] = i;
					result[1] = j;
					return result;
				}
			}
		}
		return null;
	}

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
	
	public State goalState(){
		return new StateImpl();
	}
	
	@Override
	public boolean equals(Object o){
		if(o instanceof StateImpl){
			int[][] otherMatrix = ((StateImpl) o)._matrix;
			
			for (int i = 0; i < 4; i++)
			{
				for (int j = 0; j < 4; j++)
				{
					if(_matrix[i][j] != otherMatrix[i][j]){
						return false;
					}
				}
			}
			
			return true;
		}else
			return super.equals(o);
	}
	
	@Override
	public Object clone() throws CloneNotSupportedException{
		int[][] newMatrix = new int[4][];
		for(int i=0;i<4;i++){
			newMatrix[i] = _matrix[i].clone();
		}
		
		return new StateImpl(newMatrix);
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
	
	public int getNumberOfMisplacedTiles(){
		int number = 0;
		int a = 1;
		
		for (int i = 0; i < 4; i++)
		{
			for (int j = 0; j < 4; j++)
			{
				if(_matrix[i][j] != a){
					number++;
				}
				a++;
			}
		}
		
		return number;
	}
	
	
	/**
	 * Vertausch die Matrixfelder [a1][a2] mit dem Feld [b1][b2]
	 * @param a1
	 * @param a2
	 * @param b1
	 * @param b2
	 */
	private void swap(int a1, int a2, int b1, int b2)
	{
		int c;
		c = _matrix[a1][a2];
		_matrix[a1][a2] = _matrix[b1][b2];
		_matrix[b1][b2] = c;
	}
	
	/**
	 * Liefert die Position des leeren Feldes.
	 * @return
	 */
	private int[] get16Position()
	{
		return getPos(16);
	}
}
