import { createSlice } from "@reduxjs/toolkit";

const initialCartState = JSON.parse(localStorage.getItem("userCart")) || {
  cart: {},
  count: 0,
};

const cartSlice = createSlice({
  name: "cart",
  initialState: initialCartState,
  reducers: {
    add(state, action) {
      state.count++;
      if (state.cart[action.payload.id]) {
        state.cart[action.payload.id].kiekis++;
      } else {
        state.cart[action.payload.id] = {
          id: action.payload.id,
          pavadinimas: action.payload.pavadinimas,
          aprasymas: action.payload.aprasymas,
          istaigosPavadinimas: action.payload.maitinimoIstaiga.pavadinimas,
          kiekis: 1,
        };
      }
      localStorage.setItem("userCart", JSON.stringify(state));
    },
    remove(state, action) {
      state.count--;
      if (state.cart[action.payload.id].kiekis > 1) {
        state.cart[action.payload.id].kiekis--;
      } else {
        delete state.cart[action.payload.id];
      }
      localStorage.setItem("userCart", JSON.stringify(state));
    },
    clear(state) {
      console.log("veikia");
      state.cart = {};
      state.count = 0;
      localStorage.setItem("userCart", JSON.stringify(state));
    },
  },
});

export const cartActions = cartSlice.actions;
export default cartSlice;
