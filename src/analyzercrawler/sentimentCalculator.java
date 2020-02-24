package analyzercrawler;

/* 
This class contains all the miscellaneous methods, variables & fields to calculate sentiment
*/

public class sentimentCalculator
{
	private int positive, positivec;
	private int negative, negativec;
	private int neutralc;
	/*
	 * 	Default Constructor
	 */
	public sentimentCalculator()
	{}
	/*
	 * 	Main Constructor
	 */
	public sentimentCalculator(String[] commentArr)
	{
		String placeholder;
		for(int i = 0; i < commentArr.length; i++)
		{
			if(commentArr[i].contains("score"))
			{
				placeholder = commentArr[i].replaceAll("\\D+","");
				if(Integer.parseInt(placeholder) < 0)
				{
					this.negative += Integer.parseInt(placeholder);
					this.negativec++;
				}
				else if(Integer.parseInt(placeholder) > 0)
				{
					this.positive += Integer.parseInt(placeholder);
					this.positivec++;
				}
				else
				{
					this.neutralc++;
				}
			}
		}
	}
	/*
	 * 	Below are methods that will be called to retrieve the calculated sentiments
	 */
	public int positive()
	{
		return this.positive;
	}
	public int positivec()
	{
		return this.positivec;
	}
	public int negative()
	{
		return this.negative;
	}
	public int negativec()
	{
		return this.negativec;
	}
	public int neutralc()
	{
		return this.neutralc;
	}
	public String insights()
	{
		if(this.positive > this.negative)
		{
			return "The general sentiment is positive with " + this.positivec + " being positive, " + this.negativec + " being negative and " + this.neutralc + " sitting on the fence.";
		}
		else if(this.positive == this.negative)
		{
			return "The general sentiment is neutral with " + this.positivec + " being positive, " + this.negativec + " being negative and " + this.neutralc + " sitting on the fence.";
		}
		else
		{
			return "The general sentiment is negative with " + this.positivec + " being positive, " + this.negativec + " being negative and " + this.neutralc + " sitting on the fence.";
		}
	}
}
