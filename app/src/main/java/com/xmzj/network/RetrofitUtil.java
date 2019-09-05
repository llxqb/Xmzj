package com.xmzj.network;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.util.concurrent.TimeUnit;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import okio.Buffer;
import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


@SuppressWarnings("ALL")
public class RetrofitUtil {

    private static final MediaType MEDIA_TYPE = MediaType.parse("application/json");

    private Context context;
    private boolean isHttps;
    private boolean isToJson;
    private String baseUrl;
    private String keyName;
    private String keyPwd;
    private String keyType;
    private String token;
    private Retrofit retrofit;

    public RetrofitUtil(Builder builder) {
        this.context = builder.context;
        this.baseUrl = builder.baseUrl;
        this.isToJson = builder.isToJson;
        this.isHttps = builder.isHttps;
        this.keyName = builder.keyName;
        this.keyPwd = builder.keyPwd;
        this.keyType = builder.keyType;
        this.token = builder.token;
        this.retrofit = initRetrofit();
    }

    private static String bodyToString(final Request request) {
        try {
            final Request copy = request.newBuilder().build();
            final Buffer buffer = new Buffer();
            copy.body().writeTo(buffer);
            return buffer.readUtf8();
        } catch (final IOException e) {
            return "did not work";
        }
    }

    private Retrofit initRetrofit() {
        Gson gson = new GsonBuilder().create();

        OkHttpClient okHttpClient;
        OkHttpClient.Builder okHttpClientBuilder =
                new OkHttpClient.Builder()
                        .connectTimeout(60, TimeUnit.SECONDS)
                        .readTimeout(60, TimeUnit.SECONDS)
                        .writeTimeout(60 * 1000, TimeUnit.SECONDS);
        //获取token 值
        SharedPreferences pref = context.getSharedPreferences("xmzj_cache", 0);
        String token = pref.getString("token","");

        okHttpClientBuilder.addInterceptor(new LogInterceptor(context,token));
//        okHttpClientBuilder.addInterceptor(new Interceptor() {
//            @Override
//            public Response intercept(Chain chain) throws IOException {
//                Request request = chain.request();
//                Request newRequest;
//                String method = request.method();
//                TreeMap<String, Object> rootMap = new TreeMap<>();
//                if (method.equals("GET")) {
//                    HttpUrl httpUrlurl = request.url();
//                    Set<String> parameterNames = httpUrlurl.queryParameterNames();
//
//                    for (String key : parameterNames) {
//                        rootMap.put(key, httpUrlurl.queryParameter(key));
//                    }
//                } else {
//                    RequestBody requestBody = request.body();
//                    if (requestBody instanceof FormBody) {
//                        for (int i = 0; i < ((FormBody) requestBody).size(); i++) {
//                            rootMap.put(((FormBody) requestBody).encodedName(i), ((FormBody) requestBody).encodedValue(i));
//                        }
//                    } else {
//                        Buffer buffer = new Buffer();
//                        requestBody.writeTo(buffer);
//                        String oldParamsJson = buffer.readUtf8();
//                        if (!TextUtils.isEmpty(oldParamsJson)) {
//                            rootMap = new Gson().fromJson(oldParamsJson, TreeMap.class);
//                        }
//                    }
//                }
////                String sign = ValueUtil.getSign(rootMap);
////                newRequest = request.newBuilder().addHeader("ssapp-token", sign).build();
//
//                long startTime = System.currentTimeMillis();
//                Response response = chain.proceed(request);
//                long endTime = System.currentTimeMillis();
////                Log.e("LogInterceptor", "response:" + new Gson().toJson(response));
////                String content = response.body().string();
//                if (method.equals("GET")) {
////                    String content = response.body().string();
//                    Log.i("LogInterceptor", "GET():"+"\nURL:" + request.url() + "（耗时" + (endTime - startTime) + "ms)"
//                            + "\nResponse BODY:" + response.body().string());
//                } else {
//                    String content = response.body().string();
//                    Log.i("LogInterceptor", "POST():"+"\nURL:" + request.url() + "（耗时" + (endTime - startTime) + "ms)" + " \nREQEUST BODY:" + bodyToString(request)+
//                            "\nResponse BODY:" + content);
//                }
//                return response;
//            }
//        });
//        if (isHttps) {
//            SSLSocketFactory ssl = new SSLSocketUtil.Builder()
//                    .context(context)
//                    .keyStoreName(keyName)
//                    .keyPwd(keyPwd)
//                    .build()
//                    .getSocketFactory();
//            okHttpClientBuilder.sslSocketFactory(ssl)
//                    .hostnameVerifier(
//                            org.apache.http.conn.ssl.SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
//        }
        okHttpClient = okHttpClientBuilder.build();


        Retrofit.Builder retrofitBuilder = new Retrofit.Builder().baseUrl(baseUrl);
        if (isToJson) {
            retrofitBuilder.addConverterFactory(GsonConverterFactory.create(gson));
        } else {
            retrofitBuilder.addConverterFactory(StringConverterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create());
        }
        return retrofitBuilder.addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(okHttpClient)
                .build();
    }

    public <T> T create(final Class<T> service) {
        return retrofit.create(service);
    }

    public static class Builder {

        private Context context;
        private boolean isHttps;
        private boolean isToJson;
        private String baseUrl;
        private String keyName;
        private String keyPwd;
        private String keyType;
        private String token;

        public Builder() {
            this.context = null;
            this.isHttps = false;
            this.isToJson = true;
            this.baseUrl = "";
            this.keyName = "";
            this.keyPwd = "";
            this.keyType = "PKCS12";
            this.token="";
        }

        public Builder context(Context context) {
            this.context = context;
            return this;
        }

        public Builder isHttps(boolean isHttps) {
            this.isHttps = isHttps;
            return this;
        }

        public Builder key(String keyName, String keyPwd) {
            this.keyName = keyName;
            this.keyPwd = keyPwd;
            return this;
        }

        public Builder keyType(String keyType) {
            this.keyType = keyType;
            return this;
        }

        public Builder isToJson(boolean isToJson) {
            this.isToJson = isToJson;
            return this;
        }

        public Builder baseUrl(String baseUrl) {
            this.baseUrl = baseUrl;
            return this;
        }
        public Builder setToken(String token) {
            this.token = token;
            return this;
        }

        public RetrofitUtil builder() {
            return new RetrofitUtil(this);
        }
    }


    static class StringConverterFactory extends Converter.Factory {

        static final StringConverterFactory INSTANCE = new StringConverterFactory();

        static StringConverterFactory create() {
            return INSTANCE;
        }

        @Override
        public Converter<ResponseBody, ?> responseBodyConverter(Type type, Annotation[] annotations,
                                                                Retrofit retrofit) {
            if (type == String.class) {
                return StringConverter.INSTANCE;
            }
            return null;
        }

        @Override
        public Converter<?, RequestBody> requestBodyConverter(Type type, Annotation[] parameterAnnotations, Annotation[] methodAnnotations, Retrofit retrofit) {
            if (String.class.equals(type)) {
                return new Converter<String, RequestBody>() {
                    @Override
                    public RequestBody convert(String value) throws IOException {
                        return RequestBody.create(MEDIA_TYPE, value);
                    }
                };
            }
            return null;
        }
    }

    static class StringConverter implements Converter<ResponseBody, String> {

        static final StringConverter INSTANCE = new StringConverter();

        @Override
        public String convert(ResponseBody response) throws IOException {
            return response.string();
        }
    }
}
