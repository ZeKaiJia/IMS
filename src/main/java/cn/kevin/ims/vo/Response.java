package cn.kevin.ims.vo;

/**
 * 自定义响应体类
 * Response
 * @param <T> the type parameter
 * @Author: Kevin
 * @Date: 2020 /2/18 10:56 下午
 */
public class Response<T> {
    /**
     * 是否成功
     */
    private Boolean success;
    /**
     * 状态码
     */
    private Integer code;
    /**
     * 反馈信息
     */
    private String message;
    /**
     * 时间戳
     */
    private Long timestamp;
    /**
     * 实体模板类数据
     */
    private T data;


    /**
     * 获取响应状态
     * @method: get success.
     * @return: success
     */
    public Boolean getSuccess() {
        return success;
    }


    /**
     * Sets success.
     * 设置响应状态
     * @param success the success
     * @return the success
     */
    public Response<T> setSuccess(Boolean success) {
        this.success = success;
        return this;
    }

    /**
     * 获取响应码
     * @method: get code.
     * @return: code
     */
    public Integer getCode() {
        return code;
    }

    /**
     * Sets code.
     * 设置响应码
     * @param code the code
     * @return the code
     */
    public Response<T> setCode(Integer code) {
        this.code = code;
        return this;
    }

    /**
     * 获取响应信息
     * @method: get message.
     * @return: message
     */
    public String getMessage() {
        return message;
    }


    /**
     * Sets message.
     * 设置响应信息
     * @param message the message
     * @return the message
     */
    public Response<T> setMessage(String message) {
        this.message = message;
        return this;
    }

    /**
     * 获取时间戳
     * @method: get timestamp.
     * @return: timestamp
     */
    public Long getTimestamp() {
        return timestamp;
    }

    /**
     * Sets timestamp.
     * 设置时间戳
     * @param timestamp the timestamp
     * @return the timestamp
     */
    public Response<T> setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
        return this;
    }

    /**
     * 获取数据
     * @method: get data.
     * @return: data
     */
    public T getData() {
        return data;
    }

    /**
     * Sets data.
     * 设置数据
     * @param data the data
     * @return the data
     */
    public Response<T> setData(T data) {
        this.data = data;
        return this;
    }

    /**
     * To string string.
     * 转换字符串
     * @return the string
     */
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
