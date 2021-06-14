package com.jacksonleonardo.unpaper.model.services;

import com.jacksonleonardo.unpaper.model.entities.IUser;
import com.jacksonleonardo.unpaper.model.enumerators.ERole;

public class PermissionService implements IPermissionService {

    @Override
    public boolean hasRole(IUser user, ERole role) {
        return user.getRoles().contains(role);
    }

    private PermissionService(){}
    public static IPermissionService permissionServiceDefault(){
        return new PermissionService();
    }
}
