package io.buildup.pkg20171016225815.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import buildup.behaviors.SearchBehavior;
import buildup.ds.Datasource;
import buildup.ui.ListGridFragment;
import buildup.util.ColorUtils;
import buildup.util.ViewHolder;
import io.buildup.pkg20171016225815.R;
import buildup.ds.SearchOptions;
import buildup.ds.filter.Filter;
import java.util.Arrays;
import io.buildup.pkg20171016225815.ds.StarbucksDSItem;
import io.buildup.pkg20171016225815.ds.StarbucksDS;
import android.content.Intent;
import buildup.util.Constants;
import static buildup.util.NavigationUtils.generateIntentToDetailOrForm;

/**
 * "StarbucksMenuItem1Fragment" listing
 */
public class StarbucksMenuItem1Fragment extends ListGridFragment<StarbucksDSItem>  {

    private Datasource<StarbucksDSItem> datasource;


    public static StarbucksMenuItem1Fragment newInstance(Bundle args) {
        StarbucksMenuItem1Fragment fr = new StarbucksMenuItem1Fragment();

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
        return R.layout.starbucksmenuitem1_item;
    }

    @Override
    protected Datasource<StarbucksDSItem> getDatasource() {
        if (datasource != null) {
            return datasource;
        }
        datasource = StarbucksDS.getInstance(getSearchOptions());
        return datasource;
    }

    @Override
    protected void bindView(StarbucksDSItem item, View view, int position) {
    }


    @Override
    public void showDetail(StarbucksDSItem item, int position) {
        Intent intent = generateIntentToDetailOrForm(item,
            position,
            getActivity(),
            StarbucksMenuItem1DetailActivity.class);

        if (!getResources().getBoolean(R.bool.tabletLayout)) {
            startActivityForResult(intent, Constants.DETAIL);
        } else {
            startActivity(intent);
        }
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);

        // inflate menu options and tint icon
        inflater.inflate(R.menu.filter_menu, menu);
        ColorUtils.tintIcon(menu.findItem(R.id.filter),
                            R.color.textBarColor,
                            getActivity());
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.filter){
            Intent intent = new Intent(getActivity(), StarbucksMenuItem1FilterActivity.class);

            // pass current values to filter activity
            Bundle args = new Bundle();
            args.putParcelable("search_options", getSearchOptions());
			intent.putExtras(args);
            // launch filter screen
            startActivityForResult(intent, 1);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == 1 && resultCode == Activity.RESULT_OK){
            // store the incoming selection
                        // apply filter to datasource
            clearFilters();

                        // and finally refresh the list
            refresh();

            // and redraw menu (to refresh tinted icons, like the search icon)
            getActivity().invalidateOptionsMenu();
        }
    }
}
