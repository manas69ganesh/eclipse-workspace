package com.amat.sfmpoc.beans;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

public class Product implements Serializable {

	private static final long serialVersionUID = -6896055703509177300L;

	@JsonIgnoreProperties("productName")
	private String productName;
	@JsonIgnoreProperties("productType")
	private String productType;
	@JsonIgnoreProperties("productVersion")
	private String productVersion;
	@JsonIgnoreProperties("productDescription")
	private String productDescription;

	public Product() {
		super();
	}

	public Product(String productName, String productType, String productVersion, String productDescription) {
		super();
		this.productName = productName;
		this.productType = productType;
		this.productVersion = productVersion;
		this.productDescription = productDescription;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getProductType() {
		return productType;
	}

	public void setProductType(String productType) {
		this.productType = productType;
	}

	public String getProductVersion() {
		return productVersion;
	}

	public void setProductVersion(String productVersion) {
		this.productVersion = productVersion;
	}

	public String getProductDescription() {
		return productDescription;
	}

	public void setProductDescription(String productDescription) {
		this.productDescription = productDescription;
	}

	@Override
	public String toString() {
		return "Product [productName=" + productName + ", productType=" + productType + ", productVersion="
				+ productVersion + ", productDescription=" + productDescription + "]";
	}

}
