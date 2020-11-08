using System;
using System.Collections.Generic;
using System.Diagnostics;
using System.Linq;
using System.Security.Cryptography.X509Certificates;

namespace Exercicio1Lab3
{
    class Program
    {
        #region Gerador de Pontos
        public static Ponto[] GeradorPontos(int tamanhoArray)
        {
            if (tamanhoArray <= 0)
                throw new Exception("O array não pode ter tamanho 0 ou negativo.");

            int tamanhoPositivo = tamanhoArray;
            Ponto[] pontos = new Ponto[tamanhoArray + 1];


            for(int i = 0; i <= tamanhoArray; i++)
            {
                pontos[i] = new Ponto(i + 1, (i + 1) * (-1));
            }
            return pontos.ToArray();
        }
        #endregion

        #region Força Bruta em 5 Segundos
        //O tamanho do maior conjunto possível com solução fornecida no tempo máximo de 5 segundos: 20000.
        #endregion

        /// <summary>Instâncias dos Problemas
        /// <para>Os casos bases, foram escolhidos como:</para>
        /// <para>100</para>
        /// <para>1000</para>
        /// <para>10000</para>
        /// <para>100000</para>
        /// </summary>
        /// 

        #region Resultados
        //000100:  00:00:00.0009263
        //001000:  00:00:00.0169473
        //010000:  00:00:02.0762058
        //100000:  (Impraticável)
        #endregion

        static void Main(string[] args)
        {
            Stopwatch cronometro = new Stopwatch();

            #region Chamada do Médoto de Gerar Pontos
            Ponto[] pontos = GeradorPontos(1000);
            #endregion

            #region Calcula Distância Euclidiana
            cronometro.Start();
            pontos = Calculo.DistanciaEuclidianaForcaBruta(pontos);
            cronometro.Stop();
            #endregion

            #region Print de Resultados
            pontos.ToList().ForEach(x => Console.WriteLine("x: " + x.x + " y: " + x.y));
            Console.WriteLine("Tempo: " + cronometro.Elapsed);
            #endregion
        }
    }
}
