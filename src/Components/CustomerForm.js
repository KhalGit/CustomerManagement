import React, { useState } from "react";
import api from "../api";

function CustomerForm( {operation} ) {
  const [formData, setFormData] = useState({
    lastName: "",
    firstName: "",
    AFM: "",
    phone: "",
    email: "",
  });

  const handleChange = (e) => {
    const { name, value } = e.target;
    setFormData({ ...formData, [name]: value });
  };

  const handleSubmit = (e) => {
    e.preventDefault();

    // Perform the appropriate operation based on the selected operation
    switch (operation) {
      case "Create":
        console.log("Create Submitted");
        api.post("/customer", formData)
          .then((response) => console.log("Customer Created:", response.data))
          .catch((error) => console.error("Error creating customer:", error));
        break;
      case "Update":
        console.log("Update Submitted");
        api.put(`/customer/${formData.id}`, formData)
          .then((response) => console.log("Customer Updated:", response.data))
          .catch((error) => console.error("Error updating customer:", error));
        break;
      default:
        console.log("No operation selected");
    }
  };


  return (
    <form
      onSubmit={handleSubmit}
      style={{
        display: "flex",
        flexDirection: "column",
        gap: "15px",
        width: "300px",
        margin: "0 auto",
        marginTop: "50px",
      }}
    >
      <input
        type="text"
        name="lastName"
        placeholder="Last Name"
        value={formData.lastName}
        onChange={handleChange}
        required
      />
      <input
        type="text"
        name="firstName"
        placeholder="First Name"
        value={formData.firstName}
        onChange={handleChange}
        required
      />
      <input
        type="tel"
        name="AFM"
        placeholder="AFM"
        value={formData.AFM}
        onChange={handleChange}
        required
      />
      <input
        type="tel"
        name="phone"
        placeholder="Phone"
        value={formData.phone}
        onChange={handleChange}
        required
      />
      <input
        type="email"
        name="email"
        placeholder="Email"
        value={formData.email}
        onChange={handleChange}
        required
      />

      <button type="submit" style={{ padding: "10px", cursor: "pointer" }}>
        Submit
      </button>
    </form>
  );
}

export default CustomerForm;
