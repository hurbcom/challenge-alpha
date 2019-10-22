# Desafio Alpha

**Por: Theo Mendes.**

## Instalação

### Prepare o ambiente
Para rodar este projeto você irá precisar do Ruby [Bundler](http://bundler.io), [Carthage](https://github.com/Carthage/Carthage) e [CocoaPods](https://cocoapods.org/)

Carthage pode ser instalado rodando o seguinte comando:

```bash
brew update
brew install carthage
```

O Bundle deve ter vindo junto com a instalação do Ruby. Você precisa do Ruby maior que 2.4. Se você não tem o bundle, pode ser instalado rodando o seguinte comando:

```bash
sudo gem install bundler
```

### Configuração do projeto
1. Na pasta Root do projeto rode
```bash
bundle install
```
2. Rode a instalação do CocoaPods
```bash
bundle exec pod install
```
3. Rode o Carthage
```bash
carthage bootstrap --platform ios --cache-builds --no-use-binaries
```

## Vulnerabilidades
