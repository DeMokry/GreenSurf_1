/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package warstwa_biznesowa;

import java.util.ArrayList;
import java.util.List;
import warstwa_biznesowa.dto.Produkt_dto;
import warstwa_biznesowa.entity.Produkt1;

/**
 *
 * @author DeMokry
 */
public class Fasada_warstwy_biznesowej {

    private Produkt1 produkt;
    static long klucz = 0;
    private ArrayList<Produkt1> produkty = new ArrayList();
    boolean stan = false;

    int istnieje_produkt(Produkt_dto pdto) {
        Produkt1 pom1 = this.wykonaj_produkt(pdto);
        return getProdukty().indexOf(pom1);
    }

    public ArrayList<Produkt1> getProdukty() {
        return produkty;
    }

    public void setProdukty(ArrayList<Produkt1> produkty) {
        this.produkty = produkty;
    }

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    public Produkt1 getProdukt() {
        return produkt;
    }

    public void setProdukt(Produkt1 produkt) {
        this.produkt = produkt;
    }

    //wykonanie obiektu typu Produkt1
    public void utworz_produkt(Produkt_dto produkt_dto) {
        Produkt1 produkt = wykonaj_produkt(produkt_dto);
        dodaj_produkt(produkt);
    }

    Produkt1 wykonaj_produkt(Produkt_dto produkt_dto) {
        Produkt1 produkt = new Produkt1();
        //max_klucz();
        //produkt.setId(new Long(klucz));
        produkt.setKategoria(produkt_dto.getKategoria());
        produkt.setNazwa(produkt_dto.getNazwa());
        produkt.setCena(produkt_dto.getCena());
        produkt.setPromocja(produkt_dto.getPromocja());
        produkt.setData_produkcji(produkt_dto.getData_produkcji());
        return produkt;
    }

    /*  void max_klucz() {
        long max = 0;
        for (Produkt1 p : produkty) {
            if (p.getId() > max) {
                max = p.getId();
            }
        }
        klucz = max + 1;
    }
     */
//wykonanie modelu obiektu typu Produkt1
    protected void dodaj_produkt(Produkt1 produkt) {
        if (!produkty.contains(produkt)) {
            produkty.add(produkt);
            stan = true;
        } else {
            stan = false;
        }
    }

    public ArrayList<Produkt_dto> items_() {
        ArrayList<Produkt_dto> dane = new ArrayList();
        for (Produkt1 produkt : produkty) {
            dane.add(produkt_transfer(produkt));
        }
        return dane;
    }

    public ArrayList<ArrayList<String>> items() {
        ArrayList<ArrayList<String>> dane = new ArrayList();
        for (Produkt1 p : produkty) {
            ArrayList<String> wiersz = new ArrayList();
            wiersz.add(p.getId_().toString());
            wiersz.add(p.getKategoria());
            wiersz.add("" + p.getNazwa());
            wiersz.add("" + p.getCena());
            wiersz.add("" + p.getPromocja());
            wiersz.add("" + p.cena_brutto());
            wiersz.add("" + p.getData_produkcji().toString());
            dane.add(wiersz);
        }
        return dane;
    }

    public Produkt_dto dane_produktu() {
        if (stan) {
            Produkt1 produkt = produkty.get(produkty.size() - 1);
            return produkt_transfer(produkt);
        }
        return null;
    }

    public Produkt_dto produkt_transfer(Produkt1 produkt) {
        Produkt_dto pom = new Produkt_dto();
        pom.setId(produkt.getId_());
        pom.setKategoria(produkt.getKategoria());
        pom.setNazwa(produkt.getNazwa());
        pom.setCena(produkt.getCena());
        pom.setPromocja(produkt.getPromocja());
        pom.setData_produkcji(produkt.getData_produkcji());
        pom.setCena_brutto(produkt.cena_brutto());
        return pom;
    }

    public boolean isStan() {
        return stan;
    }

    public void setStan(boolean stan) {
        this.stan = stan;
    }

    public int count() {
        return produkty.size();
    }

    public ArrayList<Produkt_dto> findRange(int[] range) {
        ArrayList<Produkt_dto> pom = new ArrayList();
        if (getProdukty().isEmpty()) {
            stan = false;
            return pom;
        }
        for (int i = range[0]; i < range[1]; i++) {
            pom.add(produkt_transfer(getProdukty().get(i)));
        }
        return pom;
    }

    public boolean edit(Produkt_dto o_przed, Produkt_dto o_update) {
        int idx1, idx2;
        stan = true;
        idx1 = this.istnieje_produkt(o_przed);
        if (idx1 == -1) //taki produkt do edycji nie istnieje
        {
            return false;
        }

        idx2 = this.istnieje_produkt(o_update);
        if (idx2 != -1) //nie mozna modyfikowac, bo już taki produkt istnieje
        {
            return false;
        }

        Produkt1 p = getProdukty().get(idx1);
        p.setKategoria(o_update.getKategoria());
        p.setCena(o_update.getCena());
        p.setData_produkcji(o_update.getData_produkcji());
        p.setPromocja(o_update.getPromocja());

        return true;
    }

    public void remove(Produkt_dto p) {
        Produkt1 produkt1 = wykonaj_produkt(p);
        getProdukty().remove(produkt1);
    }

    public void produkty_z_bazy_danych(List<Produkt1> produkty_) {
        produkty.clear();
        produkty.addAll(produkty_);
    }

}
