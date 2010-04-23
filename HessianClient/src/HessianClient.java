import com.caucho.hessian.client.HessianProxyFactory;

public class HessianClient {

	public static void main(String[] args) throws Exception {
		// 方法一
//		ApplicationContext context = new ClassPathXmlApplicationContext("remoting-client.xml");
//		IBase hello =(IBase)context.getBean("myServiceClient");
//		System.out.println(hello.greeting("Hessian"));
		
		// 方法二
		String url = "http://127.0.0.1:8080/HessianServer/hello";
		HessianProxyFactory factory = new HessianProxyFactory();
	    IBase hello =(IBase)factory.create(IBase.class,url); 
	    System.out.println(hello.greeting("Hessian"));
	}
}
