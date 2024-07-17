package uz.example.fastfood.dtos.responcseDto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BaseResponse<T> {
    private String message;
    private int code;
    private Object data;


    public static <T> BaseResponse<T> success(T data) {
        return new BaseResponse<>("Success", 200, data);
    }

    public static BaseResponse<?> successDefault() {
        return BaseResponse.success(null);
    }
}
