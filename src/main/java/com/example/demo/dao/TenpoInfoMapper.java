package com.example.demo.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.dto.TenpoSearchRequest;
import com.example.demo.dto.TenpoUpdateRequest;
import com.example.demo.entity.TenpoInfo;

/**
 * 店舗情報 Mapper
 */
@Mapper
public interface TenpoInfoMapper {
	
    /**
     * 店舗情報全件検索
     * @param tenpoInfo 検索用リクエストデータ
     * @return 検索結果
     */
    List<TenpoInfo> findAll();
    
    /**
     * 店舗情報主キー検索
     * @param id 主キー
     * @return 検索結果
     */
    TenpoInfo findById(Long id);
    
    /**
     * 店舗情報検索
     * @param tenpoInfo 検索用リクエストデータ
     * @return 検索結果
     */
    List<TenpoInfo> search(TenpoSearchRequest tenpoInfo);
    
    /**
     * 店舗情報登録
     * @param tenpoInfoRequest 登録用リクエストデータ
     */
    void save(TenpoInfo tenpoRequest);
    
    /**
     * 店舗情報更新
     * @param tenpoInfoUpdateRequest 更新用リクエストデータ
     */
    void update(TenpoUpdateRequest tenpoUpdateRequest);
    
    /**
     * 店舗情報の論理削除
     * @param id ID
     */
    void delete(Long id);
    
	/* アップロード実行処理 */
	public byte[] uploadFile(MultipartFile multipartFile);    
    
}
