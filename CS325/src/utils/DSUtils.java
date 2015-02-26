package utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import cs325.classifier.StringFeature;

public class DSUtils {
	@SuppressWarnings("unchecked")
	static public <T>List<T> createList(T... keys) {
		List<T> list=new ArrayList<>(keys.length);
		
		for (T key:keys) list.add(key);
		
		return list;
	}
	
	static public <T extends Comparable<T>>List<T> getBestList(List<T> list) {
		List<T> best=new ArrayList<>();
		T max=Collections.max(list);
		
		for (T key:list) if (key.compareTo(max)==0) best.add(key);
		
		return best;
	}
	
	static public List<StringFeature> createFeatureVector(String... typeValue) {
		List<StringFeature> features=new ArrayList<>();
		int len=typeValue.length;
		
		for (int i=0;i<len;i+=2) features.add(new StringFeature(typeValue[i],typeValue[i+1]));
	
		return features;
	}
}
