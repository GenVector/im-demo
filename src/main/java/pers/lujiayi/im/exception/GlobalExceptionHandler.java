package pers.lujiayi.im.exception;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.extension.api.R;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author Lujiayi
 * @date 2020/4/27
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public R exception(Exception e) {
        String msg = "内部服务器错误";
        if (StrUtil.isNotBlank(e.getMessage())) {
            msg = msg + ":" + e.getMessage();
        }
        return R.failed(msg);
    }

}
