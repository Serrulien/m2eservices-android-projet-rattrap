package android.eservices.rawgytbmonitor.data.di;

import android.content.Context;
import android.eservices.rawgytbmonitor.data.api.GameDisplayService;
import android.eservices.rawgytbmonitor.data.db.GameDatabase;
import android.eservices.rawgytbmonitor.data.repository.bookdisplay.GameDisplayDataRepository;
import android.eservices.rawgytbmonitor.data.repository.bookdisplay.GameDisplayRepository;
import android.eservices.rawgytbmonitor.data.repository.bookdisplay.local.GameDisplayLocalDataSource;
import android.eservices.rawgytbmonitor.data.repository.bookdisplay.mapper.GameToGameEntityMapper;
import android.eservices.rawgytbmonitor.data.repository.bookdisplay.remote.gameDisplayRemoteDataSource;

import androidx.room.Room;

import com.facebook.stetho.okhttp3.StethoInterceptor;
import com.google.gson.Gson;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Please never do that in a production app. Ever.
 * For the purpose of our course, this is the best option to cover interesting topics as
 * we don't have time to dig into Dependency Injection frameworks such as the famous Dagger.
 * Singleton are compulsory for some classes, such as the one here. If you don't know why, then ask me.
 * Note that this god object doesn't handle Scopes nor component lifecycles so this really shouldn't be
 * the way to go when you master the craft of your software.
 */
public class FakeDependencyInjection {

    private static GameDisplayService gameDisplayService;
    private static Retrofit retrofit;
    private static Gson gson;
    private static GameDisplayRepository gameDisplayRepository;
    private static GameDatabase gameDatabase;
    private static Context applicationContext;

    public static GameDisplayRepository getGameDisplayRepository() {
        if (gameDisplayRepository == null) {
            gameDisplayRepository = new GameDisplayDataRepository(
                    new GameDisplayLocalDataSource(getGameDatabase()),
                    new gameDisplayRemoteDataSource(getGameDisplayService()),
                    new GameToGameEntityMapper()
            );
        }
        return gameDisplayRepository;
    }

    public static GameDisplayService getGameDisplayService() {
        if (gameDisplayService == null) {
            gameDisplayService = getRetrofit().create(GameDisplayService.class);
        }
        return gameDisplayService;
    }

    public static Retrofit getRetrofit() {
        if (retrofit == null) {
            HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            OkHttpClient client = new OkHttpClient.Builder()
                    .addInterceptor(interceptor)
                    .addNetworkInterceptor(new StethoInterceptor())
                    .build();

            retrofit = new Retrofit.Builder()
                    .baseUrl("https://api.rawg.io/api/")
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .client(client)
                    .addConverterFactory(GsonConverterFactory.create(getGson()))
                    .build();
        }
        return retrofit;
    }

    public static Gson getGson() {
        if (gson == null) {
            gson = new Gson();
        }
        return gson;
    }

    public static void setContext(Context context) {
        applicationContext = context;
    }

    public static GameDatabase getGameDatabase() {
        if (gameDatabase == null) {
            gameDatabase = Room.databaseBuilder(applicationContext,
                    GameDatabase.class, "book-database").build();
        }
        return gameDatabase;
    }
}
