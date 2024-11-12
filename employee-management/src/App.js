import React from "react";
import { BrowserRouter as Router, Routes, Route } from "react-router-dom";
import EmployeeList from "./components/EmployeeList";
import EmployeeDetails from "./components/EmployeeDetails";
import AddEditEmployee from "./components/AddEditEmployee";

function App() {
  return (
    <Router>
      <Routes>
        <Route path="/" element={<EmployeeList />} />
        <Route path="/employee/:id" element={<EmployeeDetails />} />
        <Route path="/add" element={<AddEditEmployee />} />
        <Route path="/edit/:id" element={<AddEditEmployee />} />
      </Routes>
    </Router>
  );
  
}

export default App;
