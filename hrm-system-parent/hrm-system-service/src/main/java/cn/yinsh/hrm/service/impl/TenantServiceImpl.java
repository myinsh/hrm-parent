package cn.yinsh.hrm.service.impl;

import cn.yinsh.hrm.controller.vo.RegisterVo;
import cn.yinsh.hrm.domain.*;
import cn.yinsh.hrm.mapper.*;
import cn.yinsh.hrm.service.ITenantService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author yinsh
 * @since 2020-06-19
 */
@Service
public class TenantServiceImpl extends ServiceImpl<TenantMapper, Tenant> implements ITenantService {

    @Autowired
    private TenantMealMapper tenantMealMapper;
    @Autowired
    private RoleMapper roleMapper;
    @Autowired
    private MealPermissionMapper mealPermissionMapper;
    @Autowired
    private RolePermissionMapper rolePermissionMapper;
    @Autowired
    private EmployeeMapper employeeMapper;
    @Autowired
    private EmployeeRoleMapper employeeRoleMapper;

    @Override
    public void register(RegisterVo registerVo) {
        //租户表
        Tenant tenant = new Tenant();
        BeanUtils.copyProperties(registerVo,tenant);
        baseMapper.insert(tenant);
        //租户套餐中间表
        TenantMeal tenantMeal = new TenantMeal();
        tenantMeal.setTenantId(tenant.getId());
        tenantMeal.setMealId(registerVo.getMealId());
        tenantMealMapper.insert(tenantMeal);
        //角色表
        Role role = new Role();
        role.setName("租户管理员");
        role.setSn("admin");
        role.setTenantId(tenant.getId());
        roleMapper.insert(role);
        //角色权限中间表
        //首先查询当前套餐中所有的权限
        List<MealPermission> mealPermissions = mealPermissionMapper.selectList(
                new QueryWrapper<MealPermission>().eq("meal_id", registerVo.getMealId())
        );
        List<Long> permissionIds = mealPermissions.stream().map(MealPermission::getPermissionId).collect(Collectors.toList());
        rolePermissionMapper.insertBatch(role.getId(),permissionIds);
        //用户表
        Employee employee = new Employee();
        employee.setInputTime(System.currentTimeMillis());
        employee.setState(1);
        employee.setTenantId(tenant.getId());
        employee.setUsername(registerVo.getUsername());
        employee.setPassword(registerVo.getPassword());
        employeeMapper.insert(employee);
        //用户角色中间表
        EmployeeRole employeeRole = new EmployeeRole();
        employeeRole.setEmployeeId(employee.getId());
        employeeRole.setRoleId(role.getId());
        employeeRoleMapper.insert(employeeRole);
    }
}
