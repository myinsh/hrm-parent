package cn.yinsh.hrm.service;

import cn.yinsh.hrm.controller.vo.RegisterVo;
import cn.yinsh.hrm.domain.Sso;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 会员登录账号 服务类
 * </p>
 *
 * @author yinsh
 * @since 2020-06-29
 */
public interface ISsoService extends IService<Sso> {

    void telephoneReg(RegisterVo vo);
    Sso getByName(String name);
}
