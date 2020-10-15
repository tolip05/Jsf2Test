package com.test.db.pack;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

@ManagedBean(name = "pacHelpBean")
@SessionScoped
public class PackageHelperBean {
	@ManagedProperty(value="#{midBean}")
	private MidlleBean midlleBean;
	private final static int PAGE_SIZE = 20;
	private int currentPage;
	

	public PackageHelperBean() {
		
	}
	
	
	public int getRowBegin() {
		return (currentPage - 1) * PAGE_SIZE + 1;
	}

	public int getRowEnd() {
		return getRowBegin() + PAGE_SIZE - 1;
	}
	
	public int getCurrentPage() {
		return currentPage;
	}


	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	public void goTo(int page) {
		currentPage = page;
		midlleBean.getPackageBean()
		          .loadPageData();
	}
	
	public void nextPage() {
		goTo(currentPage + 1);
	}

	public void prevPage() {
		goTo(currentPage - 1);
	}

	public void firstPage() {
		goTo(1);
	}

	public MidlleBean getMidlleBean() {
		return midlleBean;
	}


	public void setMidlleBean(MidlleBean midlleBean) {
		this.midlleBean = midlleBean;
	}
}
