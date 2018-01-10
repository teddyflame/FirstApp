package com.xu.android.fragments;


import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.xu.myfirstapp.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class HeadLinesFragment extends ListFragment{
    OnHeadlineSelectedListener mCallback;

    //主activity 必须实现此接口，才能处理用户点击事件
    public interface OnHeadlineSelectedListener{
        public void onArticleSelected(int position);
    }


    public HeadLinesFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        int layout = android.R.layout.simple_list_item_activated_1;
        setListAdapter(new ArrayAdapter<String>(getActivity(),layout,Ipsum.Headlines));
    }

    @Override
    public void onStart() {
        super.onStart();

        //在横向布局（两个fragment都显示）的界面下，设置listView高亮
        if(getFragmentManager().findFragmentById(R.id.article_fragment)!=null){
            getListView().setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        }
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        //在onAttach阶段确保主Activity确实实现了回调接口，
        //否则抛错。
        try{
            mCallback = (OnHeadlineSelectedListener) activity;
        }catch (ClassCastException e){
            throw new ClassCastException(activity.toString()
                    + "must implement OnHeadlineSelectedListener");
        }
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {

        //通知主Activity，进行item点击。
        mCallback.onArticleSelected(position);
        //设置高亮
        getListView().setItemChecked(position,true);
    }

    //    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container,
//                             Bundle savedInstanceState) {
//        TextView textView = new TextView(getActivity());
//        textView.setText(R.string.hello_blank_fragment);
//        return textView;
//    }

}
