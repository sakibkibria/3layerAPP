
package io.buildup.pkg20171016225815.ui;

import android.support.v4.app.Fragment;
import android.util.SparseArray;

import buildup.ui.DrawerActivity;

import buildup.actions.StartActivityAction;
import buildup.util.Constants;
import io.buildup.pkg20171016225815.R;

public class VeganappSKMainActivity extends DrawerActivity {

    private final SparseArray<Class<? extends Fragment>> sectionFragments = new SparseArray<>();
    {
            sectionFragments.append(R.id.entry0, RestaurantsMenuItem1Fragment.class);
            sectionFragments.append(R.id.entry1, ChiptoleFragment.class);
            sectionFragments.append(R.id.entry2, StarbucksMenuItem1Fragment.class);
            sectionFragments.append(R.id.entry3, SubwayMenuItem1Fragment.class);
    }

    @Override
    public SparseArray<Class<? extends Fragment>> getSectionFragmentClasses() {
      return sectionFragments;
    }

}
