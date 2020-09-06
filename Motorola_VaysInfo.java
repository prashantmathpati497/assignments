package motorola_VaysInfotech;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

public class Motorola_VaysInfo 
{
	public static WebDriver driver;
	public static void main(String[] args) throws InterruptedException 
	{
		setUp();
		//verifyInvalidEmailAddressError();
		//verifyErrorMessagesForMandatoryFields();
		//verifyErrorMessagesForEnteringIncorrectValuesInFields();
		//searchProductFunctionality();
		//buyProduct();
		//verifyAddtoWishListWorksOnlyAfterLogin();
		//addToWishListAfterLogin();
		verifyTotalPriceReflectingCorrectlyWhenUSerChangesQuantityOnShoppingCartPage();
		driver.close();
	}
	public static void setUp() throws InterruptedException
	{
		String path = System.getProperty("user.dir");
		System.setProperty("webdriver.chrome.driver", path + "\\Drivers\\chromedriver_85.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.get("http://automationpractice.com/index.php?");
		Thread.sleep(3000);
	}
	
	//Verify Invalid email address in "Create Account Section"
	public static void verifyInvalidEmailAddressError() throws InterruptedException
	{
		driver.findElement(By.xpath("//a[@class='login']")).click();
		driver.findElement(By.id("email_create")).sendKeys("test");//invalid email address
		driver.findElement(By.xpath("//button[@name='SubmitCreate']/span")).click();
		Thread.sleep(3000);
		
		String errormsg=driver.findElement(By.xpath("//div[@id='create_account_error']")).getText();
		System.out.println("Error Text is: " +errormsg);
		
		if(errormsg.contains("Invalid email address."))
			System.out.println("Create Account Section: Correct Error Message is Thrown");
		else
			System.out.println("Create Account Section: InCorrect Error Message is Thrown");
		Thread.sleep(5000);
	}

	public static void verifyErrorMessagesForMandatoryFields() throws InterruptedException
	{
		driver.findElement(By.xpath("//a[@class='login']")).click();
		driver.findElement(By.id("email_create")).sendKeys("mahesh@gmail.com");
		driver.findElement(By.xpath("//button[@name='SubmitCreate']/span")).click();//clicks on create an account button
		Thread.sleep(2000);
		driver.findElement(By.xpath("//input[@id='email']")).clear();//clears the email field
		Thread.sleep(2000);
		driver.findElement(By.xpath("//button[@id='submitAccount']")).click();//clicks on register button
		
		//Number of required field in "Your Personal Information" section.
		List<WebElement> list1=driver.findElements(By.xpath("//div[@class='account_creation']/div/label/sup"));
		//Number of required fields in "Your Address" section.
		List<WebElement> list2=driver.findElements(By.xpath("//div[@class='account_creation']/p/label/sup"));
		//total number of required fields
		int count=list1.size()+list2.size();
		System.out.println("Number of Required Fields: " +count);
		
		List<WebElement> errorlist=driver.findElements(By.xpath("//div[@class='alert alert-danger']/ol/li"));
		int totalerr=errorlist.size();
		System.out.println("Error Messages Diplayed for " +totalerr+ "  Mandatory Fields Only");
		
		if(count==totalerr)
			System.out.println("Error Message Displayed for all the Mandatory Fields");
		else
			System.out.println("Error Message Not Displayed for all the Mandatory Fields");
	}

	public static void verifyErrorMessagesForEnteringIncorrectValuesInFields() throws InterruptedException
	{
		driver.findElement(By.xpath("//a[@class='login']")).click();
		driver.findElement(By.id("email_create")).sendKeys("mahesh@gmail.com");
		driver.findElement(By.xpath("//button[@name='SubmitCreate']/span")).click();//clicks on create an account button
		Thread.sleep(2000);
		driver.findElement(By.xpath("//input[@id='email']")).clear();//clears the email field
		Thread.sleep(2000);
		
		driver.findElement(By.xpath("//input[@id='customer_firstname']")).sendKeys("123");//invalid firstname
		driver.findElement(By.xpath("//input[@id='customer_lastname']")).sendKeys("1234");//invalid lastname
		driver.findElement(By.xpath("//input[@id='email']")).sendKeys("456789");//invalid email
		driver.findElement(By.xpath("//input[@id='passwd']")).sendKeys("Test@123");//password
		
		driver.findElement(By.xpath("//input[@id='address1']")).sendKeys("#23, RS Road");//address
		driver.findElement(By.xpath("//input[@id='city']")).sendKeys("12345");//city
		
		WebElement ele=driver.findElement(By.xpath("//select[@id='id_state']"));//country dropdown
		Select sel=new Select(ele);
		sel.selectByVisibleText("California");
		Thread.sleep(2000);
		
		driver.findElement(By.xpath("//input[@id='postcode']")).sendKeys("QWERT");//invalid zipcode
		driver.findElement(By.xpath("//input[@id='phone_mobile']")).sendKeys("QWERTYUIOP");//invalid phone number

		driver.findElement(By.xpath("//button[@id='submitAccount']")).click();//clicks on register button
		Thread.sleep(2000);
		
		List<WebElement> list=driver.findElements(By.xpath("//div[@class='alert alert-danger']/ol/li"));
		int count=list.size();
		System.out.println("Number of Invalid Entries: " +count);
		for(int i=0;i<count;i++)
		{
			String errormsg=list.get(i).getText();
			System.out.println("Invalid Texts Msgs: " +errormsg);
			if(errormsg.contains("invalid") && errormsg.contains("Invalid"))
				System.out.println("Invalid Text Message as '"+errormsg+"' is Displayed");
			else
				System.out.println("Invalid Text Message as '"+errormsg+"' is Not Displayed");
		}
	}
	
	public static void searchProductFunctionality() throws InterruptedException
	{
		WebElement ele=driver.findElement(By.xpath("//a[@title='Women'][text()='Women']"));
		Actions action=new Actions(driver);
		action.moveToElement(ele).perform();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//li/a[@title='Women']/following-sibling::ul/descendant::li/ul/li/a[@title='T-shirts']")).click();//clicks on T-Shirts section under Women
		WebElement prod=driver.findElement(By.xpath("//a[@class='product-name' and @title='Faded Short Sleeve T-shirts']"));//first product displayed
		String firstprod=prod.getText();
		System.out.println("First Product Displyed is: " +firstprod);
		Thread.sleep(2000);
		
		driver.findElement(By.xpath("//input[@name='search_query']")).sendKeys(firstprod);//enter the product name in the search field
		driver.findElement(By.xpath("//button[@name='submit_search']")).click();//clicks on search icon
		
		if(prod.isDisplayed())
			System.out.println("Same product is displayed on a searched page with the same details which were displayed on T-Shirt's page");
		else
			System.out.println("Same product is not displayed on a searched page with the same details which were displayed on T-Shirt's page");
		Thread.sleep(2000);
	}


	public static void buyProduct() throws InterruptedException
	{
		driver.findElement(By.xpath("//a[@class='login']")).click();//clicks on sign in link
		Thread.sleep(1000);
		driver.findElement(By.xpath("//input[@id='email']")).sendKeys("maheshsc04@gmail.com");
		Thread.sleep(1000);
		driver.findElement(By.xpath("//input[@id='passwd']")).sendKeys("Dummy01*");
		Thread.sleep(1000);
		driver.findElement(By.xpath("//button[@id='SubmitLogin']")).click();//clicks on sign in button

		//Checks the number of items ordered before placing an order
		WebElement ord=driver.findElement(By.xpath("//ul[@class='myaccount-link-list']/li/a[@title='Orders']"));
		if(ord.isDisplayed())
		{
			ord.click();//clicks on order history
			List<WebElement> beforeord=driver.findElements(By.xpath("//div[@id='center_column']/descendant::tbody/tr"));
			System.out.println("After Order Placement, Total Number of Orders is: " +beforeord.size());
		}
		WebElement ele=driver.findElement(By.xpath("//a[@title='Women'][text()='Women']"));
		Actions action1=new Actions(driver);
		action1.moveToElement(ele).perform();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//li/a[@title='Women']/following-sibling::ul/descendant::li/ul/li/a[@title='T-shirts']")).click();//clicks on T-Shirts section under Women
		
		WebElement prod=driver.findElement(By.xpath("//a[@class='product_img_link']/img"));//first product displayed
		Actions action2=new Actions(driver);
		action2.moveToElement(prod).perform();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//a[@title='View']/span[text()='More']")).click();
		
		driver.findElement(By.xpath("//p[@id='quantity_wanted_p']/a/descendant::i[@class='icon-plus']")).click();
		
		//Selects the size of the dress
		WebElement drp=driver.findElement(By.xpath("//select[@name='group_1']"));
		Select sel=new Select(drp);
		sel.selectByVisibleText("L");
		
		//clicks on desired color
		driver.findElement(By.xpath("//ul[@id='color_to_pick_list']/li/a[@title='Blue']")).click();
		//clicks on add to cart button
		driver.findElement(By.xpath("//button/span[text()='Add to cart']")).click();
		//clicks on proceed to checkout
		driver.findElement(By.xpath("//div[@id='layer_cart']/descendant::a[@title='Proceed to checkout']")).click();
		
		driver.findElement(By.xpath("//div[@class='columns-container']/descendant::a[@title='Proceed to checkout']")).click();//again clicns on proceed to checkout
		
		driver.findElement(By.xpath("//div[@id='center_column']/descendant::button[@name='processAddress']")).click();
		
		driver.findElement(By.xpath("//div[@class='checker']")).click();//Checks the terms & conditions
		driver.findElement(By.xpath("//div[@id='carrier_area']/descendant::button[@name='processCarrier']")).click();
		
		driver.findElement(By.xpath("//div[@id='HOOK_PAYMENT']/descendant::a[@class='bankwire']")).click();//clicks on pay by bank wire
		driver.findElement(By.xpath("//button[@type='submit']/span[text()='I confirm my order']")).click();//clicks on confirm my order
		
		String produrl="http://automationpractice.com/index.php?controller=history";
		driver.get(produrl);
		
		//Checks the number of items ordered after placing an order
		List<WebElement> afterord=driver.findElements(By.xpath("//div[@id='center_column']/descendant::tbody/tr"));
		System.out.println("After Order Placement, Total Number of Orders is: " +afterord.size());
	}


	public static void verifyAddtoWishListWorksOnlyAfterLogin() throws InterruptedException
	{
		//Mouseover on Women section
		WebElement ele=driver.findElement(By.xpath("//a[@title='Women'][text()='Women']"));
		Actions action1=new Actions(driver);
		action1.moveToElement(ele).perform();
		Thread.sleep(1000);
		//clicks on T-Shirts section under Women
		driver.findElement(By.xpath("//li/a[@title='Women']/following-sibling::ul/descendant::li/ul/li/a[@title='T-shirts']")).click();
		
		//Mouse over on first product
		WebElement prod=driver.findElement(By.xpath("//a[@class='product_img_link']/img"));//first product displayed
		Actions action2=new Actions(driver);
		action2.moveToElement(prod).perform();
		Thread.sleep(1000);

		WebElement wishlist=driver.findElement(By.partialLinkText("Add to Wishlist"));
		if(wishlist.isDisplayed())
		{
			wishlist.click();
			String errormsg=driver.findElement(By.xpath("//div[@class='fancybox-inner']")).getText();
			System.out.println("Error Message '" +errormsg+ "' is Displayed");
		}
		else
			System.out.println("Error Message is Not Displayed");
	}
	
	public static void addToWishListAfterLogin() throws InterruptedException
	{
		driver.findElement(By.xpath("//a[@class='login']")).click();//clicks on sign in link
		Thread.sleep(1000);
		driver.findElement(By.xpath("//input[@id='email']")).sendKeys("maheshsc04@gmail.com");
		Thread.sleep(1000);
		driver.findElement(By.xpath("//input[@id='passwd']")).sendKeys("Dummy01*");
		Thread.sleep(1000);
		driver.findElement(By.xpath("//button[@id='SubmitLogin']")).click();//clicks on sign in button
		
		//Mouseover on Women section
		WebElement ele=driver.findElement(By.xpath("//a[@title='Women'][text()='Women']"));
		Actions action1=new Actions(driver);
		action1.moveToElement(ele).perform();
		Thread.sleep(1000);
		//clicks on T-Shirts section under Women
		driver.findElement(By.xpath("//li/a[@title='Women']/following-sibling::ul/descendant::li/ul/li/a[@title='T-shirts']")).click();
		
		//Mouse over on first product
		WebElement prod=driver.findElement(By.xpath("//a[@class='product_img_link']/img"));//first product displayed
		Actions action2=new Actions(driver);
		action2.moveToElement(prod).perform();
		Thread.sleep(1000);

		WebElement wishlist=driver.findElement(By.partialLinkText("Add to Wishlist"));
		if(wishlist.isDisplayed())
		{
			wishlist.click();
			String msg=driver.findElement(By.xpath("//div[@class='fancybox-inner']")).getText();
			System.out.println("Confirmation Message '" +msg+ "' is Displayed");
		}
		else
			System.out.println("Confirmation Message After Adding to WishList is Not Displayed");
		
		//clicks on close icon
		driver.findElement(By.xpath("//a[@title='Close']")).click();
		//Clicks on ur profile name to navigate to My Account Page
		driver.findElement(By.xpath("//a[@title='View my customer account']")).click();
		
		//clicks on My WishLists
		driver.findElement(By.xpath("//a[@title='My wishlists']")).click();
		WebElement wishlistprod=driver.findElement(By.xpath("//div[@id='mywishlist']/descendant::div/table/tbody/tr"));
		if(wishlistprod.isDisplayed())
			System.out.println("PASS: Product Added to WishList Successfully");
		else
			System.out.println("FAIL: Product Not Added to WishList");
		Thread.sleep(2000);
		driver.findElement(By.xpath("//div[@id='mywishlist']/descendant::div/table/tbody/tr/descendant::td[@class='wishlist_delete']/a")).click();
		Thread.sleep(1000);
		Alert alert=driver.switchTo().alert();
		alert.accept();
		
	}
	
	public static void verifyTotalPriceReflectingCorrectlyWhenUSerChangesQuantityOnShoppingCartPage() throws InterruptedException
	{
		driver.findElement(By.xpath("//a[@class='login']")).click();//clicks on sign in link
		Thread.sleep(1000);
		driver.findElement(By.xpath("//input[@id='email']")).sendKeys("maheshsc04@gmail.com");
		Thread.sleep(1000);
		driver.findElement(By.xpath("//input[@id='passwd']")).sendKeys("Dummy01*");
		Thread.sleep(1000);
		driver.findElement(By.xpath("//button[@id='SubmitLogin']")).click();//clicks on sign in button

		WebElement ele=driver.findElement(By.xpath("//a[@title='Women'][text()='Women']"));
		Actions action1=new Actions(driver);
		action1.moveToElement(ele).perform();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//li/a[@title='Women']/following-sibling::ul/descendant::li/ul/li/a[@title='T-shirts']")).click();//clicks on T-Shirts section under Women
		
		WebElement prod=driver.findElement(By.xpath("//a[@class='product_img_link']/img"));//first product displayed
		Actions action2=new Actions(driver);
		action2.moveToElement(prod).perform();
		Thread.sleep(1000);
		
		//Clicks on More button
		driver.findElement(By.xpath("//a[@title='View']/span[text()='More']")).click();

		//Selects the size of the dress
		WebElement drp=driver.findElement(By.xpath("//select[@name='group_1']"));
		Select sel=new Select(drp);
		sel.selectByVisibleText("M");

		//clicks on desired color
		driver.findElement(By.xpath("//ul[@id='color_to_pick_list']/li/a[@title='Blue']")).click();
		//clicks on add to cart button
		driver.findElement(By.xpath("//button/span[text()='Add to cart']")).click();
		//clicks on proceed to checkout
		driver.findElement(By.xpath("//div[@id='layer_cart']/descendant::a[@title='Proceed to checkout']")).click();
		
		//Getting the total price of single quantity
		String singleqtyprice=driver.findElement(By.xpath("//table[@id='cart_summary']/descendant::tr/td[@id='total_price_container']/span[@id='total_price']")).getText();
		//removing $ & decimal point
		String sqtotal=singleqtyprice.replace("$","").replace(".", "");
		//converting string to integer
		int sqtot=Integer.parseInt(sqtotal);
		System.out.println("Total Price Before Increasing the Quantity: " +sqtot);
		//Deducting the shipping charges
		int singleqtytotal=sqtot*2-200;
		System.out.println("Expected Total Price After Increasing the Quantity by 2 is : " +singleqtytotal);
		Thread.sleep(2000);
	
		//Increasing the quantity by 2. Clicks on + icon
		driver.findElement(By.xpath("//table[@id='cart_summary']/descendant::tbody/descendant::td[@class='cart_quantity text-center']/child::div/a[@title='Add']")).click();
		Thread.sleep(2000);
		//Getting the total price of double quantity
		String doubleqtyprice=driver.findElement(By.xpath("//table[@id='cart_summary']/descendant::tr/td[@id='total_price_container']/span[@id='total_price']")).getText();
		//removing $ & decimal point
		String dqtotal=doubleqtyprice.replace("$","").replace(".", "");
		//converting string to integer
		int dqtot=Integer.parseInt(dqtotal);
		System.out.println("Actual Total Price After Increasing the Quantity by 2 : " +dqtot);
		
		if(singleqtytotal==dqtot)
			System.out.println("PASS: Total Price Is Reflecting Correctly When Users Changes Quantity in the Cart");
		else
			System.out.println("FAIL: Total Price Is Not Reflecting Correctly When Users Changes Quantity in the Cart");
	}
}
