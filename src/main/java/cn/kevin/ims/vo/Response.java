package cn.kevin.ims.vo;

/**
 * @author kevin
 */
public class Response<T> {
    private Boolean success;

    private Integer code;

    private String message;

    private Long timestamp;

    private T data;

    public Boolean getSuccess() {
        return success;
    }

    public Response<T> setSuccess(Boolean success) {
        this.success = success;
        return this;
    }

    public String getMessage() {
        return message;
    }

    public Integer getCode() {
        return code;
    }

    public Response<T> setCode(Integer code) {
        this.code = code;
        return this;
    }

    public Response<T> setMessage(String message) {
        this.message = message;
        return this;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public Response<T> setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
        return this;
    }

    public T getData() {
        return data;
    }

    public Response<T> setData(T data) {
        this.data = data;
        return this;
    }

    @Override
    public String toString() {
        return "Response{" +
                "success=" + success +
                ", code='" + code + '\'' +
                ", message='" + message + '\'' +
                ", timestamp=" + timestamp +
                ", data=" + data +
                '}';
    }
}
