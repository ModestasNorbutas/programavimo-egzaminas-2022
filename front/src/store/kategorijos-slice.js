import { createSlice } from "@reduxjs/toolkit";

const initialState = JSON.parse(localStorage.getItem("kategorijos")) || {
  kategorijos: [],
};

const kategorijosSlice = createSlice({
  name: "kategorijos",
  initialState: initialState,
  reducers: {
    add(state, action) {
      state.kategorijos = action.payload;
      localStorage.setItem("kategorijos", JSON.stringify(state));
    },
  },
});

export const kategorijosActions = kategorijosSlice.actions;
export default kategorijosSlice;
