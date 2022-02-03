package com.example.storeapp.home;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.storeapp.R;
import com.example.storeapp.dto.BookDTO;

import java.util.ArrayList;


//코드 참고 https://upcake.tistory.com/294
//https://stickode.tistory.com/166
public class MainFragment extends Fragment {
    private static final String TAG = "mainfragemnt";

    public static BookDTO bookDTO = null;
    RecyclerView recyclerView;
    MyRecyclerViewAdapter adapter;
    ArrayList<BookDTO> bookDTOArrayList;

    //ListSelect listSelect;


    public MainFragment() {
    }

    public static MainFragment newInstance() {
        MainFragment fragment = new MainFragment();
//        Bundle args = new Bundle();
//        args.putString(ARG_PARAM1, param1);
//        args.putString(ARG_PARAM2, param2);
//        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);;


        bookDTOArrayList = new ArrayList<>();
        bookDTOArrayList.add(new BookDTO(1L, "https://lky-bucket-2022.s3.ap-northeast-2.amazonaws.com/1.jpg", "" +
                "아몬드", 12000, "손원평", "창비", "영화와도 같은 강렬한 사건과 매혹적인 문체로 시선을 사로잡는 한국형 영 어덜트 소설 『아몬드』. 타인의 감정에 무감각해진 공감 불능인 이 시대에 큰 울림을 주는 이 작품은 감정을 느끼지 못하는 한 소년의 특별한 성장을 그리고 있다. 감정을 느끼는 데 어려움을 겪는 열여섯 살 소년 선윤재와 어두운 상처를 간직한 곤이, 그와 반대로 맑은 감성을 지닌 도라와 윤재를 돕고 싶어 하는 심 박사 사이에서 펼쳐지는 이야기가 우리로 하여금 타인의 감정을 이해한다는 것이 얼마나 어려운 일인지, 그럼에도 그것이 얼마나 소중한 일인지 다시 한 번 생각해 볼 기회를 전한다."));

        recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);

        adapter = new MyRecyclerViewAdapter(bookDTOArrayList, getContext());
        recyclerView.setAdapter(adapter);


        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        return view;

    }


}