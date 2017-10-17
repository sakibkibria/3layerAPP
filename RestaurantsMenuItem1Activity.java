

package io.buildup.pkg20171016225815.ui;

import android.os.Bundle;
import android.support.v4.app.Fragment;

import io.buildup.pkg20171016225815.R;

import buildup.ui.BaseListingActivity;
/**
 * RestaurantsMenuItem1Activity list activity
 */
public class RestaurantsMenuItem1Activity extends BaseListingActivity {

    @Override
    protected void onCreate(Bundle savedInstance) {
        super.onCreate(savedInstance);

        if(isTaskRoot()) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        } else {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        
        setTitle(getString(R.string.restaurantsMenuItem1Activity));
    }

    @Override
    protected Class<? extends Fragment> getFragmentClass() {
        return RestaurantsMenuItem1Fragment.class;
    }

}
