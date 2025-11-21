package com.tngocnhat.retrofit2;

import com.tngocnhat.retrofit2.model.Category;
import java.util.List;
import retrofit2.Call;
import retrofit2.http.GET;

public interface APIService {
    @GET("appfoods/categories.php")
    Call<List<Category>> getCategories();
}
