package chatRoom.exception;

/**
 * @author daizecheng
 *
 */
public class CreatorWrongException extends ChatRoomException{
	private static final long serialVersionUID = 1L; 

	public CreatorWrongException(){
		super();
	}

	public void printInfo(){
		System.out.println("非聊天室创建者!");
	}
	
	public String getInfo(){
		return "非聊天室创建者!";
	}
}
