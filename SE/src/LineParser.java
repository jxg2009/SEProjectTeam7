import java.util.Vector;
import java.util.regex.Pattern;

public class LineParser {
	public Vector<String> lineVector = new Vector<String>();
	public LineParser(){
		
	}
//	public void test(Vector<String> src){
//		int index = 0;
//		String element;
//		String list;
//		String result;
//		while(true){
//			if(src.isEmpty()) break;
//			element = src.elementAt(index);
//			if(Pattern.matches("[\\*|\\+|\\-]{1}.*$", element)){
//				boolean entered = false; 
//				result = "<ul>";
//				list = element.substring(1); // removing *, +, - in the front to gain list content
//				while(!src.elementAt(++index).isEmpty()){
//					entered = true;
//					element = src.elementAt(index);
//					if(Pattern.matches("[\\*|\\+|\\-]{1}.*", element)){
//						/*   * String
//						 *   * String
//						 *	 * String
//						 *   형태 
//						 */
//						result += "<li>" + list +"</li>\n";
//						list = element.substring(1);
//					}
//					else if(Pattern.matches("^\\s+[\\*|\\+|\\-]{1}.*$", element)){
//						/*	*String
//						 *   	- String
//						 *  *String
//						 *  	- String
//						 *  형태
//						 */
//						result += "<ul><li>" + list;
//						list = 
//						while()
//					} 
//					
//					
//					else{ // It is in the category of same list before
//						list += element;
//					}
//				}
//				if(entered) lineVector.addElement("</ul>");
//				else{
//					list = "<li>"+list+"</li></ul>";
//					
//				}
//			}
//			//if(Pattern.matches("*, input))
//		}
//		
//		
//	}
	
	public Vector<String> parseLine(Vector<String> src){
				System.out.println(src.size());
		int index = 0;
		String element;
		while(true){
			element = src.elementAt(index);
			
			if(Pattern.matches("^\\#{2}.*\\#{2,}$", element)){
				//## String ## => <h2> String <h2>
				element = element.replaceFirst("##", "<em>");
				element = element.replaceFirst("#{2,}", "</em>");
				element = element + "<br>";
				lineVector.addElement(element);
				index++;
			} 
			else if(Pattern.matches("\\=+", element)){
				// =======  => <h1></h1>
				String temp = src.elementAt( index - 1); // ==== 이전의 내용
				temp = "<h1>" + temp + "</h1><br>";
				lineVector.remove(lineVector.size() - 1); // ===== 이전에 있던 내용 지우기
				lineVector.addElement(temp); // <h1>String</h1><br> add
				index++;
			}
			else if(Pattern.matches("\\-+", element)){
				// ------  => <h2></h2>
				String temp = src.elementAt( index - 1); // ==== 이전의 내용
				temp = "<h2>" + temp + "</h2><br>";
				lineVector.remove(lineVector.size() - 1); // ------ 이전에 있던 내용 지우기
				lineVector.addElement(temp); // <h2>String</h2><br> add
				index++;
			}
			else if(element.equals("- - -")){
				// - - - => <hr>
				lineVector.addElement("<hr>");
				index++;
			}
			else if(Pattern.matches("^>.*$", element)){
				// > String => <blockquote> String <blockquote>
				element = element.replaceFirst(">", "<blockquote>");
				element = element + "</blockquote>";
				lineVector.addElement(element);
				index++;
			} 
			
			else{
				lineVector.addElement(element);
				index++;
				if(index == src.size()) break;
			}

		}
					System.out.println("out");
		return lineVector;
		
	}
}
