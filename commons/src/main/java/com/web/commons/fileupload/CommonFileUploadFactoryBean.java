package com.web.commons.fileupload;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.IOUtils;

public final class CommonFileUploadFactoryBean {
	private String targetPath;//文件存放位置
	private long limitSize;//上传文件大小限制
	private List<String> mediaTypes = null;//上传的文件类型
	private int bufferSize = 1024*2;//default buffer size 2KB
	private String headerEnconding = "UTF-8";//
	private HttpServletRequest request;
	/**
	 * 单文件上传文件
	 * @param request
	 * @throws FileUploadException 
	 * @throws IOException 
	 */
	public File upload(HttpServletRequest request) throws FileUploadException, IOException{
		this.request = request;
		UploadingFile uploadingFile = this.parseRequest(request);
		File uploadedFile = this.saveFile(uploadingFile);
		
		return uploadedFile;
	}
	private File saveFile(UploadingFile uploadingFile) throws IOException {
		
		String path = getSavePath() + uploadingFile.getFileName();
		File file = new File(path);
		BufferedOutputStream outputStream = new BufferedOutputStream(new FileOutputStream(file));
		//保存文件
		IOUtils.copy(uploadingFile.getFileStream(), outputStream);
		IOUtils.closeQuietly(uploadingFile.getFileStream());
		IOUtils.closeQuietly(outputStream);
		return file;
	}
	private String getSavePath() {
		ServletContext context = this.request.getSession(true).getServletContext();
		String rootPath = context.getRealPath("/");
		String path = rootPath + targetPath;
		File file = new File(path);
		if(!file.exists()){
			file.mkdirs();
		}
		return path;
	}
	private UploadingFile parseRequest(HttpServletRequest request) throws FileUploadException, IOException {
		System.out.println(request.getParameter("name"));
		DiskFileItemFactory fileUploadFactory = new DiskFileItemFactory();
		fileUploadFactory.setSizeThreshold(bufferSize);
		
		ServletFileUpload fileHandler = new ServletFileUpload(fileUploadFactory);
		fileHandler.setFileSizeMax(limitSize);
		fileHandler.setHeaderEncoding(headerEnconding);
		//设置文件上传监听器
		fileHandler.setProgressListener(new CommonFileUploadListener(request));
		if(ServletFileUpload.isMultipartContent(request)){
			List<FileItem> fileItems = fileHandler.parseRequest(request);
			Iterator<FileItem> iter = fileItems.iterator();
			while(iter.hasNext()){
				FileItem item = iter.next();
				if(!item.isFormField()){
					//该字段是文件域
					return instanceFile(item);
				}
			}
			throw new FileUploadException("该请求不是有效的文件上传请求!");
		}else{
			throw new FileUploadException("该请求不是有效的文件上传请求!");
		}
	}
	private UploadingFile instanceFile(FileItem item) throws IOException {
		BufferedInputStream fileStream = new BufferedInputStream(item.getInputStream());
		UploadingFile file = new UploadingFile();
		file.setFileName(item.getName());
		file.setFileStream(fileStream);
		return file;
	}
	/**
	 * 多文件上传文件
	 * @param request
	 */
	public void multiUpload(HttpServletRequest request){
		 DiskFileItemFactory factory = new DiskFileItemFactory();
		 factory.setSizeThreshold(bufferSize);
	}
	
	public String getTargetPath() {
		return targetPath;
	}
	public void setTargetPath(String targetPath) {
		this.targetPath = targetPath;
	}
	public long getLimitSize() {
		return limitSize;
	}
	public void setLimitSize(long limitSize) {
		this.limitSize = limitSize;
	}
	public int getBufferSize() {
		return bufferSize;
	}
	public void setBufferSize(int bufferSize) {
		this.bufferSize = bufferSize;
	}
	public List<String> getMediaTypes() {
		return mediaTypes;
	}
	public void setMediaTypes(List<String> mediaTypes) {
		this.mediaTypes = mediaTypes;
	}
	
	static class UploadingFile{
		private String fileName;
		private float percent;
		private long fileSize;
		private long readedBytes;
		private BufferedInputStream fileStream = null;
		private File file = null;
		public String getFileName() {
			return fileName;
		}
		public void setFileName(String fileName) {
			this.fileName = fileName;
		}
		public BufferedInputStream getFileStream() {
			return fileStream;
		}
		public void setFileStream(BufferedInputStream fileStream) {
			this.fileStream = fileStream;
		}
		public File getFile() {
			return file;
		}
		public void setFile(File file) {
			this.file = file;
		}
		public float getPercent() {
			return percent;
		}
		public void setPercent(float percent) {
			this.percent = percent;
		}
		public long getFileSize() {
			return fileSize;
		}
		public void setFileSize(long fileSize) {
			this.fileSize = fileSize;
		}
		public long getReadedBytes() {
			return readedBytes;
		}
		public void setReadedBytes(long readedBytes) {
			this.readedBytes = readedBytes;
		}
	}

}
