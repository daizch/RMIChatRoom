package chatRoom.domain;
import java.util.List;
import java.util.ArrayList;
/**聊天室类
 *
 */
public class Room {
	/**聊天室创建者*/
	private String creator;	
	/**房间名称*/
	private String roomName;
	/*是否设置密码*/	
	private Boolean isSetPassword;
	/*房间密码*/
	private String password;
	/*参加聊天室的用户列表*/
	private List<String> users = new ArrayList<String>();	
	/*消息列表*/
	private List<Message> messages = new ArrayList<Message>();

	/**
	 * 构造函数
	 * @param creator 聊天室创建者
	 * @param roomName 房间名称	
	 */
	public Room(String creator, String roomName){
		this.creator = creator;
		this.roomName = roomName;		
		this.isSetPassword = false;		
		this.users.add(creator);
	}
	/**
	 * 构造函数
	 * @param creator 聊天室创建者
	 * @param roomName 房间名称
	 * @param password 房间密码	 
	 */
	public Room(String creator, String roomName, String password){
		this.creator = creator;
		this.roomName = roomName;		
		this.password = password;
		this.isSetPassword = true;		
		this.users.add(creator);
	}
	/**
	 * 序列化函数
	 */
	public String toString(){
		return "聊天室创建者:" + this.creator + " " + "聊天室名称:" + this.roomName;
	}
	
	/**
	 * @return roomName
	 */
	public String getRoomName() {
		return roomName;
	}

	/**
	 * @return creator
	 */
	public String getCreator() {
		return creator;
	}

	/**
	 * @return password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @return password是否设置，是的返回true
	 */
	public Boolean getIsSetPassword() {
		return isSetPassword;
	}
		
	/**
	 * @return 该聊天室的用户列表
	 */
	public List<String> getUserList() {
		return this.users;
	}

	/**
	 * @return 该聊天室的聊天记录
	 */
	public List<Message> getMessages() {
		return this.messages;
	}

	/**
	 * @param roomName
	 */
	public void roomName(String roomName) {
		this.roomName = roomName;
	}

	/**
	 * @param creator 要设置的 creator
	 */
	public void setCreator(String creator) {
		this.creator = creator;
	}

	/**
	 * @param password 要设置的 password
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	 /**
	 * @param username 参加聊天室的用户名
	 */
	public void addUser(String userName) {
		if (!this.users.contains(userName))
			this.users.add(userName);
	}

	 /**
	 * @param username 要从聊天室删除的用户名
	 */
	public void removeUser(String userName) {
		if (!this.users.contains(userName))
			this.users.remove(userName);
	}

	 /**
	 * @param Message 发表的消息
	 */
	public void addMessage(Message message) {		
		this.messages.add(message);
	}
	
	 /**
	 * @param void
	 * 函数功能：清空聊天记录
	 */
	public void clearMessages() {		
		this.messages.clear();
	}
}
