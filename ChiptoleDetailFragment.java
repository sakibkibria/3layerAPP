
package io.buildup.pkg20171016225815.ui;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import buildup.behaviors.ShareBehavior;
import buildup.ds.restds.AppNowDatasource;
import buildup.util.image.ImageLoader;
import buildup.util.image.PicassoImageLoader;
import buildup.util.StringUtils;
import io.buildup.pkg20171016225815.R;
import java.net.URL;
import static buildup.util.image.ImageLoaderRequest.Builder.imageLoaderRequest;
import buildup.ds.Datasource;
import buildup.ds.SearchOptions;
import buildup.ds.filter.Filter;
import java.util.Arrays;
import io.buildup.pkg20171016225815.ds.ChipotleDSItem;
import io.buildup.pkg20171016225815.ds.ChipotleDS;

public class ChiptoleDetailFragment extends buildup.ui.DetailFragment<ChipotleDSItem> implements ShareBehavior.ShareListener {

    private Datasource<ChipotleDSItem> datasource;
    public static ChiptoleDetailFragment newInstance(Bundle args){
        ChiptoleDetailFragment fr = new ChiptoleDetailFragment();
        fr.setArguments(args);

        return fr;
    }

    public ChiptoleDetailFragment(){
        super();
    }

    @Override
    public Datasource<ChipotleDSItem> getDatasource() {
        if (datasource != null) {
            return datasource;
        }
        datasource = ChipotleDS.getInstance(new SearchOptions());
        return datasource;
    }

    @Override
    public void onCreate(Bundle state) {
        super.onCreate(state);
        addBehavior(new ShareBehavior(getActivity(), this));

    }

    // Bindings

    @Override
    protected int getLayout() {
        return R.layout.chiptoledetail_detail;
    }

    @Override
    @SuppressLint("WrongViewCast")
    public void bindView(final ChipotleDSItem item, View view) {
        
        TextView view0 = (TextView) view.findViewById(R.id.view0);
        view0.setText((item.text1 != null ? item.text1 : ""));
        
        
        ImageView view1 = (ImageView) view.findViewById(R.id.view1);
        URL view1Media = ((AppNowDatasource) getDatasource()).getImageUrl(item.picture);
        if(view1Media != null){
            ImageLoader imageLoader = new PicassoImageLoader(view1.getContext(), false);
            imageLoader.load(imageLoaderRequest()
                                   .withPath(view1Media.toExternalForm())
                                   .withTargetView(view1)
                                   .fit()
                                   .build()
                    );
            
        } else {
            view1.setImageDrawable(null);
        }
        
        TextView view2 = (TextView) view.findViewById(R.id.view2);
        view2.setText((item.desc != null ? item.desc : ""));
        
    }

    @Override
    protected void onShow(ChipotleDSItem item) {
        // set the title for this fragment
        getActivity().setTitle("TOFU BOWL");
    }
    @Override
    public void onShare() {
        ChipotleDSItem item = getItem();

        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_SEND);
        intent.setType("text/plain");

        intent.putExtra(Intent.EXTRA_TEXT, (item.text1 != null ? item.text1 : "") + "\n" +
                    (item.desc != null ? item.desc : ""));
        intent.putExtra(Intent.EXTRA_SUBJECT, "TOFU BOWL");

        startActivityForResult(Intent.createChooser(intent, getString(R.string.share)), 1);
    }
}
