package kirk.ws.demo;

public class UserServiceProxy implements kirk.ws.demo.UserService {
  private String _endpoint = null;
  private kirk.ws.demo.UserService userService = null;
  
  public UserServiceProxy() {
    _initUserServiceProxy();
  }
  
  public UserServiceProxy(String endpoint) {
    _endpoint = endpoint;
    _initUserServiceProxy();
  }
  
  private void _initUserServiceProxy() {
    try {
      userService = (new kirk.ws.demo.UserServiceServiceLocator()).getUserService();
      if (userService != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)userService)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)userService)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (userService != null)
      ((javax.xml.rpc.Stub)userService)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public kirk.ws.demo.UserService getUserService() {
    if (userService == null)
      _initUserServiceProxy();
    return userService;
  }
  
  public kirk.ws.demo.bean.UserBean getUser() throws java.rmi.RemoteException{
    if (userService == null)
      _initUserServiceProxy();
    return userService.getUser();
  }
  
  public void updateUser(kirk.ws.demo.bean.UserBean newUser) throws java.rmi.RemoteException{
    if (userService == null)
      _initUserServiceProxy();
    userService.updateUser(newUser);
  }
  
  public java.lang.String printUserInfo() throws java.rmi.RemoteException{
    if (userService == null)
      _initUserServiceProxy();
    return userService.printUserInfo();
  }
  
  
}