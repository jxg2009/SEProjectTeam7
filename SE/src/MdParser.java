import java.util.Vector;
import java.util.regex.Pattern;

public class MdParser {
	public MdParser(){};
	public Vector<String> process = new Vector<String>(); // �߰� �߰� HTML�� �ٲٴ� ������ �����ϴ� ����
	
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
		
		System.out.println("============================================");
		for(int i = 0 ; i < resultVector.size();i++){
			System.out.println(resultVector.elementAt(i));
		}
		return resultVector;
		
	}
	
	
	// ������ �������� �������� element���� ���� ���͸� ���޹޾� HTML element��� �ٲپ��ִ� �Լ�
	public Vector<String> getMdString(Vector<String> src){
		int index = 0;
		
		if(!src.isEmpty()){
			while(true){
				String element = src.elementAt(index);
				
				// <H1></H1> �±�
		
			 if(Pattern.matches("^\\*{2}\\S+\\*{2}$", element) || Pattern.matches("^\\*{1}\\S+\\*{1}$", element)){
					// <em></em>�±�.*�Ǵ� **�� �׿��� �ִ� ���� ����
	System.out.println("test: " + element );
					if(Pattern.matches("^\\*{2}\\S+\\*{2}$" ,element)){//**String** ������ ���
						element = element.replaceFirst("\\*{2}", "<em>");
						element = element.replaceFirst("\\*{2}", "</em>");
						process.addElement(element);
					} else{ // *String* ������ ���
						element = element.replaceFirst("\\*", "<em>");
						element = element.replaceFirst("\\*", "</em>");
						process.addElement(element);
					}
					index++;
				}
				else{
					process.addElement(element);
					index++;
					if(index == src.size() -1 ) break;
				}	
			}
		}

		for(int i =0; i < process.size(); i++){
			System.out.println("+++++++++++++++++++++++++++++");
			System.out.println(process.elementAt(i));
		}
		return src;
		
	}
}
