package com.ezen.spm01.dao;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Mapper;

import com.ezen.spm01.dto.QnaVO;

@Mapper
public interface IQnaDao {
	public ArrayList<QnaVO> listQna(String id);
	public void insertQna(QnaVO qnavo);
}
