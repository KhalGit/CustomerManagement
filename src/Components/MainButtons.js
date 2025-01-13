import React from "react";

import api from "../api";

function MainButtons({ activeButton, setActiveButton, setActiveForm }) {
  const buttons = [
    { label: "Customer", form: "customer" },
    { label: "Contact", form: "contact" },
    { label: "Order", form: "order" },
  ];

  return (
    <div
      style={{
        display: "flex",
        flexDirection: "row",
        justifyContent: "center",
        gap: "20px",
        marginBottom: "30px",
      }}
    >
      {buttons.map((button) => (
        <button
          key={button.form}
          style={{
            padding: "10px 20px",
            fontSize: "16px",
            cursor: "pointer",
            backgroundColor: activeButton === button.form ? "#444" : "#666",
            color: "white",
            borderRadius: "5px",
            border: "none",
            boxShadow: "0px 4px 6px rgba(0, 0, 0, 0.3)",
            transition: "background-color 0.3s",
          }}
          onMouseEnter={(e) => (e.target.style.backgroundColor = "#777")}
          onMouseLeave={(e) =>
            (e.target.style.backgroundColor =
              activeButton === button.form ? "#262626" : "#666")
          }
          onClick={() => {
            setActiveButton(button.form);  
          }}
        >
          {button.label}
        </button>
      ))}
    </div>
  );
}

export default MainButtons;