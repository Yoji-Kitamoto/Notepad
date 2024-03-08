package sql;

import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import config.DBConfig;

public class Create {
	public void NotepadCreate(int adminId, String title, String content) throws FileNotFoundException {
		// データベースへの接続情報を取得
		var dbInfo = new DBConfig();
		String url  = dbInfo.getDBInfo().get("url");
		String user = dbInfo.getDBInfo().get("user");
		String pass = dbInfo.getDBInfo().get("password");

		// 実行 SQL
		var createSql = "INSERT INTO notepad_table (admin_id, title, content) VALUES(?, ?, ?)";

		// データベースへの接続
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch(ClassNotFoundException e) {
			e.printStackTrace();
		}

		try(Connection connection = DriverManager.getConnection(url, user, pass)){
			// オートコミット機能を無効化
			connection.setAutoCommit(false);

			try(PreparedStatement preparedStatement = connection.prepareStatement(createSql)) {
				preparedStatement.setInt(1, adminId);
				preparedStatement.setString(2, title);
				preparedStatement.setString(3, content);

				preparedStatement.executeUpdate();

				// コミット
				connection.commit();
				System.out.println("コミット処理を実行しました");
			} catch(SQLException e2) {
				connection.rollback();
				System.out.println("ロールバック処理を実行しました");
				e2.printStackTrace();
			}
		}catch(SQLException e1){
			e1.printStackTrace();
		}
	}
}
