package cn.yinsh.hrm.client;

import cn.yinsh.hrm.client.impl.SystemClientImpl;
import cn.yinsh.hrm.domain.SystemdictionaryItem;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
@FeignClient(name = "system-services",path = "/systemdictionaryItem",fallback = SystemClientImpl.class )
public interface SystemClient {

    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    SystemdictionaryItem get(@PathVariable("id")Long id);

}
