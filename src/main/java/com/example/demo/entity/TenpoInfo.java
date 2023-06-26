package com.example.demo.entity;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

/**
 * 店舗情報 Entity
 */
@Data
public class TenpoInfo implements Serializable{
	
    /**
     * ID
     */
    private Long id;
    /**
     * 名前
     */
    private String name;
    /**
     * 住所
     */
    private String address;
    /**
     * 電話番号
     */
    private String phone;
    /**
     * 営業時間
     */
    private String time;
    /**
     * url
     */
    private String url;
    /**
     * 店舗画像登録用
     */
    private byte[] image;
    /**
     * 店舗画像名
     */
    private String image_name;
    /**
     * 店舗画像表示用
     */
    private String banner;
    /**
     * パスワード
     */
    private String password;
    /**
     * 更新日時
     */
    private Date updateDate;
    /**
     * 登録日時
     */
    private Date createDate;
    /**
     * 削除日時
     */
    private Date deleteDate;

}
