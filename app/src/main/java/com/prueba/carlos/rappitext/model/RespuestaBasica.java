package com.prueba.carlos.rappitext.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * Esta es la clase utilizada para recibir las respuestas de ciudades
 *
 * @author <a href="mailto:carlosfelipetorres75@gmail.com">Carlos Torres</a>
 */
public class
RespuestaBasica<T> implements Serializable {

    /** Kind of element **/
    @SerializedName("kind")
    private String kind;

    /** Data **/
    @SerializedName("data")
    private T data;

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public T getData() {
        return (T) data;
    }

    public void setData(ListingData data) {
        this.data = (T) data;
    }
}
