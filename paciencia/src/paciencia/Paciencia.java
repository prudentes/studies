/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paciencia;

import java.util.Scanner;
import java.util.Arrays;
import java.util.Stack;
import java.util.LinkedList;
import java.util.List;
import java.io.UnsupportedEncodingException;
import java.io.PrintStream;
import java.io.IOException;
import java.io.InterruptedIOException;
import java.util.InputMismatchException;

/**
 *
 * @author F7023259
 */


public class Paciencia {
    
    private int movimentos = 0;
    private int num_cartas;
    private baralho baralho = new baralho();
    
    private Stack<carta> estoque;
    private Stack<carta> descarte;

    private Stack<carta> fundacao1;
    private Stack<carta> fundacao2;
    private Stack<carta> fundacao3;
    private Stack<carta> fundacao4;
    
    private LinkedList<carta> pilha1;
    private LinkedList<carta> pilha2;
    private LinkedList<carta> pilha3;
    private LinkedList<carta> pilha4;
    private LinkedList<carta> pilha5;
    private LinkedList<carta> pilha6;
    private LinkedList<carta> pilha7;
    
    public static void imprimir() {
        try {
            PrintStream saida = new PrintStream(System.out, true, "UTF-8");
            saida.print("");
        } catch (UnsupportedEncodingException e) {
            System.out.print("error: " + e);
        }
        
    }
    
    public static void imprimir(String a) {
        try {
            PrintStream saida = new PrintStream(System.out, true, "UTF-8");
            saida.print(a);
        } catch (UnsupportedEncodingException e) {
            System.out.print("error: " + e);
        }
        
    }
    
    public static void imprimirln() {
        try {
            PrintStream saida = new PrintStream(System.out, true, "UTF-8");
            saida.println();
        } catch (UnsupportedEncodingException e) {
            System.out.println("error: " + e);
        }
        
    }
    
    public static void imprimirln(String a) {
        try {
            PrintStream saida = new PrintStream(System.out, true, "UTF-8");
            saida.println(a);
        } catch (UnsupportedEncodingException e) {
            System.out.println("error: " + e);
        }
        
    }
   
    public Paciencia() {
        this.num_cartas = 1;
    }
    
    public Paciencia(int a) {
        this.num_cartas = a;
    }
    
    public void iniciaJogo() {
        this.zerajogo();
        
        baralho.embaralhar(52);
        
        this.distribuir();
    }
    
    public void distribuir() {
        LinkedList[] ordemDistribuir = {this.pilha1, this.pilha2, this.pilha3, this.pilha4, this.pilha5, this.pilha6, this.pilha7};
        int contador = 0;
        for (int i = 0; i < ordemDistribuir.length; i++) {
            for (int j = 0; j <= i; j++) { 
                if (j == i) {
                    this.baralho.get_carta(contador).set_escondida(false);
                }
                ordemDistribuir[i].add(this.baralho.get_carta(contador));
                contador++;
            }
            
        }
        for (int i = contador; i < 52; i++) {
            this.estoque.push(this.baralho.get_carta(i));
        }
    }
    
    public void zerajogo() {
        this.baralho = new baralho();
    
        this.estoque = new Stack();
        this.descarte = new Stack();

        this.fundacao1 = new Stack();
        this.fundacao2 = new Stack();
        this.fundacao3 = new Stack();
        this.fundacao4 = new Stack();
        
        this.pilha1 = new LinkedList();
        this.pilha2 = new LinkedList();
        this.pilha3 = new LinkedList();
        this.pilha4 = new LinkedList();
        this.pilha5 = new LinkedList();
        this.pilha6 = new LinkedList();
        this.pilha7 = new LinkedList();
        this.movimentos = 0;
    }
    
    public void imprimeGameTop() {
        imprimirln(" ╔═══════════════════════════════════════════╗ ");
        List<Stack<carta>> imprimirOrdem = Arrays.asList(this.estoque, this.descarte, null, this.fundacao1, this.fundacao2, this.fundacao3, this.fundacao4);
        int i = 0;
        while (i < this.num_cartas) {
            int j = 0;
            imprimir(" ║");
            while (j < imprimirOrdem.size()) {
                imprimir(" ");
                if (i == 0) {
                    if (imprimirOrdem.get(j) == (null)) {
                        imprimir("     ");
                    } else {
                        if (!imprimirOrdem.get(j).isEmpty()) {
                            if (imprimirOrdem.get(j) == this.descarte) {
                                if (i < imprimirOrdem.get(j).size()) {
                                    imprimir(imprimirOrdem.get(j).get(imprimirOrdem.get(j).size() - this.num_cartas + i).validacao("get_carta"));
                                } else {
                                    imprimir("  ⌂  ");
                                }
                            } else {
                                imprimir(imprimirOrdem.get(j).peek().validacao("get_carta"));
                            }
                        } else {
                            imprimir("  ⌂  ");
                        }
                    }
                } else {
                    if (imprimirOrdem.get(j) == this.descarte) {
                        if (i < imprimirOrdem.get(j).size()) {
                            imprimir(imprimirOrdem.get(j).get(imprimirOrdem.get(j).size() - this.num_cartas + i).validacao("get_carta"));
                        } else {
                            imprimir("  ⌂  ");
                        }
                    } else {
                        imprimir("     ");
                    }
                }
                j++;    
            }
            imprimirln(" ║ ");
            i++;
        }
        imprimirln(" ╟───────────────────────────────────────────╢ ");
    }
    
