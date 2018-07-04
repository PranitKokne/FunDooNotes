package rabbitmq;

public class CustomMessage {
	
	private int id;
	private String message;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
	public CustomMessage(int id, String message) {
		super();
		this.id = id;
		this.message = message;
	}
	
	@Override
	public String toString() {
		return "CustomMessage [id=" + id + ", message=" + message + "]";
	}
	
	
}
