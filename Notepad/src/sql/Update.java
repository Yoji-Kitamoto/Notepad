package sql;

import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import config.DBConfig;

public class Update {
	public void notepadUpdate(String title, String content, int notepadId) throws FileNotFoundException {
		var dbInfo = new DBConfig();
		String url  = dbInfo.getDBInfo().get("url");
		String user = dbInfo.getDBInfo().get("user");
		String pass = dbInfo.getDBInfo().get("password");

		String updateSql = "UPDATE notepad_table SET title = ?, content = ? WHERE notepad_id = ?;";

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch(ClassNotFoundException e) {
			e.printStackTrace();
		}

		try(Connection connection = DriverManager.getConnection(url, user, pass)) {
			connection.setAutoCommit(false);

			try(PreparedStatement preparedStatement = connection.prepareStatement(updateSql)) {
				preparedStatement.setString(1, title);
				preparedStatement.setString(2, content);
				preparedStatement.setInt(3, notepadId);

				preparedStatement.executeUpdate();

				connection.commit();
				System.out.println("更新処理が成功しました");
			} catch(SQLException e2) {
				connection.rollback();
				System.out.println("ロールバック処理を実行しました");
				e2.printStackTrace();
			}
		} catch(SQLException e1) {
			e1.printStackTrace();
		}
	}
}
