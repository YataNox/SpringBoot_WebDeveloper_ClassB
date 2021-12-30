package com.ezen.spm01.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.ezen.spm01.dto.Paging;
import com.ezen.spm01.dto.ProductVO;
import com.ezen.spm01.service.AdminService;
import com.ezen.spm01.service.ProductService;
import com.ezen.spm01.service.QnaService;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

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
	
	@RequestMapping(value="/productWriteForm")
	public ModelAndView productWriteForm(HttpServletRequest request) {
		String kindList[] = {"Heels", "Boots", "Sandals", "Slipers", "Shckers", "Sale"};
		ModelAndView mav = new ModelAndView();
		HttpSession session = request.getSession();
		String id = (String)session.getAttribute("workId");
		if(id == null)
			mav.setViewName("redirect:/");
		else {
			mav.addObject("kindList", kindList);
			mav.setViewName("admin/product/productWriteForm");
		}
		return mav;
	}
	
	@RequestMapping(value="/selectimg")
	public String selectimg() {
		return "admin/product/selectimg";
	}
	
	@RequestMapping(value="/fileupload", method = RequestMethod.POST)
	public String fileupload(Model model, HttpServletRequest request) {
		String path = context.getRealPath("/upload");
		try {
			MultipartRequest multi = new MultipartRequest(request, path, 5*1024*1024, "UTF-8", new DefaultFileRenamePolicy());
			
			// 전송된 파일은 업로드 되고, 파일 이름은 모델에 저장합니다.
			model.addAttribute("image", multi.getFilesystemName("image"));
		}
		catch(IOException e) {
			e.printStackTrace();
		}
		return "admin/product/completeupload";
	}
	
	@RequestMapping(value="/productWrite", method = RequestMethod.POST)
	public ModelAndView productWrite(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		ProductVO pvo = new ProductVO();
		pvo.setKind(request.getParameter("kind"));
		pvo.setName(request.getParameter("name"));
		pvo.setContent(request.getParameter("content"));
		pvo.setImage(request.getParameter("imgfilename"));
		pvo.setPrice1(Integer.parseInt(request.getParameter("price1")));
		pvo.setPrice2(Integer.parseInt(request.getParameter("price2")));
		pvo.setPrice3(Integer.parseInt(request.getParameter("price3")));
		as.insertProduct(pvo);
			
		mav.setViewName("redirect:/productList");
		return mav;
	}
}
