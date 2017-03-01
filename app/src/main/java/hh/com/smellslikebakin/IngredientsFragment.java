package hh.com.smellslikebakin;

/**
 * Created by Administrator on 2017-02-17.
 */

public class IngredientsFragment extends CheckBoxesFragment {
    @Override
    public String[] getContents(int index) {
        return Recipes.ingredients[index].split("`");
    }
}