    public void imprimeGameTop(Stack<carta> a) {
        imprimirln(" ╔═══════════════════════════════════════════╤═══════╗");
        List<Stack<carta>> imprimirOrdem = Arrays.asList(this.estoque, this.descarte, null, this.fundacao1, this.fundacao2, this.fundacao3, this.fundacao4, a);
        int i = 0;
        while (i < this.num_cartas) {
            int j = 0;
            imprimir(" ║");
            while (j < imprimirOrdem.size()) {
                imprimir(" ");
                if (i == 0) {
                    if (imprimirOrdem.get(j) == (null)) {
                        imprimir("     ");
                    } else if (imprimirOrdem.get(j) == a) {
                        if (this.num_cartas <= 1) {
                            imprimir("│  Mão ");
                        } else {
                            imprimir("│      ");
                        }
                    } else {
                        if (!imprimirOrdem.get(j).isEmpty()) {
                            if (imprimirOrdem.get(j) == this.descarte) {
                                if (i < imprimirOrdem.get(j).size()) {
                                    imprimir(imprimirOrdem.get(j).get(imprimirOrdem.get(j).size() - this.num_cartas + i).validacao("get_carta"));
                                } else {
                                    imprimir("  ⌂  ");
                                }
                            } else {
                                imprimir(imprimirOrdem.get(j).peek().validacao("get_carta"));
                            }
                        } else {
                            imprimir("  ⌂  ");
                        }
                    }
                } else {
                    if (imprimirOrdem.get(j) == this.descarte) {
                        if (i < imprimirOrdem.get(j).size()) {
                                imprimir(imprimirOrdem.get(j).get(imprimirOrdem.get(j).size() - this.num_cartas + i).validacao("get_carta"));
                        } else {
                            imprimir("  ⌂  ");
                        }
                    } else if (imprimirOrdem.get(j) == a) {
                        if ((Math.floor((this.num_cartas)/2)) == i) {
                            imprimir("│  Mão ");
                        } else {
                            imprimir("│      ");
                        } 
                    } else {
                        imprimir("     ");
                    }
                }
                j++;    
            }
            imprimirln(" ║ ");
            i++;
        }

        imprimirln(" ╟───────────────────────────────────────────┼───────╢ ");
    }

    public void imprimeGameBottom() {
        List<LinkedList<carta>> imprimirOrdem = Arrays.asList(this.pilha1, this.pilha2, this.pilha3, this.pilha4, this.pilha5, this.pilha6, this.pilha7);
        int i = 0;
        while (i >= 0) {
            int j = 0;
            int contador = 0;
            imprimir(" ║");
            while (j < imprimirOrdem.size()) {
                imprimir(" ");
                if (i == 0) {
                    if (imprimirOrdem.get(j) == (null)) {
                        imprimir("     ");
                    } else {
                        if (!imprimirOrdem.get(j).isEmpty()) {
                            imprimir(imprimirOrdem.get(j).get(i).validacao("get_carta"));
                        } else {
                            imprimir("  ⌂  ");
                        }
                    }
                    contador++;
                } else {
                    if (i < imprimirOrdem.get(j).size()) {
                        imprimir(imprimirOrdem.get(j).get(i).validacao("get_carta"));
                        contador++;
                    } else {
                        imprimir("     ");
                    }
                }
                j++;
            }
            imprimirln(" ║ ");
            if (contador == 0) {
                i = -1;
            } else {
                i++;
            }
        }
        imprimirln(" ╚═══════════════════════════════════════════╝ ");
    }
    
