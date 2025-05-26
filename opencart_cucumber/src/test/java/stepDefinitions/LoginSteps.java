package stepDefinitions;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.WebDriver;

import factory.BaseClass;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import junit.framework.*;
import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import utilities.DataReader;

public class LoginSteps {
	WebDriver driver;
	HomePage hp;
	LoginPage lp;
	MyAccountPage macc;
	
	List<HashMap<String,String>> datamap; 

	@Given("the user navigates to login page")
	public void the_user_navigates_to_login_page() {
		
		BaseClass.getLogger().info("Go to my account --->click on login");
		hp=new HomePage(BaseClass.getDriver());
		
		hp.clickMyAccount();
		hp.clickLogin();
	    
	}

	@When("the user enters email as {string} and password as {string}")
	public void the_user_enters_email_as_and_password_as(String email, String pwd) {
	    
		BaseClass.getLogger().info("Entering email and password...");
		lp=new LoginPage(BaseClass.getDriver());
		lp.setEmail(email);
		lp.setPassword(pwd);
		
		
	}

	@When("the user clicks on the Login button")
	public void the_user_clicks_on_the_login_button() {
	   lp.clickLogin();
	   BaseClass.getLogger().info("Clicked on Login button..");	
		
	}

	@Then("the user must be redirected to the My Account Page")
	public void the_user_must_be_redirected_to_the_my_account_page() {
		macc=new MyAccountPage(BaseClass.getDriver());
		   boolean targetpage=macc.isMyAccountPageExists();
		   
		   Assert.assertEquals(targetpage, true);
	}
	
	//********** Data Driven Test  **********
	@Then("the user should be redirected to the MyAccount Page by passing email and password with excel row {string}")
	public void the_user_should_be_redirected_to_the_my_account_page_by_passing_email_and_password_with_excel_row(String rows)
	{
		try
		{
			datamap=DataReader.data(System.getProperty("user.dir")+"\\testData\\Opencart_LoginData.xlsx", "Sheet1");
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
		
		int index=Integer.parseInt(rows)-1;
		String email= datamap.get(index).get("username");
		String pwd= datamap.get(index).get("password");
		String exp_res= datamap.get(index).get("res");
		
		lp=new LoginPage(BaseClass.getDriver());
		lp.setEmail(email);
		lp.setPassword(pwd);
		
		lp.clickLogin();
		macc=new MyAccountPage(BaseClass.getDriver());
		try
		{
			boolean targetpage=macc.isMyAccountPageExists();
			System.out.println("target page: "+ targetpage);
			if(exp_res.equalsIgnoreCase("Valid"))
			{
				if(targetpage==true)
				{
					MyAccountPage myaccpage=new MyAccountPage(BaseClass.getDriver());
					myaccpage.clickLogout();
					Assert.assertTrue(true);
				}
				else
				{
					Assert.assertTrue(false);
				}
			}
			if(exp_res.equalsIgnoreCase("Invalid"))
			{
				if(targetpage==true)
				{
					macc.clickLogout();
					Assert.assertTrue(false);
				}
				else
				{
					Assert.assertTrue(true);
				}
			}
		}
		
		catch(Exception e)
		{
			Assert.assertTrue(false);
		}
	}
			
}
