package com.ezen.spg12.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ezen.spg12.dao.IBbsDao;

@Controller
public class BbsController {
	@Autowired
	IBbsDao bdao;
	
	@RequestMapping(value="/")
	public String userlistPage(Model model) {
		model.addAttribute("list", bdao.list());
		return "list";
	}
	
	@RequestMapping(value="/view")
	public String view(@RequestParam("id") int id, Model model) {
		model.addAttribute("dto", bdao.view(id));
		return "view";
	}
	
	@RequestMapping(value="/writeForm")
	public String writeForm() {
		return "writeForm";
	}
}
