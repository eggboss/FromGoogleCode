/**
 * ServiceDemoLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package kirk.ws.demo;

public class ServiceDemoLocator extends org.apache.axis.client.Service implements kirk.ws.demo.ServiceDemo {

    public ServiceDemoLocator() {
    }


    public ServiceDemoLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public ServiceDemoLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for ServiceDemoHttpSoap12Endpoint
    private java.lang.String ServiceDemoHttpSoap12Endpoint_address = "http://localhost:8080/WebServiceDemo/services/ServiceDemo.ServiceDemoHttpSoap12Endpoint/";

    public java.lang.String getServiceDemoHttpSoap12EndpointAddress() {
        return ServiceDemoHttpSoap12Endpoint_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String ServiceDemoHttpSoap12EndpointWSDDServiceName = "ServiceDemoHttpSoap12Endpoint";

    public java.lang.String getServiceDemoHttpSoap12EndpointWSDDServiceName() {
        return ServiceDemoHttpSoap12EndpointWSDDServiceName;
    }

    public void setServiceDemoHttpSoap12EndpointWSDDServiceName(java.lang.String name) {
        ServiceDemoHttpSoap12EndpointWSDDServiceName = name;
    }

    public kirk.ws.demo.ServiceDemoPortType getServiceDemoHttpSoap12Endpoint() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(ServiceDemoHttpSoap12Endpoint_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getServiceDemoHttpSoap12Endpoint(endpoint);
    }

    public kirk.ws.demo.ServiceDemoPortType getServiceDemoHttpSoap12Endpoint(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            kirk.ws.demo.ServiceDemoSoap12BindingStub _stub = new kirk.ws.demo.ServiceDemoSoap12BindingStub(portAddress, this);
            _stub.setPortName(getServiceDemoHttpSoap12EndpointWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setServiceDemoHttpSoap12EndpointEndpointAddress(java.lang.String address) {
        ServiceDemoHttpSoap12Endpoint_address = address;
    }


    // Use to get a proxy class for ServiceDemoHttpSoap11Endpoint
    private java.lang.String ServiceDemoHttpSoap11Endpoint_address = "http://localhost:8080/WebServiceDemo/services/ServiceDemo.ServiceDemoHttpSoap11Endpoint/";

    public java.lang.String getServiceDemoHttpSoap11EndpointAddress() {
        return ServiceDemoHttpSoap11Endpoint_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String ServiceDemoHttpSoap11EndpointWSDDServiceName = "ServiceDemoHttpSoap11Endpoint";

    public java.lang.String getServiceDemoHttpSoap11EndpointWSDDServiceName() {
        return ServiceDemoHttpSoap11EndpointWSDDServiceName;
    }

    public void setServiceDemoHttpSoap11EndpointWSDDServiceName(java.lang.String name) {
        ServiceDemoHttpSoap11EndpointWSDDServiceName = name;
    }

    public kirk.ws.demo.ServiceDemoPortType getServiceDemoHttpSoap11Endpoint() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(ServiceDemoHttpSoap11Endpoint_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getServiceDemoHttpSoap11Endpoint(endpoint);
    }

    public kirk.ws.demo.ServiceDemoPortType getServiceDemoHttpSoap11Endpoint(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            kirk.ws.demo.ServiceDemoSoap11BindingStub _stub = new kirk.ws.demo.ServiceDemoSoap11BindingStub(portAddress, this);
            _stub.setPortName(getServiceDemoHttpSoap11EndpointWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setServiceDemoHttpSoap11EndpointEndpointAddress(java.lang.String address) {
        ServiceDemoHttpSoap11Endpoint_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     * This service has multiple ports for a given interface;
     * the proxy implementation returned may be indeterminate.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (kirk.ws.demo.ServiceDemoPortType.class.isAssignableFrom(serviceEndpointInterface)) {
                kirk.ws.demo.ServiceDemoSoap12BindingStub _stub = new kirk.ws.demo.ServiceDemoSoap12BindingStub(new java.net.URL(ServiceDemoHttpSoap12Endpoint_address), this);
                _stub.setPortName(getServiceDemoHttpSoap12EndpointWSDDServiceName());
                return _stub;
            }
            if (kirk.ws.demo.ServiceDemoPortType.class.isAssignableFrom(serviceEndpointInterface)) {
                kirk.ws.demo.ServiceDemoSoap11BindingStub _stub = new kirk.ws.demo.ServiceDemoSoap11BindingStub(new java.net.URL(ServiceDemoHttpSoap11Endpoint_address), this);
                _stub.setPortName(getServiceDemoHttpSoap11EndpointWSDDServiceName());
                return _stub;
            }
        }
        catch (java.lang.Throwable t) {
            throw new javax.xml.rpc.ServiceException(t);
        }
        throw new javax.xml.rpc.ServiceException("There is no stub implementation for the interface:  " + (serviceEndpointInterface == null ? "null" : serviceEndpointInterface.getName()));
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(javax.xml.namespace.QName portName, Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        if (portName == null) {
            return getPort(serviceEndpointInterface);
        }
        java.lang.String inputPortName = portName.getLocalPart();
        if ("ServiceDemoHttpSoap12Endpoint".equals(inputPortName)) {
            return getServiceDemoHttpSoap12Endpoint();
        }
        else if ("ServiceDemoHttpSoap11Endpoint".equals(inputPortName)) {
            return getServiceDemoHttpSoap11Endpoint();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://demo.ws.kirk", "ServiceDemo");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://demo.ws.kirk", "ServiceDemoHttpSoap12Endpoint"));
            ports.add(new javax.xml.namespace.QName("http://demo.ws.kirk", "ServiceDemoHttpSoap11Endpoint"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("ServiceDemoHttpSoap12Endpoint".equals(portName)) {
            setServiceDemoHttpSoap12EndpointEndpointAddress(address);
        }
        else 
if ("ServiceDemoHttpSoap11Endpoint".equals(portName)) {
            setServiceDemoHttpSoap11EndpointEndpointAddress(address);
        }
        else 
{ // Unknown Port Name
            throw new javax.xml.rpc.ServiceException(" Cannot set Endpoint Address for Unknown Port" + portName);
        }
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(javax.xml.namespace.QName portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        setEndpointAddress(portName.getLocalPart(), address);
    }

}
