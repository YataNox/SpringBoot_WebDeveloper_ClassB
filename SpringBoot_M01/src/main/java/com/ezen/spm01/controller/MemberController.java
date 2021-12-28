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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.ezen.spm01.dto.MemberVO;
import com.ezen.spm01.service.MemberService;

@Controller
public class MemberController {
	@Autowired
	MemberService ms;
	
	// login페이지로 이동
	@RequestMapping(value="/loginForm")
	public String login_form() {
		return "member/login";
	} // loginForm End
	
	// 아이디 비밀번호 확인 후 로그인
	@RequestMapping(value="/login", method = RequestMethod.POST)
	public String login(@ModelAttribute("dto") @Valid MemberVO membervo,
			BindingResult result, Model model, HttpServletRequest request) {
		if(result.getFieldError("id") != null) {
			model.addAttribute("message", result.getFieldError("id").getDefaultMessage());
			return "member/login";
		}else if(result.getFieldError("pwd") != null) {
			model.addAttribute("message", result.getFieldError("pwd").getDefaultMessage());
			return "member/login";
		}
		
		MemberVO mvo = ms.getMember(membervo.getId());
		if(mvo == null) {
			model.addAttribute("message", "ID가 없습니다.");
			return "member/login";
		}else if(mvo.getPwd() == null) {
			model.addAttribute("message", "관리자에게 문의하세요.");
			return "member/login";
		}else if(!mvo.getPwd().equals(membervo.getPwd())) {
			model.addAttribute("message", "비밀번호가 맞지 않습니다..");
			return "member/login";
		}else if(mvo.getPwd().equals(membervo.getPwd())) {
			HttpSession session = request.getSession();
			session.setAttribute("loginUser", mvo);
			return "redirect:/";
		}else {
			model.addAttribute("message", "알수없는 이유로 로그인 실패.");
			return "member/login";
		}
	} // login End
	
	// 로그아웃
	@RequestMapping(value="/logout")
	public String logout(HttpServletRequest request) {
		HttpSession session = request.getSession();
		session.invalidate();
		return "redirect:/";
	} // logout End
	
	// 약관동의 창 이동
	@RequestMapping(value="/contract")
	public String contract() {
		return "member/contract";
	} // contract End
	
	// 회원가입 창 이동
	@RequestMapping(value="/joinForm")
	public String joinForm() {
		return "member/joinForm";
	} // joinForm End
	
	// 아이디 중복 체크 창
	@RequestMapping(value="/idCheckForm")
	public ModelAndView idCheckForm(@RequestParam("id") String id) {
		ModelAndView mav = new ModelAndView();
		MemberVO mvo = ms.getMember(id); 
		int result = 0;
		
		if(mvo == null)
			result = -1;
		else
			result = 1;
		
		mav.addObject("result", result);
		mav.addObject("id", id);
		mav.setViewName("member/idcheck");
		return mav;
	} // idCheckForm End
	
	// 주소찾기 창 
	@RequestMapping(value="/findZipNum")
	public ModelAndView find_zip(@RequestParam(value="dong", required = false) String dong) {
		ModelAndView mav = new ModelAndView();
		if(dong != null && dong.trim().equals("") == false) {
			mav.addObject("addressList", ms.selectAddressByDong(dong));
		}
		mav.setViewName("member/findZipNum");
		return mav;
	} // findZipNum End
	
	// 회원가입
	@RequestMapping(value="/join", method = RequestMethod.POST)
	public String join(@ModelAttribute("dto") @Valid MemberVO membervo,
			BindingResult result, Model model, HttpServletRequest request,
			@RequestParam(value="reid", required=false) String reid,
			@RequestParam(value="pwdCheck", required=false) String pwdCheck) {
		
		// 입력란 오류 확인
		if(result.getFieldError("id") != null) { // id 공백 오류
			model.addAttribute("message", result.getFieldError("id").getDefaultMessage());
			model.addAttribute("reid", reid);
			return "member/joinForm";
		}else if(result.getFieldError("pwd") != null) { // pwd 공백 오류
			model.addAttribute("message", result.getFieldError("pwd").getDefaultMessage());
			model.addAttribute("reid", reid);
			return "member/joinForm";
		}else if(result.getFieldError("name") != null) { // 이름 공백 오류
			model.addAttribute("message", result.getFieldError("name").getDefaultMessage());
			model.addAttribute("reid", reid);
			return "member/joinForm";
		}else if(result.getFieldError("email") != null) { // 이메일 공백 오류
			model.addAttribute("message", result.getFieldError("email").getDefaultMessage());
			model.addAttribute("reid", reid);
			return "member/joinForm";
		}else if(reid == null || (reid != null && reid.equals(membervo.getId()))) { // id 중복체크 미실시 오류
			model.addAttribute("message", "아이디 중복체크를 하지 않으셨습니다.");
			model.addAttribute("reid", reid);
			return "member/joinForm";
		}else if(pwdCheck == null || (pwdCheck != null && pwdCheck.equals(membervo.getPwd()))) {
			// 비밀번호 확인 미일치 오류
			model.addAttribute("message", "비밀번호 확인이 일치하지 않습니다..");
			model.addAttribute("reid", reid);
			return "member/joinForm";
		}else {
			membervo.setAddress(request.getParameter("addr1") + " " + request.getParameter("addr2"));
			// ms.insertMember(membervo);
			return "member/login";
		}
	} // join End
}
