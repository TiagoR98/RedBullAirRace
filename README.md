# RedBullAirRace
Red Bull Air race game based on flappy bird!

# Checkpoint
# Padrões de jogo:
• **Keep ‘em Separated** – Baseado na estrutura do jogo Asteroids fornecido. Permite separar os componentes do jogo e implementar uma arquitetura MVC.
• **One to rule them All** – Presente na classe RedBullGame, Game Controller,Game Model e Game View, de modo a numa instância juntar todos os objetos de cada componente do jogo.
• **Observer** - Implementado entre o avião e o pylon. Permite ao pylon notificar o avião que ocorreu uma colisão, desencadeando uma ação em ambos os objetos.
• **Reuse, restore, recycle** -  Os pylons serão gerados com recurso à classe pool fornecida pelo LibGDX, evitando assim a constante alocação e destruição de objetos em memória à medida que se avança no nível de jogo, aumentando a eficiência e performance.
• **Be Different** – Utilizado em definir diversos tipos para os aviões, pylons e posições de voo. Permite que estes tipos sejam totalmente dinâmicos, podendo adicionar-se novos sem a necessidade de modificar o código existente.
