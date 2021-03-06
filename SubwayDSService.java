
package io.buildup.pkg20171016225815.ds;
import java.net.URL;
import io.buildup.pkg20171016225815.R;
import buildup.ds.RestService;
import buildup.util.StringUtils;

/**
 * "SubwayDSService" REST Service implementation
 */
public class SubwayDSService extends RestService<SubwayDSServiceRest>{

    public static SubwayDSService getInstance(){
          return new SubwayDSService();
    }

    private SubwayDSService() {
        super(SubwayDSServiceRest.class);

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
