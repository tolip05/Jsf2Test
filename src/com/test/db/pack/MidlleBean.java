package com.test.db.pack;

import java.io.IOException;
import java.sql.SQLException;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import com.cc.db.exception.EmptyResultSetException;
import com.cc.db.exception.PojoCreationException;

@ManagedBean(name = "mBean")
@SessionScoped
public class MidlleBean {
	@ManagedProperty(value="#{pacBean}")
	private PackageBean packageBean;
	private Long idSender;
	

	public void startPckBean(Long addressId) throws PojoCreationException, EmptyResultSetException, SQLException, IOException {
		packageBean.save(addressId);
	}
	
	public void editPackageBean() throws SQLException {
		packageBean.setIdSenderFromMidlleBean(this.idSender);
		packageBean.changePackObject();
	}
	
	public PackageBean getPackageBean() {
		return packageBean;
	}

	public void setPackageBean(PackageBean packageBean) {
		this.packageBean = packageBean;
	}
	
	public Long getIdSender() {
		return idSender;
	}

	public void setIdSender(Long idSender) {
		this.idSender = idSender;
	}
}
