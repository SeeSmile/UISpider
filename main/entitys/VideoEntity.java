package entitys;

import com.google.gson.Gson;

public class VideoEntity {
	
	//www.miaopai
	//www.panda
	//www.zhanqi
	//m.miaopai
	//www.douyu
	//www.yixia
	//www.meipai
	//www.quanmin
	//www.meipai
	
	public String url;
	public String nickname;
	public String gender;
	public String avatar;
	public String introduce;
	
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getAvatar() {
		return avatar;
	}
	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}
	public String getIntroduce() {
		return introduce;
	}
	public void setIntroduce(String introduce) {
		this.introduce = introduce;
	}
	
	@Override
	public String toString() {
		return new Gson().toJson(this);
	}

}
