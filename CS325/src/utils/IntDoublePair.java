package utils;

public class IntDoublePair {
	private int o1;
	private double o2;
	
	public IntDoublePair(int o1, double p2)
	{
		set(o1, o2);
	}

	public int getInt()
	{
		return o1;
	}
	
	public double getDouble()
	{
		return o2;
	}
	
	public void set(int o1, double o2)
	{
		setInt (o1);
		setDouble(o2);
	}
	
	public void setInt(int o)
	{
		o1 = o;
	}
	
	public void setDouble(double o)
	{
		o2 = o;
	}
}
