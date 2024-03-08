package sql;

import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import config.DBConfig;
import object.Admin;
import object.Notepad;

public class Login {
	private String SQLExceptionMessage = "データベースとの接続を切断します";

	public Admin check(String adminID, String password) throws FileNotFoundException {
		// データベースへの接続情報をプロパティファイルから取得
		var dbInfo = new DBConfig();
		String url  = dbInfo.getDBInfo().get("url");
		String user = dbInfo.getDBInfo().get("user");
		String pass = dbInfo.getDBInfo().get("password");

		// 実行 SQL
		String loginSql = "SELECT * FROM admin_table WHERE admin_id = ? AND password = ?;";

		// データベースへの接続
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch(ClassNotFoundException e) {
			e.printStackTrace();
		}

		Admin admin = null;
		try(Connection connection = DriverManager.getConnection(url, user, pass)) {
			var preparedStatement = connection.prepareStatement(loginSql);

			// SQL 文の "?" に値を設定
			preparedStatement.setString(1, adminID);
			preparedStatement.setString(2, password);

			// SQL 文の実行及び結果の取得
			ResultSet resultSet = preparedStatement.executeQuery();

			if(resultSet.next()) {
				admin = new Admin(resultSet.getInt("admin_id"), resultSet.getString("name"), resultSet.getString("password"), true);
			} else {
				admin = new Admin(0, "", "", false);
			}
		} catch(SQLException e) {
			System.out.println(SQLExceptionMessage);
			e.printStackTrace();
		}

		return admin;
	}

	public List<Notepad> getNotepadInfo(String adminId) throws FileNotFoundException {
		// データベースの接続情報を取得
		var dbInfo = new DBConfig();
		String url  = dbInfo.getDBInfo().get("url");
		String user = dbInfo.getDBInfo().get("user");
		String pass = dbInfo.getDBInfo().get("password");

		// 実行 SQL 文
		String notepadSql = "SELECT * FROM notepad_table WHERE admin_id = ?;";


		// データベースへの接続
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch(ClassNotFoundException e) {
			e.printStackTrace();
		}

		List<Notepad> notepadList = new ArrayList<>();
		try(Connection connection = DriverManager.getConnection(url, user, pass)) {
			PreparedStatement preparedStatement = connection.prepareStatement(notepadSql);
			preparedStatement.setString(1, adminId);

			ResultSet resultSet = preparedStatement.executeQuery();

			Notepad notepadInfo = null;
			while(resultSet.next()) {
				notepadInfo = new Notepad(resultSet.getInt("notepad_id"), resultSet.getInt("admin_id"), resultSet.getString("title"), resultSet.getNString("content"), resultSet.getDate("created_time"), resultSet.getDate("updated_time"));

				notepadList.add(notepadInfo);
			}
		} catch(SQLException e) {
			System.out.println(SQLExceptionMessage);
			e.printStackTrace();
		}

		return notepadList;
	}
}
