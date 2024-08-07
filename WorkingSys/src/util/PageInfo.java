package util;

import java.util.List;


public class PageInfo<T> {
    private int currPageNo; 	// 当前页码
    private int pageSize;    	// 页面大小，即每页显示记录数
    private int totalCount;     // 记录总行数 ：select count（*） from 表名
    private int totalPageCount; // 总页数
    private List<T> resultList;  // 每页查询结果集

	public int getCurrPageNo() {
		return currPageNo;
	}
	//设置当前页码
	public void setCurrPageNo(int currPageNo) {
		this.currPageNo = currPageNo;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public int getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}
	public int getTotalPageCount() {
		if(totalCount==0){
			return 0;
		}
		//在获取时，自动计算好总页数，在返回给页面显示即可
		if(totalCount%pageSize==0){//没有余数时，总页数就是除数
			totalPageCount =totalCount / pageSize;
		}else{//有余数时，就+1页显示
			totalPageCount = (totalCount / pageSize)+1;
		}
		return totalPageCount;
	}
	public List<T> getResultList() {
		return resultList;
	}

	public void setResultList(List<T> resultList) {
		this.resultList = resultList;
	}
}
