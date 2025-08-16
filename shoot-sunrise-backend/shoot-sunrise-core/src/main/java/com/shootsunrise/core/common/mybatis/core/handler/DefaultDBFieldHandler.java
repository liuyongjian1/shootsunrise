package com.shootsunrise.core.common.mybatis.core.handler;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.shootsunrise.core.common.mybatis.core.dataobject.BaseEntity;
import org.apache.ibatis.reflection.MetaObject;

import java.time.LocalDateTime;
import java.util.Objects;

/**
 * 通用参数填充实现类
 * 如果没有显式的对通用参数进行赋值，这里会对通用参数进行填充、赋值
 * @author hexiaowu
 */
public class DefaultDBFieldHandler implements MetaObjectHandler {

    @Override
    public void insertFill(MetaObject metaObject) {
        if (Objects.nonNull(metaObject) && metaObject.getOriginalObject() instanceof BaseEntity baseEntity) {

            LocalDateTime current = LocalDateTime.now();
            // 创建时间为空，则以当前时间为插入时间
            if (Objects.isNull(baseEntity.getCreateTime())) {
                baseEntity.setCreateTime(current);
            }
            // 更新时间为空，则以当前时间为更新时间
            if (Objects.isNull(baseEntity.getUpdateTime())) {
                baseEntity.setUpdateTime(current);
            }

//            Long userId = WebFrameworkUtils.getLoginUserId();
//            // 当前登录用户不为空，创建人为空，则当前登录用户为创建人
//            if (Objects.nonNull(userId) && Objects.isNull(baseEntity.getCreator())) {
//                baseEntity.setCreator(userId.toString());
//            }
//            // 当前登录用户不为空，更新人为空，则当前登录用户为更新人
//            if (Objects.nonNull(userId) && Objects.isNull(baseEntity.getUpdater())) {
//                baseEntity.setUpdater(userId.toString());
//            }
        }
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        // 更新时间为空，则以当前时间为更新时间
        Object modifyTime = getFieldValByName("updateTime", metaObject);
        if (Objects.isNull(modifyTime)) {
            setFieldValByName("updateTime", LocalDateTime.now(), metaObject);
        }

//        // 当前登录用户不为空，更新人为空，则当前登录用户为更新人
//        Object modifier = getFieldValByName("updater", metaObject);
//        Long userId = WebFrameworkUtils.getLoginUserId();
//        if (Objects.nonNull(userId) && Objects.isNull(modifier)) {
//            setFieldValByName("updater", userId.toString(), metaObject);
//        }
    }
}
