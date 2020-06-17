package cn.yinsh.hrm.service.impl;

import cn.yinsh.hrm.domain.Employee;
import cn.yinsh.hrm.mapper.EmployeeMapper;
import cn.yinsh.hrm.service.EmployeeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class EmployeeServiceImpl extends ServiceImpl<EmployeeMapper,Employee> implements EmployeeService {
}
