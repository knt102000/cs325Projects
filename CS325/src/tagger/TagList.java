package tagger;

import java.util.ArrayList;
import java.util.List;

import cs325.classifier.Prediction;

public class TagList implements Comparable<TagList> {
	private List<String> tags;
	private double score;
	
	public TagList() {
		tags=new ArrayList<>();
		score=0;
	}
	
	public TagList(TagList p) {
		tags=new ArrayList<>(p.tags);
		score=p.score;
	}
	
	public TagList(List<String> list) {
		tags=new ArrayList<>(list);
		score=0;
	}
	
	public List<String> getTags() {
		return tags;
	}
	
	public String getTag(int index) {
		return tags.get(index);
	}
	
	public double getScore() {
		return score;
	}
	
	public void add(Prediction p) {
		tags.add(p.getLabel());
		score+=p.getScore();
	}
	
	@Override
	public int compareTo(TagList o) {
		return (int)Math.signum(score-o.score);
	}
	
	@Override
	public String toString() {
		return score+" "+tags.toString();
	}
}
