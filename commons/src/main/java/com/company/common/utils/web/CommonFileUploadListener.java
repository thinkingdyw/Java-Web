package com.company.common.utils.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.apache.commons.fileupload.ProgressListener;
import com.company.common.utils.web.CommonFileUploadFactoryBean.UploadingFile;

public class CommonFileUploadListener implements ProgressListener{

	private HttpServletRequest request;
	
	public CommonFileUploadListener(HttpServletRequest request){
		this.request = request;
	}
	@Override
	public void update(long pBytesRead, long pContentLength, int pItems) {
		HttpSession session = request.getSession(true);
		CommonFileUploadFactoryBean.UploadingFile file = null ;
		Object obj = session.getAttribute("uploadingFile");
		if(null == obj){
			file = new CommonFileUploadFactoryBean.UploadingFile();
			file.setFileSize(pContentLength);
		}else{
			file = (UploadingFile) obj;
		}
		float percent = (float)pBytesRead / pContentLength;
		
		file.setPercent(percent);
		file.setReadedBytes(pBytesRead);
		session.setAttribute("uploadingFile", file);
	}
}
