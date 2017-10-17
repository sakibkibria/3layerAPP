

package io.buildup.pkg20171016225815.ui;

import android.os.Bundle;
import android.support.v4.app.Fragment;

import io.buildup.pkg20171016225815.R;

import buildup.ui.BaseListingActivity;
/**
 * SubwayMenuItem1Activity list activity
 */
public class SubwayMenuItem1Activity extends BaseListingActivity {

    @Override
    protected void onCreate(Bundle savedInstance) {
        super.onCreate(savedInstance);

        if(isTaskRoot()) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        } else {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        
        setTitle(getString(R.string.subwayMenuItem1Activity));
    }

    @Override
    protected Class<? extends Fragment> getFragmentClass() {
        return SubwayMenuItem1Fragment.class;
    }

}
