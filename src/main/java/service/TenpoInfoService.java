package service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dao.TenpoInfoMapper;
import dto.TenpoAddRequest;
import dto.TenpoSearchRequest;
import dto.TenpoUpdateRequest;
import entity.TenpoInfo;

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
    public void save(TenpoAddRequest tenpoAddRequest) {
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
    
}