    public void imprimeGameBottom(Stack<carta> a) {
        List<LinkedList<carta>> imprimirOrdem = Arrays.asList(this.pilha1, this.pilha2, this.pilha3, this.pilha4, this.pilha5, this.pilha6, this.pilha7);
        Stack<carta> b = (Stack<carta>)a.clone();
        int i = 0;
        while (i >= 0) {
            int j = 0;
            int contador = 0;
            imprimir(" ║");
            while (j <= imprimirOrdem.size()) {
                imprimir(" ");
                if (j == imprimirOrdem.size()) {
                    if (i < a.size()) {
                        imprimir("│ " + b.pop().validacao("get_carta"));
                    } else {
                        imprimir("│      ");
                    }
                } else {
                    if (i == 0) {
                        if (imprimirOrdem.get(j) == (null)) {
                            imprimir("     ");
                        } else {
                            if (!imprimirOrdem.get(j).isEmpty()) {
                                imprimir(imprimirOrdem.get(j).get(i).validacao("get_carta"));
                            } else {
                                imprimir("  ⌂  ");
                            }
                        }
                        contador++;
                    } else {
                        if (i < imprimirOrdem.get(j).size()) {
                            imprimir(imprimirOrdem.get(j).get(i).validacao("get_carta"));
                            contador++;
                        } else {
                            imprimir("     ");
                        }
                    }
                }
                j++;
            }
            imprimirln(" ║ ");
            if (contador == 0) {
                i = -1;
            } else {
                i++;
            }
        }
        imprimirln(" ╚═══════════════════════════════════════════╧═══════╝ ");
    }
    
