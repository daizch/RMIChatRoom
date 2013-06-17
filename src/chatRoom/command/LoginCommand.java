// 注册命令类，扩展Command接口
package chatRoom.command;

import java.util.*;
import java.io.IOException;
import chatRoom.domain.*;
import chatRoom.exception.*;

public class LoginCommand implements Command{
	// 需要注册的用户
	private User user;
	// 静态工厂实例，返回实现了工厂接口的匿名内部类实例*/
	public static CmdFactory factory=new CmdFactory(){
		public Command createCommand(){
			return new LoginCommand();
		}
	};

	// 私有构造函数，只能通过工厂获取对象实例
	private LoginCommand(){
		user=null;
	}
	
	// 对参数进行合法性检查
	public void parse(List param)throws ChatRoomException, IOException{
		if(param!=null){
			int num=param.size();
			if(num == 2){							
				user = new User((String)param.get(0),(String)param.get(1));
			}
			else throw new ParameterNumWrongException();
		}
		else throw new NoParameterException();
	}
	
	// 用户登录
	public String[] excute()throws ChatRoomException,IOException{
		String[] re = new String[1];
		UserManager um = UserManager.getInstance();
		User userTmp = um.getUser(user.getUserName());					
		if (userTmp != null) {						
			if (!um.passwordValidate(userTmp, user.getPassword())) {
				throw new PasswordWrongException();				
				}			
			}
		else throw new UserNotExistException(); 												

		re[0] = "登录成功!";
		return re;
	}
}