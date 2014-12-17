/**
 * 
 * @author Ruben Thielemans, Tamo Maes, Georges Petrofski & Sam Hendrickx
 *
 */
package domain;

import java.util.Comparator;

public class CompareByTimesAsked implements Comparator<Question> {

	@Override
	public int compare(Question q1, Question q2) {
		return q1.getTimesAnswered() - q2.getTimesAnswered();
	}

}
