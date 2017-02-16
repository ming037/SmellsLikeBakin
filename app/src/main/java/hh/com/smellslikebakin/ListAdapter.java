package hh.com.smellslikebakin;

/**
 * Created by Administrator on 2017-02-16.
 */

public class ListAdapter extends RecyclerAdapter
{
    private final ListFragment.OnRecipeSelectedInterface mListener;

    public ListAdapter(ListFragment.OnRecipeSelectedInterface listener) {
        mListener = listener;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.list_item;
    }

    @Override
    protected void onListRecipeSelected(int index) {
        mListener.onListRecipeSelected(index);
    }

}
