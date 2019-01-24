import java.util.HashMap;
import java.util.Map;

/**
 * https://leetcode.com/problems/evaluate-division
 */
public class EvaluateDivision {
    private class Entry {
        String root;
        double ratio;
        
        Entry(String root, double ratio) {
            this.root = root;
            this.ratio = ratio;
        }
        
        public int hashCode() {
            return root.hashCode();
        }
        
        public boolean equals(Object that) {
            return that != null && root.equals(((Entry)that).root);
        }
    }
    
    private class UF {
        Map<String, Entry> id = new HashMap<>();
        
        void union(String operand1, String operand2, double ratio) {
            if (!id.containsKey(operand1)) id.put(operand1, new Entry(operand1, 1.0));
            if (!id.containsKey(operand2)) id.put(operand2, new Entry(operand2, 1.0));

            Entry root1 = find(operand1);
            Entry root2 = find(operand2);
            if (root1.equals(root2)) return;
            
            id.put(root1.root, new Entry(root2.root, ratio * root2.ratio / root1.ratio));
        }

        Entry find(String operand) {
            double ratio = 1;
            Entry e = id.get(operand);
            
            while (e != null && !e.root.equals(operand)) {
                ratio *= e.ratio;
                operand = e.root;
                e = id.get(operand);
            }
            
            return e != null ? new Entry(operand, ratio) : null;
        }
    }
    
    public double[] calcEquation(String[][] equations, double[] values, String[][] queries) {
        UF uf = new UF();

        for (int i = 0; i < values.length; i++) {
            String operand1 = equations[i][0];
            String operand2 = equations[i][1];
            double ratio = values[i];
            uf.union(operand1, operand2, ratio);
        }

        double[] result = new double[queries.length];
        for (int i = 0; i < queries.length; i++) {
            String operand1 = queries[i][0];
            String operand2 = queries[i][1];
            
            Entry root1 = uf.find(operand1);
            Entry root2 = uf.find(operand2);
            
            if (root1 != null && root1.equals(root2)) {
                result[i] = root1.ratio / root2.ratio;
            } else {
                result[i] = -1;
            }
        }
        return result;
    }
}