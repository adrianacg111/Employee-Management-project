import React, { useEffect, useState } from "react";
import axios from "axios";
import { useParams , Link} from "react-router-dom"; // Importa useParams

function EmployeeDetails() {
  const { id } = useParams(); // Usa useParams para obtener el parámetro id

  const [employee, setEmployee] = useState(null);

  useEffect(() => {
    if (id) {
      axios.get(`http://localhost:8080/api/employees/${id}`)
        .then(response => {
          setEmployee(response.data);
        })
        .catch(error => {
          console.error("There was an error fetching the employee details!", error);
        });
    }
  }, [id]); // Dependencia de id, se volverá a ejecutar cuando cambie

  return (
    <div>
      {employee ? (
        <div>
          <h1>Employee Details</h1>
          <h3>Employee Section</h3>
          <p>First Name: {employee.firstName}</p>
          <p>Middle Name: {employee.middleName}</p>
          <p>Last Name: {employee.lastName}</p>
          <p>Location City: {employee.locationCity}</p>
          <p>Address: {employee.address}</p>
          <p>Date of Birth: {employee.dateOfBirth}</p>
          <p>Telephone: {employee.telephone}</p>

          <h3>Position Section</h3>
          <p>Position Title: {employee.positionTitle}</p>
          <p>Hire Date: {employee.hireDate}</p>
          <p>Email: {employee.email}</p>
          <p>Salary: {employee.salary}</p>
          <p>Time in Position: {employee.timeInPosition}</p>
        </div>
      ) : (
        <p>Loading...</p>
      )}
        <Link to="/">Back</Link>
    </div>
  );
}

export default EmployeeDetails;
