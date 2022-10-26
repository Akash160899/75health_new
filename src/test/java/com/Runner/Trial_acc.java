package com.Runner;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.BaseClass.Base_Class;
import com.data.ConfigManager;
import com.pageObjectMan.PageObjMan;
import com.subscriptionData.Sub_Manager;

public class Trial_acc extends Base_Class {

	public static WebDriver driver;
	static PageObjMan pom;
	static JavascriptExecutor j;
	WebDriverWait ww;
	String dcid;
	String usid;

	@BeforeClass
	private void browser() throws IOException, InterruptedException {

		driver = browserLaunch("chrome");
		pom = new PageObjMan(driver);
		j = (JavascriptExecutor) driver;
		ww = new WebDriverWait(driver, 60);
		String ur = ConfigManager.getconfigManager().getInstanceConfigReader().getUrl();

		while (true) {
			if (ur.equals("https://localhost:8443/")) {

				driver.get(ConfigManager.getconfigManager().getInstanceConfigReader().getUrl());
				sleep(3000);
				driver.findElement(By.id("details-button")).click();
				sleep(3000);

				driver.findElement(By.id("proceed-link")).click();
				sleep(4000);
				implicitWait(30, TimeUnit.SECONDS);

				break;
			} else if (ur.equals("https://www.75health.com/login.jsp")) {
				driver.get("https://www.75health.com/login.jsp");

				break;
			}

		}

		click(pom.getInstanceLoginPage().sigIn);
		sleep(2000);
		sendkeys(pom.getInstanceLoginPage().email,
				ConfigManager.getconfigManager().getInstanceConfigReader().getEmail());
		sendkeys(pom.getInstanceLoginPage().pass, ConfigManager.getconfigManager().getInstanceConfigReader().getpass());
		click(pom.getInstanceLoginPage().login);
		sleep(3000);

		try {
			sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		if (ur.equals("https://localhost:8443/")) {
			ww.until(ExpectedConditions.urlToBe("https://localhost:8443/health/#home"));
			implicitWait(60, TimeUnit.SECONDS);
		} else if (ur.equals("https://www.75health.com/login.jsp")) {
			ww.until(ExpectedConditions.urlToBe("https://www.75health.com/health/#home"));
			implicitWait(60, TimeUnit.SECONDS);
		}
	}

	@Test(priority = 1)
	private void scenario1() throws InterruptedException, IOException {

		driver.navigate().to("https://localhost:8443/health/#allSubscriptionActiveUsers");
		sleep(3000);
		driver.navigate().to("https://localhost:8443/health/#allPaymentHistory");
		sleep(3000);

		click(pom.getInstanceSetting().clickSettings);

		WebElement manageuser = driver.findElement(By.xpath("//button[@onclick='setting.userList()']"));
		visbility(driver, manageuser, 60);
		manageuser.click();
		WebElement adduser = driver.findElement(By.xpath("//button[@onclick='Health.user_new()']"));
		visbility(driver, adduser, 60);
		adduser.click();
		sleep(2000);
		// user creation
		WebElement trp14 = driver.findElement(By.xpath("//input[@id='Firstname']"));
		visbility(driver, trp14, 60);
		sendkeys(trp14, Sub_Manager.getInstanecSubManager().getInstanceSubReader().firstname());
		WebElement trp15 = driver.findElement(By.xpath("//input[@id='LastName']"));
		visbility(driver, trp15, 60);
		sendkeys(trp15, Sub_Manager.getInstanecSubManager().getInstanceSubReader().lastname());
		sleep(2000);
		WebElement trp16 = driver.findElement(By.xpath("//input[@id='UserLoginId']"));
		visbility(driver, trp16, 60);
		sendkeys(trp16, Sub_Manager.getInstanecSubManager().getInstanceSubReader().email());
		// .sendKeys("Akashn1212@gmail.com");
		sleep(2000);
		WebElement trp17 = driver.findElement(By.xpath("//button[@id='user_dropdown']"));
		visbility(driver, trp17, 60);
		click(trp17);// .click();
		List<WebElement> usserdrp = driver.findElements(By.xpath("//button[@id='user_dropdown']//following::ul[1]/li"));
		for (WebElement web : usserdrp) {
			if (web.getText().trim().equals("Standard User")) {
				visbility(driver, web, 60);
				web.click();
				break;
			}

		}
		sleep(2000);
		// WebElement phone = driver.findElement(By.xpath("//input[@id='PhonE']"));
		// clear(phone);
		// phone.sendKeys("+91"+
		// Sub_Manager.getInstanecSubManager().getInstanceSubReader().phonenumber());
		sleep(2000);

		WebElement createuser = driver.findElement(By.xpath("(//button[@id='createButton'])[2]"));
		visbility(driver, createuser, 60);

		WebElement canceluser = driver.findElement(By.xpath("(//button[@onclick='window.history.back()'])[2]"));
		visbility(driver, canceluser, 60);
		// canceluser.click(); //
		createuser.click();
		WebElement user_dr = driver.findElement(By.xpath("(//td[@id='val-kdid'])[2]"));
		visbility(driver, user_dr, 60);
		usid = user_dr.getText();

		driver.navigate().to("https://localhost:8443/health/#allSubscriptionActiveUsers");
		sleep(3000);
		driver.navigate().to("https://localhost:8443/health/#allPaymentHistory");
		sleep(3000);
		driver.navigate().to("https://localhost:8443/health/#list_user");

		for (int j = 0; j <= 5; j++) {
			try {
				WebElement userpartsearh = driver.findElement(By.xpath("(//input[@id='userPartyName'])[1]"));
				visbility(driver, userpartsearh, 60);
				sendkeys(userpartsearh, usid);
				WebElement usrlist = driver.findElement(By.xpath("(//div[@id='listTableBody'])[1]/div[1]"));
				visbility(driver, usrlist, 60);
				javascriptclick(usrlist);
				break;

			} catch (Exception e) {

			}
		}

		/*
		 * WebElement btsn =
		 * driver.findElement(By.xpath("//button[@id='userActiveId']"));
		 * visbility(driver, btsn, 60); javascriptclick(btsn);
		 */

		/*
		 * WebElement editinfo = driver .findElement(By.
		 * xpath("(//span[text()='Basic Information'])[4]//following::span[1]"));
		 * visbility(driver, editinfo, 60); click(editinfo);
		 */

		List<WebElement> mnguserdrp;
		while (true) {
			try {
				mnguserdrp = driver.findElements(By.xpath("//button[@id='userActiveId']//following::ul[1]/li/a"));
				System.out.println("hello  buddies");
				break;
			} catch (Exception e) {

			}
		}

		for (WebElement we : mnguserdrp) {

			WebElement editinfos = driver
					.findElement(By.xpath("(//span[text()='Basic Information'])[4]//following::span[1]"));
			visbility(driver, editinfos, 60);
			click(editinfos);

			WebElement btsn2 = driver.findElement(By.xpath("//button[@id='userActiveId']"));
			visbility(driver, btsn2, 60);
			javascriptclick(btsn2);

			if (we.getText().trim().equals("ACTIVE")) {

				visbility(driver, we, 60);
				we.click();
				WebElement saveuserinfo = driver.findElement(

						By.xpath("//button[@id='userActiveId']//following::ul[1]/li/a//following::button[4]"));
				visbility(driver, saveuserinfo, 60);
				javascriptclick(saveuserinfo);
				sleep(2500);

			} else if (we.getText().trim().equals("SUSPEND")) {
				visbility(driver, we, 60);
				we.click();
				WebElement saveuserinfo = driver.findElement(
						By.xpath("//button[@id='userActiveId']//following::ul[1]/li/a//following::button[4]"));
				visbility(driver, saveuserinfo, 60);
				javascriptclick(saveuserinfo);
				sleep(2500);
				WebElement editinfosk = driver
						.findElement(By.xpath("(//span[text()='Basic Information'])[4]//following::span[1]"));
				visbility(driver, editinfos, 60);
				click(editinfosk);

				WebElement btsn2u = driver.findElement(By.xpath("//button[@id='userActiveId']"));
				visbility(driver, btsn2u, 60);
				javascriptclick(btsn2u);
				List<WebElement> cf = driver
						.findElements(By.xpath("//button[@id='userActiveId']//following::ul[1]/li/a"));
				for (WebElement web : cf) {
					if (web.getText().trim().equals("ACTIVE"))
						;
					visbility(driver, web, 60);
					javascriptclick(web);

					WebElement saveuserinfoq = driver.findElement(
							By.xpath("//button[@id='userActiveId']//following::ul[1]/li/a//following::button[4]"));
					visbility(driver, saveuserinfoq, 60);
					javascriptclick(saveuserinfoq);
					break;
				}

			} else if (we.getText().trim().equals("DEACTIVE")) {
				visbility(driver, we, 60);
				we.click();
				WebElement saveuserinfo = driver.findElement(
						By.xpath("//button[@id='userActiveId']//following::ul[1]/li/a//following::button[4]"));
				visbility(driver, saveuserinfo, 60);
				javascriptclick(saveuserinfo);
				sleep(2500);

				WebElement editinfosk = driver
						.findElement(By.xpath("(//span[text()='Basic Information'])[4]//following::span[1]"));
				visbility(driver, editinfos, 60);
				click(editinfosk);

				WebElement btsn2u = driver.findElement(By.xpath("//button[@id='userActiveId']"));
				visbility(driver, btsn2u, 60);
				javascriptclick(btsn2u);

				List<WebElement> cf = driver
						.findElements(By.xpath("//button[@id='userActiveId']//following::ul[1]/li/a"));
				for (WebElement web : cf) {
					if (web.getText().trim().equals("ACTIVE"))
						;
					visbility(driver, web, 60);
					javascriptclick(web);

					WebElement saveuserinfoq = driver.findElement(
							By.xpath("//button[@id='userActiveId']//following::ul[1]/li/a//following::button[4]"));
					visbility(driver, saveuserinfoq, 60);
					javascriptclick(saveuserinfoq);
					WebElement qrs = driver
							.findElement(By.xpath("//span[text()='Add Subscription']//following::button[2]"));
					visbility(driver, qrs, 60);
					javascriptclick(qrs);
					break;

				}

			}

		}

	}

	// Doctor Creation

	@Test(priority = 2)
	private void doctorcr() throws InterruptedException, IOException {

		driver.navigate().to("https://localhost:8443/health/#allSubscriptionActiveUsers");
		sleep(3000);
		driver.navigate().to("https://localhost:8443/health/#allPaymentHistory");
		sleep(3000);
		visbility(driver, pom.getInstanceSetting().clickSettings, 60);
		javascriptclick(pom.getInstanceSetting().clickSettings);

		WebElement manageuser = driver.findElement(By.xpath("//button[@onclick='setting.userList()']"));
		visbility(driver, manageuser, 60);
		manageuser.click();
		WebElement adduser = driver.findElement(By.xpath("//button[@onclick='Health.user_new()']"));
		visbility(driver, adduser, 60);
		adduser.click();
		sleep(2000);
		// user creation
		WebElement trp14 = driver.findElement(By.xpath("//input[@id='Firstname']"));
		visbility(driver, trp14, 60);
		sendkeys(trp14, "sandy");
		WebElement trp15 = driver.findElement(By.xpath("//input[@id='LastName']"));
		visbility(driver, trp15, 60);
		sendkeys(trp15, "san");
		sleep(2000);
		WebElement trp16 = driver.findElement(By.xpath("//input[@id='UserLoginId']"));
		visbility(driver, trp16, 60);
		sendkeys(trp16, Sub_Manager.getInstanecSubManager().getInstanceSubReader().dcemail());

		sleep(2000);
		WebElement trp17 = driver.findElement(By.xpath("//button[@id='user_dropdown']"));
		visbility(driver, trp17, 60);
		click(trp17);// .click();
		List<WebElement> usserdrp = driver.findElements(By.xpath("//button[@id='user_dropdown']//following::ul[1]/li"));
		for (WebElement web : usserdrp) {
			if (web.getText().trim().equals("Doctor")) {
				visbility(driver, web, 60);
				web.click();
				break;
			}

		}
		sleep(2000);

		WebElement createuser = driver.findElement(By.xpath("(//button[@id='createButton'])[2]"));
		visbility(driver, createuser, 60);

		WebElement canceluser = driver.findElement(By.xpath("(//button[@onclick='window.history.back()'])[2]"));
		visbility(driver, canceluser, 60);
		// canceluser.click(); //
		createuser.click();
		WebElement user_dr = driver.findElement(By.xpath("(//td[@id='val-kdid'])[2]"));
		visbility(driver, user_dr, 60);
		dcid = user_dr.getText();

		WebElement editinfo = driver
				.findElement(By.xpath("(//span[text()='Basic Information'])[4]//following::span[1]"));
		visbility(driver, editinfo, 60);
		click(editinfo);

		WebElement btsn = driver.findElement(By.xpath("//button[@id='userActiveId']"));
		visbility(driver, btsn, 60);
		javascriptclick(btsn);

		List<WebElement> mnguserdrp = driver
				.findElements(By.xpath("//button[@id='userActiveId']//following::ul[1]/li/a"));

		for (WebElement we : mnguserdrp) {

			if (we.getText().trim().equals("ACTIVE")) {

				visbility(driver, we, 60);
				we.click();
				break;
			}

		}

		WebElement saveuserinfo = driver
				.findElement(By.xpath("//button[@id='userActiveId']//following::ul[1]/li/a//following::button[4]"));
		visbility(driver, saveuserinfo, 60);
		javascriptclick(saveuserinfo);

	}

	@Test(priority = 3, enabled = false)
	private void activeuser() throws InterruptedException {

		for (int js = 0; js <= 5; js++) {
			try {
				visbility(driver, pom.getInstanceSetting().clickSettings, 60);
				javascriptclick(pom.getInstanceSetting().clickSettings);
				click(pom.getInstanceSetting().clickactiveuserpage);
				sleep(2500);
				driver.navigate().to("https://localhost:8443/health/#setting");
				click(pom.getInstanceSetting().paymenthistorypage);
				break;
			} catch (Exception e) {

			}
		}
	}

	@Test(priority = 4, enabled = false)
	private void active_dec() {
		// user dectivation status...
		visbility(driver, pom.getInstanceSetting().clickSettings, 60);
		javascriptclick(pom.getInstanceSetting().clickSettings);
		click(pom.getInstanceSetting().clickactiveuserpage);

		String rpl = usid.replace("-", "");
		System.out.println(rpl);

		WebElement userdcact = driver.findElement(By.xpath("//div[text()='" + rpl + "']//following::span[3]"));
		System.out.println(userdcact);

		WebElement ids = driver.findElement(By.xpath("//div[text()='" + rpl + "']"));
		actions("move to element", ids);

		visbility(driver, userdcact, 60);
		javascriptclick(userdcact);

		List<WebElement> cnclacdrp = driver
				.findElements(By.xpath("//div[text()='" + rpl + "']//following::span[3]//following::ul[1]/li"));
		for (WebElement web : cnclacdrp) {

			if (web.getText().trim().equals("Cancel Subscription")) {
				visbility(driver, web, 60);
				click(web);
				break;
			}

		}

	}

	@Test(priority = 5, enabled = false)
	private void upgradetobasicfromtrial() throws InterruptedException {
		boolean b = true;

		while (b) {
			try {
				WebElement ugtbsfrmtrl = driver.findElement(By.xpath("//span[text()='Upgrade Now']"));

				visbility(driver, ugtbsfrmtrl, 60);

				if (ugtbsfrmtrl.isDisplayed()) {

					javascriptclick(ugtbsfrmtrl);
					b = false;
				}

			} catch (Exception e) {

			}
		}

		WebElement basicsub = driver.findElement(By.xpath("//span[text()='$ 19']"));
		actions("move to element", basicsub);
		visbility(driver, basicsub, 60);

		// card details

		driver.switchTo().frame(driver.findElement(By.xpath("//div[@id='card-details']//iframe")));
		System.out.println("switch in frame");
		WebElement cardnum = driver.findElement(By.name("cardnumber"));
		visbility(driver, cardnum, 60);
		javascriptclick(cardnum);
		sendkeys(cardnum, "4242 4242 4242 4242");
		WebElement expyr = driver.findElement(By.name("exp-date"));
		visbility(driver, expyr, 60);
		sendkeys(expyr, "424");
		WebElement cvc = driver.findElement(By.name("cvc"));
		visbility(driver, cvc, 60);
		sendkeys(cvc, "242");

		WebElement zipcode = driver.findElement(By.name("postal"));
		visbility(driver, zipcode, 60);
		sendkeys(zipcode, "42424");

		defaultcontent();

		WebElement activesubscription = driver.findElement(By.xpath("//button[@id='payment_btn']"));
		visbility(driver, activesubscription, 60);
		javascriptclick(activesubscription);

		ww.until(ExpectedConditions.urlToBe("https://localhost:8443/payment-success-page.jsp"));
		WebElement linktext = driver.findElement(By.xpath("//a[text()='Click here to return to the login Page']"));
		visbility(driver, linktext, 60);
		javascriptclick(linktext);

		ww.until(ExpectedConditions.urlToBe("https://localhost:8443/health/#home"));

		// active userpage..

		visbility(driver, pom.getInstanceSetting().clickSettings, 60);
		javascriptclick(pom.getInstanceSetting().clickSettings);
		click(pom.getInstanceSetting().clickactiveuserpage);

		sleep(4000);

		// paymenthistory page
		visbility(driver, pom.getInstanceSetting().clickSettings, 60);
		javascriptclick(pom.getInstanceSetting().clickSettings);
		click(pom.getInstanceSetting().paymenthistorypage);

		WebElement scroltopaymentview = driver
				.findElement(By.xpath("//div[@id='allPaymentHistory']/script[1]//following::span[1]"));
		visbility(driver, scroltopaymentview, 60);
		ScriptExecutor(scroltopaymentview);
		sleep(4000);

	}

	@Test(priority = 6, dependsOnMethods = "upgradetobasicfromtrial", enabled = false)
	private void basicusercrtfrmmngeuser() throws IOException, InterruptedException {

		visbility(driver, pom.getInstanceSetting().clickSettings, 60);
		javascriptclick(pom.getInstanceSetting().clickSettings);

		WebElement manageuser = driver.findElement(By.xpath("//button[@onclick='setting.userList()']"));
		visbility(driver, manageuser, 60);
		manageuser.click();
		WebElement adduser = driver.findElement(By.xpath("//button[@onclick='Health.user_new()']"));
		visbility(driver, adduser, 60);
		adduser.click();
		sleep(2000);
		// user creation
		WebElement trp14 = driver.findElement(By.xpath("//input[@id='Firstname']"));
		visbility(driver, trp14, 60);
		sendkeys(trp14, "sandy");
		WebElement trp15 = driver.findElement(By.xpath("//input[@id='LastName']"));
		visbility(driver, trp15, 60);
		sendkeys(trp15, "san");
		sleep(2000);
		WebElement trp16 = driver.findElement(By.xpath("//input[@id='UserLoginId']"));
		visbility(driver, trp16, 60);
		sendkeys(trp16, Sub_Manager.getInstanecSubManager().getInstanceSubReader().dcemail());

		sleep(2000);
		WebElement trp17 = driver.findElement(By.xpath("//button[@id='user_dropdown']"));
		visbility(driver, trp17, 60);
		click(trp17);// .click();
		List<WebElement> usserdrp = driver.findElements(By.xpath("//button[@id='user_dropdown']//following::ul[1]/li"));
		for (WebElement web : usserdrp) {
			if (web.getText().trim().equals("Doctor")) {
				visbility(driver, web, 60);
				web.click();
				break;
			}

		}
		sleep(2000);

		WebElement createuser = driver.findElement(By.xpath("(//button[@id='createButton'])[2]"));
		visbility(driver, createuser, 60);

		WebElement canceluser = driver.findElement(By.xpath("(//button[@onclick='window.history.back()'])[2]"));
		visbility(driver, canceluser, 60);
		// canceluser.click(); //
		createuser.click();
		WebElement user_dr = driver.findElement(By.xpath("(//td[@id='val-kdid'])[2]"));
		visbility(driver, user_dr, 60);
		dcid = user_dr.getText();

		WebElement editinfo = driver
				.findElement(By.xpath("(//span[text()='Basic Information'])[4]//following::span[1]"));
		visbility(driver, editinfo, 60);
		click(editinfo);

		WebElement btsn = driver.findElement(By.xpath("//button[@id='userActiveId']"));
		visbility(driver, btsn, 60);
		javascriptclick(btsn);

		List<WebElement> mnguserdrp = driver
				.findElements(By.xpath("//button[@id='userActiveId']//following::ul[1]/li/a"));

		for (WebElement we : mnguserdrp) {

			if (we.getText().trim().equals("ACTIVE")) {
				System.out.println("condition mets");
				visbility(driver, we, 60);
				we.click();
				break;
			}

		}

		WebElement saveuserinfo = driver
				.findElement(By.xpath("//button[@id='userActiveId']//following::ul[1]/li/a//following::button[4]"));
		visbility(driver, saveuserinfo, 60);
		javascriptclick(saveuserinfo);

	}

	@Test(priority = 7, dependsOnMethods = "basicusercrtfrmmngeuser", enabled = false)
	private void adduserfromsubscription() throws IOException, InterruptedException {

		visbility(driver, pom.getInstanceSetting().clickSettings, 60);
		click(pom.getInstanceSetting().clickSettings);
		click(pom.getInstanceSetting().clickactiveuserpage);

		WebElement upgradeplanlink = driver.findElement(By.xpath("(//span[@id='manage-sub-text'])[1]/a/img"));
		visbility(driver, upgradeplanlink, 60);
		javascriptclick(upgradeplanlink);

		WebElement premiumsub = driver.findElement(By.xpath("//span[text()='$ 55']"));
		visbility(driver, premiumsub, 60);
		javascriptclick(premiumsub);

		WebElement edituser = driver.findElement(
				By.xpath("//div[@id='subscriptionPayment']/div/div/div/div[3]/div[1]//following::button[1]"));
		visbility(driver, edituser, 60);
		javascriptclick(edituser);

		// adduserfromsubrciption

		WebElement addusfrsub = driver.findElement(By.xpath("//div[@id='add-new-user']/i"));
		visbility(driver, addusfrsub, 60);
		javascriptclick(addusfrsub);

		// user creation
		WebElement trp14 = driver.findElement(By.xpath("//input[@id='Firstname']"));
		visbility(driver, trp14, 60);
		sendkeys(trp14, "sandy");
		WebElement trp15 = driver.findElement(By.xpath("//input[@id='LastName']"));
		visbility(driver, trp15, 60);
		sendkeys(trp15, "san");
		sleep(2000);
		WebElement trp16 = driver.findElement(By.xpath("//input[@id='UserLoginId']"));
		visbility(driver, trp16, 60);
		sendkeys(trp16, Sub_Manager.getInstanecSubManager().getInstanceSubReader().dcemail());

		sleep(2000);
		WebElement trp17 = driver.findElement(By.xpath("//button[@id='user_dropdown']"));
		visbility(driver, trp17, 60);
		click(trp17);// .click();
		List<WebElement> usserdrp = driver.findElements(By.xpath("//button[@id='user_dropdown']//following::ul[1]/li"));
		for (WebElement web : usserdrp) {
			if (web.getText().trim().equals("Doctor")) {
				visbility(driver, web, 60);
				web.click();
				break;
			}

		}
		sleep(2000);

		WebElement createuser = driver.findElement(By.xpath("(//button[@id='createButton'])[2]"));
		visbility(driver, createuser, 60);

		WebElement canceluser = driver.findElement(By.xpath("(//button[@onclick='window.history.back()'])[2]"));
		visbility(driver, canceluser, 60);
		// canceluser.click(); //
		createuser.click();
		WebElement user_dr = driver.findElement(By.xpath("(//td[@id='val-kdid'])[2]"));
		visbility(driver, user_dr, 60);
		dcid = user_dr.getText();

		WebElement editinfo = driver
				.findElement(By.xpath("(//span[text()='Basic Information'])[4]//following::span[1]"));
		visbility(driver, editinfo, 60);
		click(editinfo);

		WebElement btsn = driver.findElement(By.xpath("//button[@id='userActiveId']"));
		visbility(driver, btsn, 60);
		javascriptclick(btsn);

		List<WebElement> mnguserdrp = driver
				.findElements(By.xpath("//button[@id='userActiveId']//following::ul[1]/li/a"));

		for (WebElement we : mnguserdrp) {

			if (we.getText().trim().equals("ACTIVE")) {
				System.out.println("condition mets");
				visbility(driver, we, 60);
				we.click();
				break;
			}

		}

		WebElement saveuserinfo = driver
				.findElement(By.xpath("//button[@id='userActiveId']//following::ul[1]/li/a//following::button[4]"));
		visbility(driver, saveuserinfo, 60);
		javascriptclick(saveuserinfo);

		// driver.navigate().to("https://localhost:8443/health/#allEditVasPremiumSubscription");

	}

	@Test(priority = 8, dependsOnMethods = "adduserfromsubscription", enabled = false)
	private void checkuserstatusafteradding_newuser() throws InterruptedException {
		driver.navigate().to("https://localhost:8443/health/#allSubscriptionActiveUsers");
		sleep(3000);
		driver.navigate().to("https://localhost:8443/health/#allPaymentHistory");
		sleep(3000);
		driver.navigate().to("https://localhost:8443/health/#allSubscriptionActiveUsers");

		for (int a = 0; a <= 5; a++) {

			try {

				WebElement fn = driver.findElement(By.xpath("//button[@id='proceedPayment']"));
				visbility(driver, fn, 60);
				javascriptclick(fn);
				break;

			} catch (Exception e) {

			}
		}

		driver.switchTo().frame(driver.findElement(By.xpath("//div[@id='card-details']//iframe")));
		System.out.println("switch in frame");
		WebElement cardnum = driver.findElement(By.name("cardnumber"));
		visbility(driver, cardnum, 60);
		javascriptclick(cardnum);
		sendkeys(cardnum, "4242 4242 4242 4242");
		WebElement expyr = driver.findElement(By.name("exp-date"));
		visbility(driver, expyr, 60);
		sendkeys(expyr, "424");
		WebElement cvc = driver.findElement(By.name("cvc"));
		visbility(driver, cvc, 60);
		sendkeys(cvc, "242");

		WebElement zipcode = driver.findElement(By.name("postal"));
		visbility(driver, zipcode, 60);
		sendkeys(zipcode, "42424");

		defaultcontent();

		WebElement activesubscription = driver
				.findElement(By.xpath("(//button[contains(@title,'Update card and make payment')])[1]"));
		visbility(driver, activesubscription, 60);
		javascriptclick(activesubscription);
	}

	@Test(priority = 9, dependsOnMethods = "checkuserstatusafteradding_newuser", enabled = false)

	private void planupgradedtopremium() throws InterruptedException {

		driver.navigate().to("https://localhost:8443/health/#allSubscriptionActiveUsers");
		sleep(3000);
		driver.navigate().to("https://localhost:8443/health/#allPaymentHistory");

	}

}
