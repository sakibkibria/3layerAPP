

package io.buildup.pkg20171016225815.ui;

import android.os.Bundle;

import io.buildup.pkg20171016225815.R;

import java.util.ArrayList;
import java.util.List;

import buildup.MenuItem;

import buildup.actions.StartActivityAction;
import buildup.util.Constants;

/**
 * RestaurantsMenuItem1Fragment menu fragment.
 */
public class RestaurantsMenuItem1Fragment extends buildup.ui.MenuFragment {
    /**
     * Default constructor
     */
    public RestaurantsMenuItem1Fragment(){
        super();
    }

    // Factory method
    public static RestaurantsMenuItem1Fragment newInstance(Bundle args) {
        RestaurantsMenuItem1Fragment fragment = new RestaurantsMenuItem1Fragment();

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    // Menu Fragment interface
    @Override
    public List<MenuItem> getMenuItems() {
        ArrayList<MenuItem> items = new ArrayList<MenuItem>();
        items.add(new MenuItem()
            .setLabel("Chipotle")
            .setIcon(R.drawable.png_defaultmenuicon)
            .setAction(new StartActivityAction(ChiptoleActivity.class, Constants.DETAIL))
        );
        items.add(new MenuItem()
            .setLabel("Starbucks")
            .setIcon(R.drawable.png_defaultmenuicon)
            .setAction(new StartActivityAction(StarbucksMenuItem1Activity.class, Constants.DETAIL))
        );
        items.add(new MenuItem()
            .setLabel("Subway")
            .setIcon(R.drawable.png_defaultmenuicon)
            .setAction(new StartActivityAction(SubwayMenuItem1Activity.class, Constants.DETAIL))
        );
        return items;
    }

    @Override
    public int getLayout() {
        return R.layout.fragment_list;
    }

    @Override
    public int getItemLayout() {
        return R.layout.restaurantsmenuitem1_item;
    }
}
