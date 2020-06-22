package cn.yinsh.hrm.client.impl;

import cn.yinsh.hrm.client.SystemClient;
import cn.yinsh.hrm.domain.SystemdictionaryItem;
import org.springframework.stereotype.Component;

@Component
public class SystemClientImpl implements SystemClient {
    @Override
    public SystemdictionaryItem get(Long id) {
        return null;
    }
}
