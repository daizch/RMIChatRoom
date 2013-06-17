// 客户端主程序
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import java.rmi.registry.*;
import java.lang.Thread;

import chatRoom.exception.ChatRoomException;
import chatRoom.service.ChatRoomServiceInterface;
import chatRoom.service.ReceiveMessage;

public class chatClientThread extends Thread {
    public chatClientThread() {
        start();
    }
    public void run() {
        String serverIP = null;
        String serverPort = null;
        String cmd = null;
        List param = new ArrayList();
        try {
            System.out.println("欢迎使用议程管理系统～ \n可以使用help命令查看命令格式～");
            BufferedReader br = new BufferedReader(new InputStreamReader(
                        System.in));
            /*
               do {
               System.out.print("请输入注册服务器ip地址：");
               serverIP = br.readLine();
               } while (serverIP == null || "".equals(serverIP.trim()));
               do {
               System.out.print("请输入注册服务器服务端口：(缺省为1099)");
               serverPort = br.readLine();
               } while (serverPort == null || "".equals(serverPort.trim()));
               */
            serverIP = "localhost";
            serverPort = "1099";
            Registry registry = LocateRegistry.getRegistry(serverIP,Integer.parseInt(serverPort));
            ChatRoomServiceInterface csi = (ChatRoomServiceInterface) registry.lookup("ChatRoomService");

            ReceiveMessage gm = new ReceiveMessage();
            Registry delegate = (Registry) registry.lookup("REGISTRY_DELEGATE");
            String[] re = null;

            String userName = null;
            while (true) {
                try {
                    param.clear();
                    System.out.print("请输入命令>");
                    cmd = br.readLine();
                    if (cmd.trim().equals("quit")) {
                        System.out.println("议程管理系统客户端已终止...");
                        System.exit(1);
                    }
                    if (!"".equals(cmd.trim())) {
                        // 调用远程服务对象执行命令
                        re = csi.excuteCommand(cmd);
                        for (int i = 0; i < re.length; i++) {
                            System.out.println(re[i]);
                        }

                        try {
                            if (userName == null && csi.getUserName() != null) {
                                userName = csi.getUserName();
                                delegate.rebind(userName, gm);
                            } else if (userName != null && csi.getUserName() == null){
                                delegate.unbind(userName);
                                userName = null;
                            }
                        } catch (RemoteException exc) {
                            System.out.println("调用远程服务对象时发生异常！");
                        }
                    }
                } catch (ChatRoomException exc) {
                    exc.printInfo();
                } catch (RemoteException exc) {
                    System.out.println("调用远程服务对象时发生异常！");
                } catch (IOException exc) {
                    System.out.println("调用远程服务对象时发生IO异常！");
                }
            }
        } catch (IOException exc) {
            exc.printStackTrace();
        } catch (NotBoundException exc) {
            System.out.println("发生远程服务对象未注册异常！");
            exc.printStackTrace();
        }
    }
}
