# PDI

## Ideia 

A ideia do projeto é fazer um aplicativo nativo para Android que seja capaz de autenticar um usuário do Github e listar todos seus repositórios, além de apresentar os detalhes dos projetos ao serem clicados

<br />

## Arquitetura 

A estrturação do projeto é baseada na arquitetura MVVM, seguindo a recomendação da [Equipe de Desenvolvimento Android](https://developer.android.com/jetpack/guide). É possível observar o código fonte do projeto nesse [link](https://github.com/Caio-Dantas/PDI/tree/main/app/src/main/java/com/example/apppdi)

## Detalhes de implementação

O fluxo do aplicativo é dado por:
1. Login com Github
2. Listagem de Repositórios separados em Públicos e Privados
3. Página de detalhes de cada repositório

Dado isso, temos alguns detalhes em cada parte do fluxo que veremos a seguir.

<br />

## Autenticação

Utilizamos o método de autenticação OAuth como sugerido pela [documentação](https://docs.github.com/en/developers/apps/building-oauth-apps/authorizing-oauth-apps) do Github para realizar login com Web Application flow.

<br />

## Listagem de Informações e Imagens de Colaboradores

Na tela de listagem de repositórios podemos observar cards com informações resumidas do repoistório (nome, descrição e linguagem utilizada) e uma listagem com as imagens dos colaboradores. A listagem tanto dos repositórios quanto das imagens é feita de maneira assíncrona e renderizada de forma gradativa na tela conforme as informações são obtidas.

![Página de listagem](https://i.imgur.com/1hOQ9Wh.png)

<br />

## Página de detalhes

Na página de detalhes do repositório é possível obter mais informações sobre o repositório além de visualizar o README correspondente renderizado no aplicativo.

![Detalhes](https://i.imgur.com/QEJSV58.png)

<br />

## Configuração

Para configuração do projeto é necessário adicionar um arquivo ENV ao [diretório apppdi](https://github.com/Caio-Dantas/PDI/tree/main/app/src/main/java/com/example/apppdi). O arquivo deve ser do formato:

```kotlin
package com.example.apppdi

object ENV {

    const val CLIENT_ID = "<CLIENT_ID_KEY>"
    const val CLIENT_SECRET = "<CLIENT_SECRET_KEY>"
    const val CALLBACK_URI = "<CLIENT_CALLBACK_URI>"

}
```
Todas as informações necessárias são obtidas ao registrar o seu app a utilizar a autenticação do github, é possível registrar um app através desse [link](https://github.com/settings/applications/new)

## Executando

Para executar o programa é necessário ter instalado o [Android Studio](https://developer.android.com/studio/install), com isso abra o projeto clonado na IDE, faça o build e o run do projeto em um emulador ou em um dispositivo físico. [Tutorial](https://developer.android.com/training/basics/firstapp/running-app) para executar um projeto no Android Studio. Para que o app seja executado é necessário realizar a etapa de configuração.
