package kk.leech.db.vo;

import java.util.Date;

import kk.leech.db.DBEntities;

public class BONUS extends DBEntities{

    private String ename;

    private String job;

    private Integer sal;

    private Integer comm;

    public String getEname(){
        return this.ename;
    }
    public void setEname(String ename){
        this.ename = ename;
    }
    public String getJob(){
        return this.job;
    }
    public void setJob(String job){
        this.job = job;
    }
    public Integer getSal(){
        return this.sal;
    }
    public void setSal(Integer sal){
        this.sal = sal;
    }
    public Integer getComm(){
        return this.comm;
    }
    public void setComm(Integer comm){
        this.comm = comm;
    }

    public String[] getKeys() {
        String[] keys = {};
        return keys;
    }
}
