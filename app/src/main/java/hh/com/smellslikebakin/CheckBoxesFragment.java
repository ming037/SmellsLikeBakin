package hh.com.smellslikebakin;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.LinearLayout;

/**
 * Created by Administrator on 2017-02-16.
 */

public class CheckBoxesFragment extends Fragment {
    private static final String KEY_CHECKBOXES = "key_checkboxes";
    private CheckBox[] mCheckBoxes;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        int index = getArguments().getInt(ViewPagerFragment.KEY_RECIPE_INDEX);
        boolean isIngredients = getArguments().getBoolean(ViewPagerFragment.KEY_IS_INGREDIENTS);

        View view = inflater.inflate(R.layout.fragment_checkboxes, container, false);

        LinearLayout linearLayout = (LinearLayout) view.findViewById(R.id.checkBoxesLayout);

        String[] contents;
        if(isIngredients) contents = Recipes.ingredients[index].split("`");
        else contents = Recipes.directions[index].split("`");

        mCheckBoxes = new CheckBox[contents.length];
        boolean[] checkedBoxes = new boolean[mCheckBoxes.length];
        if(savedInstanceState != null && savedInstanceState.getBooleanArray(KEY_CHECKBOXES) != null)
        {
            checkedBoxes = savedInstanceState.getBooleanArray(KEY_CHECKBOXES);
        }

        setUpCheckBoxes(contents, linearLayout, checkedBoxes);
        return view;
    }

    private void setUpCheckBoxes(String[] contents, ViewGroup container, boolean[] checkedBoxes)
    {
        int i = 0;
        for(String content: contents)
        {
            mCheckBoxes[i] = new CheckBox(getActivity());
            mCheckBoxes[i].setPadding(8,16,8,16);
            mCheckBoxes[i].setTextSize(20f);
            mCheckBoxes[i].setText(content);
            container.addView(mCheckBoxes[i]);
            if(checkedBoxes[i]) mCheckBoxes[i].toggle();
            i++;
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        boolean[] stateOfCheckBoxes = new boolean[mCheckBoxes.length];
        int i = 0;
        for(CheckBox checkBox: mCheckBoxes)
        {
            stateOfCheckBoxes[i] = checkBox.isChecked();
            i++;
        }
        outState.putBooleanArray(KEY_CHECKBOXES, stateOfCheckBoxes);
        super.onSaveInstanceState(outState);
    }
}
