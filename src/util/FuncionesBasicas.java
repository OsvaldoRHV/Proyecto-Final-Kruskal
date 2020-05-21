
package util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class FuncionesBasicas {
    
    private Scanner leer = new Scanner(System.in);
    
    public String solicitarStringConsola(String texto){
        System.out.println(texto);
        return leer.nextLine();
    }
    
    public int solicitarIntConsola(String texto){
        System.out.println(texto);
        return leer.nextInt();
    }
    
    public void imprimirMatriz(int n,int m,String[][] matriz){
        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                System.out.print(matriz[i][j]);
            }
            System.out.print("\n");
            }
        }
    
    public List<String> llenarListString(int n,String texto){
        leer.nextLine();
        List<String> lista = new ArrayList<>();
        for(int i=1;i<=n;i++){
            System.out.println(texto+" "+i+": ");
            lista.add(leer.nextLine());
        }
        return lista;
    }
    
    public HashMap<String,Integer> llenarMapString(String[] clave){
        leer.nextLine();
        HashMap<String,Integer> map = new HashMap<>();
        for(int i=0; i<clave.length; i++){
            System.out.println(clave+": ");
            map.put(clave[i], leer.nextInt());
        }
        return map;
    }
    
}
