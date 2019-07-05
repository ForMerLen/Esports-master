package com.shixun.esports.Activity;



import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.shixun.esports.R;
import com.shixun.esports.Tools.Constant;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TestActivity extends AppCompatActivity {
    private ListView lv;
    List<HashMap<String, String>> list = new ArrayList<HashMap<String,String>>();
    private Message message;
    private String json="";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.testlist);

        initView();
        test();


    }

    private void initView() {
        list.clear();
        message = new Message();

    }

    private void test()
    {
        String TestUrlStr = Constant.URL_Test + "?status=0";
        Log.d("测试",TestUrlStr);
        new MyAsyncTask(message).execute(TestUrlStr);

    }

    @SuppressLint("HandlerLeak")
    Handler mHandler = new Handler(){
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);

            String data = msg.obj.toString();
            Log.d("测试1", data);

            try {
                JSONArray jsonArray = new JSONArray(data);
                for(int i=0;i<jsonArray.length();i++)
                {
                    JSONObject jsonObject = (JSONObject)jsonArray.get(i);
                    HashMap<String, String> map = new HashMap<String, String>();
                    map.put("id",jsonObject.getString("id"));
                    map.put("name",jsonObject.getString("name"));
                    list.add(map);
                    Log.d("测试3",list.toString());
                }


            } catch (JSONException e) {
                e.printStackTrace();
            }
            String[] str = new String[]{"id","name"};
        //    int[] rid = new int[]{R.id.lv_id,R.id.lv_name};
         //   SimpleAdapter simpleAdapter=new SimpleAdapter(TestActivity.this,list,R.layout.testlist_lv,str,rid);
            lv = (ListView)findViewById(R.id.lv);
         //   lv.setAdapter(simpleAdapter);
        }
    };

    public class MyAsyncTask extends AsyncTask<String, Integer, String>
    {

        StringBuilder response = new StringBuilder();

        public MyAsyncTask(Message string) {
            string.obj=response;
            mHandler.sendMessage(string);
            Log.d("测试msg",string.toString());
        }

        @Override
        protected void onPreExecute() {
            Log.w("WangJ", "task onPreExecute()");
        }

        /**
         * @param params 这里的params是一个数组，即AsyncTask在激活运行是调用execute()方法传入的参数
         */
        @Override
        protected String doInBackground(String... params) {

            HttpURLConnection connection = null;

            try {
                URL url = new URL(params[0]); // 声明一个URL,注意如果用百度首页实验，请使用https开头，否则获取不到返回报文
                connection = (HttpURLConnection) url.openConnection(); // 打开该URL连接
                connection.setRequestMethod("GET"); // 设置请求方法，“POST或GET”，我们这里用GET，在说到POST的时候再用POST
                connection.setConnectTimeout(80000); // 设置连接建立的超时时间
                connection.setReadTimeout(80000); // 设置网络报文收发超时时间
                InputStream in = connection.getInputStream();  // 通过连接的输入流获取下发报文，然后就是Java的流处理
                BufferedReader reader = new BufferedReader(new InputStreamReader(in));
                String line;
                while ((line = reader.readLine()) != null) {
                    response.append(line);
                }
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return response.toString(); // 这里返回的结果就作为onPostExecute方法的入参
        }
    }
}

