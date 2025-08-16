package com.shootsunrise.common.api.response;

import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 分页响应结果
 * @author lyj
 * @since 2025-07-27
 */
@Data
public class PageResult<T> implements Serializable {

    /**
     * 数据列表
     */
    private List<T> list;

    /**
     * 总数
     */
    private Long total;

    public PageResult() {
    }

    public PageResult(List<T> list, Long total) {
        this.list = list;
        this.total = total;
    }

    public PageResult(Long total) {
        this.list = new ArrayList<>();
        this.total = total;
    }

    public static <T> PageResult<T> empty() {
        return new PageResult<>(0L);
    }

    public static <T> PageResult<T> empty(Long total) {
        return new PageResult<>(total);
    }
}
