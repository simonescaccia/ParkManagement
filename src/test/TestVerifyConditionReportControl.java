package test;

import static org.junit.Assert.assertEquals;
import org.junit.Test;
import logic.control.controlapplicativo.VerifyConditionReportControl;

public class TestVerifyConditionReportControl {

	@Test
	public void testDistanceInKmBetweenEarthCoordinatesNearPlaces() {
		VerifyConditionReportControl vCRC = new VerifyConditionReportControl();
		double result = vCRC.distanceInKmBetweenEarthCoordinates(41.64369684, 13.3472999999, 41.6428, 13.3465);
		
		assertEquals(0.12, result, 0.01);
	}
	
}
