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
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.storeapp.R;
import com.example.storeapp.databinding.ItemBinding;
import com.example.storeapp.dto.BookDTO;

import java.util.ArrayList;

//코드 참고
// ->프래그먼트에 리사이클러뷰 만들기 https://stickode.tistory.com/166
// ->안드로이드 스프링 연동 리사이클러뷰 만들기 https://upcake.tistory.com/294
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

//            view.setClickable(true);
//            view.setOnClickListener(v -> {
//                pos = getAdapterPosition();
//                if (pos!=RecyclerView.NO_POSITION) {
//                    BookDTO bookDTO = bookDTOArrayList.get(pos);
//
//                    Long id = bookDTO.getId();
//                    String url = bookDTO.getUrl();
//                    String name = bookDTO.getName();
//                    String price = bookDTO.getPrice();
//                    String writer = bookDTO.getWriter();
//                    String company = bookDTO.getCompany();
//                    String detail = bookDTO.getDetail();
//
//                    Intent intent = new Intent(context, DetailFragment.class).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                    intent.putExtra("id", id);
//                    intent.putExtra("url", url);
//                    intent.putExtra("price", price);
//                    intent.putExtra("writer", writer);
//                    intent.putExtra("name", name);
//                    intent.putExtra("company", company);
//                    intent.putExtra("detail", detail);
//
//                    context.startActivity(intent);
//                }
//
//            });

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
        BookDTO bookDTOPosition = bookDTOArrayList.get(position);
        holder.tvName.setText(bookDTOPosition.getName());
        holder.tvWriter.setText(bookDTOPosition.getWriter());
        holder.tvCompany.setText(bookDTOPosition.getCompany());
        holder.tvPrice.setText(bookDTOPosition.getPrice()+"원");

        context = holder.itemView.getContext();

        Glide.with(context).load(bookDTOArrayList.get(position).getUrl()).into(holder.imageView);

//        holder.itemView.setOnClickListener(v -> {
//            AppCompatActivity activity = (AppCompatActivity) v.getContext();
//            activity.getFragmentManager().beginTransaction().replace(R.id.detail_frag, new DetailFragment()).addToBackStack(null).commit();
//        });
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
