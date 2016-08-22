package system;

public class BaseAccount {
	
	public int mId;
	public String account;
	public String password;
	
	public BaseAccount(int id) {
		this.mId = id;
	}

	public int getmId() {
		return mId;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
}
