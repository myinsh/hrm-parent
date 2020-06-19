package cn.yinsh.hrm.service.impl;

import cn.yinsh.hrm.domain.Employee;
import cn.yinsh.hrm.mapper.EmployeeMapper;
import cn.yinsh.hrm.service.IEmployeeService;
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
public class EmployeeServiceImpl extends ServiceImpl<EmployeeMapper, Employee> implements IEmployeeService {

}
