package com.urfread.breaknews.core.common.model;

import lombok.Data;
import lombok.experimental.Accessors;
import com.urfread.breaknews.core.common.enums.ResultCodeEnum;
/**
 * Class Description: 
 * This class represents a standardized response structure that can be used to return results
 * from various operations within the application. It includes a code, message, data, and timestamp.
 * The class utilizes Lombok annotations for boilerplate code reduction and supports chained method calls.
 *
 * @param <T> the type of data that will be returned in the response
 *
 * @author urfread
 * @date 2024-08-24 08:23
 */
@Data
@Accessors(chain = true)
public class ResultData<T> {
    private String code;
    private String msg;
    private T data;
    private Long timestamp;

    public ResultData() {
        this.timestamp = System.currentTimeMillis();
    }

    /**
     * Creates a successful ResultData instance with the given data.
     *
     * @param data the data to include in the response
     * @param <T> the type of data
     * @return a ResultData instance with a success code and message
     */
    public static <T> ResultData<T> success(T data) {
        return new ResultData<T>()
                .setCode(ResultCodeEnum.SUCCESS.getCode())
                .setMsg(ResultCodeEnum.SUCCESS.getMessage())
                .setData(data)
                .setTimestamp(System.currentTimeMillis());
    }

    /**
     * Creates a failure ResultData instance using a predefined ResultCodeEnum.
     *
     * @param resultCode the ResultCodeEnum to use for setting the code and message
     * @param <T> the type of data (typically null in a failure case)
     * @return a ResultData instance with a failure code and message
     */
    public static <T> ResultData<T> failure(ResultCodeEnum resultCode,String message) {
        return new ResultData<T>()
                .setCode(resultCode.getCode())
                .setMsg(message)
                .setTimestamp(System.currentTimeMillis());
    }
    public static void main(String[] args) {
        System.out.println(ResultData.success("success"));
        System.out.println(ResultData.failure(ResultCodeEnum.FAIL,null));
    }
}