/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ModelSemantico;

import ModelLexico.AnalisadorLexico;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

/**
 *
 * @author felipe
 */
public class AnalisadorSemantico {

    static int numberLines = 1;
    int lastNumber = 0;
    
    public AnalisadorSemantico() {
        File archive[];
        File directory = new File ("saida/");
        archive = directory.listFiles();
        for(int i = 0; i < archive.length; i++){
           // System.out.println(archive[i].getName());
             HashMap<Integer, String> hashLine = readFiles(archive[i].getName());
             parser(hashLine);
        }
        
       
    }

    public static void main(String[] args) throws FileNotFoundException, IOException{
        AnalisadorLexico analisadorLexico = new AnalisadorLexico();
        analisadorLexico.analisador();
        AnalisadorSemantico semantico = new AnalisadorSemantico();
       
    }
    
    public HashMap<Integer, String> readFiles(String nameArchive){
        HashMap<Integer, String> hashLine = new  HashMap<Integer, String>();
        try {      
                FileReader fileRead = new FileReader("saida/" + nameArchive);
                BufferedReader file = new BufferedReader(fileRead);
              
                String line;
                line = file.readLine();
                while (line != null) {
                    //System.out.println(line);
                    String number = line.split("-")[0].replace(" ", "");
                    numberLines = Integer.parseInt(number);
                    hashLine.put(numberLines, line);
                    line = file.readLine(); // le da segunda linha ate a ultima linha
                    
                }
                fileRead.close();
        } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
        }
            return hashLine;
    }
    
    public void parser(  HashMap<Integer, String> hashLine){
        for(int i = 1; i <= numberLines; i++){
            if(hashLine.containsKey(i)){
               
                System.out.println("linha: " + i + "  dado linha " + hashLine.get(i));
               
            }
        }
    }
    
    public void inserirVariavel(){
        
    }
    
    public void inserirMetodo(){
        
    }
    
    public boolean buscaVariavel(){
        
        return false;
    }
    
    public boolean buscaMetodo(){
        
        return false;
    }
    
    
    
    
}
