package com.kh.spring.board.model.vo;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Attachment {
	
	private int attachmentNo;
	private String originalFileName;
	private String renameFileName;
	private Date uploadDate;
	private int downloadCount;
	private String status;
	
	// 210701
	private int boardNo;

}
