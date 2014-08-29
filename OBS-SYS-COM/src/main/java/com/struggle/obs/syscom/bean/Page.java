package com.struggle.obs.syscom.bean;

import java.util.List;

public class Page<T> {
	// 需要传入的
	/** 当前页 */
	private int pageNum;
	/** 每页显示多少条 */
	private int pageSize;
	/** 每页页码数 */
	private int pages;

	// 查询数据库
	/** 总记录数 */
	private int totalRecord;
	/** 数据集合 */
	private List<T> resultList;

	// 计算得出
	/** 总页数 */
	private int totalPage;
	/** 每页页码列表的开始索引(包含) */
	private int startPage;
	/** 每页页码列表的结束索引(包含) */
	private int endPage;
	/** 数据库查询的开始索引 */
	private int firstResult;

	/**
	 * 只接受前4个必要的属性，会自动的计算出其他属性的值<br>
	 * 
	 * @param pageNum
	 *            当前页        默认值=10
	 * @param pageSize
	 *            每页显示多少条 默认值=1
	 * @param pages
	 *            每页页码数 默认值=10
	 * @param totalRecord
	 *            总记录数
	 */
	public Page(int pageNum, int pageSize, int pages, int totalRecord, List<T> resultList) {
		this.pageNum = pageNum;
		this.pageSize = pageSize;
		this.pages = pages;
		this.totalRecord = totalRecord;
		this.resultList = resultList;

		if (this.pages <= 0) {
			this.pages = 10;
		}

		if (this.pageNum <= 0) {
			this.pageNum = 1;
		}

		if (this.pageSize <= 0) {
			this.pageSize = 10;
		}

		// 算出总页数
		this.totalPage = (this.totalRecord + this.pageSize - 1) / this.pageSize;

		// 如果当前页大于总页数 ，就置当前页为最后一页
		if (this.pageNum > this.totalPage && this.totalPage != 0) {
			this.pageNum = this.totalPage;
		}

		// 算出用户想看的页的数据从数据库哪个地方开始取
		this.firstResult = (this.pageNum - 1) * this.pageSize;

		// 如果总页数小于每页页码数量
		if (this.totalPage <= this.pages) {
			this.startPage = 1;
			this.endPage = this.totalPage;
		} else {
			this.startPage = this.pageNum - (this.pages - 1) / 2;
			this.endPage = this.pageNum + this.pages / 2;

			if (this.startPage < 1) {
				this.startPage = 1;
				this.endPage = this.pages;
			}
			if (this.endPage > this.totalPage) {
				this.endPage = this.totalPage;
				this.startPage = this.totalPage - this.pages + 1;
			}
		}
	}

	/** 当前页 */
	public int getPageNum() {
		return pageNum;
	}

	/** 当前页 */
	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}

	/** 每页显示多少条 */
	public int getPageSize() {
		return pageSize;
	}

	/** 每页显示多少条 */
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	/** 每页页码数 */
	public int getPages() {
		return pages;
	}

	/** 每页页码数 */
	public void setPages(int pages) {
		this.pages = pages;
	}

	/** 总记录数 :调用Dao方法查询后,通过构造方法传入 */
	public int getTotalRecord() {
		return totalRecord;
	}

	/** 总记录数 :调用Dao方法查询后,通过构造方法传入 */
	public void setTotalRecord(int totalRecord) {
		this.totalRecord = totalRecord;
	}

	/** 数据集合：调用Dao方法查询后调用setter传入 */
	public List<T> getResultList() {
		return resultList;
	}

	/** 数据集合：调用Dao方法查询后调用setter传入 */
	public void setResultList(List<T> resultList) {
		this.resultList = resultList;
	}

	/** 总页数 */
	public int getTotalPage() {
		return totalPage;
	}

	/** 总页数 */
	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

	/** 每页页码列表的开始索引(包含) */
	public int getStartPage() {
		return startPage;
	}

	/** 每页页码列表的开始索引(包含) */
	public void setStartPage(int startPage) {
		this.startPage = startPage;
	}

	/** 每页页码列表的结束索引(包含) */
	public int getEndPage() {
		return endPage;
	}

	/** 每页页码列表的结束索引(包含) */
	public void setEndPage(int endPage) {
		this.endPage = endPage;
	}

	/** 数据库查询的开始索引 */
	public int getFirstResult() {
		return firstResult;
	}

	/** 数据库查询的开始索引 */
	public void setFirstResult(int firstResult) {
		this.firstResult = firstResult;
	}
	
	
	
}
