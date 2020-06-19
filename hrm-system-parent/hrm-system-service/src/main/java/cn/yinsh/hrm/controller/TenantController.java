package cn.yinsh.hrm.controller;

import cn.yinsh.hrm.controller.vo.RegisterVo;
import cn.yinsh.hrm.service.ITenantService;
import cn.yinsh.hrm.domain.Tenant;
import cn.yinsh.hrm.query.TenantQuery;
import cn.yinsh.hrm.util.AjaxResult;
import cn.yinsh.hrm.util.PageList;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tenant")
public class TenantController {
    @Autowired
    public ITenantService tenantService;

    /**
    * 保存和修改公用的
    * @param tenant  传递的实体
    * @return Ajaxresult转换结果
    */
    @RequestMapping(value="/save",method= RequestMethod.POST)
    public AjaxResult save(@RequestBody Tenant tenant){
        try {
            if(tenant.getId()!=null){
                tenantService.updateById(tenant);
            }else{
                tenantService.save(tenant);
            }
            return AjaxResult.me();
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.me().setSuccess(false).setMessage("保存对象失败！"+e.getMessage());
        }
    }

    /**
    * 删除对象信息
    * @param id
    * @return
    */
    @RequestMapping(value="/{id}",method=RequestMethod.DELETE)
    public AjaxResult delete(@PathVariable("id") Long id){
        try {
            tenantService.removeById(id);
            return AjaxResult.me();
        } catch (Exception e) {
        e.printStackTrace();
            return AjaxResult.me().setMessage("删除对象失败！"+e.getMessage());
        }
    }

    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    public Tenant get(@PathVariable("id")Long id)
    {
        return tenantService.getById(id);
    }


    /**
    * 查看所有信息
    * @return
    */
    @RequestMapping(value = "/list",method = RequestMethod.GET)
    public List<Tenant> list(){

        return tenantService.list(null);
    }


    /**
    * 分页查询数据
    *
    * @param query 查询对象
    * @return PageList 分页对象
    */
    @RequestMapping(value = "/page",method = RequestMethod.POST)
    public PageList<Tenant> page(@RequestBody TenantQuery query)
    {
        Page<Tenant> page = tenantService.page(new Page<Tenant>(query.getPageNum(), query.getPageSize()));
        return new PageList<>(page.getTotal(),page.getRecords());
    }

    @PostMapping("/register")
    public AjaxResult register(@RequestBody RegisterVo registerVo){
        try {
            registerVo.setRegisterTime(System.currentTimeMillis());
            tenantService.register(registerVo);
            return AjaxResult.me().setSuccess(true).setMessage("注册成功").setResultObj(registerVo);
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.me().setSuccess(false).setMessage("注册失败"+e.getMessage());
        }
    }


}