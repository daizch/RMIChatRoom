package chatRoom.exception;
/**
 * @author daizecheng
 *
 */
public class NoRoomChosenException extends ChatRoomException{
	private static final long serialVersionUID = 1L;

	public NoRoomChosenException(){
		super();
	}

	public void printInfo(){
		System.out.println("还没进入任何聊天室!");
	}

	public String getInfo(){
		return "还没进入任何聊天室!";
	}
}
