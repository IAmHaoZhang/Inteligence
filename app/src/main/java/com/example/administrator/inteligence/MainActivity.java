package com.example.administrator.inteligence;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private ListView guizeku_view, yizhi_view, daipipei_view;
    private List<Guizeku> guizeku_list = new ArrayList<Guizeku>();
    private List<Daipipei> daipipei_list = new ArrayList<Daipipei>();
    private List<Yizhi> yizhi_list = new ArrayList<Yizhi>();
    private Button begin, add_guize, add_yizhi;
    private TextView result, process;
    private EditText guize_content, yizhi_content;
    private GuizekuAdapter adapter1;
    private YizhiAdapter adapter2;
    private DaipipeiAdapter adapter3;
    private boolean flag = true;


    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 0:
                    System.out.println("jinruHandler");
                    String text = (String) msg.obj;
                    process.setText(text);
//                    yizhi_list.add(new Yizhi("shi"));
//                    adapter2.notifyDataSetChanged();
                    break;
                case 1:
                    String text1 = (String) msg.obj;
                    int position = msg.arg1;
                    int cishu = msg.arg2;
                    yizhi_list.add(new Yizhi(guizeku_list.get(position).getConclusion()));
                    adapter2.notifyDataSetChanged();
                    process.setText("第"+cishu+"次循环:"+text1);
                    break;
                case 3:
                    String text2 = (String) msg.obj;
                    int position1 = msg.arg1;
                    int cishu1 = msg.arg2;
                    daipipei_list.add(new Daipipei(guizeku_list.get(position1).getTite()));
                    adapter3.notifyDataSetChanged();
                    process.setText("第"+cishu1+"次循环:"+text2);
                default:
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        guize_content = (EditText) findViewById(R.id.guize_content);
        yizhi_content = (EditText) findViewById(R.id.yizhi_content);
        begin = (Button) findViewById(R.id.begin);
        result = (TextView) findViewById(R.id.result);
        process = (TextView) findViewById(R.id.process);
        add_guize = (Button) findViewById(R.id.add_guize);
        add_yizhi = (Button) findViewById(R.id.add_yizhi);
//        add_guize.setOnClickListener(this);
//        add_yizhi.setOnClickListener(this);
        begin.setOnClickListener(this);
        guizeku_view = (ListView) findViewById(R.id.guizeku_view);
        yizhi_view = (ListView) findViewById(R.id.yizhi_view);
        daipipei_view = (ListView) findViewById(R.id.daipipei_view);
        initGuizeku();
        adapter1 = new GuizekuAdapter(MainActivity.this, R.layout.guizeku, guizeku_list);
        guizeku_view.setAdapter(adapter1);
        initYizhi();
        adapter2 = new YizhiAdapter(MainActivity.this, R.layout.yizhi, yizhi_list);
        yizhi_view.setAdapter(adapter2);
        initDaipipei();
        adapter3 = new DaipipeiAdapter(MainActivity.this, R.layout.daipipei, daipipei_list);
        daipipei_view.setAdapter(adapter3);
        add_guize.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String input = guize_content.getText().toString();
                int length = FenGeZiFu(input).length;
                String[] content = FenGeZiFu(input);
                guizeku_list.add(new Guizeku(content[length - 3], content[length - 2], content[length - 1]));
                System.out.println(content[length - 3] + " " + content[length - 2] + " " + content[length - 1]);
                adapter1.notifyDataSetChanged();
                guize_content.setText("");

            }
        });
        add_yizhi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                yizhi_list.add(new Yizhi(yizhi_content.getText().toString()));
                adapter2.notifyDataSetChanged();
                yizhi_content.setText("");
            }
        });
    }

    private String[] FenGeZiFu(String input) {
        String[] strings = input.split(" ");
        return strings;
    }

    private void initDaipipei() {
        daipipei_list.add(new Daipipei("youti"));
    }

    private void initYizhi() {
        yizhi_list.add(new Yizhi("youjiao"));
    }

    private void initGuizeku() {
        guizeku_list.add(new Guizeku("R1", "youjiao", "burudongwu"));
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.begin:
                //System.out.println(Jiance() == false);
                if (String.valueOf(Jiance()) == "false") {
                    System.out.println("kaishipipei");
                    KaishiPipei();
                }
                break;
        }
    }

    private boolean Jiance() {
        for (int i = 0; i < yizhi_list.size(); i++) {
            if (yizhi_list.get(i).getYizhiName().contains("shi")) {
                System.out.println("baohan");
                Toast.makeText(MainActivity.this, "success", Toast.LENGTH_SHORT).show();
                result.setText(yizhi_list.get(i).getYizhiName());
                return true;
            }
        }
        System.out.println("bubaohan");
        return false;
    }

    private void KaishiPipei() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                int i = 1;
                System.out.println("开始匹配");
                Message message = new Message();
                message.what = 0;
                message.obj = "第" + i + "次循环";
                handler.sendMessage(message);
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                //System.out.println("daizheli");
                for (int k = 0; k < guizeku_list.size(); k++) {
                    for (int j = 0; j < yizhi_list.size(); j++) {
                        if (yizhi_list.get(j).getYizhiName().equals(guizeku_list.get(k).getGuizeName())) {
                            Message message1 = new Message();
                            message1.what = 1;
                            message1.arg1 = k;
                            message1.arg2 = i;
                            message.obj = "匹配成功,结论加入已知库";
                            handler.sendMessage(message1);
                        } else {
                            Message message2 = new Message();
                            message2.what = 2;
                            message2.arg1 = k;
                            message2.arg2 = i;
                            message2.obj = "没有可匹配对象，加入待匹配库";
                            handler.sendMessage(message2);
                        }
                    }
                }
                i++;
            }
        }).start();
    }
}
