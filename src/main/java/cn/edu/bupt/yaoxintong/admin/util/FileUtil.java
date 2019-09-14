package cn.edu.bupt.yaoxintong.admin.util;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.MultipartFile;

public class FileUtil {
	private static final Logger logger = Logger.getInstance();
	
	public static boolean store(MultipartFile file, String path, String filename) {
		if (!file.isEmpty()) {
			// 上传文件路径
			// 上传文件名
			File filepath = new File(path, filename);
			// 判断路径是否存在，如果不存在就创建一个
			if (!filepath.getParentFile().exists()) {
				filepath.getParentFile().mkdirs();
			}
			// 将上传文件保存到一个目标文件当中
			try {
				file.transferTo(new File(path + File.separator + filename));
			} catch (IllegalStateException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			logger.info("save path--->"+path);
			return true;
		}
		return false;
	}

	public static String storeImgRondom(MultipartFile file, HttpServletRequest request) throws IllegalStateException, IOException {
		Date currentTime = new Date();
		SimpleDateFormat formatter1 = new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss_");
		String times = formatter1.format(currentTime);
		String filename = times+file.getOriginalFilename();
		SimpleDateFormat formatter2 = new SimpleDateFormat("yyyy_MM_dd_");
		String pathtimes = formatter2.format(currentTime);
		String url = "/imgs/"+pathtimes+"/"+filename;
		String path = request.getServletContext().getRealPath("/images/"+pathtimes+"/");
		logger.info("url--->"+url);
		return FileUtil.store(file, path, filename)?url:null;
	}
}
