package com.get.quick_medical_helper;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;

import static android.widget.AdapterView.*;

public class MainActivity2 extends Activity {

    public static final String TAG="act2";
    private TextView search_input;
    private Button bt1;
    private Button bt2;
    private Button bt3;
    private Spinner sp;
    static String district;
    static String input_str;
    static String search_by;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search);

        final Intent intent = new Intent(MainActivity2.this,MainActivity3.class);
        final Bundle bundle = new Bundle();

        search_input = (EditText)findViewById(R.id.search_input);
        bt1 = (Button)findViewById(R.id.button1);
        bt2 = (Button)findViewById(R.id.button2);
        bt3 = (Button)findViewById(R.id.button_exit);
        sp = (Spinner)findViewById(R.id.spinner);

        bt1.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {

            input_str = search_input.getText().toString();
            Log.d(TAG, input_str);
            bundle.putString("input_str", input_str);
            bundle.putString("search_by", "hospt");
            intent.putExtras(bundle);
            startActivity(intent);
            }
        });

        bt2.setOnClickListener(new OnClickListener() {
            public void onClick(View v2) {

            input_str = search_input.getText().toString();
            Log.d(TAG, input_str);
            bundle.putString("input_str", input_str);
            bundle.putString("search_by", "deptmt");
            intent.putExtras(bundle);
            startActivity(intent);
            }
        });

        bt3.setOnClickListener(new OnClickListener() {
            public void onClick(View v3) {
            AlertDialog dialog;
            dialog = new AlertDialog.Builder(MainActivity2.this).setTitle("提示信息")
                    .setMessage("是否退出程序？")
                    .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) { MainActivity.instc_forclose.finish();MainActivity2.this.finish(); }})
                    .setNegativeButton("取消",null).create();
            dialog.show();
            }
        });

        sp.setOnItemSelectedListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                //获取spinner选中的文本
                district = MainActivity2.this.getResources().getStringArray(R.array.county)[position];
                Toast.makeText(MainActivity2.this, "您已选择 " + district, Toast.LENGTH_SHORT).show();
                Log.d(TAG, district);
                bundle.putString("district", district);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Toast.makeText(MainActivity2.this, "请选择您所在的区域" + district, Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d("TAG","pause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d("TAG","stop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}




