package com.example.storeapp.home;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.storeapp.R;
import com.example.storeapp.dto.BookDTO;
import com.example.storeapp.response.ProductResponse;
import com.example.storeapp.task.MyAPI;
import com.example.storeapp.task.RetrofitClient;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


//코드 참고 https://upcake.tistory.com/294
//https://stickode.tistory.com/166
public class MainFragment extends Fragment {
    private static final String TAG = "mainfragment";

    public static BookDTO bookDTO = null;
    RecyclerView recyclerView;
    MyRecyclerViewAdapter adapter;
    ArrayList<BookDTO> bookDTOArrayList = new ArrayList<>();

    private RetrofitClient retrofitClient;
    private MyAPI myAPI;

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


        bookDTOArrayList.clear();
        retrofitClient = RetrofitClient.getInstance();
        myAPI = RetrofitClient.getRetrofitInterface();

        recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);

        myAPI.getAllProducts().enqueue(new Callback<List<ProductResponse>>(

        ) {
            @Override
            public void onResponse(Call<List<ProductResponse>> call, Response<List<ProductResponse>> response) {
                if(response.isSuccessful()) {
                    List<ProductResponse> result = response.body();

                    for (ProductResponse item : result) {
                        bookDTOArrayList.add(new BookDTO(item.getId(), item.getUrl(), item.getName(), item.getPrice()
                                , item.getWriter(), item.getCompany(), item.getDetail()));
                        Log.d("bookdto", item.getName());
                    }
                    adapter = new MyRecyclerViewAdapter(bookDTOArrayList, getContext());
                    recyclerView.setAdapter(adapter);
                }else {
                    Log.d("bookdto", "response fail");
                }
            }

            @Override
            public void onFailure(Call<List<ProductResponse>> call, Throwable t) {
                Log.d("bookdto", "연결 실패");
                t.printStackTrace();
            }
        });





        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        return view;

    }


}