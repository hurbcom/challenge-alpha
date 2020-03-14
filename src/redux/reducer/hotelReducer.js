import { SET_HOTELS } from '../actionTypes'

const initialState = {hotels: []}

const reducer = (state = initialState, action) => {
    switch (action.type) {
        case SET_HOTELS:
            return {
                ...state, 
                hotels: action.payload
            }
        default:
            return state
    }
}

export default reducer;