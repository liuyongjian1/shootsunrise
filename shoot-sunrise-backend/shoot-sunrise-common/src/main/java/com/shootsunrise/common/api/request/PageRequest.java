package com.shootsunrise.common.api.request;

import lombok.Data;

import java.io.Serializable;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

/**
 * 分页请求基类
 * @author lyj
 * @since 2025-07-27
 */
@Data
public class PageRequest implements Serializable {

    public static final Integer PAGE_SIZE_NONE = -1;

    /**
     * 页码，从 1 开始
     */
    @NotNull(message = "页码不能为空")
    @Min(value = 1, message = "页码最小值为 1")
    private Integer pageNo = 1;

    /**
     * 每页条数，最大值为 100
     */
    @NotNull(message = "每页条数不能为空")
    @Min(value = 1, message = "每页条数最小值为 1")
    @Max(value = 100, message = "每页条数最大值为 100")
    private Integer pageSize = 10;

    public Integer getPageNo() {
        return pageNo;
    }

    public Integer getPageSize() {
        return pageSize;
    }
}
