/**
 * 
 */
package com.example.demo.dto;

import java.io.Serializable;

import lombok.Data;

/**
 * 店舗情報 検索用リクエストデータ
 */
@Data
public class TenpoSearchRequest implements Serializable{
	
    /**
     * ID
     */
    private Long id;
    /**
     * 店舗名
     */
    private String name;

}
