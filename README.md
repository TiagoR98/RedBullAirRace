# RedBullAirRace

## Autores
- Tiago José Antunes Ribeiro | 201605619 | up201605619@fe.up.pt
- Bruno Edgar Évora Rebelo Oliveira | 201605516 | up201605516@fe.up.pt

## Entega Final
### Diagramas UML

#### Classes
![Classes](/images/UML.bmp)

#### Casos de utilização
![casos](/images/use_case.bmp)

#### Diagrama de estados
![estados](/images/state.bmp)

### Padrões de jogo:
* **Keep 'em Separated** - Baseado na estrutura do jogo Asteroids fornecido. Permite separar os componentes do jogo e implementar uma arquitetura MVC. <return>
* **One to rule them All** - Presente na classe RedBullGame, Game Controller,Game Model e Game View, de modo a numa instância juntar todos os objetos de cada componente do jogo.<return>
* **Observer** - Implementado entre o avião e o pylon. Permite ao pylon notificar o avião que ocorreu uma colisão, desencadeando uma ação em ambos os objetos.<return>
* **Reuse, restore, recycle** -  Os pylons serão gerados com recurso à classe pool fornecida pelo LibGDX, evitando assim a constante alocação e destruição de objetos em memória à medida que se avança no nível de jogo, aumentando a eficiência e performance.<return>
* **Be Different** - Utilizado em definir diversos tipos para os aviães, pylons e posições de voo. Permite que estes tipos sejam totalmente dinâmicos, podendo adicionar-se novos sem a necessidade de modificar o código existente.<return>

### Mockups

Menu Principal | HighScores | Regras do Jogo
---------------|------------|---------------
![menu_principal](/images/main_menu.png)|![highscores](/images/highscores.png)|![Regras](/images/rules.png)

Escolher Avião | Jogo | Game Over
---------------|------|----------
![choose_plane](/images/choose_plane.png)|![game](/images/game.png)|![game_over](/images/game_over.png)

Submeter HighScore |
-------------------|
![submit_highscore](/images/submit_highscore.png)|

###Manual de Instruções
![Como_Jogar](/android/assets/Manual/manrules.png)
![Screenshots](/android/assets/Manual/compilacao.png)

###Dificuldades
* Conseguir carregar todos os assets do jogo apenas no início, em vez de os ir carregando repetidamente, o que causava loading screens e eventualmente um crash por falta de memória.


* Conseguir construir o jugo numa arquitetura MVC, baseada na que foi fornecida, tendo sido necessário algum tempo para entender como esta funciona e como as classes lá estão organizadas.

* Programar as físicas do avião, visto que o box2d trabalha em metros foi necessário estabelecer as dimensões físicas da arena e efetuar a sua conversão para o respetivo ecrã de modo a que a velocidade linear do avião pudesse ser a correta. Além disso, conseguir implementar o impulso correto no avião de modo ao jogo ficar com um grau de dificuldade adequado e a este ser equilibrado com a gravidade.


* Do mesmo modo, conseguir que a colisão com o pylon fosse avaliada usando medidas absolutas (metros).

-Por fim, perceber como construir um jogo em libgdx em android, lidando assim pela primeira vez com interfaces, resoluções, toques, gestos,partículas, fontes e assets.

###Tempo dispendido e distribuição
* O trabalho foi realizado durante as aulas laboratorias dedicadas ao projeto e durante as últimas duas semanas antes da data de entrega.
* Durante as aulas o grupo trabalhou em conjunto nas mesmas tarefas. Na última fase do trabalho, o elemento Tiago Ribeiro liderou o processo de desenvolvimento, contanto com a colaboração do Bruno Oliveira.

###Tempo dispendido e distribuição