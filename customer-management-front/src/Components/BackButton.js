import React from "react";
import { useNavigate } from "react-router-dom";
import api from "../api";

function BackButton() {
  const navigate = useNavigate();

  return (
    <button
      style={{
        position: "absolute",
        top: "10px",
        left: "10px",
        padding: "10px",
        fontSize: "14px",
        cursor: "pointer",
      }}
      onClick={() => navigate("/")} // Navigate to the previous page
    >
      Back
    </button>
  );
}

export default BackButton;
