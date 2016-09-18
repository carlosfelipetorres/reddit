package com.prueba.carlos.rappitext.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Class that describes the images
 *
 * @author <a href="mailto:carlosfelipetorres75@gmail.com">Carlos Torres</a>
 */
public class Imagenes {

    @SerializedName("images")
    private List<ContenidoImagenes> images;

    public List<ContenidoImagenes> getImages() {
        return images;
    }

    public void setImages(List<ContenidoImagenes> images) {
        this.images = images;
    }
}

class ContenidoImagenes {
    @SerializedName("source")
    private Content source;

    @SerializedName("resolutions")
    private Content resolutions;

    public Content getSource() {
        return source;
    }

    public void setSource(Content source) {
        this.source = source;
    }

    public Content getResolutions() {
        return resolutions;
    }

    public void setResolutions(Content resolutions) {
        this.resolutions = resolutions;
    }
}

class Content {

    @SerializedName("url")
    private String url;

    @SerializedName("width")
    private String width;

    @SerializedName("height")
    private String height;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getWidth() {
        return width;
    }

    public void setWidth(String width) {
        this.width = width;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }
}
