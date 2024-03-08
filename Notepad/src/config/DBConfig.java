package config;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class DBConfig {
	// データベースの接続情報を取得
	public Map<String, String> getDBInfo() throws FileNotFoundException {
		// プロパティファイルのフルパスを指定
		var dbPropertiesFile = "C:\\Users\\Owner\\Desktop\\pleiades\\workspace\\Notepad\\DBConfig.properties";

		var dbInfo       = new Properties();
		var dbFileStream = new FileInputStream(dbPropertiesFile);

		try {
			// プロパティファイルの読み込み
			dbInfo.load(dbFileStream);
		} catch (IOException e) {
			System.out.println("データベース設定ファイルが認識できませんでした");
			e.printStackTrace();
		}

		// DBConfig.properties のキーから値を取得
		String dbUrl  = dbInfo.getProperty("url");
		String dbUser = dbInfo.getProperty("user");
		String dbPass = dbInfo.getProperty("password");

		Map<String, String> getDBInfoForMap = new HashMap<>();
		getDBInfoForMap.put("url", dbUrl);
		getDBInfoForMap.put("user", dbUser);
		getDBInfoForMap.put("password", dbPass);

		return getDBInfoForMap;
	}
}
