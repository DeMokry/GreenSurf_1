/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package warstwa_klienta_desktop;

import java.awt.Color;
import java.awt.Font;
import java.util.Date;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import warstwa_biznesowa.dto.Produkt_dto;


/**
 *
 * @author DeMokry
 */
public final class Rezultat2_form extends JPanel {

    private final JLabel dodano_produkt = new JLabel("Dodano Produkt");
    private final JTextArea rezultat2 = new JTextArea();
    private String dane_p;

    public String getDane_p() {
        return dane_p;
    }

    public void setDane_p(String dane_p) {
        this.dane_p = dane_p;
    }

    public void init() {
        dane_do_wyswietlenia();
        setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        add(dodano_produkt);
        add(rezultat2);
        String text = getDane_p();
        rezultat2.setLineWrap(true);
        rezultat2.setBackground(Color.BLACK);
        rezultat2.setFont(new Font("Lucida Console", Font.PLAIN, 13));
        rezultat2.setForeground(Color.GREEN);
        rezultat2.setText(text);

    }

    public void dane_do_wyswietlenia() {
        Produkt_dto dp = GUI_main.getFacade().dane_produktu();
        if(dp==null){
            setDane_p("");
        }else{
        String kat = "Kategoria produktu: " + dp.getKategoria() + "\n\n";
        String nazwa = "Nazwa produktu: " + dp.getNazwa() + "\n\n";
        String cena = "Cena produktu: " + dp.getCena() + "\n\n";
        String promocja = "Promocja: " + dp.getPromocja() + "\n\n";
        String cena_brutto = "Cena brutto: " + dp.getCena_brutto() + "\n\n";
        //String data = "Data: " + (new Date(Long.parseLong(dp.getData_produkcji()))).toString() + "\n\n"  ;
        String data = "Data: " + dp.getData_produkcji()+ "\n\n"  ;
        setDane_p(kat + nazwa + cena + promocja + cena_brutto + data);
    }
    }

}
