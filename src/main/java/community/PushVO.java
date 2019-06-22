package community;

public class PushVO {
	//푸쉬 알림을 위한 VO 파라미터 String 두개 받기 귀찮아서 만듬
	
	String title;
	String body;
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getBody() {
		return body;
	}
	public void setBody(String body) {
		this.body = body;
	}
}
