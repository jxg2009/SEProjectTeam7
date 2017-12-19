import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Vector;

public class FileRead {
	Vector<String> resultVector = new Vector<String>(); // 공백 기준으로 파싱한 string이 들어갈 벡터
	Vector<String> lineVector = new Vector<String>(); // 줄 단위로 파싱한 string이 들어갈 벡터
	String fileName; // 파싱할 md파일의 이름
	FileReader fileReader = null;
	BufferedReader br = null;
	
	FileRead(String fileName){
		this.fileName = fileName;
	}
	
	public Vector<String> parseWithLine(){
		try{
			fileReader = new FileReader("C:\\Users\\User\\workspace\\SE\\src\\" + fileName); // 절대 경로
			br = new BufferedReader(fileReader);
			String s = null; // 한줄씩 읽어서 저장할 변수
			while((s = br.readLine()) != null){ // 한줄씩 읽어서 String s에 넣음(마지막 죽까지 계속)
				lineVector.add(s);
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		for(int i = 0 ; i < lineVector.size(); i++){
			System.out.println(lineVector.elementAt(i));
		}
		return lineVector;
	}
	
	// md파일을 공백을 기준으로 파싱해서 resultVector에 담는 method
	public Vector<String> parseWithWhiteSpace(){
		try{
			fileReader = new FileReader("C:\\Users\\User\\workspace\\SE\\src\\" + fileName); // 절대 경로
			br = new BufferedReader(fileReader);
			String s = null; // 한줄씩 읽어서 저장할 변수
			while((s = br.readLine()) != null){ // 한줄씩 읽어서 String s에 넣음(마지막 죽까지 계속)
				String[] elements; // 한줄을 공백으로 나눈 결과인 문자들을 저장할 변수
				elements = s.split("\\s+"); // 한줄을 공백기준으로 쪼개서 String배열 elements에 집어 넣음
				for(int i = 0; i < elements.length; i++){ // resultVector에 공백으로 구분된 문자들을 집어 넣음
					resultVector.add(elements[i]);
				}
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
//		for(int i = 0 ; i < resultVector.size(); i++){
//			System.out.println(resultVector.elementAt(i));
//		}
		
		return resultVector;
		

	}
	
	
}
