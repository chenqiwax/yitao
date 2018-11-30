package cn.et.yitao.user.exception;

/**
 * 自定义异常类(用户)
 */
public class UserException extends Exception {
    public UserException() {
    }

    public UserException(String message) {
        super(message);
    }
}
