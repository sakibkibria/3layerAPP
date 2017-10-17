

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
 * "ChipotleDS" data source. (e37eb8dc-6eb2-4635-8592-5eb9696050e3)
 */
public class ChipotleDS extends AppNowDatasource<ChipotleDSItem>{

    // default page size
    private static final int PAGE_SIZE = 20;

    private ChipotleDSService service;

    public static ChipotleDS getInstance(SearchOptions searchOptions){
        return new ChipotleDS(searchOptions);
    }

    private ChipotleDS(SearchOptions searchOptions) {
        super(searchOptions);
        this.service = ChipotleDSService.getInstance();
    }

    @Override
    public void getItem(String id, final Listener<ChipotleDSItem> listener) {
        if ("0".equals(id)) {
                        getItems(new Listener<List<ChipotleDSItem>>() {
                @Override
                public void onSuccess(List<ChipotleDSItem> items) {
                    if(items != null && items.size() > 0) {
                        listener.onSuccess(items.get(0));
                    } else {
                        listener.onSuccess(new ChipotleDSItem());
                    }
                }

                @Override
                public void onFailure(Exception e) {
                    listener.onFailure(e);
                }
            });
        } else {
                      service.getServiceProxy().getChipotleDSItemById(id, new Callback<ChipotleDSItem>() {
                @Override
                public void success(ChipotleDSItem result, Response response) {
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
    public void getItems(final Listener<List<ChipotleDSItem>> listener) {
        getItems(0, listener);
    }

    @Override
    public void getItems(int pagenum, final Listener<List<ChipotleDSItem>> listener) {
        String conditions = getConditions(searchOptions, getSearchableFields());
        int skipNum = pagenum * PAGE_SIZE;
        String skip = skipNum == 0 ? null : String.valueOf(skipNum);
        String limit = PAGE_SIZE == 0 ? null: String.valueOf(PAGE_SIZE);
        String sort = getSort(searchOptions);
                service.getServiceProxy().queryChipotleDSItem(
                skip,
                limit,
                conditions,
                sort,
                null,
                null,
                new Callback<List<ChipotleDSItem>>() {
            @Override
            public void success(List<ChipotleDSItem> result, Response response) {
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
    public void create(ChipotleDSItem item, Listener<ChipotleDSItem> listener) {
                    
        if(item.pictureUri != null){
            service.getServiceProxy().createChipotleDSItem(item,
                TypedByteArrayUtils.fromUri(item.pictureUri),
                callbackFor(listener));
        }
        else
            service.getServiceProxy().createChipotleDSItem(item, callbackFor(listener));
        
    }

    private Callback<ChipotleDSItem> callbackFor(final Listener<ChipotleDSItem> listener) {
      return new Callback<ChipotleDSItem>() {
          @Override
          public void success(ChipotleDSItem item, Response response) {
                            listener.onSuccess(item);
          }

          @Override
          public void failure(RetrofitError error) {
                            listener.onFailure(error);
          }
      };
    }

    @Override
    public void updateItem(ChipotleDSItem item, Listener<ChipotleDSItem> listener) {
                    
        if(item.pictureUri != null){
            service.getServiceProxy().updateChipotleDSItem(item.getIdentifiableId(),
                item,
                TypedByteArrayUtils.fromUri(item.pictureUri),
                callbackFor(listener));
        }
        else
            service.getServiceProxy().updateChipotleDSItem(item.getIdentifiableId(), item, callbackFor(listener));
        
    }

    @Override
    public void deleteItem(ChipotleDSItem item, final Listener<ChipotleDSItem> listener) {
                service.getServiceProxy().deleteChipotleDSItemById(item.getIdentifiableId(), new Callback<ChipotleDSItem>() {
            @Override
            public void success(ChipotleDSItem result, Response response) {
                                listener.onSuccess(result);
            }

            @Override
            public void failure(RetrofitError error) {
                                listener.onFailure(error);
            }
        });
    }

    @Override
    public void deleteItems(List<ChipotleDSItem> items, final Listener<ChipotleDSItem> listener) {
                service.getServiceProxy().deleteByIds(collectIds(items), new Callback<List<ChipotleDSItem>>() {
            @Override
            public void success(List<ChipotleDSItem> item, Response response) {
                                listener.onSuccess(null);
            }

            @Override
            public void failure(RetrofitError error) {
                                listener.onFailure(error);
            }
        });
    }

    protected List<String> collectIds(List<ChipotleDSItem> items){
        List<String> ids = new ArrayList<>();
        for(ChipotleDSItem item: items){
            ids.add(item.getIdentifiableId());
        }
        return ids;
    }

}
