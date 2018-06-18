/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paciencia;

import java.util.Collections;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author F7023259
 */
public class baralho {

    private carta[] cartas = new carta[52];
    
    public baralho() {
        int contador = 0;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 13; j++) {
                cartas[contador] = new carta(i,j);
                contador++;
            }
        }
    }
    
    public void embaralhar(int a) {
        List<carta> cartas_antigas = new ArrayList();
                       
        for (int i = 0; i < a; i++) {
            cartas_antigas.add(cartas[i]);
        }
        
        Collections.shuffle(cartas_antigas);
        
        for (int i = 0; i < a; i++) {
            cartas[i] = cartas_antigas.get(i);
        }
    }
    
    public carta get_carta(int a) {
        return cartas[a];
    }
    
    public void get_cartasStr() {
        for(int i = 0; i < this.cartas.length; i++) {
            System.out.println("ver " + cartas[i].validacao("get_carta"));
        }
    }
}
