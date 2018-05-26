package warstwa_biznesowa_ejb;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import warstwa_biznesowa.Fasada_warstwy_biznesowej;
import warstwa_biznesowa.dto.Produkt_dto;
import warstwa_biznesowa.entity.Produkt1;
import warstwa_integracji_ejb.Produkt1FacadeLocal;

@Stateless
public class Fasada_warstwy_biznesowej_ejb implements Fasada_warstwy_biznesowej_ejbRemote {

    @PostConstruct
    public void init() {
        try {
            pobierz();
        } catch (Exception e) {
        }

    }

    @EJB
    private Produkt1FacadeLocal produkt1Facade;

    Fasada_warstwy_biznesowej fasada = new Fasada_warstwy_biznesowej();

    @Override
    public void utworz_produkt(Produkt_dto produkt_dto) {
        fasada.utworz_produkt(produkt_dto);
    }

    @Override
    public Produkt_dto dane_produktu() {
        return fasada.dane_produktu();
    }

    @Override
    public ArrayList<ArrayList<String>> items() {
        return fasada.items();
    }

    @Override
    public ArrayList<Produkt_dto> items_() {
        return fasada.items_();
    }

    public ArrayList<Produkt_dto> findRange(int[] range) {
        return fasada.findRange(range);
    }

    public int count() {
        return fasada.count();
    }

    public void setStan(boolean stan) {
        fasada.setStan(stan);
    }

    public boolean isStan() {
        return fasada.isStan();
    }

    public boolean edit(Produkt_dto o_przed, Produkt_dto o_update) {
        return fasada.edit(o_przed, o_update);
    }

    @Override
    public void remove(Produkt_dto p) {
        fasada.remove(p);
    }

    public void pobierz() {
        List<Produkt1> pom = produkt1Facade.findAll();
        fasada.produkty_z_bazy_danych(pom);
    }

    public void zapisz() {
        for (Produkt1 p : fasada.getProdukty()) {
            Long id = p.getId_();
            if (id == null || produkt1Facade.find(p.getId_()) == null) {
                produkt1Facade.create(p);
            }
        }
    }

}
