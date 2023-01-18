package com.example.paginatedrv2.ui;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.paginatedrv2.api.JikanApi;
import com.example.paginatedrv2.api.MangaService;
import com.example.paginatedrv2.databinding.ActivityMainBinding;
import com.example.paginatedrv2.models.Manga;
import com.example.paginatedrv2.models.responses.MangaListResponse;
import com.example.paginatedrv2.ui.adapter.MangaAdapter;
import com.example.paginatedrv2.utils.PaginationScrollListener;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    ActivityMainBinding binding;
    MangaService mangaService;
    MangaAdapter mangaAdapter;

    // Pagination
    private static final int PAGE_START = 1;
    private boolean isLoading = false;
    private boolean isLastPage = false;
    private int currentPage = PAGE_START;
    private static int TOTAL_PAGES = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        RecyclerView rv = binding.mainRecycler;

        mangaAdapter = new MangaAdapter(this);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this,
                LinearLayoutManager.VERTICAL, false);
        rv.setLayoutManager(linearLayoutManager);
        rv.setAdapter(mangaAdapter);

        rv.addOnScrollListener(new PaginationScrollListener(linearLayoutManager) {
            @Override
            protected void loadMoreItems() {
                isLoading = true;
                currentPage += 1;

                Log.d(TAG, "loadMoreItems: currentPage: " + currentPage);
                Log.d(TAG, "loadMoreItems: TOTAL_PAGES: " + TOTAL_PAGES);
                loadNextPage();
            }

            @Override
            protected boolean isLastPage() {
                return isLastPage;
            }

            @Override
            protected boolean isLoading() {
                return isLoading;
            }
        });

        mangaService = JikanApi.getClient().create(MangaService.class);
        loadFirstPage();
    }

    private Call<MangaListResponse> getMangaList() {
        return mangaService.getMangaList(currentPage);
    }

    private List<Manga> extractResults(Response<MangaListResponse> response) {
        // throw an error if the response is not successful or the body is null
        if (!response.isSuccessful() || response.body() == null) {
            throw new RuntimeException("Response is not successful or the body is null");
        }

        return response.body().getData();
    }

    private void loadFirstPage() {
        Log.d(TAG, "loadFirstPage() called");
        currentPage = PAGE_START;
        getMangaList().enqueue(new Callback<MangaListResponse>() {
            @Override
            public void onResponse(Call<MangaListResponse> call, Response<MangaListResponse> response) {
                List<Manga> mangas = extractResults(response);
                binding.mainProgress.setVisibility(View.GONE);
                mangaAdapter.addAll(mangas);
                TOTAL_PAGES = response.body().getPagination().getItems().getTotal();

                if (currentPage <= TOTAL_PAGES) mangaAdapter.addLoading();
                else isLastPage = true;
            }

            @Override
            public void onFailure(Call<MangaListResponse> call, Throwable t) {
                Log.e(TAG, "onFailure: ", t);
            }
        });
    }

    private void loadNextPage() {
        Log.d(TAG, "loadNextPage() called");

        getMangaList().enqueue(new Callback<MangaListResponse>() {
            @Override
            public void onResponse(Call<MangaListResponse> call, Response<MangaListResponse> response) {
                mangaAdapter.removeLoading();
                isLoading = false;

                List<Manga> mangas = extractResults(response);
                mangaAdapter.addAll(mangas);

                if (currentPage != TOTAL_PAGES) mangaAdapter.addLoading();
                else isLastPage = true;
            }

            @Override
            public void onFailure(Call<MangaListResponse> call, Throwable t) {
                Log.e(TAG, "onFailure: ", t);
            }
        });
    }
}