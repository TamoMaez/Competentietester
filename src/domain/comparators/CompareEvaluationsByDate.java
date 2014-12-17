/**
 * 
 * @author Ruben Thielemans, Tamo Maes, Georges Petrofski & Sam Hendrickx
 *
 */
package domain.comparators;

import java.util.Comparator;

import domain.Evaluation;

public class CompareEvaluationsByDate implements Comparator<Evaluation> {

	@Override
	public int compare(Evaluation o1, Evaluation o2) {
		return o2.getDate().compareTo(o1.getDate());
	}

}
