package kk.leech.db.vo;

import java.util.Date;

import kk.leech.db.DBEntities;

public class DISTRIBUTOR extends DBEntities{

    private Integer cust_id;

    private String password;

    private String cust_name;

    private Integer phone;

    private String address;

    public Integer getCust_id(){
        return this.cust_id;
    }
    public void setCust_id(Integer cust_id){
        this.cust_id = cust_id;
    }
    public String getPassword(){
        return this.password;
    }
    public void setPassword(String password){
        this.password = password;
    }
    public String getCust_name(){
        return this.cust_name;
    }
    public void setCust_name(String cust_name){
        this.cust_name = cust_name;
    }
    public Integer getPhone(){
        return this.phone;
    }
    public void setPhone(Integer phone){
        this.phone = phone;
    }
    public String getAddress(){
        return this.address;
    }
    public void setAddress(String address){
        this.address = address;
    }

    public String[] getKeys() {
        String[] keys = {"cust_id"};
        return keys;
    }
}
