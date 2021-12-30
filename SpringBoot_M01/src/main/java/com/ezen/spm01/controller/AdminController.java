package com.ezen.spm01.controller;

import java.util.ArrayList;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.ezen.spm01.dto.Paging;
import com.ezen.spm01.dto.ProductVO;
import com.ezen.spm01.service.AdminService;
import com.ezen.spm01.service.ProductService;
import com.ezen.spm01.service.QnaService;

@Controller
public class AdminController {
	@Autowired
	AdminService as;
	
	@Autowired
	ProductService ps;
	
	@Autowired
	ServletContext context;
	
	@Autowired
	QnaService qs;
	
	@RequestMapping(value="/")
	public String admin() {
		return "admin/adminLoginForm";
	} // admin / End
	
	@RequestMapping(value="/adminLogin")
	public ModelAndView adminLogin(Model model, HttpServletRequest request,
			@RequestParam("workId") String workId, @RequestParam("workPwd") String workPwd) {
		ModelAndView mav = new ModelAndView();
		
		int result = as.workerCheck(workId, workPwd);
		
		if(result == 1) {
			HttpSession session = request.getSession();
			session.setAttribute("workId", workId);
			mav.setViewName("redirect:/productList");
		}else if(result == 0) {
			mav.addObject("message", "비밀번호를 확인하세요.");
			mav.setViewName("admin/adminLoginForm");
		}else if(result == -1) {
			mav.addObject("message", "아이디를 확인하세요.");
			mav.setViewName("admin/adminLoginForm");
		}else {
			mav.addObject("message", "알수없는 이유로 로그인이 실패.");
			mav.setViewName("admin/adminLoginForm");
		}
		
		return mav;
	}
	
	@RequestMapping(value="productList")
	public ModelAndView productList(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		HttpSession session = request.getSession();
		String id = (String)session.getAttribute("workId");
		if(id == null)
			mav.setViewName("redirect:/");
		else {
			int page = 1;
			if(request.getParameter("first") != null && request.getParameter("first").equals("y")) {
				page = 1;
				session.removeAttribute("page");
			}
			else if(request.getParameter("page") != null) {
				page = Integer.parseInt(request.getParameter("page"));
				session.setAttribute("page", page);
			}else if(session.getAttribute("page") != null) {
				page = (Integer)session.getAttribute("page");
			}else {
				page = 1;
				session.removeAttribute("page");
			}
			
			String key = "";
			if(request.getParameter("first") != null && request.getParameter("first").equals("y")) {
				key = "";
				session.removeAttribute("key");
			}
			else if(request.getParameter("key") != null) {
				key = request.getParameter("key");
				session.setAttribute("key", key);
			}else if(session.getAttribute("key") != null) {
				key = (String)session.getAttribute("key");
			} else {
				session.removeAttribute("key");
				key = "";
			}
			
			Paging paging = new Paging();
			paging.setPage(page);
			
			int count = as.getAllCount("product", "name", key);
			paging.setTotalCount(count);
			paging.Paging();
			
			ArrayList<ProductVO> productList = as.listProduct(paging, key);
			
			request.setAttribute("paging", paging);
			request.setAttribute("key", key);
			mav.addObject("productList", productList);
			mav.setViewName("admin/product/productList");
		}
		return mav;
	}
}
