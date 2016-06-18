package com.UErecord.app;

import java.io.File;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.chrome.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;


import com.google.common.collect.ImmutableMap;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class Spider {

	public static WebDriver setConnection(String username, String password) {

		System.setProperty("webdriver.chrome.driver", "../resources/chromedriver");
		
		String xPort = System.getProperty("Importal.xvfb.id", ":1");

            	ChromeDriverService service = new ChromeDriverService.Builder().usingDriverExecutable(new File("../resources/chromedriver")).usingAnyFreePort().withEnvironment(ImmutableMap.of("DISPLAY", xPort)).build();
		
		WebDriver driver = new ChromeDriver(service);
		
		
		//chromeDriverService.start();
		//driver = new ChromeDriver(chromeDriverService);
		
		//String Xport = System.getProperty("lmportal.xvfb.id", ":1");
		//FirefoxBinary firefoxBinary = new FirefoxBinary();
        	//firefoxBinary.setEnvironmentProperty("DISPLAY", Xport);
        	//WebDriver driver = new FirefoxDriver(firefoxBinary, null);

		String appUrl = "http://madrid.universidadeuropea.es/alumno-uem/informacion-academica/expediente-academico";
		driver.get(appUrl);
		WebElement accesoExpedienteButton = driver.findElement(By.cssSelector(
				"a[href*='https://app.uem.es/Alumnos/servlet/uem.expedienteHorario.ServletPool?expedienteInicio=a']"));
		accesoExpedienteButton.click();

		// Get last window to handle
		for (String winHandle : driver.getWindowHandles()) {
			driver.switchTo().window(winHandle);
		}

		WebElement usernameInput = driver.findElement(By.cssSelector("input[id=c2]"));
		WebElement passwordInput = driver.findElement(By.cssSelector("input[id=c3]"));
		usernameInput.sendKeys(username);
		passwordInput.sendKeys(password);

		WebElement loginButton = driver.findElement(By.xpath("//*[@value='Conectar']"));
		loginButton.click();

		// Get last window to handle
		for (String winHandle : driver.getWindowHandles()) {
			driver.switchTo().window(winHandle);
		}

		// Get iframe to parse
		driver.switchTo().frame(driver.findElement(By.name("contenido")));

		return driver;

	}

	public static String getContent(WebDriver driver) {

		String htmlContent = driver.getPageSource();
		RecordInfo recordInfo = new RecordInfo();
		Gson gson = new GsonBuilder().disableHtmlEscaping().setPrettyPrinting().create();

		Document document = Jsoup.parse(htmlContent);

		Elements doc = document.select("tbody > tr > td > form");

		for (Element element : doc) {

			// Student name
			if (element.select("table").first().select("tbody > tr").get(1).select("td").first() != null) {
				recordInfo.setName(
						element.select("table").first().select("tbody > tr").get(1).select("td").first().text().trim());
			}

			// Enrollment number
			if (element.select("table").get(1).select("tbody > tr").get(2).select("td").get(0) != null) {
				recordInfo.setEnrollmentNumber(
						element.select("table").get(1).select("tbody > tr").get(2).select("td").get(0).text().trim());
			}

			// Degree
			if (element.select("table").get(1).select("tbody > tr").get(2).select("td").get(1) != null) {
				recordInfo.setDegree(
						element.select("table").get(1).select("tbody > tr").get(2).select("td").get(1).text().trim());
			}

			// Syllabus
			if (element.select("table").get(1).select("tbody > tr").get(2).select("td").get(2) != null) {
				recordInfo.setSyllabus(
						element.select("table").get(1).select("tbody > tr").get(2).select("td").get(2).text().trim());
			}

			// English level
			if (element.select("table").get(1).select("tbody > tr").get(2).select("td").get(3) != null) {
				recordInfo.setEnglishLevel(
						element.select("table").get(1).select("tbody > tr").get(2).select("td").get(3).text().trim());
			}

			// Course
			if (element.select("table").get(1).select("tbody > tr").get(2).select("td").get(4) != null) {
				recordInfo.setCourse(
						element.select("table").get(1).select("tbody > tr").get(2).select("td").get(4).text().trim());
			}

			// State
			if (element.select("table").get(1).select("tbody > tr").get(2).select("td").get(5) != null) {
				recordInfo.setState(
						element.select("table").get(1).select("tbody > tr").get(2).select("td").get(5).text().trim());
			}

			// Subjects
			Elements subjectsList = element.select("table").get(2).select("tbody > tr");

			String sYear = "";
			String sCourse = "";
			String sGroup = "";
			String sSubject = "";
			String sCredit = "";
			String sType = "";
			String sDecemberCall = "";
			String sOrdinaryCall = "";
			String sExtraordinaryCallOne = "";
			String sExtraordinaryCallTwo = "";
			String sWastedCalls = "";

			for (Element subject : subjectsList) {

				if (!subject.select("tr > td.Textonormal8pt").isEmpty()) {

					if (subject.select("tr > td.Textonormal8pt").get(0) != null) {
						sYear = subject.select("tr > td.Textonormal8pt").get(0).text().trim();
					}

					if (subject.select("tr > td.Textonormal8pt").get(1) != null) {
						sCourse = subject.select("tr > td.Textonormal8pt").get(1).text().trim();
					}

					if (subject.select("tr > td.Textonormal8pt").get(2) != null) {
						sGroup = subject.select("tr > td.Textonormal8pt").get(2).text().trim();
					}

					if (subject.select("tr > td.Textonormal8pt").get(3) != null) {
						sSubject = subject.select("tr > td.Textonormal8pt").get(3).text().trim();
					}

					if (subject.select("tr > td.Textonormal8pt").get(4) != null) {
						sCredit = subject.select("tr > td.Textonormal8pt").get(4).text().trim();
					}

					if (subject.select("tr > td.Textonormal8pt").get(5) != null) {
						sType = subject.select("tr > td.Textonormal8pt").get(5).text().trim();
					}

					if (subject.select("tr > td.Textonormal8pt").get(6) != null) {
						sDecemberCall = subject.select("tr > td.Textonormal8pt").get(6).text().trim();
					}

					if (subject.select("tr > td.Textonormal8pt").get(7) != null) {
						sOrdinaryCall = subject.select("tr > td.Textonormal8pt").get(7).text().trim();
					}

					if (subject.select("tr > td.Textonormal8pt").get(8) != null) {
						sExtraordinaryCallOne = subject.select("tr > td.Textonormal8pt").get(8).text().trim();
					}

					if (subject.select("tr > td.Textonormal8pt").get(9) != null) {
						sExtraordinaryCallTwo = subject.select("tr > td.Textonormal8pt").get(9).text().trim();
					}

					if (subject.select("tr > td.Textonormal8pt").get(10) != null) {
						sWastedCalls = subject.select("tr > td.Textonormal8pt").get(10).text().trim();
					}

					recordInfo.addSubject(recordInfo.new RecordSubjects(sYear, sCourse, sGroup, sSubject, sCredit,
							sType, sDecemberCall, sOrdinaryCall, sExtraordinaryCallOne, sExtraordinaryCallTwo,
							sWastedCalls));
				}
			}

			// FALTA CREDITOS

			String total = "";
			String passed = "";
			String enrolled = "";
			String pending = "";

			// Basic credits
			if (element.select("table").get(4).select("tbody > tr > td.nrmltxt").get(0) != null) {
				total = element.select("table").get(4).select("tbody > tr > td.nrmltxt").get(0).text().trim();
			}
			if (element.select("table").get(4).select("tbody > tr > td.nrmltxt").get(1) != null) {
				passed = element.select("table").get(4).select("tbody > tr > td.nrmltxt").get(1).text().trim();
			}
			if (element.select("table").get(4).select("tbody > tr > td.nrmltxt").get(2) != null) {
				enrolled = element.select("table").get(4).select("tbody > tr > td.nrmltxt").get(2).text().trim();
			}
			if (element.select("table").get(4).select("tbody > tr > td.nrmltxt").get(3) != null) {
				pending = element.select("table").get(4).select("tbody > tr > td.nrmltxt").get(3).text().trim();
			}

			recordInfo.addCreditBreakdown(recordInfo.new CreditsBreakdown("Basic", total, passed, enrolled, pending));

			total = "";
			passed = "";
			enrolled = "";
			pending = "";

			// Mandatory credits
			if (element.select("table").get(4).select("tbody > tr > td.nrmltxt").get(4) != null) {
				total = element.select("table").get(4).select("tbody > tr > td.nrmltxt").get(4).text().trim();
			}
			if (element.select("table").get(4).select("tbody > tr > td.nrmltxt").get(5) != null) {
				passed = element.select("table").get(4).select("tbody > tr > td.nrmltxt").get(5).text().trim();
			}
			if (element.select("table").get(4).select("tbody > tr > td.nrmltxt").get(6) != null) {
				enrolled = element.select("table").get(4).select("tbody > tr > td.nrmltxt").get(6).text().trim();
			}
			if (element.select("table").get(4).select("tbody > tr > td.nrmltxt").get(7) != null) {
				pending = element.select("table").get(4).select("tbody > tr > td.nrmltxt").get(7).text().trim();
			}

			recordInfo
					.addCreditBreakdown(recordInfo.new CreditsBreakdown("Mandatory", total, passed, enrolled, pending));

			total = "";
			passed = "";
			enrolled = "";
			pending = "";

			// Enrolled
			if (element.select("table").get(4).select("tbody > tr > td.nrmltxt").get(8) != null) {
				total = element.select("table").get(4).select("tbody > tr > td.nrmltxt").get(8).text().trim();
			}
			if (element.select("table").get(4).select("tbody > tr > td.nrmltxt").get(9) != null) {
				passed = element.select("table").get(4).select("tbody > tr > td.nrmltxt").get(9).text().trim();
			}
			if (element.select("table").get(4).select("tbody > tr > td.nrmltxt").get(10) != null) {
				enrolled = element.select("table").get(4).select("tbody > tr > td.nrmltxt").get(10).text().trim();
			}
			if (element.select("table").get(4).select("tbody > tr > td.nrmltxt").get(11) != null) {
				pending = element.select("table").get(4).select("tbody > tr > td.nrmltxt").get(11).text().trim();
			}

			recordInfo
					.addCreditBreakdown(recordInfo.new CreditsBreakdown("Enrolled", total, passed, enrolled, pending));

			total = "";
			passed = "";
			enrolled = "";
			pending = "";

			// Pending
			if (element.select("table").get(4).select("tbody > tr > td.nrmltxt").get(12) != null) {
				total = element.select("table").get(4).select("tbody > tr > td.nrmltxt").get(12).text().trim();
			}
			if (element.select("table").get(4).select("tbody > tr > td.nrmltxt").get(13) != null) {
				passed = element.select("table").get(4).select("tbody > tr > td.nrmltxt").get(13).text().trim();
			}
			if (element.select("table").get(4).select("tbody > tr > td.nrmltxt").get(14) != null) {
				enrolled = element.select("table").get(4).select("tbody > tr > td.nrmltxt").get(14).text().trim();
			}
			if (element.select("table").get(4).select("tbody > tr > td.nrmltxt").get(15) != null) {
				pending = element.select("table").get(4).select("tbody > tr > td.nrmltxt").get(15).text().trim();
			}

			recordInfo.addCreditBreakdown(recordInfo.new CreditsBreakdown("Pending", total, passed, enrolled, pending));

		}

		return gson.toJson(recordInfo);
	}

	public static String getSchedule(WebDriver driver) {

		driver.findElement(By.xpath("//*[@href='javascript:irHorario()']")).click();
		
		Document document = Jsoup.parse(driver.getPageSource());

		Elements doc = document.select("html > body > form");

		for (Element element : doc) {
			element.select("td[width=13%]").remove();
			element.select("center").remove();
			element.select("ul").remove();
			element.select("td.titMargin4").remove();
			element.select("script").remove();
		}
		
		return doc.html();
		
		//return driver.getPageSource();

	}

	public static String getPlainContent(WebDriver driver) {
		
		Document document = Jsoup.parse(driver.getPageSource());

		Elements doc = document.select("tbody > tr > td > form");

		for (Element element : doc) {
			//element.select("td[width=6%]").remove(); //Eliminar numero de matricula
			element.select("p.nrmlTxtResalt").remove();
			element.select("span.Titulares2").remove();
		}
		
		return doc.html();
		//return driver.getPageSource();

	}

	public static void closeConnection(WebDriver driver) {
		driver.close();
	}

}
