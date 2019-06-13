/*
 * Laboratorio 3 Paradigmas de Programacion
 * Profesor Catedra: Daniel Gacitua
 * Alumno: Francisco Guajardo Villa / 19.005.801-8
 */

package chatbot;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

/**
 * Clase Utiles, la cual posee metodos static para su uso libre en la clase Main
 * @author franciscog
 */

public class Utiles 
{
    /**
     * Metodo que permite crear el archivo que almacenara la conversacion, este se almacena leyendo 
     * el arreglo de la conversacion y escribiendo en una linea de un archivo de texto plano creado
     * en el momento
     * @param log Recibe el arreglo que posee la conversacion almacenada
     * @param nombreArchivo Recibe el nombre del archivo que almacenara la conversacion, correspondiente a la fecha y hora de esta
     */
    public static void crearArchivo(ArrayList<Log> log, String nombreArchivo)
    {
        FileWriter fichero = null;
        PrintWriter pw;
        
        //El tryCatch es para prevenir algun error al momento de crear el archivo
        try {
            fichero = new FileWriter(nombreArchivo);
            pw = new PrintWriter(fichero);
            
            for(int i = 0; i < log.size(); i++)
            {
                Log dialogo = log.get(i);
                
                String fecha = dialogo.getDate();
                String user = dialogo.getTransmitter();
                String msg = dialogo.getMessage();
                
                pw.println(fecha + " , " + user + " > " + msg);
            }

        } catch (Exception e) {
            System.out.println("Error al crear archivo de salida");
            
        } finally {
            try {
            // para asegurarnos que se cierra el fichero.
                if (null != fichero)
                    fichero.close();
            } catch (Exception e2) {
                System.out.println("Error al cerrar fichero");
            }
        }  
    }
    
    /**
     * Metodo que permite, a partir de un archivo, cargar la conversacion almacenada en el y 
     * la entidad chatbot presente.
     * @param inFile String de entrada que posee el archivo a analizar.
     * @return Nos devolvera el ArrayList con la conversacion cargada en el.
     */
    public static ArrayList<Log> leerArchivo(String inFile)
    {
        ArrayList<Log> nuevoLog;
        nuevoLog = new ArrayList<>();
        File file = new File(inFile);

        FileReader fileR;
        BufferedReader file2 = null;

        //el tryCatch utilizado es para verificar que existe el archivo en el directorio del programa
        try {
            fileR = new FileReader(file);
            file2 = new BufferedReader(fileR);

        } catch (FileNotFoundException e) {
            System.out.println("No se encontro el archivo "+file.getName());
        }
        
        //este segmento es para prevenir el error NullPointerException
        try {
            String dialogo;
            while((dialogo = file2.readLine()) != null)
                {
                    String[] aux;
                    aux = dialogo.split(" , ");
                    String date = aux[0];
                    String transmitter = aux[1].split(" > ")[0];
                    String message = aux[1].split(" > ")[1];
                    Log auxDialog = new Log(date, transmitter, message);
                    nuevoLog.add(auxDialog);
                }
        } catch (IOException e) {
            System.out.println("error");
        } 
        return nuevoLog;
    }
    
    /**
     * Metodo que nos permitira rescatar la entidad chatbot de un archivo de texto plano utilizando como
     * verificador el comando !beginDialog, ya que este nos dara la semilla con la cual iniciaremos
     * nuestra entidad.
     * @param file corresponde al String que posee el nombre del archivo a recuperar.
     * @return Como return tendra el objeto Chatbot inicializado, o si es el caso, con valor null.
     */
    public static Chatbot cargarChatbot(String file) 
    {
        //iniciamos un chatbot con valor null para abarcar la situacion que en la conversacion no haya dialogo iniciado
        Chatbot chatbot = null;     
        
        //el try es para prevenir el error al encontrar sin seed la funcion !beginDialog dentro del archivo
        try{
            ArrayList<Log> log;
            String[] aux;
            String[] aux3;
            ArrayList<String[]> aux2 = new ArrayList<>();
            log = leerArchivo(file);
            for (Log temp : log)
            {
                aux = temp.getMessage().split(" ");
                if ("!begindialog".equals(aux[0].toLowerCase()))
                { 
                    aux2.add(aux); 
                }
            }

            aux3 = aux2.get(aux2.size() - 1);
            int seed = Integer.parseInt(aux3[1]);
            chatbot = new Chatbot(seed);
            return chatbot;
        }
        catch (ArrayIndexOutOfBoundsException e) 
        {
            int seed = 1;
            chatbot = new Chatbot(seed);
            return chatbot;
        }
    }
}