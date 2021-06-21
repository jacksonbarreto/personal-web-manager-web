package com.jacksonleonardo.unpaper.model.DTO;

import com.jacksonleonardo.unpaper.model.entities.IMovementCategory;
import com.jacksonleonardo.unpaper.model.entities.MovementCategory;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class CategoryDTO {
    @NotBlank
    @NotNull
    @Size(min=3)
    private String name;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public IMovementCategory toMovementCategory(){
        return new MovementCategory(this.name);
    }

    public void fromMovementCategory(IMovementCategory movementCategory){
        this.name = movementCategory.getName();
    }

    @Override
    public String toString() {
        return "CategoryDTO{" +
                "name='" + name + '\'' +
                '}';
    }
}
