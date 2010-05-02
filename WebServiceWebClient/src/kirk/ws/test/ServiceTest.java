package kirk.ws.test;

import java.rmi.RemoteException;

import kirk.ws.demo.ServiceDemoStub;

public class ServiceTest {
	public static void main(String[] args) throws RemoteException{
		
		// AXIS2 client
		// http://localhost:8080/WebServiceDemo/services/ServiceDemo?wsdl
		ServiceDemoStub stub = new ServiceDemoStub();
		ServiceDemoStub.Echo request = new ServiceDemoStub.Echo();
		request.setArgs("Hello Kirk");
		ServiceDemoStub.EchoResponse response = stub.echo(request);
		System.out.println(response.get_return());
		
		// http://localhost:8080/WebServiceDemo/wsdl/ServiceDemo.wsdl
		// 用AXIS的URL用AXIS2的CLIENT不行！
//		ServiceDemoServiceStub stub = new ServiceDemoServiceStub();
//		ServiceDemoServiceStub.Echo request = new ServiceDemoServiceStub.Echo();
//		request.setArgs("Hello Kirk.");
//		ServiceDemoServiceStub.EchoResponse echoResponse = stub.echo(request);
//		System.out.println(echoResponse.getEchoReturn());
		
		
		
//		AddressParseStub stub = new AddressParseStub();
//		AddressParseStub.AddressParseRequest request = new AddressParseStub.AddressParseRequest();
//		AddressParseStub.AddressParseRequestType requestType = new AddressParseStub.AddressParseRequestType();
//		AddressParseStub.AddressParseRequestElement_type0 type = new AddressParseStub.AddressParseRequestElement_type0();
//		AddressParseStub.IN_ADDR_type1 inAddType1 = new AddressParseStub.IN_ADDR_type1();
//		inAddType1.setIN_ADDR_type0("台北縣");
//		type.setIN_ADDR(inAddType1);
//		requestType.setAddressParseRequestElement(type);
//		request.setAddressParseRequest(requestType);
//		AddressParseStub.AddressParseResponse response = stub.addressParseOperation(request);
//		System.out.println(response.getAddressParseResponse().getAddressParseResponseElement().getOUT_ADDR().getOUT_ADDR_type0());
		
		
		
		
		// AXIS
		// http://localhost:8080/WebServiceDemo/wsdl/ServiceDemo.wsdl
//		ServiceDemoProxy proxy = new ServiceDemoProxy();
//		ServiceDemo service = proxy.getServiceDemo();
//		System.out.println(service.echo("Hello Kirk."));
		
		
		
//		AddressParsePortProxy proxy = new AddressParsePortProxy();
//		AddressParsePort addressParsePort = proxy.getAddressParsePort();
//		
//		AddressParseRequestTypeAddressParseRequestElement addressParseRequestTypeAddressParseRequestElement = new AddressParseRequestTypeAddressParseRequestElement();
//		addressParseRequestTypeAddressParseRequestElement.setIN_ADDR("台北縣");
//		
//		AddressParseRequestType addressParseRequestType = new AddressParseRequestType();
//		addressParseRequestType.setAddressParseRequestElement(addressParseRequestTypeAddressParseRequestElement);
//		
//		AddressParseResponseType addressParseResponseType = addressParsePort.addressParseOperation(addressParseRequestType);
//		AddressParseResponseTypeAddressParseResponseElement addressParseResponseTypeAddressParseResponseElement = addressParseResponseType.getAddressParseResponseElement();
//		
//		System.out.println(addressParseResponseTypeAddressParseResponseElement.getOUT_ADDR());
	}
}
