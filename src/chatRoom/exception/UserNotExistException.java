package chatRoom.exception;
/**
 * @author daizecheng
 *
 */
public class UserNotExistException extends ChatRoomException{
	private static final long serialVersionUID = 1L; 

	public UserNotExistException (){
		super();
	}

	public void printInfo(){
		System.out.println("用户不存在!");
	}
	
	public String getInfo(){
		return "用户不存在!";
	}
}
