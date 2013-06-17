
package chatRoom.exception;

/**
 * @author daizecheng
 *
 */
public class NoParameterException extends ChatRoomException{
	private static final long serialVersionUID = 1L; 

	public NoParameterException(){
		super();
	}

	public void printInfo(){
		System.out.println("没有提供应有的参数！");
	}
	
	public String getInfo(){
		return "没有提供应有的参数！";
	}
}
