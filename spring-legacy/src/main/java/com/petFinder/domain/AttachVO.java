package com.petFinder.domain;
/**
 * @title   : 공통 게시판 파일 정보 클래스
 * @author  : JIYUN
 * @date    : 2021.09.15 
 * @version : 1.0 
 **/
import lombok.Data;

@Data
public class AttachVO {
   
   private String uuid;
   private String uploadpath;
   private String filename;
   private String filetype;

   /*** 게시판 or 신고 ***/ 
   private String reportId; // 게시물 id(외래키) or 신고 id(외래키)
  

}  