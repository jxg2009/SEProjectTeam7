import java.util.Vector;
import java.util.regex.Pattern;

public class LineParser {
	public Vector<String> lineVector = new Vector<String>();
	public LineParser(){
		
	}
	
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
				String temp = src.elementAt( index - 1); // ==== ������ ����
				temp = "<h1>" + temp + "</h1><br>";
				lineVector.remove(lineVector.size() - 1); // ===== ������ �ִ� ���� �����
				lineVector.addElement(temp); // <h1>String</h1><br> add
				index++;
			}
			else if(Pattern.matches("\\-+", element)){
				// ------  => <h2></h2>
				String temp = src.elementAt( index - 1); // ==== ������ ����
				temp = "<h2>" + temp + "</h2><br>";
				lineVector.remove(lineVector.size() - 1); // ------ ������ �ִ� ���� �����
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
//			else if(Pattern.matches("[\\*|\\+|\\-]{1}.*$", element)) {
//				// list ���� ��Ʈ <ul><li></li><ul>
//				Vector<String> firstLevelVector = new Vector<String>(); // �ٱ��� ����Ʈ
//				Vector<String> secondLevelVector = new Vector<String>(); // nested list
//				
//				firstLevelVector.add(element);
//				index++;
//				element = src.elementAt(index); // ��ĭ ������ ����
//				
//				while()
//				
//				
//			}
			
			
			
//			else if(Pattern.matches("[\\*|\\+|\\-]{1}.*$", element)){
//				// list���� ��Ʈ<ul><li></li></ul>
//
//				boolean entered = false; 
//				boolean enteredList = false;
//				String list = element.substring(1); // removing *, +, - in the front to gain list content
//				String result = "<ul><li>" + list;
//				index++;
//	System.out.println("check0");		
//	System.out.println(result);
//				while(!src.elementAt(index).isEmpty()){
//					entered = true;
//					element = src.elementAt(index); // new line ���� ��
//					if(Pattern.matches("[\\*|\\+|\\-]{1}[^\\-].*", element)){
//						/*   * String(1)
//						 *   * String(2) <= ���� ��Ȳ
//						 *	 * String(3)
//						 *   ���� 
//						 */
//	System.out.println("check1");		
//	System.out.println(result);
//						result += "<li>" + list +"</li>\n"; // String(1)�� list�� ó��
//						list = element.substring(1); // string(2)
//						index++;
//					}
//					else if(Pattern.matches("^\\s+[\\*|\\+|\\-]{1}.*$", element)){
//						/*	*String(1)
//						 *   	- String(2) <= ���� ��Ȳ
//						 *  *String(3)
//						 *  	- String(4)
//						 *  ����
//						 */
//	System.out.println("check2 result: " + result);		
//	System.out.println(result);
//						result += "<ul><li>" + list; // string(1)
//						list = element.substring(1); // string(2)
//						index++;
//						element = src.elementAt(index);
//						
//						while(Pattern.matches("^\\s+[\\*|\\+|\\-]{1}.*$", element) ||
//								Pattern.matches("^\\s+.*$", element)){ // string(3)�� ���� level�� sublist�� ���
//		System.out.println("check3 result: " + result);		
//		System.out.println(result);
//							if(Pattern.matches("^\\s+[\\*|\\+|\\-]{1}.*$", element)){
//								result +="</li>" + list;
//								list = element;
//								enteredList = true;
//							} else if(Pattern.matches("^\\s+.*$", element)){
//								result += list;
//								list = element;
//							}
//							index++;
//							element = src.elementAt(index);
//						}
//						
//						if(enteredList) result += "</ul>";
//						else result += "</li></ul>";
//						index++;
//					} 
//					
//					
//					else{ // It is in the category of same list before
//						result += list;
//						list = element.substring(1);
//						index++;
//System.out.println("check4 result: " + result);		
//System.out.println(element);
//					}
//				}
//				if(entered){
//					result += "</ul1>";
//					lineVector.addElement(result);
//				}
//				else list = "<li>"+list+"</li></ul1>";
//				lineVector.addElement(result);
//				
//			}
			
			
			
			else{
				lineVector.addElement(element);
				index++;
				if(index == src.size()) break;
			}

		}
				//	System.out.println("out");
		return lineVector;
		
	}
}
