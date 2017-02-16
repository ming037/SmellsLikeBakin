package hh.com.smellslikebakin;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v13.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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
        getActivity().setTitle(Recipes.names[index]);
        View view = inflater.inflate(R.layout.fragment_viewpager, container,false);

        final IngredientsFragment ingredientsFragment = new IngredientsFragment();
        final DirectionsFragment directionsFragment = new DirectionsFragment();


        //ingredientsFragment에 인덱스 넘기기
        Bundle bundle = new Bundle();
        bundle.putInt(KEY_RECIPE_INDEX, index);

        ingredientsFragment.setArguments(bundle);

        //directionsFragment에 인덱스 넘기기
        bundle = new Bundle();
        bundle.putInt(KEY_RECIPE_INDEX, index);

        directionsFragment.setArguments(bundle);

        ViewPager viewPager = (ViewPager) view.findViewById(R.id.viewPager);
        viewPager.setAdapter(new FragmentPagerAdapter(getChildFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                 return position == 0? ingredientsFragment: directionsFragment;
            }

            @Override
            public int getCount() {
                return 2;
            }

            @Override
            public CharSequence getPageTitle(int position)  //setting page title, must use when making tab layout
            {
                return position == 0? "Ingredients" : "Directions";
            }
        });

        TabLayout tabLayout = (TabLayout) view.findViewById(R.id.tabLayout);
        tabLayout.setupWithViewPager(viewPager);
        return view;
    }
    @Override
    public void onStop() {
        super.onStop();
        getActivity().setTitle(getResources().getString(R.string.app_name));
    }
}
