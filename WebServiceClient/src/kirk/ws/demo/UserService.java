/**
 * UserService.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package kirk.ws.demo;

public interface UserService extends java.rmi.Remote {
    public kirk.ws.demo.bean.UserBean getUser() throws java.rmi.RemoteException;
    public void updateUser(kirk.ws.demo.bean.UserBean newUser) throws java.rmi.RemoteException;
    public java.lang.String printUserInfo() throws java.rmi.RemoteException;
}
