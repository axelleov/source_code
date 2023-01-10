package com.ti5b.freeciscoccna.services;

import com.ti5b.freeciscoccna.models.Post;
import com.ti5b.freeciscoccna.models.ValueData;
import com.ti5b.freeciscoccna.models.ValueNoData;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface APIService {
    @FormUrlEncoded
    @POST("loginUser")
    Call<ValueNoData> login(@Field("key") String key,
                            @Field("email") String email);

    @FormUrlEncoded
    @POST("registerUser")
    Call<ValueNoData> register(@Field("key") String key,
                            @Field("email") String email);

    @FormUrlEncoded
    @POST("getAllPost")
    Call<ValueData<Post>> getPost(@Field("key") String key);

    @FormUrlEncoded
    @POST("insertPost")
    Call<ValueNoData> addPost(@Field("key") String key,
                              @Field("email") String email,
                              @Field("image_main") String image_main,
                              @Field("heading_main") String heading_main,
                              @Field("heading1") String heading1,
                              @Field("heading2") String heading2,
                              @Field("heading3") String heading3,
                              @Field("heading4") String heading4,
                              @Field("heading5") String heading5,
                              @Field("h1_paragraph1") String h1_paragraph1,
                              @Field("h2_paragraph1") String h2_paragraph1,
                              @Field("h3_paragraph1") String h3_paragraph1,
                              @Field("h4_paragraph1") String h4_paragraph1,
                              @Field("h5_paragraph1") String h5_paragraph1,
                              @Field("h1_paragraph2") String h1_paragraph2,
                              @Field("h2_paragraph2") String h2_paragraph2,
                              @Field("h3_paragraph2") String h3_paragraph2,
                              @Field("h4_paragraph2") String h4_paragraph2,
                              @Field("h5_paragraph2") String h5_paragraph2,
                              @Field("h1_image") String h1_image,
                              @Field("h2_image") String h2_image,
                              @Field("h3_image") String h3_image,
                              @Field("h4_image") String h4_image,
                              @Field("h5_image") String h5_image);

    @FormUrlEncoded
    @POST("updatePost")
    Call<ValueNoData> updatePost(@Field("key") String key,
                                 @Field("id") int id,
                                 @Field("image_main") String image_main,
                                 @Field("heading_main") String heading_main,
                                 @Field("heading1") String heading1,
                                 @Field("heading2") String heading2,
                                 @Field("heading3") String heading3,
                                 @Field("heading4") String heading4,
                                 @Field("heading5") String heading5,
                                 @Field("h1_paragraph1") String h1_paragraph1,
                                 @Field("h2_paragraph1") String h2_paragraph1,
                                 @Field("h3_paragraph1") String h3_paragraph1,
                                 @Field("h4_paragraph1") String h4_paragraph1,
                                 @Field("h5_paragraph1") String h5_paragraph1,
                                 @Field("h1_paragraph2") String h1_paragraph2,
                                 @Field("h2_paragraph2") String h2_paragraph2,
                                 @Field("h3_paragraph2") String h3_paragraph2,
                                 @Field("h4_paragraph2") String h4_paragraph2,
                                 @Field("h5_paragraph2") String h5_paragraph2,
                                 @Field("h1_image") String h1_image,
                                 @Field("h2_image") String h2_image,
                                 @Field("h3_image") String h3_image,
                                 @Field("h4_image") String h4_image,
                                 @Field("h5_image") String h5_image);

    @FormUrlEncoded
    @POST("deletePost")
    Call<ValueNoData> deletePost(@Field("key") String key,
                                 @Field("id") int id);

}
