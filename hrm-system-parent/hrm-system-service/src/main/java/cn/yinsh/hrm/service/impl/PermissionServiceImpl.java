package cn.yinsh.hrm.service.impl;

import cn.yinsh.hrm.domain.Permission;
import cn.yinsh.hrm.mapper.PermissionMapper;
import cn.yinsh.hrm.service.IPermissionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author yinsh
 * @since 2020-06-19
 */
@Service
public class PermissionServiceImpl extends ServiceImpl<PermissionMapper, Permission> implements IPermissionService {

}
