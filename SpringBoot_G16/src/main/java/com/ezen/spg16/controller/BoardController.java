package com.ezen.spg16.controller;

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

import com.ezen.spg16.dto.BoardVO;
import com.ezen.spg16.dto.Paging;
import com.ezen.spg16.dto.ReplyVO;
import com.ezen.spg16.service.BoardService;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

@Controller
public class BoardController {
	@Autowired
	BoardService bs;
	
	@Autowired
	ServletContext context;
	
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
	
	@RequestMapping(value="/boardWriteForm")
	public String writeForm(Model model, HttpServletRequest request) {
		String url = "board/boardWriteForm";
		HttpSession session = request.getSession();
		if(session.getAttribute("loginUser")==null)
			url = "loginForm";
		return url;
	}
	
	/*@RequestMapping(value="/boardWrite", method = RequestMethod.POST)
	public String boardwrite(@ModelAttribute("dto") @Valid BoardVO boardvo,
			BindingResult result, Model model, HttpServletRequest request) {
		
		System.out.println("pass : " + boardvo.getPass());
		System.out.println("pass : " + boardvo.getTitle());
		System.out.println("pass : " + boardvo.getContent());
		if(result.getFieldError("pass") != null) {
			return "board/boardWriteForm";
		}else if(result.getFieldError("title") != null) {
			return "board/boardWriteForm";
		}else if(result.getFieldError("content") != null) {
			return "board/boardWriteForm";
		}else {
			// bs.insertBoard(boardvo);
			return "redirect:/main";
		}
	}*/
	
	@RequestMapping(value="/boardWrite", method = RequestMethod.POST)
	public String boardwrite(Model model, HttpServletRequest request) {
		String path = context.getRealPath("/upload");
		
		try {
			MultipartRequest multi = new MultipartRequest(request, path, 5*1024*1024, "UTF-8", new DefaultFileRenamePolicy());
			
			BoardVO bdto = new BoardVO();
			bdto.setPass(multi.getParameter("pass"));
			bdto.setUserid(multi.getParameter("userid"));
			bdto.setEmail(multi.getParameter("email"));
			bdto.setTitle(multi.getParameter("title"));
			bdto.setContent(multi.getParameter("content"));
			
			if(multi.getFilesystemName("image") == null) {
				bdto.setImgfilename("");
			}else {
				bdto.setImgfilename(multi.getFilesystemName("image"));
			}
			
			bs.insertBoard(bdto);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return "redirect:/main";
	}
	
	@RequestMapping(value="/boardView")
	public ModelAndView boardView(@RequestParam("num") int num, HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		
		BoardVO bdto = bs.boardView(num);
		mav.addObject("board", bdto);
		
		ArrayList<ReplyVO> list = bs.selectReply(num);
		mav.addObject("replyList", list);
		
		mav.setViewName("board/boardView");
		return mav;
	}
	
	@RequestMapping(value="/addReply")
	public String addReply(@RequestParam("boardnum") int boardnum,
			@RequestParam("userid") String userid, 
			@RequestParam("reply") String reply, HttpServletRequest request) {
	
		ReplyVO rvo = new ReplyVO();
		
		rvo.setUserid(userid);
		rvo.setContent(reply);
		rvo.setBoardnum(boardnum);
		
		bs.addReply(rvo);
		return "redirect:/boardViewWithoutCount?num=" + boardnum;
	}
	
	@RequestMapping(value="/boardViewWithoutCount")
	public ModelAndView boardViewNextUpdate(@RequestParam("num") int num, HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		BoardVO bdto = bs.getBoard(num);
		mav.addObject("board", bdto);
		
		ArrayList<ReplyVO> list = bs.selectReply(num);
		mav.addObject("replyList", list);
		
		mav.setViewName("board/boardView");
		return mav;
	}
	
	@RequestMapping(value="/deleteReply")
	public String reply_delete(HttpServletRequest request, @RequestParam("num") int num,
			@RequestParam("boardnum") int boardnum) {
		bs.deleteReply(num);
		return "redirect:/boardViewWithoutCount?num=" + boardnum;
	}
	
	@RequestMapping(value="/boardEditForm")
	public String board_edit_form(Model model, HttpServletRequest request) {
		String num = request.getParameter("num");
		model.addAttribute("num", num);
		return "board/boardCheckPassForm";
	}
}
