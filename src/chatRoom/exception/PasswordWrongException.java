package chatRoom.exception;

/**
 * @author daizecheng
 *
 */

public class PasswordWrongException extends ChatRoomException{
	private static final long serialVersionUID = 1L; 

	public PasswordWrongException(){
		super();
	}

	public void printInfo(){
		System.out.println("用户密码错误!");
	}
	
	public String getInfo(){
		return "用户密码错误!";
	}
}
