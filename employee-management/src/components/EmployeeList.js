import React, { useEffect, useState } from "react";
import axios from "axios";
import { Link } from "react-router-dom";


function EmployeeList() {
  const [employees, setEmployees] = useState([]);

  useEffect(() => {
    axios.get("http://localhost:8080/api/employees")
      .then(response => {
        setEmployees(response.data);
      })
      .catch(error => {
        console.error("There was an error fetching the employees!", error);
      });
  }, []);

  return (
    <div>
      <h1>Employee List</h1>
      <table>
        <thead>
          <tr>
            <th>First Name</th>
            <th>Last Name</th>
            <th>Position Title</th>
            <th>Date Arrival</th>
            <th>Estatus</th>
            <th>Actions</th>
          </tr>
        </thead>
        <tbody>
          {employees.map(employee => (
            <tr key={employee.id}>
              <td>{employee.firstName}</td>
              <td>{employee.lastName}</td>              
              <td>{employee.positionTitle}</td>
              <td>{employee.dateArrival}</td>
              <td>{employee.status}</td>
              <td>
                <Link to={`/employee/${employee.id}`}>View </Link>
                <Link to={`/edit/${employee.id}`}>Edit</Link>
              </td>
            </tr>
          ))}
        </tbody>
      </table>
      <Link to="/add">Add Employee</Link>
    </div>
  );
}

export default EmployeeList;
