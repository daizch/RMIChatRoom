package chatRoom.service;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import java.lang.String;
import java.rmi.*;
import java.rmi.registry.*;
import java.io.*;

public class ServiceFactoryImplement extends java.rmi.server.UnicastRemoteObject
		implements ServiceFactoryInterface {
	private static final long serialVersionUID = 1L;
    private static int serviceId = 0;
	private static final String DELEGATE_NAME = "REGISTRY_DELEGATE"; 
	private static String registryHost = null;
    private static Registry remoteRegistry = null;
    private static Registry delegate = null;
    private static final int max_value = (1 << 31) - 1;
	public ServiceFactoryImplement() throws java.rmi.RemoteException {
        registryHost= "localhost";
        try{
            remoteRegistry = LocateRegistry.getRegistry(registryHost);
            delegate = (Registry) remoteRegistry.lookup(DELEGATE_NAME);
		}catch(RemoteException exc){
			System.out.println("创建远程对象时发生异常！");
			exc.printStackTrace();
		}catch(NotBoundException exc){
			System.out.println("远程注册代理未绑定，注册伺服对象失败！");
		}
    }

    public String getChatServiceName()
        throws java.rmi.RemoteException, IOException {
        if (serviceId >= max_value)
            resetServiceId();
        serviceId += 1;
        String serviceName = Integer.toString(serviceId);
        //创建伺服对象
        ChatRoomServiceImplement csi = new ChatRoomServiceImplement();
        //注册伺服对象
        delegate.rebind(serviceName, csi);
        //System.out.println("远程对象:ChatRoomService 创建并注册成功！");
        return serviceName;
    }

    private void resetServiceId() {
        serviceId = 0;
    }
}
