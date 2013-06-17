package chatRoom.exception;
/**
 * @author daizecheng
 *
 */
public class NoLoginException extends ChatRoomException{
	private static final long serialVersionUID = 1L; 

	public NoLoginException (){
		super();
	}

	public void printInfo(){
		System.out.println("用户还未登录!");
	}

	public String getInfo(){
		return "用户还未登录!";
	}
}
