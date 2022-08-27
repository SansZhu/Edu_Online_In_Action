package top.zshan.ggkt.result;

import lombok.Data;
import top.zshan.ggkt.enums.ResultCodeEnum;

/**
 * @author SansZhu
 * @create 2022/8/10 21:12
 */
@Data
public class Result<T> {
    private Integer code;
    private String message;
    private T data;
    public Result(){

    }
    public static<T> Result<T> ok(T data){
        Result<T> result = new Result<>();
        result.setCode(ResultCodeEnum.SUCCESS.getCode());
        result.setMessage(ResultCodeEnum.SUCCESS.getMessage());
        if (data != null){
            result.setData(data);
        }
        return result;
    }
    public static<T> Result<T> fail(T data){
        Result<T> result = new Result<>();
        result.setCode(ResultCodeEnum.FAIL.getCode());
        result.setMessage(ResultCodeEnum.FAIL.getMessage());
        if (data != null){
            result.setData(data);
        }
        return result;
    }
    public Result<T> message(String message){
        this.message = message;
        return this;
    }
    public Result<T> code(Integer code){
        this.code = code;
        return this;
    }
}
