import { createSlice } from "@reduxjs/toolkit";

const initialState = {
  kategorijos: {},
};

const kategorijosSlice = createSlice({
  name: "kategorijos",
  initialState: initialState,
  reducers: {
    add(state, action) {
      action.payload.forEach((item) => {
        state.kategorijos[item.id] = item.pavadinimas;
      });
    },
  },
});

export const kategorijosActions = kategorijosSlice.actions;
export default kategorijosSlice;
