package com.amat.sfmpoc.service;

import java.sql.Connection;
import java.util.List;

import com.amat.sfmpoc.beans.GeneralResponseObject;
import com.amat.sfmpoc.beans.Product;
import com.amat.sfmpoc.repository.ProductRepository;
import com.amat.sfmpoc.util.ConnectionUtil;

public class ProductService {

	ProductRepository productRepository = new ProductRepository();

	public GeneralResponseObject createProduct(String connString, Product product) {

		GeneralResponseObject response = new GeneralResponseObject();

		try {
			Connection connection = ConnectionUtil.getConnection(connString);

			boolean result = productRepository.createProduct(connection, product);

			List<Product> products = productRepository.getAllProducts(connection);

			if (result) {
				response.setResult(true);
				response.setMessageCode(200);
				response.setMessage("SUCCESS");
				response.setData(products);
			} else {
				response.setResult(false);
				response.setMessageCode(404);
				response.setMessage("Unable to create product at this moment.");
				response.setData(null);
			}
		} catch (Throwable th) {
			response.setResult(false);
			response.setMessageCode(0);
			response.setMessage("ERROR");
			response.setData(null);
		}
		return response;
	}

	public GeneralResponseObject getProduct(String connString, String productName) {

		GeneralResponseObject result = new GeneralResponseObject();

		try {
			Connection connection = ConnectionUtil.getConnection(connString);

			Product product = productRepository.getProduct(connection, productName);

			if (product == null) {
				result.setResult(false);
				result.setMessageCode(404);
				result.setMessage("No such product exists.");
				result.setData(null);
			} else {
				result.setResult(true);
				result.setMessageCode(201);
				result.setMessage("SUCCESS");
				result.setData(product);
			}
		} catch (Throwable th) {
			result.setResult(false);
			result.setMessageCode(0);
			result.setMessage("");
			result.setData(null);
		}
		return result;
	}

	public GeneralResponseObject getAllProducts(String connString) {

		GeneralResponseObject result = new GeneralResponseObject();

		try {
			Connection connection = ConnectionUtil.getConnection(connString);

			List<Product> products = productRepository.getAllProducts(connection);

			if (products.isEmpty()) {
				result.setResult(false);
				result.setMessageCode(404);
				result.setMessage("No such product exists.");
				result.setData(null);
			} else {
				result.setResult(true);
				result.setMessageCode(200);
				result.setMessage("SUCCESS");
				result.setData(products);
			}
		} catch (Throwable th) {
			result.setResult(false);
			result.setMessageCode(0);
			result.setMessage("ERROR");
			result.setData(null);
		}
		return result;
	}

}
