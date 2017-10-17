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
import io.buildup.pkg20171016225815.ds.SubwayDSItem;
import io.buildup.pkg20171016225815.ds.SubwayDS;
import android.content.Intent;
import buildup.util.Constants;
import static buildup.util.NavigationUtils.generateIntentToDetailOrForm;

/**
 * "SubwayMenuItem1Fragment" listing
 */
public class SubwayMenuItem1Fragment extends ListGridFragment<SubwayDSItem>  {

    private Datasource<SubwayDSItem> datasource;


    public static SubwayMenuItem1Fragment newInstance(Bundle args) {
        SubwayMenuItem1Fragment fr = new SubwayMenuItem1Fragment();

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
        return R.layout.subwaymenuitem1_item;
    }

    @Override
    protected Datasource<SubwayDSItem> getDatasource() {
        if (datasource != null) {
            return datasource;
        }
        datasource = SubwayDS.getInstance(getSearchOptions());
        return datasource;
    }

    @Override
    protected void bindView(SubwayDSItem item, View view, int position) {
        
        TextView title = ViewHolder.get(view, R.id.title);
        
        title.setText((item.text1 != null ? item.text1 : ""));
        
    }


    @Override
    public void showDetail(SubwayDSItem item, int position) {
        Intent intent = generateIntentToDetailOrForm(item,
            position,
            getActivity(),
            SubwayMenuItem1DetailActivity.class);

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
            Intent intent = new Intent(getActivity(), SubwayMenuItem1FilterActivity.class);

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
