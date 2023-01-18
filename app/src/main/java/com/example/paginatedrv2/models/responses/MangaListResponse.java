package com.example.paginatedrv2.models.responses;

import com.example.paginatedrv2.models.Manga;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MangaListResponse {
    @SerializedName("pagination")
    @Expose
    private MetaPagination pagination;
    @SerializedName("data")
    @Expose
    private List<Manga> data;

    public MetaPagination getPagination() {
        return pagination;
    }

    public List<Manga> getData() {
        return data;
    }

    public static class MetaPagination {
        @SerializedName("last_visible_page")
        @Expose
        private Integer lastVisiblePage;
        @SerializedName("has_next_page")
        @Expose
        private Boolean hasNextPage;
        @SerializedName("items")
        @Expose
        private MetaPaginationItems items;

        public Integer getLastVisiblePage() {
            return lastVisiblePage;
        }

        public Boolean getHasNextPage() {
            return hasNextPage;
        }

        public MetaPaginationItems getItems() {
            return items;
        }

        public static class MetaPaginationItems {
            @SerializedName("count")
            @Expose
            private Integer count;
            @SerializedName("total")
            @Expose
            private Integer total;
            @SerializedName("per_page")
            @Expose
            private Integer perPage;

            public Integer getCount() {
                return count;
            }

            public Integer getTotal() {
                return total;
            }

            public Integer getPerPage() {
                return perPage;
            }
        }
    }
}
