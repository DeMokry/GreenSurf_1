/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package warstwa_klienta_desktop;

import java.awt.Dimension;
import java.util.ArrayList;
import java.util.Iterator;
import javax.swing.BoxLayout;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author DeMokry
 */
public class Produkty_form extends JPanel{

    private JTable tabela_produktow;    //komponent typu tabela do wyświetlania danych produktów
    MyTableModel model;                 //model widoku
    JComboBox lista_produktow;          //lista wyswietlajacadane produktów

    public void init() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        model = new MyTableModel();                     //tworzenie modelu danych tabeli
        tabela_produktow = new JTable(model);           // utworzenie tabeli i przekazanie jej modelu z danymi produktow
        table_content();                                //wypelnienie danymi produktow tabeli
        tabela_produktow.setPreferredScrollableViewportSize(new Dimension(800, 100));
        tabela_produktow.setFillsViewportHeight(true);
        tabela_produktow.getSelectionModel().addListSelectionListener(new RowListener()); //dodanie słuchacza zdarzendo obslugi//zmiany wyboru wiersza

        add(new JScrollPane(tabela_produktow));         //dodanie panelu przewijania tabdli
        JLabel lprodukty = new JLabel("Produkty");
        add(lprodukty);
        lista_produktow = new JComboBox();
        add(lista_produktow);

    }

    void table_content() {//wypelnienie tablicy typu JTable danymi produktow
        ArrayList<ArrayList<String>> produkty = GUI_main.getFacade().items();
        model.setData(produkty);
        tabela_produktow.repaint();
    }

    private void list_content(ArrayList<ArrayList<String>> col, JComboBox list) {
        ArrayList<String> s;//wypelnienielisty typu JComboBoxdanymi produktow
        list.removeAllItems();
        Iterator<ArrayList<String>> iterator = col.iterator();
        while (iterator.hasNext()) {
            s = iterator.next();
            list.addItem(s);
        }
    }

    void print_produkty() {//metoda wypelniajacalistę typu JComboBoxdanymi produktowpobranymi metodą items.
        ArrayList<ArrayList<String>> help3 = GUI_main.getFacade().items();// pobranie danych produktow metoda items
        if (help3 != null) {
            list_content(help3, lista_produktow);//wypelnianielisty typu JComboBoxdanymi produktow
        }
    }

    private class RowListener implements ListSelectionListener {      //klasa wewnetrznado obslugizdarzenzmiany wyboru wiersza tabeli

        @Override
        public void valueChanged(ListSelectionEvent event) {   //metoda do obsługi zdarzenia zmiany wybranego wiersza tabeli
            if (event.getValueIsAdjusting()) {                  //za pomocą kliknieciamyszy na wybrany rowek
                return;
            }
            print_produkty();                               // po zmianie wiersza wykonanie metody wypelniajacejlistę typu JComboBoxdanymi produktow
        }
    }

    class MyTableModel extends AbstractTableModel {//klasa wewnetrznareprezentujacamodel danych obiektu typu JTable

        private final String[] columnNames = {"Id produktu", "Kategoria", "Nazwa", "Cena", "Promocja", "Data", "Cena brutto"};
        private ArrayList<ArrayList<String>> data;//dane tabeli-kazdy element zawiera elementy wiersza, jako kolekcja lancuchow

        public void setData(ArrayList<ArrayList<String>> val) { //wstawienie danych modelu
            data = val;
        }

        @Override
        public int getColumnCount() {
            return columnNames.length; //liczba kolumn
        }

        @Override
        public int getRowCount() {
            return data.size();//liczba rowkow
        }

        @Override
        public Object getValueAt(int row, int col) {
            return data.get(row).get(col);//pobrane elementu z podanej komorkitabeli w wieszurowi kolumnie col
        }

        @Override//metodaumozliwiaprzypisanienazwz tablicycolumnNames
        public String getColumnName(int col) {//do nazwkolumnwyświetlanejtabeli
            return columnNames[col];
        }
    }

}
