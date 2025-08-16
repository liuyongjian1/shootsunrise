package com.shootsunrise.core.common.mybatis.core.type;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Set;
import java.util.stream.Collectors;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedJdbcTypes;
import org.apache.ibatis.type.MappedTypes;
import org.apache.ibatis.type.TypeHandler;

/**
 * Set<Long> 的类型转换器实现类，对应数据库的 varchar 类型
 *
 * @author lyj
 * @since 2025-07-27
 */
@MappedJdbcTypes(JdbcType.VARCHAR)
@MappedTypes(Set.class)
public class LongSetTypeHandler implements TypeHandler<Set<Long>> {

    private static final String COMMA = ",";

    @Override
    public void setParameter(PreparedStatement ps, int i, Set<Long> strings, JdbcType jdbcType) throws SQLException {
        // 设置占位符
        ps.setString(i, CollUtil.join(strings, COMMA));
    }

    @Override
    public Set<Long> getResult(ResultSet rs, String columnName) throws SQLException {
        String value = rs.getString(columnName);
        return getResult(value);
    }

    @Override
    public Set<Long> getResult(ResultSet rs, int columnIndex) throws SQLException {
        String value = rs.getString(columnIndex);
        return getResult(value);
    }

    @Override
    public Set<Long> getResult(CallableStatement cs, int columnIndex) throws SQLException {
        String value = cs.getString(columnIndex);
        return getResult(value);
    }

    private Set<Long> getResult(String value) {
        if (StrUtil.isEmpty(value)) {
            return null;
        }
        return StrUtil.split(value, COMMA).stream()
                .map(Long::parseLong)
                .collect(Collectors.toSet());
    }
}
