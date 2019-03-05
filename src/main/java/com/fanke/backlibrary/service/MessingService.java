package com.fanke.backlibrary.service;

import com.fanke.backlibrary.pojo.messing;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface MessingService {
    /**
     * 添加公告
     * @param messing
     * @return
     */
    int insertMess(messing messing);

    /**
     * 修改公告
     * @param messing
     * @return
     */
    int updateMess(messing messing);

    /**
     * 根据id获取公告
     * @param mesId
     * @return
     */
    messing selectMessById(@Param("mesId") Integer mesId);

    /**
     * 分页查询公告
     * @param pageNo
     * @param pageSize
     * @return
     */
    List<messing> selectMesslist(@Param("pageNo") Integer pageNo, @Param("pageSize") Integer pageSize);

    /**
     * 分页查询数量
     * @param pageNo
     * @param pageSize
     * @return
     */
    int selectMesslistCount(@Param("pageNo") Integer pageNo,@Param("pageSize") Integer pageSize);

    /**
     * 查询全部
     * @return
     */
    List<messing> selectAll();

    /**
     * 查询总数量
     * @return
     */
    int selectCount();
}
