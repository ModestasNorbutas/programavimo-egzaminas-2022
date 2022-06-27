import { useEffect, useState } from "react";
import { useParams } from "react-router-dom";
import Container from "../components/UI/Container";
import Navigation from "../components/UI/Navigation";
import axios from "axios";
import apiEndpoint from "../components/Services/apiEndpoint";
import swal from "sweetalert";

export default function EditMaitinimoIstaiga() {
  const { id } = useParams();

  const [pavadinimas, setPavadinimas] = useState("");
  const [kodas, setKodas] = useState("");
  const [adresas, setAdresas] = useState("");

  let title = "Maitimo įstaigos ID: " + id;
  let buttonText = "Atnaujinti duomenis";
  if (id === "new") {
    title = "Nauja Maitinimo įstaiga";
    buttonText = "Sukurti įstaigą";
  }

  useEffect(() => {
    setPavadinimas("");
    setKodas("");
    setAdresas("");
    if (id !== "new") {
      axios
        .get(`${apiEndpoint}/api/maitinimoistaiga/${id}`)
        .then((response) => {
          setPavadinimas(response.data.pavadinimas);
          setKodas(response.data.kodas);
          setAdresas(response.data.adresas);
        })
        .catch((error) => {
          swal({
            text: "Įvyko klaida. " + error.response.data,
            button: "Gerai",
          });
        });
    }
  }, [id]);

  const handleClick = (event) => {
    event.preventDefault();
    const data = {
      id: id === "new" ? null : id,
      pavadinimas: pavadinimas,
      kodas: kodas,
      adresas: adresas,
    };

    if (id === "new") {
      axios
        .post(`${apiEndpoint}/api/maitinimoistaiga/`, data)
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
        .put(`${apiEndpoint}/api/maitinimoistaiga/`, data)
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
              Įmonės kodas
            </label>
            <input
              type="text"
              className="form-control"
              id="kodas"
              name="kodas"
              value={kodas}
              onChange={(e) => setKodas(e.target.value)}
            />
          </div>
          <div className="mb-3">
            <label htmlFor="adresas" className="form-label">
              Adresas
            </label>
            <input
              type="text"
              className="form-control"
              id="adresas"
              name="adresas"
              value={adresas}
              onChange={(e) => setAdresas(e.target.value)}
            />
          </div>
          <button
            className="btn btn-primary w-100"
            onClick={(e) => handleClick(e)}
          >
            {buttonText}
          </button>
        </form>
      </Container>
    </>
  );
}
