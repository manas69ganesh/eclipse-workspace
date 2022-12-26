package com.amat.sfmpoc.util;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionUtil {

	public static Connection getConnection(String connString) {

		String[] connStringArray = connString.split(";");
		String user = "";
		String password = "";

		for (String conString : connStringArray) {

			if (!conString.isEmpty()) {

				String[] oneKeyValue = conString.split("=");

				if (oneKeyValue[0].equals("user")) {
					user = oneKeyValue[1];
				}

				if (oneKeyValue[0].equals("password")) {
					password = oneKeyValue[1];
				}

			}
		}

		Connection conn = null;

		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			conn = DriverManager.getConnection(connString, user, password);

			if (conn == null) {
				System.out.println("Connection is null");
			}
		} catch (Throwable th) {
			th.printStackTrace();
		}

		return conn;
	}
}