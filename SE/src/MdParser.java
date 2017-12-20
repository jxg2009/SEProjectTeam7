import java.util.Vector;
import java.util.regex.Pattern;

public class MdParser {
	public MdParser(){};
	public Vector<String> processVector = new Vector<String>(); // 중간 중간 HTML로 바꾸는 과정을 저장하는 벡터
	
	// 한줄씩 나눠서 저장한 벡터에서 각줄을 읽어와 단어단위로 잘라 벡터에 너은 후 그 벡터를 반환
	public Vector<String> parseWhiteSpace(Vector<String> src){
		int index = 0;
		String s = null; // 한줄씩 읽어서 저장할 변수
		Vector<String> resultVector = new Vector<String>(); //단어 단위로 자른 element가 들어감
		
		while(index != src.size()){ // 한줄씩 읽어서 String s에 넣음(마지막 죽까지 계속)
			String[] elements; // 한줄을 공백으로 나눈 결과인 문자들을 저장할 변수
			s = src.elementAt(index);
			elements = s.split("\\s+"); // 한줄을 공백기준으로 쪼개서 String배열 elements에 집어 넣음
			for(int i = 0; i < elements.length; i++){ // resultVector에 공백으로 구분된 문자들을 집어 넣음
				resultVector.add(elements[i]);
			}
			index++;
		}
		
		return resultVector;
		
	}
	
	
	// 공백을 기준으로 나뉘어진 element들을 가진 벡터를 전달받아 HTML element들로 바꾸어주는 함수
	public Vector<String> getMdString(Vector<String> src){
		int index = 0;
		
		if(!src.isEmpty()){
			while(true){
				String element = src.elementAt(index);
		
			 if(Pattern.matches("^\\*{2}\\S+\\*{2}$", element) || Pattern.matches("^\\*{1}\\S+\\*{1}$", element)){
					// <em></em>태그.*또는 **로 쌓여져 있는 문자 강조
					if(Pattern.matches("^\\*{2}\\S+\\*{2}$" ,element)){//**String** 형태인 경우
						element = element.replaceFirst("\\*{2}", "<em>");
						element = element.replaceFirst("\\*{2}", "</em>");
						processVector.addElement(element);
					} else{ // *String* 형태인 경우
						element = element.replaceFirst("\\*", "<em>");
						element = element.replaceFirst("\\*", "</em>");
						processVector.addElement(element);
					}
					index++;
			}
			 else if(Pattern.matches("^(\\\\<){1}.*(\\\\>){1}", element)){
				 	System.out.println("---------------TEST--------------:"+ element);
					element = element.replace("\\<", "&lt");
					element = element.replace("\\>", "&gt");
					System.out.println("---------------TEST2--------------:"+ element);
					processVector.addElement(element);
					index++;
			 }
				else{
					processVector.addElement(element);
					index++;
					if(index == src.size() -1 ) break;
				}	
			}
		}

		return processVector;
		
	}
}
