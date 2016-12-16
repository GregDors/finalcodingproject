package rocketBase;

import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.formula.functions.*;
import org.hibernate.Session;
import org.hibernate.Transaction;

import exceptions.RateException;
import rocketDomain.RateDomainModel;
import util.HibernateUtil;
import java.util.Collections;
import java.util.Comparator;


public class RateBLL {

	private static RateDAL _RateDAL = new RateDAL();
	
	public static double getRate(int GivenCreditScore) throws RateException 
	{
		//TODO - RocketBLL RateBLL.getRate - make sure you throw any exception
		
		//		Call RateDAL.getAllRates... this returns an array of rates
		//		write the code that will search the rates to determine the 
		//		interest rate for the given credit score
		//		hints:  you have to sort the rates...  you can do this by using
		//			a comparator... or by using an OrderBy statement in the HQL
		double rte = 0;
		ArrayList<RateDomainModel> allRates = _RateDAL.getAllRates();
		for(RateDomainModel rr : allRates){
			if(GivenCreditScore >= rr.getiMinCreditScore()){
				rte = rr.getdInterestRate();
			}
			else if(GivenCreditScore == 0){
				throw new RateException(rr);
				
			}
		}
		return rte;
		
		
	}
	
	//TODO - RocketBLL RateBLL.getPayment 
	//		how to use:
	//		https://poi.apache.org/apidocs/org/apache/poi/ss/formula/functions/FinanceLib.html
	
	public static double getPayment(double r, double n, double p, double f, boolean t)
	{		
		return FinanceLib.pmt(r, n, p, f, t);
	}
}
