
import React, { useState } from "react";
import ActionSection from '../Components/ActionSection';

function SectionList() {
  const [isOpen, setIsOpen] = useState(false);

  const toggleModal = () => {
      setIsOpen(!isOpen);
  };

    let [sections, setSections] = useState([])
    // let [sectionId, setSectionId] = useState('')
  
    let findSection = () =>
      {
        fetch('http://localhost:8081/section/index').then(response => {
          if(!response.ok){
            throw new Error("Erreur lors de l'affichage des section");
          }
          return response.json();
        }).then(data => {
            setSections(data) 
        }).catch(error =>{
          alert(error.message)
        });
      }
      findSection()
    //   console.log(sections)

    let deleteSection = (delSectionId) =>
        {
            let data =  fetch('http://localhost:8081/section/delete/' + delSectionId, {method : 'delete'}).then(response => {
              // console.log(sectionId)
                if(!response.ok){
                  throw new Error('Erreur lors de la suppression de la section');
                }
                return response.json();
              }).then(data => { 
                  alert('Section supprimé')
              }).catch(error =>{
                alert(error.message)
            });
      
        }

  return (
    <div className="content">
        <h2>Gerer les Sections</h2>
        <hr/>
        <div className="row">

            <ActionSection/>
            {/* Corp du modal pour la modification */}
            <div>
                
                {isOpen && (
                    <div className="modal-overlay">
                        <div className="modal-content"> 
                        <div className="modal-close" onClick={toggleModal}> &times; </div>
                            <h2>Modification</h2> 
                            <p>Modifier les informations de la section.</p>
                            <div className="row">
                            <div className="col-25">
                            <label for="fname">Nom </label>
                            </div>
                            <div className="col-75">
                            <input type="text" id="fname" name="firstname" placeholder="Libelle de la section .." />
                            {/* value={sectionName} onChange={(e) => {setSectionName(e.target.value)}} */}
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
                <h2>Liste des Sections </h2>
                <p> Le tableau suivant contient les Sections enregistres dans l'etablissement</p>

                <div className="container">
                <table>
                <tr>
                            <th>Identifiant</th>
                            <th>Nom</th>
                            <th>Actions</th>
                        </tr>

                            {sections.map((section) => { 
                                return(
                                <tr>
                                    <td >{section.sectionId}</td>
                                    <td>{section.sectionName}</td>
                                    <td>
                                    <button onClick={toggleModal} className="btn default">Mettre a jour</button>
                                <button onClick={() => {
                                          deleteSection(section.sectionId)}} className="btn danger">Supprimer</button>

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

export default SectionList;
