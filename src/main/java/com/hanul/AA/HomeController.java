package com.hanul.AA;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

/**
 * Handles requests for the application home page.
 */
@Controller @SessionAttributes({"category", "title"})
public class HomeController {
	
	@RequestMapping("/error")
	public String error(Model model, HttpServletRequest req) {
		int code = (Integer)req.getAttribute("javax.servlet.error.status_code");
		
		//발생한 오류의 정보 파악
		Throwable err 
			= (Throwable)req.getAttribute("javax.servlet.error.exception");
		StringBuilder msg = new StringBuilder();
		msg.append("<p>");
		while( err != null ) {
			msg.append( err.getMessage() + "<br>" );
			err = err.getCause();
		}
		msg.append("</p>");
		
		model.addAttribute("msg", msg.toString());
		model.addAttribute("title", code==404 ? "페이지를 찾을 수 없습니다" : "내부오류");
		return "error/" + ( code==404 ? "404" : "default");
	}
	
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = {"/", "home"}, method = RequestMethod.GET)
	public String home(Locale locale, Model model, HttpSession session) {
		logger.info("Welcome home! The client locale is {}.", locale);
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		model.addAttribute("category", "");
		model.addAttribute("serverTime", formattedDate );
		session.removeAttribute("status_info");
		session.removeAttribute("myHomeExistense");
		
		return "home";
	}
	
}
