/**
 * AddressParseRequestType.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.informatica.www.wsdl;

public class AddressParseRequestType  implements java.io.Serializable {
    private com.informatica.www.wsdl.AddressParseRequestTypeAddressParseRequestElement addressParseRequestElement;

    public AddressParseRequestType() {
    }

    public AddressParseRequestType(
           com.informatica.www.wsdl.AddressParseRequestTypeAddressParseRequestElement addressParseRequestElement) {
           this.addressParseRequestElement = addressParseRequestElement;
    }


    /**
     * Gets the addressParseRequestElement value for this AddressParseRequestType.
     * 
     * @return addressParseRequestElement
     */
    public com.informatica.www.wsdl.AddressParseRequestTypeAddressParseRequestElement getAddressParseRequestElement() {
        return addressParseRequestElement;
    }


    /**
     * Sets the addressParseRequestElement value for this AddressParseRequestType.
     * 
     * @param addressParseRequestElement
     */
    public void setAddressParseRequestElement(com.informatica.www.wsdl.AddressParseRequestTypeAddressParseRequestElement addressParseRequestElement) {
        this.addressParseRequestElement = addressParseRequestElement;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof AddressParseRequestType)) return false;
        AddressParseRequestType other = (AddressParseRequestType) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.addressParseRequestElement==null && other.getAddressParseRequestElement()==null) || 
             (this.addressParseRequestElement!=null &&
              this.addressParseRequestElement.equals(other.getAddressParseRequestElement())));
        __equalsCalc = null;
        return _equals;
    }

    private boolean __hashCodeCalc = false;
    public synchronized int hashCode() {
        if (__hashCodeCalc) {
            return 0;
        }
        __hashCodeCalc = true;
        int _hashCode = 1;
        if (getAddressParseRequestElement() != null) {
            _hashCode += getAddressParseRequestElement().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(AddressParseRequestType.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.informatica.com/wsdl/", "AddressParseRequestType"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("addressParseRequestElement");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.informatica.com/wsdl/", "AddressParseRequestElement"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.informatica.com/wsdl/", ">AddressParseRequestType>AddressParseRequestElement"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
    }

    /**
     * Return type metadata object
     */
    public static org.apache.axis.description.TypeDesc getTypeDesc() {
        return typeDesc;
    }

    /**
     * Get Custom Serializer
     */
    public static org.apache.axis.encoding.Serializer getSerializer(
           java.lang.String mechType, 
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType) {
        return 
          new  org.apache.axis.encoding.ser.BeanSerializer(
            _javaType, _xmlType, typeDesc);
    }

    /**
     * Get Custom Deserializer
     */
    public static org.apache.axis.encoding.Deserializer getDeserializer(
           java.lang.String mechType, 
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType) {
        return 
          new  org.apache.axis.encoding.ser.BeanDeserializer(
            _javaType, _xmlType, typeDesc);
    }

}
