import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Vector;

public class FileRead {
	Vector<String> resultVector = new Vector<String>(); // ���� �������� �Ľ��� string�� �� ����
	Vector<String> lineVector = new Vector<String>(); // �� ������ �Ľ��� string�� �� ����
	String fileName; // �Ľ��� md������ �̸�
	FileReader fileReader = null;
	BufferedReader br = null;
	
	FileRead(String fileName){
		this.fileName = fileName;
	}
	
	public Vector<String> parseWithLine(){
		try{
			fileReader = new FileReader("./" + fileName); // ���� ���
			br = new BufferedReader(fileReader);
			String s = null; // ���پ� �о ������ ����
			while((s = br.readLine()) != null){ // ���پ� �о String s�� ����(������ �ױ��� ���)
				lineVector.add(s);
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		for(int i = 0; i < lineVector.size(); i++){
			System.out.println(lineVector.elementAt(i));
		}
		
		return lineVector;
	}
	
	// md������ ������ �������� �Ľ��ؼ� resultVector�� ��� method
	public Vector<String> parseWithWhiteSpace(){
		try{ //"C:\\Users\\User\\workspace\\SE\\src\\"
			fileReader = new FileReader(fileName); // ���� ���
			br = new BufferedReader(fileReader);
			String s = null; // ���پ� �о ������ ����
			while((s = br.readLine()) != null){ // ���پ� �о String s�� ����(������ �ױ��� ���)
				String[] elements; // ������ �������� ���� ����� ���ڵ��� ������ ����
				elements = s.split("\\s+"); // ������ ����������� �ɰ��� String�迭 elements�� ���� ����
				for(int i = 0; i < elements.length; i++){ // resultVector�� �������� ���е� ���ڵ��� ���� ����
					resultVector.add(elements[i]);
				}	
			}			
		}catch(Exception e){
			e.printStackTrace();
		}
		for(int i = 0 ; i < resultVector.size(); i++){
			System.out.println(resultVector.elementAt(i));
		}
		System.out.println("====================================================");
		return resultVector;
	}
}
