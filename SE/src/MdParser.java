import java.util.Vector;
import java.util.regex.Pattern;

public class MdParser {
	public MdParser() {
	};

	public Vector<String> processVector = new Vector<String>(); // �߰� �߰� HTML��
																// �ٲٴ� ������ �����ϴ�
																// ����

	// ���پ� ������ ������ ���Ϳ��� ������ �о�� �ܾ������ �߶� ���Ϳ� ���� �� �� ���͸� ��ȯ
	public Vector<String> parseWhiteSpace(Vector<String> src) {
		int index = 0;
		String s = null; // ���پ� �о ������ ����
		Vector<String> resultVector = new Vector<String>(); // �ܾ� ������ �ڸ�
															// element�� ��

		while (index != src.size()) { // ���پ� �о String s�� ����(������ �ױ��� ���)
			String[] elements; // ������ �������� ���� ����� ���ڵ��� ������ ����
			s = src.elementAt(index);
			elements = s.split("\\s+"); // ������ ����������� �ɰ��� String�迭 elements�� ����
										// ����
			for (int i = 0; i < elements.length; i++) { // resultVector�� ��������
														// ���е� ���ڵ��� ���� ����
				resultVector.add(elements[i]);
			}
			index++;
		}
		for (int i = 0; i < resultVector.size(); i++) {
			System.out.println(resultVector.elementAt(i));
		}
		return resultVector;

	}

	// ������ �������� �������� element���� ���� ���͸� ���޹޾� HTML element��� �ٲپ��ִ� �Լ�
	public Vector<String> getMdString(Vector<String> src) {
		int index = 0;

		if (!src.isEmpty()) {
			while (true) {
				String element = src.elementAt(index);

				if (Pattern.matches("^\\*{2}\\S+\\*{2}$", element) || Pattern.matches("^\\*{1}\\S+\\*{1}$", element)) {
					// <em></em>�±�.*�Ǵ� **�� �׿��� �ִ� ���� ����
					if (Pattern.matches("^\\*{2}\\S+\\*{2}$", element)) {// **String**
																			// ������
																			// ���
						element = element.replaceFirst("\\*{2}", "<em>");
						element = element.replaceFirst("\\*{2}", "</em>");
						processVector.addElement(element);
					} else { // *String* ������ ���
						element = element.replaceFirst("\\*", "<em>");
						element = element.replaceFirst("\\*", "</em>");
						processVector.addElement(element);
					}
					index++;
				} else if (Pattern.matches("^(\\\\<){1}.*(\\\\>){1}", element)) {
					// \<String\> => &ltpre&gt ���·� ��ħ
					element = element.replace("\\<", "&lt");
					element = element.replace("\\>", "&gt");
					processVector.addElement(element);
					index++;
				} else if (Pattern.matches("^\\[.*\\]\\(.*$", element)) {
					/*
					 * change [Hisnet](http://hisnet.handong.edu "Hisnet") into
					 * <a href=��http://hisnet.handong.edu��
					 * title=��Hisnet��>Hisnet</a>
					 */
					String t = src.elementAt(index + 1);
					src.addElement("\n");
					src.remove(index + 1);
					int d = t.indexOf(")");
					t = t.substring(0, d);
					String s = element + " " + t;
					d = s.indexOf("]"); // index of last character of name
					String name = s.substring(1, d); // name - Hisnet;
					int g = s.indexOf("\""); // find the character " to find
												// Title first index
					int g2 = s.indexOf("\"", g + 1); // find the charater " to
														// find Title last index
					String title = s.substring(g, g2 + 1); // Title - Hisnet
					int g3 = s.indexOf("http"); // find the starting index of
												// "http"
					String temp = s.substring(g3);
					String[] k = temp.split("\\s+");
					String address = k[0]; // address
					String result = "<a href = \"" + address + "\" title = " + title + ">" + name + "</a>";
					processVector.add(result);
					index++;
				} else if (element.equals("<table>")) {
					// <table> tag�� <table border = '1'>�� ����
					String result = element.replace("e", "e border = '1'");
					processVector.add(result);
					index++;
				} else if (Pattern.matches("^!\\[.*\\]\\(.*\\)$", element)) {

					/*
					 * ![tag](https://www.handong.edu/site/handong/res/img/logo.png)
					 * ���� ���� image markdown syntax�� �Ʒ��� ���� ��ħ 
					 * <img src = "https://www.handong.edu/site/handong/res/img/logo.png " alt = "tag">
					 * 
					 */

					int addressIndex = element.indexOf("(");
					String address = element.substring(addressIndex + 1, element.length() - 1); // ip �ּ�

					int tagIndex1 = element.indexOf("[");
					int tagIndex2 = element.indexOf("]");
					String tag = element.substring(tagIndex1 + 1, tagIndex2); // tag �̸�

					String result = "<img src = \"" + address + "\" alt = \"" + tag + "\">"; //��ȯ�� ���
					processVector.add(result);
					index++;
				} else {
					processVector.addElement(element);
					index++;

				}
				if (index == src.size())
					break;
			}
		}

		return processVector;

	}
}
