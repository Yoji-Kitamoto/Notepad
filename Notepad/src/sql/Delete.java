package sql;

import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import config.DBConfig;

public class Delete {
	public void notepadDelete(int notepadId) throws FileNotFoundException {
		var dbInfo = new DBConfig();
		String url  = dbInfo.getDBInfo().get("url");
		String user = dbInfo.getDBInfo().get("user");
		String pass = dbInfo.getDBInfo().get("password");

		String deleteSql = "DELETE FROM notepad_table WHERE notepad_id = ?;";

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch(ClassNotFoundException e) {
			e.printStackTrace();
		}

		try(Connection connection = DriverManager.getConnection(url, user, pass)){
			connection.setAutoCommit(false);

			try(PreparedStatement preparedStatement = connection.prepareStatement(deleteSql)){
				preparedStatement.setInt(1, notepadId);

				preparedStatement.executeLargeUpdate();

				connection.commit();
				System.out.println("削除処理が成功しました");
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
