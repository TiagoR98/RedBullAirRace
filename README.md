# RedBullAirRace
Red Bull Air race game based on flappy bird!

## Checkpoint
## Padr�es de jogo:
* **Keep �em Separated** � Baseado na estrutura do jogo Asteroids fornecido. Permite separar os componentes do jogo e implementar uma arquitetura MVC. <return>
* **One to rule them All** � Presente na classe RedBullGame, Game Controller,Game Model e Game View, de modo a numa inst�ncia juntar todos os objetos de cada componente do jogo.<return>
* **Observer** - Implementado entre o avi�o e o pylon. Permite ao pylon notificar o avi�o que ocorreu uma colis�o, desencadeando uma a��o em ambos os objetos.<return>
* **Reuse, restore, recycle** -  Os pylons ser�o gerados com recurso � classe pool fornecida pelo LibGDX, evitando assim a constante aloca��o e destrui��o de objetos em mem�ria � medida que se avan�a no n�vel de jogo, aumentando a efici�ncia e performance.<return>
* **Be Different** � Utilizado em definir diversos tipos para os avi�es, pylons e posi��es de voo. Permite que estes tipos sejam totalmente din�micos, podendo adicionar-se novos sem a necessidade de modificar o c�digo existente.<return>

## Casos de teste
* VelocidadeAdequada
* ImpulsoAoToque
* ColisaoComPylon
* ColisaoComChao
* Pontuacao
* VerificacaoHighscore
* EscolhaDeAviao

## Diagramas UML

### Classes
![Classes](/images/UML.bmp)

### Packages
![Packages](/images/packages.bmp) <------ mudar

### Casos de utilização
![casos](/images/use_case.bmp)

### Diagrama de estados
![estados](/images/state.bmp)

## Mockups


Menu Principal | HighScores | Regras do Jogo
---------------|------------|---------------
![menu_principal](/images/main_menu.png)|![highscores](/images/highscores.png)|![Regras](/images/rules.png)
