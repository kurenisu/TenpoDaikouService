package dto;

import java.io.Serializable;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * ユーザー情報更新リクエストデータ
 *
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class TenpoUpdateRequest extends TenpoAddRequest implements Serializable{
	
    /**
     * ユーザーID
     */
    @NotNull
    private Long id;

}
