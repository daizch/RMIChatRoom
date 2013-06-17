// 注册命令类，扩展Command接口
package chatRoom.command;

import java.util.*;
import java.io.IOException;
import chatRoom.domain.*;
import chatRoom.exception.*;

public class QueryCommand implements Command{
	// 要查询聊天记录的聊天室
	private Room room;
	// 要查询聊天记录的聊天室的名称
	private String roomName;
	// 要查询聊天记录的聊天室的密码
	private String password;

	// 静态工厂实例，返回实现了工厂接口的匿名内部类实例*/
	public static CmdFactory factory=new CmdFactory(){
		public Command createCommand(){
			return new QueryCommand();
		}
	};

	// 私有构造函数，只能通过工厂获取对象实例
	private QueryCommand(){	
	}

	// 对参数进行合法性检查
	public void parse(List param)throws ChatRoomException{
		if(param!=null){
			int num=param.size();
			if(num == 1){
				roomName = (String)param.get(0);
			}
			if(num == 2){
				roomName = (String)param.get(0);
				password = (String)param.get(1);
			}
			else throw new ParameterNumWrongException();
			room = RoomManager.getInstance().getRoom(roomName);
			if (room == null)
				throw new RoomNotExistException();
            if (!RoomManager.getInstance().passwordValidate(room, password))
                throw new RoomPasswordWorngException();
		}
		else throw new NoParameterException();
	}

	// 查询聊天室记录
	public String[] excute()throws ChatRoomException{
		List messages = room.getMessages();
        if (messages.size() > 0) {
            String[] re = new String[messages.size() + 1];
            int i;
            for (i = 0; i < messages.size(); i++)
                re[i] = ((Message)messages.get(i)).toString();
            re[i] = "查询聊天记录结束。";
            return re;
        } else {
            String[] re = new String[2];
            re[0] = "no record";
            re[1] = "查询聊天记录结束。";
            return re;
        }
	}
}
