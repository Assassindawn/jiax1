package com.bms.util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 对分页的基本数据进行一个简单的封装
 */
public class Page<T> {

	private int page;// 当前页
	private int rows = 10;// 每页显示记录数
    private int pageNo = 1;//页码，默认是第一页

	private Long totalRecord;//总记录数
    private String parameters;

    private List<T> results;//对应的当前页记录


    private Long total;//总页数


	//查询参数
    private Map<String, Object> params = new HashMap<String, Object>();//其他的参数我们把它分装成一个Map对象

	public Long getTotalRecord() {
	       return totalRecord;
    }


    public void setTotalRecord(Long totalRecord) {
        this.totalRecord = totalRecord;
        //在设置总页数的时候计算出对应的总页数，在下面的三目运算中加法拥有更高的优先级，所以最后可以不加括号。
        //int totalPage = totalRecord%pageSize==0 ? totalRecord/pageSize : totalRecord/pageSize + 1;
        Long totalPage = totalRecord%rows==0 ? totalRecord/rows : totalRecord/rows + 1;
        this.setTotal(totalPage);
     }


	public Map<String, Object> getParams() {
		return params;
	}

	public void setParams(Map<String, Object> params) {
		this.params = params;
	}



    public String getParameters() {
		return parameters;
	}

	public void setParameters(String parameters) {
		this.parameters = parameters;
	}


	public int getPage() {
		return page;
	}


	public void setPage(int page) {
		this.page = page;
	}


	public int getRows() {
		return rows;
	}


	public void setRows(int rows) {
		this.rows = rows;
	}


	public int getPageNo() {
		return pageNo;
	}


	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}


	public Long getTotal() {
		return total;
	}


	public void setTotal(Long total) {
		this.total = total;
	}


	public List<T> getResults() {
		return results;
	}


	public void setResults(List<T> results) {
		this.results = results;
	}






}
