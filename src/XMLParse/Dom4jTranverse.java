package XMLParse;

import java.io.File;
import java.util.Iterator;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.ProcessingInstruction;
import org.dom4j.VisitorSupport;
import org.dom4j.io.SAXReader;

public class Dom4jTranverse {
	private File inputXml;
	public static void main(String[] argv) {
		Dom4jTranverse dom4jParser = new Dom4jTranverse(new File(
				"employeeError.xml"));
		dom4jParser.traversalDocumentByIterator();
		System.out.println("---------------------");
		dom4jParser.traversalDocumentByVisitor();
	}


	public Dom4jTranverse(File inputXml) {
		this.inputXml = inputXml;
	}

	public Document getDocument() {
		SAXReader saxReader = new SAXReader();
		Document document = null;
		try {
			document = saxReader.read(inputXml);
		} catch (DocumentException e) {
			e.printStackTrace();
		}
		return document;
	}

	public Element getRootElement() {
		return getDocument().getRootElement();
	}

	public void traversalDocumentByIterator() {
		Element root = getRootElement();
		// ö�ٸ��ڵ��������ӽڵ�
		for (Iterator ie = root.elementIterator(); ie.hasNext();) {
			System.out.println("======");
			Element element = (Element) ie.next();
			System.out.println(element.getName());

			// ö������
			for (Iterator ia = element.attributeIterator(); ia.hasNext();) {
				Attribute attribute = (Attribute) ia.next();
				System.out.println(attribute.getName() + ":"
						+ attribute.getData());
			}
			// ö�ٵ�ǰ�ڵ��������ӽڵ�
			for (Iterator ieson = element.elementIterator(); ieson.hasNext();) {
				Element elementSon = (Element) ieson.next();
				System.out.println(elementSon.getName() + ":"
						+ elementSon.getText());
			}
		}
	}

	public void traversalDocumentByVisitor() {
		getDocument().accept(new MyVisitor());
	}

	/**
	 * �����Լ��ķ�������
	 */
	private static class MyVisitor extends VisitorSupport {
		/**
		 * �������Խڵ㣬��ӡ���Ե����ֺ�ֵ
		 */
		public void visit(Attribute node) {
			System.out.println("attribute : " + node.getName() + " = "
					+ node.getValue());
		}

		/**
		 * ���ڴ���ָ��ڵ㣬��ӡ����ָ��Ŀ�������
		 */
		public void visit(ProcessingInstruction node) {
			System.out.println("PI : " + node.getTarget() + " "
					+ node.getText());
		}

		/**
		 * ����Ԫ�ؽڵ㣬�ж��Ƿ�ֻ�����ı����ݣ����ǣ����ӡ��ǵ����ֺ� Ԫ�ص����ݡ�������ǣ���ֻ��ӡ��ǵ�����
		 */
		public void visit(Element node) {
			if (node.isTextOnly())
				System.out.println(node.getName() + " = "
						+ node.getText());
			else
				System.out.println(node.getName());
		}
	}


}
