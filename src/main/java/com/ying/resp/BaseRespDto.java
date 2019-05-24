package com.ying.resp;

import com.ying.constant.BaseResultEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 返回值对象
 *
 * @author lyz
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class BaseRespDto<T> implements Serializable {

    private int status;
    private String msg;
    private T data;

    public BaseRespDto(int status, String msg) {
        this.status = status;
        this.msg = msg;
    }

    public BaseRespDto(BaseResultEnum baseResultEnum) {
        this.status = baseResultEnum.getStatus();
        this.msg = baseResultEnum.getMsg();
    }

    public BaseRespDto(BaseResultEnum baseResultEnum, T data) {
        this.status = baseResultEnum.getStatus();
        this.msg = baseResultEnum.getMsg();
        this.data = data;
    }
}
