package kk.leech.db.vo;

import java.util.Date;

import kk.leech.db.DBEntities;

public class SALGRADE extends DBEntities{

    private Integer grade;

    private Integer losal;

    private Integer hisal;

    public Integer getGrade(){
        return this.grade;
    }
    public void setGrade(Integer grade){
        this.grade = grade;
    }
    public Integer getLosal(){
        return this.losal;
    }
    public void setLosal(Integer losal){
        this.losal = losal;
    }
    public Integer getHisal(){
        return this.hisal;
    }
    public void setHisal(Integer hisal){
        this.hisal = hisal;
    }

    public String[] getKeys() {
        String[] keys = {};
        return keys;
    }
}
