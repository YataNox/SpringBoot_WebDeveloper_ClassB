package com.ezen.spm01.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.ezen.spm01.dto.MemberVO;
import com.ezen.spm01.dto.QnaVO;
import com.ezen.spm01.service.QnaService;

@Controller
public class QnaController {
	@Autowired
	QnaService qs;
	
	@RequestMapping(value="qnaList")
	public ModelAndView qna_list(Model model, HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		HttpSession session = request.getSession();
		MemberVO mvo = (MemberVO)session.getAttribute("loginUser");
		if(mvo == null) {
			mav.setViewName("member/login");
		}else {
			mav.addObject("qnaList", qs.listQna(mvo.getId()));
			mav.setViewName("qna/qnaList");
		}
		return mav;
	} // qnaList End
	
	@RequestMapping(value="/qnaWriteForm")
	public String qnaWriteForm(Model model, HttpServletRequest request) {
		HttpSession session = request.getSession();
		MemberVO mvo = (MemberVO)session.getAttribute("loginUser");
		if(mvo == null) {
			return "member/login";
		}else {
			return "qna/qnaWrite";
		}
	} // qnaWriteForm End
	
	@RequestMapping(value="/qnaWrite", method = RequestMethod.POST)
	public ModelAndView qnaWrite(@ModelAttribute("dto") @Valid QnaVO qnavo,
			BindingResult result, HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		if(result.getFieldError("subject") != null) {
			mav.addObject("message", "제목을 입력하세요.");
			mav.setViewName("qna/qnaWrite");
			return mav;
		}else if(result.getFieldError("content") != null) {
			mav.addObject("message", result.getFieldError("content").getDefaultMessage());
			mav.setViewName("qna/qnaWrite");
			return mav;
		}
		HttpSession session = request.getSession();
		MemberVO mvo = (MemberVO)session.getAttribute("loginUser");
		if(mvo == null) {
			mav.setViewName("member/login");
		}else {
			qnavo.setId(mvo.getId());
			qs.insertQna(qnavo);
		}
		mav.setViewName("redirect:/qnaList");
		return mav;
	} // qnaWrite End
}
