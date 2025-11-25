# Guia Completo: Java Swing do Zero

## 1. Conceitos Básicos de Java (Revisão)

### Diferenças Python vs Java

| Python | Java |
|--------|------|
| `def funcao():` | `public void funcao() {}` |
| Tipagem dinâmica | Tipagem estática: `String nome`, `int idade` |
| Sem ponto e vírgula | Termina com `;` |
| `self` | `this` |
| `__init__` | Construtor com nome da classe |
| Indentação obrigatória | Chaves `{}` obrigatórias |

### Estrutura Básica de uma Classe Java

```java
public class MinhaClasse {
    // Atributos (variáveis da classe)
    private String nome;
    private int idade;
    
    // Construtor (equivalente ao __init__)
    public MinhaClasse(String nome, int idade) {
        this.nome = nome;  // this = self em Python
        this.idade = idade;
    }
    
    // Métodos getters e setters
    public String getNome() {
        return nome;
    }
    
    public void setNome(String nome) {
        this.nome = nome;
    }
}
```

---

## 2. Introdução ao Swing

**Swing** é a biblioteca gráfica do Java (equivalente ao Tkinter do Python, mas mais poderoso).

### Componentes Principais

- **JFrame**: Janela principal (container)
- **JPanel**: Painel para organizar componentes
- **JButton**: Botão
- **JTextField**: Campo de texto (input)
- **JLabel**: Rótulo (texto estático)
- **JOptionPane**: Diálogos (alerts)

---

## 3. Criando sua Primeira Janela

### Passo 1: Estrutura Básica

```java
import javax.swing.*;

public class MinhaJanela extends JFrame {
    
    // Construtor
    public MinhaJanela() {
        setTitle("Minha Primeira Janela");
        setSize(400, 300);  // largura, altura
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);  // centraliza na tela
        setVisible(true);  // torna visível
    }
    
    public static void main(String[] args) {
        new MinhaJanela();
    }
}
```

### Passo 2: Adicionando Componentes

```java
import javax.swing.*;
import java.awt.*;

public class MinhaJanela extends JFrame {
    
    private JLabel lblNome;
    private JTextField txtNome;
    private JButton btnSalvar;
    
    public MinhaJanela() {
        // Criar componentes
        lblNome = new JLabel("Nome:");
        txtNome = new JTextField(20);  // 20 colunas
        btnSalvar = new JButton("Salvar");
        
        // Criar painel
        JPanel painel = new JPanel();
        painel.add(lblNome);
        painel.add(txtNome);
        painel.add(btnSalvar);
        
        // Adicionar painel à janela
        add(painel);
        
        // Configurar janela
        setTitle("Exemplo Simples");
        setSize(400, 150);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }
    
    public static void main(String[] args) {
        new MinhaJanela();
    }
}
```

---

## 4. Layouts (Gerenciadores de Layout)

Layouts controlam como os componentes são organizados.

### FlowLayout (Padrão do JPanel)
Componentes em sequência, da esquerda para direita.

```java
JPanel painel = new JPanel(new FlowLayout());
painel.add(new JButton("Botão 1"));
painel.add(new JButton("Botão 2"));
```

### GridLayout
Grade com linhas e colunas.

```java
JPanel painel = new JPanel(new GridLayout(3, 2));  // 3 linhas, 2 colunas
painel.add(new JLabel("Nome:"));
painel.add(new JTextField());
painel.add(new JLabel("Idade:"));
painel.add(new JTextField());
```

### BorderLayout (Padrão do JFrame)
Divide em 5 áreas: NORTH, SOUTH, EAST, WEST, CENTER.

```java
add(new JButton("Norte"), BorderLayout.NORTH);
add(new JButton("Sul"), BorderLayout.SOUTH);
add(new JButton("Centro"), BorderLayout.CENTER);
```

### GridBagLayout (Mais Complexo e Flexível)
Usado no seu código! Permite controle preciso.

```java
JPanel painel = new JPanel(new GridBagLayout());
GridBagConstraints gbc = new GridBagConstraints();

gbc.gridx = 0;  // coluna
gbc.gridy = 0;  // linha
gbc.insets = new Insets(5, 5, 5, 5);  // margem
painel.add(new JLabel("Nome:"), gbc);

gbc.gridx = 1;
gbc.gridy = 0;
painel.add(new JTextField(20), gbc);
```

