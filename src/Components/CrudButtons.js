import React, { useState } from "react";
import api from "../api";

function CrudButtons({setCrudOperation}) {

  const [activeButton, setActiveButton] = useState("");
  const [activeForm, setActiveForm] = useState("");

  return (
    <div
      style={{
        display: "flex",
        flexDirection: "row",
        justifyContent: "center",
        gap: "20px",
        marginTop: "20px",
      }}
    >
      {["Create", "Read", "Update", "Delete"].map((operation) => (
        <button
          key={operation}
          style={{
            padding: "10px 20px",
            fontSize: "14px",
            cursor: "pointer",
            backgroundColor: activeButton === operation ? "#444" : "#666",
            color: "white",
            borderRadius: "5px",
            border: "none",
            boxShadow: "0px 4px 6px rgba(0, 0, 0, 0.3)",
            transition: "background-color 0.3s",
          }}
          onMouseEnter={(e) => (e.target.style.backgroundColor = "#777")}
          onMouseLeave={(e) =>
            (e.target.style.backgroundColor =
              activeButton === operation ? "#262626" : "#666")
            }
          onClick={() => {
            setActiveButton(operation);
            setActiveForm(operation);
            setCrudOperation(operation);
            }}
        >
          {operation}
        </button>
      ))}
    </div>
  );
}

export default CrudButtons;