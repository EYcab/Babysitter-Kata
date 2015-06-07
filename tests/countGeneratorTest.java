import static org.junit.Assert.*;

import org.junit.Test;


public class countGeneratorTest {
	@Test
	public void startsAt5PM() throws Exception {
		assertEquals(5, new countGenerator().getStartingTimeFromInput(5));
	}

	@Test
	public void TimeDifferecesByNonIntegerHours() throws Exception{
		assertEquals(0, new countGenerator().getTimeDiffereces(0));
	}
}

