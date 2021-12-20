package com.ezen.spg06;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class VailController {
	@RequestMapping(value="/")
	public String main() {
		return "startPage";
	}
}
