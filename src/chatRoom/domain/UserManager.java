package chatRoom.domain;

import java.util.*;
import java.text.*;

import chatRoom.exception.*;


/**用户管理类
 *
 */
public class UserManager {
	/*用户列表*/
    private static HashMap<String,User> userList = new HashMap<String,User>();    
    /*管理类实例化*/
    private static UserManager userManager = new UserManager();
    /**
     * 构造函数   
     */
    public UserManager()
    {

    }
   // 静态方法，返回管理类的唯一实例的引用
    public static UserManager getInstance(){        
        return userManager;
    }

    /*
    * @param userName 用户名称
    *返回用户对象
    */
    public static User getUser(String userName) {
        if (userList.containsKey(userName))
            return userList.get(userName);
        else 
            return null;
    }
    
    /*
    * @param password 用户账号密码
    * 如果验证成功返回true，否则返回false
    */
    public static Boolean passwordValidate(User user, String password) {        
        return password.equals(user.getPassword());
    }    

    /*
    * @param void
    * 添加用户
    */
    public static void userRegister(User user) {        
        userList.put(user.getUserName(), user);
    }    
}
