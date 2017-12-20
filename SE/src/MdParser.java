import java.util.Vector;
import java.util.regex.Pattern;

public class MdParser {
	public MdParser(){};
	public Vector<String> processVector = new Vector<String>(); // �߰� �߰� HTML�� �ٲٴ� ������ �����ϴ� ����
	
	// ���پ� ������ ������ ���Ϳ��� ������ �о�� �ܾ������ �߶� ���Ϳ� ���� �� �� ���͸� ��ȯ
	public Vector<String> parseWhiteSpace(Vector<String> src){
		int index = 0;
		String s = null; // ���پ� �о ������ ����
		Vector<String> resultVector = new Vector<String>(); //�ܾ� ������ �ڸ� element�� ��
		
		while(index != src.size()){ // ���پ� �о String s�� ����(������ �ױ��� ���)
			String[] elements; // ������ �������� ���� ����� ���ڵ��� ������ ����
			s = src.elementAt(index);
			elements = s.split("\\s+"); // ������ ����������� �ɰ��� String�迭 elements�� ���� ����
			for(int i = 0; i < elements.length; i++){ // resultVector�� �������� ���е� ���ڵ��� ���� ����
				resultVector.add(elements[i]);
			}
			index++;
		}
		
		return resultVector;
		
	}
	
	
	// ������ �������� �������� element���� ���� ���͸� ���޹޾� HTML element��� �ٲپ��ִ� �Լ�
	public Vector<String> getMdString(Vector<String> src){
		int index = 0;
		
		if(!src.isEmpty()){
			while(true){
				String element = src.elementAt(index);
		
			 if(Pattern.matches("^\\*{2}\\S+\\*{2}$", element) || Pattern.matches("^\\*{1}\\S+\\*{1}$", element)){
					// <em></em>�±�.*�Ǵ� **�� �׿��� �ִ� ���� ����
					if(Pattern.matches("^\\*{2}\\S+\\*{2}$" ,element)){//**String** ������ ���
						element = element.replaceFirst("\\*{2}", "<em>");
						element = element.replaceFirst("\\*{2}", "</em>");
						processVector.addElement(element);
					} else{ // *String* ������ ���
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
