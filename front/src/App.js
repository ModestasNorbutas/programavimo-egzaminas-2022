import React, { useEffect, Suspense } from "react";
import { Routes, Route, Navigate } from "react-router-dom";
import { useSelector, useDispatch } from "react-redux";
import { authActions } from "./store/auth-slice";
import "./App.css";
import axios from "axios";
import swal from "sweetalert";
import apiEndpoint from "./components/Services/apiEndpoint";
import Loading from "./components/UI/Loading";
import Login from "./pages/Login";
import AdminKategorijos from "./pages/AdminKategorijos";
import EditKategorija from "./pages/EditKategorija";
import UserKategorijos from "./pages/UserKategorijos";
import AdminKnygos from "./pages/AdminKnygos";
import EditKnyga from "./pages/EditKnyga";
import UserKnygos from "./pages/UserKnygos";

export default function App() {
  const dispatch = useDispatch();
  const isAuthenticated = useSelector((state) => state.auth.isAuthenticated);
  const role = useSelector((state) => state.auth.role);
  const admin = role === "ADMIN";
  const user = role === "USER";

  useEffect(() => {
    if (isAuthenticated === null) {
      axios
        .get(`${apiEndpoint}/api/loggedUserRole`)
        .then((response) => {
          dispatch(authActions.login({ role: response.data }));
        })
        .catch((error) => {
          const unexpectedError =
            error.response &&
            error.response.status >= 400 &&
            error.response.status < 500;

          if (
            !unexpectedError ||
            (error.response && error.response.status === 404)
          ) {
            swal("Įvyko klaida, puslapis nurodytu adresu nepasiekiamas");
            dispatch(authActions.error());
          } else {
            dispatch(authActions.error(error.response.status));
          }
        });
    }
  }, [isAuthenticated, dispatch]);

  if (isAuthenticated) {
    return (
      <Suspense fallback={<Loading />}>
        <Routes>
          {admin && (
            <>
              <Route path="/home" element={<AdminKategorijos />} />
              <Route path="/knygos" element={<AdminKnygos />} />
              <Route path="/kategorija/:id" element={<EditKategorija />} />
              <Route path="/knyga/:id" element={<EditKnyga />} />
            </>
          )}
          {user && (
            <>
              <Route path="/home" element={<UserKategorijos />} />
              <Route path="/knygos" element={<UserKnygos />} />
            </>
          )}
          <Route path="*" element={<Navigate replace to="/home" />} />
        </Routes>
      </Suspense>
    );
  } else if (isAuthenticated === false) {
    return (
      <Suspense fallback={<Loading />}>
        <Routes>
          <Route path="/login" element={<Login />} />
          <Route path="*" element={<Navigate replace to="/login" />} />
        </Routes>
      </Suspense>
    );
  } else return <Loading />;
}
