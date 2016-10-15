package org.wipro.poc.webcrawler.controller;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.wipro.poc.webcrawler.service.Crawler;



@Path("searchurl")
public class WebCrawlerController {
	
	Crawler cw=new Crawler();
	
	@GET
    @Produces(MediaType.TEXT_PLAIN)
    public String getIt(@QueryParam("url") String url) {
		
		if(!("".equals(url)||url.isEmpty()))
		{
			return cw.search(url);
		}
		else
			return "URL Should not be empty";
		
		
    }

}