    public Object[] imprimeGameMenu(String status) {
        Object[] retorno = {false, ""};
        Scanner sc = new Scanner(System.in);
        int a = 0;
        
        imprimirln();
        imprimirln("(R) Reiniciar Jogo");
        imprimirln("(S) Sair do Jogo");
        imprimirln("(E) Sacar carta do estoque");
        imprimirln("(D) Selecionar carta do descarte");
        imprimirln("(F1) Selecionar carta da fundação 1");
        imprimirln("(F2) Selecionar carta da fundação 2");
        imprimirln("(F3) Selecionar carta da fundação 3");
        imprimirln("(F4) Selecionar carta da fundação 4");
        imprimirln("(P1) Selecionar carta(s) da pilha 1");
        imprimirln("(P2) Selecionar carta(s) da pilha 2");
        imprimirln("(P3) Selecionar carta(s) da pilha 3");
        imprimirln("(P4) Selecionar carta(s) da pilha 4");
        imprimirln("(P5) Selecionar carta(s) da pilha 5");
        imprimirln("(P6) Selecionar carta(s) da pilha 6");
        imprimirln("(P7) Selecionar carta(s) da pilha 7");

        imprimirln();
        imprimirln("\u001B[31m" + status +  "\u001B[30m");
        imprimirln(" --> Digite o valor correspondente a opção desejada: ");

        carta b;
        
        Stack<carta> c = new Stack();
        Stack<carta> d = new Stack();
        try{
            switch (sc.next().toUpperCase()) {
                case "R":
                    zerajogo();
                    iniciaJogo();
                    retorno[0] = true;
                    break;
                case "S":
                    retorno[0] = false;
                    break;
                case "E":
                    sacarEstoque();
                    retorno[0] = true;
                    break;
                case "D":
                    if (this.descarte.isEmpty()){
                        retorno[1] = "O descarte está vazio!";
                    } else {
                        b = this.descarte.pop();
                                            retorno = selecionarCarta(b);
                        if ((boolean)retorno[0]) {
                            this.movimentos++;
                        } else {
                            this.descarte.push(b);
                        }
                    }
                    retorno[0] = true;
                    break;
                case "F1":
                    if (this.fundacao1.isEmpty()){
                        retorno[1] = "A fundação1 está vazia!";
                    } else {
                        b = this.fundacao1.pop();
                        retorno = selecionarCarta(b);
                        if ((boolean)retorno[0]) {
                            this.movimentos++;
                        } else {
                            this.fundacao1.push(b);
                        }
                    }
                    retorno[0] = true;
                    break;
                case "F2":
                    if (this.fundacao2.isEmpty()){
                        retorno[1] = "A fundação2 está vazia!";
                    } else {
                        b = this.fundacao2.pop();
                        retorno = selecionarCarta(b);
                        if ((boolean)retorno[0]) {
                            this.movimentos++;
                        } else {
                            this.fundacao2.push(b);
                        }
                    }
                    retorno[0] = true;
                    break;
                case "F3":
                    if (this.fundacao3.isEmpty()){
                        retorno[1] = "A fundação3 está vazia!";
                    } else {
                        b = this.fundacao3.pop();
                        retorno = selecionarCarta(b);
                        if ((boolean)retorno[0]) {
                            this.movimentos++;
                        } else {
                            this.fundacao3.push(b);
                        }
                    }
                    retorno[0] = true;
                    break;
                case "F4":
                    if (this.fundacao4.isEmpty()){
                        retorno[1] = "A fundação4 está vazia!";
                    } else {
                        b = this.fundacao4.pop();
                        retorno = selecionarCarta(b);
                        if ((boolean)retorno[0]) {
                            this.movimentos++;
                        } else {
                            this.fundacao4.push(b);
                        }
                    }
                    retorno[0] = true;
                    break;
                case "P1":
                    if (this.pilha1.isEmpty()){
                        retorno[1] = "A pilha1 está vazia!";
                    } else {
                        imprimirln();
                        imprimirln("Quantas cartas você quer mover?");
                        a = sc.nextInt();
                        if (a == 1) {
                            b = this.pilha1.removeLast();
                            retorno = selecionarCarta(b);
                            if ((boolean)retorno[0]) {
                                if (!this.pilha1.isEmpty()){
                                    this.pilha1.getLast().set_escondida(false);
                                }
                                this.movimentos++;
                            } else {
                                this.pilha1.addLast(b);
                            }
                        } else if (a > 1){
                            if (this.pilha1.size() < a) {
                                retorno[1] = "Você tentou mover uma quantidade superior à existente na pilha!";
                            } else {
                                b = this.pilha1.get(this.pilha1.size() - a);
                                if (b.get_escondida()) {
                                    retorno[1] = "Cartas escondidas não podem ser movidas!";
                                } else {
                                    for (int i = 0; i < a; i++) {
                                        b = this.pilha1.removeLast();
                                        c.push(b);
                                        d.push(b);                                
                                    }

                                    retorno = selecionarCartas(c);
                                    if ((boolean)retorno[0]) {
                                        if (!this.pilha1.isEmpty()){
                                            this.pilha1.getLast().set_escondida(false);
                                        }
                                        this.movimentos++;
                                    } else {
                                        for (int i = 0; i < a; i++) {
                                            this.pilha1.addLast(d.pop());
                                        }
                                    }
                                }
                            }
                        } else {
                            retorno[1] = "Opção inválida! Tente novamente!";
                        }
                    }
                    retorno[0] = true;
                    break;
                case "P2":
                    if (this.pilha2.isEmpty()){
                        retorno[1] = "A pilha2 está vazia!";
                    } else {
                        imprimirln();
                        imprimirln("Quantas cartas você quer mover?");
                        a = sc.nextInt();
                        if (a == 1) {
                            b = this.pilha2.removeLast();
                            retorno = selecionarCarta(b);
                            if ((boolean)retorno[0]) {
                                if (!this.pilha2.isEmpty()){
                                    this.pilha2.getLast().set_escondida(false);
                                }
                                this.movimentos++;
                            } else {
                                this.pilha2.addLast(b);
                            }
                        } else if (a > 1){
                            if (this.pilha2.size() < a) {
                                retorno[1] = "Você tentou mover uma quantidade superior à existente na pilha!";
                            } else {
                                b = this.pilha2.get(this.pilha2.size() - a);
                                if (b.get_escondida()) {
                                    retorno[1] = "Cartas escondidas não podem ser movidas!";
                                } else {
                                    for (int i = 0; i < a; i++) {
                                        b = this.pilha2.removeLast();
                                        c.push(b);
                                        d.push(b);                                
                                    }

                                    retorno = selecionarCartas(c);
                                    if ((boolean)retorno[0]) {
                                        if (!this.pilha2.isEmpty()){
                                            this.pilha2.getLast().set_escondida(false);
                                        }
                                        this.movimentos++;
                                    } else {
                                        for (int i = 0; i < a; i++) {
                                            this.pilha2.addLast(d.pop());
                                        }
                                    }
                                }
                            }
                        } else {
                        retorno[1] = "Opção inválida! Tente novamente!";
                        }
                    }
                    retorno[0] = true;
                    break;
                case "P3":
                    if (this.pilha3.isEmpty()){
                        retorno[1] = "A pilha3 está vazia!";
                    } else {
                        imprimirln();
                        imprimirln("Quantas cartas você quer mover?");
                        a = sc.nextInt();
                        if (a == 1) {
                            b = this.pilha3.removeLast();
                            retorno = selecionarCarta(b);
                            if ((boolean)retorno[0]) {
                                if (!this.pilha3.isEmpty()){
                                    this.pilha3.getLast().set_escondida(false);
                                }
                                this.movimentos++;
                            } else {
                                this.pilha3.addLast(b);
                            }
                        } else if (a > 1){
                            if (this.pilha3.size() < a) {
                                retorno[1] = "Você tentou mover uma quantidade superior à existente na pilha!";
                            } else {
                                b = this.pilha3.get(this.pilha3.size() - a);
                                if (b.get_escondida()) {
                                    retorno[1] = "Cartas escondidas não podem ser movidas!";
                                } else {
                                    for (int i = 0; i < a; i++) {
                                        b = this.pilha3.removeLast();
                                        c.push(b);
                                        d.push(b);                                
                                    }

                                    retorno = selecionarCartas(c);
                                    if ((boolean)retorno[0]) {
                                        if (!this.pilha3.isEmpty()){
                                            this.pilha3.getLast().set_escondida(false);
                                        }
                                        this.movimentos++;
                                    } else {
                                        for (int i = 0; i < a; i++) {
                                            this.pilha3.addLast(d.pop());
                                        }
                                    }
                                }
                            }
                        } else {
                        retorno[1] = "Opção inválida! Tente novamente!";
                        }
                    }
                    retorno[0] = true;
                    break;
                case "P4":
                    if (this.pilha4.isEmpty()){
                        retorno[1] = "A pilha4 está vazia!";
                    } else {
                        imprimirln();
                        imprimirln("Quantas cartas você quer mover?");
                        a = sc.nextInt();
                        if (a == 1) {
                            b = this.pilha4.removeLast();
                            retorno = selecionarCarta(b);
                            if ((boolean)retorno[0]) {
                                if (!this.pilha4.isEmpty()){
                                    this.pilha4.getLast().set_escondida(false);
                                }
                                this.movimentos++;
                            } else {
                                this.pilha4.addLast(b);
                            }
                        } else if (a > 1){
                            if (this.pilha4.size() < a) {
                                retorno[1] = "Você tentou mover uma quantidade superior à existente na pilha!";
                            } else {
                                b = this.pilha4.get(this.pilha4.size() - a);
                                if (b.get_escondida()) {
                                    retorno[1] = "Cartas escondidas não podem ser movidas!";
                                } else {
                                    for (int i = 0; i < a; i++) {
                                        b = this.pilha4.removeLast();
                                        c.push(b);
                                        d.push(b);                                
                                    }

                                    retorno = selecionarCartas(c);
                                    if ((boolean)retorno[0]) {
                                        if (!this.pilha4.isEmpty()){
                                            this.pilha4.getLast().set_escondida(false);
                                        }
                                        this.movimentos++;
                                    } else {
                                        for (int i = 0; i < a; i++) {
                                            this.pilha4.addLast(d.pop());
                                        }
                                    }
                                }
                            }
                        } else {
                        retorno[1] = "Opção inválida! Tente novamente!";
                        }
                    }
                    retorno[0] = true;
                    break;
                case "P5":
                    if (this.pilha5.isEmpty()){
                        retorno[1] = "A pilha5 está vazia!";
                    } else {
                        imprimirln();
                        imprimirln("Quantas cartas você quer mover?");
                        a = sc.nextInt();
                        if (a == 1) {
                            b = this.pilha5.removeLast();
                            retorno = selecionarCarta(b);
                            if ((boolean)retorno[0]) {
                                if (!this.pilha5.isEmpty()){
                                    this.pilha5.getLast().set_escondida(false);
                                }
                                this.movimentos++;
                            } else {
                                this.pilha5.addLast(b);
                            }
                        } else if (a > 1){
                            if (this.pilha5.size() < a) {
                                retorno[1] = "Você tentou mover uma quantidade superior à existente na pilha!";
                            } else {
                                b = this.pilha5.get(this.pilha5.size() - a);
                                if (b.get_escondida()) {
                                    retorno[1] = "Cartas escondidas não podem ser movidas!";
                                } else {
                                    for (int i = 0; i < a; i++) {
                                        b = this.pilha5.removeLast();
                                        c.push(b);
                                        d.push(b);                                
                                    }

                                    retorno = selecionarCartas(c);
                                    if ((boolean)retorno[0]) {
                                        if (!this.pilha5.isEmpty()){
                                            this.pilha5.getLast().set_escondida(false);
                                        }
                                        this.movimentos++;
                                    } else {
                                        for (int i = 0; i < a; i++) {
                                            this.pilha5.addLast(d.pop());
                                        }
                                    }
                                }
                            }
                        } else {
                        retorno[1] = "Opção inválida! Tente novamente!";
                        }
                    }
                    retorno[0] = true;
                    break;
                case "P6":
                    if (this.pilha6.isEmpty()){
                        retorno[1] = "A pilha6 está vazia!";
                    } else {
                        imprimirln();
                        imprimirln("Quantas cartas você quer mover?");
                        a = sc.nextInt();
                        if (a == 1) {
                            b = this.pilha6.removeLast();
                            retorno = selecionarCarta(b);
                            if ((boolean)retorno[0]) {
                                if (!this.pilha6.isEmpty()){
                                    this.pilha6.getLast().set_escondida(false);
                                }
                                this.movimentos++;
                            } else {
                                this.pilha6.addLast(b);
                            }
                        } else if (a > 1){
                            if (this.pilha6.size() < a) {
                                retorno[1] = "Você tentou mover uma quantidade superior à existente na pilha!";
                            } else {
                                b = this.pilha6.get(this.pilha6.size() - a);
                                if (b.get_escondida()) {
                                    retorno[1] = "Cartas escondidas não podem ser movidas!";
                                } else {
                                    for (int i = 0; i < a; i++) {
                                        b = this.pilha6.removeLast();
                                        c.push(b);
                                        d.push(b);                                
                                    }

                                    retorno = selecionarCartas(c);
                                    if ((boolean)retorno[0]) {
                                        this.movimentos++;
                                        if (!this.pilha6.isEmpty()){
                                            this.pilha6.getLast().set_escondida(false);
                                        }
                                    } else {
                                        for (int i = 0; i < a; i++) {
                                            this.pilha6.addLast(d.pop());
                                        }
                                    }
                                }
                            }
                        } else {
                        retorno[1] = "Opção inválida! Tente novamente!";
                        }
                    }
                    retorno[0] = true;
                    break;
                case "P7":
                    if (this.pilha7.isEmpty()){
                        retorno[1] = "A pilha7 está vazia!";
                    } else {
                        imprimirln();
                        imprimirln("Quantas cartas você quer mover?");
                        a = sc.nextInt();
                        if (a == 1) {
                            b = this.pilha7.removeLast();
                            retorno = selecionarCarta(b);
                            if ((boolean)retorno[0]) {
                                if (!this.pilha7.isEmpty()){
                                    this.pilha7.getLast().set_escondida(false);
                                }
                                this.movimentos++;
                            } else {
                                this.pilha7.addLast(b);
                            }
                        } else if (a > 1){
                            if (this.pilha7.size() < a) {
                                retorno[1] = "Você tentou mover uma quantidade superior à existente na pilha!";
                            } else {
                                b = this.pilha7.get(this.pilha7.size() - a);
                                if (b.get_escondida()) {
                                    retorno[1] = "Cartas escondidas não podem ser movidas!";
                                } else {
                                    for (int i = 0; i < a; i++) {
                                        b = this.pilha7.removeLast();
                                        c.push(b);
                                        d.push(b);                                
                                    }

                                    retorno = selecionarCartas(c);
                                    if ((boolean)retorno[0]) {
                                        if (!this.pilha7.isEmpty()){
                                            this.pilha7.getLast().set_escondida(false);
                                        }
                                        this.movimentos++;
                                    } else {
                                        for (int i = 0; i < a; i++) {
                                            this.pilha7.addLast(d.pop());
                                        }
                                    }
                                }
                            }
                        } else {
                        retorno[1] = "Opção inválida! Tente novamente!";
                        }
                    }
                    retorno[0] = true;
                    break;
                default:
                    retorno[1] = "Opção inválida! Tente novamente!";
                    retorno[0] = true;
                    break;
            }
        } catch (InputMismatchException e) {
            retorno[1] = "Valor inválido! Tente novamente!";
            retorno[0] = true;
            sc.next(); 
        }
        return retorno;
    }
    
