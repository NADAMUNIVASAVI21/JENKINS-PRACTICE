import React, { useState, useEffect } from 'react';
import axios from 'axios';
import './style.css';
import config from './config.js';

const ShoeManager = () => {
  const [shoes, setShoes] = useState([]);
  const [shoe, setShoe] = useState({
    id: '',
    brand: '',
    model: '',
    size: '',
    color: '',
    category: '',
    price: '',
    stock: '',
    description: ''
  });
  const [idToFetch, setIdToFetch] = useState('');
  const [fetchedShoe, setFetchedShoe] = useState(null);
  const [message, setMessage] = useState('');
  const [editMode, setEditMode] = useState(false);

  const baseUrl = `${config.url}/shoeapi`;

  useEffect(() => {
    fetchAllShoes();
  }, []);

  const fetchAllShoes = async () => {
    try {
      const res = await axios.get(`${baseUrl}/all`);
      setShoes(res.data);
    } catch (error) {
      setMessage('Failed to fetch shoes.');
    }
  };

  const handleChange = (e) => {
    setShoe({ ...shoe, [e.target.name]: e.target.value });
  };

  const validateForm = () => {
    for (let key in shoe) {
      if (!shoe[key] || shoe[key].toString().trim() === '') {
        setMessage(`Please fill out the ${key} field.`);
        return false;
      }
    }
    return true;
  };

  const addShoe = async () => {
    if (!validateForm()) return;
    try {
      await axios.post(`${baseUrl}/add`, shoe);
      setMessage('Shoe added successfully.');
      fetchAllShoes();
      resetForm();
    } catch (error) {
      setMessage('Error adding shoe.');
    }
  };

  const updateShoe = async () => {
    if (!validateForm()) return;
    try {
      await axios.put(`${baseUrl}/update`, shoe);
      setMessage('Shoe updated successfully.');
      fetchAllShoes();
      resetForm();
    } catch (error) {
      setMessage('Error updating shoe.');
    }
  };

  const deleteShoe = async (id) => {
    try {
      const res = await axios.delete(`${baseUrl}/delete/${id}`);
      setMessage(res.data);
      fetchAllShoes();
    } catch (error) {
      setMessage('Error deleting shoe.');
    }
  };

  const getShoeById = async () => {
    try {
      const res = await axios.get(`${baseUrl}/get/${idToFetch}`);
      setFetchedShoe(res.data);
      setMessage('');
    } catch (error) {
      setFetchedShoe(null);
      setMessage('Shoe not found.');
    }
  };

  const handleEdit = (sh) => {
    setShoe(sh);
    setEditMode(true);
    setMessage(`Editing shoe with ID ${sh.id}`);
  };

  const resetForm = () => {
    setShoe({
      id: '',
      brand: '',
      model: '',
      size: '',
      color: '',
      category: '',
      price: '',
      stock: '',
      description: ''
    });
    setEditMode(false);
  };

  return (
    <div className="shoe-container">

      {message && (
        <div className={`message-banner ${message.toLowerCase().includes('error') ? 'error' : 'success'}`}>
          {message}
        </div>
      )}

      <h2>Shoe Management</h2>

      <div>
        <h3>{editMode ? 'Edit Shoe' : 'Add Shoe'}</h3>
        <div className="form-grid">
          <input type="number" name="id" placeholder="ID" value={shoe.id} onChange={handleChange} />
          <input type="text" name="brand" placeholder="Brand" value={shoe.brand} onChange={handleChange} />
          <input type="text" name="model" placeholder="Model" value={shoe.model} onChange={handleChange} />
          <input type="text" name="size" placeholder="Size" value={shoe.size} onChange={handleChange} />
          <input type="text" name="color" placeholder="Color" value={shoe.color} onChange={handleChange} />
          <select name="category" value={shoe.category} onChange={handleChange}>
            <option value="">Select Category</option>
            <option value="Sports">Sports</option>
            <option value="Casual">Casual</option>
            <option value="Formal">Formal</option>
          </select>
          <input type="number" name="price" placeholder="Price" value={shoe.price} onChange={handleChange} />
          <input type="number" name="stock" placeholder="Stock" value={shoe.stock} onChange={handleChange} />
          <textarea name="description" placeholder="Description" value={shoe.description} onChange={handleChange}></textarea>
        </div>

        <div className="btn-group">
          {!editMode ? (
            <button className="btn-blue" onClick={addShoe}>Add Shoe</button>
          ) : (
            <>
              <button className="btn-green" onClick={updateShoe}>Update Shoe</button>
              <button className="btn-gray" onClick={resetForm}>Cancel</button>
            </>
          )}
        </div>
      </div>

      <div>
        <h3>Get Shoe By ID</h3>
        <input
          type="number"
          value={idToFetch}
          onChange={(e) => setIdToFetch(e.target.value)}
          placeholder="Enter ID"
        />
        <button className="btn-blue" onClick={getShoeById}>Fetch</button>

        {fetchedShoe && (
          <div>
            <h4>Shoe Found:</h4>
            <pre>{JSON.stringify(fetchedShoe, null, 2)}</pre>
          </div>
        )}
      </div>

      <div>
        <h3>All Shoes</h3>
        {shoes.length === 0 ? (
          <p>No shoes found.</p>
        ) : (
          <div className="table-wrapper">
            <table>
              <thead>
                <tr>
                  {Object.keys(shoe).map((key) => (
                    <th key={key}>{key}</th>
                  ))}
                  <th>Actions</th>
                </tr>
              </thead>
              <tbody>
                {shoes.map((sh) => (
                  <tr key={sh.id}>
                    {Object.keys(shoe).map((key) => (
                      <td key={key}>{sh[key]}</td>
                    ))}
                    <td>
                      <div className="action-buttons">
                        <button className="btn-green" onClick={() => handleEdit(sh)}>Edit</button>
                        <button className="btn-red" onClick={() => deleteShoe(sh.id)}>Delete</button>
                      </div>
                    </td>
                  </tr>
                ))}
              </tbody>
            </table>
          </div>
        )}
      </div>

    </div>
  );
};

export default ShoeManager;
