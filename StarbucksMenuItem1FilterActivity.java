

package io.buildup.pkg20171016225815.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.util.Arrays;

import io.buildup.pkg20171016225815.R;

import buildup.ui.BaseFragment;
import buildup.ui.FilterActivity;
import buildup.ds.SearchOptions;
import buildup.ds.filter.Filter;


/**
 * StarbucksMenuItem1FilterActivity filter activity
 */
public class StarbucksMenuItem1FilterActivity extends FilterActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if(isTaskRoot()) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        } else {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        // set title
        setTitle(R.string.starbucksMenuItem1FilterActivity);
    }

    @Override
    protected Fragment getFragment() {
        return new PlaceholderFragment();
    }

    public static class PlaceholderFragment extends BaseFragment {
        private SearchOptions.Builder searchOptionsBuilder = SearchOptions.Builder.searchOptions();
        private SearchOptions searchOptions;


        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            return inflater.inflate(R.layout.starbucksmenuitem1_filter, container, false);
        }

        @Override
        public void onViewCreated(View view, Bundle savedInstanceState) {
            super.onViewCreated(view, savedInstanceState);

            // Get saved values
            Bundle bundle = savedInstanceState;
            if(bundle == null) {
                bundle = getArguments();
            }
            searchOptions = bundle.getParcelable("search_options");

            // get initial data
            // bind pickers
            // Bind buttons
            Button okBtn = (Button) view.findViewById(R.id.ok);
            okBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent();

                    // send filter result back to caller
                    
                    getActivity().setResult(RESULT_OK, intent);
                    getActivity().finish();
                }
            });

            Button cancelBtn = (Button) view.findViewById(R.id.reset);
            cancelBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Reset values
                                    }
            });
        }

        @Override
        public void onSaveInstanceState(Bundle bundle) {
            super.onSaveInstanceState(bundle);

            // save current status
            bundle.putParcelable("search_options", searchOptions);
        }
    }

}
