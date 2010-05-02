/**
 * AddressParseResponseTypeAddressParseResponseElement.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.informatica.www.wsdl;

public class AddressParseResponseTypeAddressParseResponseElement  implements java.io.Serializable {
    private java.lang.String OUT_ADDR;

    public AddressParseResponseTypeAddressParseResponseElement() {
    }

    public AddressParseResponseTypeAddressParseResponseElement(
           java.lang.String OUT_ADDR) {
           this.OUT_ADDR = OUT_ADDR;
    }


    /**
     * Gets the OUT_ADDR value for this AddressParseResponseTypeAddressParseResponseElement.
     * 
     * @return OUT_ADDR
     */
    public java.lang.String getOUT_ADDR() {
        return OUT_ADDR;
    }


    /**
     * Sets the OUT_ADDR value for this AddressParseResponseTypeAddressParseResponseElement.
     * 
     * @param OUT_ADDR
     */
    public void setOUT_ADDR(java.lang.String OUT_ADDR) {
        this.OUT_ADDR = OUT_ADDR;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof AddressParseResponseTypeAddressParseResponseElement)) return false;
        AddressParseResponseTypeAddressParseResponseElement other = (AddressParseResponseTypeAddressParseResponseElement) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.OUT_ADDR==null && other.getOUT_ADDR()==null) || 
             (this.OUT_ADDR!=null &&
              this.OUT_ADDR.equals(other.getOUT_ADDR())));
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
        if (getOUT_ADDR() != null) {
            _hashCode += getOUT_ADDR().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(AddressParseResponseTypeAddressParseResponseElement.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.informatica.com/wsdl/", ">AddressParseResponseType>AddressParseResponseElement"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("OUT_ADDR");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.informatica.com/wsdl/", "OUT_ADDR"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
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
