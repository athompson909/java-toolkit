import java.util.ArrayList;
import java.util.HashMap;
import java.util.TreeMap;

public class InstanceBasedLearner extends SupervisedLearner {

    private Matrix _features;
    private Matrix _labels;

    private int k = 3;
    private boolean distanceWeighted = false;
    private boolean normalized = false;

    public void train(Matrix features, Matrix labels) throws Exception {
        this._features = features;
        this._labels = labels;
    }

    public void predict(double[] features, double[] labels) throws Exception {
//        for (double feature : features) {
//            System.out.print(feature + ", ");
//        }
//        System.out.println();
        TreeMap<Double, Double> distances = new TreeMap<>();
        for (int i = 0; i < _features.rows(); ++i) {

            double sum = 0.0;
            for (int j = 0; j < features.length; ++j) {
                sum += Math.abs(features[j] - _features.row(i)[j]);
            }
            distances.put(sum, _labels.row(i)[0]);
        }

        HashMap<Double, ArrayList<Double>> options = new HashMap<>();
        int i = 0;
        for (double labelVal : options.keySet()) {
            if (options.get(labelVal) == null) {
                ArrayList<Double> l = new ArrayList<>();
                l.add(labelVal);
                options.put(distances.get(labelVal), l);
            } else {
                options.get(distances.get(labelVal)).add(labelVal);
            }

            ++i;
            if (i == k)
                break;
        }

//        int resLen = -1;
//        int res = -1;
//        for (double labelVal : options.keySet()) {
//            double key = options.get(labelVal);
//            double val = labelVal
//        }

        labels[0] = 0;
    }

}
