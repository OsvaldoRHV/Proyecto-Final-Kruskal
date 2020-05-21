
package algoritmo;

import java.util.ArrayList;
import util.FuncionesBasicas;

public class Kruskal {
    public int contador = 0;
    int n;
    int menorCostoArista = 0;
    int ciclos = 0;
    ArrayList<String> rutasArbol = new ArrayList<>();
    ArrayList<String> aristasEvaluadas = new ArrayList<>();
    String[][] matriz;
    FuncionesBasicas util = new FuncionesBasicas();
    
    public Kruskal(int n){
        this.n = n;
        this.matriz = new String[n][n];
    }
    
    public void empezarAlgoritmo(){
        this.solicitarCostosNodos();
        this.calculoArbolExpansionMinima();
       
    }
    
    public void solicitarCostosNodos(){
        System.out.println("Llenado de costo de aristas (Si no estan conectados colocar '-')");
        for(int i=0;i<n;i++){
           for(int j=0;j<n;j++){
               int aux1=i+1;
               int aux2=j+1;
               if(i!=j && matriz[i][j]==null) {
                   String var = util.solicitarStringConsola("Valor arista que conecta al nodo "+aux1+" con nodo "+aux2+": ");
                   matriz[i][j] = var; 
                   matriz[j][i] = var;
               }
               if(i==j){
                   matriz[i][j] = "-";
               }
               
           }
       }
       
    }
    
    public int numeroMasChico(ArrayList<String> listaNumeros){
        int smallest = Integer.parseInt(listaNumeros.get(0));
        for(String n:listaNumeros){
            if(smallest>Integer.parseInt(n)) smallest = Integer.parseInt(n);
        }
        return smallest;
    }
    
    public void calculoArbolExpansionMinima(){
        ArrayList<String> costos = new ArrayList<>();
        
        for(int i=0;i<n;i++){
           for(int j=0;j<n;j++){
               if(!matriz[i][j].equals("-")){
                   int costoAristaActual = Integer.parseInt(matriz[i][j]);
                   if(costoAristaActual>menorCostoArista && !costos.contains(""+costoAristaActual)) costos.add(""+costoAristaActual);
               }       
           }
        }
        
        do{
        
        System.out.println("\nLista de Costos de las aristas que aun no han sido evaluadas:");
        for(String n:costos){
            System.out.print(" "+n);
        }
        
        System.out.println("\nMenor costo de las aristas a evaluar");
        menorCostoArista = this.numeroMasChico(costos);
        System.out.println(menorCostoArista);
        
        for(int i=0;i<n;i++){
           for(int j=0;j<n;j++){
               int aux1=i+1;
               int aux2=j+1;
               if(!matriz[i][j].equals("-")){
                   int costoActual = Integer.parseInt(matriz[i][j]);
                   if(rutasArbol.isEmpty()){
                       if(costoActual== menorCostoArista) {
                           rutasArbol.add(""+aux1+"-"+aux2);
                           rutasArbol.add(""+aux2+"-"+aux1);
                       }
                   }
                   else{
                       if(costoActual==menorCostoArista){
                           
                            evitarCiclosGeneral(""+aux1, ""+aux2);
                            if(ciclos==0) {
                                rutasArbol.add(""+aux1+"-"+aux2);
                                rutasArbol.add(""+aux2+"-"+aux1);
                            }
                            ciclos = 0;
                       }
                   }
               }       
           }
        }
        
        costos.remove(""+menorCostoArista);
            if(rutasArbol.size()==n*2-2){
                System.out.println("\nEl arbol de expansion minima es: ");
        calculaCostoArbol();
                return;
            }
        }while(costos.size()>0);
        
    }
    
    public void calculaCostoArbol(){
        contador = 0;
        ArrayList<String> arbolReducido= new ArrayList<>();
            rutasArbol.stream().forEach((n) -> {
            String[] aux = n.split("-");
            if(!arbolReducido.contains(n) && !arbolReducido.contains(""+aux[1]+"-"+aux[0])){
                arbolReducido.add(n);
                System.out.print("("+n+")");
            }    
            });
            
            for(String s: arbolReducido){
                String[] aux = s.split("-");
                contador = contador + Integer.parseInt(matriz[Integer.parseInt(aux[0])-1][Integer.parseInt(aux[1])-1]);
            }
            
            System.out.println("\nCon un costo total de: "+contador);
    }
    
    public void evitarCiclosGeneral(String nodo1,String nodo2){
        
            if(rutasArbol.contains(""+nodo1+"-"+nodo2)){
                this.ciclos++;
                return;
            }
        
        System.out.println("Validando arista: "+nodo1+"-"+nodo2);
        
        aristasEvaluadas.add(""+nodo1+"-"+nodo2);
        for(int i=0;i<rutasArbol.size();i++){
            String nodo = rutasArbol.get(i);
            String[] numeros = nodo.split("-");
            if(numeros[0].equals(nodo1)&&numeros[1].equals(nodo2)) {
                System.out.println("Ciclo detectado: "+numeros[0]+"-"+numeros[1]);
                this.ciclos++;
                return;
            }
            if(numeros[0].equals(nodo1)&&!numeros[1].equals(nodo2)){
                if(!(aristasEvaluadas.contains(""+numeros[1]+"-"+nodo2))){
                    
                    evitarCiclosGeneral(numeros[1], nodo2);
                }
                
            }
        }  
        return;
    }
    
    public void evitarCiclos(String nodo1,String nodo2){
        int bucle;
        for(int i=0;i<rutasArbol.size();i++){
            String nodo = rutasArbol.get(i);
            bucle=0;
            String[] numeros = nodo.split("-");
            if(numeros[0].equals(nodo1)){
                String validar = ""+numeros[1]+"-"+nodo2;
                if(rutasArbol.contains(validar)) System.out.println("No se puede utilizar la arista porque forma un bucle");
                bucle++;
            }
            if(numeros[1].equals(nodo1)){
                String validar = ""+numeros[0]+"-"+nodo2;
                if(rutasArbol.contains(validar)) System.out.println("No se puede utilizar la arista porque forma un bucle");
                bucle++;
            }
            String agregar = ""+nodo1+"-"+nodo2;
            if(bucle==0&&!(rutasArbol.contains(agregar))){
                rutasArbol.add(""+nodo1+"-"+nodo2);
            }
        }
    }
}
