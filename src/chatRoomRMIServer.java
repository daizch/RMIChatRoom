import java.rmi.*;
import java.rmi.registry.*;
import java.io.*;

import chatRoom.service.*;

public class chatRoomRMIServer {

	private static final String DELEGATE_NAME = "REGISTRY_DELEGATE"; 
	private static String registryHost = null;
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try{
			//获取注册代理所在主机ip
		/*	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

			do{
				System.out.print("请输入注册代理所在的主机ip：");
				registryHost = br.readLine();
			}while((registryHost == null) || "".equals(registryHost.trim()));
		*/
			registryHost= "localhost";
			//创建伺服对象
			ServiceFactoryImplement factory = new ServiceFactoryImplement();
			//注册伺服对象
			Registry remoteRegistry = LocateRegistry.getRegistry(registryHost);
			Registry delegate = (Registry) remoteRegistry.lookup(DELEGATE_NAME);
			delegate.bind("ServiceFactory", factory);
            System.out.println("远程对象:ServiceFactory 创建并注册成功！");
        }catch(RemoteException exc){
            System.out.println("创建远程对象时发生异常！");
            exc.printStackTrace();
        }catch(AlreadyBoundException exc){
			System.out.println("发生重复绑定异常！");
		}catch(NotBoundException exc){
			System.out.println("远程注册代理未绑定，注册伺服对象失败！");
		}
        System.out.println("服务程序已准备就绪 ...");
	}

}
