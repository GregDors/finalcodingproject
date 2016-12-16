package exceptions;

import rocketDomain.RateDomainModel;

public class RateException extends Exception {
	private RateDomainModel b;

	public RateException(RateDomainModel b) {
		this.b = b;
	} 

	public RateDomainModel getB() {
		return b;
	}
	
	//	TODO - RocketBLL RateException - RateDomainModel should be an attribute of RateException
	//	* Add RateRomainModel as an attribute
	//	* Create a constructor, passing in RateDomainModel
	//	* Create a getter (no setter, set value only in Constructor)
}
