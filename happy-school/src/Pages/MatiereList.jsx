
import React, { useState } from "react";
import ActionMat from '../Components/ActionMat';

function MatiereList() {
    const [isOpen, setIsOpen] = useState(false);

    const toggleModal = () => {
        setIsOpen(!isOpen);
    };

  return (
    <div className="content">
        <h2>Gerer les Enseignants</h2>
        <hr/>
        <div className="row">

            <ActionMat/>

            {/* Corp du modal pour la modification */}
            <div>
                
                {isOpen && (
                    <div className="modal-overlay">
                        <div className="modal-content"> 
                        <div className="modal-close" onClick={toggleModal}> &times; </div>
                            <h2>Modification</h2> 
                            <p>Modifier les informations du Bureau.</p>
                            <div className="row">
                                <div className="col-25">
                                <label for="fname">Nom </label>
                                </div>
                                <div className="col-75">
                                <input type="text" id="fname" name="firstname" placeholder="Libelle du bureau .."/>
                                </div>
                            </div>

                            <div className="row">
                                <div className="col-25">
                                <label for="subject">Description</label>
                                </div>
                                <div className="col-75">
                                <textarea id="subject" name="subject" placeholder="Description du bureau .."  style={{ height:200 + 'px' }}></textarea>
                                </div>
                            </div>
                            
                            <div className="modal-footer">
                                <button onClick={toggleModal}>Valider</button>
                            </div>
                        </div>
            </div>
            // fin du corp du modal
            
      )}
    </div>

            <div className="column2">
                <h2>Liste des Matieres </h2>
                <p> Le tableau suivant contient les Matieres enregistres dans l'etablissement</p>

                <div className="container">
                    <table>
                        <tr>
                            <th>Nom & prenom </th>
                            <th>Adresse</th>
                            <th>Contact</th>
                            <th>Actions</th>
                        </tr>
                        <tr>
                            <td>Jill</td>
                            <td> carrefour manguier </td>
                            <td>650457812</td>
                            <td>
                                <button onClick={toggleModal} className="btn default">mettre a jour</button>
                                <button onClick={toggleModal} className="btn danger">Supprimer</button>
                            </td>
                        </tr>
                        <tr>
                            <td>Eve</td>
                            <td> nomeny </td>
                            <td>678954812</td>
                            <td>
                                <button onClick={toggleModal} className="btn default">mettre a jour</button>
                                <button onClick={toggleModal} className="btn danger">Supprimer</button>
                            </td>
                        </tr>
                        <tr>
                            <td>Adam</td>
                            <td> carrefour danger </td>
                            <td>675789812</td>
                            <td>
                                <button onClick={toggleModal} className="btn default">mettre a jour</button>
                                <button onClick={toggleModal} className="btn danger">Supprimer</button>
                            </td>
                        </tr>
                    </table>
                </div>
            </div>
        </div>
    </div>
  );
}

export default MatiereList;
