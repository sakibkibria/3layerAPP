

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
 * "StarbucksDS" data source. (e37eb8dc-6eb2-4635-8592-5eb9696050e3)
 */
public class StarbucksDS extends AppNowDatasource<StarbucksDSItem>{

    // default page size
    private static final int PAGE_SIZE = 20;

    private StarbucksDSService service;

    public static StarbucksDS getInstance(SearchOptions searchOptions){
        return new StarbucksDS(searchOptions);
    }

    private StarbucksDS(SearchOptions searchOptions) {
        super(searchOptions);
        this.service = StarbucksDSService.getInstance();
    }

    @Override
    public void getItem(String id, final Listener<StarbucksDSItem> listener) {
        if ("0".equals(id)) {
                        getItems(new Listener<List<StarbucksDSItem>>() {
                @Override
                public void onSuccess(List<StarbucksDSItem> items) {
                    if(items != null && items.size() > 0) {
                        listener.onSuccess(items.get(0));
                    } else {
                        listener.onSuccess(new StarbucksDSItem());
                    }
                }

                @Override
                public void onFailure(Exception e) {
                    listener.onFailure(e);
                }
            });
        } else {
                      service.getServiceProxy().getStarbucksDSItemById(id, new Callback<StarbucksDSItem>() {
                @Override
                public void success(StarbucksDSItem result, Response response) {
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
    public void getItems(final Listener<List<StarbucksDSItem>> listener) {
        getItems(0, listener);
    }

    @Override
    public void getItems(int pagenum, final Listener<List<StarbucksDSItem>> listener) {
        String conditions = getConditions(searchOptions, getSearchableFields());
        int skipNum = pagenum * PAGE_SIZE;
        String skip = skipNum == 0 ? null : String.valueOf(skipNum);
        String limit = PAGE_SIZE == 0 ? null: String.valueOf(PAGE_SIZE);
        String sort = getSort(searchOptions);
                service.getServiceProxy().queryStarbucksDSItem(
                skip,
                limit,
                conditions,
                sort,
                null,
                null,
                new Callback<List<StarbucksDSItem>>() {
            @Override
            public void success(List<StarbucksDSItem> result, Response response) {
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
    public void create(StarbucksDSItem item, Listener<StarbucksDSItem> listener) {
                    
        if(item.pictureUri != null){
            service.getServiceProxy().createStarbucksDSItem(item,
                TypedByteArrayUtils.fromUri(item.pictureUri),
                callbackFor(listener));
        }
        else
            service.getServiceProxy().createStarbucksDSItem(item, callbackFor(listener));
        
    }

    private Callback<StarbucksDSItem> callbackFor(final Listener<StarbucksDSItem> listener) {
      return new Callback<StarbucksDSItem>() {
          @Override
          public void success(StarbucksDSItem item, Response response) {
                            listener.onSuccess(item);
          }

          @Override
          public void failure(RetrofitError error) {
                            listener.onFailure(error);
          }
      };
    }

    @Override
    public void updateItem(StarbucksDSItem item, Listener<StarbucksDSItem> listener) {
                    
        if(item.pictureUri != null){
            service.getServiceProxy().updateStarbucksDSItem(item.getIdentifiableId(),
                item,
                TypedByteArrayUtils.fromUri(item.pictureUri),
                callbackFor(listener));
        }
        else
            service.getServiceProxy().updateStarbucksDSItem(item.getIdentifiableId(), item, callbackFor(listener));
        
    }

    @Override
    public void deleteItem(StarbucksDSItem item, final Listener<StarbucksDSItem> listener) {
                service.getServiceProxy().deleteStarbucksDSItemById(item.getIdentifiableId(), new Callback<StarbucksDSItem>() {
            @Override
            public void success(StarbucksDSItem result, Response response) {
                                listener.onSuccess(result);
            }

            @Override
            public void failure(RetrofitError error) {
                                listener.onFailure(error);
            }
        });
    }

    @Override
    public void deleteItems(List<StarbucksDSItem> items, final Listener<StarbucksDSItem> listener) {
                service.getServiceProxy().deleteByIds(collectIds(items), new Callback<List<StarbucksDSItem>>() {
            @Override
            public void success(List<StarbucksDSItem> item, Response response) {
                                listener.onSuccess(null);
            }

            @Override
            public void failure(RetrofitError error) {
                                listener.onFailure(error);
            }
        });
    }

    protected List<String> collectIds(List<StarbucksDSItem> items){
        List<String> ids = new ArrayList<>();
        for(StarbucksDSItem item: items){
            ids.add(item.getIdentifiableId());
        }
        return ids;
    }

}
