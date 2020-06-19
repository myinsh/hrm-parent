package cn.yinsh.hrm.mapper;

import cn.yinsh.hrm.domain.RolePermission;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author yinsh
 * @since 2020-06-19
 */
@Component
public interface RolePermissionMapper extends BaseMapper<RolePermission> {

    void insertBatch(@Param("roleId") Long id,@Param("permissionIds") List<Long> permissionId);
}
