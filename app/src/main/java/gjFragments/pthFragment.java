package gjFragments;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import android.widget.CheckBox;
import com.example.easytablemore.BaseFragment;
import com.example.easytablemore.HttpCallbackListener;
import com.example.easytablemore.HttpUtil;
import com.example.easytablemore.LoginActivity;
import com.example.easytablemore.MainActivity;
import com.example.easytablemore.R;

import message.Constant;
import utils.Utility;

public class pthFragment extends BaseFragment implements View.OnClickListener {

    private View rootview;
    private  Button button;
    private EditText name;
    private EditText hm,show_cj;
    // ÃÜÂë
    private SharedPreferences pref;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootview = inflater.inflate(R.layout.fragment_pth, container,
                false);
        // TODO Auto-generated method stub
        return rootview;
    }

    @Override
    protected void initView(View view) {
        // TODO Auto-generated method stub
        super.initView(view);
        button=(Button)rootview.findViewById(R.id.button);
        name = (EditText) rootview.findViewById(R.id.name);
        hm = (EditText) rootview.findViewById(R.id.hm);
        show_cj = (EditText) rootview.findViewById(R.id.show_cj);
    }

    @Override
    protected void initEvents() {
        // TODO Auto-generated method stub
        super.initEvents();
        button.setOnClickListener(this);
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

    @Override
    public void onClick(View v) {
        String xxm = name.getText().toString().trim();
        String zjj = hm.getText().toString().trim();
        switch(v.getId())
        {
            case R.id.button:
            {
                String web = "http://192.168.43.166:8080/web/user/pth.jsp?name="+ xxm+ "&zj=" + zjj;
                HttpUtil.sendRequest(web,
                        new HttpCallbackListener() {
                            @Override
                            public void onFinish(String response) {
                                show_cj.setText(response);
                                //showResult("ÄúµÄ³É¼¨Îª£º" + response);

                            }
                            @Override
                            public void onError(Exception ex) {
                                ex.printStackTrace();
                            }
                        });
                break;


            }
            default:
                break;
        }

    }

    private void showResult(final String result){
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(getActivity().getApplicationContext(),result,Toast.LENGTH_LONG).show();
            } });
    }
}
