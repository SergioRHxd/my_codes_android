package udg.cuvalles.retrofit

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface ApiServiceCoffes {
    @GET("coffee/hot")
    suspend fun getHotCoffees(): List<CoffeeItem>

    companion object {
        private const val BASE_URL = "https://api.sampleapis.com/"

        val instance: ApiServiceCoffes by lazy {
            val logging = HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            }

            val client = OkHttpClient.Builder()
                .addInterceptor(logging)
                .build()

            Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(ApiServiceCoffes::class.java)
        }
    }
}
