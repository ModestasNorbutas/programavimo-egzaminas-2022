import React, { useRef } from "react";
import { NavLink } from "react-router-dom";
import { useSelector } from "react-redux";
import Logout from "./Logout";
import styles from "./Navigation.module.scss";

export default function Navigation() {
  const navButtonRef = useRef();
  const linksRef = useRef();
  const role = useSelector((state) => state.auth.role);
  const admin = role === "ADMIN";

  const collapseNavigation = () => {
    navButtonRef.current.classList.add("collapsed");
    linksRef.current.classList.remove("show");
  };

  return (
    <nav className="navbar navbar-expand-md navbar-dark bg-primary">
      <div className={"container-fluid " + styles.navigation}>
        <NavLink className="navbar-brand" to="/home">
          Egzamino Aplikacija
        </NavLink>
        <button
          ref={navButtonRef}
          className="navbar-toggler"
          type="button"
          data-bs-toggle="collapse"
          data-bs-target="#navbarSupportedContent"
          aria-controls="navbarSupportedContent"
          aria-expanded="false"
          aria-label="Toggle navigation"
        >
          <span className="navbar-toggler-icon"></span>
        </button>
        <div
          ref={linksRef}
          className="collapse navbar-collapse"
          id="navbarSupportedContent"
        >
          <ul className="navbar-nav me-auto mb-2 mb-md-0">
            <li className="nav-item">
              <NavLink
                className="nav-link"
                aria-current="page"
                to="/home"
                onClick={collapseNavigation}
              >
                Kategorijos
              </NavLink>
            </li>
            <li className="nav-item">
              <NavLink
                className="nav-link"
                aria-current="page"
                to="/knygos"
                onClick={collapseNavigation}
              >
                Knygos
              </NavLink>
            </li>
            {admin && (
              <>
                <li className="nav-item">
                  <NavLink
                    className="nav-link"
                    aria-current="page"
                    to="/kategorija/new"
                    onClick={collapseNavigation}
                  >
                    Pridėti kategoriją
                  </NavLink>
                </li>

                <li className="nav-item">
                  <NavLink
                    className="nav-link"
                    aria-current="page"
                    to="/knyga/new"
                    onClick={collapseNavigation}
                  >
                    Pridėti knygą
                  </NavLink>
                </li>
              </>
            )}
          </ul>
          <Logout />
        </div>
      </div>
    </nav>
  );
}