    public void atualizarEstoque() {      
            if (this.descarte.isEmpty()){
                imprimirln("Não há cartas para atualizar estoque!!!");
            } else {
                while (!this.descarte.isEmpty()) {
                    carta a = this.descarte.pop();
                    a.set_escondida(true);
                    this.estoque.push(a);
                }
                this.movimentos++;
            }
    }
    
    public void sacarEstoque() {
        if (this.estoque.isEmpty()){
            imprimirln("O estoque está vazio!");
            atualizarEstoque();
        } else {
            for (int i = 0; i < this.num_cartas; i++) {
                if (!this.estoque.isEmpty()) {
                    carta a = this.estoque.pop();
                    a.set_escondida(false);            
                    this.descarte.push(a);
                }
            }
            this.movimentos++;
        }
    }
    
    public void imprimeAll() {
        Object[] condicao = {true, ""};
        
        while ((boolean) condicao[0]) {
            limparTela();

            imprimirln(" ############### Qtd. de Movimentos: " + this.movimentos + " ############### ");
            imprimirln();
            
            imprimeGameTop();
            imprimeGameBottom();
            condicao = imprimeGameMenu((String) condicao[1]);
        }
    }
    
    public void imprimeGame() {
            limparTela();
            imprimirln(" ######################### Movimentos: " + this.movimentos + " ######################### ");
            imprimirln();
            
            imprimeGameTop();
            imprimeGameBottom();
    }
    
