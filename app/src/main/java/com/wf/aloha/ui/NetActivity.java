package com.wf.aloha.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.wf.aloha.R;
import com.wf.aloha.SaxHandler;
import com.wf.aloha.utils.HttpCallbackListener;
import com.wf.aloha.utils.HttpUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParserFactory;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okio.BufferedSink;

public class NetActivity extends AppCompatActivity {

    @BindView(R.id.bt_net)
    Button btNet;
    @BindView(R.id.tv_content)
    TextView tvContent;
    @BindView(R.id.bt_okhttp)
    Button btOkhttp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_net);
        ButterKnife.bind(this);
//        set textview can scroll.
        tvContent.setMovementMethod(ScrollingMovementMethod.getInstance());
    }

    private void displayText(final String toString) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                tvContent.setText(toString);
            }
        });
    }

    @OnClick({R.id.bt_net, R.id.bt_okhttp})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.bt_net:
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            URL url = new URL("https://www.baidu.com");
                            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                            urlConnection.setRequestMethod("GET");
//                    urlConnection.setRequestMethod("POST");
//                    DataOutputStream outputStream = new DataOutputStream(urlConnection.getOutputStream());
//                    outputStream.writeBytes("wd=同仁堂");
                            urlConnection.setConnectTimeout(10000);
                            urlConnection.setReadTimeout(10000);
                            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
                            StringBuffer stringBuffer = new StringBuffer();
                            String line;
                            while ((line = bufferedReader.readLine()) != null) {
                                stringBuffer.append(line);
                            }
                            displayText(stringBuffer.toString());
                            bufferedReader.close();
                            urlConnection.disconnect();
                        } catch (MalformedURLException e) {
                            e.printStackTrace();
                        } catch (IOException e) {
                            e.printStackTrace();
                        } finally {
                        }
                    }
                }).start();
                break;

            case R.id.bt_okhttp:
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        OkHttpClient httpClient = new OkHttpClient();
                        Request request = new Request.Builder()
                                .url("http://10.10.1.166/~wangfei/data.json")
                                .build();
                        try {
                            Response response = httpClient.newCall(request).execute();
//                            displayText(response.body().string());
//                            Response.Builder body = response.newBuilder().body(ResponseBody.create(response.body().contentType(), ""));
//                            xmlpull(body.build().body().string());
//                            xmlSax(body.build().body().string());
//                            parseJsonObject(response.body().string());
                            parseGson(response.body().string());
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }).start();
//                HttpUtils.sendGetRequestOk("http://www.baidu.com",new okhttp3.Callback(){
//
//                    @Override
//                    public void onFailure(Call call, IOException e) {
//                        
//                    }
//
//                    @Override
//                    public void onResponse(Call call, Response response) throws IOException {
//
//                    }
//                });
                break;
            default:
                break;
        }
    }

    private void parseGson(String gsonStr) {
        Gson gson = new Gson();
        ArrayList<GParse> list = gson.fromJson(gsonStr, new TypeToken<List<GParse>>() {
        }.getType());
        for (GParse item:
             list) {
            Log.d("eeeeee", "parseGson: "+item.id+item.name+item.version);
        }
    }

    private void parseJsonObject(String json) {
        try {
            JSONArray jsonArray = new JSONArray(json);
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObj = jsonArray.optJSONObject(i);
                String id = jsonObj.optString("id");
                String name = jsonObj.optString("name");
                String version = jsonObj.optString("version");
                Log.d("-----", "parseJsonObject: " + id + name + version);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void xmlSax(String saxStr) {
        SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
        try {
            XMLReader xmlReader = saxParserFactory.newSAXParser().getXMLReader();
            SaxHandler saxHandler = new SaxHandler();
            xmlReader.setContentHandler(saxHandler);
            xmlReader.parse(new InputSource(new StringReader(saxStr)));
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void xmlpull(String str) {
        try {
            XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
            XmlPullParser xmlPullParser = factory.newPullParser();
            xmlPullParser.setInput(new StringReader(str));
            int next = xmlPullParser.getEventType();
            String id = "";
            String name = "";
            String version = "";
            while (next != XmlPullParser.END_DOCUMENT) {
                String nodename = xmlPullParser.getName();
                switch (next) {
                    case XmlPullParser.START_TAG:
                        if ("id".equals(nodename)) {
                            id = xmlPullParser.nextText();
                        } else if ("name".equals(nodename)) {
                            name = xmlPullParser.nextText();
                        } else if ("version".equals(nodename)) {
                            version = xmlPullParser.nextText();
                        }
                        break;
                    case XmlPullParser.END_TAG:
                        if ("app".equals(nodename)) {
                            Log.d("kkkkkk", "xmlpull: id" + id + name + version);
                        }
                        break;
                }
                next = xmlPullParser.next();
            }
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private class GParse {
        public String id;
        public String name;
        public String version;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getVersion() {
            return version;
        }

        public void setVersion(String version) {
            this.version = version;
        }
    }
}
