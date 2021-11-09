package core;
import static java.lang.Math.min;
import java.util.Arrays;

public class Digit {
    private double[] data;
    private int label;
   
    public Digit(double[] data, int label) {
        this.data = data;
        this.label = label;
    }

    public double[] to_categorical(int label) {
        double[] categories = new double[10];

        for (int i = 0; i < categories.length; i++) categories[i] = 0;

        if (label >= 0 && label <= 9) categories[label] = 1;

        return categories;
    }

    public void print() {
        int index = 0;
        for (int i = 0; i < data.length; i++) {
            System.out.print(Math.round(data[i]) + " ");
            index++;
            
            if (index == 28) {
                System.out.println("");
                index = 0;
            }
        }
    }

    public String toString() {
        final StringBuilder sb = new StringBuilder();
        String line = " ---------------------------- ";
        sb.append("Label: ").append((label == -1) ? "Unknown" : label)
                .append("  (").append(Arrays.toString(to_categorical(label)))
                .append("\n").append(line);
        int cnt = 0;
        for (int r = 0; r < 28; r++) {
            sb.append("\n|");
            for (int c = 0; c < 28; c++) {
                sb.append(toChar(data[cnt++]));
            }
            sb.append("|");
        }

        sb.append("\n").append(line).append("\n");
        return sb.toString();
    }
    
    private char toChar(double val) {
        return " .:-=+*#%@".charAt(min((int) (val * 10), 9));
    }

    public int getLabel() { return label; }
    public void setLabel(int label) { this.label = label; }

    public double[] getData() { return data; }
    public void setData(double[] data) { this.data = data; }
}
