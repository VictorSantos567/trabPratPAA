import java.awt.*;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class EncontreParMaisProximo {


    public Point[] recuperaPontosMaisProximos(Point[] aleatorio) {
        organizaPontosPorX(aleatorio);
        return recuperaPontosMaisProximos(aleatorio, 0, aleatorio.length - 1);
    }

    private void organizaPontosPorX(Point[] aleatorio) {
        int size = aleatorio.length;
        quickSort(aleatorio, 0, size - 1);
    }

    private Point[] recuperaPontosMaisProximos(Point[] pontos, int comeco, int fim) {

        if (comeco == fim) {
            return null;
        }
        if (comeco + 1 == fim) {
            return new Point[]{pontos[comeco], pontos[fim]};
        }

        int medioX = (comeco + fim) / 2;

        Point[] parMaisProximoEsquerda = this.recuperaPontosMaisProximos(pontos, comeco, medioX);
        Point[] parMaisProximoDireita = this.recuperaPontosMaisProximos(pontos, medioX, fim);
        Point[] retornoPar;

        double retornoDelta;

        double esquerdaDelta = this.recuperaDistancia(parMaisProximoEsquerda);
        double direitaDelta = this.recuperaDistancia(parMaisProximoDireita);
        double delta;
        if (esquerdaDelta < direitaDelta) {
            delta = esquerdaDelta;
            retornoPar = parMaisProximoEsquerda;
        } else {
            delta = direitaDelta;
            retornoPar = parMaisProximoDireita;
        }
        retornoDelta = delta;

        int pontaEsquerda = this.recuperaPontaEsquerdaDelta(pontos, medioX, esquerdaDelta);
        if (pontaEsquerda < comeco)
            pontaEsquerda = comeco;
        int pontaDireita = this.recuperaPontaDireitaDelta(pontos, medioX, direitaDelta);
        if (pontaDireita > fim)
            pontaDireita = fim;

        int[][] yOrdemInfo = this.ordenaPontosPorY(pontos, pontaEsquerda, pontaDireita);
        int[] indexParaOrdemY = yOrdemInfo[0];
        int[] ordemYParaIndex = yOrdemInfo[1];

        for (int i = pontaEsquerda; i <= medioX; i++) {

            int ordemY = indexParaOrdemY[i - pontaEsquerda];

            for (int j = ordemY - 1; j >= 0; j--) {

                Point[] par = new Point[]{pontos[i], pontos[ordemYParaIndex[j]]};

                if (this.recuperaDistanciaY(par) > delta)
                    break;
                double distancia = this.recuperaDistancia(par);
                if (distancia < retornoDelta) {
                    retornoDelta = distancia;
                    retornoPar = par;
                }
            }

            for (int j = ordemY + 1; j < indexParaOrdemY.length; j++) {

                Point[] par = new Point[]{pontos[i], pontos[ordemYParaIndex[j]]};

                if (this.recuperaDistanciaY(par) > delta)
                    break;

                double distancia = this.recuperaDistancia(par);
                if (distancia < retornoDelta) {
                    retornoDelta = distancia;
                    retornoPar = par;
                }
            }

        }

        return retornoPar;
    }

    private int[][] ordenaPontosPorY(Point[] pontos, int pontaEsquerda, int pontaDireita) {
        int tamanho = pontaDireita - pontaEsquerda + 1;
        int[] ordemParaIndex = new int[tamanho];
        for (int i = 0; i < tamanho; i++)
            ordemParaIndex[i] = pontaEsquerda + i;

        quickSortParaAreaDelta(pontos, ordemParaIndex, 0, tamanho - 1);

        int[] indexParaOrdem = new int[tamanho];
        for (int i = 0; i < tamanho; i++) {
            indexParaOrdem[ordemParaIndex[i] - pontaEsquerda] = i;
        }

        return new int[][]{indexParaOrdem, ordemParaIndex};
    }

    private void quickSortParaAreaDelta(Point[] pontos, int[] index, int esquerda, int direita) {
        if (esquerda >= direita)
            return;

        int i = esquerda, j = direita;
        int tmp;
        double pivot = (pontos[index[esquerda]].y + pontos[index[direita]].y) / 2;

        while (i <= j) {
            while (pontos[index[i]].y < pivot)
                i++;
            while (pontos[index[j]].y > pivot)
                j--;
            if (i <= j) {
                tmp = index[i];
                index[i] = index[j];
                index[j] = tmp;
                i++;
                j--;
            }
        }

        if (esquerda < i - 1)
            quickSortParaAreaDelta(pontos, index, esquerda, i - 1);
        if (i < direita)
            quickSortParaAreaDelta(pontos, index, i, direita);

    }

    private int recuperaPontaDireitaDelta(Point[] ps, int medioX, double direitaDelta) {
        for (int i = medioX; i < ps.length; i++) {
            if (ps[i].x - ps[medioX].x > direitaDelta)
                return i - 1;
        }
        return ps.length - 1;
    }

    private int recuperaPontaEsquerdaDelta(Point[] ps, int medioX, double leftDelta) {
        for (int i = medioX; i >= 0; i--) {
            if (ps[medioX].x - ps[i].x > leftDelta)
                return i + 1;
        }
        return 0;
    }

    private void quickSort(Point[] pontos, int left, int right) {
        if (left >= right)
            return;

        int i = left, j = right;
        Point tmp;
        double pivot = (pontos[left].x + pontos[right].x) / 2;

        while (i <= j) {
            while (pontos[i].x < pivot)
                i++;
            while (pontos[j].x > pivot)
                j--;
            if (i <= j) {
                tmp = pontos[i];
                pontos[i] = pontos[j];
                pontos[j] = tmp;
                i++;
                j--;
            }
        }

        if (left < i - 1)
            quickSort(pontos, left, i - 1);
        if (i < right)
            quickSort(pontos, i, right);

    }

    private double recuperaDistancia(Point[] ps) {
        if (ps == null || ps.length != 2)
            return -1;
        return Math.pow((ps[0].x - ps[1].x) * (ps[0].x - ps[1].x) + (ps[0].y - ps[1].y) * (ps[0].y - ps[1].y), 0.5);
    }


    private double recuperaDistanciaY(Point[] ps) {
        return Math.abs(ps[0].y - ps[1].y);
    }


    public void tempoDivisaoEConquista(Point[] aleatorios) {
        long tempoInicial = System.nanoTime();
        recuperaPontosMaisProximos(aleatorios);
        long tempoFinal = System.nanoTime();
        System.out.println("Tempo divisão e conquista: " + (tempoFinal - tempoInicial));
    }


}