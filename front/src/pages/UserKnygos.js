import Container from "../components/UI/Container";
import { useEffect, useState } from "react";
import { useSelector } from "react-redux";
import axios from "axios";
import apiEndpoint from "../components/Services/apiEndpoint";
import swal from "sweetalert";
import Loading from "../components/UI/Loading";
import Navigation from "../components/UI/Navigation";

export default function UserKnygos() {
  const [knygos, setKnygos] = useState();
  const kategorijos = useSelector((state) => state.kategorijos.kategorijos);

  useEffect(() => {
    axios
      .get(`${apiEndpoint}/api/knyga/all`)
      .then((response) => {
        setKnygos(response.data);
      })
      .catch((error) => {
        swal({
          text: "Įvyko klaida. " + error.response.data,
          button: "Gerai",
        });
      });
  }, []);

  const handleRezervuoti = () => {
    alert("funkcija dar neįdiegta");
  };

  const handleAddToList = (id) => {
    alert("funkcija dar neįdiegta");
  };

  return (
    <>
      <Navigation />
      <Container>
        {!knygos && <Loading />}
        {knygos && (
          <table className="table">
            <thead>
              <tr>
                <th>ID</th>
                <th>Pavadinimas</th>
                <th>Santrauka</th>
                <th>ISBN</th>
                <th>Puslapių</th>
                <th>Kategorija</th>
                <th>Veiksmai</th>
              </tr>
            </thead>
            <tbody>
              {knygos.map((item) => (
                <tr key={item.id}>
                  <td>{item.id}</td>
                  <td>{item.pavadinimas}</td>
                  <td>{item.santrauka}</td>
                  <td>{item.isbn}</td>
                  <td>{item.puslapiai}</td>
                  <td>
                    {
                      kategorijos.find(
                        (kategorija) => kategorija.id === item.kategorijaId
                      ).pavadinimas
                    }
                  </td>
                  <td>
                    <button
                      className="btn btn-primary me-2"
                      onClick={() => handleRezervuoti(item.id)}
                    >
                      Rezervuoti
                    </button>
                    <button
                      className="btn btn-success me-2"
                      onClick={() => handleAddToList(item.id)}
                    >
                      Į mėgstamiausių sąrašą
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
