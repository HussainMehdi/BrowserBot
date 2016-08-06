/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package browser_bot;

/**
 *
 * @author Madan Lal
 */
public class coordinates {

    public int start_x,start_y,end_x,end_y;

    public coordinates(int _start_x,int _start_y,int _end_x,int _end_y) 
    {
        start_x =   _start_x;
        start_y =   _start_y;
        end_x   =   _end_x;
        end_y   =   _end_y;
    }

    public static String Serialize(coordinates c)
    {
        return String.valueOf(c.start_x)+","+String.valueOf(c.start_y)+
                ","+String.valueOf(c.end_x)+","+String.valueOf(c.end_y);
    }
    
    public static coordinates DeSerialize(String s)
    {
        String l[] = s.split(",");
        return new coordinates(
                                Integer.parseInt(l[0]),
                                 Integer.parseInt(l[1]),
                                    Integer.parseInt(l[2]),
                                        Integer.parseInt(l[3])
                                );
    }

    
}
