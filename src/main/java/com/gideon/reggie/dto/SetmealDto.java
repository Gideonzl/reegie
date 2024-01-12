package com.gideon.reggie.dto;

import com.gideon.reggie.entity.Setmeal;
import com.gideon.reggie.entity.SetmealDish;
import lombok.Data;
import java.util.List;

@Data
public class SetmealDto extends Setmeal {

    private List<SetmealDish> setmealDishes;

    private String categoryName;
}
