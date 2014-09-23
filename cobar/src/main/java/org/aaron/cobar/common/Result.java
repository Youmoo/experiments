package org.aaron.cobar.common;

import java.io.Serializable;
import java.util.List;

/**
 * 查询结果封装
 *
 * @param <T> 实体类型
 */
public class Result<T> implements Serializable {

    private boolean isSuccess = true;
    private List<T> list;
    private int count;
    private String errorMsg;

    public boolean isSuccess() {
        return isSuccess;
    }

    public void setSuccess(boolean isSuccess) {
        this.isSuccess = isSuccess;
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;

    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

}
