package com.jacksonleonardo.unpaper.model.services;

import com.jacksonleonardo.unpaper.model.entities.IUser;
import com.jacksonleonardo.unpaper.model.enumerators.ERole;
import com.jacksonleonardo.unpaper.model.exceptions.NullArgumentException;

import java.util.HashSet;
import java.util.Set;

import static com.jacksonleonardo.unpaper.model.services.IdentificationService.identificationServiceDefault;
import static com.jacksonleonardo.unpaper.model.services.LoginService.LoginServiceDefault;
import static com.jacksonleonardo.unpaper.model.services.PermissionService.permissionServiceDefault;
import static com.jacksonleonardo.unpaper.model.services.SessionService.getCurrentUser;

public class AuthenticationService implements IAuthenticationService {

    private final IIdentificationService identificationService;
    private final IPermissionService permissionService;
    private final ILoginService loginService;

    public AuthenticationService(IIdentificationService identificationService, IPermissionService permissionService, ILoginService loginService) {
        if (identificationService == null || permissionService == null || loginService == null)
            throw new NullArgumentException();

        this.identificationService = identificationService;
        this.permissionService = permissionService;
        this.loginService = loginService;
    }

    @Override
    public boolean authenticate(String accessKey, String password) {
        if (accessKey == null || password == null)
            throw new NullArgumentException();
        IUser user = identificationService.identifyUser(accessKey);
        if (user == null)
            return false;

        return loginService.logInto(user, password);
    }

    @Override
    public boolean authenticate(String accessKey, String password, ERole role) {
        if (accessKey == null || password == null || role == null)
            throw new NullArgumentException();
        IUser user = identificationService.identifyUser(accessKey);
        if (user == null)
            return false;
        if (!permissionService.hasRole(user, role))
            return false;
        return loginService.logInto(user, password);
    }


    @Override
    public boolean isAuthenticated(IUser user, Set<ERole> roles) {
        if (user == null || roles == null)
            throw new NullArgumentException();
        if (identificationService.isValid(user)) {
            for (ERole role : roles) {
                if (!user.getRoles().contains(role))
                    return false;
            }
            return getCurrentUser() != null && getCurrentUser().equals(user) && getCurrentUser().getCredential().equals(user.getCredential());
        }
        return false;
    }

    @Override
    public boolean isAuthenticated(IUser user) {
        return isAuthenticated(user, new HashSet<>());
    }

    public static IAuthenticationService authenticationServiceDefault() {
        return new AuthenticationService(identificationServiceDefault(), permissionServiceDefault(), LoginServiceDefault());
    }
}
