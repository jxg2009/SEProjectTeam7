/*file: main.java 
 * date: 2017-11-14
 */

import java.util.*;
import java.util.regex.Pattern;


public class Markdown {
	
	public static void main(String[] args) {		
		Scanner scan = new Scanner(System.in);
		Queue que = new LinkedList();
		Boolean cmdSuccess = false;
		
		Vector<String> inputFileList = new Vector(); // md file list
		Vector<String> outputFileList = new Vector(); // output file list
		String visitorStyle = null; //visitor style: plain, slide, stylish
		Vector<String> stringVector; // 줄단위로 파싱한 벡터
		
		while (!cmdSuccess) {
			System.out.println("Enter your command(Enter 'help' for command help): ");
			String cmd = scan.nextLine();
			
			String[] array;
			array = cmd.split(" "); // split the cmd lines by space and insert into array
			
			for(int i = 0 ; i < array.length; i++){
				que.offer(array[i]); // insert the array element into queue
			}
			String cmd1 = (String)que.poll();
		
			if (cmd1.equals("help")) { // when cmd is "help"
				System.out.println("================================================================================================");
				System.out.println("1. help: Showing information about commands");
				System.out.println("2. quit: Terminating the program");
				System.out.println("3. cvt: cvt 'input file' (space) ouputfile (space) option(-pl or -st or -sl) \n\n"
						+ "\t*Changing the md input files into ouptput html files and save them with ouputfile name. \n"
						+ "\t*Default ouputfile name: change only the format .md to .html from the inputfile name\n"
						+ "\t*Input space between the command elements\n"
						+ "\t*The number of input file can be one or more. \n"
						+ "\t*Option: -pl(plain) -st(stylish) -sl(slide) // Default: -pl(plain)\n");
				System.out.println("=================================================================================================");
				
			} else if (cmd1.equals("quit")) {
				return;
			} else if (cmd1.equals("cvt")) {
				/* when cmd is "cvt" there are four cases
					1.cvt inputfile (space) outputfile (space) option(-pl, -st, -sl )
					2.cvt inputfile (space) outputfile
					3.cvt inputfile (space) option
					4.cvt inputfile  => default: plain
					*/

				// case 1 and 2 has problem

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
							if(!que.isEmpty()) element = (String)que.poll();
							else break;
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
		//^\\*{2}\\S+\\*{2}$
//		if(Pattern.matches("[\\*|\\+|\\-]{1}[^\\-].*", "-hello")){
//			System.out.println("true");
//		} else{
//			System.out.println("false");}
//		String s = ">adhfkjd;f;f adffda";
//		s = s.replaceFirst(">", "<blockquoate>");
//		s = s + "</blockquoate>";
//		System.out.println(s);

		String test = "**MarkDown**";
		test = test.replaceFirst("\\*{2}", "<em>");
		test = test.replaceFirst("\\*{2}", "</em>");
		System.out.println("testx: " + test);
		
		FileRead fileRead = new FileRead("doc2.md"); // doc1.md를 바꾼다고 가정
		stringVector = fileRead.parseWithLine(); // separate by line
		
		LineParser lineParser = new LineParser(); 
		stringVector = lineParser.parseLine(stringVector); // process with lines to make html element. 라인 단위 처리
		 
		MdParser mdParser = new MdParser();
		stringVector = mdParser.parseWhiteSpace(stringVector); // 라인단위로 처리된 벡터를 단어 단위로 쪼개서 Sring vector에 저장
		stringVector = mdParser.getMdString(stringVector); // 단어 단위로 html 문법 처리
		
		for(int i = 0 ; i < stringVector.size();i++ ){
			System.out.println(stringVector.elementAt(i));
		}
		
		
		
	}

	}

