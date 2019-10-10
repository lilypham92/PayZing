package Default_Theme;

public class xpath {
	
//Login screen 1
	String userName = "//input[@id='u']";
	String passWord = "//input[@id='p']";
	String loginBT = "//button[@id='login_submit_btn']";
	
//Login screen 2
	String ServerGroup = "//span[text()[contains(.,'Chọn cụm máy chủ')]]";
	String firstSVGroup = "//ul/li[1]";
	String userInfo = "//div[@class='user-info']";

	String server = "//span[text()[contains(.,'Chọn máy chủ')]]";
	String StartSV = "//span[text()[contains(.,'";
	String endSV = "')]]";
	String loginBT2 = "//button[@id='authBtnSubmit']";
// Home
	String pack = "//div[@class='am-gallery-item']";
//ZingCard section
	String zingCardBT = "//h4[@id='txtpaymentMethodcard']";
	String seriCardTxt = "//input[@id='txtCardSerialcard']";
	String passCardTxt = "//input[@id='txtCardPincard']";
	String confirmZingBT = "//button[@id = 'btnSubmitPaymentElementcard']";
//Result screen
	String errorMS = "//div[@id='txtSubmitErrorMessage']";
	String successMS = "//span[@id='txtResult']";
	String continueLink ="//a[@onclick='WP.payResume()']";
//ZingCard site
	String runBT= "//button[@id='submit']";
	String seriCode = "//tbody/tr/td[1]";
	String passCode = "//tbody/tr/td[2]";
//ZaloPay
	String zaloPayBT ="//h4[@id='txtpaymentMethodzalopay']";
	String zaloPaytxt = "//input[@id='txtAmountDynamicInputzalopay']";
	String confirmZaloPayBT = "//button[@id='btnSubmitPaymentElementzalopay']";
	String transID = "//span[@data-bind='text: gtCore.mess.PaymentOrderContent']";
	
// ATM
	String atmBT = "//h4[@id='txtpaymentMethodbank']";
	String bankList = "//ul[@id='paymentMethodChannelbank']";
	String bankStart = "//div[@title='";
	String bankEnd = "']";
	String banktxt = "//input[@id='txtAmountDynamicInputbank']";
	String confirmBankBT="//button[@id='btnSubmitPaymentElementbank']";
	String amountBank= "//p[@class='money-green']";
	String cancelLink = "//div[@class='cancel-transaction ']";
	String thirdPTBankSubmitBT = "//button[@type='submit']";
	String cancelLink1 = "//button[@class='btn-large bgred order-1']";
	String backLink="//a[@class='redirect-now']";
	//bank 1 = TPBank
	String chuyenNgay = "//a[@class='redirect-im']";
	String huyBank1 = "//img[@src='images/button/huy.jpg']";
	String amountBank1 = "//input[@id='OrderAmount']";
	String paymentUnsuccessMS = "//p[@class='message error']";
//Credit
	String creditBT = "//span[@id='btnPmcGrcredit']";
	String credittxt = "//input[@id='txtAmountDynamicInputcredit']";
	String confirmCreditBT = "//div[@id='btnSubmitPaymentcredit']";
//SMS Viettel
	String smsBT="//h4[@id='txtpaymentMethodsms']";
	String viettelBT ="//div[@title='Viettel']";
	String viettelOption ="//select[@id='amountPmcListsms']";
	String confirmSMSBT= "//button[@id='btnSubmitPaymentElementsms']";
	String amountSMS = "//p[@id='txtInstruction']";
//SMS Mobifone
	String mobiBT = "//div[@title='Mobifone']";
}
