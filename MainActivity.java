package com.example.before_170503_test;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @Bind(R.id.main1_intnet)
    Button main1Intnet;
    @Bind(R.id.main1_sql)
    Button main1Sql;
    @Bind(R.id.activity_main)
    RelativeLayout activityMain;
    private ModelDao dao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);


        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper (this, "Student.db", null);
        SQLiteDatabase database = helper.getWritableDatabase();
        DaoMaster daoMaster = new DaoMaster(database);
        DaoSession daoSession = daoMaster.newSession();
        dao = daoSession.getModelDao();
    }

    @OnClick({R.id.main1_intnet, R.id.main1_sql})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.main1_intnet:

                //跳转到网络获取新闻页面
                Intent intent=new Intent(MainActivity.this,IntnetActivity.class);
                MainActivity.this.startActivity(intent);

                break;

            case R.id.main1_sql:
                //跳转到数据库获取新闻页面
                Intent intent1=new Intent(MainActivity.this,SqlActivity.class);
                MainActivity.this.startActivity(intent1);




                Model model3=new Model();
                model3.setId(4L);
                dao.delete(model3);

                break;
        }
    }
}
