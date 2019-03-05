package com.fanke.backlibrary.service.impl;

import com.fanke.backlibrary.mapper.MessingMapper;
import com.fanke.backlibrary.pojo.messing;
import com.fanke.backlibrary.service.MessingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("messingServiceImpl")
@Transactional
public class MessingServiceImpl implements MessingService {

    @Autowired
    @Qualifier("messingMapper")
    private MessingMapper messingMapper;

    @Override
    public int insertMess(messing messing) {
        return this.messingMapper.insertMess(messing);
    }

    @Override
    public int updateMess(messing messing) {
        return this.messingMapper.updateMess(messing);
    }

    @Override
    public messing selectMessById(Integer mesId) {
        return this.messingMapper.selectMessById(mesId);
    }

    @Override
    public List<messing> selectMesslist(Integer pageNo, Integer pageSize) {
        return this.messingMapper.selectMesslist(pageNo,pageSize);
    }

    @Override
    public int selectMesslistCount(Integer pageNo, Integer pageSize) {
        return this.messingMapper.selectMesslistCount(pageNo,pageSize);
    }

    @Override
    public List<messing> selectAll() {
        return this.messingMapper.selectAll();
    }

    @Override
    public int selectCount() {
        return this.messingMapper.selectCount();
    }
}
