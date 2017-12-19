import java.util.Vector;
import java.util.regex.Pattern;

public class MdParser {
	public MdParser(){};
	public Vector<String> process; // �߰� �߰� HTML�� �ٲٴ� ������ �����ϴ� ����
	
	// ������ �������� �������� element���� ���� ���͸� ���޹޾� HTML element��� �ٲپ��ִ� �Լ�
	public Vector<String> getMdString(Vector<String> src){
		int index = 0;
		
		if(!src.isEmpty()){
			while(true){
				String element = src.elementAt(index);
				
				// <H1></H1> �±�
		
				if(Pattern.matches("^(=)+$", element)){ // '='�� 1�� �̻� �Ǵ� '-'�� 1�� �̻�
					process.add("h1");
				} else if(Pattern.matches("^\\*{2}\\S+\\*{2}$", element) || Pattern.matches("^\\*{1}\\S+\\*{1}$", element)){
					// <em></em>�±�.*�Ǵ� **�� �׿��� �ִ� ���� ����
					if(Pattern.matches("^\\*{2}\\S+\\*{2}$" ,element)){//**String** ������ ���
						element.replaceFirst("**", "<em>");
						element.replaceFirst("**", "</em>");
						process.addElement(element);
					} else{ // *String* ������ ���
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
