package com.example.storeapp.home;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.storeapp.R;
import com.example.storeapp.databinding.ItemBinding;
import com.example.storeapp.dto.BookDTO;

import java.util.ArrayList;

//코드 참고 -> https://stickode.tistory.com/166
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

            view.setClickable(true);
            view.setOnClickListener(v -> {
                pos = getAdapterPosition();
                Toast.makeText(v.getContext(), pos ,Toast.LENGTH_SHORT).show();

            });

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
        holder.tvPrice.setText(bookDTOPosition.getPrice());

        context = holder.itemView.getContext();

        Glide.with(context).load(bookDTOArrayList.get(position).getUrl()).into(holder.imageView);

    }

    @Override
    public int getItemCount() {
        return (bookDTOArrayList != null ? bookDTOArrayList.size() : 0);
    }
}
