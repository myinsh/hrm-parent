package cn.yinsh.hrm.service;

import cn.yinsh.hrm.controller.vo.RegisterVo;
import cn.yinsh.hrm.domain.Tenant;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author yinsh
 * @since 2020-06-19
 */
public interface ITenantService extends IService<Tenant> {

    void register(RegisterVo registerVo);
}
