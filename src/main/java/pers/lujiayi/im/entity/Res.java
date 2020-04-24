package pers.lujiayi.im.entity;

public class Res<T> {

    public static final Integer CODE_SUCCESS = 200;
    public static final Integer CODE_FAILURE = 400;

    private Integer code = CODE_SUCCESS;

    private String msg;

    private T data;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public void setSuccessMsg(String msg) {
        this.code = CODE_SUCCESS;
        this.msg = msg;
    }

    public void setFailureMsg(String msg) {
        this.code = CODE_FAILURE;
        this.msg = msg;
    }

    public static <T> Res<T> ok(T data, String msg) {
        Res res = new Res();
        res.setData(data);
        res.setSuccessMsg(msg);
        return res;
    }

    public static Res failure(String msg) {
        Res res = new Res();
        res.setFailureMsg(msg);
        return res;
    }

    public static <T> Res<T> failure(T data, String msg) {
        Res res = new Res();
        res.setData(data);
        res.setFailureMsg(msg);
        return res;
    }
}
