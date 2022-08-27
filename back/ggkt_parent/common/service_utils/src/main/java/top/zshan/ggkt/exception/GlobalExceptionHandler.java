package top.zshan.ggkt.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import top.zshan.ggkt.result.Result;

/**
 * @author SansZhu
 * @create 2022/8/11 17:23
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public Result globalExceptionHandler(Exception e){
        e.printStackTrace();
        return Result.fail(null).message("全局异常处理");
    }

    @ExceptionHandler(ArithmeticException.class)
    @ResponseBody
    public Result arithmeticException(ArithmeticException e){
        e.printStackTrace();
        return  Result.fail(null).message("ArithmeticException异常处理");
    }
    @ExceptionHandler(GgktException.class)
    @ResponseBody
    public Result ggktException(GgktException e){
        e.printStackTrace();
        return  Result.fail(null).code(e.getCode()).message(e.getMessage());
    }
}
