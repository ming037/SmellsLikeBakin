package hh.com.smellslikebakin;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements ListFragment.OnRecipeSelectedInterface{
    public static final String LIST_FRAGMENT ="list_fragment";
    public static final String VIEWPAGER_FRAGMENT ="viewpager_fragment";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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

    @Override
    public void onListRecipeSelected(int index) {
        Toast.makeText(this, Recipes.names [index], Toast.LENGTH_SHORT).show();
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
}
