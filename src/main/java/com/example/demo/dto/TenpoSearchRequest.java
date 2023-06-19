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
     * ユーザー名
     */
    private String name;

}
