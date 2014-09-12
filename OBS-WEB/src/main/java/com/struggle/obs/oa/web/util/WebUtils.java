package com.struggle.obs.oa.web.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import com.struggle.obs.bean.user.User;
import com.struggle.obs.syscom.exception.OBSException;
import com.struggle.obs.syscom.util.ConstantDefine;

/**
 * Web工具类
 * 
 * @author tangyh 2014年8月29日 下午8:38:24
 */
public class WebUtils {
	/**
	 * 获取登录员工
	 */
	public static void addUser(HttpServletRequest request, User user) {
		request.getSession().setAttribute(ConstantDefine.SESSION_USER, user);
	}

	/**
	 * 获取登录用户
	 */
	public static User getUser(HttpServletRequest request) {
		return (User) request.getSession().getAttribute(
				ConstantDefine.SESSION_USER);
	}
	
	/**
	 * 移除登录员工信息
	 * 2014年9月4日 下午11:57:22
	 * @param request
	 * @param model void
	 */
	public static void removeUser(HttpServletRequest request) {
		request.getSession().removeAttribute(ConstantDefine.SESSION_USER);
	}

	/**
	 * 根据当前日期生成字符串<br>
	 * 2014年9月1日 下午10:01:14
	 * 
	 * @param imageFileName
	 *            yyyyMMddHHmmss
	 * @return String
	 */
	public static String getTimeStr() {
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
			String dateStr = sdf.format(new Date());
			return dateStr;
		} catch (Exception e) {
			return "";
		}
	}

	/**
	 * 获取文件名的后缀<br>
	 * 2014年9月1日 下午10:11:49
	 * 
	 * @param imageFileName
	 *            .xxx
	 * @return String
	 */
	public static String getExt(String imageFileName) {
		try {
			// 截取文件后缀
			String ext = imageFileName
					.substring(imageFileName.lastIndexOf('.') + 1).toLowerCase();
			return ext;
		} catch (Exception e) {
			return "";
		}
	}
	/**
	 * 保存图片到服务器硬盘<br>
	 * 2014年9月1日 下午10:14:58
	 * 
	 * @param request
	 *            request请求
	 * @param image
	 *            图片文件
	 * @param imagePullName
	 *            图片全名，包含后缀
	 * @param ext
	 *            .后缀名
	 */
	public static void saveImageFile(HttpServletRequest request, File image,
			String imagePullName, String ext, Long id) throws Exception {
		String pathdir = "/style/images/user/" + id + "/headPhoto";
		// 得到图片保存目录的真实路径
		String realpathdir = request.getSession().getServletContext()
				.getRealPath(pathdir);
		File savedir = new File(realpathdir);
		File file = saveFile(savedir, imagePullName, image);
		
		//压缩为120*120像素
		File file140 = new File(realpathdir, "120_" + imagePullName);
		//ext不能带 .
		ImageSizer.resize(file, file140, 120, ext);
		
		//压缩为30*30像素
		File file30 = new File(realpathdir, "30_" + imagePullName);
		//ext不能带 .
		ImageSizer.resize(file, file30, 30, ext);
	}
	
	/**
	 * 保存文件
	 * 
	 * @param savedir
	 *            存放目录
	 * @param fileName
	 *            文件名称
	 * @param data
	 *            保存的内容
	 * @return 保存的文件
	 * @throws Exception
	 */
	public static File saveFile(File savedir, String fileName, File imagefile)
			throws Exception {
		File file = null;
		InputStream fileInStream = null;
		FileOutputStream fileoutstream = null;
		try {
			if (!savedir.exists()) {
				savedir.mkdirs();// 如果目录不存在就创建
			}
			file = new File(savedir, fileName);
			fileInStream = new FileInputStream(imagefile);
			fileoutstream = new FileOutputStream(file);
			byte[] buffer = new byte[1024];
			int length = -1;
			while ((length = fileInStream.read(buffer)) > 0) {
				fileoutstream.write(buffer, 0, length);
			}
		} catch (Exception e) {
			throw new OBSException("保存图片失败", e);
		} finally {
			// 先关输入流
			if (fileInStream != null) {
				fileInStream.close();
			}
			// 在关输出流
			if (fileoutstream != null) {
				fileoutstream.close();
			}
		}
		return file;
	}

	

}
