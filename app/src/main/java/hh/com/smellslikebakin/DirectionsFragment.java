package hh.com.smellslikebakin;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.LinearLayout;

/**
 * Created by Administrator on 2017-02-16.
 */

public class DirectionsFragment extends Fragment {
    private static final String KEY_CHECKBOXES = "key_checkboxes";
    private CheckBox[] mCheckBoxes;
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        int index= getArguments().getInt(ViewPagerFragment.KEY_RECIPE_INDEX);
        View view = inflater.inflate(R.layout.fragment_directions, container, false);

        LinearLayout linearLayout = (LinearLayout) view.findViewById(R.id.directionsLayout);
        String[] directions = Recipes.directions[index].split("`");

        mCheckBoxes = new CheckBox[directions.length];
        boolean[] checkedBoxes = new boolean[mCheckBoxes.length];
        if(savedInstanceState != null && savedInstanceState.getBooleanArray(KEY_CHECKBOXES) != null)
        {
            checkedBoxes = savedInstanceState.getBooleanArray(KEY_CHECKBOXES);
        }

        setUpCheckBoxes(directions, linearLayout, checkedBoxes);
        return view;
    }

    private void setUpCheckBoxes(String[] directions, ViewGroup container, boolean[] checkedBoxes)
    {
        int i = 0;
        for(String direction: directions)
        {
            mCheckBoxes[i] = new CheckBox(getActivity());
            mCheckBoxes[i].setPadding(8,16,8,16);
            mCheckBoxes[i].setTextSize(20f);
            mCheckBoxes[i].setText(direction);
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
