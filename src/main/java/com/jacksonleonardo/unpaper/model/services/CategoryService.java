package com.jacksonleonardo.unpaper.model.services;

import com.jacksonleonardo.unpaper.model.entities.IMovementCategory;
import com.jacksonleonardo.unpaper.model.entities.IUser;
import com.jacksonleonardo.unpaper.model.enumerators.ERole;
import com.jacksonleonardo.unpaper.model.exceptions.UserIsNotAuthorizedForActionException;
import com.jacksonleonardo.unpaper.model.repositories.IUserRepository;
import com.jacksonleonardo.unpaper.model.repositories.UserRepository;

public class CategoryService implements ICategoryService {
    private final IUserRepository userRepository;

    public CategoryService(IUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public static ICategoryService defaultCategoryService() {
        return new CategoryService(UserRepository.getInstance());
    }



    @Override
    public boolean registerCategory(IMovementCategory category) {
            IUser user = SessionService.getCurrentUser();
        if (category.isPublic() && !PermissionService.permissionServiceDefault().hasRole(user, ERole.ADMIN))
            throw new UserIsNotAuthorizedForActionException();
        try {
            user.addCategory(category);
            this.userRepository.update(user);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean removeCategory(IMovementCategory category) {
            IUser user = SessionService.getCurrentUser();
        if (category.isPublic() && !PermissionService.permissionServiceDefault().hasRole(user, ERole.ADMIN))
            throw new UserIsNotAuthorizedForActionException();
        try {
            user.removeCategory(category);
            this.userRepository.update(user);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean updateCategory(IMovementCategory category) {
            IUser user = SessionService.getCurrentUser();
        if (category.isPublic() && !PermissionService.permissionServiceDefault().hasRole(user, ERole.ADMIN))
            throw new UserIsNotAuthorizedForActionException();
        try {
            user.updateCategory(category);
            this.userRepository.update(user);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
