package com.amat.sfmpoc.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.amat.sfmpoc.beans.Product;

public class ProductRepository {

	private String CREATE_PRODUCT_QUERY = "insert into dbo.product(Product_Name,Product_Type,Product_Version,Product_Desc) values(?,?,?,?)";

	public boolean createProduct(Connection connection, Product product) {

		try {
			PreparedStatement preparedStatement = connection.prepareStatement(CREATE_PRODUCT_QUERY);
			preparedStatement.setString(1, product.getProductName());
			preparedStatement.setString(2, product.getProductType());
			preparedStatement.setString(3, product.getProductVersion());
			preparedStatement.setString(4, product.getProductDescription());

			int row = preparedStatement.executeUpdate();

			if (row == 0) {
				return false;
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			return false;
		}
		return true;
	}

	private String GET_PRODUCT_DETAILS = "SELECT * FROM dbo.product where Product_Name = ?";

	public Product getProduct(Connection connection, String productName) {

		Product product = null;

		try {
			PreparedStatement preparedStatement = connection.prepareStatement(GET_PRODUCT_DETAILS);
			preparedStatement.setString(1, productName);

			ResultSet result = preparedStatement.executeQuery();

			while (result.next()) {
				product = new Product();
				product.setProductName(result.getString("Product_Name"));
				product.setProductType(result.getString("Product_Type"));
				product.setProductVersion(result.getString("Product_Version"));
				product.setProductDescription(result.getString("Product_Desc"));
			}

		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return product;
	}

	private String GET_ALL_PRODUCT_DETAILS = "SELECT * FROM dbo.product";

	public List<Product> getAllProducts(Connection connection) {

		List<Product> productList = new ArrayList<>();

		try {
			PreparedStatement preparedStatement = connection.prepareStatement(GET_ALL_PRODUCT_DETAILS);

			ResultSet result = preparedStatement.executeQuery();

			while (result.next()) {
				Product product = new Product();
				product.setProductName(result.getString("Product_Name"));
				product.setProductType(result.getString("Product_Type"));
				product.setProductVersion(result.getString("Product_Version"));
				product.setProductDescription(result.getString("Product_Desc"));
				productList.add(product);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return productList;
	}

}
