import java.util.HashMap;
import java.util.Map;

public class EvaluateDivision {
    public double[] calcEquation(String[][] equations, double[] values, String[][] queries) {
    	Map<String, String> id = new HashMap<>();
    	Map<String, Double> ratios = new HashMap<>();

    	for (int i = 0; i < values.length; i++) {
    		String op1 = equations[i][0];
    		String op2 = equations[i][1];
    		double ratio = values[i];
    		union(id, ratios, op1, op2, ratio);
    	}

    	double[] result = new double[queries.length];
    	for (int i = 0; i < queries.length; i++) {
    		String op1 = queries[i][0];
    		String op2 = queries[i][1];
    		if (!id.containsKey(op1) || !id.containsKey(op2) || !find(id, ratios, op1).equals(find(id, ratios, op2))) {
    			result[i] = -1;
    		} else {
    			result[i] = ratios.get(op1) / ratios.get(op2);
    		}
    	}
    	return result;
    }

    private void union(Map<String, String> id, Map<String, Double> ratios, String op1, String op2, double ratio) {
    	if (!id.containsKey(op1)) {
    		id.put(op1, op1);
    		ratios.put(op1, 1.0);
    	}
    	if (!id.containsKey(op2)) {
    		id.put(op2, op2);
    		ratios.put(op2, 1.0);
    	}
    	String opRoot1 = find(id, ratios, op1);
    	String opRoot2 = find(id, ratios, op2);
    	id.put(opRoot1, opRoot2);
    	ratios.put(opRoot1, ratio * ratios.get(op2) / ratios.get(op1));
    }

    private String find(Map<String, String> id, Map<String, Double> ratios, String op) {
    	if (op.equals(id.get(op))) return op;
    	String parent = id.get(op);
    	String grandParent = find(id, ratios, parent);
    	id.put(op, grandParent);
    	ratios.put(op, ratios.get(op) * ratios.get(parent));
    	return grandParent;
    }
}