package com.ezen.spg07;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ValiController {
	@RequestMapping(value="/")
	public String main() {
		return "startPage";
	}
	
	@RequestMapping(value="/create")
	public String main(@ModelAttribute("dto") ContentDto contentdto, Model model, BindingResult result) {
		ContentValidator validator = new ContentValidator();
		validator.validate(contentdto, result);
		
		if(result.hasErrors()) {
			return "startPage";
		}
		
		return "DonePage";
	}
}