    public void imprimeGame(Stack<carta> a) {
            limparTela();
            imprimirln(" ######################### Movimentos: " + this.movimentos + " ######################### ");
            imprimirln();
            
            imprimeGameTop(a);
            imprimeGameBottom(a);
    }
    
    public Object[] moverCartaFundacao(carta a, Stack<carta> b) {
        Object[] retorno = {false, ""};
        if (b.isEmpty()) {
            if (a.get_valor() == 0) {
                b.push(a);
                retorno[0] = true;
            } else {
                retorno[1] = "A carta não é um 'A'!";
            }
        } else {
            if (a.get_naipe() == b.peek().get_naipe()) {
                if (a.get_valor() == b.peek().get_valor() + 1) {
                    b.push(a);
                    retorno[0] = true;
                } else {
                    retorno[1] = "A carta possui um valor diferente do valor subsequente ao da fundação!";
                }
            } else {
                retorno[1] = "A carta possui naipe diferente da fundação!";
            }
        }
        return retorno;
    }
    
    public Object[] moverCartaPilha(carta a, LinkedList<carta> b) {
        Object[] retorno = {false, ""};
        if (b.isEmpty()) {
            if (a.get_valor() == 12) {
                b.addLast(a);
                retorno[0] = true;
            } else {
                retorno[1] = "A carta não é um 'K'!";
            }
        } else {
            if ((a.get_naipe() % 2) != (b.getLast().get_naipe() % 2)) {
                if (a.get_valor() == b.getLast().get_valor() - 1) {
                    b.addLast(a);
                    retorno[0] = true;
                } else {
                    retorno[1] = "A carta possui um valor diferente do valor subsequente ao da pilha!";
                }
            } else {
                retorno[1] = "A carta possui naipe inválido para a regra da pilha!";
            }
        }
        return retorno;
    }
    
