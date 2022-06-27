import Container from "../components/UI/Container";
import { useNavigate } from "react-router-dom";
import { useEffect, useState } from "react";
import { useDispatch } from "react-redux";
import { istaigosActions } from "../store/istaigos-slice";
import axios from "axios";
import apiEndpoint from "../components/Services/apiEndpoint";
import swal from "sweetalert";
import Loading from "../components/UI/Loading";

export default function UserHome() {
  const navigate = useNavigate();
  const dispatch = useDispatch();
  const [istaigos, setIstaigos] = useState();

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
  }, [dispatch]);

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
              <th>Pavadinimas</th>
              <th>Adresas</th>
              <th></th>
            </tr>
          </thead>
          <tbody>
            {istaigos.map((istaiga) => (
              <tr key={istaiga.id}>
                <td>{istaiga.pavadinimas}</td>
                <td>{istaiga.adresas}</td>
                <td>
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
    </Container>
  );
}
