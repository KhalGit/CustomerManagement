import React, { useState } from "react";
import CustomerForm from "./CustomerForm";
import ContactForm from "./ContactForm";
import OrderForm from "./OrderForm";
import CustReadDel from "./CustReadDel";
import ContReadDel from "./ContReadDel";
import OrderReadDel from "./OrderReadDel";
import { Zoom } from "@mui/material";
import CrudButtons from "./CrudButtons";
import MainButtons from "./MainButtons";
import api from "../api";

function Home() {
  const [activeButton, setActiveButton] = useState("");
  const [crudOperation, setCrudOperation] = useState("");

  const renderForm = () => {
    if (!activeButton || !crudOperation) return null; // No active button or operation, don't render any form

    // Render based on active button (customer, contact, order)
    switch (activeButton) {
      case "customer":
        if (crudOperation === "Read") {
          return <CustReadDel operation="Read" />;
        } else if (crudOperation === "Delete") {
          return <CustReadDel operation="Delete" />;
        } else {
          return <CustomerForm operation={crudOperation} />;
        }
      case "contact":
        if (crudOperation === "Read") {
          return <ContReadDel operation="Read" />;
        } else if (crudOperation === "Delete") {
          return <ContReadDel operation="Delete" />;
        } else {
          return <ContactForm operation={crudOperation} />;
        }
      case "order":
        if (crudOperation === "Read") {
          return <OrderReadDel operation="Read" />;
        } else if (crudOperation === "Delete") {
          return <OrderReadDel operation="Delete" />;
        } else {
          return <OrderForm operation={crudOperation} />;
        }
      default:
        return null;
    }
  };

  return (
    <div
      style={{
        display: "flex",
        flexDirection: "column",
        alignItems: "center",
        backgroundColor: "#f5f5f5",
        minHeight: "100vh",
        padding: "30px",
        backgroundImage:
          "url('https://cdn.pixabay.com/photo/2020/11/10/03/26/wood-panel-5728430_640.jpg')",
        backgroundSize: "cover",
        backgroundRepeat: "no-repeat",
        backgroundPosition: "center",
        backgroundAttachment: "fixed",
        boxShadow: "inset 0px 0px 10px #ccc",
      }}
    >
      <div
        style={{
          backgroundColor: "#333",
          color: "white",
          padding: "20px",
          borderRadius: "5px",
          width: "100%",
          textAlign: "center",
          marginBottom: "30px",
          boxShadow: "0px 4px 6px rgba(0, 0, 0, 0.1)",
        }}
      >
        <h1 style={{ margin: 0 }}>Customer Management</h1>
      </div>
      <MainButtons
        activeButton={activeButton}
        setActiveButton={setActiveButton}
      />
      {activeButton && (
        <CrudButtons setCrudOperation={setCrudOperation} />
      )}

      <Zoom in={!!activeButton && !!crudOperation}>
        <div
          style={{
            position: "relative",
            marginTop: "30px",
            padding: "20px",
            borderRadius: "5px",
            width: "100%",
            maxWidth: "500px",
            boxShadow: "0px 4px 6px rgba(0, 0, 0, 0.1)",
          }}
        >
          {renderForm()}
        </div>
      </Zoom>
    </div>
  );
}

export default Home;
