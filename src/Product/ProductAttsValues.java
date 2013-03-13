package Product;

import java.util.HashMap;

public class ProductAttsValues {
	HashMap<String, String> hashMap = new HashMap<String,String>();
	
	public HashMap<String, String> getHs() {
		return hashMap;
	}
	public void setHs(HashMap<String, String> hs) {
		this.hashMap = hs;
	}
	public void addAttValue(String att,String value){
		hashMap.put(att,value);
	}
}
