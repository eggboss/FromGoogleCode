package kk.leech.db.vo;

import java.util.Date;

import kk.leech.db.DBEntities;

public class EMP extends DBEntities{

    private Integer empno;

    private String ename;

    private String job;

    private Integer mgr;

    private Date hiredate;

    private Integer sal;

    private Integer comm;

    private Integer deptno;

    public Integer getEmpno(){
        return this.empno;
    }
    public void setEmpno(Integer empno){
        this.empno = empno;
    }
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
    public Integer getMgr(){
        return this.mgr;
    }
    public void setMgr(Integer mgr){
        this.mgr = mgr;
    }
    public Date getHiredate(){
        return this.hiredate;
    }
    public void setHiredate(Date hiredate){
        this.hiredate = hiredate;
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
    public Integer getDeptno(){
        return this.deptno;
    }
    public void setDeptno(Integer deptno){
        this.deptno = deptno;
    }

    public String[] getKeys() {
        String[] keys = {"empno"};
        return keys;
    }
}
