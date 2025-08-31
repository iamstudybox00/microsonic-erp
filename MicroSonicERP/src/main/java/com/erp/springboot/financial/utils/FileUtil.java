package com.erp.springboot.financial.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import org.springframework.util.ResourceUtils;
import org.springframework.web.multipart.MultipartFile;

import jakarta.servlet.ServletException;

public class FileUtil {
	public static void uploadFiles(List<MultipartFile> fileList, List<String> sfileList)
			throws IOException, ServletException {
		String uploadDir = ResourceUtils.getFile("classpath:static/uploads/").toPath().toString();
		System.out.println(uploadDir);

		for (int i = 0; i < fileList.size(); i++) {
			MultipartFile file = fileList.get(i);
			file.transferTo(
					new File(uploadDir + File.separator + sfileList.get(i)));
		}
	}

	public static void deleteFiles(List<String> sfileList) throws FileNotFoundException{
		String uploadDir = ResourceUtils.getFile("classpath:static/uploads/").toPath().toString();
		for (int i = 0; i < sfileList.size(); i++) {
			File deleteFile = new File(uploadDir + File.separator + sfileList.get(i));
			if(deleteFile.exists()) {
				deleteFile.delete();
			}
		}
	}
//
//	public static void downloadFile(String sfileName, String ofileName, HttpServletResponse resp) {
//		String uploadDir;
//
//		try {
//			uploadDir = ResourceUtils.getFile("classpath:static/uploads/").toPath().toString();
//			String path = uploadDir + "/" + sfileName;
//			File file = new File(path);
//			FileInputStream in = new FileInputStream(path);
//			String fileNameCon = new String(ofileName.getBytes("UTF-8"), "8859_1");
//
//			resp.setContentType("application/octet-stream");
//			resp.setHeader("Content-Disposition", "attachment; filename=" + fileNameCon);
//			OutputStream os = resp.getOutputStream();
//
//			int length;
//			byte[] b = new byte[(int) file.length()];
//			while ((length = in.read(b)) > 0) {
//				os.write(b, 0, length);
//			}
//
//			os.flush();
//			os.close();
//			in.close();
//		} catch (IOException e) {
////			System.out.println("다운에러");
//			e.printStackTrace();
//		}
//	}
//
//	public static int deleteFiles(ArrayList<FileDTO> list) {
//		if (list.size() < 1)
//			return 0;
//		int deleteCount = 0;
//		try {
//			String uploadDir = ResourceUtils.getFile("classpath:static/uploads/").toPath().toString();
//			for (FileDTO dto : list) {
//				Path filePath = Paths.get(uploadDir + "/" + dto.getSfile());
//
//				if (Files.exists(filePath)) {
//					Files.delete(filePath);
//					deleteCount++;
//				}
//			}
//		} catch (FileNotFoundException e) {
//			e.printStackTrace();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//
//		return deleteCount;
//	}
//
//	public static int deleteOneFile(String sFile) {
//		try {
//			String uploadDir = ResourceUtils.getFile("classpath:static/uploads/").toPath().toString();
//			Path filePath = Paths.get(uploadDir + "/" + sFile);
//
//			if (Files.exists(filePath)) {
//				Files.delete(filePath);
//			}
//		} catch (FileNotFoundException e) {
//			e.printStackTrace();
//			return 0;
//		} catch (IOException e) {
//			e.printStackTrace();
//			return 0;
//		}
//
//		return 1;
//	}
}
