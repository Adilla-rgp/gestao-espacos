<h1>Sistema de Gerenciamento de Reservas</h1>
  <p>Este é um sistema desktop simples, desenvolvido em Java, para gerenciar reservas de espaços. Ele possui dois fluxos principais de acesso: <strong>usuário comum</strong> e <strong>administrador</strong>, cada um com suas próprias funcionalidades.</p>

  <h2>Tecnologias</h2>
  <ul>
    <li>Java (JDK 8 ou superior)</li>
    <li>Interface gráfica com Java Swing</li>
    <li>Arquitetura MVC (Model-View-Controller)</li>
  </ul>

  <h2>Estrutura do Projeto</h2>
  <pre><code>.
├── src/
│   ├── app/                  # Classe principal (Main.java)
│   ├── controller/           # Controladores do fluxo do sistema
│   ├── model/                # Modelos de dados (Usuario, Reserva, Espaço etc.)
│   └── view/                 # Telas gráficas (Swing)
├── bin/                      # Arquivos .class compilados
└── README.md
</code></pre>

  <h2>Como executar</h2>

  <h3>1. Compilar o projeto</h3>
  <p>No terminal, navegue até o diretório do projeto e execute:</p>
  <pre><code>javac -d bin src/app/Main.java src/controller/*.java src/model/*.java src/view/*.java</code></pre>
  <p>Esse comando compila todas as classes e as coloca na pasta <code>bin</code>.</p>

  <h3>2. Executar o projeto</h3>
  <p>Após a compilação, execute o projeto com:</p>
  <pre><code>java -cp bin app.Main</code></pre>

  <h2>Fluxo de Acesso</h2>

  <h3>Usuário Comum:</h3>
  <ul>
    <li>Login</li>
    <li>Dashboard</li>
    <li>Nova Reserva → Sistema de Reservas → Minhas Reservas</li>
    <li>Minhas Reservas</li>
  </ul>

  <h3>Administrador:</h3>
  <ul>
    <li>Login</li>
    <li>Dashboard</li>
    <li>Gerenciamento de Espaços ↔ Cadastro de Espaços</li>
    <li>Gerenciamento de Usuários</li>
    <li>Relatórios</li>
    <li>Todas as Reservas</li>
  </ul>

  <h2>Observações</h2>
  <ul>
    <li>O projeto é de uso educacional, voltado para a prática de Java e MVC.</li>
    <li>Não há persistência em banco de dados — os dados são temporários e armazenados em memória.</li>
  </ul>

  <h2>Licença</h2>
  <p>Este projeto é livre para uso acadêmico.</p>

</body>
</html>