---

## 5. Eventos (Actions)

Em Java, usamos **ActionListener** para capturar eventos de botões (equivalente ao `command` do Tkinter).

### Método 1: Classe Interna Anônima

```java
btnSalvar.addActionListener(new ActionListener() {
    @Override
    public void actionPerformed(ActionEvent e) {
        String nome = txtNome.getText();
        JOptionPane.showMessageDialog(null, "Olá, " + nome + "!");
    }
});
```

### Método 2: Lambda (Java 8+) - Mais Simples

```java
btnSalvar.addActionListener(e -> {
    String nome = txtNome.getText();
    JOptionPane.showMessageDialog(null, "Olá, " + nome + "!");
});
```

---

## 6. Validação e Exceções

### Criando uma Exceção Customizada

```java
public class ValidacaoException extends Exception {
    public ValidacaoException(String mensagem) {
        super(mensagem);
    }
}
```

### Usando Try-Catch

```java
btnSalvar.addActionListener(e -> {
    try {
        String nome = txtNome.getText();
        
        if (nome.isEmpty()) {
            throw new ValidacaoException("Nome não pode ser vazio!");
        }
        
        JOptionPane.showMessageDialog(this, "Salvo com sucesso!");
        
    } catch (ValidacaoException ex) {
        JOptionPane.showMessageDialog(this, 
            ex.getMessage(), 
            "Erro", 
            JOptionPane.ERROR_MESSAGE);
    }
});
```

---

## 7. Orientação a Objetos - Herança

### Classe Abstrata (Base)

```java
public abstract class Pessoa {
    private String nome;
    private int idade;
    
    public Pessoa(String nome, int idade) {
        this.nome = nome;
        this.idade = idade;
    }
    
    // Método abstrato (deve ser implementado pelas subclasses)
    public abstract String getTipo();
    
    public String getNome() {
        return nome;
    }
}
```

### Classe Filha

```java
public class Aluno extends Pessoa {
    private String matricula;
    
    public Aluno(String nome, int idade, String matricula) {
        super(nome, idade);  // chama construtor da classe pai
        this.matricula = matricula;
    }
    
    @Override
    public String getTipo() {
        return "Aluno";
    }
}
```

---

## 8. Exemplo Completo: Cadastro de Pessoa

```java
import javax.swing.*;
import java.awt.*;

public class CadastroPessoa extends JFrame {
    
    private JTextField txtNome;
    private JTextField txtIdade;
    private JButton btnSalvar;
    private JButton btnLimpar;
    
    public CadastroPessoa() {
        inicializarComponentes();
        configurarJanela();
    }
    
    private void inicializarComponentes() {
        // Criar componentes
        JLabel lblNome = new JLabel("Nome:");
        JLabel lblIdade = new JLabel("Idade:");
        
        txtNome = new JTextField(20);
        txtIdade = new JTextField(10);
        
        btnSalvar = new JButton("Salvar");
        btnLimpar = new JButton("Limpar");
        
        // Criar painel com GridBagLayout
        JPanel painel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        
        // Linha 0
        gbc.gridx = 0; gbc.gridy = 0;
        painel.add(lblNome, gbc);
        gbc.gridx = 1;
        painel.add(txtNome, gbc);
        
        // Linha 1
        gbc.gridx = 0; gbc.gridy = 1;
        painel.add(lblIdade, gbc);
        gbc.gridx = 1;
        painel.add(txtIdade, gbc);
        
        // Linha 2 - Botões
        JPanel painelBotoes = new JPanel();
        painelBotoes.add(btnSalvar);
        painelBotoes.add(btnLimpar);
        
        gbc.gridx = 0; gbc.gridy = 2;
        gbc.gridwidth = 2;
        painel.add(painelBotoes, gbc);
        
        add(painel);
        
        // Configurar eventos
        configurarEventos();
    }
    
    private void configurarEventos() {
        btnSalvar.addActionListener(e -> salvar());
        btnLimpar.addActionListener(e -> limpar());
    }
    
    private void salvar() {
        try {
            String nome = txtNome.getText();
            String idadeTexto = txtIdade.getText();
            
            if (nome.isEmpty()) {
                throw new Exception("Nome não pode ser vazio!");
            }
            
            int idade = Integer.parseInt(idadeTexto);
            
            if (idade < 0 || idade > 150) {
                throw new Exception("Idade inválida!");
            }
            
            JOptionPane.showMessageDialog(this,
                "Cadastrado: " + nome + ", " + idade + " anos",
                "Sucesso",
                JOptionPane.INFORMATION_MESSAGE);
            
            limpar();
            
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this,
                "Idade deve ser um número!",
                "Erro",
                JOptionPane.ERROR_MESSAGE);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this,
                ex.getMessage(),
                "Erro",
                JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void limpar() {
        txtNome.setText("");
        txtIdade.setText("");
        txtNome.requestFocus();
    }
    
    private void configurarJanela() {
        setTitle("Cadastro de Pessoa");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
    }
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new CadastroPessoa().setVisible(true);
        });
    }
}
```

