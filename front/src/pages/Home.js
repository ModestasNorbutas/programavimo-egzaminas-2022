import { useSelector } from "react-redux";
import Navigation from "../components/UI/Navigation";
import UserHome from "./UserHome";
import ManagerHome from "./ManagerHome";
import AdminHome from "./AdminHome";

export default function Home() {
  const role = useSelector((state) => state.auth.role);

  return (
    <>
      <Navigation />
      {role === "USER" && <UserHome />}
      {role === "MANAGER" && <ManagerHome />}
      {role === "ADMIN" && <AdminHome />}
    </>
  );
}
