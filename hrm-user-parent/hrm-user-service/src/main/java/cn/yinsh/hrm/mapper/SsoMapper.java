package cn.yinsh.hrm.mapper;

import cn.yinsh.hrm.domain.Sso;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 * 会员登录账号 Mapper 接口
 * </p>
 *
 * @author yinsh
 * @since 2020-06-29
 */
public interface SsoMapper extends BaseMapper<Sso> {

    Sso selectByName(String name);
}