    public Object[] moverCartasPilha(Stack<carta> a, LinkedList<carta> b) {
        Object[] retorno = {true, ""};
        boolean i = true;
        while (i && (boolean)retorno[0]) {
            retorno = moverCartaPilha(a.pop(), b);
            if (a.isEmpty()) {
                i = false;
            }
        }
        return retorno;
    }
    
    public Object[] selecionarCarta(carta a) {
        Object[] retorno = {false, ""};
        Scanner sc = new Scanner(System.in);
        
        Stack<carta> b = new Stack();

        if (!a.get_escondida()) {
            
            b.add(a);
            
            imprimeGame(b);
            
            imprimirln();

            imprimirln("(F1) Mover carta da fundação 1");
            imprimirln("(F2) Mover carta da fundação 2");
            imprimirln("(F3) Mover carta da fundação 3");
            imprimirln("(F4) Mover carta da fundação 4");

            imprimirln("(P1) Mover carta para a pilha 1");
            imprimirln("(P2) Mover carta para a pilha 2");
            imprimirln("(P3) Mover carta para a pilha 3");
            imprimirln("(P4) Mover carta para a pilha 4");
            imprimirln("(P5) Mover carta para a pilha 5");
            imprimirln("(P6) Mover carta para a pilha 6");
            imprimirln("(P7) Mover carta para a pilha 7");

            imprimirln();

            imprimirln(" --> Digite o valor correspondente a opção desejada: ");

             switch (sc.next().toUpperCase()) {
                case "F1":
                    retorno = moverCartaFundacao(a, this.fundacao1);
                    break;
                case "F2":
                    retorno = moverCartaFundacao(a, this.fundacao2);

                    break;
                case "F3":
                    retorno = moverCartaFundacao(a, this.fundacao3);

                    break;
                case "F4":
                    retorno = moverCartaFundacao(a, this.fundacao4);
                    break;
                case "P1":
                    retorno = moverCartaPilha(a, this.pilha1);
                    break;
                case "P2":
                    retorno = moverCartaPilha(a, this.pilha2);
                    break;
                case "P3":
                    retorno = moverCartaPilha(a, this.pilha3);
                    break;
                case "P4":
                    retorno = moverCartaPilha(a, this.pilha4);
                    break;
                case "P5":
                    retorno = moverCartaPilha(a, this.pilha5);
                    break;
                case "P6":
                    retorno = moverCartaPilha(a, this.pilha6);
                    break;
                case "P7":
                    retorno = moverCartaPilha(a, this.pilha7);
                    break;
                default:
                    retorno[0] = false;
                    retorno[1] = "Opção inválida! Tente novamente!";
                    break;
            }
        }
        return retorno;
    }
    
