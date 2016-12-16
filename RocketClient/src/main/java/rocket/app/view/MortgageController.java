package rocket.app.view;

import eNums.eAction;
import exceptions.RateException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import rocket.app.MainApp;
import rocketBase.RateBLL;
import rocketCode.Action;
import rocketData.LoanRequest;

public class MortgageController {

	private MainApp mainApp;
	
	//	TODO - RocketClient.RocketMainController
	
	//	Create private instance variables for:
	//		TextBox  - 	txtIncome
	//		TextBox  - 	txtExpenses
	//		TextBox  - 	txtCreditScore
	//		TextBox  - 	txtHouseCost
	//		ComboBox -	loan term... 15 year or 30 year
	//		Labels   -  various labels for the controls
	//		Button   -  button to calculate the loan payment
	//		Label    -  to show error messages (exception throw, payment exception)
	ObservableList<Integer> termList = FXCollections.observableArrayList(15, 30);
	
	@FXML
	private TextField txtIncome;
	@FXML
	private TextField txtExpenses;
	@FXML
	private TextField txtCreditScore;
	@FXML
	private TextField txtHouseCost;
	@FXML
	private TextField txtMortgage;
	@FXML
	private TextField txtDownPayment;  
	@FXML
	private ComboBox cmbTerm;
	@FXML
	private Label lblMortgagePayment;
	@FXML
	private Label creditScore;
	@FXML
	private Label term;
	@FXML
	private Label income;
	@FXML
	private Label loan_payment;
	@FXML
	private Label houseCost;
	@FXML
	private Label expenses;
	@FXML
	private Label down_payment;
	@FXML
	private Label final_payment;
	@FXML
	private Label piti_payment;
	@FXML
	private Button loan_pay;
	
	

	public void setMainApp(MainApp mainApp) {
		this.mainApp = mainApp;
	}
	
	@FXML
	private void initialize(){
		cmbTerm.getItems().addAll(termList);
	
	}
	//	TODO - RocketClient.RocketMainController
	//			Call this when btnPayment is pressed, calculate the payment
	@FXML
	public void btnCalculatePayment(ActionEvent event)
	{
		Object message = null;
		//	TODO - RocketClient.RocketMainController
		
		Action a = new Action(eAction.CalculatePayment);
		LoanRequest lq = new LoanRequest();
		//	TODO - RocketClient.RocketMainController
		//			set the loan request details...  rate, term, amount, credit score, downpayment
		//			I've created you an instance of lq...  execute the setters in lq
		lq.setiTerm((Integer.parseInt(cmbTerm.toString()))*12);
		try {
			lq.setdRate(RateBLL.getRate(lq.getiCreditScore()));
		} catch (RateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		lq.setdAmount(Integer.parseInt(txtHouseCost.toString()));
		lq.setiCreditScore(Integer.parseInt(txtCreditScore.toString()));
		lq.setiDownPayment(Integer.parseInt(txtDownPayment.toString()));

		a.setLoanRequest(lq);
		
		//	send lq as a message to RocketHub		
		mainApp.messageSend(lq);
	}
	
	public void HandleLoanRequestDetails(LoanRequest lRequest)
	{
		//	TODO - RocketClient.HandleLoanRequestDetails
		//			lRequest is an instance of LoanRequest.
		//			after it's returned back from the server, the payment (dPayment)
		//			should be calculated.
		//			Display dPayment on the form, rounded to two decimal places
		
		double payment = ((double)Math.round((lRequest.getdPayment())*100d))/100d;
		final_payment.setText(Double.toString(payment));
		double i = (double)(Integer.parseInt(txtIncome.toString())* 0.28);
		double o = (double)((Integer.parseInt(txtIncome.toString())* 0.36) - Integer.parseInt(txtExpenses.toString()));
		if(i < o){
			piti_payment.setText(Double.toString(i));
		}
		else{
			piti_payment.setText(Double.toString(o));
		}
		
		
	}
}
