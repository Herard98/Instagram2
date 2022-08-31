package COM.LOPES.INSTAGRAM;

import android.app.Application;

import com.parse.Parse;
import com.parse.ParseObject;

import COM.LOPES.INSTAGRAM.Models.post;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;

public class ParseApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        // Use for monitoring Parse network traffic
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        // Can be Level.BASIC, Level.HEADERS, or Level.BODY
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        // any network interceptors must be added with the Configuration Builder given this syntax
        builder.networkInterceptors().add(httpLoggingInterceptor);

        ParseObject.registerSubclass(post.class);
        Parse.initialize(new Parse.Configuration.Builder(this)
                .applicationId("MhpZ7VFO0KmGWMnL6Tec6szBd543RBkFwWf0p1NC")
                .clientKey("qC02t44UA1KK2GXRQGibSDx9sCpc7HqktrWIT4fh")
                .server("https://parseapi.back4app.com")
                .build()
        );
    }

}