    public void limparTela() {
        try {
            ProcessBuilder pb = new ProcessBuilder("cmd.exe", "/c", "cls").inheritIO();
            Process p = pb.start();
            p.waitFor();
        } catch (IOException | InterruptedException ex) {
            imprimirln(ex.getMessage());
        }

    }
    
    public static void limparTelaInicial() {
        try {
            ProcessBuilder pb = new ProcessBuilder("cmd.exe", "/c", "cls").inheritIO();
            Process p = pb.start();
            p.waitFor();
        } catch (IOException | InterruptedException ex) {
            imprimirln(ex.getMessage());
        }

    }
    
    public Object[] selecionarCartas(Stack<carta> a) {
        Object[] retorno = {false, ""};
        Scanner sc = new Scanner(System.in);
        
        imprimeGame(a);
        
        imprimirln();

        imprimirln("(P1) Mover cartas para a pilha 1");
        imprimirln("(P2) Mover cartas para a pilha 2");
        imprimirln("(P3) Mover cartas para a pilha 3");
        imprimirln("(P4) Mover cartas para a pilha 4");
        imprimirln("(P5) Mover cartas para a pilha 5");
        imprimirln("(P6) Mover cartas para a pilha 6");
        imprimirln("(P7) Mover cartas para a pilha 7");

        imprimirln();

        imprimirln(" --> Digite o valor correspondente a opção desejada: ");

         switch (sc.next().toUpperCase()) {
            case "P1":
                retorno = (moverCartasPilha(a, this.pilha1));
                break;
            case "P2":
                retorno = (moverCartasPilha(a, this.pilha2));
                break;
            case "P3":
                retorno = (moverCartasPilha(a, this.pilha3));
                break;
            case "P4":
                retorno = (moverCartasPilha(a, this.pilha4));
                break;
            case "P5":
                retorno = (moverCartasPilha(a, this.pilha5));
                break;
            case "P6":
                retorno = (moverCartasPilha(a, this.pilha6));
                break;
            case "P7":
                retorno = (moverCartasPilha(a, this.pilha7));
                break;
            default:
                retorno[0] = false;
                retorno[1] = "Opção inválida! Tente novamente!";
                break;
        }
        return retorno;
    }
    /**
     * @param args the command line arguments
     */
    
    public static void main(String[] args) {
        
            try {
                ProcessBuilder pb = new ProcessBuilder("cmd.exe", "/c", "@echo", "off").inheritIO();
                Process p = pb.start();
                p.waitFor();
                
                pb = new ProcessBuilder("cmd.exe", "/c", "chcp", "65001").inheritIO();
                p = pb.start();
                p.waitFor();
                
                pb = new ProcessBuilder("cmd.exe", "/c", "color", "20").inheritIO();
                p = pb.start();
                p.waitFor();
                
                pb = new ProcessBuilder("cmd.exe", "/c", "cls").inheritIO();
                p = pb.start();
                p.waitFor();

                boolean condicao = true;

                Scanner sc = new Scanner(System.in);
                
                String b = "";

                while (condicao) {
                    
                    limparTelaInicial();

                    imprimirln();

                    imprimirln("(F) Fechar Aplicativo");
                    imprimirln("(G) Iniciar Game");

                    imprimirln();
                    imprimirln("\u001B[31m" + b + "\u001B[30m");
                    imprimirln(" --> Digite o valor correspondente a opção desejada: ");
                    try {
                        switch (sc.next().toUpperCase()) {
                            case "F":
                                condicao = false;
                                break;
                            case "G":
                                imprimirln();
                                imprimirln("Quantas cartas deseja virar?");
                                int a = sc.nextInt();
                                if (a > 0) {
                                    if (a > 24) {
                                        b = "Valor maior que 24! Tente novamente!";
                                    } else {
                                        Paciencia game = new Paciencia(a);

                                        game.iniciaJogo();

                //game.baralho.get_cartasStr();

                                        game.imprimeAll();
                                    }
                                } else {
                                    b = "Valor inválido! Tente novamente!";
                                }
                                break;
                            default:
                                b = "Opção inválida! Tente novamente!";
                                break;
                        }
                    } catch (InputMismatchException e) {
                        
                        b = "Valor inválido! Tente novamente!";
                        sc.next(); 
                    }
                }
            } catch (IOException | InterruptedException ex) {
                System.out.println(ex.getMessage());
            }

   }
}
