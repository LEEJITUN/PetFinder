package com.petFinder.domain;
/**
 * @title   : ���� �Խ��� ���� ���� Ŭ����
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

   /*** �Խ��� or �Ű� ***/ 
   private String reportId; // �Խù� id(�ܷ�Ű) or �Ű� id(�ܷ�Ű)
  

}  