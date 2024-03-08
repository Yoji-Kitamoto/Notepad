package object;

public class Admin {
	private int id;
	private String name;
	private String password;
	private boolean isLogin;

	public Admin(int id, String name, String password, boolean isLogin) {
		this.id       = id;
		this.name     = name;
		this.password = password;
		this.isLogin  = isLogin;
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getPassword() {
		return password;
	}

	public boolean getLoginFlag() {
		return isLogin;
	}
}
