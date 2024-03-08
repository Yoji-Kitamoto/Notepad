package sql;

import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import config.DBConfig;
import object.Notepad;

public class SelectOneNotepad {
	public Notepad getOneNotepadInfo(int notepadId) throws FileNotFoundException {
		var dbInfo = new DBConfig();
		String url  = dbInfo.getDBInfo().get("url");
		String user = dbInfo.getDBInfo().get("user");
		String pass = dbInfo.getDBInfo().get("password");

		String getOneNotepadInfoSql = "SELECT * FROM notepad_table WHERE notepad_id = ?;";

		// データベースへの接続
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch(ClassNotFoundException e) {
			e.printStackTrace();
		}

		Notepad notepad = null;
		try(Connection connection = DriverManager.getConnection(url, user, pass)){
			PreparedStatement preparedStatement = connection.prepareStatement(getOneNotepadInfoSql);
			preparedStatement.setInt(1, notepadId);

			ResultSet resultSet = preparedStatement.executeQuery();

			while(resultSet.next()) {
				notepad = new Notepad(resultSet.getInt("notepad_id"), resultSet.getInt("admin_id"), resultSet.getString("title"), resultSet.getString("content"), resultSet.getDate("created_time"), resultSet.getDate("updated_time"));
			}
		} catch(SQLException e) {
			System.out.println("データベースとの接続を切断します");
			e.printStackTrace();
		}

		return notepad;
	}
}
