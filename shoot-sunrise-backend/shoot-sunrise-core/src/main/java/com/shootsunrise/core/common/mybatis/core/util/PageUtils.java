package com.shootsunrise.core.common.mybatis.core.util;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.shootsunrise.common.api.request.PageRequest;
import com.shootsunrise.common.api.response.PageResult;

import java.util.List;

/**
 * 分页工具类
 * @author lyj
 * @since 2025-07-30
 */
public class PageUtils {

    /**
     * 执行分页查询
     * @param mapper Mapper 接口
     * @param pageRequest 分页请求
     * @param queryWrapper 查询条件
     * @param <T> 实体类型
     * @return 分页结果
     */
    public static <T> PageResult<T> selectPage(BaseMapper<T> mapper, PageRequest pageRequest, Wrapper<T> queryWrapper) {
        // 特殊：不分页，直接查询全部
        if (PageRequest.PAGE_SIZE_NONE.equals(pageRequest.getPageSize())) {
            List<T> list = mapper.selectList(queryWrapper);
            return new PageResult<>(list, (long) list.size());
        }
        
        // 正常分页
        IPage<T> page = new Page<>(pageRequest.getPageNo(), pageRequest.getPageSize());
        IPage<T> result = mapper.selectPage(page, queryWrapper);
        return new PageResult<>(result.getRecords(), result.getTotal());
    }
}
