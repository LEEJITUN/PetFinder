package com.petFinder.domain;
/**
 * @title   : ������ ���� ���� Ŭ����
 * @author  : JIYUN
 * @date    : 2021.09.15 
 * @version : 1.0 
 **/
import lombok.Data;

@Data
public class MemberProfileVO {
   
   private String uuid;
   private String memberId;
   private String uploadpath;
   private String filename;
 
}  