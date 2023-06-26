package com.example.demo.service;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.dao.TenpoInfoMapper;
import com.example.demo.dto.TenpoSearchRequest;
import com.example.demo.dto.TenpoUpdateRequest;
import com.example.demo.entity.TenpoInfo;

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
    
    /**
     * 店舗情報全件検索
     * @return 検索結果
     */
    public List<TenpoInfo> findAll() {
        return tenpoInfoMapper.findAll();
    }
    
    /**
     * 店舗情報主キー検索
     * @return 検索結果
     */
    public TenpoInfo findById(Long id) {
        return tenpoInfoMapper.findById(id);
    }
    
    /**
     * 店舗情報検索
     * @param tenpoSearchRequest リクエストデータ
     * @return 検索結果
     */
    public List<TenpoInfo> search(TenpoSearchRequest tenpoSearchRequest) {
        return tenpoInfoMapper.search(tenpoSearchRequest);
    }
    
    /**
     * 店舗情報登録
     * @param tenpoAddRequest リクエストデータ
     */
    public void save(TenpoInfo tenpoAddRequest) {
        tenpoInfoMapper.save(tenpoAddRequest);
    }

    /**
     * 店舗情報更新
     * @param tenpoEditRequest リクエストデータ
     */
    public void update(TenpoUpdateRequest tenpoUpdateRequest) {
        tenpoInfoMapper.update(tenpoUpdateRequest);
    }
    /**
     * 店舗情報論理削除
     * @param id
     */
    public void delete(Long id) {
        tenpoInfoMapper.delete(id);
    }
    
	/* アップロードの実行処理 */
	public byte[] uploadFile(MultipartFile multipartFile) {
    	try {
        	// アップロードファイルをバイト値に変換
    		byte[] bytes = multipartFile.getBytes();
			
    		return bytes;
			
    	} catch (IOException e) {
        	e.printStackTrace();
    		return null;
    	}
	}    
    
}
