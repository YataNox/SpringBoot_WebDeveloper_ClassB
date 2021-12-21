package com.ezen.spg10.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.ezen.spg10.BbsDto;

@Repository
public class BbsDao implements IBbsDao{

	@Autowired
	private JdbcTemplate template;
	
	public List<BbsDto> list() {
		String sql = "select * from bbs";
		List<BbsDto> list = template.query(sql, new BeanPropertyRowMapper<BbsDto>(BbsDto.class));
		return list;
	}

	public int write(BbsDto bdto) {
		
		return 0;
	}

	public int update(BbsDto bdto) {
		
		return 0;
	}

	public int delete(String id) {
		
		return 0;
	}

	public BbsDto view(String id) {
		
		return null;
	}
	
}
