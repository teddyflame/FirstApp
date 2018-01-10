package com.xu.android.fragments;

import android.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.xu.myfirstapp.R;

public class FragmentActivity extends android.support.v4.app.FragmentActivity
    implements HeadLinesFragment.OnHeadlineSelectedListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.news_articles);

        //判断是否是含有容器的布局界面
        if (findViewById(R.id.fragment_container) != null) {
            if (savedInstanceState != null) {
                return;
            }

            //如果是，先创建headline并添加。
            HeadLinesFragment firstFragment = new HeadLinesFragment();

            firstFragment.setArguments(getIntent().getExtras());


            getSupportFragmentManager().beginTransaction()
                    .add(R.id.fragment_container, firstFragment).commit();

        }
    }


    public void onArticleSelected(int position){

        ArticleFragment articleFragment = (ArticleFragment)
                getSupportFragmentManager().findFragmentById(R.id.article_fragment);

        if(articleFragment !=null){
            //如果不为空，说明在两个frag都显示的界面

            articleFragment.updateArticleView(position);
        }else{
            //如果为空，则在只能显示一个frag的界面

            ArticleFragment newFragment = new ArticleFragment();

            Bundle args = new Bundle();

            args.putInt(ArticleFragment.ARG_POSITION,position);

            newFragment.setArguments(args);

            android.support.v4.app.FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

            transaction.replace(R.id.fragment_container,newFragment);

            transaction.addToBackStack(null);

            transaction.commit();
        }
    }
}
