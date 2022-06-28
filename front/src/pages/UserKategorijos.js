import Container from "../components/UI/Container";
import { useNavigate } from "react-router-dom";
import { useEffect, useState } from "react";
import { useDispatch } from "react-redux";
import { kategorijosActions } from "../store/kategorijos-slice";
import axios from "axios";
import apiEndpoint from "../components/Services/apiEndpoint";
import swal from "sweetalert";
import Loading from "../components/UI/Loading";
import Navigation from "../components/UI/Navigation";

export default function UserKategorijos() {
  const navigate = useNavigate();
  const dispatch = useDispatch();
  const [kategorijos, setKategorijos] = useState();

  useEffect(() => {
    axios
      .get(`${apiEndpoint}/api/kategorija/all`)
      .then((response) => {
        setKategorijos(response.data);
        dispatch(kategorijosActions.add(response.data));
      })
      .catch((error) => {
        swal({
          text: "Įvyko klaida. " + error.response.data,
          button: "Gerai",
        });
      });
  }, [dispatch]);

  const handleBrowse = (kategorijosID) => {
    navigate("/knygos/" + kategorijosID);
  };

  return (
    <>
      <Navigation />
      <Container>
        {!kategorijos && <Loading />}
        {kategorijos && (
          <table className="table">
            <thead>
              <tr>
                <th>Pavadinimas</th>
                <th>Peržiūrėti knygas</th>
              </tr>
            </thead>
            <tbody>
              {kategorijos.map((item) => (
                <tr key={item.id}>
                  <td>{item.pavadinimas}</td>
                  <td>
                    <button
                      className="btn btn-primary"
                      onClick={() => handleBrowse(item.id)}
                    >
                      Žiūrėti
                    </button>
                  </td>
                </tr>
              ))}
            </tbody>
          </table>
        )}
      </Container>
    </>
  );
}
