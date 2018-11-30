# Implementação da Curva Cúbica de Bezier

Trabalho Prático da disciplina Computação Gráfica com o objetivo de implementar uma das curvas paramétricas passadas em sala. A curva escolhida foi a Curva Cúbica de Bezier.

### Objetivo

Esse projeto permite que o usuário desenhe a curva de Bezier. Para isso, o usuário deve selecionar os quatro pontos de controle da curva clicando na tela nos pontos desejados, e assim o algoritmo desenhará a curva na tela.

### Como rodar o código

* Para a execução do código rode o arquivo clicando duas vezes em cima dele:

`````
CurvaBezier.jar
`````
* Ou use o comando na pasta em que o arquivo está localizado:

`````
java -jar CurvaBezier.jar 
`````

Para visualizar o código em si, abra a pasta CurvaBezier, e eles estarão dentro da pasta src.

### Descrição

A curva Bezier baseia os cáculos dos seus coeficientes no Binômio de Newton, que pode ser descrito pela fórmula abaixo:

![alt text](https://wikimedia.org/api/rest_v1/media/math/render/svg/0596e1dae2ec55d157c28785267b434742f53ee3)

Na qual temos:

* O valor t é um parametro que percorre toda a curva e se encontra entre os valores de 0 e 1
* n é o grau do binômio. Entretando, nesse caso como foi implementada a curva de bezier cúbica, então o n é igual a 3
* É usado n+1 pontos de controle para cada curva que desejamos desenhar, e como nesse caso o n é igual a 3, então temos 4 pontos de controle
*  Cada um dos pontos de controle (Bi) podem ser escolhidos aleatoriamente pelo usuário


Tendo como base todos esses conceitos, foi desenvolvido o método que calcula todos os pontos da curva paramétrica de Bezier, levando em consideração que é necessário a escolha dos 4 pontos de controle antes da chamada do método, como mostrado abaixo:


`````
for (double t = 0.0; t <= 1.0; t += incr) {
            
            x = ((-1 * Math.pow(t, 3) + 3 * Math.pow(t, 2) - 3 * t + 1) * p0.getX()
                    + (3 * Math.pow(t, 3) - 6 * Math.pow(t, 2) + 3 * t + 0) * p1.getX()
                    + (-3 * Math.pow(t, 3) + 3 * Math.pow(t, 2) + 0 * t + 0) * p2.getX()
                    + (1 * Math.pow(t, 3) + 0 * Math.pow(t, 2) + 0 * t + 0) * p3.getX());

            y = ((-1 * Math.pow(t, 3) + 3 * Math.pow(t, 2) - 3 * t + 1) * p0.getY()
                    + (3 * Math.pow(t, 3) - 6 * Math.pow(t, 2) + 3 * t + 0) * p1.getY()
                    + (-3 * Math.pow(t, 3) + 3 * Math.pow(t, 2) + 0 * t + 0) * p2.getY()
                    + (1 * Math.pow(t, 3) + 0 * Math.pow(t, 2) + 0 * t + 0) * p3.getY());

            buffer.setRGB((int)x, (int)y, Color.RED.getRGB());
 }
`````

### Manual do Usuário

Para mais informações de como utilizar a plataforma, leia o Manual do Usuário que está disponível em pdf e também em vídeo neste repositório, nomeados respectivamente por 'Manual do Usuário.pdf'e 'Manual em Vídeo.mov'

