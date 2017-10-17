
package io.buildup.pkg20171016225815.ds;
import java.net.URL;
import io.buildup.pkg20171016225815.R;
import buildup.ds.RestService;
import buildup.util.StringUtils;

/**
 * "ChipotleDSService" REST Service implementation
 */
public class ChipotleDSService extends RestService<ChipotleDSServiceRest>{

    public static ChipotleDSService getInstance(){
          return new ChipotleDSService();
    }

    private ChipotleDSService() {
        super(ChipotleDSServiceRest.class);

    }

    @Override
    public String getServerUrl() {
        return "https://pods.hivepod.io";
    }

    @Override
    protected String getApiKey() {
        return "M0w00fDv";
    }

    @Override
    public URL getImageUrl(String path){
        return StringUtils.parseUrl("https://pods.hivepod.io/app/59e539863b811c0400912c56",
                path,
                "apikey=M0w00fDv");
    }

}
