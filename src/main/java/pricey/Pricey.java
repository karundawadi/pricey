package pricey;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.DomElement;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

import java.util.List;
import java.util.logging.Level;


public class Pricey {
    public static void scrapeData() throws Exception{
        WebClient webClient = new WebClient(BrowserVersion.CHROME);
        webClient.getOptions().setCssEnabled(false);
        webClient.getOptions().setThrowExceptionOnFailingStatusCode(false);
        webClient.getOptions().setThrowExceptionOnScriptError(false);
        webClient.getOptions().setPrintContentOnFailingStatusCode(false);
        try{
            HtmlPage page = webClient.getPage("https://finance.yahoo.com/quote/AMZN/");
            String title = page.getTitleText();
            System.out.println("Page Title: " + title);
            DomElement element = page.getFirstByXPath("//fin-streamer[@class='Fw(b) Fz(36px) Mb(-4px) D(ib)']");
            System.out.println("$ "+element.getVisibleText());
        } catch (Exception e) {
            throw e; // New is only used if we need to change the exception type
        }
    }
    public static void main(String[] args) throws Exception {
        java.util.logging.Logger.getLogger("com.gargoylesoftware").setLevel(Level.OFF);
        System.setProperty("org.apache.commons.logging.Log", "org.apache.commons.logging.impl.NoOpLog");
        scrapeData();
    }
}
