package io.buildup.pkg20171016225815.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import buildup.behaviors.SearchBehavior;
import buildup.ds.Datasource;
import buildup.ui.ListGridFragment;
import buildup.util.ViewHolder;
import io.buildup.pkg20171016225815.R;
import buildup.ds.SearchOptions;
import buildup.ds.filter.Filter;
import java.util.Arrays;
import io.buildup.pkg20171016225815.ds.ChipotleDSItem;
import io.buildup.pkg20171016225815.ds.ChipotleDS;
import android.content.Intent;
import buildup.util.Constants;
import static buildup.util.NavigationUtils.generateIntentToDetailOrForm;

/**
 * "ChiptoleFragment" listing
 */
public class ChiptoleFragment extends ListGridFragment<ChipotleDSItem>  {

    private Datasource<ChipotleDSItem> datasource;


    public static ChiptoleFragment newInstance(Bundle args) {
        ChiptoleFragment fr = new ChiptoleFragment();

        fr.setArguments(args);
        return fr;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addBehavior(new SearchBehavior(this));
        
    }

    protected SearchOptions getSearchOptions() {
        SearchOptions.Builder searchOptionsBuilder = SearchOptions.Builder.searchOptions();
        return searchOptionsBuilder.build();
    }


    /**
    * Layout for the list itselft
    */
    @Override
    protected int getLayout() {
        return R.layout.fragment_list;
    }

    /**
    * Layout for each element in the list
    */
    @Override
    protected int getItemLayout() {
        return R.layout.chiptole_item;
    }

    @Override
    protected Datasource<ChipotleDSItem> getDatasource() {
        if (datasource != null) {
            return datasource;
        }
        datasource = ChipotleDS.getInstance(getSearchOptions());
        return datasource;
    }

    @Override
    protected void bindView(ChipotleDSItem item, View view, int position) {
        
        TextView subtitle = ViewHolder.get(view, R.id.subtitle);
        
        subtitle.setText((item.text1 != null ? item.text1 : ""));
        
    }


    @Override
    public void showDetail(ChipotleDSItem item, int position) {
        Intent intent = generateIntentToDetailOrForm(item,
            position,
            getActivity(),
            ChiptoleDetailActivity.class);

        if (!getResources().getBoolean(R.bool.tabletLayout)) {
            startActivityForResult(intent, Constants.DETAIL);
        } else {
            startActivity(intent);
        }
    }

}
