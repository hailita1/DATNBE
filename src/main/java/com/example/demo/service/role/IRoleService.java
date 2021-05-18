package com.example.demo.service.role;

import com.example.demo.model.auth.Role;
import com.example.demo.service.IGeneralService;

public interface IRoleService extends IGeneralService<Role> {
    Role findByName(String name);
}
