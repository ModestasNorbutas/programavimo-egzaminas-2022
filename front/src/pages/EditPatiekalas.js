import { useEffect, useState } from "react";
import { useParams, useNavigate } from "react-router-dom";
import Container from "../components/UI/Container";
import Navigation from "../components/UI/Navigation";
import axios from "axios";
import apiEndpoint from "../components/Services/apiEndpoint";
import swal from "sweetalert";

export default function EditPatiekalas() {
  const { istaigaID, id } = useParams();
  const navigate = useNavigate();

  const [pavadinimas, setPavadinimas] = useState("");
  const [aprasymas, setAprasymas] = useState("");

  let title = "Patiekalo ID: " + id;
  let buttonText = "Atnaujinti duomenis";
  if (id === "new") {
    title = "Naujas patiekalas";
    buttonText = "Sukurti patiekalą";
  }

  useEffect(() => {
    setPavadinimas("");
    setAprasymas("");
    if (id !== "new") {
      axios
        .get(`${apiEndpoint}/api/patiekalas/${id}`)
        .then((response) => {
          setPavadinimas(response.data.pavadinimas);
          setAprasymas(response.data.aprasymas);
        })
        .catch((error) => {
          swal({
            text: "Įvyko klaida. " + error.response.data,
            button: "Gerai",
          });
        });
    }
  }, [id]);

  const handleCreate = (event) => {
    event.preventDefault();
    const data = {
      id: id === "new" ? null : id,
      pavadinimas: pavadinimas,
      aprasymas: aprasymas,
      maitinimoIstaigaId: istaigaID,
    };

    if (id === "new") {
      axios
        .post(`${apiEndpoint}/api/patiekalas/`, data)
        .then((response) => {
          swal({
            text: response.data,
            button: "Gerai",
          });
        })
        .catch((error) => {
          swal({
            text: "Įvyko klaida. " + error.response.data,
            button: "Gerai",
          });
        });
    } else {
      axios
        .put(`${apiEndpoint}/api/patiekalas/`, data)
        .then((response) => {
          swal({
            text: response.data,
            button: "Gerai",
          });
        })
        .catch((error) => {
          swal({
            text: "Įvyko klaida. " + error.response.data,
            button: "Gerai",
          });
        });
    }
  };

  const handleGoBack = (event) => {
    event.preventDefault();
    navigate("/patiekalai/" + istaigaID);
  };

  return (
    <>
      <Navigation />
      <Container>
        <h3>{title}</h3>
        <form>
          <div className="mb-3">
            <label htmlFor="pavadinimas" className="form-label">
              Pavadinimas
            </label>
            <input
              type="text"
              className="form-control"
              id="pavadinimas"
              name="pavadinimas"
              value={pavadinimas}
              onChange={(e) => setPavadinimas(e.target.value)}
            />
          </div>
          <div className="mb-3">
            <label htmlFor="kodas" className="form-label">
              Aprašymas
            </label>
            <textarea
              className="form-control"
              id="aprasymas"
              name="aprasymas"
              value={aprasymas}
              onChange={(e) => setAprasymas(e.target.value)}
            />
          </div>
          <div className="d-flex">
            <button
              className="btn btn-primary w-100 me-2"
              onClick={(e) => handleGoBack(e)}
            >
              Grįžti
            </button>
            <button
              className="btn btn-success w-100"
              onClick={(e) => handleCreate(e)}
            >
              {buttonText}
            </button>
          </div>
        </form>
      </Container>
    </>
  );
}
