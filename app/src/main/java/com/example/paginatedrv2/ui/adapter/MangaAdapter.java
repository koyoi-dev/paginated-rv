package com.example.paginatedrv2.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.paginatedrv2.R;
import com.example.paginatedrv2.models.Manga;
import com.google.android.material.imageview.ShapeableImageView;

import java.util.ArrayList;
import java.util.List;

public class MangaAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    // View Types
    private static final int ITEM = 0;
    private static final int LOADING = 1;

    private final Context context;
    private final List<Manga> mangas;

    private boolean isLoading = false;

    public MangaAdapter(Context context) {
        this.context = context;
        this.mangas = new ArrayList<>();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());

        if (viewType == ITEM) {
            View view = inflater.inflate(R.layout.item_list, parent, false);
            return new MangaVH(view);
        }

        View view = inflater.inflate(R.layout.item_progress, parent, false);
        return new LoadingVH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (getItemViewType(position) == ITEM) {
            final MangaVH mangaVH = (MangaVH) holder;
            mangaVH.bind(mangas.get(position));
        } else {
            final LoadingVH loadingVH = (LoadingVH) holder;
            loadingVH.bind();
        }
    }

    @Override
    public int getItemCount() {
        return mangas == null ? 0 : mangas.size();
    }

    @Override
    public int getItemViewType(int position) {
        return (position == mangas.size() - 1 && isLoading) ? LOADING : ITEM;
    }

    public void add(Manga m) {
        mangas.add(m);
        notifyItemInserted(mangas.size() - 1);
    }

    public void addAll(List<Manga> mangaList) {
        mangaList.forEach(this::add);
    }

    public void addLoading() {
        isLoading = true;
        add(new Manga());
    }

    public void removeLoading() {
        isLoading = false;

        int position = mangas.size() - 1;
        Manga manga = mangas.get(position);

        if (manga != null) {
            mangas.remove(position);
            notifyItemRemoved(position);
        }
    }

    public static class MangaVH extends RecyclerView.ViewHolder {
        private TextView title;
        private TextView year;
        private TextView description;
        private ShapeableImageView poster;

        public MangaVH(@NonNull View itemView) {
            super(itemView);
            this.title = itemView.findViewById(R.id.movie_title);
            this.year = itemView.findViewById(R.id.movie_year);
            this.description = itemView.findViewById(R.id.movie_desc);
            this.poster = itemView.findViewById(R.id.movie_poster);
        }

        public void bind(Manga manga) {
            title.setText(manga.getTitle());
            year.setText(manga.getPublished().getString());
            description.setText(manga.getSynopsis());
            Glide.with(itemView)
                    .load(manga.getImages().getJpg().getImageUrl())
                    .into(poster);
        }
    }

    public static class LoadingVH extends RecyclerView.ViewHolder {
        public LoadingVH(@NonNull View itemView) {
            super(itemView);
        }

        public void bind() {
            // Showing progress bar
        }
    }
}
