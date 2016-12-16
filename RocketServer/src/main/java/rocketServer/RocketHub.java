package rocketServer;

import java.io.IOException;

import exceptions.RateException;
import netgame.common.Hub;
import rocketBase.RateBLL;
import rocketData.LoanRequest;


public class RocketHub extends Hub {

	private RateBLL _RateBLL = new RateBLL();
	
	public RocketHub(int port) throws IOException {
		super(port);
	}

	@Override
	protected void messageReceived(int ClientID, Object message) {
		System.out.println("Message Received by Hub");
		
		if (message instanceof LoanRequest) {
			resetOutput();
			
			LoanRequest lq = (LoanRequest) message;
			
			//	TODO - RocketHub.messageReceived

			//	You will have to:
			//	Determine the rate with the given credit score (call RateBLL.getRate)
			//		If exception, show error message, stop processing
			//		If no exception, continue
			//	Determine if payment, call RateBLL.getPayment
			//	
			//	you should update lq, and then send lq back to the caller(s)
			double pay = 0;
			try {
				_RateBLL.getRate(lq.getiCreditScore());
				/**
				 * n = The number of periods over which the loan or investment is to be paid: what method do i use to find this? is is getiTerm()?
				 * p = 
				 * t = An optional argument that defines whether the payment is made at the start or the end of the period. 
				 * 0 = end of period, 1 = beginning of period. which should i use?
				 * f = An optional argument that specifies the future value of the loan / investment, at the end of nper payments.
				 * has an original value of 0
				 */
				pay = Math.abs(_RateBLL.getPayment(_RateBLL.getRate(lq.getiCreditScore()), lq.getiTerm(), lq.getdAmount(), 0, false));
				
			} catch (RateException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			lq.setdPayment(pay);
			sendToAll(lq);
		}
	}
}
