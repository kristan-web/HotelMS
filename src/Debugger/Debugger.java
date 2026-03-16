package Debugger;
public class Debugger {
    private static boolean DEBUG_MODE = false;
    
    public static void Debugger(String message){
        if(DEBUG_MODE){
            System.out.println("[STRING]: " + message);
        }
    }
    
    public static void Debugger(int message){
        if(DEBUG_MODE){
            System.out.println("[INT]: " + message);
        }
    }
    
    public static void Debugger(boolean message){
        if(DEBUG_MODE){
            System.out.println("[BOOLEAN]: " + message);
        }
    }
}
