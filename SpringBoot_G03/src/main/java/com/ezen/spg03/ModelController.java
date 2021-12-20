package com.ezen.spg03;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ModelController {
	@RequestMapping(value="/")
	public @ResponseBody String root() throws Exception{
		return "Model & View";
	}
	
	@RequestMapping(value="/test1")
	public String text1(Model model){
		// Model 객체를 이용해서 , view로 Data전달 : 데이터만 설정이 가능
		model.addAttribute("name", "홍길동");
		return "test1";
	}
}
