package com.example.storeapp.home;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.storeapp.R;
import com.example.storeapp.databinding.ItemBinding;
import com.example.storeapp.dto.BookDTO;

import java.util.ArrayList;

//코드 참고
// ->프래그먼트에 리사이클러뷰 만들기 https://stickode.tistory.com/166
// ->안드로이드 스프링 연동 리사이클러뷰 만들기 https://upcake.tistory.com/294
// ->리사이클러뷰 선택 시 프래그먼트 전환 https://stackoverflow.com/questions/62709032/how-to-move-adapter-to-fragment
public class MyRecyclerViewAdapter extends RecyclerView.Adapter<MyRecyclerViewAdapter.MyViewHolder>{
    private final ArrayList<BookDTO> bookDTOArrayList;
    private Context context;

    int pos;

    String getContentsNum;
    private final String TAG = "myRecyclerViewAdapter";


    public MyRecyclerViewAdapter(ArrayList<BookDTO> bookDTOArrayList, Context context) {
        this.bookDTOArrayList = bookDTOArrayList;
        this.context = context;
    }

    //뷰 홀더
    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView tvName, tvWriter, tvCompany, tvPrice;
        public ImageView imageView;

        public MyViewHolder(View view) {
            super(view);
            this.tvName = (TextView) view.findViewById(R.id.book_name);
            this.tvWriter = (TextView) view.findViewById(R.id.book_writer);
            this.tvCompany = (TextView) view.findViewById(R.id.book_com);
            this.imageView = (ImageView) view.findViewById(R.id.imageView);
            this.tvPrice = (TextView) view.findViewById(R.id.book_price);


        }
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Log.d(TAG,"onCreateViewHolder");
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false); //view연결
        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        String id, name, writer, company, price, detail, url;
        BookDTO bookDTOPosition = bookDTOArrayList.get(position);
        id = Long.toString(bookDTOPosition.getId());
        url = bookDTOPosition.getUrl();
        name = bookDTOPosition.getName();
        writer = bookDTOPosition.getWriter();
        company = bookDTOPosition.getCompany();
        price = bookDTOPosition.getPrice();
        detail = bookDTOPosition.getDetail();

        holder.tvName.setText(name);
        holder.tvWriter.setText(writer);
        holder.tvCompany.setText(company);
        holder.tvPrice.setText(price+"원");

        context = holder.itemView.getContext();

        Glide.with(context).load(bookDTOArrayList.get(position).getUrl()).into(holder.imageView);


        holder.imageView.setOnClickListener(v -> {
            //DetailFragment fragment = new DetailFragment();
            FragmentManager fragmentManager = ((FragmentActivity)context).getSupportFragmentManager();
            FragmentTransaction ft = fragmentManager.beginTransaction();
            ft.replace(R.id.frameLayout, DetailFragment.newInstance(id, name, url, price, writer, company, detail));
            ft.addToBackStack(null);
            ft.commit();

        });

    }

    @Override
    public int getItemCount() {
        //return (bookDTOArrayList != null ? bookDTOArrayList.size() : 0);
        Log.d("size", bookDTOArrayList.size() + "");
        return bookDTOArrayList.size();
    }

    public void removeAllItem() {
        bookDTOArrayList.clear();
    }

}
