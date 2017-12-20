import java.util.Vector;
import java.util.regex.Pattern;

public class LineParser {
	public Vector<String> lineVector;
	public LineParser(){
		
	}
	public void test(Vector<String> src){
		int index = 0;
		String element;
		String list;
		while(true){
			if(src.isEmpty()) break;
			element = src.elementAt(index);
			if(Pattern.matches("[\\*|\\+|\\-]{1}.*$", element)){
				lineVector.addElement("<ul>");
				list = element.substring(1); // removing *, +, - in the front to gain list content
				while(!src.elementAt(++index).isEmpty()){
					element = src.elementAt(index);
					if(Pattern.matches("[\\*|\\+|\\-]{1}.*", element)){
						/*   * String
						 *   * String
						 *	 * String
						 *   형태 
						 */
						lineVector.addElement("<li>" + list + "</li>");
						list = element.substring(1);
					}
					else if(Pattern.matches("^\\s+[\\*|\\+|\\-]{1}.*$", element)){
						
					}
				}
				lineVector.addElement("</ul>");
			}
			//if(Pattern.matches("*, input))
		}
		
		
	}
	
	public Vector<String> parseLine(Vector<String> src){
		int index = 0;
		String element;
		while(true){
			if(src.isEmpty()) break;
			element = src.elementAt(index);
			
			if(Pattern.matches("^\\#{2}.*\\#{2,}$", element)){
				//## String ## => <h2> String <h2>
				element = element.replaceFirst("##", "<em>");
				element = element.replaceFirst("#{2,}", "</em>");
				element = element + "<br>";
				lineVector.addElement(element);
			} 
			else if(Pattern.matches("\\=+", element)){
				// =======  => <h1></h1>
				String temp = lineVector.elementAt( index - 1); // ==== 이전의 내용
				temp = "<h1>" + temp + "</h1><br>";
				lineVector.remove(index - 1); // ===== 이전에 있던 내용 지우기
				lineVector.addElement(temp); // <h1>String</h1><br> add
			}
			else if(Pattern.matches("\\-+", element)){
				// ------  => <h2></h2>
				String temp = lineVector.elementAt( index - 1); // ==== 이전의 내용
				temp = "<h2>" + temp + "</h2><br>";
				lineVector.remove(index - 1); // ------ 이전에 있던 내용 지우기
				lineVector.addElement(temp); // <h2>String</h2><br> add
			}
			else if(element.equals("- - -")){
				// - - - => <hr>
				lineVector.addElement("<hr>");
			}
			else if(Pattern.matches("^>.*$", element)){
				// > String => <blockquote> String <blockquote>
				element = element.replaceFirst(">", "<blockquote>");
				element = element + "</blockquote>";
				lineVector.addElement(element);
			}

		}
		
		return src;
		
	}
}
