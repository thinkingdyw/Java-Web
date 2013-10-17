package com.think.core.zk.config;

public class ZookeeperConfiguration {

	private String host;
	private int timeOut;
	public String getHost() {
		return host;
	}
	public void setHost(String host) {
		this.host = host;
	}
	public int getTimeOut() {
		return timeOut;
	}
	public void setTimeOut(int timeOut) {
		this.timeOut = timeOut;
	}
}
