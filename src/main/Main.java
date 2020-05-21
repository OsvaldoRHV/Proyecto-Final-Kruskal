/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import algoritmo.Kruskal;
import util.FuncionesBasicas;

public class Main {
    
    public static void main(String[] args) {
        int numeroNodos;
        int repetir = 0;
        FuncionesBasicas util = new FuncionesBasicas();
        do{
        System.out.println("Algoritmo de Kruskal");
        numeroNodos = util.solicitarIntConsola("Ingresar número de nodos");
        Kruskal kruskal = new Kruskal(numeroNodos);
        kruskal.empezarAlgoritmo();
        repetir = util.solicitarIntConsola("\n¿Desea calcular otro arbol de expansion minima? 1 = SI");
        }while(repetir==1);
    }
}
