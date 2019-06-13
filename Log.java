/*
 * Laboratorio 3 Paradigmas de Programacion
 * Profesor Catedra: Daniel Gacitua
 * Alumno: Francisco Guajardo Villa / 19.005.801-8
 */

package chatbot;

/**
 * Clase Log, correspondiente al objeto mensaje dentro del chatbot, realizada 
 * para una mayor facilidad a la hora de guardar sentencias, guardando para su posterior analisis: 
 * Fecha, Emisor y Mensaje. 
 * 
 * @author Francisco Guajardo Villa
 */
public class Log 
{
    String date;
    String transmitter;
    String message;
    
    /**
     * Metodo Constructor.
     * @param date fecha entregada por timeStamp.
     * @param transmitter corresponde a quien envia el mensaje, el usuario o el chatbot.
     * @param message string correspondiente a la interaccion.
     */
    public Log(String date, String transmitter, String message)
    {
        this.date          =   date;
        this.transmitter   =   transmitter;
        this.message       =   message;
    }
    /**
     * Metodo getDate(), utilizado para rescatar la fecha de emision de nuestra sentencia.
     * @return el valor que corresponde a la fecha en formato string.
     */
    public String getDate()
    {
        return date;
    }
    
    /**
     * Metodo getTransmitter(), rescata el emisor del objeto correspondiente a la sentencia.
     * @return el valor que corresponde al emisor en formato string.
     */
    public String getTransmitter()
    {
        return transmitter;
    }
    
    /**
     * Metodo getMessage(), rescata el mensaje del objeto sentencia/dialogo.
     * @return el valor que corresponde al mensaje en formato string.
     */
    public String getMessage()
    {
        return message;
    }

}
