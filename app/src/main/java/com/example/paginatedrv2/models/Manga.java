package com.example.paginatedrv2.models;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Manga {


    @SerializedName("mal_id")
    @Expose
    private Integer malId;
    @SerializedName("url")
    @Expose
    private String url;
    @SerializedName("images")
    @Expose
    private Images images;
    @SerializedName("approved")
    @Expose
    private Boolean approved;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("title_english")
    @Expose
    private String titleEnglish;
    @SerializedName("title_japanese")
    @Expose
    private String titleJapanese;
    @SerializedName("title_synonyms")
    @Expose
    private List<Object> titleSynonyms = null;
    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("chapters")
    @Expose
    private Integer chapters;
    @SerializedName("volumes")
    @Expose
    private Integer volumes;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("publishing")
    @Expose
    private Boolean publishing;
    @SerializedName("published")
    @Expose
    private Published published;
    @SerializedName("score")
    @Expose
    private Double score;
    @SerializedName("scored")
    @Expose
    private Double scored;
    @SerializedName("scored_by")
    @Expose
    private Integer scoredBy;
    @SerializedName("rank")
    @Expose
    private Integer rank;
    @SerializedName("popularity")
    @Expose
    private Integer popularity;
    @SerializedName("members")
    @Expose
    private Integer members;
    @SerializedName("favorites")
    @Expose
    private Integer favorites;
    @SerializedName("synopsis")
    @Expose
    private String synopsis;
    @SerializedName("background")
    @Expose
    private String background;
    @SerializedName("authors")
    @Expose
    private List<Author> authors = null;
    @SerializedName("genres")
    @Expose
    private List<Genre> genres = null;

    public Integer getMalId() {
        return malId;
    }

    public String getUrl() {
        return url;
    }

    public Images getImages() {
        return images;
    }

    public Boolean getApproved() {
        return approved;
    }

    public String getTitle() {
        return title;
    }

    public String getTitleEnglish() {
        return titleEnglish;
    }

    public String getTitleJapanese() {
        return titleJapanese;
    }

    public List<Object> getTitleSynonyms() {
        return titleSynonyms;
    }

    public String getType() {
        return type;
    }

    public Integer getChapters() {
        return chapters;
    }

    public Integer getVolumes() {
        return volumes;
    }

    public String getStatus() {
        return status;
    }

    public Boolean getPublishing() {
        return publishing;
    }

    public Published getPublished() {
        return published;
    }

    public Double getScore() {
        return score;
    }

    public Double getScored() {
        return scored;
    }

    public Integer getScoredBy() {
        return scoredBy;
    }

    public Integer getRank() {
        return rank;
    }

    public Integer getPopularity() {
        return popularity;
    }

    public Integer getMembers() {
        return members;
    }

    public Integer getFavorites() {
        return favorites;
    }

    public String getSynopsis() {
        return synopsis;
    }

    public String getBackground() {
        return background;
    }

    public List<Author> getAuthors() {
        return authors;
    }

    public List<Genre> getGenres() {
        return genres;
    }

    public static class Published {
        @SerializedName("string")
        @Expose
        private String string;

        public String getString() {
            return string;
        }
    }

    public static class Author {
        @SerializedName("mal_id")
        @Expose
        private Integer malId;
        @SerializedName("type")
        @Expose
        private String type;
        @SerializedName("name")
        @Expose
        private String name;
        @SerializedName("url")
        @Expose
        private String url;

        public Integer getMalId() {
            return malId;
        }

        public String getType() {
            return type;
        }

        public String getName() {
            return name;
        }

        public String getUrl() {
            return url;
        }
    }

    public static class Genre {

        @SerializedName("mal_id")
        @Expose
        private Integer malId;
        @SerializedName("type")
        @Expose
        private String type;
        @SerializedName("name")
        @Expose
        private String name;
        @SerializedName("url")
        @Expose
        private String url;

        public Integer getMalId() {
            return malId;
        }

        public String getType() {
            return type;
        }

        public String getName() {
            return name;
        }

        public String getUrl() {
            return url;
        }
    }

    public static class Images {
        @SerializedName("jpg")
        @Expose
        private Jpg jpg;

        public Jpg getJpg() {
            return jpg;
        }
    }

    public static class Jpg {
        @SerializedName("image_url")
        @Expose
        private String imageUrl;
        @SerializedName("small_image_url")
        @Expose
        private String smallImageUrl;
        @SerializedName("large_image_url")
        @Expose
        private String largeImageUrl;

        public String getImageUrl() {
            return imageUrl;
        }

        public String getSmallImageUrl() {
            return smallImageUrl;
        }

        public String getLargeImageUrl() {
            return largeImageUrl;
        }
    }
}
