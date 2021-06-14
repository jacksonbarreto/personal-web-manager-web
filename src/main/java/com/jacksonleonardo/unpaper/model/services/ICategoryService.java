package com.jacksonleonardo.unpaper.model.services;

import com.jacksonleonardo.unpaper.model.entities.IMovementCategory;

public interface ICategoryService {

    boolean registerCategory(IMovementCategory category);

    boolean removeCategory(IMovementCategory category);

    boolean updateCategory(IMovementCategory category);
}
