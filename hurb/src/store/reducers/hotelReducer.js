import {
  SET_ALL_HOTELS,
} from '../actions/actionTypes';

const initialState = {
  hotels: []
}

const reducer = (state = initialState, action) => {
  switch (action.type) {

    case SET_ALL_HOTELS:
      return {
        ...state,
        hotels: action.hotels
      };
      break;

    default:
      return state;
  }
}

export default reducer;