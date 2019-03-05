package com.fanke.backlibrary.pojo;

import java.util.Date;

/**
 * 员工表
 */
public class employee {
    //员工id
    private Integer empId;
    //员工姓名
    private String fname;
    //密钥
    private String lname;
    //工作编号
    private Integer jobId;
    //入职时间
    private Date hireDate;

    private String ups;

    private jobs jobs;

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public jobs getJobs() {
        return jobs;
    }

    public void setJobs(jobs jobs) {
        this.jobs = jobs;
    }

    public Integer getEmpId() {
        return empId;
    }

    public void setEmpId(Integer empId) {
        this.empId = empId;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public Integer getJobId() {
        return jobId;
    }

    public void setJobId(Integer jobId) {
        this.jobId = jobId;
    }

    public Date getHireDate() {
        return hireDate;
    }

    public void setHireDate(Date hireDate) {
        this.hireDate = hireDate;
    }

    public String getUps() {
        return ups;
    }

    public void setUps(String ups) {
        this.ups = ups;
    }

    /**
     * 有参构造
     * @param empId
     * @param fname
     * @param jobId
     * @param hireDate
     */
    public employee(Integer empId, String fname, Integer jobId, Date hireDate) {
        this.empId = empId;
        this.fname = fname;
        this.jobId = jobId;
        this.hireDate = hireDate;
    }

    /**
     * 无参构造
     */
    public employee(){}

    @Override
    public String toString() {
        return "employee{" +
                "empId=" + empId +
                ", fname='" + fname + '\'' +
                ", jobId=" + jobId +
                ", hireDate=" + hireDate +
                '}';
    }
}
