# 📱 Projeto IMC

Aplicativo desenvolvido em Kotlin para calcular o Índice de Massa Corporal (IMC) de usuários, fornecendo uma interface simples e intuitiva para entrada de dados e exibição dos resultados.

## 🚀 Funcionalidades

- Entrada de peso (kg) e altura (m) do usuário.
- Cálculo automático do IMC.
- Classificação do IMC com base nas diretrizes da OMS:
  - Abaixo do peso
  - Peso normal
  - Sobrepeso
  - Obesidade grau I, II e III
- Interface amigável e responsiva.

## 🛠️ Tecnologias Utilizadas

- [Kotlin](https://kotlinlang.org/) - Linguagem de programação principal.
- [Android Studio](https://developer.android.com/studio) - Ambiente de desenvolvimento integrado.
- [Gradle](https://gradle.org/) - Sistema de automação de builds.
## 📁 Estrutura do Projeto

Projeto_IMC/
├── .idea/
├── app/
├── gradle/
├── .gitignore
├── build.gradle.kts
├── gradle.properties
├── gradlew
├── gradlew.bat
└── settings.gradle.kts


- `.idea/`: Configurações da IDE (IntelliJ IDEA)
- `app/`: Código-fonte principal do projeto
- `gradle/`: Configurações do Gradle
- `build.gradle.kts`: Script de build com Kotlin DSL
- `settings.gradle.kts`: Configurações de build do projeto

## 📦 Como Executar o Projeto

1. Clone este repositório:
   ```bash
   git clone https://github.com/f-nogueira/Projeto_IMC.git
Abra o projeto em uma IDE compatível com Kotlin (ex: IntelliJ IDEA)

Execute o projeto a partir da IDE ou via terminal com Gradle:

./gradlew build

📌 Observações
Este projeto serve como exemplo educacional e pode ser expandido com funcionalidades adicionais como validação de entrada, interface gráfica, entre outras.

Atualmente, não há interface gráfica (GUI); o projeto pode rodar via terminal, dependendo da implementação em Main.kt.
