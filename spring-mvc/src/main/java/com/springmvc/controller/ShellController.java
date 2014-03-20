package com.springmvc.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingDeque;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.think.shell.Command;
import com.think.shell.LinuxShell;
import com.think.shell.Shell;

@Controller
@RequestMapping("/shell")
public class ShellController {

	@RequestMapping("/ping")
	@ResponseBody
	public void ping(HttpServletRequest request,final @RequestParam("ip")String ip,HttpServletResponse response){
		try {
			HttpSession session = request.getSession(true);
			Shell shell = new LinuxShell();
			Process p = shell.exe(new Command() {
				@Override
				public String getCmd() {
					return "ping "+ip;
				}
			});
			if(p != null){
				InputStream in = p.getInputStream();
				BufferedReader reader = new BufferedReader(new InputStreamReader(in,Charset.forName("gbk")));
				String line = reader.readLine();
				Queue<String> buffer = getBuffer(session);
				while(line != null){
					buffer.offer(line);
					System.out.println(line);
					line = reader.readLine();
				}
				if(reader != null){
					reader.close();
				}
				if(in != null){
					in.close();
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	private Queue<String> getBuffer(HttpSession session) {
		Queue<String> buffer = null;
		if(session.getAttribute("buffer") != null){
			buffer = (Queue<String>) session.getAttribute("buffer");
		}else{
			buffer = new LinkedBlockingDeque<String>();
			session.setAttribute("buffer", buffer);
		}
		return buffer;
	}
	@RequestMapping("/show/console")
	@ResponseBody
	public Queue<String> getConsoleInfo(HttpServletRequest request){
		HttpSession session = request.getSession();
		Queue<String> queue = getBuffer(session);
		return queue;
	}
	@RequestMapping("/toshell")
	public ModelAndView toShell(HttpServletRequest request){
		ModelAndView view = new ModelAndView("views/shell");
		return view;
	}
}
