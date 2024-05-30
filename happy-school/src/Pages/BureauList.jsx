
import React, { useState } from "react";
import ActionBur from '../Components/ActionBur';

function BureauList() {
    const [isOpen, setIsOpen] = useState(false);
  const toggleModal = () => {
    setIsOpen(!isOpen);
};


    let [offices, setOffices] = useState([])
    let [officeId, setOfficeId] = useState('')
  
    let findOffice = () =>
      {
        fetch('http://localhost:8081/office/read').then(response => {
          if(!response.ok){
            throw new Error('Erreur lors de la création du bureau');
          }
          return response.json();
        }).then(data => {
            setOffices(data) 
        }).catch(error =>{
            alert(error.message)
        });
      }
      findOffice()
    //   console.log(offices)

    let deleteOffice = (delOfficeId) =>
        {
            fetch('http://localhost:8081/office/delete/' + delOfficeId, {method : 'delete'}).then(response => {
                if(!response.ok){
                  throw new Error('Erreur lors de la création du bureau');
                }
                return response.json();
              }).then(data => { 
                  alert('Bureau supprimé')
              }).catch(error =>{
                alert(error.message)
            });
      
        }

      console.log(officeId)

    function openModal(){
        var modal = document.getElementById("myModal");

        // Get the button that opens the modal
        var btn = document.getElementById("myBtn");

        // Get the <span> element that closes the modal
        var span = document.getElementsByClassName("close")[0];

        // When the user clicks the button, open the modal 
        btn.onclick = function() {
        modal.style.display = "block";
        }

        // When the user clicks on <span> (x), close the modal
        span.onclick = function() {
        modal.style.display = "none";
        }

        // When the user clicks anywhere outside of the modal, close it
        window.onclick = function(event) {
            if (event.target == modal) {
                modal.style.display = "none";
            }
        }
    }
        
  return (
    <div className="content">
        <h2>Gerer les Bureaux</h2>
        <hr/>
        <div className="row">

            <ActionBur/>
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
                            
                            <div className="modal-footer">
                                <button onClick={toggleModal}>Valider</button>
                            </div>
                        </div>
            </div>
            // fin du corp du modal
            
      )}
    </div>
            <div className="column2">
                <h2>Liste des bureaux </h2>
                <p> Le tableau suivant contient les bureaux enregistres dans l'etablissement</p>

                <div className="container">
                

                                                                            
        {/* <button id="myBtn"  onClick={openModal()} >Open Modal</button>

    
        <div id="myModal" class="modal">

            <div class="modal-content">
                <span class="close">&times;</span>
                <p>Some text in the Modal..</p>
            </div>

        </div> */}
                    
                        <table>
                        <tr>
                            <th>Identifiant</th>
                            <th>Nom</th>
                            <th>Actions</th>
                        </tr>

                            {offices.map((office) => { 
                                return(
                                <tr>
                                    <td>{office.officeId}</td>
                                    <td>{office.officeName}</td>
                                    <td>
                                        <button onClick={toggleModal} className="btn default">Mettre a jour</button>
                                        <button onClick={() => {
                                            deleteOffice(office.officeId)}} className="btn danger">Supprimer</button>

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

export default BureauList;
