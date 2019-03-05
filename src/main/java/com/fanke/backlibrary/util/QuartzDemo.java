package com.fanke.backlibrary.util;

import com.fanke.backlibrary.pojo.sales;
import com.fanke.backlibrary.service.SalesService;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 定义任务类
 *
 *
 */
public class QuartzDemo implements Job {

    @Autowired
    @Qualifier("salesServiceImpl")
    private SalesService salesService;
    Logger logger = LoggerFactory.getLogger(getClass());
    /**
     * 任务被触发时所执行的方法
     */
    public void execute(JobExecutionContext arg0) throws JobExecutionException {
        List<sales> salesList=new ArrayList<sales>();
        try {
            logger.info("正在发送邮件");
            salesList=salesService.selectUps(new Date());
            for (sales sales : salesList) {
                logger.info("正在往"+sales.getUsersn().getMail()+"发送邮件");
                email.sendEmail(sales.getUsersn().getUname(),sales.getDoubanbookInfo().getTitle(),sales.getUsersn().getMail());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
