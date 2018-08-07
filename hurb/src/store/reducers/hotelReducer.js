import {
  SET_ALL_HOTEIS,
} from '../actions/actionTypes';

const initialState = {
  hoteis: []
}

const reducer = (state = initialState, action) => {
  switch (action.type) {

    case SET_ALL_HOTEIS:
      return {
        ...state,
        hoteis: action.hoteis
      };
      break;

    default:
      return state;
  }
}

export default reducer;