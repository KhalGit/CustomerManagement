import React, { useState } from "react";
import api from "../api";

function OrderForm({operation}) {
  const [formData, setFormData] = useState({
    customerAFM: "",
    contactId: "",
    amount: "",
    status: "",
    notes: "",
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
          api.post("/order", formData)
            .then((response) => console.log("Order created:", response.data))
            .catch((error) => console.error("Error creating order:", error));
          break;
        case "Update":
          console.log("Update Submitted");
          api.put(`/order/${formData.AFM}`, formData)
            .then((response) => console.log("Order updated:", response.data))
            .catch((error) => console.error("Error updating order:", error));
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
        name="customerAFM"
        placeholder="Customer AFM"
        value={formData.customerAFM}
        onChange={handleChange}
        required
      />
      <input
        type="text"
        name="contactId"
        placeholder="Contact ID"
        value={formData.contactId}
        onChange={handleChange}
        required
      />
      <input
        type="number"
        name="amount"
        placeholder="Amount"
        value={formData.amount}
        onChange={handleChange}
        required
      />
      <select
        name="status"
        value={formData.status}
        onChange={handleChange}
        required
      >
        <option value="" disabled>
          Select Status
        </option>
        <option value="PENDING">Pending</option>
        <option value="COMPLETED">Completed</option>
      </select>
      <textarea
        name="notes"
        placeholder="Notes"
        value={formData.notes}
        onChange={handleChange}
        rows="4"
      />
      <button type="submit" style={{ padding: "10px", cursor: "pointer" }}>
        Submit
      </button>
    </form>
  );
}

export default OrderForm;
