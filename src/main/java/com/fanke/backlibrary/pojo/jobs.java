package com.fanke.backlibrary.pojo;

/**
 * 工作表
 */
public class jobs {
    //工作编号
    private Integer jobId;
    //工作描述
    private String jobDesc;
    //保留1
    private Integer minlvl;
    //保留2
    private Integer maxlvl;

    public Integer getJobId() {
        return jobId;
    }

    public void setJobId(Integer jobId) {
        this.jobId = jobId;
    }

    public String getJobDesc() {
        return jobDesc;
    }

    public void setJobDesc(String jobDesc) {
        this.jobDesc = jobDesc;
    }

    public Integer getMinlvl() {
        return minlvl;
    }

    public void setMinlvl(Integer minlvl) {
        this.minlvl = minlvl;
    }

    public Integer getMaxlvl() {
        return maxlvl;
    }

    public void setMaxlvl(Integer maxlvl) {
        this.maxlvl = maxlvl;
    }

    /**
     * 有参构造
     * @param jobId
     * @param jobDesc
     * @param minlvl
     * @param maxlvl
     */
    public jobs(Integer jobId, String jobDesc, Integer minlvl, Integer maxlvl) {
        this.jobId = jobId;
        this.jobDesc = jobDesc;
        this.minlvl = minlvl;
        this.maxlvl = maxlvl;
    }

    /**
     * 无参构造
     */
    public jobs(){}

    @Override
    public String toString() {
        return "jobs{" +
                "jobId=" + jobId +
                ", jobDesc='" + jobDesc + '\'' +
                ", minlvl=" + minlvl +
                ", maxlvl=" + maxlvl +
                '}';
    }
}
