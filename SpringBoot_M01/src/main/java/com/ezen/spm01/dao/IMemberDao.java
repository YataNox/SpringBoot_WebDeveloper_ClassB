package com.ezen.spm01.dao;

import org.apache.ibatis.annotations.Mapper;

import com.ezen.spm01.dto.MemberVO;

@Mapper
public interface IMemberDao {
	MemberVO getMember(String id);
}
