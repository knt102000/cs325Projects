package model;

public interface ILanguageModel {
	double getLikelihood(String word1,String word2);
}
