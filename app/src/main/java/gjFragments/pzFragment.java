package gjFragments;


import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.LocalBroadcastManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.easytablemore.BaseFragment;
import com.example.easytablemore.LoginActivity;
import com.example.easytablemore.R;
import com.example.easytablemore.Zhuce;

import userFragments.AboutUsFragment;
import userFragments.TermDetailFragment;
import userFragments.TimeDetailFragment;
import utils.HorizontalProgressBarWithNumber;
import utils.SQLiteDBUtil;

import com.example.easytablemore.BaseFragment;
import com.example.easytablemore.R;

public class pzFragment extends BaseFragment implements View.OnClickListener{

    private View rootview;
    private ImageView picture1;
    private ImageView picture2;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootview = inflater.inflate(R.layout.fragment_pz, container,
                false);
        // TODO Auto-generated method stub
        return rootview;
    }
    @Override
    protected void initView(View view) {
        // TODO Auto-generated method stub
        super.initView(view);
        picture1 = (ImageView) rootview.findViewById(R.id.picture1);
        picture2=(ImageView) rootview.findViewById(R.id.picture2);

    }
    @Override
    protected void initEvents() {
        // TODO Auto-generated method stub
        super.initEvents();
        picture1.setOnClickListener(this);
        picture2.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.picture1:
                FragmentTransaction transactionAbout = getActivity()
                        .getSupportFragmentManager().beginTransaction();
                ts1Fragment chFragment = new ts1Fragment();
                transactionAbout.add(android.R.id.content, chFragment,
                        "ts1Fragment");
                transactionAbout.addToBackStack("ts1Fragment");// 添加fragment到Activity的回退栈中
                transactionAbout.show(chFragment);
                transactionAbout.commit();
                break;
            case R.id.picture2:
                FragmentTransaction transactionTermDF = getActivity()
                        .getSupportFragmentManager().beginTransaction();
                ts2Fragment termDetailFragment = new ts2Fragment();
                transactionTermDF.add(android.R.id.content, termDetailFragment,
                        "ts2DetailFragment");
                transactionTermDF.addToBackStack("ts2DetailFragment");
                transactionTermDF.show(termDetailFragment);
                transactionTermDF.commit();
                break;
            default:
                break;
        }
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onViewCreated(view, savedInstanceState);
        view.findViewById(R.id.image_back).setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        getFragmentManager().popBackStack();
                    }
                });
    }

}