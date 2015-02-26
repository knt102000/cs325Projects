package tagger;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import utils.IntDoublePair;
import cs325.classifier.HiddenMarkov;

public class HMMTagger extends AbstractTagger{
	static public String START_STATE="START_STATE";
	static public String END_STATE="END_STATE";
	private HiddenMarkov hm_model;
	
	public HMMTagger(double epsilon) {
		hm_model=new HiddenMarkov(epsilon);
	}
	
	@Override
	public void addSentence(List<String> words,List<String> tags) {
		String currentState,previousState=START_STATE;
		int i,size=words.size();
		
		for (i=0;i<size;i++) {
			currentState=tags.get(i);
			hm_model.addTransition(previousState,currentState);
			hm_model.addObservation(currentState,words.get(i));
			previousState=currentState;
		}
	}
	
	@Override
	public void train() {
		hm_model.train();
	}
	
	@Override
	public List<TagList> decode(List<String> observations) {
		final int N=hm_model.getLabels().size();
		final int T=observations.size();
		
		double[][] viterbi=new double[N][T];
		int[][] backpointer=new int[N][T];
		
		IntDoublePair max;
		
		int s,t=0;
		
		for (s=0;s<N;s++) {
			viterbi[s][0]=hm_model.getOverallLikelihood(START_STATE, hm_model.getLabel(s), observations.get(t));
			backpointer[s][0]=0;
		}
		
		for (t=1;t<T;t++) {
			for (s=0;s<N;s++) {
				max=max(viterbi,t,hm_model.getLabel(s),observations.get(t),N);
				viterbi[s][t]=max.getDouble();
				backpointer[s][t]=max.getInt();
			}
		}
		
		max=max(viterbi,t,END_STATE,null,N);
		
		List<TagList> list=new ArrayList<>(1);
		list.add(getTags(backpointer,max.getInt(),N,t));
		
		return list;
	}
	
	private IntDoublePair max(double[][] viterbi,int t,String currentState,String observation,final int N) {
		int s=0;
		double d=viterbi[s][t-1]*hm_model.getOverallLikelihood(hm_model.getLabel(s),currentState, observation);
		IntDoublePair max=new IntDoublePair(s,d);
		
		for (s=0;s<N;s++) {
			d=viterbi[s][t-1]*hm_model.getOverallLikelihood(hm_model.getLabel(s), currentState, observation);
			if (d>max.getDouble()) max.set(s,d);
		}
		
		return max;
	}
	
	private TagList getTags(int[][] backpointer,int max,final int N,final int T) {
		List<String> tags=new ArrayList<>();
		
		tags.add(hm_model.getLabel(max));
		
		int t;
		
		for (t=T-1;t>0;t--) {
			max=backpointer[max][t];
			tags.add(hm_model.getLabel(max));
		}
		
		Collections.reverse(tags);
		return new TagList(tags);
	}
}
