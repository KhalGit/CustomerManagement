import React from "react";
import BackButton from "./BackButton";
import CustomerForm from "./CustomerForm";
import api from "../api";

function Customer() {
  return (
    <div>
      <BackButton />
      <h1>Customer Page</h1>
      <CustomerForm />
    </div>
  );
}

export default Customer;
