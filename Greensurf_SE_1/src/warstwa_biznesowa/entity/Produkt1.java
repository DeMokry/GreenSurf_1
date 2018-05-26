/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package warstwa_biznesowa.entity;

import java.io.Serializable;
import java.util.Objects;
import java.util.Date;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;

/**
 *
 * @author DeMokry
 */
@Entity
public class Produkt1 implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String kategoria;

    private String nazwa;

    private float cena;

    private float promocja;
    
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date data_produkcji;

    public Date getData_produkcji() {
        return data_produkcji;
    }

    public void setData_produkcji(Date data_produkcji) {
        this.data_produkcji = data_produkcji;
    }
    
        

    public String getKategoria() {
        return kategoria;
    }

    public void setKategoria(String kategoria) {
        this.kategoria = kategoria;
    }

    public String getNazwa() {
        return nazwa;
    }

    public void setNazwa(String nazwa) {
        this.nazwa = nazwa;
    }

    public float getCena() {
        return cena;
    }

    public void setCena(float cena) {
        this.cena = cena;
    }

    public float getPromocja() {
        return promocja;
    }

    public void setPromocja(float promocja) {
        this.promocja = promocja;
    }

    public float cena_brutto() {
        float cena_brutto = cena * (1 - promocja / 100);
        return cena_brutto;
    }

    public Long getId_() {
        if(id==null)
            return new Long(0);
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Produkt1 other = (Produkt1) obj;
        if (Float.floatToIntBits(this.cena) != Float.floatToIntBits(other.cena)) {
            return false;
        }
        if (Float.floatToIntBits(this.promocja) != Float.floatToIntBits(other.promocja)) {
            return false;
        }
        if (!Objects.equals(this.kategoria, other.kategoria)) {
            return false;
        }
        if (!Objects.equals(this.nazwa, other.nazwa)) {
            return false;
        }
      

        return true;
    }

    public static boolean equals(Object a,
            Object b) {
        return (a == b)
                || (a != null && a.equals(b));
    }

    @Override
    public String toString() {
        return "warstwa.biznesowa.entity.Produkt1[ id=" + id + " ]";
    }
    
   
}
