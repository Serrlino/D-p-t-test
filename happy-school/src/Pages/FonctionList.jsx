
import React, { useState } from "react";
import ActionFonc from '../Components/ActionFonc';

function FonctionList() {

  const [isOpen, setIsOpen] = useState(false);

  const toggleModal = () => {
      setIsOpen(!isOpen);
  };


    let [fonctions, setFonctions] = useState([])
    let [fonctionName, setFonctionName] = useState('')
  
    let findFonction = () =>
      {
        fetch('http://localhost:8081/fonction/read').then(response => {
          if(!response.ok){
            throw new Error('Erreur lors de la création de la fonction');
          }
          return response.json();
        }).then(data => {
            setFonctions(data) 
            console.log(data)
        }).catch(error =>{
          alert(error.message)
        });
      }
      findFonction()

      let deleteFonction = (delFonctionName) =>
        {
            fetch('http://localhost:8081/fonction/delete/' + delFonctionName, {method : 'delete'}).then(response => {
                if(!response.ok){
                  throw new Error('Erreur lors de la création de la fonction');
                }
                return response.json();
              }).then(data => { 
                  alert('Fonction supprimé')
                  alert()
              }).catch(error =>{
                alert(error.message)
              });
      
        }

  return (
    <div className="content">
        <h2>Gerer les Fonctions</h2>
        <hr/>
        <div className="row">

            <ActionFonc/>
            {/* Corp du modal pour la modification */}
            <div>
                
                {isOpen && (
                    <div className="modal-overlay">
                        <div className="modal-content"> 
                        <div className="modal-close" onClick={toggleModal}> &times; </div>
                            <h2>Modification</h2> 
                            <p>Modifier les informations de la fonction.</p>
                            <div className="row">
                            <div className="col-25">
          <label for="fname">Nom </label>
        </div>
        <div className="col-75">
          <input type="text" id="fname" name="firstname" placeholder="Libelle de la fonction .." />
          {/* value={fonctionName} onChange={(e) => {setFonctionName(e.target.value)}} */}
        </div>
      </div>

      <div className="row">
        <div className="col-25">
          <label for="subject">Description</label>
        </div>
        <div className="col-75">
          <textarea id="subject" name="subject" placeholder="Description de la fonction .."  style={{ height:200 + 'px' }}  ></textarea>
          {/* value={description} onChange={(e) => {setDescription(e.target.value)}} */}
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
                <h2>Liste des Fonctions </h2>
                <p> Le tableau suivant contient les Fonctions enregistres dans l'etablissement</p>

                <div className="container">
                    <table>
                    <tr>
                            <th>Nom</th>
                            <th>Description</th>
                            <th>Actions</th>
                        </tr>

                            {fonctions.map((fonction) => { 
                                return(
                                <tr>
                                    <td>{fonction.fonctionName}</td>
                                    <td>{fonction.description}</td>
                                    <td>
                                    <button onClick={toggleModal} className="btn default">Mettre a jour</button>
                                <button onClick={() => {
                                             deleteFonction(fonction.fonctionName)}} className="btn danger">Supprimer</button>

                                    </td>
                                </tr>)
                            })}
                        </table>
                </div>
            </div>
        </div>
    </div>
  );
}

export default FonctionList;
