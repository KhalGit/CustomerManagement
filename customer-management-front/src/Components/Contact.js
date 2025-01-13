import React from "react";
import BackButton from "./BackButton";
import ContactForm from "./ContactForm";
import api from "../api";

function Contact() {
  return (
    <div>
      <BackButton />
      <h1>Contact Page</h1>
      <ContactForm />
    </div>
  );
}

export default Contact;
