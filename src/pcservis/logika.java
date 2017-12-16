/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pcservis;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.io.Serializable;
import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class logika implements Serializable{

    private List<accounts> acc = new ArrayList<accounts>();
    List<brokenPcs> BPC=new ArrayList<>();
    String User;

    public void setUser(JTextField jtf) {
        this.User = jtf.getText();
    }

    public String getUser() {
        return User;
    }
    

    public logika() {
    }

    public accounts addAcc(String ime, String pass) {
        accounts temp = new accounts(ime, pass);
        return temp;
    }
    public brokenPcs addBPC(JTextField nameCus, String typePc, JTextField date, JTextField time, JTextArea opis, JTextField user, String garancija, JTextField brtelefona, JTextField firma, JTextField mjesto, String oprema){
        brokenPcs temp= new brokenPcs(nameCus, typePc, date, time, opis, user, garancija, brtelefona,firma,mjesto,oprema);
        return temp;
        
    }
    public void addToBPCList(JTextField nameCus, String typePc, JTextField date, JTextField time, JTextArea opis, JTextField user, String garancija, JTextField brtelefona, JTextField firma, JTextField mjesto, String oprema){
        this.BPC.add(addBPC(nameCus, typePc, date, time, opis, user, garancija, brtelefona,firma,mjesto,oprema));
    }
    public void showBPC(JTextArea jta){
        Iterator <brokenPcs> it =this.BPC.iterator();
        while (it.hasNext()) {
            brokenPcs pcs = it.next();
            jta.append("Musterija : "+pcs.nameCus+"\nBr telefona : "+pcs.brtelefona+"\nTip racunara : "+pcs.typePc+"\nGarancija : "+pcs.garancija+"\n Opis Kvara : \n"
                    + "" +pcs.opis+"\nKorisnik : "+pcs.user+"\nDatum : "+pcs.date+"  Vrijeme : "+pcs.time);
            
            
        }
    }
        public void ShowInTable(JTable jt,List<brokenPcs> Bl){
            
            String Kolone[]={"Musterija","Br telefona","Tip racunara","Garancija","Opis Kvara","Korisnik","Datum","Vrijeme"};
            Object podaci[][]=new Object[20][8];
            Iterator<brokenPcs>it=Bl.iterator();
            int i=0;
            while (it.hasNext()) {
                brokenPcs pcs = it.next();
                podaci[i][0]=pcs.nameCus;
                podaci[i][1]=pcs.brtelefona;
                podaci[i][2]=pcs.typePc;
                podaci[i][3]=pcs.garancija;
                podaci[i][4]=pcs.opis;
                podaci[i][5]=pcs.user;
                podaci[i][6]=pcs.date;
                podaci[i][7]=pcs.time;
                i++;
                
            }
            jt.setModel(new javax.swing.table.DefaultTableModel(podaci, Kolone));
        }        public void ShowInTableVector(JTable jt,List<brokenPcs> Bl){
            
            String Kolone[]={"Musterija","Br telefona","Tip racunara","Garancija","Opis Kvara","Korisnik","Datum","Vrijeme"};
            //Object podaci[][]=new Object[20][8];
            Vector<String> Cnames=new Vector<String>();
            Cnames.add("Ime Prezime");Cnames.add("Br telefona");Cnames.add("Firma");Cnames.add("Mjesto");Cnames.add("Tip");
            Cnames.add("Garancija");Cnames.add("Opema");Cnames.add("Opis");
            Cnames.add("korisnik");Cnames.add("Datum");Cnames.add("Vrijeme");
            Iterator<brokenPcs>it=Bl.iterator();
            Vector <Vector> VofV=new Vector<Vector>();
            while (it.hasNext()) {
                Vector vec=new Vector();
                brokenPcs pcs= it.next();
                vec.add(pcs.nameCus);
                vec.add(pcs.brtelefona);
                vec.add(pcs.Firma);
                vec.add(pcs.Mjesto);
                vec.add(pcs.typePc);
                vec.add(pcs.garancija);
                vec.add(pcs.Oprema);
                vec.add(pcs.opis);
                vec.add(pcs.user);
                vec.add(pcs.date);
                vec.add(pcs.time);
                VofV.add(vec);
                
                
                
            }
            jt.setModel(new javax.swing.table.DefaultTableModel(VofV, Cnames));
        }
        public void saveToFile(List<brokenPcs> memoriaj){
        try {
            FileOutputStream fos = new FileOutputStream("test.txt");
            try {
                ObjectOutputStream oos = new ObjectOutputStream(fos);
                oos.writeObject(memoriaj);
                oos.close();
                fos.close();
            } catch (IOException ex) {
                Logger.getLogger(logika.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(logika.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
            public void upisStampa(String nazivFajla, String podaci){
        try {
            FileWriter fw = new FileWriter(nazivFajla);
            PrintWriter pw = new PrintWriter(fw);
            pw.write(podaci);
            pw.close();fw.close();
        } catch (IOException ex) {
            Logger.getLogger(logika.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

            
        public void laodFromFile(List <brokenPcs>Bl){
        try {
            FileInputStream fis= new FileInputStream("test.txt");
            try {
                ObjectInputStream ois=new ObjectInputStream(fis);
                try {
                    Bl=(List<brokenPcs>) ois.readObject();
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(logika.class.getName()).log(Level.SEVERE, null, ex);
                }
            } catch (IOException ex) {
                Logger.getLogger(logika.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        } catch (FileNotFoundException ex) {
            Logger.getLogger(logika.class.getName()).log(Level.SEVERE, null, ex);
        }
        }    public void ucitajKontakte(){
        try {
            FileInputStream fis = new FileInputStream("test.txt");
            try {
                ObjectInputStream ois = new ObjectInputStream(fis);
                try {
                    this.BPC = (List<brokenPcs>) ois.readObject();
                        ois.close();fis.close();
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(logika.class.getName()).log(Level.SEVERE, null, ex);
                }
                
            } catch (IOException ex) {
                Logger.getLogger(logika.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(logika.class.getName()).log(Level.SEVERE, null, ex);
        }
    }



    public void addToAccList() {
        this.acc.add(addAcc("admin", "admin"));
        this.acc.add(addAcc("test", "test"));
        this.acc.add(addAcc("bojan", "bojan"));
    }

    public boolean provjeraAcc(String name, String pass) {
        boolean pro = false;
        Iterator<accounts> it = this.acc.iterator();
        while (it.hasNext()) {
            accounts elem = it.next();
            if (elem.getName().equals(name)) {
                if (elem.getPassword().equals(pass)) {
                    pro = true;
                    break;

                }
            }
        }
        return pro;
    }

    public class accounts implements Serializable{

        private String name;
        private String password;

        public accounts(String name, String password) {
            this.name = name;
            this.password = password;
        }

        public String getName() {
            return name;
        }

        public String getPassword() {
            return password;
        }

    }
    public class brokenPcs implements Serializable{
        
        String nameCus;
        String typePc;
        String date;
        String time;
        String opis;
        String user;
        String garancija;
        String brtelefona;
        String Firma;
        String Mjesto;
        String Oprema;
        

        public brokenPcs(JTextField nameCus, String typePc, JTextField date, JTextField time, JTextArea opis, JTextField user, String garancija, JTextField brtelefona, JTextField
                firma, JTextField mjesto, String oprema) {
            this.nameCus = nameCus.getText();
            this.typePc = typePc;
            this.date = date.getText();
            this.time = time.getText();
            this.opis = opis.getText();
            this.user = user.getText();
            this.garancija = garancija;
            this.brtelefona = brtelefona.getText();
            this.Firma=firma.getText();
            this.Mjesto=mjesto.getText();
            this.Oprema=oprema;
        }


        
        
    }
}
