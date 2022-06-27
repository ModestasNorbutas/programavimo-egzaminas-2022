import { createSlice } from "@reduxjs/toolkit";

const initialIstaigosState = {
  istaigos: {},
};

const istaigosSlice = createSlice({
  name: "istaigos",
  initialState: initialIstaigosState,
  reducers: {
    add(state, action) {
      action.payload.forEach((istaiga) => {
        state.istaigos[istaiga.id] = istaiga.pavadinimas;
      });
    },
  },
});

export const istaigosActions = istaigosSlice.actions;
export default istaigosSlice;
