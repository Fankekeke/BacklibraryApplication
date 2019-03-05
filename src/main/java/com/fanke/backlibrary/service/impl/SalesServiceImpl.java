package com.fanke.backlibrary.service.impl;

import com.fanke.backlibrary.mapper.SalesMapper;
import com.fanke.backlibrary.pojo.sales;
import com.fanke.backlibrary.service.SalesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service("salesServiceImpl")
@Transactional
public class SalesServiceImpl implements SalesService {
    @Autowired
    @Qualifier("salesMapper")
    private SalesMapper salesMapper;

    @Override
    public List<sales> selectSales() {
        return this.salesMapper.selectSales();
    }

    @Override
    public int insertSale(sales sales) {
        return this.salesMapper.insertSale(sales);
    }

    @Override
    public int deleteSale(int saleId) {
        return this.salesMapper.deleteSale(saleId);
    }

    @Override
    public sales selectSaleById(int saleId) {
        return  this.salesMapper.selectSaleById(saleId);
    }

    @Override
    public List<sales> selectSalesByFenye(Integer pageNo, Integer pageSize, Integer titleId) {
        return this.salesMapper.selectSalesByFenye(pageNo,pageSize,titleId);
    }

    @Override
    public int selectSalesCount() {
        return this.salesMapper.selectSalesCount();
    }

    @Override
    public int updateSale(sales sales) {
        return this.salesMapper.updateSale(sales);
    }

    @Override
    public List<sales> selectUps(Date nowTime) {
        return this.salesMapper.selectUps(nowTime);
    }
}
