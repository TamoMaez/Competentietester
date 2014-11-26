package domain;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class QuestionTest {
	Category c1;
	Category c2;
	
	Question yn1;
	Option option1;
	Score score1;
	Score score2;
	
	Question yn2;
	Option option2;
	Score score3;
	Score score4;

	Question mcQ1;
	Option option3;
	Option option4;
	Option option5;
	Option option6;	
	Score score5;
	Score score6;
	Score score7;
	Score score8;
	
	
	
	
	
	@Before
	public void setUp() throws Exception {
		// Categories
		this.c1 = new Category("Science");
		this.c2 = new Category("Math");
		
		// Yes No Question 1
		this.yn1 = new YesNoQuestion("Is the Earth round?");
		this.option1 = new Option("Yes");
		this.option2 = new Option("No");
		yn1.addOption(option1);
		yn1.addOption(option2);
		yn1.addCategory(c1);
		this.score1 = new Score(2, 2, c1);
		this.score2 = new Score(2, 0, c1);
		option1.addScore(score1);
		option2.addScore(score2);
		
		
		// Yes No Question 2
		
		
		/*this.yesNoQuestion2 = new YesNoQuestion("Is the ocean smaller than 1 kilometer?");
		this.option2 = new Option("No");
		this.score3 = new Score(2, 2, c1);
		this.score4 = new Score(2, 2, c2);
		
		
		// MCQuestions
		this.mcQuestion1 = new MCQuestion("What is the square root of 36?");
		this.option3 = new Option("3");
		this.option4 = new Option("6");
		this.option5 = new Option("9");
		this.option6 = new Option("12");
		this.score5 = new Score(0, 2, c2);
		this.score6 = new Score(2, 2, c2);
		this.score7 = new Score(0, 2, c2);
		this.score8 = new Score(0, 2, c2);*/
	}

	/*@Test
	public void test_Account_Geldige_parameters_maakt_Object() {
	
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void test_Account_gegeven_nickName_null_gooit_exception() {
		 //new Account(null,emailAdres);
		
	}*/
	
	/*@Test
	public void test_yes_no_answer_met_correcte_antwoord() {
		Answer answer = new YesNoAnswer();
		answer.setQuestion(yn1);
		answer.addOption(option1);
		yn1.setAskedInCategory(c1);
		
		assertTrue(answer.isCorrect());
	}
	
	@Test
	public void test_yes_no_answer_met_foutief_antwoord() {
		Answer answer = new YesNoAnswer();
		answer.setQuestion(yn1);
		answer.addOption(option1);
		yn1.setAskedInCategory(c1);
		
		assertTrue(answer.isCorrect());
	}*/
	
	
	

}