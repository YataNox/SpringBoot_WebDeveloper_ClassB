package com.ezen.spg10;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ezen.spg10.dao.BbsDao;

@Controller
public class BbsController {
	@Autowired
	BbsDao bdao;
	
	@RequestMapping(value="/")
	public String root(Model model) {
		model.addAttribute("list", bdao.list());
		return "list";
	}
	
	@RequestMapping(value="/view")
	public String view(Model model, @RequestParam("id") String id) {
		model.addAttribute("dto", bdao.view(id));
		return "view";
	}
}
