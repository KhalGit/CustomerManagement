import React, {useState} from "react";
import api from "../api";

function CustReadDel({ operation }) {
  
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
        api.get(`/customer/${formData.AFM}`)
        .then((response) => console.log("Customer Data:", response.data))
        .catch((error) => console.error("Error fetching customer:", error));
        break;
      case "Delete":
        console.log("Delete Submitted");
        api.delete(`/customer/${formData.AFM}`)
        .then(() => console.log("Customer Deleted"))
        .catch((error) => console.error("Error deleting customer:", error));
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
      <h2>{operation === "Read" ? "Read customer" : "Delete customer"}</h2>
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
        placeholder="Enter Customer AFM" />
        </label>
        <button type="submit" style={{ padding: "10px", cursor: "pointer" }}>
        Submit
      </button>
      </form>
    </div>
  );
}

export default CustReadDel;