package chatRoom.exception;

/**
 * @author daizecheng
 *
 */

public class RoomHasExistedException extends ChatRoomException{
	private static final long serialVersionUID = 1L; 

	public RoomHasExistedException(){
		super();
	}

	public void printInfo(){
		System.out.println("该聊天室已存在!");
	}
	
	public String getInfo(){
		return "该聊天室已存在!";
	}
}
