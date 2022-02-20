package com.example.storeapp.home;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.storeapp.R;
import com.example.storeapp.databinding.ActivityDetailFragmentBinding;

//코드 참고
// ->리사이클러뷰 선택 시 프래그먼트 전환 https://stackoverflow.com/questions/62709032/how-to-move-adapter-to-fragment
// ->이미지뷰 url https://jizard.tistory.com/179
public class DetailFragment extends Fragment {
    private static final String TAG = "detailFragment";
    private static final String pid = "param1";
    private static final String pname = "param2";
    private static final String purl = "param3";
    private static final String pwriter = "param4";
    private static final String pdetail = "param5";
    private static final String pcompany = "param6";
    private static final String pprice = "param7";
    String id, name, url, writer, price, company, detail;
    TextView tv_name, tv_wrcom, tv_price, tv_detail;
    ImageView imv;

    public DetailFragment() {
    }

    public static DetailFragment newInstance(String p_id, String p_name, String p_url, String p_price,
                                             String p_writer, String p_company, String p_detail) {
        DetailFragment fragment = new DetailFragment();
        Bundle args = new Bundle();
        args.putString(pid, p_id);
        args.putString(pname, p_name);
        args.putString(purl, p_url);
        args.putString(pwriter, p_writer);
        args.putString(pprice, p_price);
        args.putString(pcompany, p_company);
        args.putString(pdetail, p_detail);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            id = getArguments().getString(pid);
            name = getArguments().getString(pname);
            url = getArguments().getString(purl);
            writer = getArguments().getString(pwriter);
            company = getArguments().getString(pcompany);
            detail = getArguments().getString(pdetail);
            price = getArguments().getString(pprice);
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_detail_fragment, container, false);


        tv_name = (TextView)view.findViewById(R.id.tv_name);
        tv_wrcom = (TextView)view.findViewById(R.id.tv_wrcom);
        imv = (ImageView)view.findViewById(R.id.imgv_detail);

        String str = writer + " | " + company;

        tv_name.setText(name);
        tv_wrcom.setText(str);

        Glide.with(this).load(url).into(imv);




        return view;

    }


}