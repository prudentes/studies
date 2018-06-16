/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paciencia;

/**
 *
 * @author F7023259
 */
public class naipe {
    private int id;
    private String naipeStr;
    private final String naipes[] = {"♦ ", "♠ ", "♥ ","♣ "};
    
    public naipe(int a) {
        id = a;
        naipeStr = naipes[a];
    }
    
    public int get_id() {
        int retorno  = this.id;
                       
        return retorno;
    }
    
    public String get_naipe() {
        String retorno  = this.naipeStr;
                       
        return retorno;
    }
    
}
