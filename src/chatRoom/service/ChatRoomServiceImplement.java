// ChatRoomService接口实现
package chatRoom.service;

import java.io.IOException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import java.lang.String;

import chatRoom.command.Command;
import chatRoom.command.CommandFactory;
import chatRoom.exception.*;
import chatRoom.domain.*;

public class ChatRoomServiceImplement extends java.rmi.server.UnicastRemoteObject
		implements ChatRoomServiceInterface {
	private static final long serialVersionUID = 1L;
	private User user;
	private String roomName;
    List<String> roomcmds = new ArrayList<String>();
    List<String> usercmds = new ArrayList<String>();
	public ChatRoomServiceImplement() throws java.rmi.RemoteException {
        roomcmds.add("lsusers");
        roomcmds.add("out");
        roomcmds.add("send");

        usercmds.add("clear");
        usercmds.add("create");
        usercmds.add("delete");
        usercmds.add("help");
        usercmds.add("in");
        usercmds.add("out");
        usercmds.add("logout");
//        usercmds.add("query");
        usercmds.add("send");

        user = null;
	}

	// 执行命令
	public String[] excuteCommand(String command) throws ChatRoomException,
			RemoteException, IOException{
		System.out.println("处理用户请求：" + command);
		List param = new ArrayList();
		StringTokenizer st = new StringTokenizer(command, " ");
		String cmdName = st.nextToken().trim();
		while (st.hasMoreTokens())
			param.add(st.nextToken().trim());
		String[] re;
		Command cmd = CommandFactory.getInstance().createCommand(cmdName);
		if (cmd != null) {
			addAuthInfo(cmdName, param);
            cmd.parse(param);
            re = cmd.excute();
            //执行后调整客户端的参数值
            paramAnalyse(cmdName, param);
        } else {
            re = new String[1];
			re[0] = "没有该种命令，请检查输入是否正确！";
		}
		return re;
    }
    public void addAuthInfo(String cmd, List param) throws ChatRoomException{
        if (roomcmds.contains(cmd)) {
            if (roomName != null && !roomName.isEmpty())
                param.add(roomName);
        }
        if (usercmds.contains(cmd)) {
            if (user == null)  {
                if (!cmd.equalsIgnoreCase("help")) throw new NoLoginException();
            }
            else
                param.add(user.getUserName());
        }
    }
    public void paramAnalyse(String cmdName, List param) {
        if (user == null && cmdName.equalsIgnoreCase("login")) {
            user = new User((String)param.get(0), (String)param.get(1));
        }
        else if (user != null) {
            if (cmdName.equalsIgnoreCase("logout")) {
                user = null;
                roomName = null;
            } else if (cmdName.equalsIgnoreCase("in")) {
                roomName = (String)param.get(0);
                System.out.println("in" + roomName);
            } else if (cmdName.equalsIgnoreCase("out") ||
                    cmdName.equalsIgnoreCase("delete")) {
                roomName = null;
            }
        }
    }

    public String getUserName() throws ChatRoomException, java.rmi.RemoteException, IOException {
        if (user == null) return null;//throw new NoLoginException();
        return user.getUserName();
    }
    public String getRoomName() throws ChatRoomException, java.rmi.RemoteException, IOException {
        if (roomName == null || roomName.isEmpty()) return null;// throw new NoRoomChosenException();
        return roomName;
    }
}
