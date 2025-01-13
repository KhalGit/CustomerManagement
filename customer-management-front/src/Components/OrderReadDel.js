import React, {useState} from "react";
import api from "../api";

function OrderReadDel({ operation }) {

  const [formData, setFormData] = useState({ id: "" });
  
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
        api.get(`/order/${formData.id}`)
          .then((response) => console.log("Order Read:", response.data))
          .catch((error) => console.error("Error Reading order:", error));
        break;
      case "Delete":
        console.log("Delete Submitted");
        api.delete(`/order/${formData.id}`)
          .then((response) => console.log("Order Deleted:", response.data))
          .catch((error) => console.error("Error deleting order:", error));
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
      <h2>{operation === "Read" ? "Read Order" : "Delete Order"}</h2>
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
        name="id"
        onChange={handleChange}
        required
        placeholder="Enter Order ID" />
        </label>
        <button type="submit" style={{ padding: "10px", cursor: "pointer" }}>
        Submit
      </button>
      </form>
    </div>
  );
}

export default OrderReadDel;