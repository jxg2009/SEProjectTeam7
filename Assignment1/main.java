/*file: main.java 
 * date: 2017-11-14
 */

import java.util.*;
import java.util.regex.Pattern;

public class main {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		Queue que = new LinkedList();
		Boolean cmdSuccess = false;
		
		Vector<String> inputFileList = new Vector(); // md file list
		Vector<String> outputFileList = new Vector(); // output file list
		String visitorStyle = null; //visitor style: plain, slide, stylish
		
		while (!cmdSuccess) {
			System.out.println("Enter your command: ");
			String cmd = scan.nextLine();
			
			String[] array;
			array = cmd.split(" "); // split the cmd lines by space and inert into array
			
			for(int i = 0 ; i < array.length; i++){
				que.offer(array[i]); // insert the array element into queue
			}
			String cmd1 = (String)que.poll();
		
			if (cmd1.equals("help")) { // when cmd is "help"
				System.out.println("================================");
				System.out.println("1. help");
				System.out.println("2. quit");
				System.out.println("1. cvt");
				System.out.println("================================");
				
			} else if (cmd1.equals("quit")) {
				return;
			} else if (cmd1.equals("cvt")) {
				/* when cmd is "cvt" there are four cases
					1.cvt inputfile (space) outputfile (space) option(-pl, -st, -sl )
					2.cvt inputfile (space) outputfile
					3.cvt inputfile (space) option
					4.cvt inputfile  => default: plain
					*/
				String pattern = "^\\S+.(?i)(md)$"; // ".md"file pattern 
				String element = "";
				
				if(!que.isEmpty()) element = (String)que.poll();
				
				if(Pattern.matches(pattern, element)){
					// when md file is given
					while(Pattern.matches(pattern, element)){
						inputFileList.addElement(element);
						if(!que.isEmpty()) element = (String)que.poll();
						else break;
					}
					pattern = "^\\S+.(?i)(html)$"; // ".html" file pattern
				
					if(Pattern.matches(pattern, element)){
						// when the html format output file is given
						while(Pattern.matches(pattern, element)){
							outputFileList.addElement(element);
							element = (String)que.poll();
						}
						
						if(element.equals("-pl")){
							visitorStyle = "plain";
						} else if(element.equals("-st")){
							visitorStyle = "stylish";
						} else if(element.equals("-sl")){
							visitorStyle = "slide";
						} else {
							// when style is not given => default: plain
							visitorStyle = "plain";
						}
						cmdSuccess = true;
					} else { 
					// when the user didn't give the output file name => default naming
						for(int i = 0 ; i < inputFileList.size(); i++){
							String outputFile = inputFileList.elementAt(i).replace(".md", ".html");
							// change the inputFileList 's name. => .md to .html
							outputFileList.addElement(outputFile);
						}
						if(element.equals("-pl")){
							visitorStyle = "plain";
						} else if(element.equals("-st")){
							visitorStyle = "stylish";
						} else if(element.equals("-sl")){
							visitorStyle = "slide";
						} else {
							// when style is not given => default: plain
							visitorStyle = "plain";
						}
						cmdSuccess = true;
					}
					
				} else{
					System.out.println("Command Error: Inputfile has not be given");
				}

			} else { // When user entered not existing cmd
				System.out.println("Command Error. Try help for more information");
				System.out.println(cmd1);
			}

		}
		
		// testing
		System.out.println("md fileList");
		for(int i = 0; i < inputFileList.size(); i++){
			System.out.print(inputFileList.elementAt(i) + " ");
		}
		
		System.out.println("\nhtml fileList");
		for(int i = 0; i < outputFileList.size(); i++){
			System.out.print(outputFileList.elementAt(i) + " ");
		}
		
		System.out.println("\nstyle: " + visitorStyle);
		

	}

	}

