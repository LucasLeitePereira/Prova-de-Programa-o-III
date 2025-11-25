# Raciocínio para Desenvolver a Aplicação

Vou te mostrar o passo a passo mental que você deve seguir para resolver esse tipo de problema.

---

## 1. Ler e Destacar o Enunciado

Primeiro, **grife as informações importantes**:

```
Imobiliária:
├── cnpj → String 18 caracteres → formato 99.999.999/0009-99
└── nome → String até 20 caracteres → apenas letras e espaços

Imóvel (ABSTRATA):
├── endereço → String 30 caracteres → letras, números, vírgula, traço
├── cep → String 9 caracteres → formato 99999-999
└── área → inteiro > 0

Casa (herda Imóvel):
└── numCômodos → inteiro > 0

Loja (herda Imóvel):
└── numPortas → inteiro > 0
```

---

## 2. Identificar as Classes Necessárias

Pergunte-se: **"Quais são os 'substantivos' do problema?"**

| Substantivo | Tipo de Classe |
|-------------|----------------|
| Imobiliária | Classe de dados (Model) |
| Imóvel | Classe abstrata (Model) |
| Casa | Classe de dados (Model) |
| Loja | Classe de dados (Model) |
| Janelas | Classes de interface (View) |
| Erros de validação | Classe de exceção |

---

## 3. Identificar os Relacionamentos

Pergunte-se: **"Quem herda de quem?"**

```
Casa  ──extends──▶ Imóvel
Loja  ──extends──▶ Imóvel
```

**Dica:** Palavras como "é um(a)" indicam herança:
- Casa **é um** Imóvel ✓
- Loja **é um** Imóvel ✓

---

## 4. Montar a Estrutura MVC

Pergunte-se: **"Onde cada classe se encaixa?"**

```
src/
├── model/      ← Classes que guardam DADOS
├── view/       ← Classes que mostram TELAS
├── exception/  ← Classes de ERRO
└── Main.java   ← Ponto de entrada
```

---

## 5. Começar Pelo Mais Simples

**Ordem recomendada de codificação:**

```
1º → ValidacaoException (é a mais simples)
2º → Imobiliaria (não tem herança)
3º → Imovel (classe pai abstrata)
4º → Casa e Loja (herdam de Imovel)
5º → JanelaPrincipal (tela simples com botões)
6º → Janelas de cadastro (formulários)
7º → Main (apenas inicializa)
```

---

## 6. Para Cada Classe de Dados, Siga Este Padrão

```java
public class NomeDaClasse {
    
    // 1. ATRIBUTOS (private)
    private String atributo;
    
    // 2. CONSTRUTORES
    public NomeDaClasse() { }
    
    public NomeDaClasse(String atributo) throws ValidacaoException {
        setAtributo(atributo);  // usa o setter para validar
    }
    
    // 3. GETTERS
    public String getAtributo() {
        return atributo;
    }
    
    // 4. SETTERS (com validação)
    public void setAtributo(String atributo) throws ValidacaoException {
        validarAtributo(atributo);  // valida antes de atribuir
        this.atributo = atributo;
    }
    
    // 5. MÉTODOS DE VALIDAÇÃO (private)
    private void validarAtributo(String atributo) throws ValidacaoException {
        if (/* condição de erro */) {
            throw new ValidacaoException("Mensagem de erro");
        }
    }
    
    // 6. toString()
    @Override
    public String toString() {
        return "Classe [atributo=" + atributo + "]";
    }
}
```

---

## 7. Para Validações com Formato, Use Regex

Pergunte-se: **"Qual é o padrão?"**

| Formato | Análise | Regex |
|---------|---------|-------|
| `99.999.999/0009-99` | dígito dígito . dígito dígito dígito ... | `\\d{2}\\.\\d{3}\\.\\d{3}/\\d{4}-\\d{2}` |
| `99999-999` | 5 dígitos - 3 dígitos | `\\d{5}-\\d{3}` |

**Legenda Regex:**
- `\\d` = um dígito (0-9)
- `\\d{5}` = exatamente 5 dígitos
- `\\.` = ponto literal
- `\\-` = traço literal

---

## 8. Para Janelas Swing, Siga Este Padrão

```java
public class MinhaJanela extends JFrame {
    
    // 1. DECLARAR COMPONENTES
    private JTextField txtCampo;
    private JButton btnAcao;
    
    // 2. CONSTRUTOR
    public MinhaJanela() {
        inicializarComponentes();
        configurarJanela();
    }
    
    // 3. CRIAR E POSICIONAR COMPONENTES
    private void inicializarComponentes() {
        // criar componentes
        // adicionar ao painel
        // configurar eventos
    }
    
    // 4. CONFIGURAR JANELA
    private void configurarJanela() {
        setTitle("Título");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
    }
    
    // 5. MÉTODOS DE AÇÃO
    private void salvar() {
        try {
            // criar objeto
            // mostrar sucesso
        } catch (ValidacaoException e) {
            // mostrar erro
        }
    }
}
```

---

## 9. Fluxograma Mental

```
┌─────────────────────────────────────────────────────────┐
│                    LER ENUNCIADO                        │
└─────────────────────┬───────────────────────────────────┘
                      ▼
┌─────────────────────────────────────────────────────────┐
│  Quais são as ENTIDADES (classes de dados)?             │
│  → Imobiliária, Imóvel, Casa, Loja                      │
└─────────────────────┬───────────────────────────────────┘
                      ▼
┌─────────────────────────────────────────────────────────┐
│  Existe HERANÇA?                                        │
│  → Sim: Casa e Loja herdam de Imóvel                    │
└─────────────────────┬───────────────────────────────────┘
                      ▼
┌─────────────────────────────────────────────────────────┐
│  Quais são os ATRIBUTOS de cada classe?                 │
│  → Listar todos com seus tipos e regras                 │
└─────────────────────┬───────────────────────────────────┘
                      ▼
┌─────────────────────────────────────────────────────────┐
│  Quais são as VALIDAÇÕES necessárias?                   │
│  → Criar regex para formatos                            │
│  → Verificar limites (> 0, máx caracteres)              │
└─────────────────────┬───────────────────────────────────┘
                      ▼
┌─────────────────────────────────────────────────────────┐
│  Quantas TELAS preciso?                                 │
│  → 1 principal + 1 para cada cadastro                   │
└─────────────────────┬───────────────────────────────────┘
                      ▼
┌─────────────────────────────────────────────────────────┐
│                    CODIFICAR                            │
└─────────────────────────────────────────────────────────┘
```

---

## 10. Checklist Final

Antes de entregar, verifique:

- [ ] Todas as classes têm `@author`?
- [ ] Estrutura MVC está correta (pastas separadas)?
- [ ] Herança implementada (`extends`)?
- [ ] Classe abstrata tem `abstract`?
- [ ] Todos os setters validam?
- [ ] Validações lançam exceção com mensagem clara?
- [ ] Janelas capturam exceção e mostram erro?
- [ ] Código está indentado?
- [ ] Sem erros no Eclipse?

---

## Resumo: As 3 Perguntas-Chave

| Pergunta | Te ajuda a identificar |
|----------|------------------------|
| **"Quais são os substantivos?"** | Classes de dados |
| **"Quem é um tipo de quem?"** | Herança |
| **"Quais são as regras?"** | Validações |

Com esse raciocínio estruturado, você consegue resolver qualquer exercício similar! 🎯
