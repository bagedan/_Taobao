package Product;

import java.util.ArrayList;
import java.util.HashMap;

public class AttValues {
	HashMap<String, ArrayList<HashMap<String, Integer>>> hashMap = new HashMap<String, ArrayList<HashMap<String, Integer>>>();
	// ����       ֵ+����
	public HashMap<String, ArrayList<HashMap<String, Integer>>> getHs() {
		return hashMap;
	}

	public void setHs(HashMap<String, ArrayList<HashMap<String, Integer>>> hs) {
		this.hashMap = hs;
	}
}
