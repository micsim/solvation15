

public interface Data
{
	public String get(int row, int column);

	public void move(int row, int column);

	public boolean isMovable(int row, int column);

	public void initializeData();
}
