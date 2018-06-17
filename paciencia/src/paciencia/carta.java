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
public class carta {
    private naipe naipe;
    private valor valor;
    private boolean escondida;
    
    public carta (int a, int b) {
        naipe = new naipe(a);
        valor = new valor(b);
        escondida = true;
    }
    
    public String validacao(String a) {
        String retorno;
        
        if (escondida) {
            retorno = "  ▩  ";
        } else {
            switch (a) {
                case "get_naipe":
                    retorno = this.get_naipeStr();
                    break;
                case "get_valor":
                    retorno = this.get_valorStr();
                    break;
                case "get_carta":
                    retorno = this.get_cartaStr();
                    break;
                default:
                    retorno = this.get_cartaStr();
                    break;
                    
            }
            
        }
        
        return retorno;
    }
    
    public void set_escondida(boolean a) {
        escondida = a;
    }
    
    public boolean get_escondida() {
        return escondida;
    }

    
    public int get_naipe() {
        int retorno  = this.naipe.get_id();         
        
        return retorno;
    }
    
    private String get_naipeStr() {
        
        String retorno  = this.naipe.get_naipe();
                       
        return retorno;
    }   
    
    public int get_valor() {
        int retorno  = this.valor.get_id();
                       
        return retorno;
    }
    
    private String get_valorStr() {
        String retorno  = this.valor.get_valor();
        
        return retorno;
    }
    
    private String get_cartaStr() {
        String retorno;
        if (get_naipe() % 2 == 1) {       
            retorno = get_valorStr() + "-" + get_naipeStr();
        } else {
            retorno = "\u001B[31m" + get_valorStr() + "-" + get_naipeStr() + "\u001B[30m";
        }
        return retorno;
    }

}
