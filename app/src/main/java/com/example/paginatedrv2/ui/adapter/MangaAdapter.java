package com.example.paginatedrv2.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.paginatedrv2.databinding.ItemListBinding;
import com.example.paginatedrv2.databinding.ItemProgressBinding;
import com.example.paginatedrv2.models.Manga;

import java.util.ArrayList;
import java.util.List;

public class MangaAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    // View Types
    private static final int ITEM = 0;
    private static final int LOADING = 1;

    private Context context;
    private List<Manga> mangas;

    private boolean isLoading = false;

    public MangaAdapter(Context context) {
        this.context = context;
        this.mangas = new ArrayList<>();
    }

    public List<Manga> getMangas() {
        return mangas;
    }

    public void setMangas(List<Manga> mangas) {
        this.mangas = mangas;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());

        if (viewType == ITEM) {
            ItemListBinding itemListBinding = ItemListBinding.inflate(inflater, parent, false);
            return new MangaVH(itemListBinding);
        }

        ItemProgressBinding binding = ItemProgressBinding.inflate(inflater, parent, false);
        return new LoadingVH(binding);
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
        private final ItemListBinding binding;

        public MangaVH(@NonNull ItemListBinding itemBinding) {
            super(itemBinding.getRoot());
            this.binding = itemBinding;
        }

        public void bind(Manga manga) {
            binding.movieTitle.setText(manga.getTitle());
            binding.movieYear.setText(manga.getPublished().getString());
            binding.movieDesc.setText(manga.getSynopsis());
            Glide.with(binding.getRoot())
                    .load(manga.getImages().getJpg().getImageUrl())
                    .into(binding.moviePoster);
        }
    }

    public static class LoadingVH extends RecyclerView.ViewHolder {
        private final ItemProgressBinding binding;

        public LoadingVH(@NonNull ItemProgressBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bind() {
            // Showing progress bar
        }
    }
}
