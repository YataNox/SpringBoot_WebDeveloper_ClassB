package com.ezen.spm01.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ezen.spm01.dao.IMemberDao;
import com.ezen.spm01.dto.MemberVO;

@Service
public class MemberService {
	@Autowired
	IMemberDao mdao;

	public MemberVO getMember(String id) {
		return mdao.getMember(id);
	}
}
