

package io.buildup.pkg20171016225815.ui;

import android.os.Bundle;
import android.support.v4.app.Fragment;

import io.buildup.pkg20171016225815.R;

import buildup.ui.BaseListingActivity;
/**
 * ChiptoleActivity list activity
 */
public class ChiptoleActivity extends BaseListingActivity {

    @Override
    protected void onCreate(Bundle savedInstance) {
        super.onCreate(savedInstance);

        if(isTaskRoot()) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        } else {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        
        setTitle(getString(R.string.chiptoleActivity));
    }

    @Override
    protected Class<? extends Fragment> getFragmentClass() {
        return ChiptoleFragment.class;
    }

}
