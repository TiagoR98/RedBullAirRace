# RedBullAirRace

## Autores
- Tiago José Antunes Ribeiro | 201605619 | up201605619@fe.up.pt
- Bruno Edgar Évora Rebelo Oliveira | 201605516 | up201605516@fe.up.pt

## Checkpoint

## Padrôes de jogo:
* **Keep 'em Separated** - Baseado na estrutura do jogo Asteroids fornecido. Permite separar os componentes do jogo e implementar uma arquitetura MVC. <return>
* **One to rule them All** - Presente na classe RedBullGame, Game Controller,Game Model e Game View, de modo a numa instância juntar todos os objetos de cada componente do jogo.<return>
* **Observer** - Implementado entre o avião e o pylon. Permite ao pylon notificar o avião que ocorreu uma colisão, desencadeando uma ação em ambos os objetos.<return>
* **Reuse, restore, recycle** -  Os pylons serão gerados com recurso à classe pool fornecida pelo LibGDX, evitando assim a constante alocação e destruição de objetos em memória à medida que se avança no nível de jogo, aumentando a eficiência e performance.<return>
* **Be Different** - Utilizado em definir diversos tipos para os aviães, pylons e posições de voo. Permite que estes tipos sejam totalmente dinâmicos, podendo adicionar-se novos sem a necessidade de modificar o código existente.<return>

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
Escolher Avião | Jogo | Game Over
---------------|------|----------
![choose_plane](/images/choose_plane.png)|![game](/images/game.png)|![game_over](/images/game_over.png)
Submeter HighScore |
-------------------|
![submit_highscore](/images/submit_highscore.png)|
