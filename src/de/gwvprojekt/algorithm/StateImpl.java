package de.gwvprojekt.algorithm;

import java.util.Random;

public class StateImpl implements State, Cloneable{
	// Felder
	private byte[][] _matrix;
	
	/**
	 * Konstruktor der Klasse DataImpl, die das byteerface Data implementiert
	 */
	public StateImpl()
	{
		_matrix = new byte[4][4];
		initializeData();
	}
	
	public StateImpl(byte[][] matrix){
		_matrix = matrix;
	}
	
	@Override
	public String getStringValue(byte row, byte column)
	{
		return ""+_matrix[row][column];
	}

	@Override
	public void move(byte row, byte column)
	{
		if(isMovable(row, column))
		{

			byte[] pos16 = get16Position();
			swap(row, column, pos16[0], pos16[1]);
		}
	}

	@Override
	public boolean isMovable(byte row, byte column)
	{
		boolean isOutOfBounds = !(row >= 0 && row <= 3 && column >= 0 && column <= 3);
		assert !isOutOfBounds : "Out of bounds!";
		// TODO Why does the above assert not work?
		if(isOutOfBounds)
			return false;
		
		boolean result = false;
		byte[] pos16 = get16Position();		
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
		byte a = 1;
		
		for (byte i = 0; i < 4; i++)
		{
			for (byte j = 0; j < 4; j++)
			{
				//System.out.prbyteln(a);
				_matrix[i][j] = a;
				a++;
			}
		}
	}
	
	public void randomizeData(Random random){
		random.setSeed(System.nanoTime());

		byte swapnumber = 50; 
		for (byte i = 0; i < swapnumber; i++)
		{
			byte a1, a2, b1, b2;
			do{
				a1 = (byte) random.nextInt(4);
				a2 = (byte) random.nextInt(4);
				b1 = (byte) random.nextInt(4);
				b2 = (byte) random.nextInt(4);
			}while((a1==b1)&&(a2==b2));
			
			swap(a1,a2,b1,b2);
		}
	}
	
	public byte getValue(byte row, byte column) {
		return _matrix[row][column];
	}

	public byte[] getPos(byte value)
	{
		byte[] result = new byte[2];
		
		for(byte i = 0; i < 4; i++)
		{
			for (byte j = 0; j < 4; j++)
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
	public State move(byte direction) throws CloneNotSupportedException {
		byte[] tile_pos = getPos((byte) 16);
		
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
			byte[][] otherMatrix = ((StateImpl) o)._matrix;
			
			for (byte i = 0; i < 4; i++)
			{
				for (byte j = 0; j < 4; j++)
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
		byte[][] newMatrix = new byte[4][];
		for(byte i=0;i<4;i++){
			newMatrix[i] = _matrix[i].clone();
		}
		
		return new StateImpl(newMatrix);
	}

	@Override
	public byte[] getPossibleDirections() {
		byte[] pos = getPos((byte) 16);

		if(pos[0] > 0 && pos[0] < 3 && pos[1] > 0 && pos[1] < 3){
			byte[] arr = {1,2,3,4}; // All directions.
			return arr;
		}else if(pos[0] == 0){
			// In the first row.
			if(pos[1] > 0 && pos[1] < 3){
				byte[] arr = {2, 3, 4}; // Not up.
				return arr;
			}else if(pos[1] == 0){
				// Upper left corner.
				byte[] arr = {3, 4}; // Right or down.
				return arr;
			}else{
				// Upper right corner.
				byte[] arr = {3, 4}; // Left or down.
				return arr;
			}
		}else if(pos[0] == 3){
			// In the last row.
			if(pos[1] > 0 && pos[1] < 3){
				byte[] arr = {1, 2, 3}; // Not down.
				return arr;
			}else if(pos[1] == 0){
				// Lower left corner.
				byte[] arr = {1, 3}; // Right or up.
				return arr;
			}else{
				// Lower right corner.
				byte[] arr = {1, 2}; // Left or up.
				return arr;
			}
		}else if(pos[1] == 0){
			// In the first column. (Not at the edges.)
			byte[] arr = {1, 3, 4}; // Up, down or right.
			return arr;
		}else{
			// In the last column. (Not at the edges.)
			byte[] arr = {1, 2, 4}; // Up, down or left.
			return arr;
		}
	}
	
	public byte getNumberOfMisplacedTiles(){
		byte number = 0;
		byte a = 1;
		
		for (byte i = 0; i < 4; i++)
		{
			for (byte j = 0; j < 4; j++)
			{
				if(_matrix[i][j] != a){
					number++;
				}
				a++;
			}
		}
		
		return number;
	}
	
	public int getManhattanSum(){
		int sum = 0;
		for(int i=0; i<4; i++){
			for(int j=0; j<4; j++){
				int value = _matrix[i][j] - 1;
				int row = value/4;
				int column = value - row*4;
				
				sum += Math.abs(row - i) + Math.abs(column - j);
			}
		}
		
		return sum;
	}
	
	
	/**
	 * Vertausch die Matrixfelder [a1][a2] mit dem Feld [b1][b2]
	 * @param a1
	 * @param a2
	 * @param b1
	 * @param b2
	 */
	private void swap(byte a1, byte a2, byte b1, byte b2)
	{
		byte c;
		c = _matrix[a1][a2];
		_matrix[a1][a2] = _matrix[b1][b2];
		_matrix[b1][b2] = c;
	}
	
	/**
	 * Liefert die Position des leeren Feldes.
	 * @return
	 */
	private byte[] get16Position()
	{
		return getPos((byte) 16);
	}
}
