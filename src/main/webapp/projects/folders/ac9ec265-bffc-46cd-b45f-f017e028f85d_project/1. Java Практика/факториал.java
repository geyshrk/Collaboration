import java.util.Scanner;
public class Program3 {
    public static void main (String[] args) {
        Scanner in = new Scanner(System.in);
        float x = in.nextFloat();
        float answer = 1;

        float factor = 1;

        float xd = 1;
        float xx = x;

        float n = 1;

        
        while (xd > 0.000001f){
            xd = x / factor;
            answer += xd;
            
            n += 1;
            factor *= n;
            x *= xx;
        }
        System.out.println(answer);
    }
}