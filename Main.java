/*
 * Laboratorio 3 Paradigmas de Programacion
 * Profesor Catedra: Daniel Gacitua
 * Alumno: Francisco Guajardo Villa / 19.005.801-8
 */
package chatbot;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

/**
 * Clase Main, clase principal que contiene la funcion main, la cual se encarga del
 * manejo de las interacciones entre el usuario y la entidad chatbot.
 * @author Francisco Guajardo Villa
 * @version 18.06.2018
 */
public class Main
{   
    /**
     * Funcion main encargada de manejar las interacciones 
     * @param args sin parametros adicionales 
     */
    public static void main(String[] args) 
    {
        ArrayList<Log> log;
        ArrayList<String> notasChatbot;
        
        String entradaTeclado;
        String timeStamp;
        String[] entrada;
        Scanner entradaScanner;
        Log dialogo;
        Chatbot chatbot = null;
        Boolean delimitador = false;
        
        System.out.println("Sistema > Hola y bienvenido, este chatbot te ayudara a registrar"
                + " una compra dentro de la tienda Xamer, especializada"
                + " en venta de videojuegos, peliculas y mangas \nPara ayuda ejecute el comando !help");
        
        log = new ArrayList<>();
        boolean auxBoolean;
        auxBoolean = true;
        
        while(auxBoolean)
        {
            notasChatbot = new ArrayList<>();
            entradaScanner = new Scanner (System.in); //Creación de un objeto Scanner
            System.out.print("User > "); //Para diferenciar la entrada del user a la del chatbot
            entradaTeclado = entradaScanner.nextLine (); //Invocamos un método sobre un objeto Scanner
            entrada = entradaTeclado.split(" ");
            timeStamp = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(new Date());

            dialogo = new Log(timeStamp, "usuario", entradaTeclado);
            log.add(dialogo);

            if (entrada[0].toLowerCase().equals("!begindialog")) 
            {
                if (delimitador)
                {
                    timeStamp = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(new Date());
                    System.out.print("Sistema > ");
                    String stringAux = "Conversacion ya iniciada, imposible de iniciar una segunda conversacion"
                            + " sin haber finalizado la conversacion actual.";
                    System.out.println(stringAux);
                    dialogo = new Log(timeStamp, "sistema", stringAux);
                    log.add(dialogo);
                }
                
                else
                {
                    String saludoChatbot;
                    delimitador = true;
                    timeStamp = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(new Date());
                    //se realiza un tryCatch para prevenir el error si el usuario no ingresa la seed del chatbot
                    try {
                        int seed = Integer.parseInt(entrada[1]);
                        chatbot = new Chatbot(seed);
                        saludoChatbot = chatbot.getSaludo();
                        System.out.println("Chatbot > " + saludoChatbot);

                        dialogo = new Log(timeStamp, "chatbot", saludoChatbot);
                        log.add(dialogo);

                    } catch (ArrayIndexOutOfBoundsException e) {
                        int seed = 1;
                        chatbot = new Chatbot(seed);
                        saludoChatbot = chatbot.getSaludo();
                        System.out.println("Chatbot > " + saludoChatbot);

                        dialogo = new Log(timeStamp, "chatbot", saludoChatbot);
                        log.add(dialogo);
                    }
                    
                    entradaScanner = new Scanner (System.in);
                    System.out.print("User > ");
                    entradaTeclado = entradaScanner.nextLine ();
                    timeStamp = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(new Date());
                    dialogo = new Log(timeStamp, "usuario", entradaTeclado);
                    Usuario user = new Usuario(entradaTeclado);
                    log.add(dialogo);

                    timeStamp = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(new Date());
                    String aux = chatbot.getPostNombre();
                    dialogo = new Log(timeStamp, "chatbot", aux);
                    System.out.println("Chatbot > " + aux);
                    log.add(dialogo);
                  
                }                
            }

            else if (entrada[0].toLowerCase().equals("!savelog")) 
            {
                String nombreArchivo;
                timeStamp = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss").format(new Date());
                nombreArchivo = timeStamp + ".log";
                Utiles.crearArchivo(log, nombreArchivo);
            }
            
            else if (entrada[0].toLowerCase().equals("!loadlog")) 
            {
                log = Utiles.leerArchivo(entrada[1]);
                //si es que la conversacion ya existia una entidad chatbot esta se cargara en el programa.
                chatbot = Utiles.cargarChatbot(entrada[1]);
                if (chatbot != null) { delimitador = true; }
            }
            
            else if (entrada[0].toLowerCase().equals("!help")) 
            {
                timeStamp = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(new Date());
                String aux;
                System.out.print("Sistema > ");
                aux = "La lista de comandos existentes en este programa de ayuda son los siguientes:\n"
                        + "*!beginDialog seed: Permite iniciar el chatbot con un valor semilla (seed) para su personalidad.(Debe de estar entre 1 y 2)\n"
                        + "*!endDialog: Termina la conversacion con el chatbot.\n"
                        + "*!saveLog: Guarda en un archivo de texto la conversacion realizada hasta el momento.\n"
                        + "*!loadLog nombreArchivo: Lee el archivo ingresado y carga la conversacion almacenada.\n"
                        + "*!rate notaChatbot notaUsuario: Evalua el chatbot y su propio comportamiento con una nota entre 1 a 5.\n"
                        + "*!exit: termina el programa\n"
                        + "Estos son todos los comandos existentes, porfavor prosiga la conversacion.";
                System.out.println(aux);
                dialogo = new Log(timeStamp, "sistema", aux);
                log.add(dialogo);
            }
            
            else if (entrada[0].toLowerCase().equals("!exit")) 
            {
                System.out.println("Sistema > Asistente finalizado.");
                auxBoolean = false;
            }
            
            else if (entrada[0].toLowerCase().equals("!enddialog"))
            {
                if (delimitador == false)
                {
                    System.out.print("Sistema > ");
                    timeStamp = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(new Date());
                    String aux = "No se puede finalizar una conversacion que no ha sido iniciada, porfavor\ninicie una conversacion primero.";
                    System.out.println(aux);
                    dialogo = new Log(timeStamp, "sistema", aux);
                    log.add(dialogo);
                }
                
                else
                {
                    String despedidaChatbot;
                    despedidaChatbot = chatbot.getDespedida();
                    timeStamp = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(new Date());
                    System.out.print("Chatbot > ");
                    System.out.println(despedidaChatbot);
                    dialogo = new Log(timeStamp, "chatbot", despedidaChatbot);
                    log.add(dialogo);
                    delimitador = false;
                }
            }
            
            else if (entrada[0].toLowerCase().equals("!rate"))
            {
                if (delimitador == false)
                {
                    String notaChatbot = entrada[1];
                    String notaUser = entrada[2];

                    timeStamp = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(new Date());
                    String nota = notaChatbot + "," + notaUser + "," + timeStamp;
                    notasChatbot.add(nota);

                    System.out.print("Sistema > ");
                    timeStamp = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(new Date());
                    String auxiliar = "Nota ingresada.";
                    System.out.println(auxiliar);
                    dialogo = new Log(timeStamp, "sistema", auxiliar);
                    log.add(dialogo);
                }
                
                else
                {
                    System.out.print("Sistema > ");
                    timeStamp = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(new Date());
                    String aux = "No se puede evaluar una conversacion que no ha sido finalizada, porfavor\ninicie una conversacion primero.";
                    System.out.println(aux);
                    dialogo = new Log(timeStamp, "sistema", aux);
                    log.add(dialogo);
                }
                
            }
            
            else 
            {
                String [] aux;
                aux = entrada[0].split("");
                
                if ("!".equals(aux[0])) 
                {
                    System.out.print("Sistema > ");
                    timeStamp = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(new Date());
                    String auxiliar = "Comando inexistente, para ayuda escriba !help.";
                    System.out.println(auxiliar);
                    dialogo = new Log(timeStamp, "sistema", auxiliar);
                    log.add(dialogo);
                }
                
                else
                {
                    if (delimitador)
                    {
                        String claves1 = "comprar adquirir reservar";
                        String claves2 = "cuesta precio";
                        String claves3 = "existe stock";
                        String claves4 = "descuento oferta";
                        int auxiliar = 0;

                        for (String stringAux: entrada)
                        {
                            if (claves1.contains(stringAux))
                            {
                                auxiliar = 1;
                                break;
                            }
                            
                            else if(claves2.contains(stringAux))
                            {
                                auxiliar = 2;
                                break;
                            }
                            
                            else if(claves3.contains(stringAux))
                            {
                                auxiliar = 3;
                                break;
                            }
                            
                            else if(claves4.contains(stringAux))
                            {
                                auxiliar = 4;
                                break;
                            }
                        }
                        
                        if (auxiliar == 1)
                        {
                            String respuesta;
                            respuesta = chatbot.getRespuesta1();
                            timeStamp = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(new Date());
                            System.out.print("Chatbot > ");
                            System.out.println(respuesta);
                            dialogo = new Log(timeStamp, "chatbot", respuesta);
                            log.add(dialogo);
                        }
                            
                        else if(auxiliar == 2)
                        {
                            String respuesta;
                            respuesta = chatbot.getRespuesta2();
                            timeStamp = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(new Date());
                            System.out.print("Chatbot > ");
                            System.out.println(respuesta);
                            dialogo = new Log(timeStamp, "chatbot", respuesta);
                            log.add(dialogo);
                        }
                            
                        else if(auxiliar == 3)
                        {
                            String respuesta;
                            respuesta = chatbot.getRespuesta3();
                            timeStamp = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(new Date());
                            System.out.print("Chatbot > ");
                            System.out.println(respuesta);
                            dialogo = new Log(timeStamp, "chatbot", respuesta);
                            log.add(dialogo);
                        }
                            
                        else if(auxiliar == 4)
                        {
                            String respuesta;
                            respuesta = chatbot.getRespuesta4();
                            timeStamp = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(new Date());
                            System.out.print("Chatbot > ");
                            System.out.println(respuesta);
                            dialogo = new Log(timeStamp, "chatbot", respuesta);
                            log.add(dialogo);
                        }
                            
                        else
                        {
                            String respuesta;
                            respuesta = chatbot.getRespuestasVarias();
                            timeStamp = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(new Date());
                            System.out.print("Chatbot > ");
                            System.out.println(respuesta);
                            dialogo = new Log(timeStamp, "chatbot", respuesta);
                            log.add(dialogo);
                        }
                    }
                    
                    else
                    {
                        System.out.print("Sistema > ");
                        timeStamp = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(new Date());
                        String aux3 = "Por favor, para hablar con el chatbot primero inicie la conversacion";
                        System.out.println(aux3);
                        dialogo = new Log(timeStamp, "sistema", aux3);
                        log.add(dialogo);
                    }
                        
                }
            }
        }
    }
}
