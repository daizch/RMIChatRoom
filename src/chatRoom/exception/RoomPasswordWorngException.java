package chatRoom.exception;
/**
 * @author daizecheng
 *
 */
public class RoomPasswordWorngException extends ChatRoomException{
	private static final long serialVersionUID = 1L; 

	public RoomPasswordWorngException(){
		super();
	}

	public void printInfo(){
		System.out.println("聊天室密码错误!");
	}
	
	public String getInfo(){
		return "聊天室密码错误!";
	}
}
