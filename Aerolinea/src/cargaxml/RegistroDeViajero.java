package cargaxml; 

import java.io.Serializable;

public class RegistroDeViajero implements Serializable {

    private long dni;
    private String id;
    private float monto, litro;

    public RegistroDeViajero() {
    }

    public long getDNI() {
        return dni;
    }

    public void setDNI(long dni) {
        this.dni = dni;
    }

    public String getID() {
        return id;
    }

    public void setID(String id) {
        this.id = id;
    }

    public float getValorNumerico() {
        if (monto != 0 && litro != 0) {
            return -1;
        } else if (monto == 0 && litro == 0) {
            return 0;
        } else if (monto != 0) {
            return monto;
        } else {
            return litro;
        }
    }

    public void setMonto(float monto) {
        this.monto = monto;
    }

    public float getLitro() {
        return litro;
    }

    public void setLitro(float litro) {
        this.litro = litro;
    }

    @Override
    public String toString() {
        return "DNI{" + "numero= " + dni + ", ID= " + id + ", monto= " + monto + ", litro=" + litro + '}';
    }

}
