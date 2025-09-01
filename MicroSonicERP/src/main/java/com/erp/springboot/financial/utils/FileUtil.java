package com.erp.springboot.financial.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

import org.springframework.util.ResourceUtils;
import org.springframework.web.multipart.MultipartFile;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletResponse;

public class FileUtil {
	public static void uploadFiles(List<MultipartFile> fileList, List<String> sfileList)
			throws IOException, ServletException {
		String uploadDir = ResourceUtils.getFile("classpath:static/uploads/").toPath().toString();
		System.out.println(uploadDir);

		for (int i = 0; i < fileList.size(); i++) {
			MultipartFile file = fileList.get(i);
			String fileName = sfileList.get(i);
			file.transferTo(new File(uploadDir + File.separator + fileName.substring(0, fileName.lastIndexOf("."))));
		}
	}

	public static void deleteFiles(List<String> sfileList) throws FileNotFoundException {
		String uploadDir = ResourceUtils.getFile("classpath:static/uploads/").toPath().toString();
		for (int i = 0; i < sfileList.size(); i++) {
			File deleteFile = new File(uploadDir + File.separator + sfileList.get(i));
			if (deleteFile.exists()) {
				deleteFile.delete();
			}
		}
	}
//	
//	public static byte[] readFileContent(String fileName) throws FileNotFoundException {
//		String uploadDir = "C:/DevData/project/MicroSonicERP/MicroSonicERP/bin/main/static/uploads/";
//        Path filePath = Paths.get(uploadDir).resolve(fileName).normalize();
//        
//        try {
//            return Files.readAllBytes(filePath);
//        } catch (IOException e) {
//            throw new RuntimeException("Failed to read file", e);
//        }
//    }
//	
//	public static ResponseEntity<byte[]> createResponseEntity(String fileName, byte[] fileContent) {
//	    String cleanFileName = StringUtils.cleanPath(fileName);
//	    HttpHeaders headers = new HttpHeaders();
//	    headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
//	    headers.setContentDispositionFormData("attachment", cleanFileName);
//	    return ResponseEntity.ok()
//	        .headers(headers)
//	        .body(fileContent);
//	}

	public static void downloadFile(String sfile, String ofile, HttpServletResponse resp) throws IOException {
		try {
			String uploadDir = ResourceUtils.getFile("classpath:static/uploads/").toPath().toString();
			String path = uploadDir + "/" + sfile;
			File file = new File(path);
			FileInputStream in = new FileInputStream(path);
			String fileNameCon = new String(ofile.getBytes("UTF-8"), "8859_1");

			resp.setContentType("application/octet-stream");
			resp.setHeader("Content-Disposition", "attachment; filename=" + fileNameCon);
			OutputStream os = resp.getOutputStream();

			int length;
			byte[] b = new byte[(int) file.length()];
			while ((length = in.read(b)) > 0) {
				os.write(b, 0, length);
			}

			os.flush();
			os.close();
			in.close();
		} catch (IOException e) {
//			System.out.println("다운에러");
			e.printStackTrace();
		}
	}
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
