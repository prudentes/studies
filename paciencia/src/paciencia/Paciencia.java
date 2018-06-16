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
        System.out.println(" ╔═══════════════════════════════════════\t╗ ");
        List<Stack<carta>> imprimirOrdem = Arrays.asList(this.estoque, this.descarte, null, this.fundacao1, this.fundacao2, this.fundacao3, this.fundacao4);
        int i = 0;
        while (i < this.num_cartas) {
            int j = 0;
            System.out.print(" ║ ");
            while (j < imprimirOrdem.size()) {
                System.out.print("\t");
                if (i == 0) {
                    if (imprimirOrdem.get(j) == (null)) {
                        System.out.print("     ");
                    } else {
                        if (!imprimirOrdem.get(j).isEmpty()) {
                            if (imprimirOrdem.get(j) == this.descarte) {
                                if (i < imprimirOrdem.get(j).size()) {
                                    System.out.print(imprimirOrdem.get(j).get(imprimirOrdem.get(j).size() - this.num_cartas + i).validacao("get_carta"));
                                } else {
                                    System.out.print("  ▢  ");
                                }
                            } else {
                                System.out.print(imprimirOrdem.get(j).peek().validacao("get_carta"));
                            }
                        } else {
                            System.out.print("  ▢  ");
                        }
                    }
                } else {
                    if (imprimirOrdem.get(j) == this.descarte) {
                        if (i < imprimirOrdem.get(j).size()) {
                            System.out.print(imprimirOrdem.get(j).get(imprimirOrdem.get(j).size() - this.num_cartas + i).validacao("get_carta"));
                        } else {
                            System.out.print("  ▢  ");
                        }
                    } else {
                        System.out.print("     ");
                    }
                }
                j++;    
            }
            System.out.println("\t║ ");
            i++;
        }

        System.out.println(" ╟───────────────────────────────────────\t╢ ");
    }

    public void imprimeGameBottom() {
        List<LinkedList<carta>> imprimirOrdem = Arrays.asList(this.pilha1, this.pilha2, this.pilha3, this.pilha4, this.pilha5, this.pilha6, this.pilha7);
        int i = 0;
        while (i >= 0) {
            int j = 0;
            int contador = 0;
            System.out.print(" ║ ");
            while (j < imprimirOrdem.size()) {
                System.out.print("\t");
                if (i == 0) {
                    if (imprimirOrdem.get(j) == (null)) {
                        System.out.print("     ");
                    } else {
                        if (!imprimirOrdem.get(j).isEmpty()) {
                            System.out.print(imprimirOrdem.get(j).get(i).validacao("get_carta"));
                        } else {
                            System.out.print("  ▢  ");
                        }
                    }
                    contador++;
                } else {
                    if (i < imprimirOrdem.get(j).size()) {
                        System.out.print(imprimirOrdem.get(j).get(i).validacao("get_carta"));
                        contador++;
                    } else {
                        System.out.print("     ");
                    }
                }
                j++;
            }
            System.out.println("\t║ ");
            if (contador == 0) {
                i = -1;
            } else {
                i++;
            }
        }
        System.out.println(" ╚═══════════════════════════════════════\t╝ ");
    }
    public boolean imprimeGameMenu() {
        boolean retorno = false;
        Scanner sc = new Scanner(System.in);
        int a = 0;
        
        System.out.println();
        System.out.println("(R) Reiniciar Jogo");
        System.out.println("(S) Sair do Jogo");
        System.out.println("(E) Sacar carta do estoque");
        System.out.println("(D) Selecionar carta do descarte");
        System.out.println("(F-1) Selecionar carta da fundação 1");
        System.out.println("(F-2) Selecionar carta da fundação 2");
        System.out.println("(F-3) Selecionar carta da fundação 3");
        System.out.println("(F-4) Selecionar carta da fundação 4");
        System.out.println("(P-1) Selecionar carta(s) da pilha 1");
        System.out.println("(P-2) Selecionar carta(s) da pilha 2");
        System.out.println("(P-3) Selecionar carta(s) da pilha 3");
        System.out.println("(P-4) Selecionar carta(s) da pilha 4");
        System.out.println("(P-5) Selecionar carta(s) da pilha 5");
        System.out.println("(P-6) Selecionar carta(s) da pilha 6");
        System.out.println("(P-7) Selecionar carta(s) da pilha 7");

        System.out.println();

        System.out.println(" --> Digite o valor correspondente a opção desejada: ");

        carta b;
        
        Stack<carta> c = new Stack();
        Stack<carta> d = new Stack();

        switch (sc.next().toUpperCase()) {
            case "R":
                zerajogo();
                iniciaJogo();
                retorno = true;
                break;
            case "S":
                retorno = false;
                break;
            case "E":
                sacarEstoque();
                retorno = true;
                break;
            case "D":
                if (this.descarte.isEmpty()){
                    System.out.println("O descarte está vazio!");
                } else {
                    b = this.descarte.pop();
                    if (selecionarCarta(b)) {
                        this.movimentos++;
                    } else {
                        this.descarte.push(b);
                    }
                }
                retorno = true;
                break;
            case "F-1":
                if (this.fundacao1.isEmpty()){
                    System.out.println("A fundação1 está vazia!");
                } else {
                    b = this.fundacao1.pop();
                    if (selecionarCarta(b)) {
                        this.movimentos++;
                    } else {
                        this.fundacao1.push(b);
                    }
                }
                retorno = true;
                break;
            case "F-2":
                if (this.fundacao2.isEmpty()){
                    System.out.println("A fundação2 está vazia!");
                } else {
                    b = this.fundacao2.pop();
                    if (selecionarCarta(b)) {
                        this.movimentos++;
                    } else {
                        this.fundacao2.push(b);
                    }
                }
                retorno = true;
                break;
            case "F-3":
                if (this.fundacao3.isEmpty()){
                    System.out.println("A fundação3 está vazia!");
                } else {
                    b = this.fundacao3.pop();
                    if (selecionarCarta(b)) {
                        this.movimentos++;
                    } else {
                        this.fundacao3.push(b);
                    }
                }
                retorno = true;
                break;
            case "F-4":
                if (this.fundacao4.isEmpty()){
                    System.out.println("A fundação4 está vazia!");
                } else {
                    b = this.fundacao4.pop();
                    if (selecionarCarta(b)) {
                        this.movimentos++;
                    } else {
                        this.fundacao4.push(b);
                    }
                }
                retorno = true;
                break;
            case "P-1":
                if (this.pilha1.isEmpty()){
                    System.out.println("A pilha1 está vazia!");
                } else {
                    System.out.println("Quantas cartas você quer mover?");
                    a = sc.nextInt();
                    if (a == 1) {
                        b = this.pilha1.removeLast();
                        if (selecionarCarta(b)) {
                            if (!this.pilha1.isEmpty()){
                                this.pilha1.getLast().set_escondida(false);
                            }
                            this.movimentos++;
                        } else {
                            this.pilha1.addLast(b);
                        }
                    } else if (a > 1){
                        if (this.pilha1.size() < a) {
                            System.out.println("Você tentou mover uma quantidade superior à existente na pilha!");
                        } else {
                            b = this.pilha1.get(this.pilha1.size() - a);
                            if (b.get_escondida()) {
                                System.out.println("Cartas escondidas não podem ser movidas!");
                            } else {
                                for (int i = 0; i < a; i++) {
                                    b = this.pilha1.removeLast();
                                    c.push(b);
                                    d.push(b);                                
                                }

                                if (selecionarCartas(c)) {
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
                        System.out.println("Opção inválida! Tente novamente!");
                    }
                }
                retorno = true;
                break;
            case "P-2":
                if (this.pilha2.isEmpty()){
                    System.out.println("A pilha2 está vazia!");
                } else {
                    System.out.println("Quantas cartas você quer mover?");
                    a = sc.nextInt();
                    if (a == 1) {
                        b = this.pilha2.removeLast();
                        if (selecionarCarta(b)) {
                            if (!this.pilha2.isEmpty()){
                                this.pilha2.getLast().set_escondida(false);
                            }
                            this.movimentos++;
                        } else {
                            this.pilha2.addLast(b);
                        }
                    } else if (a > 1){
                        if (this.pilha2.size() < a) {
                            System.out.println("Você tentou mover uma quantidade superior à existente na pilha!");
                        } else {
                            b = this.pilha2.get(this.pilha2.size() - a);
                            if (b.get_escondida()) {
                                System.out.println("Cartas escondidas não podem ser movidas!");
                            } else {
                                for (int i = 0; i < a; i++) {
                                    b = this.pilha2.removeLast();
                                    c.push(b);
                                    d.push(b);                                
                                }

                                if (selecionarCartas(c)) {
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
                    System.out.println("Opção inválida! Tente novamente!");
                    }
                }
                retorno = true;
                break;
            case "P-3":
                if (this.pilha3.isEmpty()){
                    System.out.println("A pilha3 está vazia!");
                } else {
                    System.out.println("Quantas cartas você quer mover?");
                    a = sc.nextInt();
                    if (a == 1) {
                        b = this.pilha3.removeLast();
                        if (selecionarCarta(b)) {
                            if (!this.pilha3.isEmpty()){
                                this.pilha3.getLast().set_escondida(false);
                            }
                            this.movimentos++;
                        } else {
                            this.pilha3.addLast(b);
                        }
                    } else if (a > 1){
                        if (this.pilha3.size() < a) {
                            System.out.println("Você tentou mover uma quantidade superior à existente na pilha!");
                        } else {
                            b = this.pilha3.get(this.pilha3.size() - a);
                            if (b.get_escondida()) {
                                System.out.println("Cartas escondidas não podem ser movidas!");
                            } else {
                                for (int i = 0; i < a; i++) {
                                    b = this.pilha3.removeLast();
                                    c.push(b);
                                    d.push(b);                                
                                }

                                if (selecionarCartas(c)) {
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
                    System.out.println("Opção inválida! Tente novamente!");
                    }
                }
                retorno = true;
                break;
            case "P-4":
                if (this.pilha4.isEmpty()){
                    System.out.println("A pilha4 está vazia!");
                } else {
                    System.out.println("Quantas cartas você quer mover?");
                    a = sc.nextInt();
                    if (a == 1) {
                        b = this.pilha4.removeLast();
                        if (selecionarCarta(b)) {
                            if (!this.pilha4.isEmpty()){
                                this.pilha4.getLast().set_escondida(false);
                            }
                            this.movimentos++;
                        } else {
                            this.pilha4.addLast(b);
                        }
                    } else if (a > 1){
                        if (this.pilha4.size() < a) {
                            System.out.println("Você tentou mover uma quantidade superior à existente na pilha!");
                        } else {
                            b = this.pilha4.get(this.pilha4.size() - a);
                            if (b.get_escondida()) {
                                System.out.println("Cartas escondidas não podem ser movidas!");
                            } else {
                                for (int i = 0; i < a; i++) {
                                    b = this.pilha4.removeLast();
                                    c.push(b);
                                    d.push(b);                                
                                }

                                if (selecionarCartas(c)) {
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
                    System.out.println("Opção inválida! Tente novamente!");
                    }
                }
                retorno = true;
                break;
            case "P-5":
                if (this.pilha5.isEmpty()){
                    System.out.println("A pilha5 está vazia!");
                } else {
                    System.out.println("Quantas cartas você quer mover?");
                    a = sc.nextInt();
                    if (a == 1) {
                        b = this.pilha5.removeLast();
                        if (selecionarCarta(b)) {
                            if (!this.pilha5.isEmpty()){
                                this.pilha5.getLast().set_escondida(false);
                            }
                            this.movimentos++;
                        } else {
                            this.pilha5.addLast(b);
                        }
                    } else if (a > 1){
                        if (this.pilha5.size() < a) {
                            System.out.println("Você tentou mover uma quantidade superior à existente na pilha!");
                        } else {
                            b = this.pilha5.get(this.pilha5.size() - a);
                            if (b.get_escondida()) {
                                System.out.println("Cartas escondidas não podem ser movidas!");
                            } else {
                                for (int i = 0; i < a; i++) {
                                    b = this.pilha5.removeLast();
                                    c.push(b);
                                    d.push(b);                                
                                }

                                if (selecionarCartas(c)) {
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
                    System.out.println("Opção inválida! Tente novamente!");
                    }
                }
                retorno = true;
                break;
            case "P-6":
                if (this.pilha6.isEmpty()){
                    System.out.println("A pilha6 está vazia!");
                } else {
                    System.out.println("Quantas cartas você quer mover?");
                    a = sc.nextInt();
                    if (a == 1) {
                        b = this.pilha6.removeLast();
                        if (selecionarCarta(b)) {
                            if (!this.pilha6.isEmpty()){
                                this.pilha6.getLast().set_escondida(false);
                            }
                            this.movimentos++;
                        } else {
                            this.pilha6.addLast(b);
                        }
                    } else if (a > 1){
                        if (this.pilha7.size() < a) {
                            System.out.println("Você tentou mover uma quantidade superior à existente na pilha!");
                        } else {
                            b = this.pilha6.get(this.pilha6.size() - a);
                            if (b.get_escondida()) {
                                System.out.println("Cartas escondidas não podem ser movidas!");
                            } else {
                                for (int i = 0; i < a; i++) {
                                    b = this.pilha6.removeLast();
                                    c.push(b);
                                    d.push(b);                                
                                }

                                if (selecionarCartas(c)) {
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
                    System.out.println("Opção inválida! Tente novamente!");
                    }
                }
                retorno = true;
                break;
            case "P-7":
                if (this.pilha7.isEmpty()){
                    System.out.println("A pilha7 está vazia!");
                } else {
                    System.out.println("Quantas cartas você quer mover?");
                    a = sc.nextInt();
                    if (a == 1) {
                        b = this.pilha7.removeLast();
                        if (selecionarCarta(b)) {
                            if (!this.pilha7.isEmpty()){
                                this.pilha7.getLast().set_escondida(false);
                            }
                            this.movimentos++;
                        } else {
                            this.pilha7.addLast(b);
                        }
                    } else if (a > 1){
                        if (this.pilha7.size() < a) {
                            System.out.println("Você tentou mover uma quantidade superior à existente na pilha!");
                        } else {
                            b = this.pilha7.get(this.pilha7.size() - a);
                            if (b.get_escondida()) {
                                System.out.println("Cartas escondidas não podem ser movidas!");
                            } else {
                                for (int i = 0; i < a; i++) {
                                    b = this.pilha7.removeLast();
                                    c.push(b);
                                    d.push(b);                                
                                }

                                if (selecionarCartas(c)) {
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
                    System.out.println("Opção inválida! Tente novamente!");
                    }
                }
                retorno = true;
                break;
            default:
                System.out.println("Opção inválida! Tente novamente!");
                retorno = true;
                break;
        }
        
        return retorno;
    }
    
    public void atualizarEstoque() {      
            if (this.descarte.isEmpty()){
                System.out.println("Não há cartas para atualizar estoque!!!");
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
            System.out.println("O estoque está vazio!");
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
    
    public void imprimeGame() {
        boolean condicao = true;
        
        while (condicao) {
            System.out.println(" ######################### Movimentos: " + this.movimentos + " ######################### ");
            System.out.println();
            
            imprimeGameTop();
            imprimeGameBottom();
            condicao = imprimeGameMenu();
        }
    }
    
    public boolean moverCartaFundacao(carta a, Stack<carta> b) {
        boolean retorno = false;
        if (b.isEmpty()) {
            if (a.get_valor() == 0) {
                b.push(a);
                retorno = true;
            } else {
                System.out.println("A carta não é um 'A'!");
            }
        } else {
            if (a.get_naipe() == b.peek().get_naipe()) {
                if (a.get_valor() == b.peek().get_valor() + 1) {
                    b.push(a);
                    retorno = true;
                } else {
                    System.out.println("A carta possui um valor diferente do valor subsequente ao da fundação!");
                }
            } else {
                System.out.println("A carta possui naipe diferente da fundação!");
            }
        }
        return retorno;
    }
    
    public boolean moverCartaPilha(carta a, LinkedList<carta> b) {
        boolean retorno = false;
        if (b.isEmpty()) {
            if (a.get_valor() == 12) {
                b.addLast(a);
                retorno = true;
            } else {
                System.out.println("A carta não é um 'K'!");
            }
        } else {
            if ((a.get_naipe() % 2) != (b.getLast().get_naipe() % 2)) {
                if (a.get_valor() == b.getLast().get_valor() - 1) {
                    b.addLast(a);
                    retorno = true;
                } else {
                    System.out.println("A carta possui um valor diferente do valor subsequente ao da pilha!");
                }
            } else {
                System.out.println("A carta possui naipe inválido para a regra da pilha!");
            }
        }
        return retorno;
    }
    
    public boolean moverCartasPilha(Stack<carta> a, LinkedList<carta> b) {
        boolean retorno = true;
            for (int i = 0; i < a.size(); i++) {
                retorno = retorno && moverCartaPilha(a.pop(), b);
            }
        return retorno;
    }
    
    public boolean selecionarCarta(carta a) {
        boolean retorno = false;
        Scanner sc = new Scanner(System.in);

        if (!a.get_escondida()) {
        
            System.out.println();

            System.out.println("(F-1) Mover carta da fundação 1");
            System.out.println("(F-2) Mover carta da fundação 2");
            System.out.println("(F-3) Mover carta da fundação 3");
            System.out.println("(F-4) Mover carta da fundação 4");

            System.out.println("(P-1) Mover carta para a pilha 1");
            System.out.println("(P-2) Mover carta para a pilha 2");
            System.out.println("(P-3) Mover carta para a pilha 3");
            System.out.println("(P-4) Mover carta para a pilha 4");
            System.out.println("(P-5) Mover carta para a pilha 5");
            System.out.println("(P-6) Mover carta para a pilha 6");
            System.out.println("(P-7) Mover carta para a pilha 7");

            System.out.println();

            System.out.println(" --> Digite o valor correspondente a opção desejada: ");

             switch (sc.next().toUpperCase()) {
                case "F-1":
                    retorno = (moverCartaFundacao(a, this.fundacao1));
                    break;
                case "F-2":
                    retorno = (moverCartaFundacao(a, this.fundacao2));

                    break;
                case "F-3":
                    retorno = (moverCartaFundacao(a, this.fundacao3));

                    break;
                case "F-4":
                    retorno = (moverCartaFundacao(a, this.fundacao4));
                    break;
                case "P-1":
                    retorno = (moverCartaPilha(a, this.pilha1));
                    break;
                case "P-2":
                    retorno = (moverCartaPilha(a, this.pilha2));
                    break;
                case "P-3":
                    retorno = (moverCartaPilha(a, this.pilha3));
                    break;
                case "P-4":
                    retorno = (moverCartaPilha(a, this.pilha4));
                    break;
                case "P-5":
                    retorno = (moverCartaPilha(a, this.pilha5));
                    break;
                case "P-6":
                    retorno = (moverCartaPilha(a, this.pilha6));
                    break;
                case "P-7":
                    retorno = (moverCartaPilha(a, this.pilha7));
                    break;
                default:
                    System.out.println("Opção inválida! Tente novamente!");
                    retorno = false;
                    break;
            }
        }
        return retorno;
    }
    
    public boolean selecionarCartas(Stack<carta> a) {
        boolean retorno = false;
        Scanner sc = new Scanner(System.in);
        
        System.out.println();

        System.out.println("(P-1) Mover cartas para a pilha 1");
        System.out.println("(P-2) Mover cartas para a pilha 2");
        System.out.println("(P-3) Mover cartas para a pilha 3");
        System.out.println("(P-4) Mover cartas para a pilha 4");
        System.out.println("(P-5) Mover cartas para a pilha 5");
        System.out.println("(P-6) Mover cartas para a pilha 6");
        System.out.println("(P-7) Mover cartas para a pilha 7");

        System.out.println();

        System.out.println(" --> Digite o valor correspondente a opção desejada: ");

         switch (sc.next().toUpperCase()) {
            case "P-1":
                retorno = (moverCartasPilha(a, this.pilha1));
                break;
            case "P-2":
                retorno = (moverCartasPilha(a, this.pilha2));
                break;
            case "P-3":
                retorno = (moverCartasPilha(a, this.pilha3));
                break;
            case "P-4":
                retorno = (moverCartasPilha(a, this.pilha4));
                break;
            case "P-5":
                retorno = (moverCartasPilha(a, this.pilha5));
                break;
            case "P-6":
                retorno = (moverCartasPilha(a, this.pilha6));
                break;
            case "P-7":
                retorno = (moverCartasPilha(a, this.pilha7));
                break;
            default:
                System.out.println("Opção inválida! Tente novamente!");
                retorno = false;
                break;
        }
        return retorno;
    }
    /**
     * @param args the command line arguments
     */
    
    public static void main(String[] args) {
   
        boolean condicao = true;
        
        Scanner sc = new Scanner(System.in);
        
        while (condicao) {
        
            System.out.println();

            System.out.println("(F) Fechar Aplicativo");
            System.out.println("(G) Iniciar Game");

            System.out.println();

            System.out.println(" --> Digite o valor correspondente a opção desejada: ");
            
            switch (sc.next().toUpperCase()) {
                case "F":
                    condicao = false;
                    break;
                case "G":
                    System.out.println("Quantas cartas deseja virar?");
                    int a = sc.nextInt();
                    if (a > 0) {
                        if (a > 24) {
                            System.out.println("Valor maior que 24! Tente novamente!");
                        } else {
                            Paciencia game = new Paciencia(a);

                            game.iniciaJogo();

    //game.baralho.get_cartasStr();

                            game.imprimeGame();
                        }
                    } else {
                        System.out.println("Valor inválido! Tente novamente!");
                    }
                    break;
                default:
                    System.out.println("Opção inválida! Tente novamente!");
                    break;
            }
        }
    }
}
