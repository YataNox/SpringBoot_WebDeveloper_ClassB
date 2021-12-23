package com.ezen.spg16.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.ezen.spg16.dto.Paging;
import com.ezen.spg16.service.BoardService;

@Controller
public class BoardController {
	@Autowired
	BoardService bs;
	
	@RequestMapping(value="/main")
	public ModelAndView main(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		HttpSession session = request.getSession();
		if(session.getAttribute("loginUser") == null)
			mav.setViewName("loginForm");
		else {
			int page = 1;
			
			if(request.getParameter("page") != null) {
				page = Integer.parseInt(request.getParameter("page"));
				session.setAttribute("page", page);
			}else if(session.getAttribute("page") != null) {
				page = (int) session.getAttribute("page");
			}else {
				page = 1;
				session.removeAttribute("page");
			}
			
			Paging paging = new Paging();
			paging.setPage(page);
			
			int count = bs.getAllCount();
			paging.setTotalCount(count);
			paging.Paging();
			
			mav.addObject("boardList", bs.selectBoardAll(paging));
			mav.addObject("paging", paging);
			mav.setViewName("main");
		}
		return mav;
	}
}
