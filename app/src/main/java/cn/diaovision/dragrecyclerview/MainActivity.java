package cn.diaovision.dragrecyclerview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    MyRecyclerView recyclerView;
    RecyclerViewAdapter adapter;
    List<Bean> list = new ArrayList<>();
    //处理拖拽和滑动的实现类
    ItemTouchHelper itemTouchHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager
                .LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);
        recyclerView = (MyRecyclerView) findViewById(R.id.recycler_view);
        adapter=new RecyclerViewAdapter(this,list);
        initData();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
        recyclerView.setHasFixedSize(true);

        recyclerView.addOnItemTouchListener(new OnRecyclerItemClickListener(recyclerView) {
            @Override
            public void onLongClick(RecyclerView.ViewHolder vh) {
                if (vh.getLayoutPosition()!=list.size()-1) {
                    Toast.makeText(MainActivity.this,"长按了",Toast.LENGTH_SHORT).show();
                    itemTouchHelper.startDrag(vh);
                    //VibratorUtil.Vibrate(getActivity(), 70);   //震动70ms
                }
            }
        });

        itemTouchHelper = new ItemTouchHelper(new ItemTouchHelperCallBack(adapter));
        //和RecyclerView进行关联
        itemTouchHelper.attachToRecyclerView(recyclerView);
    }

    private void initData() {
        for (int i = 0; i < 15; i++) {
            Bean bean=new Bean(i);
            list.add(bean);
        }
        adapter.notifyDataSetChanged();
    }
}
