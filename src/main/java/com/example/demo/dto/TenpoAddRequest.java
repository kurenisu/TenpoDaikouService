package com.example.demo.dto;

import java.io.Serializable;

import org.springframework.web.multipart.MultipartFile;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

/**
 * 店舗情報登録 リクエストデータ
 */
@Data
public class TenpoAddRequest implements Serializable {
	
    /**
     * 名前
     */
    @NotEmpty(message = "名前を入力してください")
    @Size(max = 50, message = "名前は50桁以内で入力してください")
    private String name;
    
    /**
    * 郵便番号(3桁)
    */
   @NotEmpty(message = "上3桁の郵便番号を入力してください")
   @Size(max = 3, message = "上3桁の郵便番号は3桁以内で入力してください")
   private String postal1;
   
   /**
   * 郵便番号(4桁)
   */
   @NotEmpty(message = "下4桁の郵便番号を入力してください")
   @Size(max = 4, message = "下4桁の郵便番号は4桁以内で入力してください")
   private String postal2;
    
    /**
    * 住所
    */
   @NotEmpty(message = "住所を入力してください")
   @Size(max = 100, message = "住所は100桁以内で入力してください")
   private String address;
   
   /**
    * 電話番号
    */
   @Pattern(regexp = "0\\d{1,4}-\\d{1,4}-\\d{4}", message = "電話番号の形式で入力してください")
   private String phone;
   
   /**
   * 営業時間
   */
   @NotEmpty(message = "営業時間を入力してください")
   @Size(max = 45, message = "営業時間は45桁以内で入力してください")
   private String time;
  
   /**
   * 店舗url
   */
   private String url;
   
   /**
   * 画像
   */
   private MultipartFile image;
   
   /**
   * 画像名
   */
   private String imageName;

}
