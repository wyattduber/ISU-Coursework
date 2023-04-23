package lab3;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class ProfileTest {
	private Profile profile;
	private BooleanQuestion question;
	@Before
	public void create() {
		profile = new Profile("Bull Hockey, Inc.");
		question = new BooleanQuestion(1, "Got bonuses?");
	}

	@Test
	public void test() {
		Question question = new BooleanQuestion(1, "Got bonuses?");
		Criteria criteria = new Criteria();
		Answer criteriaAnswer = new Answer(question, Bool.TRUE);
		Criterion criterion = new Criterion(criteriaAnswer, Weight.MustMatch);
		criteria.add(criterion);
	}

	@Test
	public void matchAnswersTrueForAnyDontCareCriteria() {
		Answer profileAnswer = new Answer(question, Bool.FALSE);
		profile.add(profileAnswer);
		Answer criteriaAnswer = new Answer(question, Bool.TRUE);
		Criterion criterion = new Criterion(criteriaAnswer, Weight.DontCare);
		Criteria criteria = new Criteria();
		criteria.add(criterion);
		boolean matches = profile.matches(criteria);
		assertTrue(matches);
	}

	@Test
	public void matchAnswersFalseWhenMustMatchCriteriaNotMet() {
		Answer profileAnswer = new Answer(question, Bool.FALSE);
		profile.add(profileAnswer);
		Answer criteriaAnswer = new Answer(question, Bool.TRUE);
		Criterion criterion = new Criterion(criteriaAnswer, Weight.MustMatch);
		Criteria criteria = new Criteria();
		criteria.add(criterion);
		boolean matches = profile.matches(criteria);
		assertFalse(matches);
	}


}

