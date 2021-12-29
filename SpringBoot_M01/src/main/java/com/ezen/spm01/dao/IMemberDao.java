package com.ezen.spm01.dao;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Mapper;

import com.ezen.spm01.dto.AddressVO;
import com.ezen.spm01.dto.MemberVO;

@Mapper
public interface IMemberDao {
	public MemberVO getMember(String id);

	public ArrayList<AddressVO> selectAddressByDong(String dong);

	public void insertMember(MemberVO membervo);

	public void updateMember(MemberVO membervo);
}
