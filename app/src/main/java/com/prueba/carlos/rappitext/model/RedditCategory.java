package com.prueba.carlos.rappitext.model;

import com.google.gson.annotations.SerializedName;
import com.j256.ormlite.field.DatabaseField;

/**
 * Class that describes the category
 *
 * @author <a href="mailto:carlosfelipetorres75@gmail.com">Carlos Torres</a>
 */
public class RedditCategory {

    /** Local Category ID **/
    @DatabaseField(generatedId = true)
    private Integer idLocal;

    @SerializedName("id")
    @DatabaseField(canBeNull = false)
    private String id;

    @SerializedName("banner_img")
    @DatabaseField(canBeNull = false)
    private String bannerImg;

    @SerializedName("submit_text")
    @DatabaseField(canBeNull = false)
    private String submitText;

    @SerializedName("display_name")
    @DatabaseField(canBeNull = false)
    private String displayName;

    @SerializedName("header_img")
    @DatabaseField(canBeNull = false)
    private String headerImg;

    @SerializedName("title")
    @DatabaseField(canBeNull = false)
    private String title;

    @SerializedName("icon_img")
    @DatabaseField(canBeNull = false)
    private String iconImg;

    @SerializedName("header_title")
    @DatabaseField(canBeNull = false)
    private String headerTitle;

    @SerializedName("description")
    @DatabaseField(canBeNull = false)
    private String description;

    @SerializedName("subscribers")
    @DatabaseField(canBeNull = false)
    private String subscribers;

    @SerializedName("key_color")
    @DatabaseField(canBeNull = false)
    private String keyColor;

    @SerializedName("url")
    @DatabaseField(canBeNull = false)
    private String url;

    @SerializedName("public_description")
    @DatabaseField(canBeNull = false)
    private String publicDescription;

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

    public String getBannerImg() {
        return bannerImg;
    }

    public void setBannerImg(String bannerImg) {
        this.bannerImg = bannerImg;
    }

    public String getSubmitText() {
        return submitText;
    }

    public void setSubmitText(String submitText) {
        this.submitText = submitText;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getHeaderImg() {
        return headerImg;
    }

    public void setHeaderImg(String headerImg) {
        this.headerImg = headerImg;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getIconImg() {
        return iconImg;
    }

    public void setIconImg(String iconImg) {
        this.iconImg = iconImg;
    }

    public String getHeaderTitle() {
        return headerTitle;
    }

    public void setHeaderTitle(String headerTitle) {
        this.headerTitle = headerTitle;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSubscribers() {
        return subscribers;
    }

    public void setSubscribers(String subscribers) {
        this.subscribers = subscribers;
    }

    public String getKeyColor() {
        return keyColor;
    }

    public void setKeyColor(String keyColor) {
        this.keyColor = keyColor;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getPublicDescription() {
        return publicDescription;
    }

    public void setPublicDescription(String publicDescription) {
        this.publicDescription = publicDescription;
    }
}
