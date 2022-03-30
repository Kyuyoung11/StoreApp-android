package com.example.storeapp.home;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.storeapp.R;
import com.example.storeapp.dto.BookDTO;
import com.example.storeapp.dto.GlobalVar;
import com.example.storeapp.request.StringRequest;
import com.example.storeapp.response.ProductResponse;
import com.example.storeapp.task.MyAPI;
import com.example.storeapp.task.RetrofitClient;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CartFragment extends Fragment {
    private static final String TAG = "cartFragment";

    //LayoutInflater inflater;
    View footer;
    LinearLayout lin;


    public static BookDTO bookDTO = null;
    RecyclerView recyclerView;
    MyRecyclerViewAdapter adapter;
    ArrayList<BookDTO> bookDTOArrayList = new ArrayList<>();

    private RetrofitClient retrofitClient;
    private MyAPI myAPI;

    private static final String uid = "param1";
    private Long id;

    public CartFragment() {
    }

    public static CartFragment newInstance(String p_id) {
        CartFragment fragment = new CartFragment();
        Bundle args = new Bundle();
        args.putString(uid, p_id);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

        footer = getLayoutInflater().inflate(R.layout.activity_fragment, null);

        lin = (LinearLayout) footer.findViewById(R.id.lin_bar);
        lin.setVisibility(View.VISIBLE);

        if (getArguments() != null) {
            id = Long.parseLong(getArguments().getString(uid).trim());
        }

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_cart_fragment, container, false);




        bookDTOArrayList.clear();

        recyclerView = view.findViewById(R.id.cart_recyler);
        recyclerView.setHasFixedSize(true);


        retrofitClient = RetrofitClient.getInstance();
        myAPI = RetrofitClient.getRetrofitInterface();

        myAPI.getCartProducts(id).enqueue(new Callback<List<ProductResponse>>() {
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