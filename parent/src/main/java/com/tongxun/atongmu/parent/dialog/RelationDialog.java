package com.tongxun.atongmu.parent.dialog;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;

import com.hyphenate.util.DensityUtil;
import com.tongxun.atongmu.parent.R;
import com.tongxun.atongmu.parent.model.FamilyModel;

import java.util.List;

/**
 * Created by Jayce on 2016/9/13.
 */
public class RelationDialog extends BaseDialog {
    private List<FamilyModel> list;
    private GridView gridView;
    private Context context;
    private MyAdapter adapter;
    private GoRelationDialog goRelationDialog;

    public RelationDialog(Context context, GoRelationDialog goRelationDialog, List<FamilyModel> list) {
        super(context, View.inflate(context, R.layout.dlg_relation, null), new ViewGroup.LayoutParams(DensityUtil.dip2px(context, ViewGroup.LayoutParams.MATCH_PARENT), DensityUtil.dip2px(context,
                ViewGroup.LayoutParams.MATCH_PARENT)));
        this.list = list;
        this.context = context;
        this.goRelationDialog = goRelationDialog;
        init();
    }

    private void init() {
        gridView = (GridView) findViewById(R.id.dlg_rela_grid);
        adapter = new MyAdapter(context,list);
        gridView.setAdapter(adapter);
    }

    public interface GoRelationDialog{
        void go(String text);
    }

    class MyAdapter extends BaseAdapter {
        LayoutInflater inflater;
        ViewHolder holder = null;
        Context context;
        List<FamilyModel> list;

        public MyAdapter(Context context, List<FamilyModel> list){
            this.context = context;
            this.list = list;
            inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }

        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public Object getItem(int i) {
            return list.get(i);
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(final int i, View view, ViewGroup viewGroup) {
            if (view == null){
                holder = new ViewHolder();
                view = inflater.inflate(R.layout.rela_item,null);
                holder.btnRela = (Button) view.findViewById(R.id.btn_rela);
                view.setTag(holder);
            }else {
                holder = (ViewHolder) view.getTag();
            }
            final String name = list.get(i).getRelation();
            holder.btnRela.setText(name);
            holder.btnRela.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    goRelationDialog.go(name);
                }
            });
            return view;
        }

        public final class ViewHolder{
            public Button btnRela;
        }

    }
}
