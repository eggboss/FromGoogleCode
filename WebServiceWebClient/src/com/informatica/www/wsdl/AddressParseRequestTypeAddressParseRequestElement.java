/**
 * AddressParseRequestTypeAddressParseRequestElement.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.informatica.www.wsdl;

public class AddressParseRequestTypeAddressParseRequestElement  implements java.io.Serializable {
    private java.lang.String IN_ADDR;

    public AddressParseRequestTypeAddressParseRequestElement() {
    }

    public AddressParseRequestTypeAddressParseRequestElement(
           java.lang.String IN_ADDR) {
           this.IN_ADDR = IN_ADDR;
    }


    /**
     * Gets the IN_ADDR value for this AddressParseRequestTypeAddressParseRequestElement.
     * 
     * @return IN_ADDR
     */
    public java.lang.String getIN_ADDR() {
        return IN_ADDR;
    }


    /**
     * Sets the IN_ADDR value for this AddressParseRequestTypeAddressParseRequestElement.
     * 
     * @param IN_ADDR
     */
    public void setIN_ADDR(java.lang.String IN_ADDR) {
        this.IN_ADDR = IN_ADDR;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof AddressParseRequestTypeAddressParseRequestElement)) return false;
        AddressParseRequestTypeAddressParseRequestElement other = (AddressParseRequestTypeAddressParseRequestElement) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.IN_ADDR==null && other.getIN_ADDR()==null) || 
             (this.IN_ADDR!=null &&
              this.IN_ADDR.equals(other.getIN_ADDR())));
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
        if (getIN_ADDR() != null) {
            _hashCode += getIN_ADDR().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(AddressParseRequestTypeAddressParseRequestElement.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.informatica.com/wsdl/", ">AddressParseRequestType>AddressParseRequestElement"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("IN_ADDR");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.informatica.com/wsdl/", "IN_ADDR"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
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
