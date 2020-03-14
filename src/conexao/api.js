import linkapi from './linkapi'

const _api = async (_url, _method, _body) => {
    const response = await fetch(linkapi + _url, { 
        method: _method, 
        headers: {'Content-Type': 'application/json'},
        body: JSON.stringify(_body)
      })
    const json = await response.json()
    return json
  }

/**
 * Faz uma chamada na API
 */
export const api = {
    /**
     * Retorna uma Promisse de GET
     * @param {String} url Endereço do endpoint (Não inicie com "/" )
     * @param {JSON} body 
     */
    get: (url, body)=>_api(url,'GET',body),

    /**
     * Retorna uma Promisse de POST
     * @param {String} url
     * @param {JSON} body 
     */
    post: (url, body)=>_api(url,'POST',body),

     /**
     * Retorna uma Promisse de PUT
     * @param {String} url
     * @param {JSON} body 
     */
    put: (url, body)=>_api(url,'PUT',body),

    /**
     * Retorna uma Promisse de DELETE
     * @param {String} url
     * @param {JSON} body 
     */
    delete: (url, body)=>_api(url,'DELETE',body),
}