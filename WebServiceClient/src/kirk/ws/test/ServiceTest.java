package kirk.ws.test;

import java.rmi.RemoteException;

import kirk.ws.demo.UserService;
import kirk.ws.demo.UserServiceProxy;
import kirk.ws.demo.bean.UserBean;

public class ServiceTest {
	static public void main(String[] args) throws RemoteException{
		// 針對AXIS的WSDL Path
		// http://localhost:8080/WebServiceDemo/wsdl/ServiceDemo.wsdl
//		ServiceDemoProxy proxy = new ServiceDemoProxy();
//		ServiceDemo service = proxy.getServiceDemo();
//		System.out.println(service.echo("Hello Kirk."));
		
//		HelloWorldProxy _proxy = new HelloWorldProxy();
//		HelloWorld helloWorld = _proxy.getHelloWorld();
//		System.out.println(helloWorld.echo("Hello World!"));
		
		UserService userService = new UserServiceProxy().getUserService();
		UserBean user = userService.getUser();
		System.out.println(userService.printUserInfo());
		user.setAge(33);
		userService.updateUser(user);
		System.out.println(userService.printUserInfo());
		
		// 針對AXIS2的WSDL Path
		// http://localhost:8080/WebServiceDemo/services/ServiceDemo?wsdl
//		ServiceDemoPortTypeProxy proxy = new ServiceDemoPortTypeProxy();
//		ServiceDemoPortType serviceDemoPortType = proxy.getServiceDemoPortType();
//		System.out.println(serviceDemoPortType.echo("Hello Kirk"));
	}
}
