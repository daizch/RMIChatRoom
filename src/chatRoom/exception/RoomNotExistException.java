package chatRoom.exception;

/**
 * @author daizecheng
 *
 */

public class RoomNotExistException extends ChatRoomException{
	private static final long serialVersionUID = 1L; 

	public RoomNotExistException(){
		super();
	}

	public void printInfo(){
		System.out.println("该聊天室不存在!");
	}
	
	public String getInfo(){
		return "该聊天室不存在!";
	}
}
