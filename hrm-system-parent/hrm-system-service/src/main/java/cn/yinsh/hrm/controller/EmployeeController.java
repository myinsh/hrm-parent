package cn.yinsh.hrm.controller;

import cn.yinsh.hrm.domain.Employee;
import cn.yinsh.hrm.service.EmployeeService;
import cn.yinsh.hrm.util.AjaxResult;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @PostMapping("/login")
    public AjaxResult login(@RequestBody Employee employee) {
        if (StringUtils.isEmpty(employee.getUsername())) {
            return new AjaxResult().setSuccess(false).setMessage("用户名不能为空");
        }
        if (StringUtils.isEmpty(employee.getPassword())) {
            return new AjaxResult().setSuccess(false).setMessage("密码不能为空");
        }
        QueryWrapper<Employee> wrapper = new QueryWrapper<>();
        wrapper.eq("username",employee.getUsername());
        wrapper.eq("password",employee.getPassword());
        Employee loginUser = employeeService.getOne(wrapper);

        if (loginUser == null){
            return new AjaxResult().setSuccess(false).setMessage("用户名或密码错误");
        }
        loginUser.setPassword(null);
        return new AjaxResult().setSuccess(true).setMessage("登录成功").setResultObj(loginUser);
    }
}
