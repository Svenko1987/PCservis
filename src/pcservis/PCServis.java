/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pcservis;

import java.io.Serializable;


public class PCServis implements Serializable{
       static Login Log=new Login();
       static Entry Ent=new Entry();
       static Base Bas=new Base();

    public static void main(String[] args) {
        
        PCServis pc=new PCServis();
        pc.goToLogin();
        
        

    }
    public void goToEntry(){
        this.Log.setVisible(false);
        this.Bas.setVisible(false);
        this.Ent.setVisible(true);
    }
    public void goToBase(){
        this.Log.setVisible(false);
        this.Bas.setVisible(true);
        this.Ent.setVisible(false);
    }
    public void goToLogin(){
        this.Log.setVisible(true);
        this.Bas.setVisible(false);
        this.Ent.setVisible(false);
    }
}
