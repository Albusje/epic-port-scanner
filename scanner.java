package my_package_name;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

public class scanner {
    //These values can limit the search length
    final static int MIN_PORT = ;   // Give the lowest wanted value to limit search
    final static int MAX_PORT = ;   // Give the highest wanted value to limit search
    
    public static void main(String[] args) throws Exception {
        Scanner reader = new Scanner(System.in);

        System.out.println("Service address to be scanned?");
        String address = reader.nextLine();
        
        System.out.println("Select ports starting point:");
        int start = Integer.parseInt(reader.nextLine());
        
        System.out.println("End to which port?");
        int end = Integer.parseInt(reader.nextLine());

        Set<Integer> ports = getAccessiblePorts(address, start, end);
        System.out.println("");

        if (ports.isEmpty()) {
            System.out.println("Couldn't find any ports, sorry.");       
        } else {
            // Prints out the found scanned ports, one by one slowly
            System.out.println("Ports found:");
            ports.stream().forEach(p -> 
                    System.out.println("\t" + p));
        }
        
    }
    
    public static Set<Integer> getAccessiblePorts(String address, int start, int end) {
        Set<Integer> portSet = new TreeSet<>();
        start = Math.max(start, MIN_PORT);
        end = Math.min(end, MAX_PORT);
        
        // 
        
        Socket socket;
        for(int i=start; i<end+1; i++){
            System.out.println(i);
            try{
               socket = new Socket(address, i);
               
               Scanner reader = new Scanner(socket.getInputStream());
               while(reader.hasNextLine()){
                    System.out.println(reader.nextLine());   
               }
               
               portSet.add(i);
               socket.close();
               
            }catch(Exception e){
            } 
            
        }
        
        return portSet;
    }
}