---

## 9. WindowBuilder (Eclipse Plugin)

**WindowBuilder** é uma ferramenta visual para criar interfaces (drag-and-drop).

### Como Instalar no Eclipse:

1. Help → Eclipse Marketplace
2. Buscar "WindowBuilder"
3. Instalar e reiniciar

### Como Usar:

1. Botão direito no arquivo `.java` → Open With → WindowBuilder Editor
2. Arraste componentes da paleta
3. Configure propriedades no painel direito
4. O código é gerado automaticamente!

### Vantagens:
- Visual e rápido
- Gera código limpo
- Fácil para protótipos

### Desvantagens:
- Pode gerar código complexo
- Importante entender o código gerado

---

## 10. Boas Práticas

### Organização do Código

```
src/
├── Main.java (ponto de entrada)
├── model/ (classes de dados)
│   ├── Pessoa.java
│   └── Aluno.java
├── view/ (interfaces gráficas)
│   ├── JanelaPrincipal.java
│   └── JanelaCadastro.java
└── exception/ (exceções customizadas)
    └── ValidacaoException.java
```

### Convenções de Nomes

- **Classes**: `PascalCase` (ex: `MinhaClasse`)
- **Métodos**: `camelCase` (ex: `meuMetodo`)
- **Variáveis**: `camelCase` (ex: `minhaVariavel`)
- **Constantes**: `MAIUSCULA_COM_UNDERSCORE` (ex: `MAX_VALOR`)
- **Componentes Swing**: prefixo indicando tipo
  - `btn` para JButton (ex: `btnSalvar`)
  - `txt` para JTextField (ex: `txtNome`)
  - `lbl` para JLabel (ex: `lblTitulo`)

### Separação de Responsabilidades

```java
// ❌ EVITE: tudo em um método
public void salvar() {
    // validação + lógica + interface misturados
}

// ✅ BOM: separar responsabilidades
private void salvar() {
    try {
        validarCampos();
        Pessoa pessoa = criarPessoa();
        salvarPessoa(pessoa);
        exibirSucesso();
    } catch (Exception ex) {
        exibirErro(ex);
    }
}
```

---

## 11. Próximos Passos

1. **Praticar os exemplos** acima
2. **Modificar o código** que você recebeu
3. **Criar novos formulários** do zero
4. **Aprender sobre**:
   - JTable (tabelas)
   - JComboBox (dropdown)
   - Banco de dados (JDBC)
   - Padrão MVC

---

## 12. Recursos para Estudar

- **Documentação Oracle**: docs.oracle.com/javase/tutorial/uiswing/
- **YouTube**: busque "Java Swing tutorial"
- **Prática**: recrie aplicações simples (calculadora, agenda, to-do list)

---

## Resumo Comparativo Python x Java Swing

| Recurso | Python (Tkinter) | Java (Swing) |
|---------|------------------|--------------|
| Janela | `Tk()` | `JFrame` |
| Botão | `Button()` | `JButton` |
| Input | `Entry()` | `JTextField` |
| Label | `Label()` | `JLabel` |
| Evento | `command=funcao` | `addActionListener()` |
| Alert | `messagebox.showinfo()` | `JOptionPane.showMessageDialog()` |

---

**Dica Final**: Comece simples! Crie uma janela básica, adicione um botão, faça ele mostrar um alert. Depois vá adicionando complexidade aos poucos. 🚀