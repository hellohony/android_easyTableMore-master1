package gjFragments;

import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;

import android.widget.ImageView;


import com.example.easytablemore.BaseFragment;

import com.example.easytablemore.R;


import noteFragments.NoteFragment;
import userFragments.UserFragment;


public class GjFragment  extends BaseFragment  implements OnClickListener{
    private View rootview;
    private ImageView cjchaxun,bj,djs,more;
    private ImageView xytt,bbjj;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootview = inflater.inflate(R.layout.fragment_gj, container,
                false);
        return rootview;
    }
    @Override
    protected void initView(View view) {
        super.initView(view);
        cjchaxun = (ImageView) rootview.findViewById(R.id.cjchaxun);
        bj = (ImageView) rootview.findViewById(R.id.bj);
        bbjj = (ImageView) rootview.findViewById(R.id.bbjj);
        djs = (ImageView) rootview.findViewById(R.id.djs);
        more = (ImageView) rootview.findViewById(R.id.more);
        xytt = (ImageView) rootview.findViewById(R.id.xytt);
    }
    @Override
    protected void initEvents() {
        super.initEvents();
        cjchaxun.setOnClickListener(this);
        bj.setOnClickListener(this);
        djs.setOnClickListener(this);
        more.setOnClickListener(this);
        xytt.setOnClickListener(this);
        bbjj.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.cjchaxun:
                FragmentTransaction transactionAbout = getActivity()
                        .getSupportFragmentManager().beginTransaction();
                chooseFragment chFragment = new chooseFragment();
                transactionAbout.add(android.R.id.content, chFragment,
                        "chooseFragment");
                transactionAbout.addToBackStack("chooseFragment");// 添加fragment到Activity的回退栈中
                transactionAbout.show(chFragment);
                transactionAbout.commit();
                break;
            case R.id.bj:
                //Toast.makeText(getActivity(), 666, Toast.LENGTH_SHORT).show();
                FragmentTransaction transactionAbout2 = getActivity()
                        .getSupportFragmentManager().beginTransaction();
                NoteFragment NoteFragment = new NoteFragment();
                transactionAbout2.add(android.R.id.content, NoteFragment,
                        "NoteFragment");
                transactionAbout2.addToBackStack("NoteFragment");// 添加fragment到Activity的回退栈中
                transactionAbout2.show(NoteFragment);
                transactionAbout2.commit();
                break;
            case R.id.djs:
                //Toast.makeText(getActivity(), 666, Toast.LENGTH_SHORT).show();
                FragmentTransaction djs = getActivity()
                        .getSupportFragmentManager().beginTransaction();
                djsFragment djsFragment = new djsFragment();
                djs.add(android.R.id.content, djsFragment,
                        "djsFragment");
                djs.addToBackStack("djsFragment");// 添加fragment到Activity的回退栈中
                djs.show(djsFragment);
                djs.commit();
                break;
            case R.id.more:

                FragmentTransaction user = getActivity()
                        .getSupportFragmentManager().beginTransaction();
                nothingFragment nothingFragment = new nothingFragment();
                user.add(android.R.id.content, nothingFragment,
                        "nothingFragment");
                user.addToBackStack("nothingFragment");// 添加fragment到Activity的回退栈中
                user.show(nothingFragment);
                user.commit();
                break;
            case R.id.xytt:
                FragmentTransaction transaction = getActivity()
                        .getSupportFragmentManager().beginTransaction();
                pzFragment chFrag = new pzFragment();
                transaction.add(android.R.id.content, chFrag,
                        "chooseFragment");
                transaction.addToBackStack("chooseFragment");// 添加fragment到Activity的回退栈中
                transaction.show(chFrag);
                transaction.commit();
                break;
            case R.id.bbjj:
                FragmentTransaction trans = getActivity()
                        .getSupportFragmentManager().beginTransaction();
                NoteFragment NoteFragment1 = new NoteFragment();
                trans.add(android.R.id.content, NoteFragment1,
                        "NoteFragment");
                trans.addToBackStack("chooseFragment");// 添加fragment到Activity的回退栈中
                trans.show(NoteFragment1);
                trans.commit();
                break;
            default:
                break;
        }
    }
}
