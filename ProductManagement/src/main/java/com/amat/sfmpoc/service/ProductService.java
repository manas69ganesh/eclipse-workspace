package com.amat.sfmpoc.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import org.springframework.stereotype.Service;

import com.amat.sfmpoc.beans.Product;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class ProductService {

	public String createProduct(Product product) {

		StringBuffer response = new StringBuffer();

		try {
			URL url = new URL("https://nso-cloudmes-mrs-jfa.azurewebsites.net/product-manage/create-product");
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setDoOutput(true);
			conn.setRequestMethod("POST");
			conn.setRequestProperty("Content-Type", "application/json");

			String input = new ObjectMapper().writeValueAsString(product);

			OutputStream os = conn.getOutputStream();
			os.write(input.getBytes());
			os.flush();

			if (conn.getResponseCode() != HttpURLConnection.HTTP_CREATED) {
				throw new RuntimeException("Failed : HTTP error code : " + conn.getResponseCode());
			}

			BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));

			String output;
			while ((output = br.readLine()) != null) {
				response.append(output);
			}
			conn.disconnect();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return response.toString();
	}

	// search product
	public String searchProduct(String productName) {

		StringBuffer response = new StringBuffer();

		try {
			URL url = new URL("https://nso-cloudmes-mrs-jfa.azurewebsites.net/product-manage/get-product/" + productName);

			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setDoOutput(true);
			conn.setRequestMethod("GET");
			conn.setRequestProperty("Accept", "application/json");

			/*
			 * if (conn.getResponseCode() != HttpURLConnection.HTTP_OK) { throw new
			 * RuntimeException("Failed : HTTP error code : " + conn.getResponseCode()); }
			 */
			BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));

			String output;
			while ((output = br.readLine()) != null) {
				response.append(output);
			}
			conn.disconnect();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return response.toString();
	}

}
