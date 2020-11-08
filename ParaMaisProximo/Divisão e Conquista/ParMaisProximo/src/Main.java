import java.awt.*;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Main {
    public static void main(String args[]) {
        EncontreParMaisProximo encontreParMaisProximo = new EncontreParMaisProximo();

        Point[] aleatorios = workload(70000, 10, 20);
//            Point[] par = encontreParMaisProximo.recuperaPontosMaisProximos(aleatorios);
        encontreParMaisProximo.tempoDivisaoEConquista(aleatorios);
//            System.out.print(par[0].toString());
//            System.out.println(par[1].toString());
    }

    static public Point[] workload(int n, int maxX, int maxY){
        List<Point> nova = new ArrayList<Point>(n);
        Random aleat = new Random(LocalTime.now().getNano());

        for(int i=0; i<n; i++){
            int x = aleat.nextInt(maxX);
            int y = aleat.nextInt(maxY);
            Point p = new Point(x,y);
            nova.add(p);
        }

        Point[] aux = new Point[nova.size()];
        aux = nova.toArray(aux);
        return aux;
    }
}
