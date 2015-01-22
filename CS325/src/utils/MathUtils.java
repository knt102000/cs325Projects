package utils;

public class MathUtils {
	static public double divide(int numerator, int denominator) { //Divides numerator by the denominator and returns result in decimal form
		return (double)numerator/denominator;
	}
	
	static public void main(String[] args) { //Prints the division result of 1/2, should print out 0.5
		System.out.println(divide(1,2));
	}
}
