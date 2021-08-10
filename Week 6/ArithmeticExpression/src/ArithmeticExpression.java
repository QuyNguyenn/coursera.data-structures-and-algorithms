import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ArithmeticExpression {

    private static List<Float> MaximumValueCalculator(int[] number, int[] arithmetic, int l, int r) {
        List<Float> result = new ArrayList<>();
        if (l == r) {
            result.add((float) number[l]);
            return result;
        }

        float max = Float.NEGATIVE_INFINITY;
        for (int i = 0; i < r - l; i++) {
            List<Float> tmp1 = MaximumValueCalculator(number, arithmetic, l, l + i);
            List<Float> tmp2 = MaximumValueCalculator(number, arithmetic, l + i + 1, r);
            switch (arithmetic[l + i]) {
                case 1:
                    for (int j = 0; j < tmp1.size(); j++) {
                        for (int k = 0; k < tmp2.size(); k++)
                            result.add(tmp1.get(j) + tmp2.get(k));
                    }
                    break;
                case 2:
                    for (int j = 0; j < tmp1.size(); j++) {
                        for (int k = 0; k < tmp2.size(); k++)
                            result.add(tmp1.get(j) - tmp2.get(k));
                    }
                    break;
                case 3:
                    for (int j = 0; j < tmp1.size(); j++) {
                        for (int k = 0; k < tmp2.size(); k++)
                            result.add(tmp1.get(j) * tmp2.get(k));
                    }
                    break;
                case 4:
                    for (int j = 0; j < tmp1.size(); j++) {
                        for (int k = 0; k < tmp2.size(); k++)
                            result.add(tmp1.get(j) / tmp2.get(k));
                    }
                    break;
                default: break;
            }
        }
        return result;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = scanner.nextInt();
        int[] number = new int[n];
        int[] arithmetic = new int[n];
        for (int i = 0; i < n; i++) {
            number[i] = scanner.nextInt();

            // 0 == no arithmetic expressions
            // 1 == "+"
            // 2 == "-"
            // 3 == "*"
            // 4 == "/"
            arithmetic[i] = scanner.nextInt();
        }

        List<Float> value = MaximumValueCalculator(number, arithmetic, 0, n - 1);
        float result = Float.NEGATIVE_INFINITY;
        for (float afloat: value) {
            result = Math.max(result, afloat);
        }
        System.out.println(result);
        System.out.println(value.size());
    }
}
