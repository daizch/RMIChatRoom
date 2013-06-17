// 注册命令类，扩展Command接口
package chatRoom.command;

import java.util.*;
import java.io.IOException;
import chatRoom.domain.*;
import chatRoom.exception.*;

public class InRoomCommand implements Command{
	// 要进入的聊天室名称
	private String roomName;
	// 要进入的聊天室密码
	private String password;
	//  要进入的聊天室的用户名
	private String userName;
	// 静态工厂实例，返回实现了工厂接口的匿名内部类实例*/
	public static CmdFactory factory=new CmdFactory(){
		public Command createCommand(){
			return new InRoomCommand();
		}
	};

	// 私有构造函数，只能通过工厂获取对象实例
	private InRoomCommand(){
	}

	// 对参数进行合法性检查
	public void parse(List param)throws ChatRoomException{
		if(param!=null){
			int num=param.size();
            password = null;
			if(num == 2){
				roomName = (String)param.get(0);
				userName = (String)param.get(1);
			} else if (num == 3) {
				roomName = (String)param.get(0);
				password = (String)param.get(1);
				userName = (String)param.get(2);
			}
			else throw new ParameterNumWrongException();
			Room room = RoomManager.getInstance().getRoom(roomName);
			if (room == null)
				throw new RoomNotExistException();
            if (!RoomManager.getInstance().passwordValidate(room, password))
                throw new RoomPasswordWorngException();
		}
		else throw new NoParameterException();
	}

	// 加入聊天室
	public String[] excute()throws ChatRoomException{
		String[] re = new String[1];
		RoomManager.getInstance().joinRoom(userName, roomName);	
		re[0] = "加入聊天室成功。";
		return re;
	}
}
