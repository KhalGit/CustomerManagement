import React, { useState } from "react";
import api from "../api";

function ContReadDel({ operation }) {

  const [formData, setFormData] = useState({ AFM: "" });
  
    const handleChange = (e) => {
      const { name, value } = e.target;
      setFormData({ ...formData, [name]: value });
    };

  const handleSubmit = (e) => {
    e.preventDefault();

    // Perform the appropriate operation based on the selected operation
    switch (operation) {
      case "Read":
        console.log("Read Submitted");
        api.get(`/contact/${formData.id}`)
          .then((response) => console.log("Contact Read:", response.data))
          .catch((error) => console.error("Error Reading contact:", error));
        break;
      case "Delete":
        console.log("Delete Submitted");
        api.delete(`/contact/${formData.id}`)
          .then((response) => console.log("Contact Deleted:", response.data))
          .catch((error) => console.error("Error deleting contact:", error));
        break;
      default:
        console.log("No operation selected");
    }
  };

  return (
    <div
    style={{
      display: "flex",
      flexDirection: "column",
      gap: "15px",
      width: "300px",
      margin: "0 auto",
      marginTop: "50px",
    }}
    >
      <h2>{operation === "Read" ? "Read Contact" : "Delete Contact"}</h2>
      <form
      onSubmit={handleSubmit}
      style={{
        display: "flex",
        flexDirection: "column",
        gap: "15px",
        width: "300px",
        margin: "0 auto",
        marginTop: "50px",
      }}>
        <label>
        <input
        type="number"
        name="AFM"
        onChange={handleChange}
        required
        placeholder="Enter Contact ID" />
        </label>
        <button type="submit" style={{ padding: "10px", cursor: "pointer" }}>
        Submit
      </button>
      </form>
    </div>
  );
}

export default ContReadDel;