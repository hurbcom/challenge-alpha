import { SET_FAVORITES } from '../actionTypes'

export const set_favorites = favorites => {
    return {
        type: SET_FAVORITES,
        payload: favorites
    }
}