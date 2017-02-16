package hh.com.smellslikebakin;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

/**
 * Created by Administrator on 2017-02-16.
 */

public class ViewPagerFragment extends Fragment{
   public static final String KEY_RECIPE_INDEX = "recipe_index";


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        int index = getArguments().getInt(KEY_RECIPE_INDEX);

        Toast.makeText(getActivity(), Recipes.names[index], Toast.LENGTH_SHORT).show();
        View view = inflater.inflate(R.layout.fragment_viewpager,container,false);
        return view;
    }
}
