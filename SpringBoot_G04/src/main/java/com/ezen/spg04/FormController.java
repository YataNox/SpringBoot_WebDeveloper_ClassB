package com.ezen.spg04;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class FormController {
	@RequestMapping(value="/")
	public @ResponseBody String root() throws Exception{
		return "Form 데이터 전달받아 사용하기";
	}
	
	@RequestMapping(value="/test1")
	public String text1(Model model, HttpServletRequest request){
		String id = request.getParameter("id");
		String name = request.getParameter("name");
		model.addAttribute("id", id);
		model.addAttribute("name", name);
		return "test1";
	}
	
	@RequestMapping(value="/test2")
	public String text2(Model model, @RequestParam("id") String id, @RequestParam("name") String name){
		model.addAttribute("id", id);
		model.addAttribute("name", name);
		return "test2";
	}
	
	@RequestMapping(value="/test3")
	public String text3(Member member, Model model){
		// 파라미터와 일치하는 빈을 만들어서 매개변수로 사용할 수 있습니다.
		// 전달된 파라미터는 매개변수 빈에 자동으로 입력됩니다.
		// View 페이지에서 model을 사용하지 않고 member 빈을 전달합니다.
		// member.setId("scott");
		// member.setName("tiger");
		// model.addAttribute("member", member);
		return "test3";
	}
}
