package com.subscriptionData;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Properties;

public class Sub_Reader {

	Properties pro;

	public Sub_Reader() throws IOException {

		pro = new Properties();

		File f = new File(System.getProperty("user.dir") + "\\subscriptionProperty\\Subscription.properties");

		FileInputStream fs = new FileInputStream(f);

		pro.load(fs);

	}

	public String Subscribe() {

		String plandetail = pro.getProperty("plan");
		return plandetail;

	}

	public String addUser() {

		String adduser = pro.getProperty("name");
		return adduser;

	}

	public String firstname() {

		String name = pro.getProperty("Firstname");
		return name;

	}

	public String lastname() {

		String lname = pro.getProperty("Lastname");
		return lname;

	}

	public String phonenumber() {
		String phn = pro.getProperty("Phonenumber");
		return phn;

	}

	public String email() {
		String eml = pro.getProperty("email");
		return eml;
	}

	public String dcemail() {

		String dcmail = pro.getProperty("dcemail");
		return dcmail;

	}

}
