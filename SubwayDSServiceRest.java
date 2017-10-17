
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

public interface SubwayDSServiceRest{

	@GET("/app/59e539863b811c0400912c56/r/subwayDS")
	void querySubwayDSItem(
		@Query("skip") String skip,
		@Query("limit") String limit,
		@Query("conditions") String conditions,
		@Query("sort") String sort,
		@Query("select") String select,
		@Query("populate") String populate,
		Callback<List<SubwayDSItem>> cb);

	@GET("/app/59e539863b811c0400912c56/r/subwayDS/{id}")
	void getSubwayDSItemById(@Path("id") String id, Callback<SubwayDSItem> cb);

	@DELETE("/app/59e539863b811c0400912c56/r/subwayDS/{id}")
  void deleteSubwayDSItemById(@Path("id") String id, Callback<SubwayDSItem> cb);

  @POST("/app/59e539863b811c0400912c56/r/subwayDS/deleteByIds")
  void deleteByIds(@Body List<String> ids, Callback<List<SubwayDSItem>> cb);

  @POST("/app/59e539863b811c0400912c56/r/subwayDS")
  void createSubwayDSItem(@Body SubwayDSItem item, Callback<SubwayDSItem> cb);

  @PUT("/app/59e539863b811c0400912c56/r/subwayDS/{id}")
  void updateSubwayDSItem(@Path("id") String id, @Body SubwayDSItem item, Callback<SubwayDSItem> cb);

  @GET("/app/59e539863b811c0400912c56/r/subwayDS")
  void distinct(
        @Query("distinct") String colName,
        @Query("conditions") String conditions,
        Callback<List<String>> cb);
    
    @Multipart
    @POST("/app/59e539863b811c0400912c56/r/subwayDS")
    void createSubwayDSItem(
        @Part("data") SubwayDSItem item,
        @Part("picture") TypedByteArray picture,
        Callback<SubwayDSItem> cb);
    
    @Multipart
    @PUT("/app/59e539863b811c0400912c56/r/subwayDS/{id}")
    void updateSubwayDSItem(
        @Path("id") String id,
        @Part("data") SubwayDSItem item,
        @Part("picture") TypedByteArray picture,
        Callback<SubwayDSItem> cb);
}
