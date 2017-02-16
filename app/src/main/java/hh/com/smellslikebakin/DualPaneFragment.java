package hh.com.smellslikebakin;

import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Administrator on 2017-02-17.
 */

public class DualPaneFragment extends Fragment {
    private static final String INGREDEINTS_FRAGMENTS = "INGREDEINTS_FRAGMENTS";
    private static final String DIRECTIONS_FRAGMENT = "DIRECTIONS_FRAGMENT";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        int index = getArguments().getInt(ViewPagerFragment.KEY_RECIPE_INDEX);
        getActivity().setTitle(Recipes.names[index]);
        View view = inflater.inflate(R.layout.fragment_dualpane, container,false);

        FragmentManager fragmentManager = getChildFragmentManager();

        //IngredientsFragement
        IngredientsFragment savedingredientsFragment = (IngredientsFragment) fragmentManager.findFragmentByTag(INGREDEINTS_FRAGMENTS);
        if(savedingredientsFragment == null)
        {
            IngredientsFragment ingredientsFragment = new IngredientsFragment();
            Bundle bundle = new Bundle();
            bundle.putInt(ViewPagerFragment.KEY_RECIPE_INDEX, index);
            ingredientsFragment.setArguments(bundle);
            fragmentManager.beginTransaction()
                    .add(R.id.leftPlaceholder, ingredientsFragment, INGREDEINTS_FRAGMENTS).commit();
        }

        //DirectionsFragment
        DirectionsFragment savedDirectionsFragment = (DirectionsFragment) fragmentManager.findFragmentByTag(DIRECTIONS_FRAGMENT);
        if(savedDirectionsFragment == null)
        {
            DirectionsFragment directionsFragment = new DirectionsFragment();
            Bundle bundle = new Bundle();
            bundle.putInt(ViewPagerFragment.KEY_RECIPE_INDEX, index);
            directionsFragment.setArguments(bundle);
            fragmentManager.beginTransaction()
                    .add(R.id.rightPlaceholder, directionsFragment, DIRECTIONS_FRAGMENT).commit();
        }

        return view;
    }
    @Override
    public void onStop() {
        super.onStop();
        getActivity().setTitle(getResources().getString(R.string.app_name));
    }
}
