
import React, { useState } from "react";
import ActionGrade from '../Components/ActionGrade';

function GradeList() {
    const [isOpen, setIsOpen] = useState(false);

    const toggleModal = () => {
        setIsOpen(!isOpen);
    };

    let [classes, setClasses] = useState([])
    let [gradeId, setGradeId] = useState('')
  
    let findGrade = () =>
      {
        fetch('http://localhost:8081/grade/read').then(response => {
          if(!response.ok){
            throw new Error('Erreur lors de la création de la classe');
          }
          return response.json();
        }).then(data => {
            setClasses(data) 
        }).catch(error =>{
            alert(error.message)
        });
      }
      findGrade()
    //   console.log(classes)

    let deleteGrade = (delGradeId) =>
        {
            fetch('http://localhost:8081/grade/delete/' + delGradeId, {method : 'delete'}).then(response => {
                if(!response.ok){
                  throw new Error('Erreur lors de la suppression de la classe');
                }
                return response.json();
              }).then(data => { 
                  alert('Classe supprimé')
              }).catch(error =>{
                alert(error.message)
            });
      
        }
  return (
    <div className="content">
        <h2>Gerer les Classes</h2>
        <hr/>
        <div className="row">

            <ActionGrade/>
            {/* Corp du modal pour la modification */}
            <div>
                
                {isOpen && (
                    <div className="modal-overlay">
                        <div className="modal-content"> 
                        <div className="modal-close" onClick={toggleModal}> &times; </div>
                            <h2>Modification</h2> 
                            <p>Modifier les informations de la classe.</p>
                            <div className="row">
                            <div className="col-25">
                                    <label for="fname">Nom </label>
                                </div>
                                <div class="col-75">
                                    <input type="text" id="fname" name="firstname" placeholder="Libelle du grade .." />
                                    {/* value={gradeName} onChange={(e) => {setGradeName(e.target.value)}} */}
                                </div>
                                
                                
                                <div className="col-25">
                                <label for="finventory">Effictif </label>
                                </div>

                                <div className="col-75">
                                    <input type="text" id="finventory" name="firstname" placeholder="Effectif .."  />
                                    {/* value={inventory} onChange={(e) => {setInventory(e.target.value)}} */}
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
                <h2>Liste des classes </h2>
                <p> Le tableau suivant contient les classes enregistres dans l'etablissement</p>

                <div className="container">
                    <table>
                    <tr>
                            <th>Identifiant</th>
                            <th>Nom</th>
                            <th>Effectif</th>
                            <th >Actions</th>
                        </tr>

                            {classes.map((grade) => { 
                                return(
                                <tr>
                                    <td>{grade.gradeId}</td>
                                    <td>{grade.gradeName}</td>
                                    <td>{grade.inventory}</td>
                                    <td>
                                    <button onClick={toggleModal} className="btn default">Mettre a jour</button>
                                <button onClick={() => {
                                            deleteGrade(grade.gradeId)}} className="btn danger">Supprimer</button>
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

export default GradeList;
