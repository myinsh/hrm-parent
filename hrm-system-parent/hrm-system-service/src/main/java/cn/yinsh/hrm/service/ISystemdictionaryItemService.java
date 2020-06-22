package cn.yinsh.hrm.service;

import cn.yinsh.hrm.domain.SystemdictionaryItem;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author yinsh
 * @since 2020-06-19
 */
public interface ISystemdictionaryItemService extends IService<SystemdictionaryItem> {

    List<SystemdictionaryItem> getBySn(String course_level);
}
