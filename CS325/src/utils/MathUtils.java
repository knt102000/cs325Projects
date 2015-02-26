package utils;

import java.util.Collection;

public class MathUtils {
	static public double average(Collection<Double> col) {
		return sum(col)/col.size();
	}
	
	static public double sum(Collection<Double> col) {
		double sum=0;
		for (double key:col)
			sum+=key;
		return sum;
	}
	
	static public double divide(int numerator, int denominator) { //Divides numerator by the denominator and returns result in decimal form
		return (double)numerator/denominator;
	}
	
	static public void main(String[] args) { //Prints the division result of 1/2, should print out 0.5
		System.out.println(divide(1,2));
	}
}
