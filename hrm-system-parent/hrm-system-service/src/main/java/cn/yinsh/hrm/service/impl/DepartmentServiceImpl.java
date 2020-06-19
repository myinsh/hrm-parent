package cn.yinsh.hrm.service.impl;

import cn.yinsh.hrm.domain.Department;
import cn.yinsh.hrm.mapper.DepartmentMapper;
import cn.yinsh.hrm.service.IDepartmentService;
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
public class DepartmentServiceImpl extends ServiceImpl<DepartmentMapper, Department> implements IDepartmentService {

}
