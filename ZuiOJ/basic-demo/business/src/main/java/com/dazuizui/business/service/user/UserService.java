package com.dazuizui.business.service.user;

import com.dazuizui.basicapi.entry.User;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

@Service
public interface UserService {
    /**
     * 用户登入
     * @param user
     * @return
     */
    public String userLogin(@RequestBody User user);
}
