import ReactDOM from "react-dom";
import styles from "./Loading.module.scss";

export default function Loading() {
  const Content = () => {
    return (
      <div className={styles.container}>
        <h4 className={styles.loading}>Loading</h4>
        <div className={styles.spinner}></div>
      </div>
    );
  };

  return ReactDOM.createPortal(<Content />, document.getElementById("portal"));
}
