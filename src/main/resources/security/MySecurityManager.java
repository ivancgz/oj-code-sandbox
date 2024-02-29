
import java.security.Permission;

/**
 * 禁用所有权限的安全管理器
 */
public class MySecurityManager extends SecurityManager{

    @Override
    public void checkExec(String cmd) {
        throw new SecurityException("checkExec 权限异常: " + cmd);
    }

}
