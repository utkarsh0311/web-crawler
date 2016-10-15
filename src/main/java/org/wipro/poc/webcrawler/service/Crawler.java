package org.wipro.poc.webcrawler.service;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Properties;
import java.util.Set;

import org.apache.log4j.Logger;

public class Crawler {
	final static Logger logger = Logger.getLogger(Crawler.class);

private static Properties prop;
	
	static{
		InputStream is = null;
		try {
			logger.info("loading properties file");
			prop = new Properties();
			is = Crawler.class.getResourceAsStream("/config.properties");
			prop.load(is);
			logger.info("properties file loaded");
		} catch (FileNotFoundException e) {
			logger.error("FileNotFoundException while loading properties file: "+e.getStackTrace());
		} catch (IOException e) {
			logger.error("IOException while loading properties file: "+e.getStackTrace());
		}
	}
	
	public static String getPropertyValue(String key){
		return prop.getProperty(key);
	}
	
	
	//  private static final int MAX_PAGES_TO_SEARCH = 10;
	  
	  private Set<String> pagesVisited = new HashSet<String>();
	  private List<String> pagesToVisit = new LinkedList<String>();
	
	  /*
	   * search method will search for the URL by traversing different pages and 
	   * stores the URL in pagesVisited.Internally this will call nextUrl method for traversing
	   */
	
	  public String search(String url)
	  {
		 String data="";
		 int MAX_PAGES_TO_SEARCH=Integer.parseInt(getPropertyValue("MAX_PAGES_TO_SEARCH"));
		  while(this.pagesVisited.size()<MAX_PAGES_TO_SEARCH)
		  {
			  String currentUrl;
			  CrawlerSearch crawlersearch = new CrawlerSearch();
	          if(this.pagesToVisit.isEmpty())
	          {
	              currentUrl = url;
	              this.pagesVisited.add(url);
	          }
	          else
	          {
	              currentUrl = this.nextUrl();
	          }
	          
	          if(!crawlersearch.crawl(currentUrl))
	          {
	        	  data="HTML page not found: "+currentUrl;
	        	  return data;
	          }
	          
	          
	          this.pagesToVisit.addAll(crawlersearch.getLinks());
	      }
	      
	     
	      for(String datacollected:pagesVisited)
	      {
	    	  data=datacollected+"\n"+data;
	      }
	      int lastindex=data.lastIndexOf("\n");
	      return data.substring(0,lastindex);  
	  
	  }
	  
	  
	  private String nextUrl()
	  {
	      String nextUrl;
	      do
	      {
	          nextUrl = this.pagesToVisit.remove(0);
	      } while(this.pagesVisited.contains(nextUrl));
	      this.pagesVisited.add(nextUrl);
	      return nextUrl;
	  }
	
	
	
}
