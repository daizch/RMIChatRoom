// 注册命令类，扩展Command接口
package chatRoom.command;

import java.util.*;
import java.io.IOException;
import chatRoom.domain.*;
import chatRoom.exception.*;

public class LogoutCommand implements Command{
	// 需要退出登陆的用户
	private String userName;
	// 静态工厂实例，返回实现了工厂接口的匿名内部类实例*/
	public static CmdFactory factory=new CmdFactory(){
		public Command createCommand(){
			return new LogoutCommand();
		}
	};

	// 私有构造函数，只能通过工厂获取对象实例
	private LogoutCommand(){
	}

	// 检查数据合法性
	public void parse(List param)throws ChatRoomException, IOException{
		if (param != null) {
			userName = (String)param.get(0);
		}
		else throw new NoParameterException();
	}

	// 用户登录
	public String[] excute()throws ChatRoomException,IOException{
		String[] re = new String[1];
		RoomManager.getInstance().removeUserFromRoom(userName);
		re[0] = "退出登录成功!";
		return re;
	}
}
