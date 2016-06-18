package com.UErecord.controller;

import java.io.IOException;
import javax.servlet.http.HttpServletResponse;
import org.openqa.selenium.WebDriver;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.UErecord.app.Spider;

@RestController
public class MainController {

	@RequestMapping(value = "/expediente", method = RequestMethod.GET, produces = "application/json")
	@ResponseStatus(HttpStatus.OK)
	public @ResponseBody String getRecord(HttpServletResponse response, @RequestParam("username") String username,
			@RequestParam("password") String password) throws IOException {

		response.setHeader("Access-Control-Allow-Origin", "*");
		String content = "";

			WebDriver driver = Spider.setConnection(username, password);
			content = Spider.getContent(driver);
			Spider.closeConnection(driver);

			System.out.println("REQUEST - Record - Username: " + username);

		return content;
	}

	@RequestMapping(value = "/expedientePlano", method = RequestMethod.GET, produces = "text/html;charset=UTF-8")
	@ResponseStatus(HttpStatus.OK)
	public @ResponseBody String getRecordPlain(HttpServletResponse response, @RequestParam("username") String username,
			@RequestParam("password") String password) throws IOException {

		response.setHeader("Access-Control-Allow-Origin", "*");
		String content = "";

		WebDriver driver = Spider.setConnection(username, password);
		content = Spider.getPlainContent(driver);
		Spider.closeConnection(driver);

		System.out.println("REQUEST - Record - Username: " + username);

		return content;
	}

	@RequestMapping(value = "/horario", method = RequestMethod.GET, produces = "text/html;charset=UTF-8")
	@ResponseStatus(HttpStatus.OK)
	public @ResponseBody String getSchedule(HttpServletResponse response, @RequestParam("username") String username,
			@RequestParam("password") String password) throws IOException {

		response.setHeader("Access-Control-Allow-Origin", "*");

		WebDriver driver = Spider.setConnection(username, password);
		String content = Spider.getSchedule(driver);
		Spider.closeConnection(driver);

		System.out.println("REQUEST - Schedule - Username: " + username);

		return content;
	}

}
