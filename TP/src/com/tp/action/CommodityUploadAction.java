package com.tp.action;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;
import com.tp.tools.JsonUtil;
public class CommodityUploadAction{
	// 这里用List来存放上传过来的文件，file同样指的是临时文件夹中的临时文件，而不是真正上传过来的文件
	private List<File> file;
	// 这个List存放的是文件的名字，和List<File>中的文件相对应
	private List<String> fileFileName;

	public List<File> getFile() {
		return file;
	}

	public void setFile(List<File> file) {
		this.file = file;
	}

	public List<String> getFileFileName() {
		return fileFileName;
	}

	public void setFileFileName(List<String> fileFileName) {
		this.fileFileName = fileFileName;
	}

	public void execute() throws Exception {
		HttpServletResponse response = ServletActionContext.getResponse();
		HttpServletRequest request = ServletActionContext.getRequest();
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter printWrite = response.getWriter();
		BufferedInputStream reader=null;
		String root = ServletActionContext.getServletContext().getRealPath(
				"/upload");
		List<String>urls=new LinkedList<String>();
		for (int i = 0; i < file.size(); i++) {
		    reader = new BufferedInputStream(
					new FileInputStream(file.get(i)));
			FileUtils.copyFile(file.get(i), new File("C://upload", fileFileName.get(i)));// 复制文件
			String url="upload/"+fileFileName.get(i);
			urls.add(url);
		}
		printWrite.write(JsonUtil.toJson(urls));
		reader.close();
		printWrite.close();
	}
}
