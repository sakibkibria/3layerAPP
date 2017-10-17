

package io.buildup.pkg20171016225815.ui;

import android.os.Bundle;
import android.support.v4.app.Fragment;

import buildup.ui.BaseDetailActivity;

/**
 * ChiptoleDetailActivity detail activity
 */
public class ChiptoleDetailActivity extends BaseDetailActivity {

  	@Override
    protected void onCreate(Bundle savedState) {
        super.onCreate(savedState);

        if(isTaskRoot()) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        } else {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }

    @Override
    protected Class<? extends Fragment> getFragmentClass() {
        return ChiptoleDetailFragment.class;
    }
}

