package hh.com.smellslikebakin;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Administrator on 2017-02-17.
 */

public class GridFragment extends Fragment {
        public interface OnRecipeSelectedInterface
        {
            void onGridRecipeSelected(int index); //MainActivity에서 구현
        }
        @Nullable
        @Override
        public View onCreateView (LayoutInflater inflater, ViewGroup container, Bundle
        savedInstanceState){

            OnRecipeSelectedInterface listener = (OnRecipeSelectedInterface) getActivity();
            View view = inflater.inflate(R.layout.fragment_recyclerview, container, false);

            RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.RecyclerView);
            GridAdapter gridAdapter = new GridAdapter(listener);
            recyclerView.setAdapter(gridAdapter);

            DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
            float dpWidth = displayMetrics.widthPixels / displayMetrics.density;
            int numColums = (int)(dpWidth/200);
            RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getActivity(), numColums);
            recyclerView.setLayoutManager(layoutManager);
            return view;
        }
}
