# Comparação de Desempenho: Modelos de Threads N:M vs 1:1

## Descrição do Projeto

Este projeto implementa e compara dois modelos de execução de threads:
- **Modelo 1:1**: Cada thread de usuário mapeia diretamente para uma thread do sistema operacional
- **Modelo N:M**: N threads de usuário são multiplexadas em M threads do SO (pool fixo de 8 threads)

## Tarefa Computacional

Cada thread executa:
1. Cálculos matemáticos (1000 iterações de operações com sqrt e sin)
2. Sleep de 10ms para simular operações de I/O

## Ambiente de Teste

- **Sistema Operacional**: Linux Ubuntu 24.04
- **Processador**: Intel(R) Core(TM) i5-8250U CPU @ 1.60GHz
- **Memória RAM**: 8GB DDR4
- **Versão Java**: OpenJDK-24

## Resultados dos Testes

### Tabela de Tempos de Execução (em milissegundos)

| Quantidade de Threads | Modelo 1:1 | Modelo N:M (M=8) | Diferença |
|-----------------------|------------|------------------|-----------|
| 10                    |            |                  |           |
| 100                   |            |                  |           |
| 500                   |            |                  |           |
| 1000                  |            |                  |           |

### Exemplo de Saída

```
=== MODELO 1:1 ===
Threads: 10 | Tempo: XXX ms
Threads: 100 | Tempo: XXX ms
Threads: 500 | Tempo: XXX ms
Threads: 1000 | Tempo: XXX ms

=== MODELO N:M ===
Threads: 10 | Tempo: XXX ms
Threads: 100 | Tempo: XXX ms
Threads: 500 | Tempo: XXX ms
Threads: 1000 | Tempo: XXX ms
```

## Compilação e Execução

```bash
javac OneToOneModel.java
java OneToOneModel

javac NToMModel.java
java NToMModel
```

## Análise dos Resultados

### Modelo 1:1
**Vantagens:**
- Execução verdadeiramente paralela em sistemas multi-core
- Melhor aproveitamento de múltiplos processadores

**Desvantagens:**
- Overhead na criação de muitas threads do SO
- Maior consumo de recursos do sistema

### Modelo N:M
**Vantagens:**
- Menor overhead na criação de threads
- Controle fino sobre o número de threads do sistema
- Mais eficiente para grande quantidade de tarefas

**Desvantagens:**
- Limitado pelo tamanho do pool
- Pode haver espera na fila quando N >> M

### Conclusões

===============================================
RESULTADOS COMPARATIVOS
===============================================

Threads    | Modelo 1:1   | Modelo N:M   | Diferenca    | Mais Rapido
---------------------------------------------------------------------
10         | 16           | 22           | 6            | 1:1       
100        | 36           | 133          | 97           | 1:1       
500        | 120          | 643          | 523          | 1:1       
1000       | 126          | 1289         | 1163         | 1:1

===============================================

## Autores

- Afonso Henrique de Christo Muller
- Mateus Natan Maoski

## Data de Entrega

[Data]