// @author: Busra zenbilci

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;

public class Main {
    public static void main (String[] args) {
        if (args.length != 1) {
            System.err.println("Usage: java Main <filename>");
            System.exit(1);
        }

        String filename = args[0];
        CPU cpu = new CPU();
        cpu.readFile(filename);
        cpu.Run();
    }
}

class CPU {

	private int AC = 0;
	private int[] M = new int[256];
	private int F = 0;
	private int PC = 0;
	private Boolean halt = false;
	
	private ArrayList<String> program = new ArrayList<String>();
	
	Scanner scan = new Scanner(System.in);
	
	public a120170808054(){
		
		for (int i = 0; i < M.length; i++) {
			M[i] = 0;
		}	
	}
	
	
	public void Run(){
		
		while(!halt){
			processCommand(program.get(PC));
		}
	}
	
	public void processCommand(String line){
        String[] command = line.split(" ");
        
        if(command.length == 3){
            if(command[1].equals("LOAD")){
                AC = Integer.parseInt(command[2]);
                PC += 1; 
            }
            else if(command[1].equals("LOADM")){
                AC = M[Integer.parseInt(command[2])];
                PC += 1; 
            }
            else if(command[1].equals("STORE")){
                M[Integer.parseInt(command[2])] = AC;
                PC += 1; 
            }
            else if(command[1].equals("CMPM")){
                if(AC > M[Integer.parseInt(command[2])])
                    F = 1;
                else if(AC < M[Integer.parseInt(command[2])])
                    F = -1;
                else
                    F = 0;
                
                PC += 1;
            }
            else if(command[1].equals("CJMP")){
                if(F > 0 )
                    PC = Integer.parseInt(command[2]);
                else
                    PC += 1;
            }
            else if(command[1].equals("JMP")){
                PC = Integer.parseInt(command[2]);
            }
            else if(command[1].equals("ADD")){
                AC += Integer.parseInt(command[2]);
                PC += 1; 
            }
            else if(command[1].equals("ADDM")){
                AC += M[Integer.parseInt(command[2])];
                PC += 1; 
            }
            else if(command[1].equals("SUBM")){
                AC -= M[Integer.parseInt(command[2])];
                PC += 1; 
            }
            else if(command[1].equals("SUB")){
                AC -= Integer.parseInt(command[2]);
                PC += 1; 
            }
            else if(command[1].equals("MUL")){
                AC *= Integer.parseInt(command[2]);
                PC += 1; 
            }
            else if(command[1].equals("MULM")){
                AC *= M[Integer.parseInt(command[2])];
                PC += 1; 
            }
            else {
                System.out.println("There is no command like " + command[1]);
            }
        }
        else if(command.length == 2){
            if(command[1].equals("START")){
                PC += 1; 
            }
            else if(command[1].equals("HALT")){
                halt = true;
            }
            else if(command[1].equals("DISP")){
                System.out.println(AC);
                PC += 1; 
            }
        }
    }
    
	
	public void readFile(String filename){
		
		try {
		      File file = new File(filename);
		      Scanner reader = new Scanner(file);
		      
		      while (reader.hasNextLine()) {
		        String data = reader.nextLine();
		        program.add(data);
		      }
		      
		      reader.close();
		      
		    } catch (FileNotFoundException e) {
		      System.err.println(filename + " file Not Found!!");
		      
		    }
	}


    
}


