import React from "react";
import BackButton from "./BackButton";
import OrderForm from "./OrderForm";
import api from "../api";

function Order() {
  return (
    <div>
      <BackButton />
      <h1>Order Page</h1>
      <OrderForm />
    </div>
  );
}

export default Order;
