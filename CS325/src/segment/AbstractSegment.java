package segment;

import model.ILanguageModel;

public abstract class AbstractSegment {
	protected ILanguageModel l_model;
	
	public AbstractSegment(ILanguageModel model) {
		l_model=model;
	}
	
	public abstract Sequence segment(String str);
}
