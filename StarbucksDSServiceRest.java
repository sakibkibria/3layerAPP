
package io.buildup.pkg20171016225815.ds;
import java.util.List;
import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Query;
import retrofit.http.POST;
import retrofit.http.Body;
import retrofit.http.DELETE;
import retrofit.http.Path;
import retrofit.http.PUT;
import retrofit.mime.TypedByteArray;
import retrofit.http.Part;
import retrofit.http.Multipart;

public interface StarbucksDSServiceRest{

	@GET("/app/59e539863b811c0400912c56/r/starbucksDS")
	void queryStarbucksDSItem(
		@Query("skip") String skip,
		@Query("limit") String limit,
		@Query("conditions") String conditions,
		@Query("sort") String sort,
		@Query("select") String select,
		@Query("populate") String populate,
		Callback<List<StarbucksDSItem>> cb);

	@GET("/app/59e539863b811c0400912c56/r/starbucksDS/{id}")
	void getStarbucksDSItemById(@Path("id") String id, Callback<StarbucksDSItem> cb);

	@DELETE("/app/59e539863b811c0400912c56/r/starbucksDS/{id}")
  void deleteStarbucksDSItemById(@Path("id") String id, Callback<StarbucksDSItem> cb);

  @POST("/app/59e539863b811c0400912c56/r/starbucksDS/deleteByIds")
  void deleteByIds(@Body List<String> ids, Callback<List<StarbucksDSItem>> cb);

  @POST("/app/59e539863b811c0400912c56/r/starbucksDS")
  void createStarbucksDSItem(@Body StarbucksDSItem item, Callback<StarbucksDSItem> cb);

  @PUT("/app/59e539863b811c0400912c56/r/starbucksDS/{id}")
  void updateStarbucksDSItem(@Path("id") String id, @Body StarbucksDSItem item, Callback<StarbucksDSItem> cb);

  @GET("/app/59e539863b811c0400912c56/r/starbucksDS")
  void distinct(
        @Query("distinct") String colName,
        @Query("conditions") String conditions,
        Callback<List<String>> cb);
    
    @Multipart
    @POST("/app/59e539863b811c0400912c56/r/starbucksDS")
    void createStarbucksDSItem(
        @Part("data") StarbucksDSItem item,
        @Part("picture") TypedByteArray picture,
        Callback<StarbucksDSItem> cb);
    
    @Multipart
    @PUT("/app/59e539863b811c0400912c56/r/starbucksDS/{id}")
    void updateStarbucksDSItem(
        @Path("id") String id,
        @Part("data") StarbucksDSItem item,
        @Part("picture") TypedByteArray picture,
        Callback<StarbucksDSItem> cb);
}
