import React, { useState, useEffect } from "react";
import axios from "axios";
import { useNavigate, useParams, Link } from "react-router-dom";

function AddEditEmployee() {
  const [employee, setEmployee] = useState({
    firstName: "",
    middleName: "",
    lastName: "",
    locationCity: "",
    address: "",
    dateArrival: "",
    dateOfBirth: "",
    telephone: "",
    positionTitle: "",
    hireDate: "",
    email: "",
    salary: "",
    status: "",
  });

  const [errors, setErrors] = useState({});
  const [serverError, setServerError] = useState("");
  const { id } = useParams();
  const navigate = useNavigate();
  const [cityError, setCityError] = useState("");

  useEffect(() => {
    if (id) {
      axios.get(`http://localhost:8080/api/employees/${id}`)
        .then(response => {
          setEmployee(response.data);
        })
        .catch(error => {
          console.error("There was an error fetching the employee!", error);
        });
    }
  }, [id]);

  const handleChange = (e) => {
    setEmployee({ ...employee, [e.target.name]: e.target.value });
  };

  const validate = () => {
    const newErrors = {};
    if (!employee.firstName) newErrors.firstName = "First name is required";
    if (!employee.middleName) newErrors.middleName = "Middle name is required";
    if (!employee.lastName) newErrors.lastName = "Last name is required";
    if (!employee.locationCity) newErrors.locationCity = "Location city is required";
    if (!employee.address) newErrors.address = "Address is required";
    if (!employee.dateArrival) newErrors.dateArrival = "Date of arrival is required";
    if (!employee.dateOfBirth) newErrors.dateOfBirth = "Date of birth is required";
    if (!employee.telephone) newErrors.telephone = "Telephone is required";
    if (!employee.positionTitle) newErrors.positionTitle = "Position title is required";
    if (!employee.hireDate) newErrors.hireDate = "Hire date is required";
    if (!employee.email) {
      newErrors.email = "Email is required";
    } else if (!/\S+@\S+\.\S+/.test(employee.email)) {
      newErrors.email = "Invalid email format";
    }
    if (!employee.salary) newErrors.salary = "Salary is required";
    if (!employee.status) newErrors.status = "Status is required";

    setErrors(newErrors);
    return Object.keys(newErrors).length === 0;
  };

  const handleCityChange = async (e) => {
    const city = e.target.value;
    try {
      const response = await axios.get(`https://api.opencagedata.com/geocode/v1/json?q=${city}&key=56eceecc22a34c10b83a3788effd4794`);
      if (response.data.results.length > 0) {
        setCityError(""); // Limpiar error si la ciudad es válida
      } else {
        setCityError("La ciudad de ubicación no es válida."); // Establecer error si la ciudad no es válida
      }
    } catch (error) {
      setCityError("Hubo un error al verificar la ciudad.");
    }
    setEmployee({ ...employee, locationCity: city });
  };

  const handleSubmit = (e) => {
    e.preventDefault();
    setServerError("");

    if (!validate()) {
      return;
    }

    const submitRequest = id
      ? axios.put(`http://localhost:8080/api/employees/${id}`, employee)
      : axios.post("http://localhost:8080/api/employees", employee);

    submitRequest
      .then(() => {
        navigate("/");
      })
      .catch(error => {
        if (error.response && error.response.data) {
          const errorMessage = error.response.data; // El mensaje de error que viene desde el backend
          setServerError(errorMessage);  // Guardamos el mensaje como texto en el estado
        } else {
          setServerError("Hubo un error al procesar la solicitud.");
          console.error("There was an error saving the employee!", error);
        }
  });
  };

  return (
    <div>
      <h1>{id ? "Edit Employee" : "Add Employee"}</h1>
      {serverError && <p style={{ color: 'red' }}>{serverError}</p>}
      <form onSubmit={handleSubmit}>
      <label htmlFor="firstName">First Name</label>
        <input
          type="text"
          name="firstName"
          value={employee.firstName}
          onChange={handleChange}
          placeholder="First Name"
        />
        {errors.firstName && <p style={{ color: 'red' }}>{errors.firstName}</p>}
        <label htmlFor="middleName">Middle Name</label>
        <input
          type="text"
          name="middleName"
          value={employee.middleName}
          onChange={handleChange}
          placeholder="Middle Name"
        />
        {errors.middleName && <p style={{ color: 'red' }}>{errors.middleName}</p>}
        <label htmlFor="lastName">Last Name</label>
        <input
          type="text"
          name="lastName"
          value={employee.lastName}
          onChange={handleChange}
          placeholder="Last Name"
        />
        {errors.lastName && <p style={{ color: 'red' }}>{errors.lastName}</p>}
        <label htmlFor="locationCity">Location City</label>
        <input
          type="text"
          name="locationCity"
          value={employee.locationCity}
          onChange={handleCityChange} // Usamos handleCityChange aquí
          placeholder="Location City"
          required
        />
        {cityError && <p style={{ color: 'red' }}>{cityError}</p>} {/* Mostrar error de ciudad */}
       
        {errors.locationCity && <p style={{ color: 'red' }}>{errors.locationCity}</p>}
        <label htmlFor="address">Address</label>
        <input
          type="text"
          name="address"
          value={employee.address}
          onChange={handleChange}
          placeholder="Address"
        />
        {errors.address && <p style={{ color: 'red' }}>{errors.address}</p>}
        <label htmlFor="dateArrival">Date Arrival</label>
        <input
          type="date"
          name="dateArrival"
          value={employee.dateArrival}
          onChange={handleChange}
        />
        {errors.dateArrival && <p style={{ color: 'red' }}>{errors.dateArrival}</p>}
        <label htmlFor="dateOfBirth">Date Of Birth</label>
        <input
          type="date"
          name="dateOfBirth"
          value={employee.dateOfBirth}
          onChange={handleChange}
        />
        {errors.dateBirth && <p style={{ color: 'red' }}>{errors.dateBirth}</p>}
        <label htmlFor="telephone">Telephone</label>
        <input
          type="tel"
          name="telephone"
          value={employee.telephone}
          onChange={handleChange}
          placeholder="Telephone"
        />
        {errors.telephone && <p style={{ color: 'red' }}>{errors.telephone}</p>}
        <label htmlFor="positionTitle">Position Title</label>
        <input
          type="text"
          name="positionTitle"
          value={employee.positionTitle}
          onChange={handleChange}
          placeholder="Position Title"
        />
        {errors.positionTitle && <p style={{ color: 'red' }}>{errors.positionTitle}</p>}
        <label htmlFor="hireDate">Hire Date</label>
        <input
          type="date"
          name="hireDate"
          value={employee.hireDate}
          onChange={handleChange}
        />
        {errors.hireDate && <p style={{ color: 'red' }}>{errors.hireDate}</p>}
        <label htmlFor="email">Email</label>
        <input
          type="email"
          name="email"
          value={employee.email}
          onChange={handleChange}
          placeholder="Email"
        />
        {errors.email && <p style={{ color: 'red' }}>{errors.email}</p>}
        <label htmlFor="salary">Salary</label>
        <input
          type="number"
          name="salary"
          value={employee.salary}
          onChange={handleChange}
          placeholder="Salary"
        />
        {errors.salary && <p style={{ color: 'red' }}>{errors.salary}</p>}
        <label htmlFor="status">Status</label>
        <input
          type="text"
          name="status"
          value={employee.status}
          onChange={handleChange}
          placeholder="Status"
        />
        {errors.status && <p style={{ color: 'red' }}>{errors.status}</p>}

        <button type="submit">{id ? "Update Employee" : "Add Employee"}</button>
        {serverError && <p style={{ color: 'red' }}>{serverError}</p>}
        <Link to="/">Back</Link>
      </form>
     
    </div>
    
  );
}

export default AddEditEmployee;
