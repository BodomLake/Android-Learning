package com.bodomlake.jetpack.paging.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.paging.PagedListAdapter;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.bodomlake.helloworld.databinding.ActivityPagingBinding;
import com.squareup.picasso.Picasso;
import com.bodomlake.helloworld.R;
import com.bodomlake.jetpack.paging.model.Movie;

public class MoviePagedListAdapter extends PagedListAdapter<Movie, MoviePagedListAdapter.MovieViewHolder> {

    private static final DiffUtil.ItemCallback<Movie> DIFF_CALLBACK = new DiffUtil.ItemCallback<Movie>() {
        @Override
        public boolean areItemsTheSame(@NonNull Movie oldItem, @NonNull Movie newItem) {
            // return false;
            return newItem == oldItem;
        }

        @Override
        public boolean areContentsTheSame(@NonNull Movie oldItem, @NonNull Movie newItem) {
            // return false;
            return oldItem.equals(newItem);
        }
    };

    private final Context context;

    // DiffUtil.ItemCallback<Movie> diffCallback
    public MoviePagedListAdapter(Context context) {
        super(DIFF_CALLBACK);
        this.context = context;
    }

    @NonNull
    @Override
    public MoviePagedListAdapter.MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // View root = LayoutInflater.from(context).inflate(R.layout.recycle_view_item, parent, false);
        // from聚焦于上下文，inflate出View对象
        View root = LayoutInflater.from(context).inflate(R.layout.recycle_view_item, parent, false);
        // 从含有 <data></data>的 View对象中，套取binding信息
        ActivityPagingBinding binding = DataBindingUtil.getBinding(root);
        // return new MovieViewHolder(root);
        return new MovieViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder holder, int position) {
        Movie movie = getItem(position);
        if (null != movie) {
            Picasso.get().load(movie.cover)
                    .placeholder(R.drawable.ic_launcher_foreground)
                    .error(R.drawable.ic_launcher_background)
                    .into(holder.iv);
            String shortTitle = movie.title.length() >= 8 ? movie.title.substring(0, 7) : movie.title;
            holder.tvRate.setText(movie.rate);
            holder.tvTitle.setText(shortTitle);
        }
    }

    // item 的 holder
    public class MovieViewHolder extends RecyclerView.ViewHolder {
        private ImageView iv;
        private TextView tvTitle;
        private TextView tvRate;

/*        public MovieViewHolder(@NonNull View itemView) {
            super(itemView);
            this.iv = itemView.findViewById(R.id.imageView);
            // this.tvTitle = itemView.findViewById(R.id.textView);
            // this.tvRate = itemView.findViewById(R.id.textView);
        }
        */

        public MovieViewHolder(@NonNull ActivityPagingBinding binding) {
            super(binding.getRoot());
            // this.iv = itemView.findViewById(R.id.imageView);
            // this.tvTitle = itemView.findViewById(R.id.textView);
            // this.tvRate = itemView.findViewById(R.id.textView);
            this.iv = binding.getRoot().findViewById(R.id.imageView);
            this.tvTitle = binding.getRoot().findViewById(R.id.textViewTitle);
            this.tvRate = binding.getRoot().findViewById(R.id.textViewRate);
        }

    }
}
