/**
 * AddressParseResponseType.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.informatica.www.wsdl;

public class AddressParseResponseType  implements java.io.Serializable {
    private com.informatica.www.wsdl.AddressParseResponseTypeAddressParseResponseElement addressParseResponseElement;

    public AddressParseResponseType() {
    }

    public AddressParseResponseType(
           com.informatica.www.wsdl.AddressParseResponseTypeAddressParseResponseElement addressParseResponseElement) {
           this.addressParseResponseElement = addressParseResponseElement;
    }


    /**
     * Gets the addressParseResponseElement value for this AddressParseResponseType.
     * 
     * @return addressParseResponseElement
     */
    public com.informatica.www.wsdl.AddressParseResponseTypeAddressParseResponseElement getAddressParseResponseElement() {
        return addressParseResponseElement;
    }


    /**
     * Sets the addressParseResponseElement value for this AddressParseResponseType.
     * 
     * @param addressParseResponseElement
     */
    public void setAddressParseResponseElement(com.informatica.www.wsdl.AddressParseResponseTypeAddressParseResponseElement addressParseResponseElement) {
        this.addressParseResponseElement = addressParseResponseElement;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof AddressParseResponseType)) return false;
        AddressParseResponseType other = (AddressParseResponseType) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.addressParseResponseElement==null && other.getAddressParseResponseElement()==null) || 
             (this.addressParseResponseElement!=null &&
              this.addressParseResponseElement.equals(other.getAddressParseResponseElement())));
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
        if (getAddressParseResponseElement() != null) {
            _hashCode += getAddressParseResponseElement().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(AddressParseResponseType.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.informatica.com/wsdl/", "AddressParseResponseType"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("addressParseResponseElement");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.informatica.com/wsdl/", "AddressParseResponseElement"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.informatica.com/wsdl/", ">AddressParseResponseType>AddressParseResponseElement"));
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
