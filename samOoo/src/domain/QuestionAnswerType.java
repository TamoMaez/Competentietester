package domain;

import java.util.ArrayList;

public enum QuestionAnswerType {
	YesNo("JaNeenVraag"), MC("MeerkeuzeVraag");
	
	private final String text;

    /**
     * @param text
     */
    private QuestionAnswerType(final String text) {
        this.text = text;
    }

    /* (non-Javadoc)
     * @see java.lang.Enum#toString()
     */
    @Override
    public String toString() {
        return text;
    }
    
    public static ArrayList<String> getQuestionTypes(){
    	ArrayList<String> types = new ArrayList<>();
    	for(QuestionAnswerType type : values()){
    		types.add(type.toString());
    	}
    	return types;
    }
    
    public static QuestionAnswerType enumOf(String string){
        for(QuestionAnswerType t : values()){
            if( t.toString().equals(string)){
                return t;
            }
        }
        return null;
    }
}
