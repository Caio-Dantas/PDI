# PDI

## Ideia 

A ideia do projeto é fazer um aplicativo nativo para Android que seja capaz de autenticar um usuário do Github e listar todos seus repositórios, além de apresentar os detalhes dos projetos ao serem clicados

<br />

## Arquitetura 

A estrturação do projeto é baseada na arquitetura MVVM, seguindo a recomendação da [Equipe de Desenvolvimento Android](https://developer.android.com/jetpack/guide). É possível observar o código fonte do projeto nesse [link](https://github.com/Caio-Dantas/PDI/tree/main/app/src/main/java/com/example/apppdi)

<br />

## Autenticação

Utilizamos o método de autenticação OAuth como sugerido pela [documentação](https://docs.github.com/en/developers/apps/building-oauth-apps/authorizing-oauth-apps) do Github para realizar login com Web Application flow.

<br />

## Configuração

Para configuração do projeto é necessário adicionar um arquivo ENV.kt ao [diretório apppdi](https://github.com/Caio-Dantas/PDI/tree/main/app/src/main/java/com/example/apppdi). O arquivo deve ser do formato:

```kotlin
package com.example.apppdi

object ENV {

    const val CLIENT_ID = "<CLIENT_ID_KEY>"
    const val CLIENT_SECRET = "<CLIENT_SECRET_KEY>"
    const val CALLBACK_URI = "<CLIENT_CALLBACK_URI>"

}
```
Todas as informações necessárias são obtidas ao registrar o seu app a utilizar a autenticação do github, é possível registrar um app através desse [link](https://github.com/settings/applications/new)
