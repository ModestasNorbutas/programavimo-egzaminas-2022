import Container from "../components/UI/Container";
import { useNavigate } from "react-router-dom";
import { useEffect, useState } from "react";
import { useDispatch } from "react-redux";
import { istaigosActions } from "../store/istaigos-slice";
import axios from "axios";
import apiEndpoint from "../components/Services/apiEndpoint";
import swal from "sweetalert";
import Loading from "../components/UI/Loading";

export default function AdminHome() {
  const navigate = useNavigate();
  const dispatch = useDispatch();
  const [istaigos, setIstaigos] = useState();
  const [refresh, setRefresh] = useState(false);

  const handleCreate = () => {
    navigate("/maitinimoistaiga/new");
  };

  useEffect(() => {
    axios
      .get(`${apiEndpoint}/api/maitinimoistaiga/`)
      .then((response) => {
        setIstaigos(response.data);
        dispatch(istaigosActions.add(response.data));
      })
      .catch((error) => {
        swal({
          text: "Įvyko klaida. " + error.response.data,
          button: "Gerai",
        });
      });
  }, [refresh, dispatch]);

  const handleTrinti = (id) => {
    axios
      .delete(`${apiEndpoint}/api/maitinimoistaiga/${id}`)
      .then((response) => {
        swal({
          text: response.data,
          button: "Gerai",
        });
      })
      .then(() => {
        setRefresh((prevState) => !prevState);
      })
      .catch((error) => {
        swal({
          text: "Įvyko klaida. " + error.response.data,
          button: "Gerai",
        });
      });
  };

  const handleRedaguoti = (id) => {
    navigate("/maitinimoistaiga/" + id);
  };

  const handleMeniu = (id) => {
    navigate("/patiekalai/" + id);
  };

  return (
    <Container>
      {!istaigos && <Loading />}
      {istaigos && (
        <table className="table">
          <thead>
            <tr>
              <th>ID</th>
              <th>Pavadinimas</th>
              <th>Įmonės kodas</th>
              <th>Adresas</th>
              <th>Veiksmai</th>
            </tr>
          </thead>
          <tbody>
            {istaigos.map((istaiga) => (
              <tr key={istaiga.id}>
                <td>{istaiga.id}</td>
                <td>{istaiga.pavadinimas}</td>
                <td>{istaiga.kodas}</td>
                <td>{istaiga.adresas}</td>
                <td>
                  <button
                    className="btn btn-danger me-2"
                    onClick={() => handleTrinti(istaiga.id)}
                  >
                    Trinti
                  </button>
                  <button
                    className="btn btn-secondary me-2"
                    onClick={() => handleRedaguoti(istaiga.id)}
                  >
                    Redaguoti
                  </button>
                  <button
                    className="btn btn-primary"
                    onClick={() => handleMeniu(istaiga.id)}
                  >
                    Patiekalai
                  </button>
                </td>
              </tr>
            ))}
          </tbody>
        </table>
      )}
      <button className="btn btn-primary w-100" onClick={handleCreate}>
        Pridėti maitinimo įstaigą
      </button>
    </Container>
  );
}
