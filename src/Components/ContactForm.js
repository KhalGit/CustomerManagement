import React, { useState } from "react";
import api from "../api";

function ContactForm({operation}) {
  const [formData, setFormData] = useState({
    contactName: "",
    customerAFM: "",
    email: "",
    phone: "",
    relation: "",
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
        api.post("/contact", formData)
          .then((response) => console.log("Contact created:", response.data))
          .catch((error) => console.error("Error creating contact:", error));
        break;
      case "Update":
        console.log("Update Submitted");
        api.put(`/contact/${formData.AFM}`, formData)
          .then((response) => console.log("Contact updated:", response.data))
          .catch((error) => console.error("Error updating contact:", error));
        break;
      default:
        console.log("Unknown operation");
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
        name="contactName"
        placeholder="Contact Name"
        value={formData.contactName}
        onChange={handleChange}
      />
      <input
        type="text"
        name="customerAFM"
        placeholder="Customer AFM"
        value={formData.customerAFM}
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
      <input
        type="tel"
        name="phone"
        placeholder="Phone"
        value={formData.phone}
        onChange={handleChange}
        required
      />
      <input
        type="text"
        name="relation"
        placeholder="Relation"
        value={formData.relation}
        onChange={handleChange}
        required
      />
      <button type="submit" style={{ padding: "10px", cursor: "pointer" }}>
        Submit
      </button>
    </form>
  );
}

export default ContactForm;
