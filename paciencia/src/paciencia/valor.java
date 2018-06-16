/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paciencia;

/**
 *
 * @author mprud
 */
public class valor {
    private int id;
    private String valorStr;
    private final String valores[] = {" A", " 2", " 3", " 4", " 5", " 6", " 7", " 8", " 9", "10", " J", " Q"," K"};
    
    public valor(int a) {
        id = a;
        valorStr = valores[a];
    }
    
    public int get_id() {
        int retorno  = this.id;
                       
        return retorno;
    }
    
    public String get_valor() {
        String retorno  = this.valorStr;
                       
        return retorno;
    }
}
