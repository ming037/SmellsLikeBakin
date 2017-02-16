package hh.com.smellslikebakin;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity
        implements ListFragment.OnRecipeSelectedInterface, GridFragment.OnRecipeSelectedInterface{
    public static final String LIST_FRAGMENT ="list_fragment";
    public static final String VIEWPAGER_FRAGMENT ="viewpager_fragment";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        boolean isTablet = getResources().getBoolean(R.bool.is_tablet);

        if(!isTablet)
        {
            ListFragment savedFragment = (ListFragment) getFragmentManager()
                    .findFragmentByTag(LIST_FRAGMENT); //findViewbyID로 하면 회전할 때 에러남
            //fragment를 찾을 수 없으면 savedFragment에 null이 저장된다.

            if(savedFragment == null) {
                ListFragment fragment = new ListFragment();
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

                fragmentTransaction.add(R.id.placeHolder, fragment, LIST_FRAGMENT);//3번째 인자는 tag

                fragmentTransaction.commit();
            }
        }
        else
        {
            GridFragment savedFragment = (GridFragment) getFragmentManager()
                    .findFragmentByTag(LIST_FRAGMENT); //findViewbyID로 하면 회전할 때 에러남
            //fragment를 찾을 수 없으면 savedFragment에 null이 저장된다.

            if(savedFragment == null) {
                GridFragment fragment = new GridFragment();
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

                fragmentTransaction.add(R.id.placeHolder, fragment, LIST_FRAGMENT);//3번째 인자는 tag

                fragmentTransaction.commit();
            }
        }



    }

    @Override
    public void onListRecipeSelected(int index) {

        ViewPagerFragment fragment = new ViewPagerFragment();

        Bundle bundle = new Bundle();
        bundle.putInt(ViewPagerFragment.KEY_RECIPE_INDEX, index);
        fragment.setArguments(bundle);


        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        fragmentTransaction.replace(R.id.placeHolder, fragment,VIEWPAGER_FRAGMENT); //3번째 인자는 tag
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    @Override
    public void onGridRecipeSelected(int index) {

        DualPaneFragment fragment = new DualPaneFragment();

        Bundle bundle = new Bundle();
        bundle.putInt(ViewPagerFragment.KEY_RECIPE_INDEX, index);
        fragment.setArguments(bundle);


        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        fragmentTransaction.replace(R.id.placeHolder, fragment,VIEWPAGER_FRAGMENT); //3번째 인자는 tag
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();

    }
}
