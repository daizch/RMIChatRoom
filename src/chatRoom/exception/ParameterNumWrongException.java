package chatRoom.exception;

/**
 * @author daizecheng
 *
 */
public class ParameterNumWrongException extends ChatRoomException{
	private static final long serialVersionUID = 1L; 

	public ParameterNumWrongException(){
		super();
	}

	public void printInfo(){
		System.out.println("参数个数不正确，请检查输入!");
	}
	
	public String getInfo(){
		return "参数个数不正确，请检查输入!";
	}
}
