package bb.carddeck.API;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;

import bb.carddeck.model.CardList;
import bb.carddeck.model.Deck;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.*;
import retrofit2.http.Query;

public class API {

    private static APIInterface apiInterface;
    private static String url = "http://deckofcardsapi.com/";
    public static APIInterface getClient() {
        if (apiInterface == null) {
            OkHttpClient okHttpClient = new OkHttpClient.Builder()
                    .addInterceptor(
                            new Interceptor() {
                                @Override
                                public Response intercept(Chain chain) throws IOException {
                                    Request request = chain.request().newBuilder()
                                            .addHeader("Accept", "application/json").build();
                                    return chain.proceed(request);
                                }
                            }).build();

            Gson gson = new GsonBuilder()
                    .setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
                    .create();

            Retrofit client = new Retrofit.Builder()
                    .baseUrl(url)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .client(okHttpClient)
                    .build();
            apiInterface = client.create(APIInterface.class);
        }
        return apiInterface;
    }
    public interface APIInterface {

        @FormUrlEncoded
        @GET("api/deck/new/shuffle/")
        Call<Deck> GetShuffledDeck(@Field("deck_count") Integer number);

        @FormUrlEncoded
        @GET("api/deck/{deck_id}/draw/")
        Call<CardList> GetCards(@Path(value ="deck_id") String deck_id, @Field("count") Integer number);
    }
}