package com.wingliberty.gateway.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author aiLun
 * @date 2023/5/20-16:42
 */

@FeignClient(name = "authorization")
@RequestMapping("/auth")
public interface AuthFeign {


    @PostMapping("/user/check_toke")
    Object checkToken(@RequestBody String token);

}
