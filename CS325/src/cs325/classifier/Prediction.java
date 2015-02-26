package cs325.classifier;

public class Prediction implements Comparable<Prediction>
{
	private String label;
	private double score;

	public Prediction(String label, double score)
	{
		setLabel(label);
		setScore(score);
	}
	
	public Prediction(Prediction p)
	{
		setLabel(p.getLabel());
		setScore(p.getScore());
	}
	
	public String getLabel()
	{
		return label;
	}
	
	public void setLabel(String label)
	{
		this.label = label;
	}
	
	public double getScore()
	{
		return score;
	}
	
	public void setScore(double score)
	{
		this.score = score;
	}
	
	public void addScore(double score)
	{
		this.score += score;
	}

	@Override
	public int compareTo(Prediction o)
	{
		return (int)Math.signum(score - o.score);
	}
	
	@Override
	public String toString()
	{
		return label+":"+score;
	}
}
