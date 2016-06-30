package arturvasilov.udacity.nanodegree.popularmoviesnew.binding;

import android.databinding.BindingAdapter;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;

import arturvasilov.udacity.nanodegree.popularmoviesnew.widget.BaseAdapter;
import arturvasilov.udacity.nanodegree.popularmoviesnew.widget.EmptyRecyclerView;

/**
 * @author Artur Vasilov
 */
public class RecyclerViewAdapters {

    @BindingAdapter({"app:layoutManager", "app:adapter"})
    public static void setItems(@NonNull EmptyRecyclerView recyclerView,
                                @NonNull RecyclerView.LayoutManager manager,
                                @NonNull BaseAdapter adapter) {
        recyclerView.setLayoutManager(manager);
        adapter.attachToRecyclerView(recyclerView);
    }

}
