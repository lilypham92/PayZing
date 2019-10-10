package Default_Theme;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class General {
	WebDriver driver;
	xpath x;
	Control control;
	String userName;
	String passWord;
	String wrongKey;
	String amount;
	String wrongAmount;
	String errorMS1;
	String errorMS2;
	String successMS1;
	String transIDExpected;
	String bank1;
	String bank2;
	String bank3;
	
	public static Map<String, GameInfo> gameList;
	
	static {
		gameList = new HashMap<String, GameInfo>();
		//General
		GameInfo General = new GameInfo();
		General.wrongKey = "ABCDE";
		General.amount = "200000";
		General.wrongAmount = "4000";
		General.errorMS1 = "Sai định dạng thẻ. Vui lòng nhập lại";
		General.errorMS2 = "Số tiền thanh toán không hợp lệ.";
		General.paymentUnsuccess = "THANH TOÁN KHÔNG THÀNH CÔNG";
		General.successMS1 = "Giao dịch thành công";
		General.transIDExpected = "Mã đơn hàng:";
		General.bank1 = "Tiền Phong";
		General.bank2 = "Vietcombank";
		General.bank3 = "Vietinbank";
		gameList.put("General", General);
		//DKV
		GameInfo DKV = new GameInfo();
		DKV.userName = "giinboo1";
		DKV.passWord = "Aa123456!";
		DKV.svName = "DKVM-01";
		DKV.url= "https://sandbox-pay.mto.zing.vn/wplogin/mobile/dkvm";
		gameList.put("DKVM", DKV);
		
		//Gunny Mobi
		GameInfo GNM = new GameInfo();
		GNM.userName = "giinboo1";
		GNM.passWord = "Aa123456!";
		GNM.url= "https://sandbox-pay.mto.zing.vn/wplogin/mobile/gnm";
		gameList.put("GNM", GNM);
	}
}
	
	