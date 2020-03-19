import { SET_FAVORITES } from '../actionTypes'

const initialState = {favorites: []}

const reducer = (state = initialState, action) => {
    switch (action.type) {
        case SET_FAVORITES:
            return {
                ...state, 
                favorites: action.payload
            }
        default:
            return state
    }
}

export default reducer;