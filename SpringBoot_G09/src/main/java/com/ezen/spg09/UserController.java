package com.ezen.spg09;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class UserController {
	@Autowired
	UserDao udao;
	
	// main
	@RequestMapping(value="/")
	public String userListPage(Model model) {
		// user list 조회
		List<UserDto> list = udao.list();
		
		// userlist를 model 저장 후 userlist.jsp 이동
		model.addAttribute("users", list);
		return "userlist";
	}
}
