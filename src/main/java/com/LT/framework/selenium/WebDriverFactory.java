package com.LT.framework.selenium;

import com.LT.framework.FrameworkException;
import com.LT.framework.Settings;
//import com.gargoylesoftware.htmlunit.DefaultCredentialsProvider;
//import com.gargoylesoftware.htmlunit.WebClient;
//import com.opera.core.systems.OperaDriver;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;
import org.openqa.selenium.Platform;
import org.openqa.selenium.Proxy;
import org.openqa.selenium.Proxy.ProxyType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.firefox.internal.ProfilesIni;
//import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;

public class WebDriverFactory {
	private static Properties properties;

	public static WebDriver getDriver(Browser browser)
  {
    WebDriver driver = null;
    properties = Settings.getInstance();
    boolean proxyRequired = 
      Boolean.parseBoolean(properties.getProperty("ProxyRequired"));

    switch (browser)
    {
    case Chrome:
      System.setProperty("webdriver.chrome.driver",properties.getProperty("ChromeDriverPath")); 
      driver = new ChromeDriver();
      break;
    case Firefox:
    	/*
    	 * Changes made to enable firefox window pop up haldles 
    	 */
	    System.setProperty("webdriver.firefox.profile", "MySeleniumProfile");
	    DesiredCapabilities desiredCapabilities1 =
	    new DesiredCapabilities("firefox", "", Platform.ANY);
    	FirefoxProfile profile1 = new ProfilesIni().getProfile("MySeleniumProfile");
    	desiredCapabilities1.setCapability("firefox_profile", profile1);
    	//WebDriver webdriver = new RemoteWebDriver(desiredCapabilities1);
     /*FirefoxProfile profile = new FirefoxProfile();
      profile.setAcceptUntrustedCertificates( true );
      profile.setPreference( "security.enable_java", true ); 
      profile.setPreference("plugin.state.java", 2);*/
     // driver = new FirefoxDriver(profile1);
    	driver = new FirefoxDriver(desiredCapabilities1);
      break;
    case HtmlUnit:
     /* driver = new HtmlUnitDriver();

      if (!(proxyRequired)) break ;
      //label266
      boolean proxyAuthenticationRequired = 
        Boolean.parseBoolean(properties.getProperty("ProxyAuthenticationRequired"));
      if (proxyAuthenticationRequired)
      {
        driver = new HtmlUnitDriver()
        {
          protected WebClient modifyWebClient(WebClient client) {
            DefaultCredentialsProvider credentialsProvider = new DefaultCredentialsProvider();
            credentialsProvider.addNTLMCredentials(WebDriverFactory.properties.getProperty("Username"), 
              WebDriverFactory.properties.getProperty("Password"), 
              WebDriverFactory.properties.getProperty("ProxyHost"), 
              Integer.parseInt(WebDriverFactory.properties.getProperty("ProxyPort")), 
              "", WebDriverFactory.properties.getProperty("Domain"));
            client.setCredentialsProvider(credentialsProvider);
            return client;
          }
        };
      }

      ((HtmlUnitDriver)driver).setProxy(properties.getProperty("ProxyHost"), 
        Integer.parseInt(properties.getProperty("ProxyPort")));
*/
      break;
    case InternetExplorer:
      System.setProperty("webdriver.ie.driver", 
        properties.getProperty("InternetExplorerDriverPath"));
      driver = new InternetExplorerDriver();
      break;
    case Opera:
      if (proxyRequired) {
        DesiredCapabilities desiredCapabilities = getProxyCapabilities();
        driver = new OperaDriver(desiredCapabilities);
        break;
        //label266:
        }
      driver = new OperaDriver();

      break;
    case Safari:
      driver = new SafariDriver();
      break;
    default:
      throw new FrameworkException("Unhandled browser!");
    }

    label266: return driver;
  }

	

	private static DesiredCapabilities getProxyCapabilities() {
		Proxy proxy = new Proxy();
		proxy.setProxyType(Proxy.ProxyType.MANUAL);

		properties = Settings.getInstance();
		String proxyUrl = properties.getProperty("ProxyHost") + ":" + properties.getProperty("ProxyPort");

		proxy.setHttpProxy(proxyUrl);
		proxy.setFtpProxy(proxyUrl);
		proxy.setSslProxy(proxyUrl);
		DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
		desiredCapabilities.setCapability("proxy", proxy);

		return desiredCapabilities;
	}

	public static WebDriver getDriver(Browser browser, String remoteUrl) {
		return getDriver(browser, null, null, remoteUrl);
	}

	public static WebDriver getDriver(Browser browser, String browserVersion, Platform platform, String remoteUrl) {
		properties = Settings.getInstance();
		boolean proxyRequired = Boolean.parseBoolean(properties.getProperty("ProxyRequired"));

		DesiredCapabilities desiredCapabilities = null;
		if ((((browser.equals(Browser.HtmlUnit)) || (browser.equals(Browser.Opera)))) && (proxyRequired))
			desiredCapabilities = getProxyCapabilities();
		else {
			desiredCapabilities = new DesiredCapabilities();
		}

		desiredCapabilities.setBrowserName(browser.getValue());

		if (browserVersion != null) {
			desiredCapabilities.setVersion(browserVersion);
		}
		if (platform != null) {
			desiredCapabilities.setPlatform(platform);
		}

		desiredCapabilities.setJavascriptEnabled(true);
		URL url;
		try {
			url = new URL(remoteUrl);
		} catch (MalformedURLException e) {
			e.printStackTrace();
			throw new FrameworkException("The specified remote URL is malformed");
		}
		URL url1;
		return new RemoteWebDriver(url, desiredCapabilities);
	}
}