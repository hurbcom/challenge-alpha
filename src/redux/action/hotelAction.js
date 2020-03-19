import {SET_HOTELS } from '../actionTypes'

export const set_hotels = hotels => {
    return {
        type: SET_HOTELS,
        payload: hotels
    }
}
