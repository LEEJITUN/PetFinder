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
	
	/************************************ �ǹ� ���� ó�� *************************************/
	
	/**
	 *  @methodName : getFolder 
	 *  @author 	: JIYUN
	 *  @date 		: 2021.09.15
	 *  @return		: String
	 **/
	// ��/��/�� ������ ������ �����ϴ� �޼ҵ�
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
	// �̹��� �������� ���� �����ϴ� �޼ҵ�
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
	// ÷������ ���ε�(����� ����) �� attacList �����ϴ� �޼ҵ�
	public List<AttachVO> uploadFilesAndGetAttachList(List<MultipartFile> files, String id,String fileForder) throws IllegalStateException, IOException {
		
		List<AttachVO> attachList = new ArrayList<AttachVO>();
		

		// ������ ���������� ������ ����
		if (files == null || files.size() == 0) {
			System.out.println("���ε��� ÷������ ���� : " + files.size());
			return attachList;
		}
		
		// ���� �����
		File uploadPath = makeForder(fileForder,"C");
		
		
		for (MultipartFile multipartFile : files) {
			
			// ���� ���ε�
			AttachVO attachVO =  (AttachVO) fileUpload(multipartFile,uploadPath,id,"A",fileForder);
			
			attachList.add(attachVO); // ����Ʈ�� �߰�
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
		// ������ ���������� ������ �޼ҵ� ����
		if (attachList == null || attachList.size() == 0) {
			return;
		}
		
		String basePath = "C:/upload";
		
		for (AttachVO attachVO : attachList) {
			String uploadpath = basePath + "/" + attachVO.getUploadpath();
			String filename = attachVO.getUuid() + "_" + attachVO.getFilename();
			
			File file = new File(uploadpath, filename);
			
			if (file.exists() == true) { // �ش� ��ο� ÷�������� �����ϸ�
				file.delete(); // ÷������ �����ϱ�
			}
			
			// ������ �̹��� �����ϱ�
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
		// ������ ���������� ������ �޼ҵ� ����
		if (profileVO == null ) {
			return;
		}
		
		String basePath = "C:/upload";
	
		String uploadpath = basePath + "/" + profileVO.getUploadpath();
		String filename = profileVO.getUuid() + "_" + profileVO.getFilename();
		
		File file = new File(uploadpath, filename);
		
		if (file.exists() == true) { // �ش� ��ο� ÷�������� �����ϸ�
			file.delete(); // ÷������ �����ϱ�
		}
		
		// ������ �̹��� �����ϱ�
		File thumbnailFile = new File(uploadpath, "s_" + filename);
		if (thumbnailFile.exists() == true) {
			thumbnailFile.delete();
		}
			
		
	} // deleteAttachFiles
	
	/**
	 * 	������ ���� ���ε�
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
		
		// ������ ���������� ������ ����
		if (file == null) {
			return profilePicVO;
		}
		
		// ���� �����
		File uploadPath = makeForder("profilePic/" + id,"P");
		
		// ���� ���ε�
		profilePicVO = (MemberProfileVO) fileUpload(file,uploadPath,id,"P","profilePic");
		
		return profilePicVO;
	} // uploadProfilePic
		
	
	/**
	 * 	���� ����
	 * 
	 *  @methodName : makeForder 
	 *  @author 	: JIYUN
	 *  @date 		: 2021.09.15
	 *  @param		: forderName 
	 *  @param		: fileType 
	 *  @return		: File
	 **/
	public File makeForder (String forderName,String fileType) {
		
		String uploadFolder = "C:/upload/" + forderName;  // ���ε� ���ذ�� (D:/upload/Adoptemp/)
		
		File uploadPath;
		
		// ������ ������ ���
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
	 * 	���� ���ε�
	 * 
	 *  @methodName : fileUpload 
	 *  @author 	: JIYUN
	 *  @date 		: 2021.09.15
	 *  @param		: file 
	 *  @param		: uploadPath 
	 *  @param		: id 
	 *  @param		: fileType(attach�������� , profile ��������) 
	 *  @return		: Object
	 **/
	private Object fileUpload (MultipartFile file, File uploadPath, String id,String fileType,String fileForder) throws IllegalStateException, IOException {
	
		Object ob = new Object();
		
		if (!file.isEmpty()) {
			String originalFilename = file.getOriginalFilename();
			UUID uuid = UUID.randomUUID();
			String uploadFilename = uuid.toString() + "_" + originalFilename;
			
			File proFile = new File(uploadPath, uploadFilename); // ������ �����̸� ����
			
			// ���� ���ε�(���� ����) �Ϸ�
			file.transferTo(proFile);
			// ======================================================
			
			// ���� ���ε��� ������ �̹��� �����̸� ����� �̹����� �߰��� �����ϱ�
			File outFile = new File(uploadPath, "s_" + uploadFilename);
			
			Thumbnailator.createThumbnail(proFile, outFile, 300, 300);  // ����� �̹��� ���� �����ϱ�
			
			// ���� �� ��ü
			if(fileType.equals("A")) {				
				//===== insert�� �ֱ� AttachVO ��ü ������ ���� ======
				AttachVO attachVO = new AttachVO();
				attachVO.setUuid(uuid.toString());
				attachVO.setUploadpath(fileForder+ "/" + getFolder());
				attachVO.setFilename(originalFilename);
				attachVO.setReportId(id);
				ob = attachVO;
			}else {
				//===== insert�� �ֱ� AttachVO ��ü ������ ���� ======
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
