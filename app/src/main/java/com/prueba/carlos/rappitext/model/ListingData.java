package com.prueba.carlos.rappitext.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Esta es la clase modela los datos recibidos
 *
 * @author <a href="mailto:carlosfelipetorres75@gmail.com">Carlos Torres</a>
 */
public class ListingData {
    /** Lista de elementos **/
    @SerializedName("children")
    private List<RespuestaBasica<RedditCategory>> elementos;

    /** Modhash **/
    @SerializedName("modhash")
    private String modhash;

    public List<RespuestaBasica<RedditCategory>> getElementos() {
        return (List<RespuestaBasica<RedditCategory>>) elementos;
    }

    public void setElementos(List<RespuestaBasica<RedditCategory>> elementos) {
        this.elementos = elementos;
    }

    public String getModhash() {
        return modhash;
    }

    public void setModhash(String modhash) {
        this.modhash = modhash;
    }
}
