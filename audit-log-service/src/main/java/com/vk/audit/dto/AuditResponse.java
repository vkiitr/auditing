package com.vk.audit.dto;

import java.util.List;

public class AuditResponse {

	List<AuditRequest> data;
	private int pageNo;
	private int pageSize;
	private long totalElements;
	private int totalPages;
	private boolean last;

	public AuditResponse() {
		super();
	}

	public AuditResponse(List<AuditRequest> data) {
		super();
		this.data = data;
	}

	public AuditResponse(List<AuditRequest> data, int pageNo, int pageSize, long totalElements, int totalPages,
			boolean last) {
		super();
		this.data = data;
		this.pageNo = pageNo;
		this.pageSize = pageSize;
		this.totalElements = totalElements;
		this.totalPages = totalPages;
		this.last = last;
	}

	public List<AuditRequest> getData() {
		return data;
	}

	public void setData(List<AuditRequest> data) {
		this.data = data;
	}

	public int getPageNo() {
		return pageNo;
	}

	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public long getTotalElements() {
		return totalElements;
	}

	public void setTotalElements(long totalElements) {
		this.totalElements = totalElements;
	}

	public int getTotalPages() {
		return totalPages;
	}

	public void setTotalPages(int totalPages) {
		this.totalPages = totalPages;
	}

	public boolean isLast() {
		return last;
	}

	public void setLast(boolean last) {
		this.last = last;
	}

}
