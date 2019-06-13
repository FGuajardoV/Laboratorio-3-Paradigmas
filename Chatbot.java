/*
 * Laboratorio 3 Paradigmas de Programacion
 * Profesor Catedra: Daniel Gacitua
 * Alumno: Francisco Guajardo Villa / 19.005.801-8
 */

package chatbot;

import java.util.concurrent.ThreadLocalRandom;

/**
 * Clase Chatbot que contiene la IA chatbot
 * @author Francisco Guajardo Villa 
 */
public class Chatbot 
{
    String saludo;
    String despedida;
    String postNombre;
    
    String respuesta1;
    String respuesta2;
    String respuesta3;
    String respuesta4; 
    String respuestasVarias;
    
    /**
     * Metodo Constructor
     * @param seed dependiendo de la seed ingresada por el usuario, este iniciara una personalidad distinta
     */
    public Chatbot(int seed)
    {
        if (seed == 1)
        {
            saludo = "Hola, bienvenido. Cual es su nombre?";
            despedida = "Me despido, gracias";
            postNombre = "En que le puedo ayudar";            
            respuesta1 = "Que desea comprar?";
            respuesta2 = "Este producto esta a $" + String.valueOf(ThreadLocalRandom.current().nextInt(10000, 50000 + 1));
            respuesta3 = "Si tiene, lo lleva?";
            respuesta4 = "No, lo llevara";
            respuestasVarias = "No entiendo";
        }
        
        else if (seed == 2)
        {
            saludo = "Hola hola, bienvenido señor cliente, como me dirijo hacia usted?";
            despedida = "Hasta su proxima visita, muchas gracias por su patronaje";
            postNombre = "En que le puedo servir mi buen cliente?";
            respuesta1 = "Que desea adquirir hoy mi estimado?";
            respuesta2 = "Este producto lo tenemos a un precio de $" + String.valueOf(ThreadLocalRandom.current().nextInt(10000, 50000 + 1));
            respuesta3 = "Este producto si se encuentra en nuestra bodega, desea adquirirlo?";
            respuesta4 = "Me disculpo pero no posee descuento, lo lleva de igual forma?";
            respuestasVarias = "No comprendo lo que me desea decir";
        }
    }
    
    String getSaludo() { return saludo;}

    String getDespedida() { return despedida;}
    
    String getPostNombre() { return postNombre;}
    
    String getRespuesta1() { return respuesta1;}
    
    String getRespuesta2() { return respuesta2;}
    
    String getRespuesta3() { return respuesta3;}
    
    String getRespuesta4() { return respuesta4;}
    
    String getRespuestasVarias() { return respuestasVarias;} 
}
