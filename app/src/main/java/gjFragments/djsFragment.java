package gjFragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.easytablemore.BaseFragment;
import com.example.easytablemore.R;

public class djsFragment extends BaseFragment {

    private View rootview;

    private TextView timeDetailCurrent;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootview = inflater.inflate(R.layout.fragment_djs, container,
                false);
        // TODO Auto-generated method stub
        return rootview;
    }

    @Override
    protected void initView(View view) {
        // TODO Auto-generated method stub
        super.initView(view);

    }

    @Override
    protected void initEvents() {
        // TODO Auto-generated method stub
        super.initEvents();
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
