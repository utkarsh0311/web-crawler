

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.wipro.poc.webcrawler.controller.WebCrawlerController;

public class CrawlerTest {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testGetIt() {
		WebCrawlerController webcrawlercontroller =new WebCrawlerController();
		String result=webcrawlercontroller.getIt("http://www.wiprodigital.com");
		System.out.println(result);
		assertEquals("http://www.wiprodigital.com",result);
		
	}
	
	@Test
	public void testNullUrl() {
		WebCrawlerController webcrawlercontroller =new WebCrawlerController();
		String result=webcrawlercontroller.getIt("");
		System.out.println(result);
		assertEquals("URL Should not be empty",result);
		
	}
	
	@Test
	public void testInvalidUrl() {
		WebCrawlerController webcrawlercontroller =new WebCrawlerController();
		String result=webcrawlercontroller.getIt("http://www.hrjeh.com/");
		System.out.println(result);
		assertEquals("HTML page not found: http://www.hrjeh.com/",result);
		
	}
	
	
	
	


}
