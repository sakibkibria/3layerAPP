

package io.buildup.pkg20171016225815.ds;

import android.content.Context;

import java.net.URL;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

import buildup.ds.SearchOptions;
import buildup.ds.restds.AppNowDatasource;
import buildup.util.StringUtils;
import buildup.ds.restds.TypedByteArrayUtils;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * "SubwayDS" data source. (e37eb8dc-6eb2-4635-8592-5eb9696050e3)
 */
public class SubwayDS extends AppNowDatasource<SubwayDSItem>{

    // default page size
    private static final int PAGE_SIZE = 20;

    private SubwayDSService service;

    public static SubwayDS getInstance(SearchOptions searchOptions){
        return new SubwayDS(searchOptions);
    }

    private SubwayDS(SearchOptions searchOptions) {
        super(searchOptions);
        this.service = SubwayDSService.getInstance();
    }

    @Override
    public void getItem(String id, final Listener<SubwayDSItem> listener) {
        if ("0".equals(id)) {
                        getItems(new Listener<List<SubwayDSItem>>() {
                @Override
                public void onSuccess(List<SubwayDSItem> items) {
                    if(items != null && items.size() > 0) {
                        listener.onSuccess(items.get(0));
                    } else {
                        listener.onSuccess(new SubwayDSItem());
                    }
                }

                @Override
                public void onFailure(Exception e) {
                    listener.onFailure(e);
                }
            });
        } else {
                      service.getServiceProxy().getSubwayDSItemById(id, new Callback<SubwayDSItem>() {
                @Override
                public void success(SubwayDSItem result, Response response) {
                                        listener.onSuccess(result);
                }

                @Override
                public void failure(RetrofitError error) {
                                        listener.onFailure(error);
                }
            });
        }
    }

    @Override
    public void getItems(final Listener<List<SubwayDSItem>> listener) {
        getItems(0, listener);
    }

    @Override
    public void getItems(int pagenum, final Listener<List<SubwayDSItem>> listener) {
        String conditions = getConditions(searchOptions, getSearchableFields());
        int skipNum = pagenum * PAGE_SIZE;
        String skip = skipNum == 0 ? null : String.valueOf(skipNum);
        String limit = PAGE_SIZE == 0 ? null: String.valueOf(PAGE_SIZE);
        String sort = getSort(searchOptions);
                service.getServiceProxy().querySubwayDSItem(
                skip,
                limit,
                conditions,
                sort,
                null,
                null,
                new Callback<List<SubwayDSItem>>() {
            @Override
            public void success(List<SubwayDSItem> result, Response response) {
                                listener.onSuccess(result);
            }

            @Override
            public void failure(RetrofitError error) {
                                listener.onFailure(error);
            }
        });
    }

    private String[] getSearchableFields() {
        return new String[]{"text1", "desc"};
    }

    // Pagination

    @Override
    public int getPageSize(){
        return PAGE_SIZE;
    }

    @Override
    public void getUniqueValuesFor(String searchStr, final Listener<List<String>> listener) {
        String conditions = getConditions(searchOptions, getSearchableFields());
                service.getServiceProxy().distinct(searchStr, conditions, new Callback<List<String>>() {
             @Override
             public void success(List<String> result, Response response) {
                                  result.removeAll(Collections.<String>singleton(null));
                 listener.onSuccess(result);
             }

             @Override
             public void failure(RetrofitError error) {
                                  listener.onFailure(error);
             }
        });
    }

    @Override
    public URL getImageUrl(String path) {
        return service.getImageUrl(path);
    }

    @Override
    public void create(SubwayDSItem item, Listener<SubwayDSItem> listener) {
                    
        if(item.pictureUri != null){
            service.getServiceProxy().createSubwayDSItem(item,
                TypedByteArrayUtils.fromUri(item.pictureUri),
                callbackFor(listener));
        }
        else
            service.getServiceProxy().createSubwayDSItem(item, callbackFor(listener));
        
    }

    private Callback<SubwayDSItem> callbackFor(final Listener<SubwayDSItem> listener) {
      return new Callback<SubwayDSItem>() {
          @Override
          public void success(SubwayDSItem item, Response response) {
                            listener.onSuccess(item);
          }

          @Override
          public void failure(RetrofitError error) {
                            listener.onFailure(error);
          }
      };
    }

    @Override
    public void updateItem(SubwayDSItem item, Listener<SubwayDSItem> listener) {
                    
        if(item.pictureUri != null){
            service.getServiceProxy().updateSubwayDSItem(item.getIdentifiableId(),
                item,
                TypedByteArrayUtils.fromUri(item.pictureUri),
                callbackFor(listener));
        }
        else
            service.getServiceProxy().updateSubwayDSItem(item.getIdentifiableId(), item, callbackFor(listener));
        
    }

    @Override
    public void deleteItem(SubwayDSItem item, final Listener<SubwayDSItem> listener) {
                service.getServiceProxy().deleteSubwayDSItemById(item.getIdentifiableId(), new Callback<SubwayDSItem>() {
            @Override
            public void success(SubwayDSItem result, Response response) {
                                listener.onSuccess(result);
            }

            @Override
            public void failure(RetrofitError error) {
                                listener.onFailure(error);
            }
        });
    }

    @Override
    public void deleteItems(List<SubwayDSItem> items, final Listener<SubwayDSItem> listener) {
                service.getServiceProxy().deleteByIds(collectIds(items), new Callback<List<SubwayDSItem>>() {
            @Override
            public void success(List<SubwayDSItem> item, Response response) {
                                listener.onSuccess(null);
            }

            @Override
            public void failure(RetrofitError error) {
                                listener.onFailure(error);
            }
        });
    }

    protected List<String> collectIds(List<SubwayDSItem> items){
        List<String> ids = new ArrayList<>();
        for(SubwayDSItem item: items){
            ids.add(item.getIdentifiableId());
        }
        return ids;
    }

}
