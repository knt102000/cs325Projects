package ngrams;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.regex.Pattern;

public class IOUtils {
	static public void readUnigrams(Unigram unigram, InputStream in) {
		try
		{
			BufferedReader reader = new BufferedReader(new InputStreamReader(in));
			Pattern p = Pattern.compile("\t");
			String line;
			String[] t;
			
			while ((line = reader.readLine()) != null)
			{
				t = p.split(line.toLowerCase());
				unigram.add(t[1], Long.parseLong(t[0]));
			}
			unigram.estimateMaximumLikelihoods();
		}
		catch (IOException e) {e.printStackTrace();}
	}
	
	static public void readBigrams(Bigram bigram, InputStream in) {
		try
		{
			BufferedReader reader = new BufferedReader(new InputStreamReader(in));
			Pattern p = Pattern.compile("\t");
			String line;
			String[] t;
			
			while ((line = reader.readLine()) != null)
			{
				t = p.split(line.toLowerCase());
				bigram.add(t[1], t[2], Long.parseLong(t[0]));
			}
			
			bigram.estimateMaximumLikelihoods();
		}
		catch (IOException e) {e.printStackTrace();}
	}
}