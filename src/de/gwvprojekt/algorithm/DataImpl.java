package de.gwvprojekt.algorithm;

import java.util.Random;

import de.gwvprojekt.ui.Data;

public class DataImpl implements Data
{
	private int[][] _matrix;
	private Random _random;
	

	public DataImpl()
	{
		_matrix = new int[4][4];
		_random = new Random();
		initializeData();
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
		_random.setSeed(System.nanoTime());
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
	
	private void swap(int a1, int a2, int b1, int b2)
	{
		int c;
		c = _matrix[a1][a2];
		_matrix[a1][a2] = _matrix[b1][b2];
		_matrix[b1][b2] = c;
	}
	
	private int[] get16Position()
	{
		return getPos(16);
	}
}
