package com.prueba.carlos.rappitext.model;

import com.google.gson.annotations.SerializedName;
import com.j256.ormlite.field.DatabaseField;

/**
 * Class that describes the reddit app
 *
 * @author <a href="mailto:carlosfelipetorres75@gmail.com">Carlos Torres</a>
 */
public class RedditApp {

    /** Local app ID **/
    @DatabaseField(generatedId = true)
    private Integer idLocal;

    @SerializedName("id")
    @DatabaseField(canBeNull = false)
    private String id;

    @SerializedName("subreddit")
    @DatabaseField(canBeNull = false)
    private String subreddit;

    @SerializedName("selftext")
    @DatabaseField(canBeNull = false)
    private String selftext;

    @SerializedName("likes")
    @DatabaseField(canBeNull = false)
    private String likes;

    @SerializedName("author")
    @DatabaseField(canBeNull = false)
    private String author;

    @SerializedName("score")
    @DatabaseField(canBeNull = false)
    private String score;

    @SerializedName("url")
    @DatabaseField(canBeNull = false)
    private String url;

    @SerializedName("title")
    @DatabaseField(canBeNull = false)
    private String title;

    @SerializedName("preview")
    private Imagenes preview;

    @SerializedName("thumbnail")
    @DatabaseField(canBeNull = false)
    private String thumbnail;

    public Integer getIdLocal() {
        return idLocal;
    }

    public void setIdLocal(Integer idLocal) {
        this.idLocal = idLocal;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSubreddit() {
        return subreddit;
    }

    public void setSubreddit(String subreddit) {
        this.subreddit = subreddit;
    }

    public String getSelftext() {
        return selftext;
    }

    public void setSelftext(String selftext) {
        this.selftext = selftext;
    }

    public String getLikes() {
        return likes;
    }

    public void setLikes(String likes) {
        this.likes = likes;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Imagenes getPreview() {
        return preview;
    }

    public void setPreview(Imagenes preview) {
        this.preview = preview;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }
}
