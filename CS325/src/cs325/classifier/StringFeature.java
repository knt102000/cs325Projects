package cs325.classifier;

public class StringFeature
{
	private String type;
	private String value;
	
	public StringFeature(String type, String value)
	{
		setType(type);
		setValue(value);
	}

	public String getType()
	{
		return type;
	}

	public void setType(String type)
	{
		this.type = type;
	}

	public String getValue()
	{
		return value;
	}

	public void setValue(String value)
	{
		this.value = value;
	}
}
