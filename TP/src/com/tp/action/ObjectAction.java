package com.tp.action;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
public class ObjectAction {
	private HttpServletRequest request;
	private HttpServletResponse response;
	private PrintWriter printWriter;
	public ObjectAction(){
		try {
			response = ServletActionContext.getResponse();
			request = ServletActionContext.getRequest();
			response.setCharacterEncoding("UTF-8");
			request.setCharacterEncoding("UTF-8");
			response.setContentType("text/html;charset=UTF-8");
			printWriter=response.getWriter();
		} catch (Exception e) {
			e.printStackTrace();
		}	
	}
	public HttpServletRequest getRequest() {
		return request;
	}
	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}
	public HttpServletResponse getResponse() {
		return response;
	}
	public void setResponse(HttpServletResponse response) {
		this.response = response;
	}
	public PrintWriter getPrintWriter() {
		return printWriter;
	}
	public void setPrintWriter(PrintWriter printWriter) {
		this.printWriter = printWriter;
	}
	 public void writerClose(){
		 this.printWriter.flush();
		 this.printWriter.close();
	 }
}
