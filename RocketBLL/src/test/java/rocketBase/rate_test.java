package rocketBase;

import static org.junit.Assert.*;

import org.junit.Test;

import exceptions.RateException;

public class rate_test {

	//TODO - RocketBLL rate_test
	//		Check to see if a known credit score returns a known interest rate
	
	//TODO - RocketBLL rate_test
	//		Check to see if a RateException is thrown if there are no rates for a given
	//		credit score
	@Test
	public void test() throws RateException {
		RateBLL rate = new RateBLL();
		double dpay = rate.getPayment((0.04)/12, 360, 300000, 0, false);
		double rr = rate.getRate(600);
		
		assertEquals(rr,5.0,0.1);
		assertEquals(Math.abs(dpay), Math.abs(1432.25), 0.01);
	}

}
