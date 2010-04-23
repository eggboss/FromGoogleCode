package kk.leech.db.vo;

import java.util.Date;

import kk.leech.db.DBEntities;

public class DEPT extends DBEntities{

    private Integer deptno;

    private String dname;

    private String loc;

    public Integer getDeptno(){
        return this.deptno;
    }
    public void setDeptno(Integer deptno){
        this.deptno = deptno;
    }
    public String getDname(){
        return this.dname;
    }
    public void setDname(String dname){
        this.dname = dname;
    }
    public String getLoc(){
        return this.loc;
    }
    public void setLoc(String loc){
        this.loc = loc;
    }

    public String[] getKeys() {
        String[] keys = {"deptno"};
        return keys;
    }
}
