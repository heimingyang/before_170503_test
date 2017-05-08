package com.example.before_170503_test;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ListView;
import android.widget.RelativeLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import de.greenrobot.dao.query.Query;
import de.greenrobot.dao.query.QueryBuilder;

public class SqlActivity extends AppCompatActivity {

    @Bind(R.id.activity_sql)
    RelativeLayout activitySql;
    @Bind(R.id.aql_lv)
    ListView aqlLv;
    private ModelDao dao;
    private ArrayList<Model> list1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sql);
        ButterKnife.bind(this);

        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(this, "Student.db", null);
        SQLiteDatabase database = helper.getWritableDatabase();
        DaoMaster daoMaster = new DaoMaster(database);
        DaoSession daoSession = daoMaster.newSession();
        dao = daoSession.getModelDao();

        QueryBuilder<Model> teacherQueryBuilder = dao.queryBuilder();
        Query<Model> query = teacherQueryBuilder.build();
        List<Model> list = query.list();

        list1 = (ArrayList<Model>) list;
        MAdapter adapter = new MAdapter(list1);
        aqlLv.setAdapter(adapter);


    }
}
