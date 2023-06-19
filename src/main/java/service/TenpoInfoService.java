package service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dao.TenpoInfoMapper;

/**
 * 店舗情報 Service
 */
@Service
public class TenpoInfoService {
	
	
    /**
     * 店舗情報 Mapper
     */
    @Autowired
    private TenpoInfoMapper tenpoInfoMapper;

}
