
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

public interface ChipotleDSServiceRest{

	@GET("/app/59e539863b811c0400912c56/r/chipotleDS")
	void queryChipotleDSItem(
		@Query("skip") String skip,
		@Query("limit") String limit,
		@Query("conditions") String conditions,
		@Query("sort") String sort,
		@Query("select") String select,
		@Query("populate") String populate,
		Callback<List<ChipotleDSItem>> cb);

	@GET("/app/59e539863b811c0400912c56/r/chipotleDS/{id}")
	void getChipotleDSItemById(@Path("id") String id, Callback<ChipotleDSItem> cb);

	@DELETE("/app/59e539863b811c0400912c56/r/chipotleDS/{id}")
  void deleteChipotleDSItemById(@Path("id") String id, Callback<ChipotleDSItem> cb);

  @POST("/app/59e539863b811c0400912c56/r/chipotleDS/deleteByIds")
  void deleteByIds(@Body List<String> ids, Callback<List<ChipotleDSItem>> cb);

  @POST("/app/59e539863b811c0400912c56/r/chipotleDS")
  void createChipotleDSItem(@Body ChipotleDSItem item, Callback<ChipotleDSItem> cb);

  @PUT("/app/59e539863b811c0400912c56/r/chipotleDS/{id}")
  void updateChipotleDSItem(@Path("id") String id, @Body ChipotleDSItem item, Callback<ChipotleDSItem> cb);

  @GET("/app/59e539863b811c0400912c56/r/chipotleDS")
  void distinct(
        @Query("distinct") String colName,
        @Query("conditions") String conditions,
        Callback<List<String>> cb);
    
    @Multipart
    @POST("/app/59e539863b811c0400912c56/r/chipotleDS")
    void createChipotleDSItem(
        @Part("data") ChipotleDSItem item,
        @Part("picture") TypedByteArray picture,
        Callback<ChipotleDSItem> cb);
    
    @Multipart
    @PUT("/app/59e539863b811c0400912c56/r/chipotleDS/{id}")
    void updateChipotleDSItem(
        @Path("id") String id,
        @Part("data") ChipotleDSItem item,
        @Part("picture") TypedByteArray picture,
        Callback<ChipotleDSItem> cb);
}
