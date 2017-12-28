/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ModelSemantico;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Stack;

/**
 *
 * @author felipe
 */
public class AnalisadorSemantico {

    static int numberLines = 1;
    int lastNumber = 0;
    HashMap<Integer, ArrayList<String>> tokenMap;
    HashMap<Integer, ArrayList<String>> dataLineMap;
    
    ArrayList<String> lineArchive;
    ArrayList<String> words;
    ArrayList<String> tokenWords;
    ArrayList<String> errors;
    ArrayList<Variavel> variaveisGlobais;
    ArrayList<Variavel> variaveisLocal;
    ArrayList<Metodos> metodos;
    Stack  pilha;
    public AnalisadorSemantico() {
        
       
    }

    public void start(String nameArchive){
        tokenMap = new HashMap<Integer, ArrayList<String>>();
        dataLineMap = new HashMap<Integer, ArrayList<String>>();
        pilha = new Stack();
        
        readFiles(nameArchive);
        parser();
    }
    
    public void readFiles(String nameArchive){
        HashMap<Integer, String> hashLine = new  HashMap<Integer, String>();
        try {      
                FileReader fileRead = new FileReader("saida/" + nameArchive);
                BufferedReader file = new BufferedReader(fileRead);
                String line;
                String token;
                String[] lineSplits;
                line = file.readLine();
                while (line != null) {

                    if(!line.contains("ERRO") && !line.contains("MAL")){
                        token = line.split("-")[2];
                        String numberLine = line.split("-")[0];
                        numberLine = numberLine.replaceAll(" ", "");
                        int number = Integer.parseInt(numberLine);    
                        line = line.split("-")[1];
                        lineSplits = line.split("-");
                        line = lineSplits[0].replaceAll(" ", "");
                        //token = lineSplits[1].replaceAll(" ", "");

                        

                        if(tokenMap.containsKey(number)){
                                tokenMap.get(number).add(line);
                                dataLineMap.get(number).add(token);
                        }else{
                                //System.out.println("  aa" + number);
                                words = new ArrayList<String>();
                                words.add(line);
                                tokenMap.put(number, words);
                                lastNumber = number;

                                tokenWords = new ArrayList<String>();
                                tokenWords.add(token);
                                dataLineMap.put(number, tokenWords);
                        }
                        //System.out.println("SINTATICO " + line + " linha " + number );
                        tokenMap.get(tokenMap.size() -1 + "");
                    }
                    line = file.readLine(); // le da segunda linha ate a ultima linha
                    
                }
                fileRead.close();
        } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
        }
           
    }
    
    public void parser(){
        System.out.println("FOI" + " " + lastNumber);
        
        for(int i = 1; i <= lastNumber; i++){
          
            if(dataLineMap.containsKey(i)){
                System.out.println("linha: " + i + "  dado linha " + dataLineMap.get(i));
                for(int j = 0; j < tokenMap.get(i).size(); j++){
                    if(tokenMap.get(i).get(j).contains("DELIMITADOR")){
                        pilha.add(dataLineMap.get(i).get(j));
                    }else if(tokenMap.get(i).get(j).contains("RESERVADA")){
                        switch(dataLineMap.get(i).get(j)){
                            case "class": break;
                            case "final": break;
                            case "if": break;
                            case "else": break;
                            case "for": break;
                            case "scan": break;
                            case "print": break;
                            case "float": break;
                            case "bool": break;
                            case "main": break;
                            case "true": break;
                            case "false": break;
                            case "string": break;
                                                
                                                
                        }
                    }
                }
            }
        }
    }
    
    public void printLine(){
		
        for(int i = 1; i <= lastNumber; i++){
            if(tokenMap.containsKey(i)){

            System.out.println(">>>>>>>>>>>> " + tokenMap.get(i));
                for(int j = 0; j < tokenMap.get(i).size(); j++){
                        System.out.println("linha: " + i + "  token " + tokenMap.get(i).get(j) +" dado " + dataLineMap.get(i).get(j));		
//					System.out.println("linha: " + i  + " " + tokenMap.get(i).get(j));
                        //System.out.println("===============" + lineMap.get(i).get(j).trim());

                }
            }
        }
    }
    
    public void novaClasse(boolean privado, String nome, String tipo){
        variaveisGlobais = new ArrayList<>();
        metodos = new ArrayList<>();
        Classe classe = new Classe(privado, nome, tipo, metodos, variaveisLocal);
    }

    public void inserirVariavelGlobal(boolean isPravado,String tipo, String nome){
        Variavel variavel = new Variavel(tipo, nome, isPravado);
        variaveisGlobais.add(variavel);
    }
    
    public void inserirVariavelMetodo(boolean isPravado,String tipo, String nome){
        Variavel variavel = new Variavel(tipo, nome, isPravado);
        variaveisGlobais.add(variavel);
    }
    public void inserirMetodo(boolean privado, String nome, String tipo, ArrayList parametrosNome){
        variaveisLocal = new ArrayList<>();
        Metodos metodo = new Metodos(privado, nome, tipo, parametrosNome, parametrosNome, variaveisLocal);
        metodos.add(metodo);
    }
    
    public boolean buscaVariavel(){
        
        return false;
    }
    
    public boolean buscaMetodo(){
        
        return false;
    }
    
    
    
    
}
