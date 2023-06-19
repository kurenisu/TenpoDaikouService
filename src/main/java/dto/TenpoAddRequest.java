package dto;

import java.io.Serializable;

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
    * 住所
    */
   @Size(max = 100, message = "住所は100桁以内で入力してください")
   private String address;
   
   /**
    * 電話番号
    */
   @Pattern(regexp = "0\\d{1,4}-\\d{1,4}-\\d{4}", message = "電話番号の形式で入力してください")
   private String phone;

}
