package com.informatica.www;

public class AddressParsePortProxy implements com.informatica.www.AddressParsePort {
  private String _endpoint = null;
  private com.informatica.www.AddressParsePort addressParsePort = null;
  
  public AddressParsePortProxy() {
    _initAddressParsePortProxy();
  }
  
  public AddressParsePortProxy(String endpoint) {
    _endpoint = endpoint;
    _initAddressParsePortProxy();
  }
  
  private void _initAddressParsePortProxy() {
    try {
      addressParsePort = (new com.informatica.www.AddressParseLocator()).getAddressParsePort();
      if (addressParsePort != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)addressParsePort)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)addressParsePort)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (addressParsePort != null)
      ((javax.xml.rpc.Stub)addressParsePort)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public com.informatica.www.AddressParsePort getAddressParsePort() {
    if (addressParsePort == null)
      _initAddressParsePortProxy();
    return addressParsePort;
  }
  
  public com.informatica.www.wsdl.AddressParseResponseType addressParseOperation(com.informatica.www.wsdl.AddressParseRequestType parameters) throws java.rmi.RemoteException{
    if (addressParsePort == null)
      _initAddressParsePortProxy();
    return addressParsePort.addressParseOperation(parameters);
  }
  
  
}