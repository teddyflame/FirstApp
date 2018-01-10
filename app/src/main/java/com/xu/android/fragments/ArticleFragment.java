package com.xu.android.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.xu.myfirstapp.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class ArticleFragment extends Fragment {

    final static String ARG_POSITION = "position";
    int mCurrentPosition = -1;


    public ArticleFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if(savedInstanceState!=null){
            mCurrentPosition = savedInstanceState.getInt(ARG_POSITION);
        }

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_article, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();

        //检查有没有fragment传过来的args
        Bundle args = getArguments();

        //如果有，根据传参确定点击位置
        if(args!=null){
            updateArticleView(args.getInt(ARG_POSITION));
        }else if(mCurrentPosition!=-1){
            //否则，根据savedInstance确定
            updateArticleView(mCurrentPosition);
        }
    }

    public void updateArticleView(int position){
        TextView article=(TextView) getActivity().findViewById(R.id.article);
        article.setText(Ipsum.Articles[position]);

        mCurrentPosition = position;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putInt(ARG_POSITION,mCurrentPosition);
    }
}
