// 注册命令类，扩展Command接口
package chatRoom.command;

import java.util.*;
import java.io.IOException;
import chatRoom.domain.*;
import chatRoom.exception.*;
import chatRoom.service.*;

import java.rmi.RemoteException;
import java.rmi.*;
import java.rmi.registry.*;

public class SendCommand implements Command{
	// 发送的消息	
	private Message message;
    //发送消息的聊天室的名称
	private String roomName;
    private ReceiveMessageInterface  gm;
	// 静态工厂实例，返回实现了工厂接口的匿名内部类实例*/
	public static CmdFactory factory=new CmdFactory(){
		public Command createCommand(){
			return new SendCommand();
		}
	};
    // 私有构造函数，只能通过工厂获取对象实例
    private SendCommand(){
    }

	// 对参数进行合法性检查
	public void parse(List param)throws ChatRoomException{
		if(param!=null){
			int num=param.size();
			if (num >= 3) {
				String msg = "\n";
                for (int i = 0; i < num-2; i++)
                    msg += (String)param.get(i) + " ";
				roomName = (String)param.get(num-2);
				String userName = (String)param.get(num-1);	
				message = new Message(userName, msg);
			}
			else throw new ParameterNumWrongException();
		}
		else throw new NoParameterException();
    }

    // 查询聊天室记录
    public String[] excute()throws ChatRoomException{
        String[] re = new String[1];
        RoomManager.getInstance().putMessageToRoom(roomName, message);
        publishAllUser();
        re[0] = "成功发送消息.";
        return re;
    }

    //发送消息给聊天室所有用户
    public void publishAllUser() throws ChatRoomException {
        Room room = RoomManager.getInstance().getRoom(roomName);
        List<String> userList = room.getUserList();
        for (int i = 0; i < userList.size(); i++)
            sendMessageToUser((String)userList.get(i));
    }

    public void sendMessageToUser(String userName) throws ChatRoomException {
        String serverIP = "localhost",
        serverPort = "1099",
        msg = message.toString();
        try {
            Registry registry = LocateRegistry.getRegistry(serverIP,Integer.parseInt(serverPort));
            gm = (ReceiveMessageInterface) registry.lookup(userName);
            gm.sendMessage(msg);
        }catch (RemoteException e) {
            System.out.println("创建远程对象时发生异常");
        } catch(NotBoundException exc){
            System.out.println("远程注册代理未绑定，注册伺服对象失败！");
        } catch(IOException e){
            System.out.println("发送消息失败！");
        }
    }
}
