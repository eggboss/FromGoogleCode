package kirk.ws.demo;

public class ServiceDemoPortTypeProxy implements kirk.ws.demo.ServiceDemoPortType {
  private String _endpoint = null;
  private kirk.ws.demo.ServiceDemoPortType serviceDemoPortType = null;
  
  public ServiceDemoPortTypeProxy() {
    _initServiceDemoPortTypeProxy();
  }
  
  public ServiceDemoPortTypeProxy(String endpoint) {
    _endpoint = endpoint;
    _initServiceDemoPortTypeProxy();
  }
  
  private void _initServiceDemoPortTypeProxy() {
    try {
      serviceDemoPortType = (new kirk.ws.demo.ServiceDemoLocator()).getServiceDemoHttpSoap11Endpoint();
      if (serviceDemoPortType != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)serviceDemoPortType)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)serviceDemoPortType)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (serviceDemoPortType != null)
      ((javax.xml.rpc.Stub)serviceDemoPortType)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public kirk.ws.demo.ServiceDemoPortType getServiceDemoPortType() {
    if (serviceDemoPortType == null)
      _initServiceDemoPortTypeProxy();
    return serviceDemoPortType;
  }
  
  public java.lang.String echo(java.lang.String args) throws java.rmi.RemoteException{
    if (serviceDemoPortType == null)
      _initServiceDemoPortTypeProxy();
    return serviceDemoPortType.echo(args);
  }
  
  
}