package com.ezen.spm01.dto;

import java.sql.Timestamp;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class ProductVO {
	@NotNull
	@NotEmpty
	private Integer pseq;
	private String name;
	private String kind;
	private Integer price1;
	@NotNull
	@NotEmpty
	private Integer price2;
	private Integer price3;
	@NotNull
	@NotEmpty
	private String content;
	private String image;
	private String useyn;
	private String bestyn;
	private Timestamp indate;
}
