package com.get.quick_medical_helper;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.Nullable;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.RealmResults;
import io.realm.annotations.Ignore;
import io.realm.annotations.PrimaryKey;

public class MainActivity3 extends Activity {
    static String input_str;//用户输入的查询字符串
    static String district;//用户选择的地区(决定查询某一个表/collection)
    static String search_by;//用户选择的查询方式(hospt/deptmt)
    public static final String TAG="act3";
    public static final String TAG_realm="realmtest";
    private static InputStream is = null;
    private static FileOutputStream fos = null;
    static String path;
    private Realm realm;
    private List<String> hospt_name;
    private ArrayAdapter<String> adapter;
    private ListView listView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.result);

        Intent intent = getIntent();
        input_str = intent.getStringExtra("input_str");
        district = intent.getStringExtra("district");
        search_by = intent.getStringExtra("search_by");
        Log.d(TAG, input_str);
        Log.d(TAG, district);
        Log.d(TAG, search_by);


        Realm.init(this);
        RealmConfiguration config = new RealmConfiguration.Builder()
              .name("qmh.realm").build();

        Realm.setDefaultConfiguration(config);
        realm = Realm.getInstance(config);


//        //move database from assets to data//files
//        path = "/data/data/com.get.quick_medical_helper/files";
//        File data_file = new File(path + "/" + "qmh.realm");
//        try {
//            File dir = new File(path);
//            if (!dir.exists())
//                dir.mkdirs();
//            if (data_file.exists())//删除已经存在的
//                data_file.deleteOnExit();
//            if (!data_file.exists())
//                data_file.createNewFile();
//
//            // 从assets目录下复制
//            is = getAssets().open("qmh.realm");
//            fos = new FileOutputStream(data_file);
//            int len = 0;
//            byte[] b = new byte[1024];
//            while ((len = is.read(b)) != -1) {
//                fos.write(b, 0, len);
//                b = new byte[1024];
//            }
//            fos.flush();
//            if (is != null)
//                is.close();
//            if (fos != null)
//                fos.close();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//
//
        class User extends RealmObject {

            @PrimaryKey
            private String name;
            private String id;
            private RealmList<String> details;
            private int score;
            private RealmList<String> address;
            private RealmList<String> depart;
            private RealmList<String> doctor;
            @Ignore
            private int sessionId;

            RealmResults<User> r;
            RealmResults<User> search(String search_by, String input_str, Realm realm) {
                if (search_by == "hospt") {//按照医院查询
                    r = realm.where(User.class).contains("name", input_str).findAll();
                } else {//按照科室/症状查询
//            ToDo  按照科室/症状查询

                }
                return r;
            }

        }
//
//
//        User user = new User();
//        RealmResults<User> r;
//        r = user.search(search_by, input_str, realm);
//        for (int i=0;i<r.size();i++){
//            hospt_name.add(r.get(i).name);
//            Log.d(TAG_realm, r.get(i).name);
//            Log.d(TAG_realm, r.get(i).address.toString());
//            Log.d(TAG_realm, r.get(i).depart.toString());
//            Log.d(TAG_realm, r.get(i).details.toString());
//            Log.d(TAG_realm, r.get(i).doctor.toString());
//
//        }
//        adapter = new ArrayAdapter<String>(MainActivity3.this,android.R.layout.simple_list_item_1,hospt_name);
//        listView = (ListView) findViewById(R.id.result_list);
//        listView.setAdapter(adapter);
//
//
//        //点击查看详情
//        listView.setOnItemClickListener(new ListView.OnItemClickListener(){
//
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//
//            }
//
//        });


    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        realm.close();
    }
}