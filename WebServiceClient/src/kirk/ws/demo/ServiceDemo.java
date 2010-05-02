/**
 * ServiceDemo.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package kirk.ws.demo;

public interface ServiceDemo extends javax.xml.rpc.Service {
    public java.lang.String getServiceDemoHttpSoap12EndpointAddress();

    public kirk.ws.demo.ServiceDemoPortType getServiceDemoHttpSoap12Endpoint() throws javax.xml.rpc.ServiceException;

    public kirk.ws.demo.ServiceDemoPortType getServiceDemoHttpSoap12Endpoint(java.net.URL portAddress) throws javax.xml.rpc.ServiceException;
    public java.lang.String getServiceDemoHttpSoap11EndpointAddress();

    public kirk.ws.demo.ServiceDemoPortType getServiceDemoHttpSoap11Endpoint() throws javax.xml.rpc.ServiceException;

    public kirk.ws.demo.ServiceDemoPortType getServiceDemoHttpSoap11Endpoint(java.net.URL portAddress) throws javax.xml.rpc.ServiceException;
}
