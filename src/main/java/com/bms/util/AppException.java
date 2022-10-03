package com.bms.util;

/*
 * lhy
 * 自定义异常类
 */
public final class AppException extends Exception {

	private static final long serialVersionUID = -3042307748648946113L;

	private String errtext;
	/**
     * 默认异常构造器
     */
    public AppException() {
    }

    /**
     * 构造带有指定信息的异常
     */
    public AppException(String s, String errtext) {
    	super(s);
    	this.errtext = errtext;
    }

	public String getErrtext() {
		return errtext;
	}

	public void setErrtext(String errtext) {
		this.errtext = errtext;
	}
}
