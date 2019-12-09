package com.sziti.counterfeittopnews.http.Response;

public class ResultResponse<T> {
	private T data;
	private boolean success;

	public T getData() {
		return data;
	}

	public boolean isSuccess() {
		return success;
	}
}
