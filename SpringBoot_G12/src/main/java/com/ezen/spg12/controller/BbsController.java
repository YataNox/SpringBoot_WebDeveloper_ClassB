package com.ezen.spg12.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

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
}
