package com.example.before_170503_test;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import in.srain.cube.views.ptr.PtrClassicDefaultFooter;
import in.srain.cube.views.ptr.PtrClassicDefaultHeader;
import in.srain.cube.views.ptr.PtrDefaultHandler2;
import in.srain.cube.views.ptr.PtrFrameLayout;

public class IntnetActivity extends AppCompatActivity {

    @Bind(R.id.intnet_lv)
    ListView intnetLv;
    @Bind(R.id.activity_intnet)
    RelativeLayout activityIntnet;

    private ArrayList<Mint> list;
    private MyAdapter adapter;
      private PtrFrameLayout ptrFrameLayout;
    private ModelDao dao;
 private int pno=1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intnet);
        ButterKnife.bind(this);


        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper (this, "Student.db", null);
        SQLiteDatabase database = helper.getWritableDatabase();
        DaoMaster daoMaster = new DaoMaster(database);
        DaoSession daoSession = daoMaster.newSession();
        dao = daoSession.getModelDao();

        list=new ArrayList<>();
        initdata(pno);

        adapter = new MyAdapter(list);
        intnetLv.setAdapter(adapter);

        intnetLv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent=new Intent(IntnetActivity.this,XiangqingActivity.class);
                intent.putExtra("url",list.get(position).getResult().getList().get(position).getUrl());
                IntnetActivity.this.startActivity(intent);

            }
        });


        ptrFrameLayout= (PtrFrameLayout) findViewById(R.id.PtrFrameLayout);
        PtrClassicDefaultFooter defaultFooter=new PtrClassicDefaultFooter(this);
        PtrClassicDefaultHeader defaultHeader=new PtrClassicDefaultHeader(this);

        ptrFrameLayout.setFooterView(defaultFooter);
        ptrFrameLayout.setHeaderView(defaultHeader);

        ptrFrameLayout.addPtrUIHandler(defaultFooter);
        ptrFrameLayout.addPtrUIHandler(defaultHeader);

        ptrFrameLayout.setPtrHandler(new PtrDefaultHandler2() {
            @Override
            public void onLoadMoreBegin(PtrFrameLayout frame) {
                pno++;
                initdata(pno);
                MyRunable.mainthread(new Runnable() {
                    @Override
                    public void run() {
                        list.clear();
                        ptrFrameLayout.refreshComplete();
                        adapter.notifyDataSetChanged();
                    }
                });
            }

            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {
                MyRunable.mainthread(new Runnable() {
                    @Override
                    public void run() {
                        ptrFrameLayout.refreshComplete();
                        adapter.notifyDataSetChanged();

                    }
                });
            }
        });
    }

    private void initdata(int pno) {
        String url="http://v.juhe.cn/weixin/query?key=25ed29d133cfc9f40e0412051a944168&pno="+pno;
        RequestQueue queue= Volley.newRequestQueue(IntnetActivity.this);
        MyGosnRequest<Mint> request=new MyGosnRequest<Mint>( Request.Method.GET,url, Mint.class,
                new Response.Listener<Mint>() {
                    @Override
                    public void onResponse(Mint model) {
                        Log.e("TAG","网络请求成功"+model.toString());

                         for(int i=0;i<model.getResult().getList().size();i++){
                             list.add(model);

                            Mint.ResultBean.ListBean bean=model.getResult().getList().get(i);

                            Model model1=new Model();
                            model1.setFirstImg(bean.getFirstImg());
                            model1.setMark(bean.getMark());
                            model1.setSource(bean.getSource());
                            model1.setTitle(bean.getTitle());
                            model1.setUrl(bean.getUrl());

                            dao.insert(model1);
                        }
                        MyRunable.mainthread(new Runnable() {
                            @Override
                            public void run() {
                                adapter.notifyDataSetChanged();
                            }
                        });
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                Log.e("TAG","网络请求失败"+volleyError.getMessage().toString());
            }
        });
        queue.add(request);
    }
}
