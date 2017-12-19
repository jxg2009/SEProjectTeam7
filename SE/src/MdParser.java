import java.util.Vector;
import java.util.regex.Pattern;

public class MdParser {
	public MdParser(){};
	public Vector<String> process; // 중간 중간 HTML로 바꾸는 과정을 저장하는 벡터
	
	// 공백을 기준으로 나뉘어진 element들을 가진 벡터를 전달받아 HTML element들로 바꾸어주는 함수
	public Vector<String> getMdString(Vector<String> src){
		int index = 0;
		
		if(!src.isEmpty()){
			while(true){
				String element = src.elementAt(index);
				
				// <H1></H1> 태그
		
				if(Pattern.matches("^(=)+$", element)){ // '='가 1개 이상 또는 '-'가 1개 이상
					process.add("h1");
				} else if(Pattern.matches("^\\*{2}\\S+\\*{2}$", element) || Pattern.matches("^\\*{1}\\S+\\*{1}$", element)){
					// <em></em>태그.*또는 **로 쌓여져 있는 문자 강조
					if(Pattern.matches("^\\*{2}\\S+\\*{2}$" ,element)){//**String** 형태인 경우
						element.replaceFirst("**", "<em>");
						element.replaceFirst("**", "</em>");
						process.addElement(element);
					} else{ // *String* 형태인 경우
						element.replaceFirst("*", "<em>");
						element.replaceFirst("*", "</em>");
						process.addElement(element);
					}
				} else if(element.equals("*")){
					String list = ""; // list String
					process.addElement("ul");
					list += (process.elementAt(++index) + " ");
				}
				index++;
				if(index == src.size() -1 ) break;
				
				
			}
		}

		
		return src;
		
	}
}
