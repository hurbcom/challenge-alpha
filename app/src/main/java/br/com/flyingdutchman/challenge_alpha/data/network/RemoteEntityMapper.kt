package br.com.flyingdutchman.challenge_alpha.data.network

interface RemoteEntityMapper<M, E> {
    fun mapFromRemote(type: M): E
}