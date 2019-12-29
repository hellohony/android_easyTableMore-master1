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

public class chooseFragment extends BaseFragment implements View.OnClickListener {

    private View rootview;
    private Button jwcjchaxun;
    private Button sljchaxun;
    private Button pthchaxun;
    private Button djchaxun;

    private TextView timeDetailCurrent;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootview = inflater.inflate(R.layout.fragment_choose, container,
                false);
        // TODO Auto-generated method stub
        return rootview;
    }

    @Override
    protected void initView(View view) {
        // TODO Auto-generated method stub
        super.initView(view);
        jwcjchaxun = (Button) rootview.findViewById(R.id.jwcjchaxun);
        sljchaxun=(Button) rootview.findViewById(R.id.sljchaxun);
        pthchaxun=(Button) rootview.findViewById(R.id.pthchaxun);
        djchaxun=(Button) rootview.findViewById(R.id.djchaxun);
    }

    @Override
    protected void initEvents() {
        // TODO Auto-generated method stub
        super.initEvents();
        jwcjchaxun.setOnClickListener(this);
        sljchaxun.setOnClickListener(this);
        pthchaxun.setOnClickListener(this);
        djchaxun.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.jwcjchaxun:
                FragmentTransaction transactionAbout = getActivity()
                        .getSupportFragmentManager().beginTransaction();
                jwcjFragment chFragment = new jwcjFragment();
                transactionAbout.add(android.R.id.content, chFragment,
                        "jwcjFragment");
                transactionAbout.addToBackStack("jwcjFragment");// 添加fragment到Activity的回退栈中
                transactionAbout.show(chFragment);
                transactionAbout.commit();
                break;
            case R.id.sljchaxun:
                FragmentTransaction transactionTermDF = getActivity()
                        .getSupportFragmentManager().beginTransaction();
                sljFragment termDetailFragment = new sljFragment();
                transactionTermDF.add(android.R.id.content, termDetailFragment,
                        "TermDetailFragment");
                transactionTermDF.addToBackStack("TermDetailFragment");
                transactionTermDF.show(termDetailFragment);
                transactionTermDF.commit();
                break;
            case R.id.pthchaxun:
                FragmentTransaction transactionTerm = getActivity()
                        .getSupportFragmentManager().beginTransaction();
                pthFragment pFragment = new pthFragment();
                transactionTerm.add(android.R.id.content, pFragment,
                        "pthFragment");
                transactionTerm.addToBackStack("pthFragment");
                transactionTerm.show(pFragment);
                transactionTerm.commit();
                break;
            case R.id.djchaxun:
                FragmentTransaction transaction = getActivity()
                        .getSupportFragmentManager().beginTransaction();
                djFragment p = new djFragment();
                transaction.add(android.R.id.content, p,
                        "pthFragment");
                transaction.addToBackStack("pthFragment");
                transaction.show(p);
                transaction.commit();
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
