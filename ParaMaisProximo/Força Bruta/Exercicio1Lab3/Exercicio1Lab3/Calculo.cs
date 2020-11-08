using System;
using System.Collections.Generic;
using System.Text;

namespace Exercicio1Lab3
{
    public class Calculo
    {
        public static Ponto[] DistanciaEuclidianaForcaBruta(Ponto[] pontos)
        {
            double menosDistancia = int.MaxValue;
            Ponto[] pontosMaisProximos = new Ponto[2];

            for(int i = 0; i <= pontos.Length; i++)
            {
                for(int j = i + 1; j < pontos.Length; j++)
                {
                    var pitagoras = Math.Sqrt((pontos[i].x - pontos[j].x) * (pontos[i].x - pontos[j].x) + (pontos[i].y - pontos[j].y) * (pontos[i].y - pontos[j].y));
                    if (pitagoras < menosDistancia)
                    {
                        menosDistancia = pitagoras;
                        pontosMaisProximos[0] = pontos[i];
                        pontosMaisProximos[1] = pontos[j];
                    }
                }
            }
            return pontosMaisProximos;
        }
    }
}
