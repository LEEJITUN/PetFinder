package com.petFinder.service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.petFinder.domain.AttachVO;
import com.petFinder.domain.MemberProfileVO;
import com.petFinder.mapper.BannerMapper;

import net.coobird.thumbnailator.Thumbnailator;

@Service
@Transactional
public class AttachFile {	
	
	/************************************ 실물 파일 처리 *************************************/
	
	/**
	 *  @methodName : getFolder 
	 *  @author 	: JIYUN
	 *  @date 		: 2021.09.15
	 *  @return		: String
	 **/
	// 년/월/일 형식의 폴더명 리턴하는 메소드
	public String getFolder() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
		String str = sdf.format(new Date());
		return str;
	}
	
	/**
	 *  @methodName : checkImageType 
	 *  @author 	: JIYUN
	 *  @date 		: 2021.09.15
	 *  @param		: file
	 *  @return		: boolean
	 **/
	// 이미지 파일인지 여부 리턴하는 메소드
	public boolean checkImageType(File file) throws IOException {
		boolean isImage = false;
		
		String contentType = Files.probeContentType(file.toPath()); // "image/jpg"
		System.out.println("contentType : " + contentType);
		
		isImage = contentType.startsWith("image");
		return isImage;
	}
	
	
	/**
	 *  @methodName : uploadFilesAndGetAttachList 
	 *  @author 	: JIYUN
	 *  @date 		: 2021.09.15
	 *  @param		: files 
	 *  @param		: id  (boardId or reportId)
	 *  @param		: fileForder (boardTypeID)
	 *  @return		: List<AttachVO>
	 **/
	// 첨부파일 업로드(썸네일 생성) 후 attacList 리턴하는 메소드
	public List<AttachVO> uploadFilesAndGetAttachList(List<MultipartFile> files, String id,String fileForder) throws IllegalStateException, IOException {
		
		List<AttachVO> attachList = new ArrayList<AttachVO>();
		

		// 생성할 파일정보가 없으면 종료
		if (files == null || files.size() == 0) {
			System.out.println("업로드한 첨부파일 개수 : " + files.size());
			return attachList;
		}
		
		// 폴더 만들기
		File uploadPath = makeForder(fileForder,"C");
		
		
		for (MultipartFile multipartFile : files) {
			
			// 파일 업로드
			AttachVO attachVO =  (AttachVO) fileUpload(multipartFile,uploadPath,id,"A",fileForder);
			
			attachList.add(attachVO); // 리스트에 추가
		} // for
		
		return attachList;
	} // uploadFilesAndGetAttachList
	
		
	
	/**
	 *  @methodName : deleteAttachFiles 
	 *  @author 	: JIYUN
	 *  @date 		: 2021.09.15
	 *  @param		: deleteAttachFiles 
	 *  @return		: void
	 **/
	public void deleteAttachFiles(List<AttachVO> attachList) {
		// 삭제할 파일정보가 없으면 메소드 종료
		if (attachList == null || attachList.size() == 0) {
			return;
		}
		
		String basePath = "C:/upload";
		
		for (AttachVO attachVO : attachList) {
			String uploadpath = basePath + "/" + attachVO.getUploadpath();
			String filename = attachVO.getUuid() + "_" + attachVO.getFilename();
			
			File file = new File(uploadpath, filename);
			
			if (file.exists() == true) { // 해당 경로에 첨부파일이 존재하면
				file.delete(); // 첨부파일 삭제하기
			}
			
			// 섬네일 이미지 삭제하기
			File thumbnailFile = new File(uploadpath, "s_" + filename);
			if (thumbnailFile.exists() == true) {
				thumbnailFile.delete();
			}
			
		} // for
	} // deleteAttachFiles
	
	/**
	 *  @methodName : deleteProfileFile
	 *  @author 	: JIYUN
	 *  @date 		: 2021.09.15
	 *  @param		: deleteAttachFiles 
	 *  @return		: void
	 **/
	public void deleteProfileFile(MemberProfileVO profileVO) {
		// 삭제할 파일정보가 없으면 메소드 종료
		if (profileVO == null ) {
			return;
		}
		
		String basePath = "C:/upload";
	
		String uploadpath = basePath + "/" + profileVO.getUploadpath();
		String filename = profileVO.getUuid() + "_" + profileVO.getFilename();
		
		File file = new File(uploadpath, filename);
		
		if (file.exists() == true) { // 해당 경로에 첨부파일이 존재하면
			file.delete(); // 첨부파일 삭제하기
		}
		
		// 섬네일 이미지 삭제하기
		File thumbnailFile = new File(uploadpath, "s_" + filename);
		if (thumbnailFile.exists() == true) {
			thumbnailFile.delete();
		}
			
		
	} // deleteAttachFiles
	
	/**
	 * 	프로필 사진 업로드
	 * 
	 *  @methodName : uploadProfilePic 
	 *  @author 	: JIYUN
	 *  @date 		: 2021.09.15
	 *  @param		: file 
	 *  @param		: id
	 *  @return		: MemberProfileVO
	 **/
	public MemberProfileVO uploadProfilePic(MultipartFile file, String id) throws IllegalStateException, IOException {
		
		MemberProfileVO profilePicVO = new MemberProfileVO();
		
		// 생성할 파일정보가 없으면 종료
		if (file == null) {
			return profilePicVO;
		}
		
		// 폴더 만들기
		File uploadPath = makeForder("profilePic/" + id,"P");
		
		// 파일 업로드
		profilePicVO = (MemberProfileVO) fileUpload(file,uploadPath,id,"P","profilePic");
		
		return profilePicVO;
	} // uploadProfilePic
		
	
	/**
	 * 	폴더 생성
	 * 
	 *  @methodName : makeForder 
	 *  @author 	: JIYUN
	 *  @date 		: 2021.09.15
	 *  @param		: forderName 
	 *  @param		: fileType 
	 *  @return		: File
	 **/
	public File makeForder (String forderName,String fileType) {
		
		String uploadFolder = "C:/upload/" + forderName;  // 업로드 기준경로 (D:/upload/Adoptemp/)
		
		File uploadPath;
		
		// 프로필 사진일 경우
		if(fileType.equals("P")) {
			uploadPath = new File(uploadFolder); // C:/upload/Adoptemp
		}else {
			uploadPath = new File(uploadFolder, getFolder()); // C:/upload/Adoptemp/2021/08/31
		}
		
		if (uploadPath.exists() == false) {  // !uploadPath.exists()
			uploadPath.mkdirs();
		}
		
		return uploadPath;
	}

	
	/**
	 * 	파일 업로드
	 * 
	 *  @methodName : fileUpload 
	 *  @author 	: JIYUN
	 *  @date 		: 2021.09.15
	 *  @param		: file 
	 *  @param		: uploadPath 
	 *  @param		: id 
	 *  @param		: fileType(attach파일인지 , profile 사진인지) 
	 *  @return		: Object
	 **/
	private Object fileUpload (MultipartFile file, File uploadPath, String id,String fileType,String fileForder) throws IllegalStateException, IOException {
	
		Object ob = new Object();
		
		if (!file.isEmpty()) {
			String originalFilename = file.getOriginalFilename();
			UUID uuid = UUID.randomUUID();
			String uploadFilename = uuid.toString() + "_" + originalFilename;
			
			File proFile = new File(uploadPath, uploadFilename); // 생성할 파일이름 정보
			
			// 파일 업로드(파일 생성) 완료
			file.transferTo(proFile);
			// ======================================================
			
			// 현재 업로드한 파일이 이미지 파일이면 썸네일 이미지를 추가로 생성하기
			File outFile = new File(uploadPath, "s_" + uploadFilename);
			
			Thumbnailator.createThumbnail(proFile, outFile, 300, 300);  // 썸네일 이미지 파일 생성하기
			
			// 저장 할 객체
			if(fileType.equals("A")) {				
				//===== insert할 주글 AttachVO 객체 데이터 생성 ======
				AttachVO attachVO = new AttachVO();
				attachVO.setUuid(uuid.toString());
				attachVO.setUploadpath(fileForder+ "/" + getFolder());
				attachVO.setFilename(originalFilename);
				attachVO.setReportId(id);
				ob = attachVO;
			}else {
				//===== insert할 주글 AttachVO 객체 데이터 생성 ======
				MemberProfileVO memberProfileVO = new MemberProfileVO();
				memberProfileVO.setUuid(uuid.toString());
				memberProfileVO.setUploadpath("profilePic/" + id);
				memberProfileVO.setFilename(originalFilename);
				memberProfileVO.setMemberId(id);
				
				ob = memberProfileVO;
			}
		}else {
			return null;
		}
		
		return ob;
	}


}
