package cn.yinsh.hrm.service.impl;

import cn.yinsh.hrm.domain.Systemdictionary;
import cn.yinsh.hrm.domain.SystemdictionaryItem;
import cn.yinsh.hrm.mapper.SystemdictionaryItemMapper;
import cn.yinsh.hrm.mapper.SystemdictionaryMapper;
import cn.yinsh.hrm.service.ISystemdictionaryItemService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author yinsh
 * @since 2020-06-19
 */
@Service
public class SystemdictionaryItemServiceImpl extends ServiceImpl<SystemdictionaryItemMapper, SystemdictionaryItem> implements ISystemdictionaryItemService {
    @Autowired
    private SystemdictionaryMapper systemdictionaryMapper;
    @Override
    public List<SystemdictionaryItem> getBySn(String sn) {
        Systemdictionary systemdictionary = systemdictionaryMapper.selectOne(new QueryWrapper<Systemdictionary>()
                .eq("sn", sn));
        if(systemdictionary==null){
            return null;
        }
        return baseMapper.selectList(new QueryWrapper<SystemdictionaryItem>()
                .eq("parent_id",systemdictionary.getId()));
    }
}
