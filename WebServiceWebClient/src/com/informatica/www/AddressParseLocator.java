/**
 * AddressParseLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.informatica.www;

public class AddressParseLocator extends org.apache.axis.client.Service implements com.informatica.www.AddressParse {

    public AddressParseLocator() {
    }


    public AddressParseLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public AddressParseLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for AddressParsePort
    private java.lang.String AddressParsePort_address = "http://172.16.5.191:7333/wsh/services/RealTime/AddressParse";

    public java.lang.String getAddressParsePortAddress() {
        return AddressParsePort_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String AddressParsePortWSDDServiceName = "AddressParsePort";

    public java.lang.String getAddressParsePortWSDDServiceName() {
        return AddressParsePortWSDDServiceName;
    }

    public void setAddressParsePortWSDDServiceName(java.lang.String name) {
        AddressParsePortWSDDServiceName = name;
    }

    public com.informatica.www.AddressParsePort getAddressParsePort() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(AddressParsePort_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getAddressParsePort(endpoint);
    }

    public com.informatica.www.AddressParsePort getAddressParsePort(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            com.informatica.www.AddressParseBindingStub _stub = new com.informatica.www.AddressParseBindingStub(portAddress, this);
            _stub.setPortName(getAddressParsePortWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setAddressParsePortEndpointAddress(java.lang.String address) {
        AddressParsePort_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (com.informatica.www.AddressParsePort.class.isAssignableFrom(serviceEndpointInterface)) {
                com.informatica.www.AddressParseBindingStub _stub = new com.informatica.www.AddressParseBindingStub(new java.net.URL(AddressParsePort_address), this);
                _stub.setPortName(getAddressParsePortWSDDServiceName());
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
        if ("AddressParsePort".equals(inputPortName)) {
            return getAddressParsePort();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://www.informatica.com/", "AddressParse");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://www.informatica.com/", "AddressParsePort"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("AddressParsePort".equals(portName)) {
            setAddressParsePortEndpointAddress(address);
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
