package com.prueba.carlos.rappitext.model;

import com.google.gson.annotations.SerializedName;
import com.j256.ormlite.field.DataType;
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

    @DatabaseField(canBeNull = false)
    private String idCategory;

    @SerializedName("id")
    @DatabaseField(canBeNull = false)
    private String id;

    @SerializedName("subreddit")
    @DatabaseField(canBeNull = true)
    private String subreddit;

    @SerializedName("selftext")
    @DatabaseField(canBeNull = true)
    private String selftext;

    @SerializedName("likes")
    @DatabaseField(columnName = "likes")
    private String likes;

    @SerializedName("author")
    @DatabaseField(canBeNull = true)
    private String author;

    @SerializedName("score")
    @DatabaseField(canBeNull = true)
    private String score;

    @SerializedName("url")
    @DatabaseField(canBeNull = true)
    private String url;

    @SerializedName("title")
    @DatabaseField(canBeNull = true)
    private String title;

    @SerializedName("thumbnail")
    @DatabaseField(canBeNull = true)
    private String thumbnail;

    @SerializedName("num_comments")
    @DatabaseField(canBeNull = true)
    private String numComments;

    @SerializedName("ups")
    @DatabaseField(canBeNull = true)
    private String ups;

    @SerializedName("preview")
    @DatabaseField(dataType= DataType.SERIALIZABLE, canBeNull = true)
    private Imagenes imagenes;

    public String getIdCategory() {
        return idCategory;
    }

    public void setIdCategory(String idCategory) {
        this.idCategory = idCategory;
    }

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

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getNumComments() {
        return numComments;
    }

    public void setNumComments(String numComments) {
        this.numComments = numComments;
    }

    public String getUps() {
        return ups;
    }

    public void setUps(String ups) {
        this.ups = ups;
    }

    public Imagenes getImagenes() {
        return imagenes;
    }

    public void setImagenes(Imagenes imagenes) {
        this.imagenes = imagenes;
    }
}
