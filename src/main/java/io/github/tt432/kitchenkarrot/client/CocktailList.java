package io.github.tt432.kitchenkarrot.client;

import java.util.ArrayList;
import java.util.List;

/**
 * @author DustW
 **/
public class CocktailList {
    public static final CocktailList INSTANCE = new CocktailList();

    private CocktailList() {}

    public List<String> cocktails = new ArrayList<>();
}
