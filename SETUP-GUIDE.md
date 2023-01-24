# SETUP GUIDE

**Name**: Fabio Akira Takahashi

**Developer Github**: <https://github.com/akirafabio>

**Original Challenge URL**: <https://github.com/hurbcom/challenge-alpha>

---

## Configuração Inicial do Projeto

O projeto está na pasta HurbChallengeApp e para initializar o projeto é necessário ter o **Xcodegen** instalado na maquina.

Tutoriais com formas de instalar com XcodeGen
(<https://github.com/yonaskolb/XcodeGen>)

Rodar o comando abaixo dentro da pasta HurbChallengeApp e criar o arquivo HurbApp.xcodeproj :

```
xcodegen generate
```

---

## Ferramentas Externas Utilizadas

### XcodeGen (<https://github.com/yonaskolb/XcodeGen>)
Decidi utlizar a ferramente XcodeGen como facilitador nos conflitor de merge que ocorrem dentro do arquivo **project.pbxproj**, fazendo com que não seja necessário que o revisor de um PR consiga analisar o código que foi desenvolvido
sem a necessidades de impactar as configurações de referencia de arquivo do projeto, e podendo centralizar em um arquivo
mais legível quando houver a necessidade de ocorrer uma alteração de configuração do projeto.

### Kingfisher (<https://github.com/onevcat/Kingfisher>)
Como o projeto possui bastante interação com imagens (carregamento e exibição), acabei trazendo uma lib externa para poder 
realizar um gerenciamento tanto do carregamento quanto de alocação em memoria das imagens, facilitando e podendo realizar
uma configuração de forma mais rápida com as necessidades que o projeto demanda.
